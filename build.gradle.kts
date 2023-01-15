plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.7.0"
    id("com.google.cloud.tools.jib") version "2.8.0"
    id("io.micronaut.test-resources") version "3.7.0"
}

version = "0.1"
group = "com.learning"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.data:micronaut-data-document-processor")
    kapt("io.micronaut.micrometer:micronaut-micrometer-annotation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.data:micronaut-data-mongodb")
    implementation("io.micronaut.jmx:micronaut-jmx")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-datadog")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-jmx")
    implementation("io.micronaut.mongodb:micronaut-mongo-reactive")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("org.mongodb:mongodb-driver-reactivestreams")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("com.learning.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    jib {
        to {
            image = "gcr.io/myapp/jib-image"
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("com.learning.*")
    }
}



