\# MaltaShop Microservices



A microservices-based e-commerce prototype demonstrating REST APIs, asynchronous messaging with RabbitMQ, independent MySQL databases, and Docker Compose deployment.



\## Architecture



The project contains two Spring Boot microservices:



\- \*\*Order Service\*\* — exposes a REST API to create orders and publishes an `OrderPlaced` event.

\- \*\*Notification Service\*\* — consumes `OrderPlaced` events from RabbitMQ and stores notification records.



Each service owns a separate MySQL database.



\### Technology Stack



\- Java 21

\- Spring Boot

\- Spring Data JPA and Hibernate

\- MySQL 8.4

\- RabbitMQ

\- Maven

\- Docker and Docker Compose



\### Architecture Flow



1\. A client sends an HTTP POST request to the Order Service.

2\. The Order Service stores the order in `order\_db`.

3\. The Order Service publishes an `OrderPlaced` event to RabbitMQ.

4\. The Notification Service consumes the event.

5\. The Notification Service stores the notification in `notification\_db`.



\## Project Structure



```text

maltashop-microservices/

├── order-service/

│   ├── src/

│   ├── pom.xml

│   └── Dockerfile

├── notification-service/

│   ├── src/

│   ├── pom.xml

│   └── Dockerfile

├── docker-compose.yml

├── .env

├── .gitignore

└── README.md

```



\## Prerequisites



Install the following tools:



\- Docker Desktop with Docker Compose

\- Git

\- Postman or another HTTP client for testing



Docker Compose builds the Java services and starts the application dependencies.



\## Environment Configuration



Create a `.env` file in the project root:



```dotenv

DB\_USERNAME=root

DB\_PASSWORD=your\_local\_mysql\_password

```



Replace the password placeholder with your own local MySQL password. Do not commit real credentials to GitHub.



The Compose configuration uses these variables to configure the database containers and Spring Boot services.



\## Run the Application



Clone the repository:



```bash

git clone https://github.com/rameshbhandari11458/maltashop-microservices.git

cd maltashop-microservices

```



Create and configure the `.env` file, then start the system:



```bash

docker compose up --build -d

```



Check the container status:



```bash

docker compose ps

```



View service logs:



```bash

docker compose logs -f order-service notification-service

```



Stop the application:



```bash

docker compose down

```



The named database volumes preserve database data when containers are stopped and removed. To delete the stored database data as well, use `docker compose down -v` with care.



\## API Testing



\### Create an Order



\*\*Endpoint:\*\* `POST http://localhost:8080/api/orders`



\*\*Content-Type:\*\* `application/json`



Request body:



```json

{

&#x20; "customerId": 201,

&#x20; "productId": 45,

&#x20; "quantity": 2,

&#x20; "totalAmount": 1200

}

```



A successful request returns HTTP `201 Created` with the created order details.



\### Verify Notification Processing



Inspect the Notification Service logs:



```bash

docker compose logs --tail=100 notification-service

```



Look for messages indicating that an `OrderPlaced` event was received and the notification was saved.



The Notification Service runs on port `8081`. RabbitMQ's management interface is available at `http://localhost:15672` with the default local development credentials `guest` / `guest`.



\## Databases and Ports



| Component | Database / Purpose | Host Port |

|---|---|---:|

| Order Service | REST API | 8080 |

| Notification Service | Notification consumer | 8081 |

| Order MySQL | `order\_db` | 3307 |

| Notification MySQL | `notification\_db` | 3308 |

| RabbitMQ | AMQP messaging | 5672 |

| RabbitMQ Management | Web interface | 15672 |



Within Docker Compose, the services communicate using their Compose service names rather than `localhost`.



\## Current Scope



This prototype demonstrates separate service ownership, REST-based order creation, asynchronous event delivery, persistent notification storage, and containerized execution.



Further improvements may include automated CI tests, correlation IDs for distributed tracing, application health checks, non-root container users, stronger secret management, and resilient event-processing strategies.



