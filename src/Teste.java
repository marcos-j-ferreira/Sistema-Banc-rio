import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import main.*;

        //System.out.println(contas.get(0).getSaldo());

class Bank{


    private Conta c2 = new ContaPupanca(00);
    private Conta c3 = new ContaCorrente(00);

    protected void Array(){

        List<Conta> contas = new ArrayList<>();
        contas.add( new ContaPupanca(100));
        contas.add( new ContaCorrente(100));

    }

    Map<String, ContaPupanca> contas = new HashMap<>();

    protected void mapp(){


        contas.put("Marcos", new ContaPupanca(100));

        ContaPupanca user = contas.get("Marcos");

        System.out.println(contas.getSaldo());

    }

    protected void saldo(){

        List<ContaPupanca> contas = new ArrayList<>();

        contas.add( new ContaPupanca(1));
        contas.add( new ContaPupanca(2));
        contas.add( new ContaPupanca(3));
        contas.add( new ContaPupanca(4));

    }

}

public class Teste{

    private static ContaPupanca bank;

    public static void main(String[] args){
        
        // Conta c1 = new ContaPupanca(100);

        Bank b1 = new Bank();

        // b1.saldo();

        //System.out.println(c1.getSaldo());

        b1.mapp();
    }

}