plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("kr.entree.spigradle") version "2.2.3"
}

java {
    sourceCompatibility = JavaVersion.toVersion(8)
    targetCompatibility = JavaVersion.toVersion(8)
}

group = "org.golde.bukkit.imagefireworksreborn"
version = "1.4.1"
description = "This is a port of the plugin ImageFireworks by deantonious (https://www.spigotmc.org/resources/imagefireworks.18932/) that I have ported to Spigot 1.11.2 and below, and have added new features. Thanks to deantonious for letting me take over and manage his project ❤"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.5-R0.1-SNAPSHOT")
    //implementation("cloud.commandframework", "cloud-paper", "1.4.0")
    //implementation("net.kyori", "adventure-text-minimessage", "4.1.0-SNAPSHOT")
}

spigot {
    apiVersion = "1.16"
    website = "http://eric.golde.org/"
    authors("Eric Golde", "Peter Golde", "deantonious")
    commands {
        create("imagefireworks") {
            description = "Image Fireworks command"
            aliases("imgfws", "if", "imagef", "imgf", "imgfw", "imagefw")
            usage = "/<command>"
        }
        permissions {
            create("imagefireworks.use") {
                defaults = "op"
                description = "Permission for /imagefireworks"
            }
        }
    }

}

tasks {
    withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        minimize()
        listOf(
            "cloud.commandframework",
            "io.leangen",
            "net.kyori.adventure.text.minimessage"
        )
        archiveClassifier.set("")
    }
}
