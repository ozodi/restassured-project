package com.cydeo.day05;


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

/**
 * Serialization :
 *          Process of Converting from Java Object to Json(or any other text)
 * De-Serialization:
 *          Process of Converting from Json(any text) to Java Object
 *
 */
public class PostRequestWithObjectTest extends SpartanTestBase{
    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     instead of providing above body in String,
     We want to be able to provide the body as java object(like map or POJO)
     and let any kind of Serialization library convert that into String for us
     while sending the request
     **/

    @Test
    public void testPostWithMap(){

        Map<String,Object> bodyMap  = new LinkedHashMap<>();
        bodyMap.put("name","API POST");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",1231231231l);

        System.out.println("bodyMap = " + bodyMap);


        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body( bodyMap ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);


    }

    @Test
    public void testPostWithMapWithRandomData(){

        Map<String ,Object> bodyMap = SpartanUtil.getRandomSpartanMapBody();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body( bodyMap ).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);


    }

    @Test
    public void  testUpdate1DataWithRandomBody(){

        // instead of guessing what id exists in my server
        // I will send request to get all spartans and get last json object id

        int lastId = get("/spartans").path("id[-1]");
        System.out.println("lastId = " + lastId);

        // prepare Updated body
        Map<String,Object> updatedBodyMap = SpartanUtil.getRandomSpartanMapBody();

        given()
                .log().all()
                .pathParam("id",lastId)
                .contentType(ContentType.JSON)
                .body(updatedBodyMap).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);

        //Homework: Send another get request to verify the body actually get updated.


    }



}
