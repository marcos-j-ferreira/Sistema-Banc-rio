package sistema.service;

import sistema.model.*;
import sistema.repositorio.*;
import sistema.service.*;

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
