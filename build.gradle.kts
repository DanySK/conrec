import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning")
    `java-library`
    signing
    `maven-publish`
    id("org.danilopianini.publish-on-central")
}

gitSemVer {
    version = computeGitSemVer() // THIS IS MANDATORY, AND MUST BE LAST IN BLOCK
}

if (System.getenv("CI") == true.toString()) {
    if (!Os.isFamily(Os.FAMILY_MAC)) {
        /*
         * Java 6 cannot be installed on CI on MacOS devices
         */
        java {
            sourceCompatibility = JavaVersion.VERSION_1_6
        }
        tasks.withType<JavaCompile>().configureEach {
            options.apply {
                isFork = true
                val java6Home: String by project
                forkOptions.javaHome = file(java6Home)
            }
        }
    }
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
    projectDescription.set("An algorithm for contouring surfaces")
    projectLongName.set("Conrec")
    licenseName.set("MIT")
    licenseUrl.set("https://opensource.org/licenses/MIT")
}

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
