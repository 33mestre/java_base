/*
---------------------------------
    _____ __         __                    ______                          _
   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
 /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
---------------------------------
Currency Conversion API - Changelog generation and version update
---------------------------------
This Groovy script performs the following tasks:

- Obtains the list of Git commits in the format: hash, date, author, and message.
- Defines and increments the project version according to the commits.
- Updates the 'CHANGELOG.md' file with a detailed log of all changes.
- Updates the '.env' file with the new project version.
- Ignores commits containing the keyword "AUTO_COMMIT".

Versions are incremented based on valid commits, and the changelog is generated in reverse chronological order.
---------------------------------
*/

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.Properties

// ---------------------------------
// Define the project directory
// ---------------------------------
def projectDir = Paths.get("").toAbsolutePath().normalize().toString()

// ---------------------------------
// Read properties from project.properties
// ---------------------------------
def projectPropertiesPath = Paths.get(projectDir, '.', 'project.properties')
def projectProperties = new Properties()

try {
    projectProperties.load(Files.newInputStream(projectPropertiesPath))
} catch (IOException e) {
    println("Error: Unable to load 'project.properties'. Please ensure the file exists at ${projectPropertiesPath}.")
    return
}

// ---------------------------------
// user, repo and i18n from properties
// ---------------------------------
def user = projectProperties['GITHUB_REPO_USER']
def repo = projectProperties['GITHUB_REPO_NAME']
def i18n = projectProperties['i18n']

def i18nPropertiesPath = Paths.get(projectDir, 'i18n', "${i18n}.properties")
def i18nProperties = new Properties()

try {
    i18nProperties.load(Files.newInputStream(i18nPropertiesPath))
} catch (IOException e) {
    println("Error: Unable to load 'project.properties'. Please ensure the file exists at ${propertiesPath}.")
    return
}

def projectTitle = i18nProperties['PROJECT_TITLE']
def projectDescription = i18nProperties['PROJECT_DESCRIPTION']
def projectChangeLogDescription = i18nProperties['label.project.change.log']
def currenceVersionDescription = i18nProperties['label.current.version']

// ---------------------------------
// Executes the Git command to get the list of commits with hash, date, author, and message
// ---------------------------------
def process = new ProcessBuilder('git', 'log', '--reverse', '--pretty=format:%h - %ad - %an - %s', '--date=format:%d/%m/%Y %H:%M')
        .directory(new File(projectDir))
        .redirectErrorStream(true)
        .start()

def commits = process.inputStream.text.readLines()
// println("Total commits: " + commits.size())
// commits.each { println it } // Print all commits for debugging

// ---------------------------------
// Define the initial version format
// ---------------------------------
def major = 0
def minor = 1
def patch = 0

// ---------------------------------
// Function to increment the version
// ---------------------------------
def incrementVersion(major, minor, patch) {
    if (patch < 9) {
        patch += 1
    } else {
        patch = 0
        if (minor < 9) {
            minor += 1
        } else {
            minor = 0
            major += 1
        }
    }
    [major, minor, patch]
}

// ---------------------------------
// Initialize variables to store the last version, date, and author
// ---------------------------------
def lastVersion = null
def lastDate = null
def lastAuthor = null
def lastCommitUrl = null

// ---------------------------------
// Initialize the list to store the commits
// ---------------------------------
def commitList = []

// ---------------------------------
// Initialize the changelog content
// ---------------------------------
commits.each { commit ->
    def parts = commit.split(' - ')
    def commitHash = parts[0]
    def date = parts[1] + "h"  // Adds "h" after the hour
    def author = parts[2]
    def message = parts[3]

    def commitUrl = "https://github.com/${user}/${repo}/commit/${commitHash}"
    lastCommitUrl = "https://github.com/${user}/${repo}/commit/${commitHash}"

    // ---------------------------------
    // Array of keywords to ignore
    // ---------------------------------
    def ignoreKeywords = ["AUTO_COMMIT", "AUTO_CHANGELOG"]

    // ---------------------------------
    // Ignore commits with specified keywords
    // ---------------------------------
    if (ignoreKeywords.any { keyword -> message.contains(keyword) }) {
        return // Continue to next commit
    }

    // ---------------------------------
    // Increment the version for each valid commit
    // ---------------------------------
    def version = "[${major}.${minor}.${patch}](${commitUrl})"
    lastVersion = "${major}.${minor}.${patch}"
    lastDate = date
    lastAuthor = author

    def commitEntry = [
        version: version,
        date: date,
        commitHash: commitHash,
        author: author,
        message: message,
        commitUrl: commitUrl
    ]
    commitList.add(commitEntry)

    (major, minor, patch) = incrementVersion(major, minor, patch)
}

// ---------------------------------
// Define the changelog header
// ---------------------------------
def header = new StringBuilder()
header.append("""
# ${projectTitle} - ${projectChangeLogDescription}

**${currenceVersionDescription}:** [${lastVersion}](${lastCommitUrl}) (${lastAuthor}) - ${lastDate}

---
""")

// ---------------------------------
// Reverse the order of the commits to display from the most recent to the oldest
// ---------------------------------
commitList.reverse().each { entry ->
    header.append("\n${entry.version} - ${entry.date} - ${entry.commitHash} - ${entry.author}\n")
    header.append("- ${entry.message}\n")
    header.append("\n---\n")
}

// ---------------------------------
// Create the final changelog content
// ---------------------------------
def changelogContentFinal = new StringBuilder(header)

// ---------------------------------
// Write the changelog to the file
// ---------------------------------
def filePath = Paths.get(projectDir, 'CHANGELOG.md')
Files.write(filePath, changelogContentFinal.toString().bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

println("File 'CHANGELOG.md' generated successfully.")

// ---------------------------------
// Update the .env file with the new version
// ---------------------------------
def envFilePath = Paths.get(projectDir, '.env')
def newVersion = "PROJECT_VERSION=${lastVersion}-SNAPSHOT\n"

// ---------------------------------
// Clear the existing content of the .env file, if any
// ---------------------------------
Files.write(envFilePath, newVersion.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

println(".env file updated with the new project version.")
