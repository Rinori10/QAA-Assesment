package stepsdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import lombok.extern.slf4j.Slf4j;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.with;

@Slf4j
public class StepDefinitions {
    private static final String APIKEY = "76a78216af765a6392fcf44d4712c363";

    //    @When("^Sent request to openweathermap for \"([^\"]*)\"$") //for specific city
    @When("Sent request to openweathermap - GET")
    public void sentRequestToOpenweathermapFor() {
        log.info("Call OpenWheatherMap API step for 7 days weather forecast");
        getResponse();
    }

    @Then("Server returns correct weather information for 7 days")
    public void server_returns_weather_forecast() {
        log.info("" + getStatus());
    }

    private void getResponse() {
        ResponseOptions<Response> currentResponse = null;
        String BASE_STATION_URI = "http://api.openweathermap.org/data/2.5/onecall";

        currentResponse = with().baseUri(BASE_STATION_URI)
                .queryParam("lat", "19.0760") //coordinated for mumbai city (unimplemented for 4 cities...to be done)
                .queryParam("lon", "72.8777")
                .queryParam("appid", APIKEY)
                .get()
                .andReturn();
        log.info("Response we got: " + currentResponse.getBody().prettyPrint()); //in the API inside the "daily"
        // data element you can see weather forecast for 7 days
    }

    private int getStatus() {
        int currentResponse = 0;
        String BASE_STATION_URI = "http://api.openweathermap.org/data/2.5/onecall";

        currentResponse = with().baseUri(BASE_STATION_URI)
                .queryParam("lat", "19.0760") //coordinated for mumbai city (unimplemented for 4 cities...to be done)
                .queryParam("lon", "72.8777")
                .queryParam("appid", APIKEY)
                .get()
                .statusCode();
        log.info("Response status we got: " + currentResponse);
        return currentResponse;




    }

}
