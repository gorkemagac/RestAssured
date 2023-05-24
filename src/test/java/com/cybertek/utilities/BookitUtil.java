package com.cybertek.utilities;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookitUtil {


    public static String getToken(String email, String password)
    {
        Map<String, Object> credentials = new LinkedHashMap<>();
        credentials.put("email",email);
        credentials.put("password",password);


        String token =  given().and().accept(ContentType.JSON)
                .and().when().queryParams(credentials)
                .and().get("https://cybertek-reservation-api-qa3.herokuapp.com/sign")
                .then()
                .statusCode(200).extract().jsonPath().getString("accessToken");

        String finalToken = "Bearer " + token;

        return  finalToken;

    }
}
