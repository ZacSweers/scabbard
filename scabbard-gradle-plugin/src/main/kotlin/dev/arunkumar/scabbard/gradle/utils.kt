package dev.arunkumar.scabbard.gradle

import org.gradle.api.Project

inline fun projectBlock(project: Project, block: Project.() -> Unit) {
  block(project)
}