package com.cybertek.day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class FormulaOneXMLTest {

    @BeforeAll
    public static void setUp()
    {
        baseURI = "https://ergast.com";
        basePath="/api/f1";
    }

    @Test
    public void testXmlPath()
    {
   Response response = given().pathParam("driver","alonso").when().
        get("/drivers/{driver}").then().statusCode(200).log().all().extract().response();

XmlPath xmlPath = response.xmlPath();

        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        String DateOfBirth = xmlPath.getString("MRData.DriverTable.Driver.DateOfBirth");
        String Nationality = xmlPath.getString("MRData.DriverTable.Driver.Nationality");

        System.out.println(givenName);
        System.out.println(familyName);
        System.out.println(DateOfBirth);
        System.out.println(Nationality);

       String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println(driverId);

       String code = xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println(code);

        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println(url);
    }
}
