# Grpc backend for the tabletop helper admin

This project contains the source for a backend gRPC API used to store assets and configuration data
used in the Tabletop Combat Helper Unity Client and Angular frontend client.

You can find these here:

  ## Unity Client

  https://github.com/dcasadevall/tabletop-combat-helper

  ## Web Admin Client (Angular 7)

  https://github.com/dcasadevall/tabletop-combat-helper-admin


## Prerequisites

* [Java 8](http://openjdk.java.net/install/)
* [Docker](https://www.docker.com/products/docker)

## Building and Running the Server

    ./gradlew build

To run the Java server locally:

# Start the server (listens on port 8000 by default)
java -jar ./server/build/libs/server.jar
