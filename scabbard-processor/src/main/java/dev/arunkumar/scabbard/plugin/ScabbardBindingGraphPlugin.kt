package dev.arunkumar.scabbard.plugin

import com.google.auto.service.AutoService
import dagger.model.BindingGraph
import dagger.spi.BindingGraphPlugin
import dagger.spi.DiagnosticReporter
import javax.annotation.processing.Filer
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

@AutoService(BindingGraphPlugin::class)
class ScabbardBindingGraphPlugin : BindingGraphPlugin {

    private lateinit var filer: Filer
    private lateinit var types: Types
    private lateinit var elements: Elements

    override fun initFiler(filer: Filer) {
        this.filer = filer
    }

    override fun initTypes(types: Types) {
        this.types = types
    }

    override fun initElements(elements: Elements) {
        this.elements = elements
    }

    override fun visitGraph(bindingGraph: BindingGraph, diagnosticReporter: DiagnosticReporter) {
    }
}