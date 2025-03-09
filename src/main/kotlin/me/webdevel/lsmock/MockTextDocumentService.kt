package me.webdevel.lsmock

import org.eclipse.lsp4j.*
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
        val item1 = CompletionItem("fakeItem1").apply {
            detail = "This is a fake completion item for testing"
            tags = listOf(CompletionItemTag.Deprecated)
        }
        val item2 = CompletionItem("fakeItem2").apply { detail = "Another fake completion item" }
        completionList.items = listOf(item1, item2)

        return CompletableFuture.completedFuture(Either.forRight(completionList))
    }

    override fun resolveCompletionItem(unresolved: CompletionItem): CompletableFuture<CompletionItem> {
        val markupContent = MarkupContent().apply {
            kind = MarkupKind.MARKDOWN
            value = """
            ## Example Usage
            ```
            val example = "usage example"
            ```
        """.trimIndent()
        }
        unresolved.documentation = Either.forRight(markupContent)
        return CompletableFuture.completedFuture(unresolved)
    }

}