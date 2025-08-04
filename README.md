# 💰 Event-Driven Finance Management System

A scalable, secure, and event-driven **Finance Management Backend** built with microservices architecture, Apache Kafka, and Spring Boot. Designed to help users manage their transactions, budgets, and goals in real-time — with intelligent notifications and token-based security.

---

## 🚀 Features

- ✅ User Registration & Authentication (Access & Refresh Tokens)
- 💳 Add, update, and delete transactions
- 🎯 Set and track financial goals
- 🧮 Budget tracking & alerts when limits are exceeded
- 📬 Real-time email/SMS notifications using Kafka
- 🧵 Asynchronous communication using Apache Kafka
- 🛡️ JWT + Refresh Token Authentication
- 🌐 API Gateway & Service Discovery with Spring Cloud
- 🧱 Modular microservices architecture

---

## 🧱 Microservice Architecture

[ Client ]
|
[ API Gateway ]
|
| | | | | | |
User Token Budget Transaction Goal Messaging Registry
MS MS MS MS MS MS MS

yaml
Copy
Edit

All communication is event-driven using **Apache Kafka** as the message broker.

---

## ⚙️ Tech Stack

- **Java 17**, **Spring Boot 3**
- **Spring Cloud Gateway**, **Eureka Discovery Server**
- **Kafka** for event-driven processing
- **PostgreSQL** for user data and transaction logs
- **MongoDB** (planned for event sourcing)
- **JWT + Refresh Tokens**
- **Docker**, **Docker Compose** (in-progress)
- **JUnit + Postman** for testing

---

## 🧾 Modules & Responsibilities

| Microservice     | Responsibilities                                 |
|------------------|--------------------------------------------------|
| User Service     | Register/Login users, send Kafka event           |
| Token Service    | Generate Access/Refresh Tokens, validate JWTs    |
| Transaction MS   | Manage transactions, produce Kafka messages      |
| Budget Service   | Update budget on transaction events              |
| Goal Service     | Monitor savings goals                            |
| Messaging Service| Consume events and send email/SMS notifications  |
| Registry Service | Eureka Service Discovery                         |
| API Gateway      | Route requests securely to correct microservices |

---

## 🔄 Kafka Topics Used

| Topic Name            | Producer              | Consumer               |
|-----------------------|-----------------------|------------------------|
| `user_registered`     | User Service          | Messaging Service      |
| `transaction_created` | Transaction Service   | Budget Service, Messaging Service |
| `goal_achieved`       | Goal Service          | Messaging Service      |
| `budget_exceeded`     | Budget Service        | Messaging Service      |

---

## 🔐 Authentication Flow

1. Client logs in via **User Service**
2. Credentials verified → User Service calls **Token Service** via Feign
3. Token Service returns **JWT + Refresh Token**
4. Access token is added to headers for secure routes
5. If JWT expires → Frontend sends refresh token to API Gateway
6. API Gateway forwards it to Token Service → New token generated

---

## 📬 Kafka Notification Flow (Example)

```text
User signs up  →  User Service sends 'user_registered' event →
Messaging Service consumes event  →  Sends Welcome Email
📂 Folder Structure (Simplified)
pgsql
Copy
Edit
event-driven-finance-management/
├── user-service/
├── token-service/
├── transaction-service/
├── budget-service/
├── goal-service/
├── messaging-service/
├── registry-service/
├── api-gateway/
├── kafka/
└── docker-compose.yml (coming soon)
🧪 How to Run Locally
Prerequisites
Java 17+

Apache Kafka (Zookeeper & Broker)

PostgreSQL

Maven

Docker (optional)

Steps
Start Kafka (via Docker or local install)

Start PostgreSQL

Run Eureka Server (registry-service)

Start each service individually using:

bash
Copy
Edit
cd user-service
mvn spring-boot:run
Or use docker-compose (in progress)

🧠 Future Enhancements
 Budget Forecasting with AI/ML

 Role-based access & admin panel

 Swagger/OpenAPI docs for all microservices

 Redis for token blacklisting

 Full observability stack (Prometheus + Grafana)

📄 API Samples
✅ Register User
http
Copy
Edit
POST /api/user/signup
{
  "name": "Aditya",
  "email": "aditya@example.com",
  "password": "securepass"
}
💳 Create Transaction
http
Copy
Edit
POST /api/transaction
Headers: Authorization: Bearer <Access_Token>
{
  "amount": 500,
  "type": "expense",
  "category": "groceries",
  "userId": "123"
}
🙋‍♂️ Author
Aditya Bedi
Backend Engineer | Kafka | Microservices | DevOps Learner
LinkedIn • GitHub

🧑‍💻 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change







