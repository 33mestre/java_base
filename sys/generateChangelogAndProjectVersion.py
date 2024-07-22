# ---------------------------------
#     _____ __         __                    ______                          _
#    / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#    \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#   ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
#  /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API - Changelog generation and version update
# ---------------------------------
# This Python script performs the following tasks:
#
# - Obtains the list of Git commits in the format: hash, date, author, and message.
# - Defines and increments the project version according to the commits.
# - Updates the 'CHANGELOG.md' file with a detailed log of all changes.
# - Updates the '.env' file with the new project version.
# - Ignores commits containing the keywords "AUTO_COMMIT" and "AUTO_CHANGELOG".
#
# Versions are incremented based on valid commits, and the changelog is generated 
# in reverse chronological order.
# ---------------------------------

import subprocess
import os
from pathlib import Path

# Function to read properties from a .properties file without section headers
def read_properties(filepath):
    properties = {}
    with open(filepath, 'r', encoding='utf-8') as f:
        for line in f:
            line = line.strip()
            if not line or line.startswith('#') or line.startswith('!'):
                continue
            if '=' in line:
                key, value = line.split('=', 1)
                properties[key.strip()] = value.strip()
    return properties

# Define the project directory as two levels up from the current script directory
project_dir = Path(__file__).resolve().parent.parent

# Load properties from project.properties
properties_path = project_dir / 'project.properties'
properties = read_properties(properties_path)

# Extract user, repo, and i18n from properties
user = properties['github.repo.user']
repo = properties['github.repo.name']
i18n = properties['i18n']

# Load i18n properties
i18n_properties_path = project_dir / 'i18n' / f'{i18n}.properties'
i18n_properties = read_properties(i18n_properties_path)

# Execute Git command to get the list of commits
git_command = ['git', 'log', '--reverse', '--pretty=format:%h - %ad - %an - %s', '--date=format:%d/%m/%Y %H:%M']
process = subprocess.Popen(git_command, stdout=subprocess.PIPE, text=True, cwd=project_dir)
commits = process.stdout.readlines()

# Initial version
major, minor, patch = 0, 1, 0

# Function to increment version
def increment_version(major, minor, patch):
    if patch < 9:
        patch += 1
    else:
        patch = 0
        if minor < 9:
            minor += 1
        else:
            minor = 0
            major += 1
    return major, minor, patch

# Prepare the changelog
changelog_entries = []
last_version, last_date, last_author, last_commit_url = None, None, None, None

for commit in commits:
    parts = commit.strip().split(' - ')
    commit_hash, date, author, message = parts[0], parts[1] + "h", parts[2], parts[3]
    commit_url = f"https://github.com/{user}/{repo}/commit/{commit_hash}"
    last_commit_url = commit_url

    # Terms to ignore in commit messages
    ignore_terms = ["AUTO_COMMIT", "AUTO_CHANGELOG"]

    # Ignore specified terms in commit messages
    if any(term in message for term in ignore_terms):
        continue

    version = f"[{major}.{minor}.{patch}]({commit_url})"
    last_version = f"{major}.{minor}.{patch}"
    last_date, last_author = date, author

    changelog_entries.append(f"{version} - {date} - {author}\n- {message}\n\n---\n")
    major, minor, patch = increment_version(major, minor, patch)

# Generate the changelog content
changelog_header = f"# {i18n_properties['project.title']} - {i18n_properties['label.project.change.log']}\n\n**{i18n_properties['label.current.version']}:** [{last_version}]({last_commit_url}) ({last_author}) - {last_date}\n\n---\n"
changelog_content = changelog_header + "\n".join(reversed(changelog_entries))

# Write the changelog to the file
changelog_path = project_dir / 'CHANGELOG.md'
changelog_path.write_text(changelog_content, encoding='utf-8')

print("File 'CHANGELOG.md' generated successfully.")

# Update the .env file with the new version
env_path = project_dir / '.env'
env_content = f"PROJECT_VERSION={last_version}-SNAPSHOT\n"
env_path.write_text(env_content, encoding='utf-8')

print("File '.env' updated with the new project version.")





