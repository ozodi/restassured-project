package com.cydeo.day06;

import com.cydeo.pojo.Job;
import com.cydeo.utility.HrORDSTestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.cydeo.pojo.SpartanWithID;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class HR_ORDS_Test extends HrORDSTestBase {

    // Get /jobs
    @Test
    public void testJobs(){

        JsonPath jp = given()
                            .log().uri().
                    when()
                            .get("/jobs")
                            //.prettyPeek()
                            .jsonPath();

        // we want to de-serialize first json object from json array

        Job job1 = jp.getObject("items[0]",Job.class);
        System.out.println("job1 = " + job1);

        List<Job> allJobs = jp.getList("items",Job.class);
        System.out.println("allJobs = " + allJobs);

        // As a Homework
        // find out all Jobs name with min salary more than 5000

        String title_with_5 = "";

        for (int i = 0; i < allJobs.size(); i++) {

            Job job2 = jp.getObject("items["+i+"]",Job.class);
            if (job2.getMinSalary()>5000) {
                title_with_5 = job2.getJobTitle();
            }
            System.out.println("title_with_5 = " + title_with_5);
        }


    }

}
