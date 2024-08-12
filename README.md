# Security OAuth Base Project

Este repositório foi criado como uma base para projetos que necessitam de autenticação utilizando OAuth em uma aplicação Java Spring. O projeto serve como um ponto de partida, permitindo que seja facilmente clonado e adaptado conforme as necessidades de diferentes projetos.

## Visão Geral

Este projeto implementa a autenticação utilizando OAuth 2.0, garantindo uma estrutura de segurança robusta e extensível. É ideal para projetos que precisam de uma camada de segurança com autenticação e autorização.

## Recursos

- **Autenticação OAuth 2.0**: Implementação completa de OAuth 2.0 para garantir a segurança dos usuários.
- **Java Spring**: Utiliza o poderoso framework Spring para criar uma aplicação modular e fácil de manter.
- **Configurações Básicas**: Pronto para ser usado como base para novos projetos, com as configurações de segurança já definidas.

## Como Usar

1. **Clone o Repositório**:

    ```bash
    https://github.com/ErickClinton/base-auth
    ```

2. **Instale as Dependências**:

    Navegue até o diretório do projeto e execute:

    ```bash
    ./mvnw install
    ```

3. **Configurações**:

    Ajuste as configurações de OAuth e segurança conforme necessário no arquivo `application.properties` ou `application.yml`.

4. **Execute a Aplicação**:

    ```bash
    ./mvnw spring-boot:run
    ```

## Autenticação

O projeto inclui um endpoint `/login` para autenticação, que utiliza os seguintes DTOs:

- **Request** 
   - Campos: `username` (String), `password` (String)

- **Response**
   - Campos: `accesToken` (String), `expiresIn` (Long)

O projeto inclui um endpoint `/user/register` para criação de usuários, que utiliza os seguintes DTOs:

- **Request**
   - Campos: `username` (String), `password` (String)

## Roles

O sistema de roles está configurado com dois níveis:

- **Admin**: Role que só pode ser criada diretamente no banco de dados.
- **Basic**: Role padrão para todos os novos usuários.