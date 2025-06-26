package sistema.repositorio;

import marcos.sistema.model.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.model.*;



public class Gerenciamento{
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