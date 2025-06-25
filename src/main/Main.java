package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    contaC = new ContaCorrente(100);
    contaP = new ContaPupanca(100);
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
        }
        System.out.println("Erro na transferencia");
    }

    public final void sacar(char value, double valor){

        
        if( value == 'C' && contaC.getSaldo() >= valor){
            try{
                contaC.sacar(valor);
                System.out.println("Valor Sacado com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }else if(value == 'P' && contaP.getSaldo() >= valor){

            try{
                contaP.sacar(valor);
                System.out.println("Valor sacado com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }
        System.out.println("Erro na transferencia");
    }

    public final void depositar(char value, double valor){
        
        if( value == 'C'){
            try{
                contaC.depositar(valor);
                System.out.println("Valor depositado com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }else if(value == 'P'){

            try{
                contaC.depositar(valor);
                System.out.println("Valor depositado com sucesso!!");
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error"+ e);
            }
        }
        System.out.println("Erro na transferencia");
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

public class Main{

    public static void main(String[] args){


        Gerenciamento g1 = new Gerenciamento();

        g1.criarContas();
        g1.saldos();
        
        // TesteCorrente testeC = new TesteCorrente();
        // TestePopanca testeP = new TestePopanca();
        // testeC.testeD();
    }
}

// adicionar uma classe para gerenciar as operações nas contas, e adicionar a trasferencia
// adicionar uma classe que vai ser UI do sistema, onde a main só vai chamar.