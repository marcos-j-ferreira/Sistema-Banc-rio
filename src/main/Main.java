package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface Conta{
    void depositar (double valor) throws SaldoInsuficienteException;
    void sacar (double valor) throws SaldoInsuficienteException;
    double getSaldo();
}

class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensage){
        super(mensage);
    }
}

class ContaPupanca implements Conta{
    private double saldo;

    ContaPupanca(double valor){
        this.saldo = valor;
    }

    @Override
    public void depositar(double valor) throws SaldoInsuficienteException{
        if(valor < 1){
            throw new SaldoInsuficienteException("Valor minimo 1$ real");
        }
        saldo += valor;  
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{

        if(valor > saldo){
            double result = valor - saldo;
            throw new SaldoInsuficienteException("Saldo insuficientes!\nSaldo: "+saldo+ " \nSaque: "+valor +"\nfaltam: "+ result );
        }
        saldo -= valor;
    }

    @Override
    public double getSaldo(){
        return saldo;
    }
}

class ContaCorrente implements Conta{
    private double limite = 100;
    private double saldo;

    ContaCorrente(double valor){
        this.saldo = valor + limite;
    }

    @Override
    public void depositar(double valor) throws SaldoInsuficienteException{
        if(valor < 1){
            throw new SaldoInsuficienteException("Valor minimo 1$ real");
        }
        saldo += valor;  
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{

        if(valor > saldo){
            double result = valor - saldo;
            throw new SaldoInsuficienteException("Saldo insuficientes!\nSaldo: "+saldo+ " \nSaque: "+valor +"\nfaltam: "+ result );
        }
        saldo -= valor;
    }

    @Override
    public double getSaldo(){
        return saldo;
    }
}

class Gerenciamento{
    private final Map<String, Conta> contas = new HashMap<>();
    private Conta contaC;
    private Conta contaP;

   Gerenciamento(){
    contaC = new ContaCorrente(0);
    contaP = new ContaPupanca(0);
    contas.put("Corrente", contaC);
    contas.put("Poupança", contaP);
   }

    public final void criarContas(){
        System.out.println("Contas criadas");
    }

    public final void transferir(char value, double valor){

        if( value == 'C' && contaC.getSaldo() >= valor){
            try{
                contaC.sacar(valor);
                contaP.depositar(valor);
                System.out.println("Valor transferido com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }else if(value == 'P' && contaP.getSaldo() >= valor){

            try{
                contaP.sacar(valor);
                contaC.depositar(valor);
                System.out.println("Valor transferido com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }else{
            System.out.println("Erro na transferencia");
        }
    }

    public final void sacar(char value, double valor){
        try{
            Conta conta = (value == 'C') ? contaC : contaP;
            conta.sacar(valor);
            System.out.println("Saque realizado!");
        }catch (SaldoInsuficienteException e){
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public final void depositar(char value, double valor){

        try{
            Conta conta = (value == 'C') ? contaC : contaP;
            conta.depositar(valor);
            System.out.println(" Deposito feito com sucesso");
        }catch (SaldoInsuficienteException e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }

    public final void saldos(){

        try{
            double saldoP = contaP.getSaldo();
            double saldoC = contaC.getSaldo();
            System.out.println(" --- Saldos das contas --- \nConta poupança: "+saldoP+"\nConta corrente: "+saldoC+" \n");

        } catch (Exception e ){
            System.out.println("Erro ao exibir saldo: "+ e);
        }
    }
}

class TestePopanca{
    static Conta c1 = new ContaPupanca(100);

    public static void TestSacar(double value){

        try{
            System.out.println("\n-- Teste de sacar dinheiro --");
            double value1 = c1.getSaldo();
            c1.sacar(value);
            double value2 = c1.getSaldo();
            System.out.println("Saldo: "+ value1+ "\nValor de saque: "+ value+"\nSaldo atual: "+value2);

        }catch (SaldoInsuficienteException e){
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    public static void TestDeposito(double value){

        try{
            System.out.println("\n-- Teste de deposito de dinheiro --");
            double value1 = c1.getSaldo();
            c1.depositar(value);
            double value2 = c1.getSaldo();
            System.out.println("Saldo: "+ value1+ "\nValor do deposito: "+ value+"\nSaldo atual: "+value2);

        }catch (SaldoInsuficienteException e){
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    public final void testeD(){
        double[] valores = {1,100,10.99,1.1,0,0.1,200}; 
        for( int i = 0; i < valores.length; i++){
            TestDeposito(valores[i]);
        }
    }
    
    public final void testeS(){
        double[] valores = {1,100,10.99,1.1,0,0.1,1000}; 
        for( int i = 0; i < valores.length; i++){
            TestSacar(valores[i]);
        }
    }
}

class TesteCorrente{
    static Conta c1 = new ContaCorrente(100);
    public static void TestSacar(double value){

        try{
            System.out.println("\n-- Teste de sacar dinheiro --");
            double value1 = c1.getSaldo();
            c1.sacar(value);
            double value2 = c1.getSaldo();
            System.out.println("Saldo: "+ value1+ "\nValor de saque: "+ value+"\nSaldo atual: "+value2);

        }catch (SaldoInsuficienteException e){
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }
    public static void TestDeposito(double value){

        try{
            System.out.println("\n-- Teste de deposito de dinheiro --");
            double value1 = c1.getSaldo();
            c1.depositar(value);
            double value2 = c1.getSaldo();
            System.out.println("Saldo: "+ value1+ "\nValor do deposito: "+ value+"\nSaldo atual: "+value2);

        }catch (SaldoInsuficienteException e){
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }
    public final void testeD(){
        double[] valores = {1,100,10.99,1.1,0,0.1,200}; 
        for( int i = 0; i < valores.length; i++){
            TestDeposito(valores[i]);
        }
    }
    public final void testeS(){
        double[] valores = {1,100,10.99,1.1,0,0.1,1000}; 
        for( int i = 0; i < valores.length; i++){
            TestSacar(valores[i]);
        }
    }
}

class BancoUI {
    private Gerenciamento gerenciamento;
    private Scanner scanner;

    public BancoUI() {
        this.gerenciamento = new Gerenciamento();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("====================================");
        System.out.println("  BEM-VINDO AO SISTEMA BANCÁRIO  ");
        System.out.println("====================================");

        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuOperacao('D'); // Depósito
                    break;
                case 2:
                    menuOperacao('S'); // Saque
                    break;
                case 3:
                    gerenciamento.saldos();
                    break;
                case 4:
                    menuTransferencia();
                    break;
                case 5:
                    System.out.println("Obrigado por usar nosso sistema. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL:");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Ver Saldos");
        System.out.println("4 - Transferir");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double lerValor() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, digite um valor numérico válido.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private void menuOperacao(char tipoOperacao) {
        System.out.println("\nSelecione a conta:");
        System.out.println("C - Conta Corrente");
        System.out.println("P - Conta Poupança");
        System.out.print("Escolha: ");
        char conta = scanner.next().toUpperCase().charAt(0);

        if (conta != 'C' && conta != 'P') {
            System.out.println("Opção de conta inválida.");
            return;
        }

        System.out.print("Digite o valor: ");
        double valor = lerValor();

        if (tipoOperacao == 'D') {
            gerenciamento.depositar(conta, valor);
        } else if (tipoOperacao == 'S') {
            gerenciamento.sacar(conta, valor);
        }
    }

    private void menuTransferencia() {
        System.out.println("\nTRANSFERÊNCIA ENTRE CONTAS");
        System.out.println("Selecione a conta de origem:");
        System.out.println("C - Conta Corrente");
        System.out.println("P - Conta Poupança");
        System.out.print("Escolha: ");
        char contaOrigem = scanner.next().toUpperCase().charAt(0);

        if (contaOrigem != 'C' && contaOrigem != 'P') {
            System.out.println("Opção de conta inválida.");
            return;
        }

        System.out.print("Digite o valor para transferir: ");
        double valor = lerValor();

        gerenciamento.transferir(contaOrigem, valor);
    }
}

public class Main {
    public static void main(String[] args) {
        BancoUI bancoUI = new BancoUI();
        bancoUI.iniciar();
    }
}
