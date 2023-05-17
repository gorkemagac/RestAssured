package com.cybertek.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class ParametrizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5})
    public void testMultipleNumbers(int number)
    {
        System.out.println("number = " + number);
        Assertions.assertTrue(number>5);
    }
    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name)
    {
        System.out.println("Names = " + name);
    }

    @ParameterizedTest
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035,22036})
    public void test(int zipcodes)
    {
        given().baseUri("https://api.zippopotam.us").accept(ContentType.JSON)
                .pathParam("zipcode",zipcodes)
                .log().all()
                .when().get("/us/{zipcode}")
                .then().log().all().statusCode(200);
    }
}
