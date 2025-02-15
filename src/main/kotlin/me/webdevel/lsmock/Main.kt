package me.webdevel.lsmock

import org.eclipse.lsp4j.launch.LSPLauncher
import java.io.InputStream
import java.io.OutputStream

fun main() {

    val inputStream: InputStream = System.`in`
    val outputStream: OutputStream = System.out
    val server = MockLanguageServer()


    val launcher = LSPLauncher.createServerLauncher(
        server,
        inputStream,
        outputStream
    )

    launcher.startListening()

    println("LSP Server started. Listening for connections...")
}