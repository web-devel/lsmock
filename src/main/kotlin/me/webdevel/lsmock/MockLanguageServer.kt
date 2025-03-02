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
    }

    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        return CompletableFuture.completedFuture(InitializeResult(ServerCapabilities()))
    }

    override fun shutdown(): CompletableFuture<Any> {
        return CompletableFuture.completedFuture(null)
    }

    override fun exit() {
    }

    override fun getTextDocumentService(): TextDocumentService {
        return textDocumentService
    }

    override fun getWorkspaceService(): WorkspaceService {
        return workspaceService
    }

    override fun connect(client: LanguageClient?) {
    }
}


