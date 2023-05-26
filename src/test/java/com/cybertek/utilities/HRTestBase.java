package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {
    @BeforeAll
    public  static void init()
    {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.144.137.19:1000/ords/hr";
    }

}
