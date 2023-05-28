package com.cybertek.utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanUtil2 {

   public static Map<String, Object> newSpartan = new LinkedHashMap<>();

    public static Response test1()
    {
        Faker faker = new Faker();
        newSpartan.put("name",faker.name().firstName().toString());
        newSpartan.put("gender",faker.options().option("Male", "Female"));
        newSpartan.put("phone",faker.phoneNumber().subscriberNumber(11));

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).and().body(newSpartan)
                .when().post("/api/spartans")
                .then().statusCode(201).extract().response();

        return response;


    }


}
