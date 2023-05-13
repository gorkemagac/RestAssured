package com.cybertek.Day6;

import com.cybertek.pojo.*;
import com.cybertek.utilities.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @Test
    public void employeeGet()
    {
        Employee employee1 = get("/employees").then().statusCode(200).extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println(employee1);
    }

    @Test
    public void regionPojoTest()
    {
       Regions regions =  get("/regions").then().statusCode(200).extract().response().as(Regions.class);
        assertThat(regions.getCount(),is(4));

        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();
        List<Region> items = regions.getItems();

        for (Region region : items)
        {
            regionIds.add(region.getRId());
            regionNames.add(region.getRegion_name());
        }

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));

    }



}
