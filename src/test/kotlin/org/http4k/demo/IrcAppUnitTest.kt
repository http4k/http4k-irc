package org.http4k.demo

import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.should.shouldMatch
import org.http4k.core.Credentials
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.demo.Settings.CREDENTIALS
import org.http4k.testing.testWsClient
import org.http4k.websocket.PolyHandler
import org.http4k.websocket.WsMessage
import org.junit.Test

class IrcAppUnitTest {

    private val credentials = Credentials("user", "password")
    private val app = IrcApp(Settings.defaults.withProp(CREDENTIALS, credentials).reify())

    private class NewUser(app: PolyHandler) {
        private val client = app.testWsClient(Request(GET, "/ws"))!!

        fun leaves() = client.close(Status.OK)

        fun sends(message: String) = client.send(WsMessage(message))

        fun receives(vararg messages: String) = client.received()
            .take(messages.size).toList() shouldMatch equalTo(messages.map(::WsMessage))
    }

    @Test
    fun `users get expected messages when they interact`() {
        val user1 = NewUser(app)
        user1.receives("user1 joined")
        user1.sends("hello!")
        user1.receives("user1: hello!")

        val user2 = NewUser(app)
        user1.receives("user2 joined")

        user2.receives("user1 joined", "user1: hello!", "user2 joined")
        user2.sends("hello user1!")

        user1.receives("user2: hello user1!")
        user2.receives("user2: hello user1!")

        user1.leaves()

        user2.receives("user1 left")
    }

}