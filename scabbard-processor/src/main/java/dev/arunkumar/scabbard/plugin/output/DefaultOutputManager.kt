package dev.arunkumar.scabbard.plugin.output

import com.squareup.javapoet.ClassName
import dev.arunkumar.scabbard.plugin.di.ProcessorScope
import dev.arunkumar.scabbard.plugin.options.ScabbardOptions
import javax.annotation.processing.Filer
import javax.inject.Inject
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation.CLASS_OUTPUT

@ProcessorScope
class DefaultOutputManager
@Inject
constructor(
  override val filer: Filer,
  private val scabbardOptions: ScabbardOptions
) : FilerOutputManager {

  override fun createOutputFiles(currentComponent: TypeElement): OutputFiles {
    val componentName = ClassName.get(currentComponent)
    val packageName = componentName.packageName()
    val componentSimpleNames = componentName.simpleNames().joinToString(".")
    val relativeName = "$packageName.$componentSimpleNames".replace("$", ".")
    val pkg = "scabbardGraph.$componentName"
    val graphOutput = filer.createResource(
      CLASS_OUTPUT,
      pkg,
      "$relativeName.png"
    )
    val dotOutput = filer.createResource(
      CLASS_OUTPUT,
      pkg,
      "$relativeName.dot"
    )
    return OutputFiles(
      graphOutput,
      dotOutput
    )
  }
}