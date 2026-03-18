// File: Exercise1_NullableProfile.kt

fun main() {
    // Non-nullable variable
    val studentName: String = "John"

    // Nullable variable initialized to null
    val middleName: String? = null

    // Using Elvis operator (?:) to handle null
    val displayMiddleName = middleName ?: "No Middle Name"

    println("Welcome, $studentName $displayMiddleName!")

    // Test with a non-null middleName
    val middleName2: String? = "Alex"
    val displayMiddleName2 = middleName2 ?: "No Middle Name"
    println("Welcome, $studentName $displayMiddleName2!")
}