# Tous Les Temp Store Management

This project is a store management system for Tous Les Temp, built with Spring Boot 3.0 for the backend and Angular 15 for the frontend. It integrates various technologies and features for enhanced functionality.

## Features

- User authentication and authorization using Spring Security
- Two-factor authentication (2FA) with OTP verification
- Integration with Thymeleaf for sending emails
- API documentation management with Swagger 3.0
- Angular Material for UI components
- Chart.js for data visualization
- Integration with Google Maps API for displaying maps

## Prerequisites

Before running the project, make sure you have the following prerequisites installed:

- Java JDK 17
- Node.js
- Angular CLI

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Backend Setup

1. Clone the repository 
  - git clone https://github.com/imstudent2210/DATN.git
2. Navigate to the `TousLesTemp` directory.
  - cd TousLesTemp
3. Install the required dependencies using Maven:
  - mvn install
4. Configure the application properties:
  - Open the src/main/resources/application.yaml file.
  - Modify the database configuration, such as the URL, username, and password, to match your setup.
  - Customize other configuration properties as needed.

### Frontend Setup

1. Navigate to the `TousLestemp-frontend` directory.
2. Install the required package:
  - npm install
3. Run the frontend server:
  - ng serve

## Usage

### Running the Backend

1. Navigate to the `TousLesTemp` directory.
2. Run the backend server: 
  - mvn spring-boot:run

### Running the Frontend

1. Navigate to the `frontend` directory.
2. [Instructions to run the frontend]

## Documentation

The API documentation is generated using Swagger 3.0. You can access the API documentation by following these steps:

1. Start the backend server.
2. Open your browser and navigate to: http://localhost:9090/touslestemp/swagger-ui/index.html

## Contributing

We welcome contributions to improve this project. To contribute, please follow these steps:

1. Fork the project repository.
2. Create a new branch.
3. Make your changes and commit them.
4. Push the changes to your forked repository.
5. Submit a pull request.

## Contact

If you have any questions or suggestions, feel free to reach out to us at imstudent2210@gmail.com
