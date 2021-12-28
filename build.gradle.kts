plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "org.http4k"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    val http4kVersion = "4.17.6.0"
    val junitVersion = "5.8.2"

    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-cloudnative")
    implementation("org.http4k:http4k-server-jetty")

    testImplementation("org.http4k:http4k-testing-hamkrest")
    testImplementation("org.http4k:http4k-client-websocket")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("Only needed to run tests in a version of IntelliJ IDEA that bundles older versions")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("org.http4k.demo.IrcLauncherKt")
}

//task stage(dependsOn: ['installDist'])

tasks.wrapper {
    gradleVersion = "7.3.3"
    distributionType = Wrapper.DistributionType.ALL
}
