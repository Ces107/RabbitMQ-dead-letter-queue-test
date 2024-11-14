
# 🐰 RabbitMQ Dead Letter Queue Demo Project

Welcome to the **RabbitMQ Dead Letter Queue Demo Project**! This project is a minimalist yet professional implementation of RabbitMQ with Dead Letter Queues (DLQ) in a Spring Boot application. It's designed to showcase best practices and provide a solid foundation for your messaging needs.

## 🚀 Getting Started

Follow this guide to set up, run, and test the project on your local machine.

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [Testing the API](#testing-the-api)
    - [Send a Message](#send-a-message)
    - [Dead Letter Queue Behavior](#dead-letter-queue-behavior)
- [Monitoring RabbitMQ](#monitoring-rabbitmq)
- [Contributing](#contributing)
- [License](#license)

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 11** or higher
- **Maven 3.6** or higher
- **RabbitMQ Server** (with the Management Plugin enabled)
- **Git** (optional, for cloning the repository)

## 🛠️ Installation

1. **Clone the repository** (or download the source code):

   ```bash
   git clone https://github.com/yourusername/rabbitmq-dlq-demo.git
   cd rabbitmq-dlq-demo
   ```

2. **Build the project using Maven**:

   ```bash
   mvn clean install
   ```

## 📂 Project Structure

Here's an overview of the key files and directories in the project:

```plaintext
rabbitmq-dlq-demo/
├── src/
│   ├── main/
│   │   ├── java/com/cesar/demodelrabbit/
│   │   │   ├── App.java                  # Main application class
│   │   │   ├── DTO.java                  # Data Transfer Object
│   │   │   ├── Controller.java           # REST Controller
│   │   │   ├── Sender.java               # Message Sender Service
│   │   │   ├── listener/
│   │   │   │   └── Listener.java         # Message Listener
│   │   │   └── rabbit/
│   │   │       └── RabbitMQConfiguration.java  # RabbitMQ Configuration
│   │   └── resources/
│   │       └── application.properties    # Application properties
├── pom.xml                               # Maven configuration
└── README.md                             # This README file
```

## ▶️ Running the Application

### 1. Start RabbitMQ Server

Ensure that the RabbitMQ server is running on your local machine. By default, it runs on `localhost:5672` for AMQP and `localhost:15672` for the Management UI.

### 2. Run the Spring Boot Application

You can run the application using Maven or your IDE.

- **Using Maven**:

  ```bash
  mvn spring-boot:run
  ```

- **Using Java**:

  ```bash
  java -jar target/rabbitmq-dlq-demo-0.0.1-SNAPSHOT.jar
  ```

- **Using Your IDE**:

  Import the project into your IDE and run the `App.java` class.

## 🧪 Testing the API

The application exposes a RESTful API to send messages, which are then processed by RabbitMQ.

### 📍 Base URL

```
http://localhost:8080
```

### 1. Send a Message

**Endpoint**: `/send`

**Method**: `POST`

**Content-Type**: `application/json`

#### **Request Body Example**

```json
{
  "title": "Test Message",  
  "content": "This is a test message",
  "id": "42"
}
```

# If title = "error" it will produce a Exception in receiver:
```json
{
  "title": "error",  
  "content": "This is a test error message",
  "id": "1337"
}
```



#### **cURL Command**

```bash
curl -X POST   http://localhost:8080/send   -H 'Content-Type: application/json'   -d '{
    "title": "Test Message",
    "content": "This is a test message",
    "id": "12345"
}'
```

#### **Response**

```
Mensaje enviado
```

### 2. Dead Letter Queue Behavior

The application is configured to simulate an error during message processing, causing the message to be sent to the Dead Letter Queue.

#### **Simulating Message Failure**

In the `Listener.java` class, an exception is thrown to simulate a processing error:

```java
throw new Exception("Error processing the message");
```

#### **Expected Behavior**

- The message is sent to the main queue (`app.queue`).
- The listener receives the message and throws an exception.
- The message is rejected and routed to the Dead Letter Queue (`app.queue.deadletter`).
- The message can be inspected in RabbitMQ's Management UI under the Dead Letter Queue.
