package externalFuns

import BooleanData
import ErrorData
import ExternalFunctionData

val boolAnd = "&&" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is BooleanData }) {
        BooleanData((args[0] as BooleanData).bool && (args[1] as BooleanData).bool)
    } else {
        ErrorData("Incompatible argument types")
    }
}

val boolOr = "||" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is BooleanData }) {
        BooleanData((args[0] as BooleanData).bool || (args[1] as BooleanData).bool)
    } else {
        ErrorData("Incompatible argument types")
    }
}

val boolNot = "!" to ExternalFunctionData { args ->
    if (args.size != 1) {
        ErrorData("Invalid amount of arguments")
    } else if (args.first() is BooleanData) {
        BooleanData(!(args[0] as BooleanData).bool)
    } else {
        ErrorData("Incompatible argument types")
    }
}