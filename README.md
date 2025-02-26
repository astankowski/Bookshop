# Bookshop Project

## Overview
A multi-module Spring Boot application for managing books, authors, and orders. It includes API generation, book management, and order processing and printing(PDF).

## Modules
1. **Api-gen**: Generates API code using OpenAPI
2. **BookShop**: Manages books, authors, and integrates with orders
3. **BookOrder**: Handles order processing and PDF report generation

## Features
- Book management (add, update, delete, retrieve)
- Author management
- Order processing and PDF reports
- OpenAPI documentation

## Technologies
- Spring Boot, Spring Data JPA, H2 Database
- OpenAPI Generator, Feign Client, Lombok, MapStruct, Log4j, OpenPDF

### Installation
1. Clone the repository:

    ```bash
    git clone https://github.com/astankowski/Bookshop.git
    cd Bookshop
    ```

2. Build the project:

    ```bash
    mvn clean install
    ```

3. Run the BookShop application:

    ```bash
    cd BookShop
    mvn spring-boot:run
    ```

4. Run the BookOrder application:

    ```bash
    cd BookOrder
    mvn spring-boot:run
    ```

### API Documentation
- BookShop: `http://localhost:8081/swagger-ui.html`
- BookOrder: `http://localhost:8080/swagger-ui.html`

### H2 Database
- Access the H2 console at:
    - BookShop: `http://localhost:8081/h2-console`
    - BookOrder: `http://localhost:8080/h2-console`
- Use the following credentials:
    - **Username**: `sa`
    - **Password**: `password`

### Testing
Run the tests with:

    ```bash
    mvn test
    ```
