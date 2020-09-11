package com.acmerobotics.roadrunner.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

/**
 * Factory for constructing the path designer panel.
 */
class PluginToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val content = toolWindow.contentManager.factory.createContent(PluginPanel(project), "", true)
        toolWindow.contentManager.addContent(content)
    }
}
