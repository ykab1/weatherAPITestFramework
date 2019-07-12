package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WeatherDefSteps {
    @When("^A JSON GET request is sent to the Metaweather server$")
    public void aJSONGETRequestIsSentToTheMetaweatherServer() {

        given().contentType(ContentType.JSON); // instantiate the Rest-assured Gherkin

    }

    @Then("^The weather in London on this date will appear with status$")
    public void theWeatherInLondonOnThisDateWillAppearWithStatus(DataTable table) {
        List<List<String>> data = table.raw(); // we store data from the data in a nested List of String data type
        HttpBDD
                //Call each Date by indexing the Data table which provides us with the index for the JSon file

                .confirmWeatherStatus("https://www.metaweather.com/api/location/44418/", // if expected status for todays weather matches
                        data.get(1).get(0), data.get(1).get(2));
        HttpBDD
                .confirmWeatherStatus("https://www.metaweather.com/api/location/44418/", // if expected status for tomorrows weather matches
                        data.get(2).get(0), data.get(2).get(2));
        HttpBDD
                .confirmWeatherStatus("https://www.metaweather.com/api/location/44418/",// if expected status for tomorrow +1 weather matches
                        data.get(3).get(0), data.get(3).get(2));
        HttpBDD
                .confirmWeatherStatus("https://www.metaweather.com/api/location/44418/", // if expected status for tomorrow+2 weather matches
                        data.get(4).get(0), data.get(4).get(2));
    }

    @Then("^Data for the following cities is returned$")
    public void dataForTheFollowingCitiesIsReturned(DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        // Verify weather in cardiff using WOE
        assertThat("Assert Cardiff weather data",
                HttpBDD.assertJSONData("https://www.metaweather.com/api/location/" + data.get(1).
                        get(1) + "/", data.get(1).get(0)), is(true));
        assertThat("Assert Cardiff weather data",
                HttpBDD.assertJSONData("https://www.metaweather.com/api/location/" + data.get(2).
                        get(1) + "/", data.get(2).get(0)), is(true));
        assertThat("Assert Cardiff weather data",
                HttpBDD.assertJSONData("https://www.metaweather.com/api/location/" + data.get(3).
                        get(1) + "/", data.get(3).get(0)), is(true));
        assertThat("Assert Cardiff weather data",
                HttpBDD.assertJSONData("https://www.metaweather.com/api/location/" + data.get(4).
                        get(1) + "/", data.get(4).get(0)), is(true));
        assertThat("Assert Cardiff weather data",
                HttpBDD.assertJSONData("https://www.metaweather.com/api/location/" + data.get(5).
                        get(1) + "/", data.get(5).get(0)), is(true));


    }

    @Then("^server Status response is (\\d+) meaning it Ok$")
    public void serverStatusResponseIsMeaningItOk(int arg0) {
        // Asserts whether the server response is 200
        HttpBDD.response200("https://www.metaweather.com/api/location/44418/");
    }

    @Then("^At least one weather state is displayed$")
    public void atLeastOneWeatherStateIsDisplayed() {
        assertThat("At Least one weather state listed in documentation will be displayed for todays weather state",
                HttpBDD.dynamicWeatherStateCheck("https://www.metaweather.com/api/location/44418/"), is (true));

    }
}
