package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class TestSpartanAPI {

    @Test
    public void testHello(){

        System.out.println("Hello world");

        //send request to below request url and save the response
        // GET http://50.17.84.26:8000/api/hello
        //RestAssured.get("http://50.17.84.26:8000/api/hello");
        // the result of sending request can be stored in Response object coming from RestAssured

        Response response = get("http://50.17.84.26:8000/api/hello");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        response.prettyPrint();

        Assertions.assertEquals(200,response.getStatusCode());



    }



}
