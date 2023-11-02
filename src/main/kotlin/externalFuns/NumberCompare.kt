package externalFuns

import BooleanData
import ErrorData
import ExternalFunctionData
import NumberData

val numCompGT = ">" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is NumberData }) {
        BooleanData((args[0] as NumberData).num.toDouble() > (args[1] as NumberData).num.toDouble())
    } else {
        ErrorData("Incompatible argument types")
    }
}

val numCompLT = "<" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is NumberData }) {
        BooleanData((args[0] as NumberData).num.toDouble() < (args[1] as NumberData).num.toDouble())
    } else {
        ErrorData("Incompatible argument types")
    }
}

val numCompGTE = ">=" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is NumberData }) {
        BooleanData((args[0] as NumberData).num.toDouble() >= (args[1] as NumberData).num.toDouble())
    } else {
        ErrorData("Incompatible argument types")
    }
}

val numCompLTE = "<=" to ExternalFunctionData { args ->
    if (args.size != 2) {
        ErrorData("Invalid amount of arguments")
    } else if (args.all { it is NumberData }) {
        BooleanData((args[0] as NumberData).num.toDouble() <= (args[1] as NumberData).num.toDouble())
    } else {
        ErrorData("Incompatible argument types")
    }
}