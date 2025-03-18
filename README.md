Event-Driven Finance Management System

Overview

The Event-Driven Finance Management System is a microservices-based application designed to manage financial transactions, budgets, and goals while utilizing event-driven architecture with Apache Kafka. This system ensures efficient event processing and real-time notifications for users.

Features

~ User Management: Manage user queries as well as validate the users.

~ Transaction Management: Record, track, and categorize financial transactions.

~ Budget Management: Set budgets and receive alerts when exceeded.

~ Goal Management: Define and track financial goals.

~ Event-Driven Notifications: Kafka-based event streaming for user sign-ups, transactions, budget alerts and goal completion.

~ Microservices Architecture: Decoupled services for scalability and maintainability.

~ API Gateway: Centralized API access using Spring Cloud Gateway also handle user authentication and validation using spring security.

~ Token Service: Centralized authentication and token handling.

~ Email Notifications: Event-based email alerts for transactions, budget, goal updates.

~ Registry: Contain all the serivces that are running on the application.

Architecture

The system follows a microservices approach with the following key components:

~ User Service: Handles user registration and authentication as well as send event to kafka when new user signup.

~ User Consumer: This service receive the User Service kafka event and store the user data into database.

~ Transaction Service: Manages user transactions and triggers related events.

~ Budget Service: Tracks and updates budget limits based on transactions as well as triggers related evnets to kafka.

~ Goal Service: Enables users to set and monitor financial goals as well as triggers related evnets to kafka.

~ Token Service: Handles JWT authentication and refresh token management.

~ Messaging Service: Listens to Kafka events and sends email notifications.

~ API Gateway: Manages routing and security.

~ Registry Service: This service contain all the microservices record running on the application.

Event Flow (Kafka Integration)

1. User Sign-up Event:

~ User Service publishes an event when a new user registers.

~ User Consumer service listens to the event ans store the user into database.

~ Message Service listens to the event and sends a welcome email.

2. Transaction Event:

~ Transaction Service publishes an event after a new transaction.

~ Budget Service listen to the transaction event and updates the budget if applicable.

~ Messaging Service listens to the event and sends notifications.

3. Budget Alert Event:

~ Budget Service publishes an event when the budget is exceeded.

~ Messaging Service listens and notifies the user.

4. Goal Alert Event:

~ Goal Service publishes an event when the goal is acheived.

~ Messaging Service listens and notifies the user.

Tech Stack

~ Backend: Java, Spring Boot (Spring Cloud, Spring Security, Spring Kafka, Spring Data JPA)

~ Database: MySql

~ Messaging: Apache Kafka for event-driven communication

~ Authentication: JWT-based authentication with Refresh Tokens

~ API Gateway: Spring Cloud Gateway

