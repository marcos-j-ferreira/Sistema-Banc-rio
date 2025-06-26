package marcos.sistema.model;

import sistema.execao.SaldoInsuficienteException;

interface Conta{
    void depositar (double valor) throws SaldoInsuficienteException;
    void sacar (double valor) throws SaldoInsuficienteException;
    double getSaldo();
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