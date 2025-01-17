import org.jetbrains.kotlin.config.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.intellij") version "1.17.4"

}

group = "com.aymenDev"
version = "1.2.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.google.code.gson:gson:2.8.9") // Add Gson dependency
    implementation("org.apache.xmlgraphics:xmlgraphics-commons:2.6")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.20")


}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.6")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("android", "org.jetbrains.kotlin"))

}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("AYMOAI_CHAIN"))
        privateKey.set(System.getenv("AYMOAI_CERTIF"))
        password.set(System.getenv("AYMOAI_PASS"))
    }
    publishPlugin {
        token.set(System.getenv("AYMOAI_TOCKEN"))
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
    test {
        java.srcDir("src/test/kotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


