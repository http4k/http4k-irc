package org.http4k.demo

import io.github.konfigur8.ConfigurationTemplate
import io.github.konfigur8.Property
import org.http4k.core.Credentials

object Settings {
    val CREDENTIALS = Property("CREDENTIALS", String::toCredentials, Credentials::fromCredentials)

    val defaults = ConfigurationTemplate()
        .requiring(CREDENTIALS)
}

fun Credentials.fromCredentials() = "$user:$password"
fun String.toCredentials() = split(":").run { Credentials(get(0), get(1)) }
