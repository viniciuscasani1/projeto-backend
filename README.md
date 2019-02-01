# projeto-backend

Projeto backend do desafio, a aplicação roda dentro de um container docker, juntamente com o banco de dados e o rabbitMQ

# Build

Na pasta do projeto Executar o comando `docker-compose build`

# Running

Executar `docker-compose up` <br />
Ao executar este comando o banco de dados, o rabbitMQ e a aplicação serão iniciadas

# Monitorar filas

Acessar RabbitMQ atraves do endereço http://localhost:15672  <br />
Para logar utilizar: <br />
usuario: guest <br />
senha: guest
