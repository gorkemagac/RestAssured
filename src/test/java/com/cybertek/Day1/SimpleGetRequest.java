package com.cybertek.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String url = "http://54.144.137.19:8000/api/spartans";

    @Test
    public void test1()
    {
       Response response = RestAssured.get(url);
        System.out.println(response.statusCode());

        System.out.println(response.prettyPrint());

    }

}
