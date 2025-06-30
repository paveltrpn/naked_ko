package objrectifier

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class ObjRectifier : CliktCommand() {
    private val inputObjFilePath: String by option("-i").prompt().help("Path to input wavefront obj file")
    private val outputCodeFile: String by option("-o").prompt().help("Path to output file.")

    private val wd: String = "src/main/resources/"

    override fun run() {
        val obj = ObjFile(wd + inputObjFilePath)
        obj.out()
    }
}

// usage: gradle objrectifier:run --args="-i a -o b"
fun main(args: Array<String>) {
    try {
        ObjRectifier().main(args)
    } catch (e: Exception) {
        println("catch some exception")
        println(e.toString())
    }
}