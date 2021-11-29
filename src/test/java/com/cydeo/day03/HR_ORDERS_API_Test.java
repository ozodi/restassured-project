package com.cydeo.day03;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationRegistry.reset;


public class HR_ORDERS_API_Test {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://50.17.84.26:1000";
        RestAssured.basePath = "/ords/hr/";

    }

    @AfterAll
    public static void teardown(){

        reset();
    }

    @Test
    public void testGetAllJobs(){
        // Get  / jobs

        Response response = given()
                            .log().all()
                        .when()
                            .get("/jobs");
        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        // json filed count value
        int countValue = response.path("count");
        Assertions.assertEquals(19,countValue);

        /*
         6. save second job_id into String
         7. print 4th mix_salary and
         8. save all of the job_title into List<String>
         */

        String secondJobId  = response.path("items[1].job_id");
        System.out.println("secondJobId = " + secondJobId);

        int fourthMinSalary = response.path("items[3].min_salary");
        System.out.println("fourthMinSalary = " + fourthMinSalary);

        List<String> allJobTitle = response.path("items.job_title");
        System.out.println("allJobTitle = " + allJobTitle);

    }


    @Test
    public void testJobsWithQueryParam(){

        Response response = given()
                            .log().all()
                            .queryParam("limit",5).
                    when()
                            .get("/jobs");

        response.prettyPrint();

        int actualCount = response.path("count");
        Assertions.assertEquals(5, actualCount);

        String lastJobId = response.path("items[-1].job_id");
        // String lastJobId  = response.path("items[4].job_id");
        System.out.println("lastJobId = " + lastJobId);


        Assertions.assertEquals("AD_VP",lastJobId);
    }


    @Test
    public void testSingleJobWithPathParam(){

        Response response = given()
                            .log().all()
                            .pathParam("job_id","AD_VP")
                        .when()
                            .get("/jobs/{job_id}")
                            .prettyPeek()//it will print entire response and return same Response object

                                ;
        //response.prettyPrint();
        //response.prettyPeek();
        String actualJobTitle = response.path("job_title");

        Assertions.assertEquals("Administration Vice President",actualJobTitle);

        // There is one more way to print your response using prettyPeek
        // prettyPeek, just like  prettyPrint it will print the result.
        //              unlike prettyPrint, it will return  same Response object
        //              so you can keep chaining the methods


    }



}
