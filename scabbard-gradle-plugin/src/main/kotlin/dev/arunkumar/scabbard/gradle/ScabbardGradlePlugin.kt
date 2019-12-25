package dev.arunkumar.scabbard.gradle

import dev.arunkumar.scabbard.gradle.tasks.PostBuildCopyFiles
import org.gradle.api.Plugin
import org.gradle.api.Project

class ScabbardGradlePlugin : Plugin<Project> {

  override fun apply(project: Project) = projectBlock(project) {
    extensions.create(
      SCABBARD,
      ScabbardPluginExtension::class.java,
      project
    )
    registerTasks(project)
  }

  private fun registerTasks(project: Project) {
    PostBuildCopyFiles.register(project)
  }

  companion object {
    const val SCABBARD = "scabbard"
    const val PLUGIN_ID = "scabbard-gradle-plugin"
  }
}