# Student Registration System

A lightweight Java web application built using **Java Servlets**, **Maven**, and **JDBC**. This project demonstrates the core principles of the Request-Response cycle and database persistence in a web environment.

---

## 🚀 Project Overview
The Student Registration System allows users to register students through a web form and view a real-time list of all registered individuals. It follows the **Model-View-Controller (MVC)** architectural pattern using server-side Java.

### Key Features
* **Student Registration:** Submit name, email, and academic year.
* **Data Persistence:** Securely stores records in a relational database.
* **Dynamic Data Retrieval:** View all registered students in an auto-generated HTML table.

---

## 🛠 Technologies Used
* **Language:** Java (JDK 8+)
* **Web Framework:** Java Servlet API
* **Server:** Apache Tomcat (v9.0 or v10.0)
* **Build Tool:** Apache Maven
* **Database:** MySQL / PostgreSQL
* **IDE:** VS Code / IntelliJ / Eclipse

---

## ⚙️ Application Endpoints

| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/` | GET | Displays the Registration Form. |
| `/register` | POST | Processes form data and saves it to the database. |
| `/show_all` | GET | Fetches and displays all student records in a table. |

---

## 🗄 Database Schema
Execute the following SQL commands to set up your database environment:

``sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    year INT NOT NULL
);

 Setup & Installation
1. Prerequisites
Ensure you have the following installed:

JDK 8 or higher.

Apache Maven.

Apache Tomcat Server (v9 or v10).

MySQL Server.

2. Configuration
Update the database credentials in your Java Database Utility class or Servlet:

URL: jdbc:mysql://localhost:3306/student_db

Username: your_username

Password: your_password

3. Build & Deploy
Open your terminal in the project root directory.

Build the project using Maven:

Bash

mvn clean install
Locate the .war file in the /target directory.

Copy the .war file to the webapps folder of your Apache Tomcat installation.

Start the Tomcat server.

4. Accessing the App
Open your browser and navigate to:

Registration Page: http://localhost:8080/student-registration/

View Records: http://localhost:8080/student-registration/show_all

🔍 Troubleshooting
404 Error: Ensure the servlet mapping in @WebServlet or web.xml matches your URL structure and that Tomcat is running.

Database Connection Error: Verify the MySQL service is active and the JDBC driver dependency is correctly added to your pom.xml.

Port Conflict: If port 8080 is already in use, modify the port in Tomcat's conf/server.xml.

🎯 Conclusion
This project provides a robust foundation for understanding enterprise Java development. It successfully integrates front-end forms with back-end logic and persistent storage, following industry-standard build processes with Maven. Its modular design allows for easy extension, such as adding Update/Delete functionality or styling the UI with Bootstrap.
