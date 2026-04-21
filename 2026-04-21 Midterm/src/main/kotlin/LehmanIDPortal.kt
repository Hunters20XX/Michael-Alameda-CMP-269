package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import kotlinx.serialization.Serializable
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.util.concurrent.ConcurrentHashMap

// 1. DATA MODELING (Kotlin Fundamentals)
@Serializable
data class Student (
    val id: String,
    val name: String,
    val major: String?, //Nullable as per requirements
    val accessLevel: Int
)

// QR Code Generator Function (from Exercise 4)
fun generateQRCode(content: String, size: Int = 300): ByteArray {
    val hints = mapOf(com.google.zxing.EncodeHintType.CHARACTER_SET to "UTF-8")
    val bitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints)
    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream.toByteArray()
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

// 2. THREAD-SAFE DATABASE (TOP-LEVEL)
val studentDb = ConcurrentHashMap<String, Student>().apply {
    put("12345", Student("12345", "Alice Smith", "Computer Science", 5))
    put("67890", Student("67890", "Bob Jones", null, 3)) //Will test Elvis operator
}

fun Application.module() {
    // 3. CONTENT NEGOTIATION (JSON API)
    install(ContentNegotiation) {
        json()
    }
    routing {
        // A. STATIC PORTAL
        staticResources("/", "static")

        // B. STUDENT API
        get("/api/student/{id}") {
            val id = call.parameters["id"]

            if (id.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Student ID is required")
                return@get
            }

            val student = studentDb[id]

            if (student != null) {
                val response = Student(
                    id = student.id,
                    name = student.name,
                    major = student.major ?: "Undecided", // Elvis operator handles null
                    accessLevel = student.accessLevel
                )
                call.respond(response)
            } else {
                call.respond(HttpStatusCode.NotFound, "Student with ID '$id' not found")
            }
        }

        // C. QR GENERATOR (Query Parameters & Image Response)
        get("/generate-id") {
            val sid = call.request.queryParameters["sid"]

            if (sid.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Query parameter 'sid' is required")
                return@get
            }

            val student = studentDb[sid]

            if (student == null) {
                call.respond(HttpStatusCode.NotFound, "Student with ID '$sid' not found")
                return@get
            }

            val qrContent = "LehmanID:${student.id}|Level:${student.accessLevel}|Name:${student.name}"
            val qrBytes = generateQRCode(qrContent, 300)

            call.respondBytes(
                contentType = ContentType.Image.PNG,
                bytes = qrBytes
            )
        }
    }
}