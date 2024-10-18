# Arago Ad System

This project is a microservice-based ad-serving and impression-tracking system. It consists of two microservices: an ad-serving service and an impression-tracking service, both integrated with a Redis database.

- **Ad-Serving Microservice**: Manages ad-related functionalities, including creating, retrieving, and serving ads.
- **Impression-Tracking Microservice**: Tracks ad impressions to ensure accurate monitoring.

## Repositories

- **Ad-Serving Microservice**: [Link to Ad-Serving Repository](https://github.com/laura-hbl/ad-serving-microservice.git)
- **Impression-Tracking Microservice**: [Link to Impression-Tracking Repository](https://github.com/laura-hbl/impression-tracking-microservice.git)

Both code are on **master** branch.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 21 JDK**
- **Maven 4.0.0**
- **Redis Server**

## Database - Redis

Both microservices use Redis for data storage and retrieval.

## Communication - gRPC & REST

The microservices communicate using **gRPC** for internal service-to-service communication, while external requests are managed through **REST**.
The protocol buffers (defined in .proto files) are located in the src/main/proto directory.

## Technical Specifications

The system is built using the following technologies:

- **Java 21**: For the backend logic.
- **Spring Boot**: A Java-based framework for building the microservices.
- **gRPC**: For inter-service communication.
- **Redis**: An in-memory data structure store, used as a database.
- **Maven**: For dependency management and project build.

## Running the Microservices Locally

To run the microservices on your local machine, follow these steps for each repository:

### Ad-Serving Microservice

1. **Clone the Ad-Serving Repository**:
   ```bash
   git clone https://github.com/laura-hbl/ad-serving-microservice.git
   cd ad-serving-microservice
   ```
2. **Start the Redis Server**: Ensure that the Redis server is up and running.
   Navigate to the redis directory
   ```bash
   cd bin
   ./redis-server
   ```
3. **Build the project**: Navigate to the Ad-Serving project's root directory and run:
   ```bash
   mvn clean install
   ```
4. **Run the Ad-Serving Microservice**:
   ```bash
   mvn spring-boot:run
   ```
5. The ad-serving microservice will run on `http://localhost:8080` and the redis server listen on port 9090.

### Impression-Tracking Microservice

1. **Clone the Impression-Tracking Repository**:
   ```bash
   git clone https://github.com/laura-hbl/impression-tracking-microservice.git
   cd impression-tracking-microservice
   ```
2. **Start the Redis Server**: Ensure that the Redis server is up and running.
   Navigate to the redis directory
   ```bash
   cd bin
   ./redis-server
   ```
3. **Build the project**: Navigate to the Impression-Tracking project's root directory and run:
   ```bash
   mvn clean install
   ```
4. **Run the Impression-Tracking Microservice**:
   ```bash
   mvn spring-boot:run
   ```
5. The impression-tracking microservice will run on `http://localhost:8081` and gRPC calls will be received on port 9090.

## API Endpoints

### Ad-Serving Microservice

1. **Create a new ad**

   - **Method**: `POST`
   - **Endpoint**: `http://localhost:8080/ads`
   - **Description**: Adds a new ad with the specified title, optional description, and URL.
   - **Request Body**:
     ```json
     {
       "title": "Ad Title", 
       "description": "Optional description", 
       "url": "http://example.com"
     }
     ```
   - **Response Body**:
     ```json
     {
      "id": "43962779-1043-42bd-9587-a8770bfb46a0",
      "title": "Ad Title", 
      "description": "Optional description", 
      "url": "http://example.com"
     }
     ```

2. **Retrieve an ad by its ID**

   - **Method**: `GET`
   - **Endpoint**: `http://localhost:8080/ads/{id}`
   - **Parameter**: `id` (required)
   - **Description**: Fetches the ad details based on the provided ID.
   - **Request**: `http://localhost:8080/ads/43962779-1043-42bd-9587-a8770bfb46a0`
   - **Response**:
     ```json
     {
      "id": "43962779-1043-42bd-9587-a8770bfb46a0",
      "title": "Ad Title", 
      "description": "Optional description", 
      "url": "http://example.com"
     }
     ```
3. **Serve an ad and track its impression**

   - **Method**: `GET`
   - **Endpoint**: `http://localhost:8080/ads/{id}/serve`
   - **Parameter**: `id` (required)
   - **Description**: Returns the ad's URL and tracks the impression for the given ad ID.
   - **Request**: `http://localhost:8080/ads/43962779-1043-42bd-9587-a8770bfb46a0/serve`
   - **Response**:
     ```json
     {
      "url": "http://example.com"
     }
     ```

### Impression-Tracking Microservice

1. **Increment the ad impression count**

   - **gRPC Method**: `TrackAdImpression`
   - **Service**: `TrackingService`
   - **Parameter**: `adId` (required)
   - **Description**: Increases the impression count for the specified ad using gRPC communication.

## Bonus Points

### Expiration Time

The expiration time for ads has been established, with ads automatically removed from the database after **5 minutes** for testing purposes.

### Rate limiting

A rate-limiting mechanism has been implemented on the ad creation endpoint, restricting users to a maximum of **5 requests per minute** to prevent abuse. If the limit is exceeded, the system will return a 'Rate limit exceeded. Try again later.' message.

### Validation

Basic input validation has been added for creating ads (ensure title and URL are present and not empty).

## Running Tests

The project includes some unit tests for both services. To run the tests, execute:

```bash
mvn test
```
