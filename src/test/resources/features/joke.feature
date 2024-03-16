@joke
Feature: Joke

  Scenario: 1. A variety of jokes
    Given a variety of jokes from "jokes.json"
    When a joke is retrieved
    Then the joke is "Schrödinger's cat walks into a bar and doesn't."

  Scenario: 2. A variety of jokes all invalid
    Given a variety of jokes from "no-valid-jokes.json"
    When a joke is retrieved
    Then an http error is returned with message "404 : \"What happens when the rabbits run from the hat? ... Joke not found.\""
