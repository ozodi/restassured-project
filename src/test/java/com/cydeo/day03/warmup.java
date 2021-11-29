package com.cydeo.day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class warmup {

    @Test
    public void testSome(){

        Response response = get("http://api.open-notify.org/astros.json");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        response.prettyPrint();





    }


}
