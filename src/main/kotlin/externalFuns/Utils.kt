package externalFuns

import ErrorData
import ExternalFunctionData
import runtimeError

// (try command...)
val utilTry = "try" to ExternalFunctionData { args ->
    val err = args.firstOrNull { it is ErrorData } as? ErrorData
        ?: return@ExternalFunctionData null
    err
}

// (ignore val ignored...)
val utilIgnore = "ignore" to ExternalFunctionData { args ->
    args.firstOrNull()
}

// (catch instead command)
val utilCatch = "catch" to ExternalFunctionData { args ->
    if (args.size != 2) {
        return@ExternalFunctionData ErrorData("Expected 2 arguments")
    }

    val instead = args[0]
    val obj = args[1]

    if (obj is ErrorData)
        instead
    else
        obj
}

// (assert value)
// if the value is a error, throw a runtime error otherwise return the value
val utilAssert = "assert" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Expected 1 argument")
    }

    val obj = args[0]

    if (obj is ErrorData)
        runtimeError("!(Assertion failed: ${obj.error})")

    obj
}