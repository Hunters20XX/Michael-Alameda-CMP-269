package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*                             // Application scope
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json                 // json() function
import kotlinx.serialization.json.Json                         // optional configuration

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            // Configure kotlinx-json if you want; default json() also works
            json(Json { prettyPrint = true; ignoreUnknownKeys = true })
        }

        configureRouting() // register routes that call call.respond(...) with data classes
    }.start(wait = true)
}