package sistema.testecorrente;

import marcos.sistema.model.Conta;

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