package externalFuns

import ArrayData
import ErrorData
import ExternalFunctionData
import NumberData

val arrLen = "length" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg = args[0]!!

    if (arg !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    return@ExternalFunctionData NumberData(arg.array.size)
}

val arrFirst = "first" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg = args[0]!!

    if (arg !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg.array.isEmpty()) {
        return@ExternalFunctionData ErrorData("Empty array")
    }

    return@ExternalFunctionData arg.array.first()
}

val arrLast = "last" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg = args[0]!!

    if (arg !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg.array.isEmpty()) {
        return@ExternalFunctionData ErrorData("Empty array")
    }

    return@ExternalFunctionData arg.array.last()
}

val arrEmpty = "empty" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg = args[0]!!

    if (arg !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    return@ExternalFunctionData NumberData(if (arg.array.isEmpty()) 1 else 0)
}

val arrGet = "get" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!

    if (arg1 !is ArrayData || arg2 !is NumberData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg2.num.toInt() < 0 || arg2.num.toInt() >= arg1.array.size) {
        return@ExternalFunctionData ErrorData("Index out of bounds")
    }

    return@ExternalFunctionData arg1.array[arg2.num.toInt()]
}

val arrAppend = "append" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!

    if (arg1 !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    return@ExternalFunctionData ArrayData(arg1.array + arg2)
}

val arrJoin = "join" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!

    if (arg1 !is ArrayData || arg2 !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    return@ExternalFunctionData ArrayData(arg1.array + arg2.array)
}

val arrSet = "set" to ExternalFunctionData { args ->
    if (args.size != 3) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!
    val arg3 = args[2]!!

    if (arg1 !is ArrayData || arg2 !is NumberData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg2.num.toInt() < 0 || arg2.num.toInt() >= arg1.array.size) {
        return@ExternalFunctionData ErrorData("Index out of bounds")
    }

    val new = arg1.array.toMutableList()

    new[arg2.num.toInt()] = arg3

    return@ExternalFunctionData ArrayData(new)
}

val arrRemove = "remove" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!

    if (arg1 !is ArrayData || arg2 !is NumberData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg2.num.toInt() < 0 || arg2.num.toInt() >= arg1.array.size) {
        return@ExternalFunctionData ErrorData("Index out of bounds")
    }

    val new = arg1.array.toMutableList()

    new.removeAt(arg2.num.toInt())

    return@ExternalFunctionData ArrayData(new)
}

val arrIndex = "index" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!

    if (arg1 !is ArrayData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    return@ExternalFunctionData NumberData(arg1.array.indexOf(arg2))
}

val arrInsert = "insert" to ExternalFunctionData { args ->
    if (args.size != 3) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    val arg1 = args[0]!!
    val arg2 = args[1]!!
    val arg3 = args[2]!!

    if (arg1 !is ArrayData || arg2 !is NumberData) {
        return@ExternalFunctionData ErrorData("Invalid arguments")
    }

    if (arg2.num.toInt() < 0 || arg2.num.toInt() >= arg1.array.size) {
        return@ExternalFunctionData ErrorData("Index out of bounds")
    }

    val new = arg1.array.toMutableList()

    new.add(arg2.num.toInt(), arg3)

    return@ExternalFunctionData ArrayData(new)
}