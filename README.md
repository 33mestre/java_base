[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Build and Test For PR](https://github.com/33mestre/java_base/actions/workflows/main.yml/badge.svg)](https://github.com/33mestre/java_base/actions/workflows/main.yml)

[![Known Vulnerabilities](https://snyk.io/test/github/33mestre/java_base/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/33mestre/java_base?targetFile=pom.xml) 

![GitHub repo size](https://img.shields.io/github/repo-size/33mestre/java_base.svg) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/33mestre/java_base.svg) ![GitHub top language](https://img.shields.io/github/languages/top/33mestre/java_base.svg) ![GitHub language count](https://img.shields.io/github/languages/count/33mestre/java_base.svg) ![GitHub last commit](https://img.shields.io/github/last-commit/33mestre/java_base.svg)

[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/33mestre/java_base.svg)](http://isitmaintained.com/project/33mestre/java_base) [![Percentage of issues still open](http://isitmaintained.com/badge/open/33mestre/java_base.svg)](http://isitmaintained.com/project/33mestre/java_base)

--- 

# Currency Conversion API

![Currency Symbol](sys/symbol.jpg)

## Goal

A Currency conversion project - server, database, access clients (input data and list data).

## In a Nutshell

Steps to download, install, configure, run and start the Java Rest API server, the database server, insert data into the database, as well as test and view the persisted data in the project:

**Open the Command Prompt and Clone the GitHub repository and access the project folder:**
    
```bash
git clone https://github.com/33mestre/java_base.git
cd java_base
```

### Procedures for Unix / Linux Environment

**Define Scripts Permissions:**

```bash
chmod +x index.sh
chmod +x scripts/*.sh
```

### Procedures for MS Windows Environment

No special permissions are required.

### Running the Project

**Execute the main script:**

For Unix / Linux:

```bash
./index.sh
```

For Windows:

```bash
index.bat
```

**Menu Options:**

When you run the main script (`index.sh` or `index.bat`), you will be presented with the following options:

```bash
Select an option:
[1] - Server
[2] - Console
[3] - DB
[4] - Directory
[5] - Changelog
```

**Options Description:**

- **Server:** Starts the Java Spring server with the application running.
- **Console:** Launches the access client to interact with the currency conversion application.
- **DB:** Runs the data lister to view the data persisted in the H2 database.
- **Directory:** Generates the `DIRECTORY.md` file (used for testing purposes, as this is also automated in the CI/CD pipeline).
- **Changelog:** Updates the `CHANGELOG.md` file (used for testing purposes, as this is also automated in the CI/CD pipeline).

With just these simple steps, you should have:

- A Java Spring server with the application running.
- An H2 Database server running.
- An access client to access the currency conversion application.
- A data lister for the data persisted in the database.

This setup requires only Docker installed, without additional installations, working in MS Windows, Linux, and Unix environments. The scripts will handle the Docker installation if it's not already installed; for MS Windows, a notification will prompt you to download it manually.

## Project authorship

> [Authors](./AUTHORS.md)

## Code of Conduct

> [Project Contributor Code of Conduct](./CODE_OF_CONDUCT.md)

## Project version history

> [Project Change Log](./CHANGELOG.md)

The generation of `CHANGELOG.md` is automated and occurs with each new commit to the repository, thanks to a CI/CD pipeline. This pipeline uses a Docker container that runs a Groovy script to extract commit information, such as hash, date, author, and message, organizing them in an incremental version format. Commits containing the keyword `"AUTO_COMMIT"` are ignored. The Groovy script is responsible for generating the changelog, continuously updating it to reflect all changes made to the project in a precise and detailed manner. The process begins with the CI/CD pipeline calling the Groovy script inside the Docker container, ensuring the changelog is generated correctly.

## Project JavaDoc (Markdown format)

[Project JavaDoc](docs/site/index.md)

---

## Wiki Menu

### 1. Introduction to the Project
- [Overview](https://github.com/33mestre/java_base/wiki/overview): Presentation of the project, highlighting its purpose and the context in which it is embedded.
- [Project Objectives](https://github.com/33mestre/java_base/wiki/project-objectives): Enumeration of the main objectives that the project aims to achieve.
- [Scope and Functionalities](https://github.com/33mestre/java_base/wiki/scope-and-functionalities): Description of the main functionalities offered by the project and its scope of operation.

### 2. Configuration and Installation
- [Initial Configurations](https://github.com/33mestre/java_base/wiki/initial-configurations): Steps required to set up the development or production environment.
- [Installation Instructions](https://github.com/33mestre/java_base/wiki/installation-instructions): Detailed procedures for installing the project in different environments.
- [Docker Configuration](https://github.com/33mestre/java_base/wiki/docker-configuration): Specifications on how to configure and use Docker for the project.

### 3. Project Structure
- [Folder Structure](/DIRECTORY.md): Description of the organization of the project directories.
- [Project Architecture](/ARCHITECTURE.md): Explanation of the architecture used, including design patterns and technical decisions.

### 4. Development
- [Development Flow](https://github.com/33mestre/java_base/wiki/development-flow): Description of the development process adopted, including planning, coding, and review stages.
- [Contributors and Authors](/AUTHORS.md): Recognition of the contributors to the project.
- [Contributions](/CONTRIBUTING.md): Guidelines on how to contribute to the project, including code standards and pull request requirements, tips and best practices.
- [Code of Conduct](/CODE_OF_CONDUCT.md): Behavioral guidelines expected for the project community.

### 5. API and Documentation
- [OpenAPI Specification](https://github.com/33mestre/java_base/wiki/openapi-specification): Details about the OpenAPI specification used to document the API endpoints.
- [API Documentation with Swagger](https://github.com/33mestre/java_base/wiki/api-documentation-with-swagger): Information on how to access and use the interactive API documentation generated by Swagger.
- [Javadoc Documentation](https://github.com/33mestre/java_base/wiki/javadoc-documentation): Information on the Javadoc documentation generated for the project.

### 6. Endpoints and Database
- [Endpoint Description](https://github.com/33mestre/java_base/wiki/endpoint-description): Details of the available API endpoints, including methods, parameters, and usage examples.
- [Database Management](https://github.com/33mestre/java_base/wiki/database-management): Strategies and practices for efficient management of the database used by the project.

### 7. Testing
- [Testing Strategies](https://github.com/33mestre/java_base/wiki/testing-strategies): Approach and methods used to test the software, including unit, integration, and E2E tests.
- [Testing Tools](https://github.com/33mestre/java_base/wiki/testing-tools): Description of the testing tools used in the project and how to configure them.

### 8. CI/CD and Automations
- [CI/CD Pipeline](https://github.com/33mestre/java_base/wiki/ci-cd-pipeline): Explanation of the continuous integration and delivery pipeline, detailing each stage and its function.
- [Automations and Artifact Generation](https://github.com/33mestre/java_base/wiki/automations-and-artifact-generation): Description of the automations incorporated into the CI/CD, including documentation generation and build artifacts.

### 9. Configuration Files
- [.gitignore, .editorconfig and project.properties](https://github.com/33mestre/java_base/wiki/gitignore-and-editorconfig-and-project-properties): Utility of these files to maintain code consistency and quality.
- [Maven Wrapper and application.properties](https://github.com/33mestre/java_base/wiki/maven-wrapper-and-application-properties): Explanation of how these files help standardize the development environment.
- [.env File and Travis CI Settings](https://github.com/33mestre/java_base/wiki/env-file-and-travis-ci-settings): Use of these files to configure the environment and CI integrations.

### 10. Best Practices
- [Code Standards and Security](https://github.com/33mestre/java_base/wiki/code-standards-and-security): Guidelines for maintaining code quality and security.
- [Monitoring and Logging Practices](https://github.com/33mestre/java_base/wiki/monitoring-and-logging-practices): Recommended techniques for monitoring and logging in the project.

### 11. Legal and Licensing
- [Licensing](https://github.com/33mestre/java_base/wiki/licensing): Information about the rights and restrictions associated with the use of the software.
- [Terms of Use](https://github.com/33mestre/java_base/wiki/terms-of-use): Information about the terms and conditions for using the software.

### 12. Projections and Innovations
- [Future Plans](/FUTURE.md): Discussion on functionalities and improvements considered for future versions of the project.
- [Improvement Proposals](https://github.com/33mestre/java_base/wiki/improvement-proposals): Space for the community to suggest and debate improvements and innovations.

### 13. Attachments and Useful Links
- [External Links and References](https://github.com/33mestre/java_base/wiki/external-links-and-references): Additional resources and external documentation relevant to the project.

### 14. Security
- [Security Policy](/SECURITY.md): Details on the supported versions, reporting vulnerabilities, and general security practices.
