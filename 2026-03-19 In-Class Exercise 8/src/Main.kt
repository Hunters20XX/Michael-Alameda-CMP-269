// ============================================================
// Main Function - Testing All Exercises
// ============================================================
fun main() {
    println("=" .repeat(60))
    println("EXERCISE 1: WebResponse Data Class")
    println("=" .repeat(60))

    // Create a "Success" response (200)
    val successResponse = WebResponse(
        statusCode = 200,
        statusMessage = "OK",
        body = """{"message": "Welcome to the API!"}"""
    )

    // Create a "Not Found" response (404)
    val notFoundResponse = WebResponse(
        statusCode = 404,
        statusMessage = "Not Found",
        body = null
    )

    println("Success Response:")
    println(successResponse)
    println()
    println("Not Found Response:")
    println(notFoundResponse)

    // Note: Kotlin's data class automatically provides:
    // - toString() for nice readable format
    // - equals() and hashCode()
    // - copy() function

    println("\n" + "=" .repeat(60))
    println("EXERCISE 2: Status Code Interpreter")
    println("=" .repeat(60))

    // Test with various status codes
    val testCodes = listOf(201, 404, 503, 302, 418, 600)

    for (code in testCodes) {
        println("Status $code: ${describeStatus(code)}")
    }

    println("\n" + "=" .repeat(60))
    println("EXERCISE 3: Simple Request Router")
    println("=" .repeat(60))

    // Test /home with no user (Guest)
    println("Test 1 - /home (no user):")
    println(routeRequest("/home", null))

    // Test /home with a user
    println("\nTest 2 - /home (with user):")
    println(routeRequest("/home", "Alice"))

    // Test /grades without authorization
    println("\nTest 3 - /grades (no user):")
    println(routeRequest("/grades", null))

    // Test /grades with authorization
    println("\nTest 4 - /grades (with user):")
    println(routeRequest("/grades", "Bob"))

    // Test unknown path
    println("\nTest 5 - /api/unknown:")
    println(routeRequest("/api/unknown", null))
}