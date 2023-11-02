fun evalExpr(exprIn: String): Data? {
    val expr = exprIn.trim()

    if (expr.isEmpty())
        return null

    val first = expr.first()

    if (first.isDigit()) {
        return NumberData(
            if ('.' in expr) expr.toDouble()
            else expr.toInt()
        )
    }

    if (first == '!') {
        return ErrorData(expr.substring(2, expr.length-1))
    }

    if (first == '(') {
        val (used, segments) = parseParents(expr, allOpen, allClose)

        if (used != expr.length) {
            return ErrorData("Invalid expression")
        }

        if (segments.isEmpty()) {
            return ErrorData("Empty function call expression")
        }

        val func = evalExpr(segments.first())

        val args = segments.drop(1).map { evalExpr(it) }

        if (func is ExternalFunctionData) {
            return func.body(args)
        }

        if (func !is FunctionData) {
            if (func is ErrorData) {
                return ErrorData("Cannot call function; function error: ${func.error}")
            }
            return ErrorData("Invalid function call")
        }

        args.forEach {
            if (it is ErrorData) {
                return ErrorData("Cannot call function; argument error: ${it.error}")
            }
        }

        if (args.size != func.args.size) {
            return ErrorData("Invalid function call; expected ${func.args.size} arguments, got ${args.size}")
        }

        val oldVariables = variables.toMutableMap()

        func.args.forEachIndexed { index, arg ->
            variables[arg] = args[index]!!
        }

        val result = evalExpr(func.body)

        variables = oldVariables

        return result
    }

    if (first == '[') {
        val (used, segments) = parseParents(expr, allOpen, allClose)

        if (used != expr.length) {
            return ErrorData("Invalid expression")
        }

        if (segments.isEmpty()) {
            return ErrorData("Empty array expression")
        }

        val array = segments.map { evalExpr(it) }

        array.forEach {
            if (it is ErrorData) {
                return ErrorData("Cannot create array; element error: ${it.error}")
            }
        }

        return ArrayData(array)
    }

    if (first == '{') {
        val (used, segments) = parseParents(expr, allOpen, allClose)

        if (used != expr.length) {
            return ErrorData("Invalid expression")
        }

        if (segments.size == 1) {
            return FunctionData(segments.first(), listOf())
        }

        if (segments.size == 2) {
            val firs = segments.first()
            val (used2, args) = parseParents(firs, listOf('['), listOf(']'))

            if (used2 != segments.first().length) {
                return ErrorData("Error parsing function argument specification")
            }

            return FunctionData(segments.last(), args)
        }

        return ErrorData("Invalid function expression")
    }

    if (expr in variables) {
        return variables[expr]
    }

    return ErrorData("Unknown expression: \"$expr\"")
}