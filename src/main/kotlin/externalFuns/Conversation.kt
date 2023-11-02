package externalFuns

import ArrayData
import BooleanData
import ErrorData
import ExternalFunctionData
import FunctionData
import NumberData
import StringData
import variables

val convStr = "string" to ExternalFunctionData { args ->
    if (args.size != 1) {
        return@ExternalFunctionData ErrorData("Invalid amount of arguments")
    }

    when (val f = args.first()) {
        is StringData -> StringData("\"${f.string}\"")
        is NumberData -> StringData(f.num.toString())
        is ArrayData -> StringData(
            f.array.joinToString(", ", "[", "]") {
                (variables["string"]!! as ExternalFunctionData).body(listOf(it))
                    .let { l -> (l as StringData).string }
            })
        is FunctionData -> StringData("<function>")
        is ExternalFunctionData -> StringData("<external function>")
        is ErrorData -> StringData("!(${f.error})")
        is BooleanData -> StringData(if (f.bool) "true" else "false")
        else -> ErrorData("Invalid argument type")
    }
}