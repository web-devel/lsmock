package org.example.ij_lsp_plugin

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor


class MockLspServerDescriptor (project: Project) : ProjectWideLspServerDescriptor(project, "Mock"){

    override fun isSupportedFile(file: VirtualFile): Boolean = true

    override fun createCommandLine(): GeneralCommandLine {
        val isWindows = System.getProperty("os.name")
            .contains("windows", ignoreCase = true)

        val gradleScript = if (isWindows) "gradlew.bat" else "gradlew"

        return GeneralCommandLine(gradleScript, "run").apply {
            withWorkDirectory("../../")
        }
    }

}