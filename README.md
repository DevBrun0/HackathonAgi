# Hakagi - Sistema de Gestão de Equipamentos Corporativos

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.java.com/)
[![MongoDB](https://img.shields.io/badge/MongoDB-Latest-green)](https://www.mongodb.com/)
[![Gradle](https://img.shields.io/badge/Gradle-Latest-orange)](https://gradle.org/)
[![Swagger](https://img.shields.io/badge/Swagger-Latest-85EA2D)](https://swagger.io/)

O Hakagi é um sistema prático para gestão de empréstimos de equipamentos corporativos, desenvolvido para simplificar o controle de ferramentas e recursos compartilhados entre funcionários.

## ✨ Funcionalidades Principais

- **Cadastro e gerenciamento** de funcionários e ferramentas
- **Controle completo** de empréstimo e devolução
- **Gestão de compras** de novos equipamentos
- **Consulta de histórico** de movimentações
- **Relatórios** de ferramentas, funcionários e suas pendências

## 🚀 Começando

### Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [MongoDB (Última versão)](https://www.mongodb.com/try/download/community)
- [Gradle (Última versão)](https://gradle.org/install/)
- [JUnit 5](https://junit.org/junit5/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)

# Estrutura de Pastas - Projeto HackathonAgi

## 📁 raiz do projeto (`HackathonAgi`)
Contém os arquivos principais de configuração do projeto, como build, README, etc.

### 📁 gradle/
Contém configurações do **Gradle**, o sistema de build utilizado no projeto.

- **wrapper/**: Scripts para garantir que todos usem a mesma versão do Gradle, independente de estar instalada localmente.

---

## 📁 src/
Contém todo o código-fonte e os testes do projeto.

### 📁 main/
Código-fonte principal da aplicação.

#### 📁 java/com/hackathon/agi/agibank/
Pacote base da aplicação, dividido por responsabilidades.

- **config/**  
  Arquivos de configuração do Spring Boot, como habilitação de CORS, Beans e configurações gerais.

- **controller/**  
  Camada que expõe os endpoints da API (REST), recebendo e respondendo requisições.

- **domain/**  
  Contém as entidades (modelos) da aplicação, separadas por módulos/domínios.

  - **almoxarifado/**  
    Representa a parte de controle de "estoque".
    - `request/`: DTOs que representam os dados de entrada das requisições.
    - `response/`: DTOs que representam os dados de saída nas respostas.

  - **compra/**  
    Representa o módulo de compras dos equipametos.
    - `request/`: Objetos de entrada.
    - `response/`: Objetos de retorno.

  - **equipamento/**  
    Modelos relacionados à gestão de equipamentos.
    - `request/`: Entrada de dados.
    - `response/`: Resposta de dados.

  - **funcionario/**  
    Modelos relacionados a funcionários.
    - `request/`: Dados enviados pelo cliente.

  - **enums/**  
    Enumerações utilizadas em todo o projeto, para padronizar constantes.

- **exceptions/**  
  Pacote responsável pelo tratamento de erros da aplicação.
  - Subpastas divididas por domínio (`almoxarifado`, `compra`, `funcionario`) contêm exceções específicas.

- **mapper/**  
  Realiza a conversão de dados entre os modelos (`Domain`) e os DTOs (`Request` e `Response`).

- **repository/**  
  Interfaces que acessam o banco de dados, usando o Mongo Repository.

- **service/**  
  Contém a lógica de negócio da aplicação, responsável por processar os dados.

#### 📁 resources/
Arquivos de recursos da aplicação, como:

- `application.properties`: Configurações do Spring Boot e a configuração de conexão do mongo repository.

---

### 📁 test/
Contém os testes automatizados da aplicação.

- **java/com/hackathon/agi/agibank/controller/**  
  Testes da camada de controladores (endpoints).

## 📚 Documentação da API

A API segue o padrão RESTful e está documentada com Swagger. Para acessar a documentação interativa:

1. Inicie a aplicação
2. Acesse no seu navegador: https://hackathonagi.onrender.com/swagger-ui.html
