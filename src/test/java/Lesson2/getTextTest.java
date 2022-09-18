package Lesson2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class getTextTest {

    @Test
    public void testGetText () {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        //response.prettyPrint(); выводит не чистый текст, а вместе с тегами

        response.body().print();

    }
}
