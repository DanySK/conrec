plugins {
    id("com.gradle.enterprise") version "3.17.6"
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.8"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}

gitHooks {
    commitMsg { conventionalCommits() }
    createHooks()
}

rootProject.name = "conrec"
