package Lesson3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedTests {


    //Тесты проверяют разные кейсы, но сами почти одинаковые

//    @Test
//    public void testHelloMethodWithoutName() {
//
//        JsonPath response = RestAssured
//                .get("https://playground.learnqa.ru/api/hello")
//                .jsonPath();
//
//        String answer = response.getString("answer");
//        assertEquals("Hello, someone", answer, "The answer is not expected");
//
//    }
//        @Test
//        public void testHelloMethodWithName () {
//            String name = "Username";
//
//            JsonPath response = RestAssured
//                    .given()
//                    .queryParam("name", name)
//                    .get("https://playground.learnqa.ru/api/hello")
//                    .jsonPath();
//
//            String answer = response.getString("answer");
//            assertEquals("Hello, " + name, answer, "The answer is not expected");
//
//
//        }

    @ParameterizedTest
    @ValueSource(strings = {"", "John", "Pete"})

    public void testHelloMethodWithoutName(String name ) {
        Map<String, String> queryParams = new HashMap<>();

        if (name.length() > 0 ) {
            queryParams.put("name", name);
        }

        JsonPath response = RestAssured
                .given()
                .queryParams(queryParams)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.getString("answer");
        String expectedName = (name.length() > 0 ) ? name: "someone2";
        assertEquals("Hello, " + expectedName, answer, "The answer is not expected");

    }
}

