# PipoSaude - Exercício Engenharia v2

## DESCRIÇÃO DO PROJETO

Projeto backend para facilitar o processo de inclusão de novos funcionários nos benefícios oferecidos pelas empresas Acme Co. e Tio Patinhas Bank.
A solução é uma API Rest com endpoints que simplificam o processo de inclusão de benefícios para funcionários contratados.
A ideia é que somente as informações necessárias sejam exigidas para a inclusão de benefícios oferecidos por uma determinada empresa.

***Exemplo:***
Os benefícios oferecidos pela empresa Acme Co., exigem, além das informações já armazenadas no cadastro, o “peso” e a “altura” do funcionário. Neste caso só é preciso informar o “CPF”, o “peso” e a “altura” do contratado. Desta forma, usando o “CPF” como referência, o sistema buscará todas as informações necessárias já registradas no cadastro do funcionário.
 
## STACK

### Tecnologias utilizadas no desenvolvimento:
- Kotlin (Linguagem de programação orientada a objetos)
- Spring Boot (Framework)
- MongoDB (Banco de dados)
- Apache Tomcat (Servidor Web)

### Ferramentas utilizadas no desenvolvimento:
- IntelliJ (IDE)
- MongoDB Compass (GUI)
- Postman (Plataforma de API)
- Spring initializr (Gerador de projetos Spring Boot)

## COMO EXECUTAR O PROJETO LOCALMENTE

Tendo o ambiente de JVM configurado e o MongoDB instalado na máquina, o projeto pode ser executado localmente.

Os testes foram realizados executando o projeto pelo IntelliJ, simulando as chamadas dos endpoints pelo Postman e consultando os dados pelo MongoDB Compass.

Ao executar a função “main” da classe “EmployeeBenefitsApplication”, o servidor local (pela porta 8080) e o projeto são inicializados. A partir deste momento os endpoints já podem ser executados.

### CONEXÃO COM O MONGODB COMPASS
1. Download e instalação (https://www.mongodb.com/try/download/compass)
2. Ao executa-lo será exibido um formulário que deve ser preenchido com os dados da conexão. Basta preencher os campo "Hostname" e "Port". (Por padrão a conexão é feita pelo Hostname “127.0.0.1” e pelo Port “27017”)
3. Clicar no botão "CONNECT"
4. Selecionar o banco de dados (Por padrão o banco de dados é criado com o nome "test")

