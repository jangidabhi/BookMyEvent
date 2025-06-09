# 🎫 Event Management System (Spring Boot + JWT)

This is a full-stack **Event Management System** developed using **Java Spring Boot** in the backend, with secure **JWT-based authentication**. The system includes role-based access for Admin, Organiser, and User. The application was developed using **Eclipse IDE** and connected to a relational database (MySQL).

---

## 🛠️ Features

- 🔐 JWT-based authentication and role-based authorization
- 🧑 Admin dashboard: Manage users and events
- 🧑‍💼 Organiser dashboard: Create, update, delete own events
- 🙋 User dashboard: View, register, and track event participation
- 📊 Analytics and reporting for admins (charts, graphs)
  

---

## 🖥️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **Hibernate**
- **MySQL / PostgreSQL**
- **Maven**
- **Eclipse IDE**

---

## 🔐 Security with JWT

The project includes stateless security using **JSON Web Tokens (JWT)**. Features include:

- Authentication via `/login` endpoint (generates JWT)
- Protected routes for Admin/Organiser/User
- Stateless session with token stored client-side
- Custom JWT filters, UserDetailsService, and token validation

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Eclipse IDE
- Maven
- MySQL (or PostgreSQL)

### Installation Steps

```bash
# Clone the project
git clone https://github.com/jangidabhi/BookMyEvent.git
cd BookMyEvent

# Configure application.properties with your DB credentials

# Run the application
mvn spring-boot:run
