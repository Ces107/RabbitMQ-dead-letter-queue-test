
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
  "id": "12345"
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




# 🐰 RabbitMQ 死信队列教程

欢迎来到 **RabbitMQ 死信队列教程**！这个项目是一个简洁而专业的 Spring Boot 应用，演示了如何在 RabbitMQ 中使用死信队列（DLQ）。它展示了最佳实践，为您的消息处理需求提供了坚实的基础。

## 🚀 快速开始

按照以下步骤在本地机器上设置、运行和测试该项目。

## 📋 目录

- [前置条件](#前置条件)
- [安装](#安装)
- [项目结构](#项目结构)
- [运行应用](#运行应用)
- [测试 API](#测试-api)
    - [发送消息](#发送消息)
    - [死信队列行为](#死信队列行为)
- [监控 RabbitMQ](#监控-rabbitmq)
- [贡献](#贡献)
- [许可证](#许可证)

## 📋 前置条件

开始之前，请确保您已安装以下软件：

- **Java JDK 11** 或更高版本
- **Maven 3.6** 或更高版本
- **RabbitMQ 服务器**（启用管理插件）
- **Git**（可选，用于克隆仓库）

## 🛠️ 安装

1. **克隆仓库**（或下载源代码）：

   ```bash
   git clone https://github.com/yourusername/rabbitmq-dlq-demo.git
   cd rabbitmq-dlq-demo
   ```

2. **使用 Maven 构建项目**：

   ```bash
   mvn clean install
   ```

## 📂 项目结构

以下是项目中关键文件和目录的概览：

```plaintext
rabbitmq-dlq-demo/
├── src/
│   ├── main/
│   │   ├── java/com/cesar/demodelrabbit/
│   │   │   ├── App.java                  # 主应用程序类
│   │   │   ├── DTO.java                  # 数据传输对象
│   │   │   ├── Controller.java           # REST 控制器
│   │   │   ├── Sender.java               # 消息发送服务
│   │   │   ├── listener/
│   │   │   │   └── Listener.java         # 消息监听器
│   │   │   └── rabbit/
│   │   │       └── RabbitMQConfiguration.java  # RabbitMQ 配置
│   │   └── resources/
│   │       └── application.properties    # 应用程序配置
├── pom.xml                               # Maven 配置
└── README.md                             # 此 README 文件
```

## ▶️ 运行应用

### 1. 启动 RabbitMQ 服务器

确保 RabbitMQ 服务器在本地机器上运行。默认情况下，AMQP 端口为 `localhost:5672`，管理界面为 `localhost:15672`。

### 2. 运行 Spring Boot 应用

您可以使用 Maven 或您的 IDE 运行应用程序。

- **使用 Maven**：

   ```bash
   mvn spring-boot:run
   ```

- **使用 Java**：

   ```bash
   java -jar target/rabbitmq-dlq-demo-0.0.1-SNAPSHOT.jar
   ```

- **使用您的 IDE**：

  将项目导入您的 IDE，运行 `App.java` 类。

## 🧪 测试 API

该应用程序暴露了一个 RESTful API，用于发送消息，这些消息将由 RabbitMQ 处理。

### 📍 基本 URL

```
http://localhost:8080
```

### 1. 发送消息

**端点**：`/send`

**方法**：`POST`

**内容类型**：`application/json`

#### **请求示例**

```json
{
  "title": "测试消息",
  "content": "这是一个测试消息",
  "id": "12345"
}
```

#### **cURL 命令**

```bash
curl -X POST   http://localhost:8080/send   -H 'Content-Type: application/json'   -d '{
    "title": "测试消息",
    "content": "这是一个测试消息",
    "id": "12345"
}'
```

#### **响应**

```
消息已发送
```

### 2. 死信队列行为

该应用程序配置为在消息处理期间模拟错误，导致消息被发送到死信队列。

#### **模拟消息处理失败**

在 `Listener.java` 类中，抛出异常以模拟处理错误：

```java
throw new Exception("处理消息时出错");
```

#### **预期行为**

- 消息被发送到主队列（`app.queue`）。
- 监听器接收消息并抛出异常。
- 消息被拒绝并路由到死信队列（`app.queue.deadletter`）。
- 您可以在 RabbitMQ 的管理界面中的死信队列查看消息。

## 🔍 监控 RabbitMQ

您可以使用 RabbitMQ 管理界面监控队列、交换机和消息。

### 访问管理界面

- **URL**： [http://localhost:15672](http://localhost:15672)
- **默认凭据**：
    - **用户名**：`guest`
    - **密码**：`guest`

### 检查队列

- **主队列**：`app.queue`
- **死信队列**：`app.queue.deadletter`

#### **步骤**：

1. 登录 RabbitMQ 管理界面。
2. 导航到 **Queues**（队列）选项卡。
3. 点击 `app.queue.deadletter` 查看死信队列中的消息。
4. 您可以根据需要检查、重新排队或清除消息。

## 🤝 贡献

欢迎贡献！请按照以下步骤：

1. **Fork 本仓库**。
2. **创建新分支**：

   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **提交您的更改**：

   ```bash
   git commit -m '添加新功能'
   ```

4. **推送到分支**：

   ```bash
   git push origin feature/your-feature-name
   ```

5. **打开 Pull Request**。

## 📜 许可证

该项目使用 [MIT 许可证](LICENSE)。

---

**由 ❤️ 制作**
