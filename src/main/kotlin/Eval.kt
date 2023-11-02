import java.lang.StringBuilder

fun eval(code: String) {
    val lines = mutableListOf<StringBuilder>()

    code.lines().forEach { lineIn ->
        val line = lineIn.trim()

        if (line.isEmpty()) return@forEach

        val first = line.first()

        if (first == '#') return@forEach

        if (first == '/') {
            if (lines.isEmpty()) {
                println("!(Cannot append to empty line)")
            }
            lines.last().append(" " + line.substring(1).trim())
            return@forEach
        }

        lines.add(StringBuilder(line))
    }

    lines.forEach {
        evalStatement(it.toString())
    }
}