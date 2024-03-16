# Joke Service

## Requirements

- Java 17
- Maven
- A terminal
- A browser (optional)

## Build

`mvn clean install` (or `mvn clean package` for the purist)

## Run

`mvn spring-boot:run`

## Usage

From the terminal:
- curl http://localhost:8080/joke-service/joke

In a browser:
- Navigate to http://localhost:8080/joke-service/joke 

## Comments on implementation

The field "flags" in the json response from the external jokeapi simply begs to be created as a Map. However, without additional knowledge about the endpoint and after considering YAGNI and KISS principles, the choice was made to create an object instead.

The assumption was made that the field "safe" is what is used to check whether the joke is safe. The external API also includes the field "nsfw" (not safe for work) as a flag, this field is ignored. 
