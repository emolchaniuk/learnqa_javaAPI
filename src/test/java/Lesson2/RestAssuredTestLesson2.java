package Lesson2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredTestLesson2 {

    @Test
    public void restAssuredTestL2() {

        Map<String, Object> body = new HashMap<>();
        body.put("param1","value1");
        body.put("param2","value2");


        //GET method
//    Response response = RestAssured
//            .given()
//            .queryParam("param1", "value1")
//            .queryParam("param2", "value2")
//            .get("https://playground.learnqa.ru/api/check_type")
//            .andReturn();
//
//    response.print();

        //POST method
        Response response = RestAssured
                .given()
                //.body("param1=value1&param2=value2")  - способ передать параметры в сыром виде
                // .body("{\"param1\":\"value1\", \"param2\":\"value2\"}")  - передать параметры в виде json !!!мучительно больно
                .body(body) //передать параметры в виде json - норм
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        response.print();

        }
}
