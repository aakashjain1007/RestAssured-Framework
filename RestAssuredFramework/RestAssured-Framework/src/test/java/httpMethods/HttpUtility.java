package httpMethods;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class HttpUtility {

    public static Response getHttpMethod(String path) {
        return RestAssured.given().
                when().
                headers("Content-Type", ContentType.JSON).
                filter(new AllureRestAssured()).
                get(path);
    }
}
