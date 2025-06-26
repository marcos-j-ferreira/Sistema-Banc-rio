package sistema.execao;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensage){
        super(mensage);
    }
}