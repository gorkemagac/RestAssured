package com.cybertek.Day3;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.startsWith;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init()
    {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.144.137.19:1000/ords/hr";
    }

    @Test
    public void test1()
    {
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }
    @Test
    public void test2()
    {
        Response response= given().accept(ContentType.JSON).and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .log().all().when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();


        List<String> allJobIDs = response.path("items.job_id");

        for (String allJobID : allJobIDs)
        {
            System.out.println("allJobID = " + allJobID);
            assertEquals("IT_PROG",allJobID);
        }
    }


}
