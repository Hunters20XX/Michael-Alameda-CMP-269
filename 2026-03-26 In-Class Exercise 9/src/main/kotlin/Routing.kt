package com.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.net.URLDecoder

@Serializable
data class Stock(val symbol: String, val price: Double)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Server is online at Lehman College.")
        }

        get("/greet/{name}") {
            val name = call.parameters["name"] ?: "Guest"
            call.respondText("Hello, $name! Welcome to CMP 269.")
        }

        get("/grade/{studentId}") {
            val grades = mapOf("123" to 95, "456" to 82)
            val studentId = call.parameters["studentId"]
            if (studentId == null) {
                call.respond(HttpStatusCode.NotFound, "Student not found")
                return@get
            }
            val grade = grades[studentId]
            if (grade != null) {
                call.respondText("Grade for student $studentId is $grade")
            } else {
                call.respond(HttpStatusCode.NotFound, "Student not found")
            }
        }

        // New JSON endpoint
        get("/api/stock/{symbol}") {
            val raw = call.parameters["symbol"] ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Missing symbol"
            )

            // decode URL-encoded symbol and construct a sample Stock object
            val symbol = URLDecoder.decode(raw, Charsets.UTF_8.name()).uppercase()
            val stock = Stock(symbol, 150.25) // replace price with real lookup later
            call.respond(stock) // ContentNegotiation will serialize to JSON
        }

        // static resources, etc...
    }
}