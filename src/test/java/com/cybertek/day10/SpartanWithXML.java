package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanWithXML extends SpartanAuthTestBase {

    @Test
    public void getSpartanXml()
    {
        given().accept(ContentType.XML).auth().basic("admin","admin").and().
        when().get("/api/spartans")
                        .then().statusCode(200).contentType("application/xml;charset=UTF-8")
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male")).log().all();

    }
    @Test
    public void testXmlPath()
    {
        Response response = given().accept(ContentType.XML).auth().basic("admin","admin").and().
                when().get("/api/spartans");

      XmlPath xmlPath = response.xmlPath();

       String name = xmlPath.getString("List.item[0].name");
       int id = xmlPath.getInt("List.item[2].id");

        System.out.println(name);
        System.out.println(id);

        List<String> names = xmlPath.getList("List.item.name");

        System.out.println(names);

    }
}
