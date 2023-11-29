package apiMethodsImplementation;

import io.restassured.response.Response;
import pojos.GetTodosResponsePojo;

import java.util.List;

import static constantPaths.ApiEndpoints.GET_ALL_TODOS;
import static httpMethods.HttpUtility.getHttpMethod;

public class GetTodosApiMethods {

    private GetTodosApiMethods() {
    }

    public static Response getAllTodoRequest(int userId) {
        return getHttpMethod(GET_ALL_TODOS + "?userId=" + userId);
    }

    public static GetTodosResponsePojo[] deserializeTodoResponsePojo(Response response) {
        return response.getBody().as(GetTodosResponsePojo[].class);
    }

    public static boolean verifyUserIdListContainsOnlySameUsers(List<Integer> list, int userId) {
        for (int value : list) {
            if (value != userId) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyCompletedTaskListContainsOnlyTrueFalse(List<Boolean> list) {
        for (Boolean value : list) {
            if (!(value.equals(true) || value.equals(false))) {
                return false;
            }
        }
        return true;
    }
}
