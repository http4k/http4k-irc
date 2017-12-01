package org.http4k.demo

import org.http4k.client.WebsocketClient
import org.http4k.core.Credentials
import org.http4k.core.Uri
import org.http4k.demo.Settings.CREDENTIALS
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.After
import org.junit.Before

class IrcAppServerTest : IrcContract() {
    override fun newUser() = NewUser(WebsocketClient.blocking(Uri.of("ws://localhost:8000/ws")))

    private val server = IrcApp(Settings.defaults
        .withProp(CREDENTIALS, Credentials("user", "password")).reify()
    ).asServer(Jetty(8000))

    @Before
    fun before() {
        server.start()
    }

    @After
    fun after() = server.stop()
}