plugins {
    kotlin("jvm") version "2.1.10"
}

group = "me.webdevel.lsmock"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j:0.24.0")
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.jsonrpc:0.24.0")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.15.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.register<JavaExec>("runServerWithDebug") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("me.webdevel.lsmock.MainKt")
    standardInput = System.`in`
    standardOutput = System.out

    debug = true
    debugOptions {
        enabled = true
        server = true
        suspend = false // enable to debug initialization
        host = "localhost"
        port = 5006
    }
}

tasks.register<JavaExec>("runTcpServer") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("me.webdevel.lsmock.MainKt")

    args("--tcpServerPort", "5051")
}