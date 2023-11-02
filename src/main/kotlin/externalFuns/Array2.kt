package externalFuns

import ArrayData
import ErrorData
import ExternalFunctionData
import FunctionLikeData
import NumberData
import callFunction

val arrMap = "map" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Expected 2 arguments")
    }

    val first = args.first()
    val second = args[1]

    if (first !is ArrayData) {
        return@ExternalFunctionData ErrorData("Expected first argument to be an array")
    }

    if (second !is FunctionLikeData) {
        return@ExternalFunctionData ErrorData("Expected second argument to be a function")
    }

    val res = first.array.map {
        callFunction(second, listOf(it))
    }

    return@ExternalFunctionData ArrayData(res)
}

val arrReduce = "reduce" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Expected 2 arguments")
    }

    val first = args.first()
    val second = args[1]

    if (first !is ArrayData) {
        return@ExternalFunctionData ErrorData("Expected first argument to be an array")
    }

    if (second !is FunctionLikeData) {
        return@ExternalFunctionData ErrorData("Expected second argument to be a function")
    }

    val res = first.array.reduce { acc, data ->
        callFunction(second, listOf(acc, data))
    }

    return@ExternalFunctionData res
}