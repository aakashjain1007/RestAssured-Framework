package apiMethodsImplementation;

import io.restassured.response.Response;
import pojos.GetUsersResponsePojo;

import static constantPaths.ApiEndpoints.GET_ALL_USERS;
import static httpMethods.HttpUtility.getHttpMethod;

public class GetUsersApiMethods {
    public static Response getAllUsersRequest() {
        return getHttpMethod(GET_ALL_USERS);
    }

     public static GetUsersResponsePojo[] deserializeUserResponsePojo(Response response) {
        return response.getBody().as(GetUsersResponsePojo[].class);
    }

}
