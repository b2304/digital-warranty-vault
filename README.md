# 🛡️ Digital Warranty Vault

A Java Spring Boot application for managing digital product warranties securely. This API-based service allows users to store, retrieve, and track product warranties with expiration alerts and multithreaded warranty reminders. Designed for modularity, scalability, and real-world production practices.

---

## 🧠 Features

- 📦 Store product and warranty information
- 👤 User registration and authentication (basic)
- ⏰ Warranty expiry alerting system (multithreaded)
- 📊 Warranty statistics endpoints
- 🛢️ MySQL integration using Liquibase for schema versioning
- 🚀 RESTful APIs with proper layered architecture
- 🧪 Unit and integration testing with JUnit and Mockito
- 🐳 Docker and Docker Compose support

---

## 🔧 Tech Stack

| Layer        | Tech Used                       |
|--------------|---------------------------------|
| Backend API  | Java 17, Spring Boot            |
| Database     | MySQL                           |
| ORM          | Spring Data JPA (Hibernate)     |
| Migrations   | Liquibase                       |
| Multithreading | ExecutorService (Java)       |
| Testing      | JUnit, Mockito                  |
| Containerization | Docker, Docker Compose     |
| Build Tool   | Maven                           |

---

## 🧪 API Endpoints (Sample)

### ✅ Users

- `POST /api/users/register` – Register a new user
- `GET /api/users/{id}` – Get user by ID

### 📦 Products & Warranty

- `POST /api/products` – Add a product with warranty
- `GET /api/products/user/{userId}` – All warranties for a user
- `GET /api/products/expiring-soon/{userId}` – Products with expiring warranty

---

## 🧠 Multithreading Use Case

A scheduled service runs daily using a thread pool to:
- Check for warranties expiring in X days
- Send alerts/reminders to users concurrently
- Designed using `ExecutorService` with graceful shutdown

---

## 🚀 Running the Project

### 📌 Prerequisites

- Java 17+
- Maven
- Docker

### 🐳 Option 1: Dockerized

```bash
docker-compose up --build

This will:
- Build the Spring Boot app
- Start MySQL and initialize DB via Liquibase
- Expose API at http://localhost:8080


🔧 Option 2: Manual
# Start MySQL (manually or via Docker)
mvn clean install
mvn spring-boot:run
Access: http://localhost:8080/swagger-ui.html (if Swagger is enabled)

🧪 Testing
Run all unit and integration tests:
  mvn test

📂 Liquibase Setup
Master file: changelog-master.xml

Includes:
users-changelog.xml
products-changelog.xml
Optional: data-seed-changelog.xml

To run Liquibase independently:
mvn liquibase:update

✨ Future Enhancements
- Add JWT-based authentication
- Email notifications for warranty expiry
- Web dashboard UI
- Role-based access
- Attach purchase receipts (file upload)
