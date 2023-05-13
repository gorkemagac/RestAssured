package com.cybertek.Day5;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.reset;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;

public class ORDSHamcrestTest extends HRTestBase {

    @Test
    public void employeesTest()
    {
        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees").then().statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .body("items.email",containsInAnyOrder("DLORENTZ","VPATABAL","DAUSTIN","BERNST","AHUNOLD"));

    }
    @Test
    public void employeesTest2()
    {
      JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees").then().statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

               assertThat(jsonPath.getList("items.first_name"),hasSize(5));

               assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }

}
