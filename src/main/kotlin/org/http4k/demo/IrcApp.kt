package org.http4k.demo

import io.github.konfigur8.Configuration
import org.http4k.filter.ServerFilters
import org.http4k.routing.ResourceLoader
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.static
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import javax.security.sasl.Sasl.CREDENTIALS


object IrcApp {
    operator fun invoke(config: Configuration): RoutingHttpHandler {
        val userCounter = AtomicInteger()
        val messages = mutableListOf<String>()
        val participants = ConcurrentHashMap<String, WebSocket>()

        fun addMessage(new: String) {
            messages.add(new)
            participants.values.forEach { it.send(WsMessage(new)) }
        }

        fun newConnection(ws: WebSocket) {
            val id = "user${userCounter.incrementAndGet()}"
            participants += id to ws
            addMessage("$id joined")
            messages.map { WsMessage(it) }.forEach { ws.send(it) }
            ws.onMessage {
                addMessage("$id: ${it.bodyString()}")
            }
            ws.onClose {
                participants -= id
                addMessage("$id left")
            }
        }

        return PolyHandler(
            ServerFilters.BasicAuth("http4k", config[CREDENTIALS]).then(static(ResourceLoader.Classpath())),
            websockets("/ws" bind ::newConnection)
        )
    }
}