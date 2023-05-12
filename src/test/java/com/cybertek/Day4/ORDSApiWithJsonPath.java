package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @Test
    public void test1()
    {
        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

       String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


       List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        List<String> allCountryIdsWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(allCountryIdsWithRegionID2);
    }

    @Test
    public void test2()
    {
       Response response = given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

       List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        List<String> employeeNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(employeeNames);

        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingFirstNamePathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingFirstName = " + kingFirstNamePathMethod);


    }





}
