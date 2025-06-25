# Compila todas as classes Java a partir do diretório 'main' e coloca os arquivos compilados no diretório 'out'
javac -d out $(find main -name "*.java")

# Entra no diretório 'out'
cd out

# Executa a classe Main localizada no pacote 'main'
java main.Main

# Volta ao diretório anterior
cd ..
