package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParsing {

    @Test
    public void JsonParse () {


        JsonPath response = RestAssured
                 .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        String message2 = response.getString("messages[1]");
        System.out.println(message2);




    }

}
