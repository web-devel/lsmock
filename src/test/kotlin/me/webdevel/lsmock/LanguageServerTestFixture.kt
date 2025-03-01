package me.webdevel.lsmock

import org.eclipse.lsp4j.ClientCapabilities
import org.eclipse.lsp4j.CompletionCapabilities
import org.eclipse.lsp4j.CompletionParams
import org.eclipse.lsp4j.Diagnostic
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.MessageActionItem
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.PublishDiagnosticsParams
import org.eclipse.lsp4j.ShowMessageRequestParams
import org.eclipse.lsp4j.TextDocumentClientCapabilities
import org.eclipse.lsp4j.TextDocumentIdentifier
import org.eclipse.lsp4j.TextDocumentItem
import org.eclipse.lsp4j.TextDocumentPositionParams
import org.eclipse.lsp4j.WorkspaceFolder
import org.eclipse.lsp4j.services.LanguageClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture

abstract class LanguageServerTestFixture : LanguageClient {

    private val workspaceRoot: Path = createWorkspaceRoot()
    protected val server = MockLanguageServer()
    private var diagnostics = listOf<Diagnostic>()

    @BeforeEach
    fun setup() {
        val init = InitializeParams().apply {
            capabilities = ClientCapabilities().apply {
                textDocument = TextDocumentClientCapabilities().apply {
                    completion = CompletionCapabilities()
                }
            }
            workspaceFolders = listOf(WorkspaceFolder().apply {
                name = workspaceRoot.fileName.toString()
                uri = workspaceRoot.toUri().toString()
            })
        }

        server.connect(this)
        server.initialize(init).join()
    }

    @AfterEach
    fun teardown() {
        server.shutdown()
        server.exit()
    }

    protected fun textDocumentPosition(relativePath: String, line: Int, column: Int): TextDocumentPositionParams {
        val uri = workspaceRoot.resolve(relativePath).toUri().toString()
        return TextDocumentPositionParams(
            TextDocumentIdentifier(uri),
            Position(line - 1, column - 1)
        )
    }

    protected fun completionParams(relativePath: String, line: Int, column: Int): CompletionParams {
        val uri = workspaceRoot.resolve(relativePath).toUri().toString()
        return CompletionParams(
            TextDocumentIdentifier(uri),
            Position(line,column)
        )
    }

    protected fun openDocument(relativePath: String, content: String, languageId: String = "dummmy") {
        val uri = workspaceRoot.resolve(relativePath).toUri().toString()
        val document = TextDocumentItem(uri, languageId, 1, content)
        server.textDocumentService.didOpen(DidOpenTextDocumentParams(document))
    }

    private fun createWorkspaceRoot(): Path {
        return Path.of(System.getProperty("java.io.tmpdir"), "mock-ls-test-${System.currentTimeMillis()}")
    }

    // LanguageClient implementation
    override fun publishDiagnostics(diagnostics: PublishDiagnosticsParams) {
        this.diagnostics = diagnostics.diagnostics
    }

    override fun showMessage(messageParams: MessageParams) {
        println("[${messageParams.type}] ${messageParams.message}")
    }

    override fun showMessageRequest(requestParams: ShowMessageRequestParams): CompletableFuture<MessageActionItem> {
        return CompletableFuture.completedFuture(null)
    }

    override fun logMessage(message: MessageParams) {
        println("[LOG] ${message.message}")
    }

    override fun telemetryEvent(obj: Any) {
        println("[TELEMETRY] $obj")
    }
}