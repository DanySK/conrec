plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.2"
    `java-library`
    signing
    `maven-publish`
    id("org.danilopianini.publish-on-central") version "0.2.3"
}

gitSemVer {
    version = computeGitSemVer() // THIS IS MANDATORY, AND MUST BE LAST IN BLOCK
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
}

tasks.withType<JavaCompile>().configureEach {
    if (!name.contains("Test")) {
        println("Sconfiguring $name")
        options.apply {
            isFork = true
            val java6Home: String by project
            forkOptions.javaHome = file(java6Home)
        }
    }
}

if (System.getenv("CI") == true.toString()) {
    signing {
        val signingKey: String? by project
        val signingPassword: String? by project
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}

group = "org.danilopianini" // This must be configured for the generated pom.xml to work correctly
/*
 * The plugin comes with defaults that are useful to myself. You should configure it to behave as you please:
 */
publishOnCentral {
    projectDescription.set("An algorithm for contouring surfaces") // Defaults to "No description provided"
    projectLongName.set("Conrec") // Defaults to the project name
    licenseName.set("MIT") // Defaults to "Apache License, Version 2.0"
    licenseUrl.set("https://opensource.org/licenses/MIT") // Defaults to http://www.apache.org/licenses/LICENSE-2.0
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                developers {
                    developer {
                        name.set("Danilo Pianini")
                        email.set("danilo.pianini@gmail.com")
                        url.set("http://www.danilopianini.org/")
                    }
                }
            }
        }
    }
}

