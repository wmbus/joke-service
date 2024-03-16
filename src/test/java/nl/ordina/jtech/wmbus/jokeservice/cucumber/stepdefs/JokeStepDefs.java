package nl.ordina.jtech.wmbus.jokeservice.cucumber.stepdefs;

import com.github.tomakehurst.wiremock.common.ContentTypes;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import nl.ordina.jtech.wmbus.jokeservice.JokeServiceApplication;
import nl.ordina.jtech.wmbus.jokeservice.dto.JokeDto;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CucumberContextConfiguration
@SpringBootTest(classes = JokeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JokeStepDefs {

    private static final String JOKEAPI_URL = "/joke/Any?type=single&amount=16";

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private String port;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8932));

    private static ResponseEntity<JokeDto> latestResponse;
    private static HttpClientErrorException latestException;
    private static String mockFileName;

    @Before
    public void beforeEach() {
        latestResponse = null;
        latestException = null;
        mockFileName = null;
    }

    @Given("a variety of jokes from {string}")
    public void aVarietyOfJokesFrom(String fileName) {
        mockFileName = fileName;
    }

    @When("a joke is retrieved")
    public void aJokeIsRetrieved() {
        wireMockRule.start();

        wireMockRule.stubFor(get(urlEqualTo(JOKEAPI_URL))
                .willReturn(aResponse()
                        .withHeader("content-type", ContentTypes.APPLICATION_JSON)
                        .withBodyFile(mockFileName)));

        try {
            latestResponse = restTemplate.getForEntity(getUrl(), JokeDto.class);
        } catch (HttpClientErrorException e) {
            latestException = e;
        }

        wireMockRule.stop();
    }

    private String getUrl() {
        return "http://localhost:" + port + "/joke-service/joke";
    }

    @Then("the joke is {string}")
    public void theJokeIs(String joke) {
        assertNotNull(latestResponse.getBody());
        assertEquals(200, latestResponse.getStatusCode().value());
        assertEquals(joke, latestResponse.getBody().randomJoke().replaceAll("\n", " "));
    }

    @Then("an http error is returned with message {string}")
    public void anHttpErrorIsReturnedWithMessage(String errorMessage) {
        assertEquals(404, latestException.getStatusCode().value());
        assertEquals(errorMessage, latestException.getMessage());
    }
}
