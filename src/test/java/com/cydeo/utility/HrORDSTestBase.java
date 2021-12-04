package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationRegistry.reset;

public class HrORDSTestBase {

    /**
     * This classs will serve as Base Class to set up BaseURI and BasePath
     * and clean up after all test for any Spartan related test
     *
     */
    // set up
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://50.17.84.26:1000";
        RestAssured.basePath = "/ords/hr";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }




}
