import kotlin.system.exitProcess

fun runtimeError(msg: String): Nothing {
    println(msg)
    exitProcess(1)
}