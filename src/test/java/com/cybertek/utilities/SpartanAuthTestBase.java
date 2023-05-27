package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {

    @BeforeAll
    public static void init()
    {
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://54.144.137.19:7000";

    }


}
