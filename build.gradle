group 'org.http4k'
version '1.0-SNAPSHOT'

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

apply plugin: 'java'
apply plugin: 'kotlin'
apply plugin: 'application'

sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation platform("org.http4k:http4k-bom:$http4k_version")
    implementation "org.http4k:http4k-core"
    implementation "org.http4k:http4k-cloudnative"
    implementation "org.http4k:http4k-server-jetty"

    testImplementation "org.http4k:http4k-testing-hamkrest"
    testImplementation "org.http4k:http4k-client-websocket"

    testImplementation platform("org.junit:junit-bom:$junit_version")
    testImplementation "org.junit.jupiter:junit-jupiter-api"
    testImplementation "org.junit.jupiter:junit-jupiter-engine"
}

test {
    useJUnitPlatform()
}

mainClassName = 'org.http4k.demo.IrcLauncherKt'

task stage(dependsOn: ['installDist'])
