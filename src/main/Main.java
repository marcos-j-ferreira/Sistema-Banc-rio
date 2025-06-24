import java.util.ArrayList;
import java.util.List;

class interface Conta{
    void depositar (double valor);
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
    public sacar( double valor) throws SaldoInsuficienteException{

        if(valor > saldo){
            throw new SaldoInsuficienteException("Saldo + limite insuficientes!");
        }
        saldo -= valor;
    }

    @Override
    public double getSaldo(){
        return saldo;
    }

}

class ContaCorrente extends ContaPupanca{
    private double saldo;

    ContaCorrente(double valor){
        this.saldo = valor;
    } 
}

class Banco{
    private List<Conta> contas = new ArrayList<>();
}