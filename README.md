# Backend Installation Guide
**Project name: KaiBank**

---

- [Backend Installation Guide](#backend-installation-guide)
  * [[TOC]](#-toc-)
  * [Technology stack](#technology-stack)
  * [Setup local environment](#setup-local-environment)
  * [Setup database](#setup-database)
  * [Useful plugins (Intellij extensions)](#useful-plugins--intellij-extensions-)
  * [How to build the project:](#how-to-build-the-project-)
    + [Clone the project from the repository :](#clone-the-project-from-the-repository--)
    + [Build the project.](#build-the-project)
    + [Run the project.](#run-the-project)
    + [Access the application.](#access-the-application)
  * [Local database credentials can be found in application.yml:](#local-database-credentials-can-be-found-in-applicationyml-)
  * [Access API documentation](#access-api-documentation)
  * [Architecture](#architecture)
    

---

## Technology stack

| Technology | Description | Link to download |
| --- | --- | --- |
| Java 11 | Programming Language | [Link](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) |
| Spring Boot | Backend framework | [Link](https://www.mysql.com/downloads/) |
| Maven 3.6.3 | Build automation tool | [Link](https://maven.apache.org/download.cgi) |
| Apache Tomcat | Web server | |
| MySql 8 | Database | |
| Intellij | IDE | [Link](https://www.jetbrains.com/idea/download/#section=windows) |

---

## Setup local environment
Needs to install `Java 11`, `Maven 3.6.3` or above, `MySql 8` and `Intellij`.

## Setup database
1. Create a database using below SQL script
    - `CREATE DATABASE kaibank;`
2. Create a user using the below SQL script
    - `CREATE DATABASE kaibank;`
    - `CREATE USER 'kaibankuser'@'localhost' IDENTIFIED BY '1qaz2Wsx';`
    - `GRANT ALL ON kaibank.* TO 'kaibankuser'@'localhost';`


## Useful plugins (Intellij extensions)
    
- `Spring Assistant` - assist in developing spring applications
- `Lombok` - to remove boilerplate codes
- `google-java-format` - to format the code
- `SonarLint` - to check code quality

## How to build the project:
1. ### Clone the project from the repository :
    ```shell
    git clone git@github.com:ashfaqshuvo007/kaibank-backend-springboot.git
    ```

2. ### Build the project.
        - Go to project directory
        - Run command: `mvn clean install`

3. ### Run the project.
- Using IDE:
    - Open project in Intellij
    - Right-click on the main spring boot application class (`KaibankSystemApplication`) and click on Run.
- Run from terminal ( as spring boot project):
    - Go to the project directory
    - Run this command: `mvn spring-boot:run`

4. ### Access the application.

- Access the deployed application: http://localhost:8081/kaibank-system/


## Local database credentials can be found in application.yml:

- [Link to application.yml](https://gitlab.com/pabasarajw/kaibank-backend/-/blob/main/kaibank-system/src/main/resources/application.yml)

## Access API documentation 
Swagger API documentation can be found here: http://localhost:8081/kaibank-system/api-ui.html

## Architecture

All details of the architecture of the system with class diagram, sequence diagrams etc. 
can be found here: [Architecture Details](architecture.md)