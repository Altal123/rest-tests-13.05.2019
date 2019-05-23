import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class WeatherTest {

    @Test
    public void getWeatherPerCityTest(){

        //Getting Lviv`s ID
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/";
//        RestAssured.basePath = "search.php";

//        RestAssured.baseURI = "https://pinformer.sinoptik.ua/search.php?lang=ua&return_id=1&q=Lviv";

        ValidatableResponse response = RestAssured.given()
                .basePath("search.php")
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", "Lviv")
//                .log()
//                .uri()
                .get()
                .then()
//                .log().all()
                .statusCode(200);

        String cityResponse = response.extract().asString();
//        String cityId = cityResponse.substring(cityResponse.lastIndexOf("|")+1);
//        System.out.println("cityId" + cityId);
        System.out.println(cityResponse);

        String[] num = cityResponse.split("\\|");
        String id = num[num.length-1];
        System.out.println("Lviv`s ID: " + id);

        //Getting Lviv`s wheather by Id
//        RestAssured.baseURI = "https://pinformer.sinoptik.ua/pinformer4.php";
//        String endpoint = "https://pinformer.sinoptik.ua/pinformer4.php";

        response = RestAssured.given()
                .param("type", "js")
                .param("lang", "ua")
                .param("id", id)
                .log()
                .uri()
                .get("pinformer4.php")
                .then()
                .contentType(ContentType.JSON)
                .log().all()
                .statusCode(200)//Это обычный ассерт
                .body("any { it.key == '{pcity}' }", is (true))  //GroovyPath with hamcrest Matcher
                .body("'{pcity}'", is (id));  //JsonPath with hamcrest Matcher

        System.out.println(response.extract().path("'{city}'"));
//        System.out.println(response.extract().response().jsonPath().getString("$"));
        System.out.println(response.extract().response().jsonPath().getString("'{temp}'"));


        //2 языка запросов для JSON: JsonPath и GroovyPath
    }

}
