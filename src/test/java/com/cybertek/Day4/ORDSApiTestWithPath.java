package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase{

    @Test
    public void test1()
    {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"limit\") = " + response.path("hasMore"));

        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryname = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryname);

        String thirdCountryLink = response.path("items[2].links[0].href");
        System.out.println("thirdCountryLink = " + thirdCountryLink);

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        List<Integer> allRegionIDs = response.path("items.region_id");

        for (Integer allRegionID : allRegionIDs)
        {
            System.out.println("regionId = " + allRegionID);
            assertEquals(2,allRegionID);
        }
    }


//print each name of IT_PROGS

    @Test
    public void nameOfItProgs()
    {
        Response response = given().accept(ContentType.JSON)
                .and().get("/employees");



        JsonPath jsonPath = response.jsonPath();
        List<String> countryNames = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");

        System.out.println(countryNames);

       /* List<Object> employees = jsonPath.getList("items");
        int numberOfEmployees = employees.size();

        for (int i = 0; i < numberOfEmployees; i++)
        {
           String jobs =response.path("items["+i+"].job_id");
           String names =response.path("items["+i+"].first_name");

           if(jobs.equals("IT_PROG"))
           {
               System.out.println(jobs + " " + names);
           }
        }*/






    }


















}
