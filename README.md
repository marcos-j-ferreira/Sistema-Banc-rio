# 📌 **Sistema Bancário em Java**  

Um projeto simples em Java para simular operações bancárias básicas (saque, depósito, transferência e consulta de saldo) usando **Programação Orientada a Objetos (OOP)** e **tratamento de exceções**.  

---

## 🚀 **Funcionalidades**  

✅ **Depósito** em conta corrente ou poupança.  
✅ **Saque** com validação de saldo.  
✅ **Transferência** entre contas.  
✅ **Consulta de saldo** das contas.  
✅ **Tratamento de erros** (ex.: saldo insuficiente).  

## 🛠️ **Tecnologias e Conceitos Usados**  
 
- **POO** (Abstração, Encapsulamento, Interfaces)  
- **Estruturas de dados**: `Map`, `ArrayList`  
- **Tratamento de exceções** (`try-catch`, exceções customizadas)  
- **Clean Code** (Métodos bem definidos, nomes descritivos)  

---

## ▶️ **Como Executar**  

1. **Clone o repositório** (ou copie os arquivos `.java`):  
   ```sh
   git clone [URL_DO_REPO]
   ```

2. **Compile e execute**:  
   ```sh
   javac src/*.java -d bin/
   java -cp bin/ Main
   ```

3. **Use o menu interativo** via terminal:  
   ```
   ====================================
     BEM-VINDO AO SISTEMA BANCÁRIO  
   ====================================
   
   MENU PRINCIPAL:
   1 - Depositar
   2 - Sacar
   3 - Ver Saldos
   4 - Transferir
   5 - Sair
   Escolha uma opção: 
   ```

---

## 📝 **Exemplo de Uso**  

1. **Depositar R$ 100 na conta corrente**:  
   ```
   Escolha uma opção: 1  
   Selecione a conta:  
   C - Conta Corrente  
   P - Conta Poupança  
   Escolha: C  
   Digite o valor: 100  
   Deposito feito com sucesso!  
   ```

2. **Transferir R$ 50 para poupança**:  
   ```
   Escolha uma opção: 4  
   Selecione a conta de origem:  
   C - Conta Corrente  
   P - Conta Poupança  
   Escolha: C  
   Digite o valor: 50  
   Valor transferido com sucesso!  
   ```

3. **Ver saldos**:  
   ```
   Escolha uma opção: 3  
   --- Saldos das contas ---  
   Conta poupança: 50.0  
   Conta corrente: 50.0  
   ```

---

## 📌 **Próximas Melhorias**  

🔹 Adicionar **persistência** (salvar dados em arquivo).  
🔹 Implementar **extrato de transações**.  
🔹 Criar **sistema de login** com múltiplos clientes.  
🔹 Adicionar **juros** para conta poupança.  

---

## 🤝 **Contribuição**  

Sinta-se à vontade para abrir **issues** ou **pull requests** com melhorias!  

