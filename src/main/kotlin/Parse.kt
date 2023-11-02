fun parseParents(
    str: String,
    allOpen: List<Char>,
    allClose: List<Char>
): Pair<Int, List<String>> { // amount of used chars; segments
    var used = 0
    val segments = mutableListOf<String>()

    val current = StringBuilder()
    var indent = 0
    for (c in str) {
        used++

        if (c in allOpen) {
            indent++
            if (indent == 1) {
                continue
            }
        }

        if (c in allClose) {
            indent--
            if (indent == 0) {
                segments += current.toString()
                current.clear()
                continue
            }
        }

        if (indent == 0) {
            if (current.isNotEmpty()) {
                segments += current.toString()
            }
            return used to segments
        }

        if (indent == 1 && c == ' ') {
            segments += current.toString()
            current.clear()
            continue
        }

        current.append(c)
    }

    if (current.isNotEmpty()) {
        segments += current.toString()
    }

    return used to segments
}