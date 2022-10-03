package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAgentTest {


    @ParameterizedTest
    @CsvSource({
            "’Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30’, Mobile, No, Android",
            "'Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1', Mobile, Chrome, iOS",
            "'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)', Googlebot, Unknown, Unknown",
            "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0', Web, Chrome, No",
            "'Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1', Mobile, No, iPhone"
    })

    public void userAgentTest(String userAgent, String platform, String browser, String device) {
        Map<String, String> headers = new HashMap<>();
        //put our data into headers to send it into response
        headers.put("User-Agent", userAgent );
        headers.put("platform", platform);
        headers.put("browser", browser);
        headers.put("device", device);

        JsonPath response = RestAssured
                .given()
                .headers(headers)
                .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                .jsonPath();

        String userAgentR = response.getString("user_agent");
        String platformR = response.getString("platform");
        String browserR = response.getString("browser");
        String deviceR = response.getString("device");


        System.out.println(userAgentR);
        System.out.println(platformR);
        System.out.println(browserR);
        System.out.println(deviceR);


        assertEquals(headers.get("User-Agent"), userAgentR, "header 'user_agent' is not correct");
        assertEquals(headers.get("platform"), platformR, "header 'platform' is not correct");
        assertEquals(response.get("browser"), browserR, "header 'browser' is not correct");
        assertEquals(response.get("device"), deviceR, "header 'device' is not correct");






    }
}
