package org.http4k.demo

import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.should.shouldMatch
import org.http4k.testing.WsClient
import org.http4k.websocket.WsMessage
import org.junit.Test

class NewUser(private val client: WsClient) {
    fun leaves() = client.close()

    fun sends(message: String) = client.send(WsMessage(message))

    fun receives(vararg messages: String) = client.received()
        .take(messages.size).map(WsMessage::bodyString).toList() shouldMatch equalTo(messages.toList())
}

abstract class IrcContract {
    abstract fun newUser(): NewUser

    @Test
    fun `users get expected messages when they interact`() {
        val user1 = newUser()
        user1.receives("user1 joined")
        user1.sends("hello!")
        user1.receives("user1: hello!")

        val user2 = newUser()
        user1.receives("user2 joined")

        user2.receives("user1 joined", "user1: hello!", "user2 joined")
        user2.sends("hello user1!")

        user1.receives("user2: hello user1!")
        user2.receives("user2: hello user1!")

        user1.leaves()

        user2.receives("user1 left")
    }
}