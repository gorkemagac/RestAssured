package com.cybertek.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import com.cybertek.utilities.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class MockApi {

    @Test
    public void test1()
    {
        given().baseUri("https://18cd0ae0-1f24-48b5-839b-9e0eeb45f9e0.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when().get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));
    }
    @Test
    public void test2()
    {
        given().baseUri("https://18cd0ae0-1f24-48b5-839b-9e0eeb45f9e0.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when().get("/employees")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));
    }

}
