package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpHeaders
import io.ktor.http.ContentType


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        // QR-as-a-Service endpoint
        get("/qr") {
            // Capture the query parameter "text"
            val text = call.request.queryParameters["text"]

            // Check if text is provided
            if (text.isNullOrBlank()) {
                call.respondText("Please provide a 'text' parameter. Example: /qr?text=Hello")
                return@get
            }

            try {
                // Generate QR code in memory
                val qrCodeBytes = generateQRCode(text)

                // Set the response header for PNG content type
                call.response.header(HttpHeaders.ContentType, ContentType.Image.PNG.toString())

                // Respond with the byte array
                call.respondBytes(qrCodeBytes)
            } catch (e: Exception) {
                call.respondText("Error generating QR code: ${e.message}")
            }
        }


    }
}