package me.webdevel.lsmock

import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.DidChangeWatchedFilesParams
import org.eclipse.lsp4j.services.WorkspaceService

class MockWorkspaceService : WorkspaceService {

    override fun didChangeConfiguration(params: DidChangeConfigurationParams?) {
        println("didChangeConfiguration called with: $params")
    }

    override fun didChangeWatchedFiles(params: DidChangeWatchedFilesParams?) {
        println("didChangeWatchedFiles called with: $params")
    }

}
