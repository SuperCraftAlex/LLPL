val allOpen = listOf('(', '[', '{')
val allClose = listOf(')', ']', '}')

var variables = mutableMapOf<String, Data>()

fun main() {
    variables += stdlibNative
    val code = """
        x = [1 2 3 5 9]
        y = (append x 4)
        z = (map y {[it] (+ it 1)})
        (print z)
        
        a = !(err)
        (print (assert a))
    """
    eval(code)
}