// File: Exercise2_DataClasses.kt

// Data class - automatically generates equals(), hashCode(), toString(), copy()
data class Laptop(val brand: String, val ramGB: Int)

// Extension function for Int class
fun Int.toLehmanGigabytes(): String {
    return "$this GB (Lehman Standard)"
}

fun main() {
    // Create two instances of Laptop
    val laptop1 = Laptop("Dell", 16)
    val laptop2 = Laptop("MacBook", 32)

    // Use the extension function to print RAM
    println("Laptop 1: ${laptop1.brand}, RAM: ${laptop1.ramGB.toLehmanGigabytes()}")
    println("Laptop 2: ${laptop2.brand}, RAM: ${laptop2.ramGB.toLehmanGigabytes()}")

    // Demonstrate data class features
    println("\nData class features:")
    println("laptop1 = $laptop1")
    println("laptop1 == laptop2: ${laptop1 == laptop2}")

    // Copy with modification
    val laptop1Upgraded = laptop1.copy(ramGB = 32)
    println("Copied and upgraded: $laptop1Upgraded")
}