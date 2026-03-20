// ============================================================
// Exercise 3: Simple Request Router (Simulating Server Logic)
// ============================================================
/**
 * Simulates a basic server router that directs users based on URL path.
 *
 * @param path The URL path requested
 * @param user The authenticated user (nullable)
 * @return The response message based on the route
 */
fun routeRequest(path: String, user: String?): String {
    return when (path) {
        "/home" -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> {
            if (user == null) {
                "Error: Unauthorized access to grades."
            } else {
                "Loading grades for $user..."
            }
        }
        else -> "404: Path $path not found."
    }
}