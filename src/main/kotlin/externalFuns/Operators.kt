package externalFuns

import ArrayData
import ErrorData
import ExternalFunctionData
import NumberData
import StringData
import repeat

val opPlus = "+" to ExternalFunctionData { args ->
    if (args.all { it is NumberData }) {
        NumberData(args.sumOf { (it as NumberData).num.toDouble() })
    } else if (args.all { it is StringData }) {
        StringData(args.joinToString("") { (it as StringData).string })
    } else {
        ErrorData("Incompatible argument types")
    }
}

val opMinus = "-" to ExternalFunctionData { args ->
    if (args.all { it is NumberData }) {
        NumberData(args.map { (it as NumberData).num.toDouble() }.reduce { a, b -> a - b })
    } else {
        ErrorData("Incompatible argument types")
    }
}

val opTimes = "*" to ExternalFunctionData { args ->
    if (args.all { it is NumberData }) {
        NumberData(args.map { (it as NumberData).num.toDouble() }.reduce { a, b -> a * b })
    } else if (args.firstOrNull() is StringData && args.getOrNull(1) is NumberData) {
        StringData((args[0] as StringData).string.repeat((args[1] as NumberData).num.toInt()))
    } else if (args.firstOrNull() is ArrayData && args.getOrNull(1) is NumberData) {
        ArrayData((args[0] as ArrayData).array.repeat((args[1] as NumberData).num.toInt()))
    } else {
        ErrorData("Incompatible argument types")
    }
}

val opDiv = "/" to ExternalFunctionData { args ->
    if (args.all { it is NumberData }) {
        NumberData(args.map { (it as NumberData).num.toDouble() }.reduce { a, b -> a / b })
    } else {
        ErrorData("Incompatible argument types")
    }
}

val opMod = "%" to ExternalFunctionData { args ->
    if (args.all { it is NumberData }) {
        NumberData(args.map { (it as NumberData).num.toDouble() }.reduce { a, b -> a % b })
    } else {
        ErrorData("Incompatible argument types")
    }
}