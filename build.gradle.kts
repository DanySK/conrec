plugins {
    `java-library`
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.2"
}

gitSemVer {
    version = computeGitSemVer() // THIS IS MANDATORY, AND MUST BE LAST IN BLOCK
}
