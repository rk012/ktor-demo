package io.github.rk012.routing

import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureStaticRouting() {
    routing {
        static {
            resource("/", "pages/index.html")
            resource("/submit", "pages/submit.html")

            static("static") {
                resources("static")
            }
        }
    }
}
