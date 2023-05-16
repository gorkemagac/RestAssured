package com.cybertek.day8;
import com.cybertek.utilities.BookitUtil;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.*;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class BookItTest {

    @BeforeAll
    public static void init()
    {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";

    @Test
    public void testAuth1()
    {
        given().header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
                .when().get("/api/campuses")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void getToken()
    {
        System.out.println(BookitUtil.getToken("mforkerh@dailymail.co.uk", "mariusforker"));
    }


}
