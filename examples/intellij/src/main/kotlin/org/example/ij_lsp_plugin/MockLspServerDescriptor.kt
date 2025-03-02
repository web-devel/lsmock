package org.example.ij_lsp_plugin

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
import kotlin.io.path.Path


class MockLspServerDescriptor (project: Project) : ProjectWideLspServerDescriptor(project, "Mock"){

    override fun isSupportedFile(file: VirtualFile): Boolean = true

    override fun createCommandLine(): GeneralCommandLine = GeneralCommandLine( // todo attach to a running debuggable server
        "java",
        "-jar",
        Path("..", "..", "build", "libs", "lsmock-1.0-SNAPSHOT.jar").toString()
    )

}