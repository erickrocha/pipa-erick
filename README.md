# Test Erick Pipa Studios

##### Stack aplicação

* Java 8
* Spring-boot 2.3.1
* swagger 

Como requerido no teste uma aplicação http based com armazenamento dos dados em memória

A aplicação foi estruturada sobre o conceito de clean architecture e Arquitetura hexagonal
   
Como executar a aplicação.

É necessário ter o **java 8** runtime instalado

**Java na linha de comando**

Dentro do diretório da aplicação execute o comando:

**java -jar app.jar**

Executar com maven:

**mvn clean spring-boot:run**

Executar como um container docker
É necessário ter o docker instalado na máquina

**docker build -t runtime:1.0 .**

depois de gerar a imagem executar

**docker run -p 8080:8080 -t runtime:1.0**

Para facilitar existe um arquivo chamado run.sh que executa pela linha de comando
basta executar 

**./run.sh**

Para acessar api documentation

**http://localhost:8080**

**http://localhost:8080/swagger-ui.html**

###Comandos extras para executar outras tarefas
É necessário maven instalado

executer somente os testes

mvn test

compilar 

mvn compile

ou para executar dos os ciclos

mvn clean install


