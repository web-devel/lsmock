package me.webdevel.lsmock

import me.webdevel.lsmock.args.ServerArgsParser
import org.eclipse.lsp4j.launch.LSPLauncher
import java.net.ServerSocket
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    val config = ServerArgsParser.parse(args)
    if (config.tcpServerPort != null) {
        startTcpServer(config.tcpServerPort)
    } else {
        startStdioServer()
    }
}

private fun startStdioServer() {
    val server = MockLanguageServer()

    val launcher = LSPLauncher.createServerLauncher(
        server,
        System.`in`,
        System.out
    )

    launcher.startListening()
}

private fun startTcpServer(port: Int) {
    val (inputStream, outputStream ) = ServerSocket(port).accept() // blocks
        .let { Pair(it.inputStream, it.outputStream) }

    val server = MockLanguageServer()
    val threads = Executors.newCachedThreadPool()
    val launcher = LSPLauncher.createServerLauncher(server, inputStream, outputStream, threads) { it }

    launcher.startListening()
}