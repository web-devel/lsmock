package me.webdevel.lsmock

import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.InitializeResult
import org.eclipse.lsp4j.ServerCapabilities
import org.eclipse.lsp4j.services.*
import java.util.concurrent.CompletableFuture

class MockLanguageServer : LanguageServer, LanguageClientAware {

    private val textDocumentService = MockTextDocumentService()
    private val workspaceService = MockWorkspaceService()


    init {
        println("Mock Language Server initialized")
    }

    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        println("Method 'initialize' called with params: $params")
        return CompletableFuture.completedFuture(InitializeResult(ServerCapabilities()))
    }

    override fun shutdown(): CompletableFuture<Any> {
        println("Method 'shutdown' called")
        return CompletableFuture.completedFuture(null)
    }

    override fun exit() {
        println("Method 'exit' called")
    }

    override fun getTextDocumentService(): TextDocumentService {
        println("Method 'getTextDocumentService' called")
        return textDocumentService
    }

    override fun getWorkspaceService(): WorkspaceService {
        println("Method 'getWorkspaceService' called")
        return workspaceService
    }

    override fun connect(client: LanguageClient?) {
        println("Method 'connect' called with client: $client")
    }
}


