package org.http4k.demo

import org.http4k.core.Credentials
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.demo.Settings.CREDENTIALS
import org.http4k.testing.testWsClient

class IrcAppUnitTest : IrcContract() {
    private val credentials = Credentials("user", "password")
    private val app = IrcApp(Settings.defaults.withProp(CREDENTIALS, credentials).reify())

    override fun newUser(): NewUser = NewUser(app.testWsClient(Request(GET, "/ws"))!!)
}