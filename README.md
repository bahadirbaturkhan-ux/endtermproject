# Bank System REST API

## Project Description
This project is a Spring Boot REST API for a simple Bank System.
It demonstrates Object-Oriented Programming, SOLID principles,
design patterns, and JDBC database interaction.

The system allows managing customers and their bank accounts.

## Architecture
The project follows a layered architecture:
Controller → Service → Repository → Database

## OOP Principles
Encapsulation  
Inheritance  
Polymorphism  
Abstraction  
Composition

## SOLID Principles
Single Responsibility Principle  
Open / Closed Principle  
Liskov Substitution Principle  
Interface Segregation Principle  
Dependency Inversion Principle

## Design Patterns
Singleton  
Factory  
Builder

## Database
PostgreSQL is used as the database.
pgAdmin is used for database management.
The system contains two main tables:
customers and accounts.
There is a one-to-many relationship between customers and accounts.

## REST API Endpoints

Customers:
POST /api/customers  
GET /api/customers  
GET /api/customers/{id}  
DELETE /api/customers/{id}

Accounts:
POST /api/accounts  
GET /api/accounts  
GET /api/accounts/{id}  
GET /api/accounts/by-customer/{customerId}  
PUT /api/accounts/{id}/balance  
DELETE /api/accounts/{id}

## Global Exception Handling
The project uses @RestControllerAdvice to handle validation errors,
database errors, and business logic exceptions in a centralized way.

## UML Diagram
The system contains Customer and Account entities.
AccountBase is an abstract class.
SavingsAccount and CheckingAccount extend AccountBase.
One Customer can have multiple Accounts.

## How to Run
1. Create PostgreSQL database named bank_db
2. Configure application.properties
3. Run the project using Maven:
   mvn spring-boot:run

## Technologies
Java  
Spring Boot  
JDBC  
PostgreSQL  
Maven

## Author
Batyrkhan Bakhadir