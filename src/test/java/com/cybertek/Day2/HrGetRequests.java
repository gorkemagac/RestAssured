package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.144.137.19:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1()
    {
        Response response = get("/regions");
        //print the status code
        System.out.println(response.statusCode());
    }

    @Test
    public void test2()
    {
        Response response = RestAssured.get("/regions/2");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals(response.body().asString().contains("Americas"),true);
    }
























}
