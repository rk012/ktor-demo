package io.github.rk012.routing

import io.github.rk012.Card
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*

private val cards = mutableMapOf<Int, Card>()

fun Application.configureApiRouting() {
    routing {
        install(ContentNegotiation) {
            json()
        }

        route("/api") {
            put {
                val card = call.receive<Card>()
                val id = cards.maxOfOrNull { it.key }?.plus(1) ?: 1

                cards[id] = card

                call.respond(HttpStatusCode.Created, id)
            }

            get {
                call.respond(cards.mapValues { it.value.title })
            }

            get("/{id}")  {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
                val card = cards[id] ?: return@get call.respond(HttpStatusCode.NotFound)

                call.respond(card)
            }

            put("/{id}") {
                val card = call.receive<Card>()
                val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)

                cards[id] ?: return@put call.respond(HttpStatusCode.NotFound)

                cards[id] = card

                call.respond(HttpStatusCode.NoContent)
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                cards.remove(id) ?: return@delete call.respond(HttpStatusCode.NotFound)

                call.respond(HttpStatusCode.NoContent)
            }
        }
    }

}