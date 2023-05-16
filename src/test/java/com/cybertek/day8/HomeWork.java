package com.cybertek.day8;

import com.cybertek.utilities.DBUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomeWork {
    @BeforeAll
    public static void init()
    {
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com/";

    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";
    @Test
    void test1()
    {
        Response response = given().header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
                .and().pathParam("id",2907)
                .when().get("/api/students/{id}")
                .then().statusCode(200)
                .extract().response();


        JsonPath jsonPath = response.jsonPath();

        String nameApi =  jsonPath.getString("firstName");
        String roleAPi =  jsonPath.getString("role");

        System.out.println(nameApi);
        System.out.println(roleAPi);

        Response response1 = given().header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
                .and().pathParam("number",8)
                .when().get("/api/batches/{number}")
                .then().statusCode(200)
                .extract().response();


        JsonPath jsonPath1 = response1.jsonPath();

        int batchNoApi = jsonPath1.getInt("number");
       String teamNameAPi = jsonPath1.getString("teams[4].name");


        System.out.println(teamNameAPi);


    }












}
