// File: Exercise3_SealedClasses.kt

// Sealed class - restricted hierarchy
sealed class EnrollmentStatus {
    // Subclass with data
    data class Success(val courseCode: String) : EnrollmentStatus()

    // Subclass with data
    data class Error(val message: String) : EnrollmentStatus()

    // Subclass without data (object singleton)
    data object Loading : EnrollmentStatus()
}

// Function using when expression - exhaustive check (no else needed!)
fun printStatus(status: EnrollmentStatus) {
    when (status) {
        is EnrollmentStatus.Success -> {
            println("✓ Enrollment Successful! Course: ${status.courseCode}")
        }
        is EnrollmentStatus.Error -> {
            println("✗ Enrollment Failed: ${status.message}")
        }
        is EnrollmentStatus.Loading -> {
            println("⏳ Enrollment in progress...")
        }
    }
}

fun main() {
    // Test with Success object
    val successStatus = EnrollmentStatus.Success("KOTLIN101")
    printStatus(successStatus)

    // Test with Error object
    val errorStatus = EnrollmentStatus.Error("Course is full")
    printStatus(errorStatus)

    // Test with Loading
    val loadingStatus = EnrollmentStatus.Loading
    printStatus(loadingStatus)
}