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


abstract class RunServerTask : JavaExec() {
    // VS Code language-client unconditionally sends --stdio flag
    @Option(option = "stdio", description = "Enable stdio communication for the server")
    @Input
    var useStdio: Boolean = false
}

tasks.register<RunServerTask>("runServerWithDebug") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("me.webdevel.lsmock.MainKt")
    standardInput = System.`in`
    standardOutput = System.out

    // not using `debug` since it pollutes stdout
    // set `suspend=y` to debug the language server initialization
    jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006,quiet=y")
}

tasks.register<JavaExec>("runTcpServer") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("me.webdevel.lsmock.MainKt")

    args("--tcpServerPort", "5051")
}