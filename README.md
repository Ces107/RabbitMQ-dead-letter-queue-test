# 🐰 RabbitMQ Dead Letter Queue Demo Project

Welcome to the **RabbitMQ Dead Letter Queue Demo Project**! This project is a minimalist yet professional implementation of RabbitMQ with Dead Letter Queues (DLQ) in a Spring Boot application. It's designed to showcase best practices and provide a solid foundation for your messaging needs.

## 🚀 Getting Started

Follow this guide to set up, run, and test the project on your local machine.

---

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Running the Application](#running-the-application)
- [Testing the API](#testing-the-api)
    - [Send a Message](#1-send-a-message)
    - [Dead Letter Queue Behavior](#2-dead-letter-queue-behavior)
- [Monitoring RabbitMQ](#monitoring-rabbitmq)
- [Contributing](#contributing)
- [License](#license)

---

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 11** or higher
- **Maven 3.6** or higher
- **RabbitMQ Server** (with the Management Plugin enabled)
- **Git** (optional, for cloning the repository)

---

## Installation

1. **Clone the repository** (or download the source code):

   ```bash
   git clone https://github.com/ces107/rabbitmq-dlq-demo.git
   cd rabbitmq-dlq-demo
