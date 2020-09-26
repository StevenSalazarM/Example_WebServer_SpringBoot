This repository contains a maven project (in demo directory) that allows to create a Spring Boot HTTP Server.

The served enables two possible POST requests on http://ip:port/clients/ and http://ip:port/get-all-position.

This HTTP server was used as a HTTP server for my thesis project that is available at [Performance-Comparison-Mqtt-Http](https://github.com/StevenSalazarM/Performance-comparison-http-mqtt).

the url /get-all-position/ will response with a JSON containing all the position of the users in the database.

the url /client/positions/ allows to each client to send the current position and store them in the database.

In order to make the project run you should have Redis already running and maven installed.
