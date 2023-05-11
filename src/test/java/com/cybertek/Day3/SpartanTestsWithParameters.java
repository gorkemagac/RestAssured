package com.cybertek.Day3;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init()
    {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.144.137.19:8000";
    }

    @Test
    public void test1()
    {
        Response response = given().accept(ContentType.JSON).and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));
    }

    @Test
    public void test2()
    {
        Response response = given().accept(ContentType.JSON).and().pathParam("id",500)
                .when().get("/api/spartans/{id}");

        Assertions.assertEquals(404,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));
    }

    @Test
    public void test3()
    {
        Response response = given().accept(ContentType.JSON).and().queryParam("nameContains","e")
                .queryParam("gender","Female")
                .when().get("/api/spartans/search");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @Test
    public void test4()
    {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");


        Response response = given().log().all().accept(ContentType.JSON).and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }























}
