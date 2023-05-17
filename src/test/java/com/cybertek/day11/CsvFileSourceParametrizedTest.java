package com.cybertek.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParametrizedTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void zipCodeTestWithFile(String stateArg,String cityArg,int zipCountArg)
    {
        System.out.println(stateArg);
        System.out.println(cityArg);
        System.out.println(zipCountArg);

        given().baseUri("https://api.zippopotam.us").accept(ContentType.JSON)
                .pathParam("state",stateArg)
                .pathParam("city",cityArg)
                .log().uri()
                .when().get("/us/{state}/{city}").then().statusCode(200)
                .body("places",hasSize(zipCountArg));


    }

}
