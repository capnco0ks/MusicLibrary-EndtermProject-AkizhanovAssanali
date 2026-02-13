# Music Library Spring Boot API

# Cache Clearing (In-Memory)
## EXAMPLE SCREENSHOTS YOU CAN SEE IN DOCS PACKAGE


This project now includes a simple in-memory cache for `GET /api/authors`

## What was added
- Caching of author list responses in memory.
- Automatic cache invalidation after data-changing operations:
    - `POST /api/authors`
    - `PUT /api/authors/{id}`
    - `DELETE /api/authors/{id}`
- Manual cache clearing endpoint:
    - `DELETE /api/authors/cache`

## Manual clear example
```bash
DELETE http://localhost:8080/api/authors/cache
```

## Why this is useful
- Reduces repeated DB reads for frequent `GET /api/authors` calls.
- Keeps data fresh after create/update/delete operations.



## Project Overview

This project is a **Music Library** backend application built with Spring Boot. It provides REST API endpoints for managing authors and demonstrates design patterns, component principles, and SOLID architecture.

**Features:**
- RESTful API for Author CRUD operations
- PostgreSQL database integration via JDBC
- Design patterns: Singleton, Factory, Builder
- Component principles: REP, CCP, CRP
- Global exception handling
- Layered architecture: Controller → Service → Repository → Database

---

## REST API Documentation

### Base URL
http://localhost:8080/api/authors

### Endpoints

Method  Endpoint  Description 

GET => /api/authors => Get all authors 

GET => /api/authors/{id} => Get author by ID 

POST => /api/authors => Create new author 

PUT => /api/authors/{id} => Update author 

DELETE => /api/authors/{id} => Delete author 

### Example Requests

**GET all authors:**

bash
http://localhost:8080/api/authors


**POST create author:**


POST http://localhost:8080/api/authors \
  "Content-Type: application/json" \
  {"id":1,"name":"John Doe","rating":8}


**PUT update author:**


PUT http://localhost:8080/api/authors/1 \
   "Content-Type: application/json" \
  {"id":1,"name":"Jane Doe","rating":9}


**DELETE author:**

DELETE http://localhost:8080/api/authors/1


### Response Format


**Success (200/201):**


{
  "id": 1,
  "name": "John Doe",
  "rating": 8
}


**Error (404/400/500):**


{
  "message": "Author with id 1 not found",
  "status": 404
}


---

## Design Patterns

### Singleton Pattern
Three implementations:
- **DatabaseConfigManager** - Single database connection manager
- **ApplicationConfig** - Application configuration (name, version)
- **LoggingService** - Centralized logging service

All ensure a single shared instance across the application.

### Factory Pattern
**MediaFactory** creates Media subclasses (Song, Podcast) and returns the base type Media. This allows easy extension without modifying existing code.

**Usage:**

Media song = MediaFactory.createSong(id, name, duration, author, genre, category);
Media podcast = MediaFactory.createPodcast(id, name, duration, author, episodeNumber, category);


### Builder Pattern
**AuthorBuilder** constructs Author objects with fluent method calls and optional fields.

**Usage:**

Author author = new AuthorBuilder()
    .id(1)
    .name("John Doe")
    .rating(8)
    .build();


---

## Component Principles

### REP (Reuse/Release Equivalence)
Reusable modules are grouped together:
- repository Data access layer
- service - Business logic layer
- utils - Generic utilities
- patterns - Design pattern implementations

### CCP (Common Closure Principle)
Classes that change together are grouped:
- model Domain entities (change when domain rules change)
- exception Error handling (change when error strategy changes)
- repository - Data access (change when storage changes)
- service - Business logic (change when business rules change)

### CRP (Common Reuse Principle)
Modules avoid forcing dependencies on unused classes. For example, utils contains only generic utilities, not database-specific code.

---

## SOLID & OOP Summary

**SOLID Principles:**
- **Single Responsibility**: Each class has one reason to change (Controller handles HTTP, Service handles business logic, Repository handles data access)
- **Open/Closed**: Factory pattern allows extension without modification
- **Liskov Substitution**: Song and Podcast can be used wherever Media is expected
- **Interface Segregation**: CrudRepository interface is focused and specific
- **Dependency Inversion**: Service depends on CrudRepository interface, not concrete implementation

**OOP Features:**
- Inheritance: Author extends BaseEntity, Song/Podcast extend Media
- Polymorphism: Media array can hold Song and Podcast instances
- Abstraction: Media abstract class, interfaces (Playable, Printable, Validatable)
- Encapsulation: Private fields with public getters/setters

---

## Database Schema

**Table: authors**

CREATE TABLE authors (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  rating INTEGER NOT NULL
);


**Connection:** PostgreSQL database music_library_db (configured in application.properties`)

---

## Instructions to Run

### Prerequisites
- Java 25+
- Maven 3.6+
- PostgreSQL (running on localhost:5432)

### Database Setup
1. Create database and user:

CREATE USER music_user WITH PASSWORD 'pass123';
CREATE DATABASE music_library_db OWNER music_user;


2. Create table:

 music_library_db

CREATE TABLE authors 
(
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  rating INTEGER NOT NULL


### Run Application

**Option 1: Maven**

mvn spring-boot:run


**Option 2: IDE**
Run example.demo.MusicLibrarySpringApplication class

The API will be available at http://localhost:8080

### Test with Postman
1. Import endpoints or create requests manually
2. Use base URL: http://localhost:8080/api/authors
3. Set Content-Type: application/json for POST/PUT requests

---

## Reflection

**What I Learned:**
- Implementing design patterns (Singleton, Factory, Builder) in a real application
- Organizing code using component principles (REP, CCP, CRP)
- Building REST APIs with Spring Boot
- Global exception handling for clean error responses

---

## Project Structure


src/main/java/
controller/     
 REST controllers
service/        
 Business logic
repository/     
 Data access
model/          
 Domain entities
dto/            
 Data transfer objects
exception/      
 Exception handling
config/         
 Configuration (Singleton)
patterns/       
 Design patterns (Factory, Builder)
utils/          
 Utility classes




**Technologies:** Java 25, Spring Boot 4.0.2, PostgreSQL, JDBC, Maven
