package com.cybertek.day8;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
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
public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @Test
    public void test1()
    {
        given().accept(ContentType.JSON).when().
        get("/api/spartans").then().statusCode(401).log().all();
    }

    @Test
    public void testAdmin()
    {
        given().auth().basic("admin","admin").and().accept(ContentType.JSON).when().
                get("/api/spartans").then().statusCode(200).log().all();
    }

    @Test
    public void testEditorDelete()
    {
        given().auth().basic("editor","editor").and().accept(ContentType.JSON).when().
                and().pathParam("id",30).when().
                delete("/api/spartans/{id}").then().statusCode(403).log().body();
    }

    @Test
    public void testAdminCreate()
    {
        Spartan spartan = new Spartan();
        spartan.setName("Gorkem");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

       Response response = given().auth().basic("admin","admin").and().accept(ContentType.JSON).when()
                .and().contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));
    }
    @Test
    public void testAdminRead()
    {

        given().auth().basic("admin","admin").and().accept(ContentType.JSON)
                .when().and().pathParam("id",103)
                .then()
                .when().get("/api/spartans/{id}").then().statusCode(200).log().body();
    }
    @Test
    public void testAdminUpdate()
    {
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","Gorkem");
        putRequestMap.put("gender","Female");
        putRequestMap.put("phone",8877445596L);

        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id",100)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void testAdminDelete()
    {

        given().auth().basic("admin","admin").contentType(ContentType.JSON)
                .and().when().pathParam("id",103)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void testEditorCreate()
    {
        Spartan spartan = new Spartan();
        spartan.setName("Furkan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        Response response = given().auth().basic("editor","editor").and().accept(ContentType.JSON).when()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));
    }
    @Test
    public void testEditorRead()
    {

        given().auth().basic("editor","editor").and().accept(ContentType.JSON)
                .when().and().pathParam("id",106)
                .then()
                .when().get("/api/spartans/{id}").then().statusCode(200).log().body();;
    }
    @Test
    public void testEditorUpdate()
    {
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","Furkan");
        putRequestMap.put("gender","Female");
        putRequestMap.put("phone",8877445596L);


        given().auth().basic("editor","editor").contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id",106)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void testUserCreate()
    {
        Spartan spartan = new Spartan();
        spartan.setName("Furkan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        Response response = given().auth().basic("user","user").and().accept(ContentType.JSON).when()
                .and().contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(403));
        assertThat(response.contentType(),is("application/json;charset=UTF-8"));
    }
    @Test
    public void testUserRead()
    {

        given().auth().basic("user","user").and().accept(ContentType.JSON)
                .when().and().pathParam("id",106)
                .then()
                .when().get("/api/spartans/{id}").then().statusCode(200).log().body();;
    }
    @Test
    public void testUserUpdate()
    {
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","Furkan");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8877445596L);


        given().auth().basic("user","user").contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id",106)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(403);
    }

    @Test
    public void testUserDelete()
    {

        given().auth().basic("user","user").contentType(ContentType.JSON)
                .and().when().pathParam("id",107)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(403);

    }

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able to take all CRUD (Create, Read, Update, Delete)
            Editor should be able to take all CRUD (Create, Read, Update, Delete)
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all*/

}
