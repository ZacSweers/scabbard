package dev.arunkumar.scabbard.gradle.tasks

import dev.arunkumar.scabbard.gradle.projectBlock
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction

open class PostBuildCopyFiles : DefaultTask() {
  companion object {
    //TODO(arun) Find a way to share this with processor
    fun register(project: Project) = projectBlock(project) {
      val copyTask = tasks.register("copyScabbardFiles", PostBuildCopyFiles::class.java)
      tasks.named("assemble").configure {
        finalizedBy(copyTask)
      }
    }
  }


  init {
    setTaskInputOutputs()
  }

  @InputFiles
  lateinit var generatedGraphFiles: FileTree
  @OutputFiles
  lateinit var outputGraphFiles: FileTree

  // Make this task cacheable
  private fun setTaskInputOutputs() {
    generatedGraphFiles = project.fileTree("${project.buildDir}") {
      include("**/scabbardGraph/**/*.dot")
      include("**/scabbardGraph/**/*.png")
    }
    outputGraphFiles = project.fileTree("${project.buildDir}/scabbard/") {
      include("*.dot")
      include("*.png")
    }
  }

  @TaskAction
  fun doCopying() = projectBlock(project) {
    copy {
      from("$buildDir")
      include("**/scabbardGraph/*/*.dot")
      include("**/scabbardGraph/**/*.png")
      into("$buildDir/scabbard/")
    }
  }
}