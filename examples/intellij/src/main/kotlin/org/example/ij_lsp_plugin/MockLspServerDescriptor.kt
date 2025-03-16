package org.example.ij_lsp_plugin

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor


class MockLspServerDescriptor (project: Project) : ProjectWideLspServerDescriptor(project, "Mock Language Server"){

    override fun isSupportedFile(file: VirtualFile): Boolean = true

    override fun createCommandLine(): GeneralCommandLine {
        val isWindows = System.getProperty("os.name")
            .contains("windows", ignoreCase = true)

        val command = if (isWindows) {
            listOf("cmd", "/c", "gradlew.bat")
        } else {
            listOf("./gradlew")
        }

        val workingDir = System.getProperty("mock.lsp.working.dir") ?: ""

        return GeneralCommandLine(command).apply {
            withParameters("runServerWithDebug", "-q", "--console=plain")
            withWorkDirectory(workingDir)
        }
    }

}