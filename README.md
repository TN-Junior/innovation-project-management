# Innovation Project Manager - CESAR

## Visão Geral
**Innovation Project Manager** é um sistema para gerenciar projetos de inovação, desenvolvido com **Spring Boot**. O objetivo é facilitar o acompanhamento de projetos, atribuição de equipes e monitoramento de progresso.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Web**
- **H2** 
- **Lombok**
- **Springdoc OpenAPI (Swagger)**

## 📂 Estrutura do Projeto
```
innovation-manager/
├── src/main/java/com/cesar/innovationmanager
│   ├── controllers/ProjetoController.java
│   ├── entities/Projeto.java
│   ├── repositories/ProjetoRepository.java
│   ├── services/ProjetoService.java
│   ├── config/SwaggerConfig.java
│
├── src/main/resources
│   ├── application.properties
│
├── pom.xml
└── README.md
```

## Configuração do Ambiente
### **1. Pré-requisitos**
- **JDK 17 ou superior**
- **Maven**
- **PostgreSQL**

### **2. Configuração do Banco de Dados**
Atualize o arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/innovationdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```


## Endpoints da API
### **1. Criar um Projeto**
`POST /projetos`
```json
{
  "nome": "Plataforma de Inovação CESAR",
  "descricao": "Gerenciamento de projetos inovadores.",
  "status": "Em andamento",
  "dataInicio": "2024-01-15T09:00:00",
  "dataFim": "2024-12-20T18:00:00",
  "orcamento": 500000.00,
  "liderProjeto": "Ana Souza",
  "integrantes": ["Ana Souza", "Carlos Pereira", "Fernanda Lima"]
}
```

### **2. Listar Todos os Projetos**
`GET /projetos`

### **3. Buscar Projeto por ID**
`GET /projetos/{id}`

### **4. Atualizar Projeto**
`PUT /projetos/{id}`

### **5. Excluir Projeto**
`DELETE /projetos/{id}`

### **6. Filtrar Projetos por Status**
`GET /projetos/status/{status}`

## Documentação com Swagger
Após iniciar o projeto, acesse:
```
http://localhost:8081/swagger-ui/index.html
```
![image](https://github.com/user-attachments/assets/eaccaf31-19b6-4b57-b7d2-325d7767d720)






