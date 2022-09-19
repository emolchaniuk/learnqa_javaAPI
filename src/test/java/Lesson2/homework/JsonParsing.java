package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonParsing {

    @Test
    public void JsonParse () {


        Response response = RestAssured
                 .get("https://playground.learnqa.ru/api/get_json_homework")
                .andReturn();

        response.print();


    }

}
