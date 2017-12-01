# http4k demo: IRC 

[![build status](https://travis-ci.org/daviddenton/http4k-demo-irc.svg?branch=master)](https://travis-ci.org/daviddenton/http4k-demo-irc.svg?branch=master)

This is a simple IRC clone built with [**http4k**](https://http4k.org) and deployed to Heroku through a fully CD pipeline run on Travis. the full application is 30 lines of code (when imports are excluded).

It uses the following [**http4k**](https://http4k.org) modules and features:

- http4k core `http4k-core`
- Jetty unit-testable Websockets
- Jetty server module `http4k-server-jetty`

It is deployed into Heroku [**here**](http://http4k-demo-irc.herokuapp.com/).

## In action:

<img src="https://github.com/daviddenton/http4k-demo-irc/raw/master/screenshot.png"/>

## Running it locally

Required environment variables:
```
CREDENTIALS=<user>:<password>                   // for basic auth on the site
S3_REGION=<s3-region>                           // eg. us-east-1
AWS_CREDENTIALS=<awsAccessKey>:<awsSecretKey>   // AWS access key with full S3 access
AWS_BUCKET=<bucket>                             // existing AWS bucket
```

Set the above environment variables and run the `S3BoxLauncher` class. The app will be available on [http://localhost:5000](http://localhost:5000)
