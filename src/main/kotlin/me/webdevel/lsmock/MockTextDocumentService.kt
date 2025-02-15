package me.webdevel.lsmock

import org.eclipse.lsp4j.DidChangeTextDocumentParams
import org.eclipse.lsp4j.DidCloseTextDocumentParams
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.DidSaveTextDocumentParams
import org.eclipse.lsp4j.services.TextDocumentService

class MockTextDocumentService : TextDocumentService {

    override fun didOpen(params: DidOpenTextDocumentParams) {
        println("didOpen called for: ${params.textDocument.uri}")
    }

    override fun didChange(params: DidChangeTextDocumentParams) {
        println("didChange called for: ${params.textDocument.uri}")
    }

    override fun didClose(params: DidCloseTextDocumentParams) {
        println("didClose called for: ${params.textDocument.uri}")
    }

    override fun didSave(params: DidSaveTextDocumentParams) {
        println("didSave called for: ${params.textDocument.uri}")
    }
}