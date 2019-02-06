In /demo/ you can find a project that allows to comunicate with a server HTTP, this server enables two urls to which you can send requests.

the url /get-all-position/ will response with a JSON containing all the position of the users in the database.

the url /client/positions/ allows to each client to send the current position and store them in the database.

In order to make the project run you should have Redis already running.
