package stepsdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import lombok.extern.slf4j.Slf4j;

import static net.serenitybdd.rest.SerenityRest.with;

@Slf4j
public class StepDefinitions {
    private static final String APIKEY = "76a78216af765a6392fcf44d4712c363";

    //    @When("^Sent request to openweathermap for \"([^\"]*)\"$") //for specific city
    @When("Sent request to openweathermap for Pristina - GET")
    public void sentRequestToOpenweathermapForPristina() {
        log.info("Call OpenWheatherMap API step for 7 days weather forecast");
        getResponse("42.6629","21.1655");
    }

    @When("Sent request to openweathermap for London - GET")
    public void sentRequestToOpenweathermapForLondon() {
        log.info("Call OpenWheatherMap API step for 7 days weather forecast");
        getResponse("51.5072","0.1276");
    }

    @Then("Server returns correct weather information for 7 days for Pristina")
    public void server_returns_weather_forecastPristina() {
        log.info("" + getStatus("42.6629", "21.1655"));
    }

    @Then("Server returns correct weather information for 7 days for London")
    public void server_returns_weather_forecastLondon() {
        log.info("" + getStatus("51.5072","0.1276"));
    }

    private int getStatus(String lat, String lon) {
        int currentResponse = 0;
        String BASE_STATION_URI = "http://api.openweathermap.org/data/2.5/onecall";

        currentResponse = with().baseUri(BASE_STATION_URI)
                .queryParam("lat", lat) //coordinated for Pristina
                .queryParam("lon", lon)
                .queryParam("appid", APIKEY)
                .get()
                .statusCode();
        log.info("Response status we got: " + currentResponse);
        return currentResponse;

    }

    private void getResponse(String lat, String lon) {
        ResponseOptions<Response> currentResponse = null;
        String BASE_STATION_URI = "http://api.openweathermap.org/data/2.5/onecall";

        currentResponse = with().baseUri(BASE_STATION_URI)
                .queryParam("lat", lat) //coordinated for Pristina city
                .queryParam("lon", lon)
                .queryParam("appid", APIKEY)
                .get()
                .andReturn();
        log.info("Response we got: " + currentResponse.getBody().prettyPrint()); //in the API inside the "daily"
        // data element you can see weather forecast for 7 days
    }
}
