package naked

import module.Module

fun main() {
    println("hello world")

    val foo = Additional()
    foo.LoneleyMethod()

    val bar = Module()
    bar.LoneleyModuleMethod()
}
