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
def baseUrl = "https://github.com/33mestre/shelson/tree/master/src/main/java/com/shelson"

// ---------------------------------
// Path to the output file
// ---------------------------------
def projectDir = Paths.get("").toAbsolutePath().normalize().toString()
def outputFilePath = Paths.get(projectDir, 'DIRECTORY.md').toString()

generateDirectoryMarkdown(rootDirPath, baseUrl, outputFilePath)
