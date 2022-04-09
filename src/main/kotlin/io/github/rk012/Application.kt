package io.github.rk012

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.github.rk012.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureApiRouting()
        configureStaticRouting()
    }.start(wait = true)
}
