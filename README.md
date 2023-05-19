<h1 align="center"> Desafio GFT </h1>

# 📁 Acesso ao projeto

**https://github.com/rafael-queiroz/desafio-gft.git**

## 🔨 Funcionalidades do projeto

O App lista users com nome, email endereço e habilidades. Também, é possível cadastrar users. Todo armazenamento é mantido em base de dados postgres mas em container e sem volumes, portanto, ao finalizar o App as informações são perdidas.

Segue os curl's para o teste via postman

- Create User

  `curl --location 'http://localhost:8080/users' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name": "ererer",
  "email": "rafael.qp@hotmail.com",
  "dateOfBirth": "1985-02-14",
  "address": "Rua José Inaldo, 759, Praia do Imperador Guia de Pacobaíba, Magé - RJ",
  "skills": ["teste", "teste 56", "teste 3"]
  }'`


- List users pageable

  `curl --location 'http://localhost:8080/users?page=0&size=0&sort=description,asc'`


- Find user by id

  `curl --location 'http://localhost:8080/users/1'`

## ✔️ Técnicas e tecnologias utilizadas

As tecnologias utilizadas pra isso são:

- `Java - Spring boot`
- `Postgres`
- `Docker`
- `Docker compose`
- Foi criado um filter para bloquear chamadas sem um api-key, mas esta foi comentada por falta de tempo de implementar o integração com o junit

# 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com o Intellij. Para isso, na tela de launcher clique em:

- **Open an Existing Project** (ou alguma opção similar)
- Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo)
- Por fim clique em OK
- No terminal rode o comando  "mvn -B package -DskipTests=true" para gerar o aruivo jar do projeto
- Agora somente rodar o docker-compose up para rodar o projeto.
- Para rodar os testes é necessário configurar o profile de dev, vide application.yml.