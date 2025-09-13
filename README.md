# Quiz App REST API

A Spring Boot REST API for creating, managing, and taking quizzes. The application allows users to create quizzes from a pool of questions, filter questions by category, and submit answers to receive scores. It uses PostgreSQL for data storage.

## Features
- Create quizzes with random questions from a selected category
- Add and manage quiz questions
- Filter questions by category
- Submit quiz responses and receive a score

## Technologies Used
- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Getting Started

### Prerequisites
- Java 21+
- Maven
- PostgreSQL

### Setup
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd quiz-app-rest-api
   ```
2. **Configure the database:**
   - Ensure PostgreSQL is running.
   - Create a database named `questiondb`.
   - Open `src/main/resources/application.properties` and set your database username and password:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/questiondb
     spring.datasource.username= your_db_username
     spring.datasource.password= your_db_password
     ```
     
   - **Recommended:** For better security, you can set these as environment variables before running the app:
     - On Windows (Command Prompt):
       ```cmd
       set your_db_username=your_db_username
       set your_db_password=your_db_password
       ```
     - On Linux/macOS:
       ```bash
       export your_db_username=your_db_username
       export your_db_password=your_db_password
       ```
     Then reference them in `application.properties` as:
     ```properties
     spring.datasource.username=${your_db_username}
     spring.datasource.password=${your_db_password}
     ```
3. **Build and run the application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8081/`.

     spring.datasource.password=root
   mvn clean install
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8081/`.

## API Endpoints

### Quiz Endpoints

#### Create a Quiz
- **POST** `/quiz/create?category={category}&numQ={numQ}&title={title}`
- **Description:** Create a new quiz with random questions from a category.
- **Parameters:**
  - `category` (String, required): The category of questions.
  - `numQ` (Integer, required): Number of questions in the quiz.
  - `title` (String, required): Title for the quiz.
- **Response:**
  - `201 Created` on success, with body: `"success"`

#### Get Quiz Questions
- **GET** `/quiz/get/{quizid}`
- **Description:** Retrieve questions for a specific quiz.
- **Path Variable:**
  - `quizid` (Integer, required): The ID of the quiz.
- **Response:**
  - `200 OK` with body: List of questions (without answers), e.g.
    ```json
    [
      {
        "id": 1,
        "questionTitle": "What is Java?",
        "option1": "A programming language",
        "option2": "A coffee",
        "option3": "An island",
        "option4": "A car"
      },
      ...
    ]
    ```

#### Submit Quiz Answers
- **POST** `/quiz/submit/{quizid}`
- **Description:** Submit answers for a quiz and get the score.
- **Path Variable:**
  - `quizid` (Integer, required): The ID of the quiz.
- **Request Body:**
  - JSON array of responses:
    ```json
    [
      { "id": 1, "response": "A programming language" },
      { "id": 2, "response": "Option text" }
    ]
    ```
- **Response:**
  - `200 OK` with body: Integer score (number of correct answers)

### Question Endpoints

#### Get All Questions
- **GET** `/question/allQuestions`
- **Description:** Get all questions in the database.
- **Response:**
  - `200 OK` with body: List of all questions (including answers)

#### Filter Questions by Category
- **GET** `/question/filterByCategory/{category}`
- **Description:** Get questions filtered by category.
- **Path Variable:**
  - `category` (String, required): The category to filter by.
- **Response:**
  - `200 OK` with body: List of questions in the category

#### Add a New Question
- **POST** `/question/addQuestion`
- **Description:** Add a new question to the database.
- **Request Body:**
  - JSON object:
    ```json
    {
      "questionTitle": "What is Java?",
      "option1": "A programming language",
      "option2": "A coffee",
      "option3": "An island",
      "option4": "A car",
      "rightAnswer": "A programming language",
      "difficultyLevel": "Easy",
      "category": "Programming"
    }
    ```
- **Response:**
  - `201 Created` on success, with body: `"success"`

## Data Models

### Question
- `id`: Integer
- `questionTitle`: String
- `option1`, `option2`, `option3`, `option4`: String
- `rightAnswer`: String
- `difficultyLevel`: String
- `category`: String

### Quiz
- `id`: Integer
- `title`: String
- `questions`: List of Question

### Response
- `id`: Integer (question id)
- `response`: String (user's answer)

## Configuration
- Application properties are in `src/main/resources/application.properties`.
- Default server port: 8081
- Default DB: PostgreSQL (`questiondb`)

## Contact
For questions, contact the project maintainer.
