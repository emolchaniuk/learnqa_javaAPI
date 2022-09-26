package Lesson3.homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HeaderMethodTest {

    @Test
    public void HeaderMethodTest (){


        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();


        String headers = String.valueOf(response.getHeaders());
        System.out.println(headers);

        //1 способ
        assertEquals("Some secret value", response.getHeader("x-secret-homework-header"), "Headers has another value");

        //2 способ
        String headerValue = new String("Some secret value");
        assertTrue(response.getHeader("x-secret-homework-header").equals(headerValue), "wrong header value");





    }

}
