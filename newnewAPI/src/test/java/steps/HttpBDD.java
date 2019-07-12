package steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class HttpBDD {


    public static void response200(String url) {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .statusCode(200);


    }

    //TODO: Ensure method below is dynamic

    public static void confirmWeatherStatus(String url, String day, String status) {

        try {
            when()
                    .get(url)
                    .then()
                    .statusCode(200)
                    .and()
                    .body("consolidated_weather.weather_state_name[" + day + "]", equalTo("" + status + ""));

        } catch (Exception e) {

        }
    }

    public static Boolean assertJSONData(String url, String city) {


        when()
                .get(url)
                .then()
                .statusCode(200)
                .and()
                .body("title", equalTo("" + city + ""));
        return true;


    }

    public static boolean dynamicWeatherStateCheck(String url) {

        String data = get(url)
                .path("consolidated_weather.weather_state_name[0]"); // Extracts Todays weather State name

        if (data.equals("Snow") ||
                data.equals("Sleet") ||
                data.equals("Hail") ||
                data.equals("Thunderstorm") ||
                data.equals("Heavy Rain") ||
                data.equals("Light Rain") ||
                data.equals("Showers") ||
                data.equals("Heavy Cloud") ||
                data.equals("Light Cloud") ||
                data.equals("Clear")) {
            // This compares the weather state name against the String
            return true;

        }else {

            return false;

        }


    }
}

