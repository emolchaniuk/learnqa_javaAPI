package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class apiCoreRequests {
    @Step("Make a GET request with token and auth cookie")
    public Response makeGetRequest(String url, String token, String cookie) {
        return given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .cookie("auth_sid", cookie)
                .get(url)
                .andReturn();
    }

        @Step("Make a GET request with  auth cookie only")
        public Response makeGetRequestWithCookie(String url, String cookie) {
            return given()
                    .filter(new AllureRestAssured())
                    .cookie("auth_sid", cookie)
                    .get(url)
                    .andReturn();
    }

    @Step("Make a GET request with auth token only")
    public Response makeGetRequestWithToken(String url, String token) {
        return given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .get(url)
                .andReturn();
    }

    @Step("Make a POST request")
    public Response makePostRequest(String url, Map<String, String> authData) {
        return given()
                .filter(new AllureRestAssured())
                .body(authData)
                .post(url)
                .andReturn();
    }

    @Step("Try to create user with invalid email - without @")
    public Response createUserWithInvalidEmail (String url, Map<String, String> userData ) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }


    @Step ("Try to create user without one parameter")
    public Response createUserWithoutOneField (String url, Map<String, String> userData) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }



    @Step ("Try to create user with too short username")
    public Response createUserWithShortUsername (String url,Map<String, String> userData ) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }

    @Step("Try to create user with too long username")
    public Response createUserWithLongUsername (String url, Map<String, String> userData) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }

    @Step("Try to login and see details of another user")
    public Response testGetUserDetailsAuthAsOtherUser (String url, Map<String, String> authData, String token, String cookie) {
        return given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .cookie("auth_sid", cookie)
                .body(authData)
                .get(url)
                .andReturn();
    }

    @Step("Try to edit data by unauthorized user")
    public Response makePutRequest(String url, Map<String, String> editData) {
        return given()
                .filter(new AllureRestAssured())
                .body(editData)
                .put(url)
                .andReturn();
    }


    @Step ("Try to Change Email Of User")
    public Response changeEmailOfUser (String url,Map<String, String> userData ) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }

    @Step ("Try to Change firstName Of User")
    public Response changeFirstNameOfUser (String url,Map<String, String> userData ) {
        return given()
                .filter(new AllureRestAssured())
                .body(userData)
                .post(url)
                .andReturn();
    }

    @Step("Try to change email")
    public Response makePutRequestToChangeEmail (String url, Map<String, String> authData, String token, String cookie) {
        return given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .cookie("auth_sid", cookie)
                .body(authData)
                .put(url)
                .andReturn();
    }

    @Step("Try to change email")
    public Response makePutRequestToChangeFirtsName (String url, Map<String, String> authData, String token, String cookie) {
        return given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .cookie("auth_sid", cookie)
                .body(authData)
                .put(url)
                .andReturn();
    }





 }
