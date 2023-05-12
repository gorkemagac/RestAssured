package com.cybertek.Day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init()
    {
        baseURI = "https://api.training.cydeo.com";
    }

    @Test
    public void test1()
    {
        given().accept(ContentType.JSON)
                .and().pathParam("id",4)
                .when().get("/student/{id}").then().statusCode(200).and().assertThat().header("Date",notNullValue())
                        .and().assertThat().body("students[0].firstName" ,is("ertret"))
                .body("students[0].batch" ,is(1))
                        .body("students[0].section" ,equalTo("100000"))
                                .body("students[0].contact.emailAddress" ,is("324234@email.com"))
                                        .body("students[0].company.companyName" ,is("ewttwre"));




    }

    @Test
    public void test2()
    {
        given().accept(ContentType.JSON).and().pathParam("id",6)
                .when().get("/teacher/{id}").then().statusCode(200).and().contentType("application/json;charset=UTF-8")
                .and().header("server",is("envoy")).and().header("Date",notNullValue())
                .and().assertThat().body("teachers[0].firstName",is("Tory"))
                .body("teachers[0].lastName",is("Reb"))
                .body("teachers[0].gender",is("Male"));
    }


    @Test
    public void teachersTest()
    {
      given().accept(ContentType.JSON).when().get("/teacher/all")
              .then().statusCode(200)
              .and().body("teachers.firstName",hasItems("Erik","Ron","Valter"));


    }



}
