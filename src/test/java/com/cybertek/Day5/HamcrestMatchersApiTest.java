package com.cybertek.Day5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    @Test
    public void test1()
    {
       given().accept(ContentType.JSON)
               .and().pathParam("id",15)
               .when().get("http://54.144.137.19:8000/api/spartans/{id}")
               .then().statusCode(200).and().contentType("application/json")
               .and()
               .body("id",equalTo(15),"name",is("Meta"),"gender",is("Female"),"phone",is(1938695106));
    }

}
