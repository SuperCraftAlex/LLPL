fun <E> List<E>.repeat(toInt: Int): List<E> {
    val list = mutableListOf<E>()
    repeat(toInt) {
        list.addAll(this)
    }
    return list
}

fun callFunction(func: FunctionLikeData, args: List<Data?>): Data? {
    if (func is ExternalFunctionData) {
        return func.body(args)
    }

    func as FunctionData

    if (args.size != func.args.size) {
        return ErrorData("Invalid function call; expected ${func.args.size} arguments, got ${args.size}")
    }

    val oldVariables = variables.toMutableMap()

    args.forEachIndexed { i, arg ->
        if (arg is ErrorData) {
            return ErrorData("Cannot call function; argument error: ${arg.error}")
        }

        if (arg == null) {
            return ErrorData("Cannot call function; invalid argument")
        }

        variables[func.args[i]] = arg
    }

    val res = evalExpr(func.body)

    variables = oldVariables

    return res
}