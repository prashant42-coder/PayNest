# PayNest Bank 

## Overview
**PayNest Bank** is a dynamic banking application providing customers with a seamless experience to manage their transaction history. Built using **Spring Boot** and **Thymeleaf**, the project leverages modern web technologies for a responsive and user-friendly interface.

---

## Features
- 📝 **Transaction History**: View detailed transaction logs including ID, type, amount, and timestamp.
- 🔒 **Secure Login**: Access your account with robust security protocols.
- 📊 **Responsive Design**: Optimized for various devices with an attractive and functional UI.
- 🚀 **Thymeleaf Integration**: Simplified dynamic data rendering for the front end.

---

## Tech Stack
### Backend:
- **Spring Boot**: Fast and scalable backend framework.
- **Java**: Core programming language.
- **Spring Security**: For authentication and authorization.

### Frontend:
- **Thymeleaf**: Server-side Java template engine.
- **HTML/CSS**: Responsive and visually appealing design.
- **Bootstrap**: Ensures a consistent and modern UI.

---

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL Database

### Installation Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/YourGitHubUsername/PayNestBank.git
   cd PayNestBank

2.Configure Database
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
3. Build and Run
mvn clean install
mvn spring-boot:run

Project Structure:
src/
├── main/
│   ├── java/
│   │   └── com.paynestbank/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── model/
│   │       └── repository/
│   └── resources/
│       ├── static/
│       ├── templates/
│       │   └── transactions.html
│       └── application.properties
└── test/
![Screenshot (133)](https://github.com/user-attachments/assets/18674d56-0ffe-4f6d-b79f-64645200e90f)![Uploading Screenshot (134).png…]()


