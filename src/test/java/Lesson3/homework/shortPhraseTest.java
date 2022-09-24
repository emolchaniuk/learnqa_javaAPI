package Lesson3.homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class shortPhraseTest {

        @ParameterizedTest
        @ValueSource(strings = {"It's long string for first test", "It's short"})

        public void testshortString(String anyString ) {
        Map<String, String> queryParams = new HashMap<>();

        JsonPath response = RestAssured
                .given()
                .queryParams(queryParams)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String expectedString = (anyString.length() > 15 ) ? anyString: "someone2";
        assertTrue(anyString.length() > 15, "The length of phrase is less than 15 symbols");

        }
    }
