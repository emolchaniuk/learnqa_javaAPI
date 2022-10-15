package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;
import lib.apiCoreRequests;
import java.util.HashMap;
import java.util.Map;

public class UserEditTest extends BaseTestCase {

    private final apiCoreRequests apiCoreRequests = new apiCoreRequests();

    @Test
    public void testEditJustCreatedTest () {
    //generate user
        Map <String, String> userData = DataGenerator.getRegistrationData();

        JsonPath responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .jsonPath();

        String userId = responseCreateAuth.getString("id");

        //login
        Map <String, String> authData = new HashMap<>();
        authData.put("email", userData.get("email"));
        authData.put("password", userData.get("password"));

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        //edit data
        String newName = "Changed Name";
        Map <String, String> editData = new HashMap<>();
        editData.put("firstName", newName);

        Response responseEditUser = RestAssured
                .given()
                .header("x-csrf-token", this.getHeader(responseGetAuth, "x-csrf-token"))
                .cookie("auth_sid", this.getCookie(responseGetAuth, "auth_sid"))
                .body(editData)
                .put("https://playground.learnqa.ru/api/user/" + userId)
                .andReturn();

        //Get
        Response responseUserData = RestAssured
                .given()
                .header("x-csrf-token", this.getHeader(responseGetAuth, "x-csrf-token"))
                .cookie("auth_sid", this.getCookie(responseGetAuth, "auth_sid"))
                .get("https://playground.learnqa.ru/api/user/"+ userId)
                .andReturn();

        Assertions.assertJsonByName(responseUserData, "firstName", newName);

    }


    @Test
    public void testEditDataWithoutAuth () {

        String newName = "Changed Name";
        Map <String, String> editData = new HashMap<>();
        editData.put("firstName", newName);

        Response responseEditUser = apiCoreRequests
                .makePutRequest("https://playground.learnqa.ru/api/user/2", editData);

        Assertions.assertResponseCodeEquals(responseEditUser, 400);
    }

    @Test
    public void testEditDataByAuthOtherUser () {

        //login
        Map <String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");

        Response loginWithUser = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/login", authData);


        String header = loginWithUser.getHeader("x-csrf-token");
        String cookie = loginWithUser.getCookie("auth_sid");



        //edit data of another user
        String newName = "Changed Name";
        Map <String, String> editData = new HashMap<>();
        editData.put("firstName", newName);
        String urlforEdit = "https://playground.learnqa.ru/api/user/" + 5;

        Response responseEditUser = apiCoreRequests
                .makePutRequest(urlforEdit, editData);

        Assertions.assertResponseCodeEquals(responseEditUser, 400);

    }

    @Test
    public void testChangeEmailOfUser () {


        //login

        Map <String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");

        Response responseGetAuth = apiCoreRequests
                .changeEmailOfUser("https://playground.learnqa.ru/api/user/login", authData);

        String header = responseGetAuth.getHeader("x-csrf-token");
        String cookie = responseGetAuth.getCookie("auth_sid");

        //edit data
        String newEmail = "tkarambler.ru";
        Map <String, String> editData = new HashMap<>();
        editData.put("email", newEmail);

        Response responseEditUser = apiCoreRequests
                .makePutRequestToChangeEmail("https://playground.learnqa.ru/api/user/2", editData, header, cookie );

        Assertions.assertResponseCodeEquals(responseEditUser, 400);

        //Get
        Response responseUserData = apiCoreRequests
                .makeGetRequest("https://playground.learnqa.ru/api/user/2", header, cookie);

        Assertions.assertJsonByName(responseUserData, "email","vinkotov@example.com");

    }

    @Test
    public void testChangefirstNameOfUser () {


        //login

        Map <String, String> authData = new HashMap<>();
        authData.put("email", "tka7771@rambler.ru");
        authData.put("password", "1234");

        Response responseGetAuth = apiCoreRequests
                .changeFirstNameOfUser("https://playground.learnqa.ru/api/user/login", authData);

        String header = responseGetAuth.getHeader("x-csrf-token");
        String cookie = responseGetAuth.getCookie("auth_sid");

        //edit data
        String newFirstName = "n";
        Map <String, String> editData = new HashMap<>();
        editData.put("firstName", newFirstName);

        Response responseEditUser = apiCoreRequests
                .makePutRequestToChangeFirtsName("https://playground.learnqa.ru/api/user/45040", editData, header, cookie);


        Assertions.assertResponseCodeEquals(responseEditUser, 400);

        //Get
        Response responseUserData = apiCoreRequests
                .makeGetRequest("https://playground.learnqa.ru/api/user/45040", header, cookie);

        Assertions.assertJsonByName(responseUserData, "firstName","molchanyuk");

    }



}
