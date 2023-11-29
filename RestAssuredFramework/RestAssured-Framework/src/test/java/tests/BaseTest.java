package tests;

import constantPaths.FilePath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public static void baseSetup() {
        ConfigReader iniReader = new ConfigReader(FilePath.getCONFIG_INI());
        String getEnvironment = iniReader.getValue("setup", "environment");
        switch (getEnvironment.toLowerCase()) {
            case "staging":
                RestAssured.baseURI = iniReader.getValue(getEnvironment, "url");
            case "production":
                RestAssured.baseURI = iniReader.getValue(getEnvironment, "url");
            default:
                RestAssured.baseURI = iniReader.getValue(getEnvironment, "url");
        }
        if (RestAssured.urlEncodingEnabled) {
            RestAssured.urlEncodingEnabled = false;
        }
    }

    public static void verifyResponseSchema(Response response, String pathToSchema) {
        SoftAssert softAssert = new SoftAssert();
        response.then().assertThat().body(matchesJsonSchemaInClasspath("responseSchema/" + pathToSchema));
        softAssert.assertAll();
    }
}
