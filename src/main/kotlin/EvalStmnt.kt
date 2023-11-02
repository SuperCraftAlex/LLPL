fun evalStatement(stmtIn: String) {
    val stmt = stmtIn.trim()
    if (stmt.isEmpty())
        return

    val first = stmt.first()

    if (first == '(') {
        evalExpr(stmt)
        return
    }

    val pos = stmt.indexOf('=')
    if (pos == -1) {
        runtimeError("!(Not a statement: \"$stmt\")")
    }

    val name = stmt.substring(0, pos).trim()

    val value = evalExpr(stmt.substring(pos+1).trim())
        ?: runtimeError("!(Error evaluating rvalue: not a expression)")

    if (name in variables) {
        runtimeError("!(Error evaluating statement: variable \"$name\" already defined)")
    }

    variables[name] = value
}