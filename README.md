[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Build and Test For PR](https://github.com/33mestre/java_base/actions/workflows/main.yml/badge.svg)](https://github.com/33mestre/java_base/actions/workflows/main.yml)

[![Known Vulnerabilities](https://snyk.io/test/github/33mestre/java_base/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/33mestre/java_base?targetFile=pom.xml) 

![GitHub repo size](https://img.shields.io/github/repo-size/33mestre/java_base.svg) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/33mestre/java_base.svg) ![GitHub top language](https://img.shields.io/github/languages/top/33mestre/java_base.svg) ![GitHub language count](https://img.shields.io/github/languages/count/33mestre/java_base.svg) ![GitHub last commit](https://img.shields.io/github/last-commit/33mestre/java_base.svg)

[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/33mestre/java_base.svg)](http://isitmaintained.com/project/33mestre/java_base) [![Percentage of issues still open](http://isitmaintained.com/badge/open/33mestre/java_base.svg)](http://isitmaintained.com/project/33mestre/java_base)

--- 

# Currency Conversion API

## Goal

A Currency conversion project - server, database, access clients (input data and list data).

## In a nutshell

**Steps to download, install, configure, run and start the Java Rest API server, the database server, insert data into the database, as well as test and view the persisted data in the project:**

- **Open the Command Prompt**

- **Clone the GitHub repository and access the project folder:**
    
```bash
git clone https://github.com/33mestre/java_base.git
cd shelson
```

### Procedures for Unix / Linux Environment

**Permissions:**

```bash
chmod +x index.sh
chmod +x scripts/*.sh
```

**Server:**

```bash
bash server.sh
```

**Access Client:**

```bash
bash console.sh
```

**Data Listing:**

```bash
bash db.sh
```

### Procedures for MS Windows Environment

```bash
server.bat
```

**Access Client:**

```bash
console.bat
```

**Data Listing:**

```bash
db.bat
```

It's Ready. With just these simple steps, you should have:

- A Java Spring server with the application running;
- An H2 Database server running;
- An access client to access the currency conversion application;
- A data lister for the data persisted in the database.

Only with Docker installed, without additional installations, working in MS Windows, Linux, and Unix environments.

When running the scripts, Docker is installed via terminal by the scripts if it is not already installed; in the case of MS Windows, you will be notified that it is necessary to download manually.

Next, an extensive documentation of all aspects of the project.

## Project authorship

> [Authors](./AUTHORS.md)

## Code of Conduct

> [Project Contributor Code of Conduct](./CODE_OF_CONDUCT.md)

## Project version history

> [Project Change Log](./CHANGELOG.md)

The generation of `CHANGELOG.md` is automated and occurs with each new commit to the repository, thanks to a CI/CD pipeline. This pipeline uses a Docker container that runs a Groovy script to extract commit information, such as hash, date, author, and message, organizing them in an incremental version format. Commits containing the keyword `"AUTO_COMMIT"` are ignored. The `Groovy script` is responsible for generating the changelog, continuously updating it to reflect all changes made to the project in a precise and detailed manner. The process begins with the CI/CD pipeline calling the Groovy script inside the Docker container, ensuring the changelog is generated correctly.

## Project JavaDoc (Markdown format)

[Project JavaDoc](docs/site/index.md)

## Wiki

- [Presentation](https://github.com/33mestre/java_base/wiki/presentation)
- [Project](https://github.com/33mestre/java_base/wiki/project)
- [Technical Definitions](https://github.com/33mestre/java_base/wiki/technical-definitions)
- [Project Structure](https://github.com/33mestre/java_base/wiki/project-structure)
- [Project Components](https://github.com/33mestre/java_base/wiki/project-components)
- [Testing](https://github.com/33mestre/java_base/wiki/testing)
- [Documentation and Swagger](https://github.com/33mestre/java_base/wiki/documentation-and-swagger)
- [Application Execution](https://github.com/33mestre/java_base/wiki/application-execution)
- [H2 Database](https://github.com/33mestre/java_base/wiki/h2-database)
- [CI/CD Pipeline](https://github.com/33mestre/java_base/wiki/ci-cd-pipeline)
- [Configuration Files](https://github.com/33mestre/java_base/wiki/configuration-files)
- [Logs and Best Practices](https://github.com/33mestre/java_base/wiki/logs-and-best-practices)
- [Differentials](https://github.com/33mestre/java_base/wiki/differentials)
- [Project Architecture Documentation](/ARCHITECTURE.md)
- [Attachments](https://github.com/33mestre/java_base/wiki/attachments)
- [Licensing](https://github.com/33mestre/java_base/wiki/licensing)
- [Useful Links](https://github.com/33mestre/java_base/wiki/useful-links)
- [Authors](/AUTHORS.md)
- [Code of Conduct](/CODE_OF_CONDUCT.md)
- [Project Change Log](/CHANGELOG.md)
- [Contributing to the Project](/CONTRIBUTING.md)
- [DIRECTORY](/DIRECTORY.md)
- [Planned/Under Study Improvements](/FUTURE.md)