package externalFuns

import ExternalFunctionData
import StringData
import variables

val ioPrint = "print" to ExternalFunctionData { args ->
    val str = args.joinToString(" ") {
        (variables["string"]!! as ExternalFunctionData).body(listOf(it))
            .let { l -> (l as StringData).string }
    }
    println(str)
    null
}