package me.webdevel.lsmock

import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.CompletionList
import org.eclipse.lsp4j.CompletionParams
import org.eclipse.lsp4j.DidChangeTextDocumentParams
import org.eclipse.lsp4j.DidCloseTextDocumentParams
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.DidSaveTextDocumentParams
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.TextDocumentService
import java.util.concurrent.CompletableFuture

class MockTextDocumentService : TextDocumentService {

    override fun didOpen(params: DidOpenTextDocumentParams) {
    }

    override fun didChange(params: DidChangeTextDocumentParams) {
    }

    override fun didClose(params: DidCloseTextDocumentParams) {
    }

    override fun didSave(params: DidSaveTextDocumentParams) {
    }

    override fun completion(position: CompletionParams): CompletableFuture<Either<List<CompletionItem>, CompletionList>> {

        val completionList = CompletionList()
        val item1 = CompletionItem("fakeItem1").apply { detail = "This is a fake completion item for testing" }
        val item2 = CompletionItem("fakeItem2").apply { detail = "Another fake completion item" }
        completionList.items = listOf(item1, item2)

        return CompletableFuture.completedFuture(Either.forRight(completionList))
    }

}