# Arago Ad System

This project is a microservice-based ad-serving and impression-tracking system. It consists of two microservices: an ad-serving service and an impression-tracking service, both integrated with a Redis database. The microservices communicate with each other using **gRPC**

- **Ad-Serving Microservice**: Manages ad-related functionalities, including creating, retrieving, and serving ads.
- **Impression-Tracking Microservice**: Tracks ad impressions to ensure accurate monitoring.

## Repositories

- **Ad-Serving Microservice**: [Link to Ad-Serving Repository](https://github.com/laura-hbl/ad-serving-microservice.git)
- **Impression-Tracking Microservice**: [Link to Impression-Tracking Repository](https://github.com/laura-hbl/impression-tracking-microservice.git)

Both code are on master branch.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 21 JDK**
- **Maven 4.0.0**
- **Spring Boot 3.4.0**
- **gRPC 3.1.0.RELEASE** 
- **Redis Server**

## Database - Redis

Both microservices use Redis for data storage and retrieval. 

## Communication - gRPC

The microservices communicate using **gRPC** .The protocol buffers (`.proto` files) are defined in  .

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
   cd ad-serving-repo
   ```
2. **Start the Redis Server**: Ensure that the Redis server is up and running.
3. **Build the project**: Navigate to the Ad-Serving project's root directory and run:
   ```bash
   mvn clean install
   ```
4. **Run the Ad-Serving Microservice**:
   ```bash
   mvn spring-boot:run
   ```
5. The ad-serving microservice will run on `http://localhost:8080`.

### Impression-Tracking Microservice

1. **Clone the Impression-Tracking Repository**:
   ```bash
   git clone https://github.com/laura-hbl/impression-tracking-microservice.git
   cd impression-tracking-repo
   ```
2. **Start the Redis Server**: Ensure that the Redis server is up and running.
3. **Build the project**: Navigate to the Impression-Tracking project's root directory and run:
   ```bash
   mvn clean install
   ```
4. **Run the Impression-Tracking Microservice**:
   ```bash
   mvn spring-boot:run
   ```
5. The impression-tracking microservice will run on `http://localhost:8081`.

## API Endpoints

### Ad-Serving Microservice

1. **Create a new ad**

   - **Method**: `POST`
   - **Endpoint**: `http://localhost:8080/api/ads`
   - **Request Body**: 
     ```json
     {
       "title": "Ad Title", 
       "description": "Optional description", 
       "url": "http://example.com"
     }
     ```
   - **Description**: Adds a new ad with the specified title, optional description, and URL.

2. **Retrieve an ad by its ID**

   - **Method**: `GET`
   - **Endpoint**: `http://localhost:8080/api/ads/{id}`
   - **Parameter**: `id` (required)
   - **Description**: Fetches the ad details based on the provided ID.

3. **Serve an ad and track its impression**

   - **Method**: `GET`
   - **Endpoint**: `http://localhost:8080/api/ads/{id}/serve`
   - **Parameter**: `id` (required)
   - **Description**: Returns the ad's URL and tracks the impression for the given ad ID.

### Impression-Tracking Microservice

1. **Increment the ad impression count**

   - **gRPC Method**: `IncrementImpression`
   - **Service**: `ImpressionTrackerService`
   - **Parameter**: `adId` (required)
   - **Description**: Increases the impression count for the specified ad using gRPC communication.


## Running Tests

The project includes tests for both services. To run the tests, execute:

```bash
mvn test
```
