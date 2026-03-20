// ============================================================
// Exercise 1: Modeling the "Web Response" (Data Classes)
// ============================================================
/**
 * Data class representing an HTTP response sent back to a client.
 * 
 * @property statusCode The HTTP status code (e.g., 200, 404)
 * @property statusMessage The HTTP status message (e.g., "OK", "Not Found")
 * @property body Optional content (JSON, HTML, etc.) - can be null
 */
data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String? = null
)