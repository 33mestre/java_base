// ---------------------------------
//    _____ __         __                    ______                          _
//   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
//   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
//  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
// /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
// ---------------------------------
// Currency Conversion API - Unix / Linux Version
// Groovy script
// That generates the DIRECTORY.md file
// ---------------------------------

import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

// ---------------------------------
// Function to list files and directories and generate the markdown content
// ---------------------------------
def listFiles(File dir, int level, rootDir, baseUrl, writer, packageInfos) {
    dir.eachFile { file ->
        if (file.isDirectory()) {
            writer.append("${"  " * level}* ${file.name}\n")
            listFiles(file, level + 1, rootDir, baseUrl, writer, packageInfos)
        } else if (file.name.endsWith('.java') && !file.name.equals('package-info.java')) {
            def relativePath = rootDir.toURI().relativize(file.toURI()).getPath()
            def link = "${baseUrl}/${relativePath.replace("\\", "/")}"
            writer.append("${"  " * (level + 1)}* [${file.name.replace('.java', '')}](${link})\n")
        } else if (file.name.equals('package-info.java')) {
            packageInfos << file
        }
    }
}

// ---------------------------------
// Function to read the javadoc from package-info.java files
// ---------------------------------
def readPackageInfo(File file) {
    def packageDoc = new StringBuilder()
    def inComment = false
    file.eachLine { line ->
        line = line.trim()
        if (line.startsWith("/**")) {
            inComment = true
        } else if (line.endsWith("*/")) {
            inComment = false
        } else if (inComment) {
            packageDoc.append(line.replaceFirst("\\*", "").trim() + " ")
        }
    }
    return packageDoc.toString().trim()
}

// ---------------------------------
// Function to get the package name from the package-info.java file
// ---------------------------------
def getPackageName(File file) {
    def packageName = ""
    file.eachLine { line ->
        if (line.startsWith("package")) {
            packageName = line.replace("package", "").replace(";", "").trim()
        }
    }
    return packageName
}

// ---------------------------------
// Function to generate the package link on GitHub
// ---------------------------------
def getPackageLink(baseUrl, packageName) {
    def packagePath = packageName.replace(".", "/")
    return "${baseUrl}/${packagePath}"
}

// ---------------------------------
// Function to generate the DIRECTORY.md file
// ---------------------------------
def generateDirectoryMarkdown(rootDirPath, baseUrl, outputFilePath) {
    def writer = new StringWriter()
    writer.append("## src\n")
    writer.append("  * main\n")
    writer.append("    * java\n")
    
    def rootDir = new File(rootDirPath)
    def packageInfos = []
    listFiles(rootDir, 4, rootDir, baseUrl, writer, packageInfos)
    
    writer.append("\n\n---\n\n")
    writer.append("## Package Documentation\n\n")
    
    packageInfos.each { file ->
        def packageDoc = readPackageInfo(file)
        def packageName = getPackageName(file)
        def packageLink = getPackageLink(baseUrl, packageName)
        writer.append("- [${packageName}](${packageLink})\n")
        writer.append("${packageDoc}\n\n")
    }

    writer.append("\n")
        .append("## Wiki Menu\n")
        .append("\n")
        .append("[Home Page](/README.md)\n")
        .append("\n")
        .append("### 1. Introduction to the Project\n")
        .append("- [Overview](https://github.com/33mestre/java_base/wiki/overview): Presentation of the project, highlighting its purpose and the context in which it is embedded.\n")
        .append("- [Project Objectives](https://github.com/33mestre/java_base/wiki/project-objectives): Enumeration of the main objectives that the project aims to achieve.\n")
        .append("- [Scope and Functionalities](https://github.com/33mestre/java_base/wiki/scope-and-functionalities): Description of the main functionalities offered by the project and its scope of operation.\n")
        .append("\n")
        .append("### 2. Configuration and Installation\n")
        .append("- [Initial Configurations](https://github.com/33mestre/java_base/wiki/initial-configurations): Steps required to set up the development or production environment.\n")
        .append("- [Installation Instructions](https://github.com/33mestre/java_base/wiki/installation-instructions): Detailed procedures for installing the project in different environments.\n")
        .append("- [Docker Configuration](https://github.com/33mestre/java_base/wiki/docker-configuration): Specifications on how to configure and use Docker for the project.\n")
        .append("\n")
        .append("### 3. Project Structure\n")
        .append("- **Folder Structure**\n")
        .append("- [Project Architecture](/ARCHITECTURE.md): Explanation of the architecture used, including design patterns and technical decisions.\n")
        .append("\n")
        .append("### 4. Development\n")
        .append("- [Apache Camel Integration](https://github.com/33mestre/java_base/wiki/apache-camel-integration): Guide on integrating Apache Camel into the project, including configuration and usage.\n")
        .append("- [Development Flow](https://github.com/33mestre/java_base/wiki/development-flow): Description of the development process adopted, including planning, coding, and review stages.\n")
        .append("- [Contributors and Authors](/AUTHORS.md): Recognition of the contributors to the project.\n")
        .append("- [Contributions](/CONTRIBUTING.md): Guidelines on how to contribute to the project, including code standards and pull request requirements, tips and best practices.\n")
        .append("- [Code of Conduct](/CODE_OF_CONDUCT.md): Behavioral guidelines expected for the project community.\n")
        .append("\n")
        .append("### 5. API and Documentation\n")
        .append("- [OpenAPI Specification](https://github.com/33mestre/java_base/wiki/openapi-specification): Details about the OpenAPI specification used to document the API endpoints.\n")
        .append("- [API Documentation with Swagger](https://github.com/33mestre/java_base/wiki/api-documentation-with-swagger): Information on how to access and use the interactive API documentation generated by Swagger.\n")
        .append("- [Javadoc Documentation](https://github.com/33mestre/java_base/wiki/javadoc-documentation): Information on the Javadoc documentation generated for the project.\n")
        .append("\n")
        .append("### 6. Endpoints and Database\n")
        .append("- [Endpoint Description](https://github.com/33mestre/java_base/wiki/endpoint-description): Details of the available API endpoints, including methods, parameters, and usage examples.\n")
        .append("- [Database Management](https://github.com/33mestre/java_base/wiki/database-management): Strategies and practices for efficient management of the database used by the project.\n")
        .append("\n")
        .append("### 7. Testing\n")
        .append("- [Testing Strategies](https://github.com/33mestre/java_base/wiki/testing-strategies): Approach and methods used to test the software, including unit, integration, and E2E tests.\n")
        .append("- [Testing Tools](https://github.com/33mestre/java_base/wiki/testing-tools): Description of the testing tools used in the project and how to configure them.\n")
        .append("\n")
        .append("### 8. CI/CD and Automations\n")
        .append("- [CI/CD Pipeline](https://github.com/33mestre/java_base/wiki/ci-cd-pipeline): Explanation of the continuous integration and delivery pipeline, detailing each stage and its function.\n")
        .append("- [Automations and Artifact Generation](https://github.com/33mestre/java_base/wiki/automations-and-artifact-generation): Description of the automations incorporated into the CI/CD, including documentation generation and build artifacts.\n")
        .append("\n")
        .append("### 9. Configuration Files\n")
        .append("- [.gitignore, .editorconfig and project.properties](https://github.com/33mestre/java_base/wiki/gitignore-and-editorconfig-and-project-properties): Utility of these files to maintain code consistency and quality.\n")
        .append("- [Maven Wrapper and application.properties](https://github.com/33mestre/java_base/wiki/maven-wrapper-and-application-properties): Explanation of how these files help standardize the development environment.\n")
        .append("- [.env File and Travis CI Settings](https://github.com/33mestre/java_base/wiki/env-file-and-travis-ci-settings): Use of these files to configure the environment and CI integrations.\n")
        .append("\n")
        .append("### 10. Best Practices\n")
        .append("- [Code Standards and Security](https://github.com/33mestre/java_base/wiki/code-standards-and-security): Guidelines for maintaining code quality and security.\n")
        .append("- [Monitoring and Logging Practices](https://github.com/33mestre/java_base/wiki/monitoring-and-logging-practices): Recommended techniques for monitoring and logging in the project.\n")
        .append("\n")
        .append("### 11. Legal and Licensing\n")
        .append("- [Licensing](https://github.com/33mestre/java_base/wiki/licensing): Information about the rights and restrictions associated with the use of the software.\n")
        .append("- [Terms of Use](https://github.com/33mestre/java_base/wiki/terms-of-use): Information about the terms and conditions for using the software.\n")
        .append("\n")
        .append("### 12. Projections and Innovations\n")
        .append("- **Future Plans**.\n")
        .append("- [Improvement Proposals](https://github.com/33mestre/java_base/wiki/improvement-proposals): Space for the community to suggest and debate improvements and innovations.\n")
        .append("\n")
        .append("### 13. Attachments and Useful Links\n")
        .append("- [External Links and References](https://github.com/33mestre/java_base/wiki/external-links-and-references): Additional resources and external documentation relevant to the project.\n")
        .append("\n")
        .append("### 14. Security\n")
        .append("- [Security Policy](/SECURITY.md): Details on the supported versions, reporting vulnerabilities, and general security practices.\n")

    def filePath = Paths.get(outputFilePath)
    Files.write(filePath, writer.toString().bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    println("File 'DIRECTORY.md' generated successfully.")
}

// ---------------------------------
// Root directory of the source code
// ---------------------------------
def rootDirPath = "src/main/java/com/shelson"

// ---------------------------------
// Base URL of the GitHub repository
// ---------------------------------
def baseUrl = "https://github.com/33mestre/java_base/tree/master/src/main/java/com/shelson"

// ---------------------------------
// Path to the output file
// ---------------------------------
def projectDir = Paths.get("").toAbsolutePath().normalize().toString()
def outputFilePath = Paths.get(projectDir, 'DIRECTORY.md').toString()

generateDirectoryMarkdown(rootDirPath, baseUrl, outputFilePath)