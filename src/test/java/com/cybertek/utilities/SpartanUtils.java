package com.cybertek.utilities;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtils {

    public static Map<String, Object> newSpartanJsonMap= new LinkedHashMap<>();

    public static Response createNewSpartan() {

        Faker faker = new Faker();

        newSpartanJsonMap.put("name", faker.name().firstName().toString());
        newSpartanJsonMap.put("gender", faker.random().nextBoolean() ? "Male" : "Female");
        newSpartanJsonMap.put("phone", faker.phoneNumber().subscriberNumber(11));

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(newSpartanJsonMap).log().all()
                .when().post("/api/spartans");

        return response;

    }
}
