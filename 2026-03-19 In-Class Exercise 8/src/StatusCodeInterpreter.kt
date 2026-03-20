// ============================================================
// Exercise 2: The "Status Code Interpreter" (When Expressions)
// ============================================================
/**
 * Interprets an HTTP status code and returns a category description.
 *
 * @param code The HTTP status code to interpret
 * @return A string describing the category of the status code
 */
fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else -> "Unknown status code."
    }
}