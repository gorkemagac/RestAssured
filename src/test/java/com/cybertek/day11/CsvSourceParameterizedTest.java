package com.cybertek.day11;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;

import io.restassured.http.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.awt.geom.RectangularShape;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class CsvSourceParameterizedTest {


    @ParameterizedTest
    @CsvSource({"1,3,4","2,3,5","8,7,15","10,9,19"})
    public void testAddition(int num1,int num2, int sum)
    {
        assertThat(num1+num2,equalTo(sum));
    }

    @ParameterizedTest
    @CsvSource({"NY,New York","CO,Denver","VA,Fairfax","VA,Arlington","MA,Boston","NY,New York","MD,Annapolis"})
    public void testCity(String state,String city)
    {

     int placeNumber = given().baseUri("https://api.zippopotam.us").accept(ContentType.JSON)
            .pathParam("state",state)
                .pathParam("city",city)
                .and().when().get("/us/{state}/{city}").then().statusCode(200)
              .and().body("places.'place name'",everyItem(containsStringIgnoringCase(city)))
              .log().body()
              .extract().jsonPath().getList("places").size();

        System.out.println(placeNumber);


    }
}
