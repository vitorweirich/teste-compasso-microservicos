# Recrutamento Compasso Com Micro Serviços

O Objetivo desse projeto é adaptar um projeto já existente, nesse repositório: [Recrutamento-Compasso](https://github.com/vitorweirich/recrutamento-compasso), passando de uma Api monolítica para a arquitetura de micro serviços.


Desenvolvido usando as ferramentas do spring cloud, sendo que algumas delas já estão descontinuadas, como o Zuul.

## Organização do projeto
O projeto consiste em 6 micro serviços sendo eles:
##### ct-cidade
- Responsável pelos dados relacionados a cidade.
##### ct-cliente
- Responsável pelos dados dos clientes.
##### ct-user
- Responsável por guardar as credencias usadas para autorização.
##### ct-oauth
- Responsável pela autorização.
##### ct-eureka-server
- Responsável por cadastrar as APIs que serão gerenciadas pelo zuul.
##### ct-zuul
- Responsável por verificar a autorização do usuário, balanceamento de carga, api gateway e disponibilizar o conteúdo.

## Instruções para teste
Basta compilar e rodar todos os micro serviços individualmente. O projeto ct-eureka-server precisa ser o primeiro depois dele a ordem não importa. Para facilitar os testes estarei disponibilizando um arquivo json de uma coleção do postman com o nome de postman-colec.json(você apenas precisara criar uma variável de ambiente chamada 'token' para tudo funcionar automaticamente).

O projeto tem implementado autorização por meio de token jwt, ou seja para fazer acessar qualquer um dos endpoints que serão listados abaixo primeiramente é necessário fazer login por meio do seguinte endpoint: http://localhost:8765/ct-oauth/oauth/token passando as credencias do app em base64 no cabeçalho da requisição e ainda as credencias de um dos dois usuários já cadastrados(para ser mais fácil é só usar o endpint login disponibilizado no postman), se tudo estiver certo você recebera um token de retorno que precisa ser mandado em todas as requisições que quiser fazer(é um bearer token). Deixarei todas as credencias no final desse README.

De maneira simplificada o usuário operador tem acesso somente aos endpoints de cliente e de usuários, o administrador tem acesso a todos.

É importante lembrar que o banco de dados utilizado é o h2, logo exceto o projeto de usuários(ct-user) todos os outros virão sem nenhum dado cadastrado, e que para se cadastrar um cliente antes é necessário ter a cidade onde ele mora cadastrada, o que por sua vez implica que obrigatóriamente é necessário se logar como administrador para cadastrar cidades. 

Como é utilizado o Zuul para balancear carga e como gateway todos a requisições serão feitas para ele, que se encaregará de chamar os serviços individualmente, nesse projeto mantive sua porta padrão, a 8765.

## Endpoints
> #### ct-oath:
>> ##### Método POST:
>>> **Login:** http://localhost:8765/ct-oauth/oauth/token, passando todas as credencias da aplicação e do usuário.
> #### ct-cidade:
>> ##### Método GET: 
>>> **Buscar pelo nome**: http://localhost:8765/ct-cidade/cidade/nome?nome={nomeDaCidade}
>>> **Buscar pelo estado:** http://localhost:8765/ct-cidade/cidade/estado?estado={nomeDoEstado}
>> ##### Método POST:
>>> **Cadastrar cidade**: http://localhost:8765/ct-cidade/cidade, passando no corpo da requisição em json o nome e estado da cidade
> #### ct-cliente
>> ##### Método GET:
>>> **Buscar pelo nome**: http://localhost:8765/ct-cliente/cliente/nome?nome={nomeDoCliente}
>>> Buscar pelo id: http://localhost:8765/ct-cliente/cliente/id?id={id}
>> ##### Método POST:
>>> **Cadastrar Cliente:** http://localhost:8765/ct-cliente/cliente, passando no corpo em json os dados, nome, sexo, dataNascimente, idade, cidade(o nome).
>> ##### Método PATCH:
>>> **Alterar Cliente:** http://localhost:8765/ct-cliente/cliente/{id}, passando no corpo em json o novo nome.
>> ##### Método DELETE:
>>> **Deletar pelo id:** http://localhost:8765/ct-cliente/cliente/{id}
> #### ct-user:
>> ##### Método GET:
>>> **Buscar pelo id:** http://localhost:8765/ct-user/users/{id}  
>>> **Buscar pelo email:** http://localhost:8765/ct-user/users/search?email={email}

## Credencias
> #### App
>> ~~~
>> app-name = compasso-test-app-name
>> app-secret = compasso-teste-app-secret

> #### Usuários
>> ~~~
>>Usuário operador = nina@gmail.com
>>senha = 123456 
>
>> ~~~
>>Usuário administrador = leia@gmail.com
>>senha = 123456
>>~~~

# Autor: Vitor Mateus Weirich
