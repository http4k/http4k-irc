# http4k demo: IRC

[![build status](https://travis-ci.org/daviddenton/http4k-demo-irc.svg?branch=master)](https://travis-ci.org/daviddenton/http4k-demo-irc.svg?branch=master)

This is a simple IRC clone built with [**http4k**](https://http4k.org) and deployed to Heroku
through a fully CD pipeline run on Travis. the full application is 30 lines of code (when imports
are excluded).

It uses the following [**http4k**](https://http4k.org) modules and features:

- http4k core `http4k-core`
- Jetty server module `http4k-server-jetty` including unit-testable Websockets
- http4k core `http4k-cloudnative` for configuration
- Shared behavioural contracts for Unit (offline) and Server (online) testing of Websockets. Ie. 1
  contract, reusable across testing scopes.

It is deployed into Heroku [**here**](http://http4k-demo-irc.herokuapp.com/).

## In action:

<img src="screenshot.png" alt="Screenshot of IRC clone, with users sending messages"/>

## Running it locally

Required environment variables:

```
CREDENTIALS=<user>:<password> // for basic auth on the site
```

Set the above environment variables and run the `IrcLauncher` class. The app will be available
on [http://localhost:5000](http://localhost:5000)
