import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin

plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = sourceCompatibility
}

allprojects {
    group = "me.nahu.ptc"
    version = "0.1.0"
}

subprojects {
    apply {
        plugin<JavaPlugin>()
        plugin<ShadowPlugin>()
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }

        maven {
            name = "spigotmc"
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }

        maven {
            name = "papermc"
            url = uri("https://papermc.io/repo/repository/maven-public/")
        }

        maven {
            name = "aikar-repo"
            url = uri("https://repo.aikar.co/content/groups/aikar/")
        }

        jcenter()
        mavenCentral()
    }

    dependencies {
        compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
        compileOnly("org.jetbrains:annotations:20.1.0")
        implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
    }
}