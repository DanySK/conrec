plugins {
    `java-library`
    signing
    alias(libs.plugins.gitSemVer)
    alias(libs.plugins.multiJvmTesting)
    alias(libs.plugins.publishOnCentral)
    alias(libs.plugins.taskTree)
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
    repoOwner = "DanySK"
    projectDescription.set("An algorithm for contouring surfaces")
    projectLongName.set("Conrec")
    licenseName.set("MIT")
    licenseUrl.set("https://opensource.org/licenses/MIT")
}

tasks.withType<Test>().configureEach { failOnNoDiscoveredTests = false }

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                developers {
                    developer {
                        name.set("Nicholas Yue")
                    }
                    developer {
                        name.set("Lorenzo Paganelli")
                        email.set("lorenzo.paganelli3@studio.unibo.it")
                    }
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
