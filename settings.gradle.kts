import org.danilopianini.VersionAliases.justAdditionalAliases

plugins {
    id("de.fayard.refreshVersions") version "0.51.0"
}

rootProject.name = "conrec"

refreshVersions {
    featureFlags {
        enable(de.fayard.refreshVersions.core.FeatureFlag.LIBS)
    }
    extraArtifactVersionKeyRules = justAdditionalAliases
}

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("org.danilopianini:refreshversions-aliases:+")
    }
}
