package com.cydeo.day05;

import com.cydeo.pojo.Spartan;
import org.junit.jupiter.api.Test;

import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostPutRequestWithPOJO extends SpartanTestBase{

    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     We learned how to represent json body using Map
     and let Jackson data-bind library to serialize it into json when sending to the server

     Another common way of representing json data is using
     special purpose Java Object created from A class
     that have fields matched to json keys
     and have getters and setters
     This type of Object , sole purpose is to represent data ,
     known as POJO , plain old java object
     The class to create POJO known as POJO class
     It's used for creating POJO , just like you create any other object

     for example: in order to represent a jason data with 3 keys, name, gender, phone
     we can create a java class with 3 with

     **/


    @Test
    public void testPostWithPOJOBody(){

        Spartan sp1 = new Spartan("B23 POJO","Male",5555555555l);
        System.out.println("sp1 = " + sp1);

        // now we are going to use this body in post request
        // and expected jackson-databind to convert that to json string when sending as body
        // so it can be added to

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body( sp1 ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);



    }






}
