# modulo-comunicacao

## Um micro serviço em Java para agendamento de envio de comunicação para clientes

### Tecnologias utilizadas no projeto:

- [Java 11](https://www.oracle.com/java/technologies/javase/11-relnotes.html)
- [Spring Boot](https://spring.io/https://www.linkedin.com/in/valdelar//spring-boot)
- [Docker](https://www.docker.com/)
- [Micrometer](https://micrometer.io/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)
- [Spring Actuator](https://spring.io/guides/gs/actuator-service/)
- [MySQL](https://www.mysql.com/)


### Como rodar:

**É necessário ter o Docker instalado em sua máquina**

 1. Faça o clone do projeto: git clone https://github.com/valdelarm/modulo-comunicacao.git
 2. entre na pasta do projeto: cd modulo-comunicacao
 3. execute o comando `./mvnw package -DskipTests` para gerar o jar que será utilizado para gerar a imagem docker.
 4. execute o comando `docker-compose up --build` para gerar e executar a imagem com a aplicação Java, um MySQL, Prometheus e Grafana.

 A aplicação estará disponível em http://localhost:8080 e a documentação da API estará em http://localhost:8080/swagger-ui/
 
 - Se quiser verificar se a aplicação está saudável, acesse http://localhost:8080/actuator/health
 - Prometheus estará rodando no endereço http://localhost:9090
 - Grafana estará rodando no endereço http://localhost:3000

### Como criar gráficos com as métricas disponibilizadas pela aplicacação?

- Acesse a URL mencionada acima para acessar o Grafana (acesse após fazer algumas requisições para ter dados).
- Utilize o login admin e senha admin
- Clique na engrenagem e adicione um datasource
- Escolha Prometheus e na URL coloque **http://prometheus:9090** e salve.
- Adicione um novo Dashboard e o datasource escolhido estará disponível com os dados da aplicação. Você poderá adicionar dados da JVM e da máquina.
- É possível recolher métricas do nosso banco de dados também, caso queira.

### Desenvolvedor

Valdelar Martins <valdelar.martins@gmail.com>

Linkedin <https://www.linkedin.com/in/valdelar/>

