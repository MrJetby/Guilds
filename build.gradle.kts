
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java-library")
    id("maven-publish")
    alias(libMinix.plugins.kotlin.jvm)
    alias(libMinix.plugins.shadow)
    alias(libMinix.plugins.minecraft.runPaper)
    alias(libMinix.plugins.slimjar)
    id("org.jetbrains.dokka") version "1.7.20"
}

slimJar {
    fun relocates(vararg dependencies: String) {
        dependencies.forEach {
            val split = it.split(".")
            val name = split.last()
            relocate(it, "me.glaremasters.guilds.libs.$name")
        }
    }

    relocates(
        "org.bstats",
        "co.aikar.commands",
        "co.aikar.locales",
        "co.aikar.taskchain",
        "ch.jalu.configme",
        "com.zaxxer.hikari",
        "org.jdbi",
        "org.mariadb.jdbc",
        "dev.triumphteam.gui",
        "net.kyori"
    )
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        content { includeGroup("org.bukkit") }
    }
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.aikar.co/content/groups/aikar/") {
        content { includeGroup("co.aikar") }
    }
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")

    maven("https://repo.codemc.org/repository/maven-public/") {
        content { includeGroup("org.codemc.worldguardwrapper") }
    }
    maven("https://repo.glaremasters.me/repository/public/")

    maven("https://repo.racci.dev/releases") {
        name = "RacciRepo"
        mavenContent { releasesOnly() }
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")

    implementation(libMinix.slimjar)

    slim(libMinix.minecraft.bstats.bukkit)
    slim(libs.acf.paper)
    slim(libs.taskchain.bukkit)
    slim(libs.worldguardwrapper)
    slim(libs.configme)
    slim(libs.json.configuration)
    slim(libs.xseries)
    slim(libMinix.adventure.platform.bukkit)
    slim(libs.triumph.gui)
    slim(libs.hikaricp)
    slim(libs.jdbi.core)
    slim(libs.jdbi.sqlobject)
    slim(libs.mariadb.java.client)
    slim(libs.slf4j.api)
    slim(libMinix.bundles.kotlin)

    compileOnly(libs.vault)
    compileOnly(libs.authlib)
    compileOnly(libs.placeholderapi)
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Guilds")

            includes.from(project.files(), "Module.md")

            sourceLink {
                localDirectory.set(projectDir.resolve("src"))
                remoteUrl.set(URL("https://github.com/guilds-plugin/Guilds/tree/master/src"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }

//    indra {
//        mitLicense()
//
//        javaVersions {
//            target(8)
//        }
//
//        github("guilds-plugin", "guilds") {
//            publishing(true)
//        }
//
//        publishAllTo("guilds", "https://repo.glaremasters.me/repository/guilds/")
//    }

    compileKotlin {
        kotlinOptions.javaParameters = true
        kotlinOptions.jvmTarget = "1.8"
    }

    compileJava {
        options.compilerArgs = listOf("-parameters")
    }

    runServer {
        minecraftVersion("1.19.3")
    }

//    license {
//        header.set(resources.text.fromFile(rootProject.file("LICENSE")))
//        exclude("me/glaremasters/guilds/scanner/ZISScanner.java")
//        exclude("me/glaremasters/guilds/updater/UpdateChecker.java")
//        exclude("me/glaremasters/guilds/utils/PremiumFun.java")
//    }

    shadowJar {
        dependencies {
            project.configurations.implementation.get().dependencies.forEach {
                include(dependency(it))
            }
            relocate("io.github.slimjar", "me.glaremasters.guilds.libs.slimjar")
        }
    }

//    shadowJar {
//        fun relocates(vararg dependencies: String) {
//            dependencies.forEach {
//                val split = it.split(".")
//                val name = split.last()
//                relocate(it, "me.glaremasters.guilds.libs.$name")
//            }
//        }
//
//        relocates(
//            "io.github.slimjar"
//        )
//
//        minimize()
//
//        archiveClassifier.set(null as String?)
//        archiveFileName.set("Guilds-${project.version}.jar")
//        destinationDirectory.set(rootProject.tasks.shadowJar.get().destinationDirectory.get())
//    }

    processResources {
        expand("version" to rootProject.version)
    }
}
