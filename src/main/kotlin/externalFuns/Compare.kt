package externalFuns

import BooleanData
import ExternalFunctionData

val compEq = "==" to ExternalFunctionData { args ->
    BooleanData(args.all { it == args.first() })
}

val compNEq = "!=" to ExternalFunctionData { args ->
    BooleanData(args.any { it != args.first() })
}