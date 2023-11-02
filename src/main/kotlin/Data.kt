open class Data

data class NumberData(
    val num: Number
): Data()

data class BooleanData(
    val bool: Boolean
): Data()

data class ArrayData(
    val array: List<Data?>
): Data()

sealed class FunctionLikeData: Data()

data class FunctionData(
    val body: String,
    val args: List<String>
): FunctionLikeData()

data class ExternalFunctionData(
    val body: (List<Data?>) -> Data?
): FunctionLikeData()

data class ErrorData(
    val error: String
): Data()

data class StringData(
    val string: String
): Data()