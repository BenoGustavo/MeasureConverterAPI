# MeasureConverter Project Documentation

MeasureConverter is an all-in-one converter application built using Java Spring Boot. It provides various conversion functionalities and is designed to be easily extendable. This project is developed to improve the understanding of Spring Boot and its ecosystem. It is also a part of the Programming Language 3 subject curriculum.

## Project Structure

```
MeasureConverter/
├───.mvn
│   └───wrapper
├───src
│   ├───test
│   │   └───java
│   │       └───com
│   │           └───api
│   │               └───MeasureConverter
│   └───main
│       ├───java
│       │   └───com
│       │       └───api
│       │           └───measureconverter -> Main Folder
│       │               ├───seed -> Database seeders
│       │               ├───repositories
│       │               ├───error -> Global handler & custom errors
│       │               ├───services
│       │               ├───config -> Swagger, Security and app config files
│       │               ├───utils
│       │               │   ├───reponse
│       │               │   ├───enums
│       │               │   ├───dto -> DataTransferObjects
│       │               │   └───jwt -> Jwt utility code
│       │               ├───model -> Database models
│       │               ├───filter -> Jwt filter
│       │               └───controller
│       └───resources -> App properties
└───target
       ├───test-classes
       └───com
            └───api
                └───{Compiled-code}
```

## Project Description

MeasureConverter is an all-in-one converter application built using Java Spring Boot. It provides various conversion functionalities and is designed to be easily extendable.


## Requirements

To run the MeasureConverter application, you need to meet the following requirements:

1. **Java Development Kit (JDK)**:
   - Version 11 or higher.
   - Ensure that `JAVA_HOME` is set correctly.

2. **Maven**:
   - Apache Maven 3.6.0 or higher.
   - Used for building and managing the project dependencies.

3. **Database**:
   - A running instance of a database (e.g., PostgreSQL, MySQL).
   - Ensure you have the database URL, username, and password.

4. **Environment Variables**:
   - Create a [`.env`](./.env) file in the root directory with the necessary environment variables as specified in the [`.env-example`](./.env-example).

5. **Spring Boot**:
   - Spring Boot dependencies are managed via Maven, so no additional installation is required.

6. **Git**:
   - Git for version control (optional but recommended).

Ensure all the above requirements are met before attempting to build and run the application.

## How to Execute the Project

1. **Create your own [`.env`](./.env) file**

   Copy the contents of [`.env-example`](./.env-example) to a new file named [`.env`](./.env) and replace the placeholder values with your actual configuration.

    ### Example [`.env`](./.env) File

    ```env
    DATABASE_URL=your_database_url
    DATABASE_USERNAME=your_database_username
    DATABASE_PASSWORD=your_database_password
    SHOW_SQL=true
    JWT_SECRET_KEY=your_secret_key

    # ADMIN_CREATION
    ADMIN_USERNAME=your_admin_username
    ADMIN_EMAIL=your_admin_email
    ADMIN_PASSWORD=your_admin_password
    ```

### Steps to Create [`.env`](./.env) File

1. **Copy [`.env-example`](./.env-example) to [`.env`](./.env):**

   ```sh
   cp .env-example .env
   ```

2. **Edit [`.env`](./.env) File:**

   Open the [`.env`](./.env) file in your preferred text editor and replace the placeholder values (`CHANGE-ME`) with your actual configuration values.

   ```sh
   nano .env
   ```

   Replace the placeholders with your actual values:

   ```env
   DATABASE_URL=your_database_url
   DATABASE_USERNAME=your_database_username
   DATABASE_PASSWORD=your_database_password
   SHOW_SQL=true
   JWT_SECRET_KEY=your_secret_key

   # ADMIN_CREATION
   ADMIN_USERNAME=your_admin_username
   ADMIN_EMAIL=your_admin_email
   ADMIN_PASSWORD=your_admin_password
   ```

3. **Save and Close the File:**

   Save the changes and close the text editor.

    By following these steps, you will have a properly configured [`.env`]("./.env") file for your project.

2. **Clone the Repository:**
   ```sh
   git clone <repository-url>
   cd MeasureConverter
   ```

3. **Build the Project:**
   Ensure you have Maven installed. Run the following command to build the project:
   ```sh
   mvn clean install
   ```

4. **Run the Application:**
   You can run the application using the Spring Boot Maven plugin:
   ```sh
   mvn spring-boot:run
   ```

   Alternatively, you can run the generated JAR file:
   ```sh
   java -jar target/MeasureConverter-0.0.1-SNAPSHOT.jar
   ```

## How to compile compile

   ```sh
   mvn package && java -jar target/MeasureConverter-0.0.1-SNAPSHOT.jar
   ```

## Main Classes

- **[`MeasureConverterApplication`](./src/main/java/com/api/measureconverter/MeasureConverterApplication.java):**
  The main entry point of the application. It contains the [`main`](./src/main/java/com/api/measureconverter/MeasureConverterApplication.java "Go to definition") method which starts the Spring Boot application.

  ```java
  @SpringBootApplication
  public class MeasureConverterApplication {
      public static void main(String[] args) {
          SpringApplication.run(MeasureConverterApplication.class, args);
      }
  }
  ```

- **[`MeasureConverterApplicationTests`](./src/test/java/com/api/MeasureConverter/MeasureConverterApplicationTests.java):**
  Contains unit tests for the application.

## Configuration

- **`application.properties`:**
  Located in [`src/main/resources`](./src/main/resources/application.properties), this file contains the configuration properties for the Spring Boot application, major of the infos here are filled with the .env file.

## Additional Information

- **[`.gitignore`](.gitignore):**
  Specifies files and directories that should be ignored by Git.

- **[`pom.xml`](./pom.xml):**
  The Maven Project Object Model file which contains information about the project and configuration details used by Maven to build the project.

## License

This project is licensed under the terms specified in the [`LICENSE`](./LICENSE) file.

---

Feel free to update this documentation as the project evolves.