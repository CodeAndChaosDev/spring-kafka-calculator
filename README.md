# Calculator REST API with Kafka Integration

This project is a RESTful API for performing basic arithmetic calculations. It leverages **Spring Boot** for the REST API and integrates **Apache Kafka** for handling asynchronous calculation requests. The project is containerized using Docker for easy deployment.

## Features

1. **RESTful Endpoints**: Provides endpoints to perform addition, subtraction, multiplication, and division.
2. **Kafka Integration**: Sends calculation requests to a Kafka topic and processes responses asynchronously.
3. **Modular Structure**: Divided into two main components:
    - **REST API Module**: Exposes HTTP endpoints.
    - **Calculator Module**: Processes arithmetic calculations.
4. **Dockerized Deployment**: Easily build and deploy the application using Docker.

---

## Project Structure

```
project-root/
├── pom.xml                      # Parent POM (for multi-module Maven projects)
├── rest/                        # REST API Module
│   ├── pom.xml                  # REST module dependencies and build configuration
│   ├── src/main/java/           # REST API code
│   └── Dockerfile               # Docker configuration for REST API
├── calculator/                  # Calculator Module
│   ├── pom.xml                  # Calculator module dependencies and build configuration
│   ├── src/main/java/           # Kafka consumer and calculation logic
│   └── Dockerfile               # Docker configuration for Calculator
└── docker-compose.yml           # Docker Compose file for multi-container deployment
```

---

## Technologies Used

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Kafka
- **Apache Kafka**
- **Docker**
- **Maven**

---

## Setup and Installation

### Prerequisites

1. Install **Docker** and **Docker Compose**.
2. Install **Java 17** and **Maven**.

---

### Build the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd project-root
   ```

2. Build the Maven modules:
   ```bash
   mvn clean package
   ```

---

### Running with Docker Compose

1. Build the Docker images:
   ```bash
   docker-compose build
   ```

2. Start the services:
   ```bash
   docker-compose up
   ```

This will start:
- **Kafka Broker**
- **REST API Service**
- **Calculator Service**

---

### REST API Endpoints

Base URL: `http://localhost:8080/api/calculator`

| Method | Endpoint          | Description                             |
|--------|-------------------|-----------------------------------------|
| POST   | `/{operation}`    | Sends a calculation request to Kafka.   |

#### Example Request

- **Endpoint**: `POST /add`
- **Query Parameters**:
  - `num1`: First number (e.g., `5`)
  - `num2`: Second number (e.g., `3`)

**Curl Example**:
```bash
curl -X POST "http://localhost:8080/api/calculator/add?num1=5&num2=3"
```

**Response**:
```
Request sent to Kafka topic: add,5,3
```

---

### Kafka Topics

- **Input Topic**: `calculator-requests`
  - Receives messages in the format: `<operation>,<num1>,<num2>`
- **Output Topic**: `calculator-responses`
  - Sends responses in the format: `<operation>,<num1>,<num2>,<result>`

---

## Configuration

### Kafka Configuration
Update Kafka configurations in the `application.properties` files:

- **REST Module**:
  ```properties
  spring.kafka.bootstrap-servers=localhost:9092
  spring.kafka.template.default-topic=calculator-requests
  ```

- **Calculator Module**:
  ```properties
  spring.kafka.bootstrap-servers=localhost:9092
  spring.kafka.consumer.group-id=calculator-group
  spring.kafka.consumer.auto-offset-reset=earliest
  ```

---

## Development

### Testing

Run unit tests:
```bash
mvn test
```

### Logs

Both modules use **SLF4J** and **Logback** for logging. Logs are output to the console by default.

---

## Future Enhancements

- Add support for additional operations (e.g., square root, power).
- Implement a user interface for sending calculation requests.
- Improve fault tolerance and retry logic for Kafka consumers.
- Fix the current entrypoints bug due to faulty docker files. "No jar encountered on both modules." (**BUG**)

---

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

---

## Contributing

Contributions are welcome! Feel free to submit issues or pull requests to improve the project.

