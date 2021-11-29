package com.cydeo.day04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanPutPatchDeletePracticeTest extends SpartanTestBase{

    @Test
    public void testUpdate(){

        /**
         PUT /spartans/{id}
         content type is json
         body is
         {
         "name":"New Body",
         "gender":"Male",
         "phone":5555555555
         }
         *
         * expect status 204
         */

        String updatedBody = "{\n" +
                "             \"name\":\"New Body\",\n" +
                "             \"gender\":\"Male\",\n" +
                "             \"phone\":5555555555\n" +
                "         }" ;

        given()
                .log().all()
                .pathParam("id", 5)
                .contentType(ContentType.JSON)
                .body(updatedBody).
                when()
                .put("/spartans/{id}").
                then()
                .log().all()
                .statusCode( equalTo(204) ) ;
        // Homework : make another get request to check its actually updated.
    }


    @Test
    public void testPartialUpdate(){

        /**
         PATCH /spartans/{id}
         content type is json
         body is
         {
         "name":"Updated Name",
         }
         *
         * expect status 204
         */

        String updatedBody = "{\"name\":\"Updated Name\"}" ;

        given()
                .log().all()
                .pathParam("id", 5)
                .contentType(ContentType.JSON)
                .body(updatedBody).
                when()
                .patch("/spartans/{id}").
                then()
                .log().all()
                .statusCode( equalTo(204) ) ;
        // Homework : make another get request to check its actually updated.

    }

    @Test
    public void testDelete(){
        /**
         DELETE /spartans/{id}
         */
        // Keep in mind that , once you delete the data , your test will fail
        // in order to avoid that
        // we can send request to GET /spartans endpoint
        // and get the existing id in the system
        // in this particular case , we chose to delete last data that exists in system
        // You can decide anything you want , for example get first one or  get random id instead
//       Response response =  get("/spartans") ;
//       int lastId = response.path("id[-1]") ;
        int lastId =  get("/spartans").path("id[-1]") ;
        // Homework : challenge yourself to create new data instead and use newly generated data

        given()
                .log().all()
                .pathParam("id", lastId).
                when()
                .delete("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204) ;
        // we can additionally send another get request to this id and expect 404
        // to make sure it actually worked.

    }


}