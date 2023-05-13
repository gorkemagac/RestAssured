package com.cybertek.Day6;

import com.cybertek.pojo.Regions;
import com.cybertek.pojo.Search;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;

public class SpartanPojoGetRequestTest extends SpartanTestBase
{
    @Test
    public void oneSpartanPojo()
    {
      Response response = given().accept(ContentType.JSON).and().pathParam("id",15)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
              .log().all()
                .extract().response();

        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());

        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());
    }
    @Test
    public void spartanSearchWithPojo()
    {
        JsonPath jsonPath = given().accept(ContentType.JSON).and().queryParams("nameContains","a","gender","Male")
                .when().get("api/spartans/search")
                .then().statusCode(200).extract().jsonPath();

        Spartan s1 = jsonPath.getObject("content[0]",Spartan.class);

        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());
    }
    @Test
    public void test3()
    {
        Response response = given().accept(ContentType.JSON).and().queryParams("nameContains","a","gender","Male")
                .when().get("api/spartans/search")
                .then().statusCode(200).extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());
    }


    @Test
    public void test4()
    {
        List<Spartan> spartanList = given().accept(ContentType.JSON).and().queryParams("nameContains","a","gender","Male")
                .when().get("api/spartans/search")
                .then().statusCode(200).extract().jsonPath().getList("content",Spartan.class);

        System.out.println(spartanList.get(1).getName());

    }

















}
