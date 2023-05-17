package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @Test
    public void schemaValidation()
    {
        given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();
    }
    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cybertek/day10/allSpartansSchema.json")));

    }
    @Test
    public void postANewSpartanTest(){

        Map<String,Object>spartan=new HashMap<>();
        spartan.put("name","Kerim");
        spartan.put("gender","Male");
        spartan.put("phone",1231312323L);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .body(spartan)
                .post("/api/spartans")
                .then()
                .statusCode(201)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cybertek/day10/postANewSpartan.json")));

    }
}
