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

The server starts and listens to port 8000. You first need to build it.

    ./gradlew build
    
Now, you can either start the server locally for development, or deploy it to a cloud solution.
This example will cover the GCP deployment.

## Start the server locally
    
    java -jar ./server/build/libs/server.jar
    
## Run the test client (With the local server running)
    
    java -jar ./client/build/libs/client.jar 

## Deploy to GCP

Follow the instructions at: https://cloud.google.com/endpoints/docs/grpc/get-started-kubernetes-engine.

In the Configuring Endpoints section, you can skip the part where we generate the api_descriptors,
as they are already generated for you in the protos folder.
A .yaml configuration file already exists for you as well. You just have to change the project id
to fit your server.
