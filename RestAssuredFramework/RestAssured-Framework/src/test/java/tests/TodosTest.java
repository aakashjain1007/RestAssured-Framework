package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.GetUsersResponsePojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static apiMethodsImplementation.GetTodosApiMethods.*;
import static apiMethodsImplementation.GetUsersApiMethods.deserializeUserResponsePojo;
import static apiMethodsImplementation.GetUsersApiMethods.getAllUsersRequest;
import static utils.CommonUtils.parseDouble;

public class TodosTest extends BaseTest {
    List<Integer> userIdList = new ArrayList<>();

    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : To get Fancode Users where lat (-40 to 5) and lng (5 to 100)")
    @BeforeClass
    private void getAllFanCodeUsers() {
        SoftAssert softAssert = new SoftAssert();
        Response responseUsers = getAllUsersRequest();
        softAssert.assertEquals(responseUsers.getStatusCode(), 200, "Expected Status Code is 200 but found " + responseUsers.getStatusCode());
        verifyResponseSchema(responseUsers, "getUsersResponseSchema.json");
        GetUsersResponsePojo[] deserializeAllUsersResponsePojo = deserializeUserResponsePojo(responseUsers);
        for (int i = 0; i < deserializeAllUsersResponsePojo.length; i++) {
            if (parseDouble(deserializeAllUsersResponsePojo[i].getAddress().getGeo().getLat()) >= parseDouble("-40") && parseDouble(deserializeAllUsersResponsePojo[i].getAddress().getGeo().getLat()) <= parseDouble("5")
                    && parseDouble(deserializeAllUsersResponsePojo[i].getAddress().getGeo().getLng()) >= parseDouble("5") && parseDouble(deserializeAllUsersResponsePojo[i].getAddress().getGeo().getLng()) <= parseDouble("100"))
                userIdList.add(deserializeAllUsersResponsePojo[i].getId());
        }
        softAssert.assertAll();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : To verify Fancode Users Completed Task is more than 50%")
    @Test(dataProvider = "userId")
    private void verifyFancodeUsersCompletedTaskPercentShouldBeGreaterThan50(int userId) {
        SoftAssert softAssert = new SoftAssert();
        Response responseTodos = getAllTodoRequest(userId);
        softAssert.assertEquals(responseTodos.getStatusCode(), 200, "Expected Status Code is 200 but found " + responseTodos.getStatusCode());

        verifyResponseSchema(responseTodos, "getTodosResponseSchema.json");

        List<Integer> listOfUsersId = com.jayway.jsonpath.JsonPath.read(responseTodos.asString(), "$..userId");
        boolean containsOnlySameUsers = verifyUserIdListContainsOnlySameUsers(listOfUsersId, userId);
        softAssert.assertTrue(containsOnlySameUsers, "listOfUsersId of Users contains some other User Id: " + listOfUsersId + " it should contain only UserId as " + userId);

        List<Boolean> listOfCompletedTask = com.jayway.jsonpath.JsonPath.read(responseTodos.asString(), "$..completed");
        boolean containsOnlyTrueFalse = verifyCompletedTaskListContainsOnlyTrueFalse(listOfCompletedTask);
        softAssert.assertTrue(containsOnlyTrueFalse, "listOfCompletedTask of Users should not contain other than True or False Values: " + listOfCompletedTask);

        List<Boolean> completedTaskList = com.jayway.jsonpath.JsonPath.read(responseTodos.asString(), "$..completed");
        double totalCompletedList = completedTaskList.size();
        double totalCompletedWork = completedTaskList.stream().filter(Boolean::valueOf).count();
        double totalCompletedWorkPercent = (totalCompletedWork * 100) / totalCompletedList;

        softAssert.assertTrue(totalCompletedWorkPercent >= 50.0, "User completion percentage is less than 50% for UserId: " + userId + " | Actual Completed Percent " + totalCompletedWorkPercent);
        softAssert.assertAll();
    }

    @DataProvider(name = "userId")
    private Iterator<Integer> userId() {
        return userIdList.iterator();
    }
}
