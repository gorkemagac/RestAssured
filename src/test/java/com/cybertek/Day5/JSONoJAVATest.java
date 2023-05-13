package com.cybertek.Day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONoJAVATest extends SpartanTestBase {

    @Test
    public void oneSpartanToMap() {
        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();


        Map<String, Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));
    }

    @Test
    public void getAllSpartan() {

       Response response = get("/api/spartans").then().statusCode(200).extract().response();

       List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);
    }


}