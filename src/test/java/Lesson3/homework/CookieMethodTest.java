package Lesson3.homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieMethodTest {

    @Test
    public void cookieMethoodTest () {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        String cookie = response.getCookie("HomeWork");
        System.out.println(cookie);

        assertEquals("hw_value", response.getCookie("HomeWork"), "Cookie has another value");


    }

}
