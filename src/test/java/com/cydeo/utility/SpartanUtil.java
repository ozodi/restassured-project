package com.cydeo.utility;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {


    public static Map<String,Object> getRandomSpartanMapBody(){

        // Creat random data instead of always creating with same body
        // oprionally: create a utility out of it.
        Faker faker = new Faker();

        Map<String,Object> bodyMap  = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName()    );
        bodyMap.put("gender", faker.demographic().sex()  );
        bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999L) );

        System.out.println("bodyMap = " + bodyMap);

        return bodyMap;
    }

}
