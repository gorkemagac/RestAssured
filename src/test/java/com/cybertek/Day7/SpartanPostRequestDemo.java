package com.cybertek.Day7;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import com.cybertek.utilities.SpartanUtil2;
import com.cybertek.utilities.SpartanUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

    @Test
    public void postMethod1()
    {
        String requestJsonBody = "{\"gender\":\"Male\",\n" +
            "\"name\":\"Severus\",\n" +
            "\"phone\":8877445596}";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

    }

    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){

        //create a map to keep request json body information
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name","Severus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",8877445596L);


        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();
    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){
        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("SeverusSpartan");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("SeverusSpartan"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //this example we implement serialization with creatin spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("BruceWayne");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);
        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }
    @DisplayName("POST with MAP to JSON, use SpartanUtils")
    @Test
    public void createNewSpartan() {

        Response response = SpartanUtils.createNewSpartan();


        // verify status code
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(201));
        MatcherAssert.assertThat(response.contentType(), Matchers.is("application/json"));

        // verify success message
        String expectedResponseMessage = "A Spartan is Born!";
        String actualResponseMessage = response.path("success");
        MatcherAssert.assertThat(actualResponseMessage, Matchers.is(expectedResponseMessage));

        // verify name, gender and phone
        MatcherAssert.assertThat(response.path("data.name"), Matchers.is(SpartanUtils.newSpartanJsonMap.get("name")));
        MatcherAssert.assertThat(response.path("data.gender"), Matchers.is(SpartanUtils.newSpartanJsonMap.get("gender")));
        MatcherAssert.assertThat(response.path("data.phone").toString(), Matchers.is(SpartanUtils.newSpartanJsonMap.get("phone")));

        response.prettyPrint();
    }



}
