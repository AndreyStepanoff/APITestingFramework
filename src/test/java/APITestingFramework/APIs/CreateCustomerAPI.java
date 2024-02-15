package APITestingFramework.APIs;

import APITestingFramework.listeners.ExtentListeners;
import APITestingFramework.setUp.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {

    public static Response sendPostReqCreateCustomerAPIWithValidAithKey(Hashtable<String,String> data){
        Response response =
                given()
                        .auth()
                        .basic(config.getProperty("validSecretKey"),"")
                        .formParam("email",data.get("email"))
                        .formParam("description", data.get("description"))
                        .post(config.getProperty("customerAPIEndPoint"));
        return response;
    }

    public static Response sendPostRequestToCreateCustomerAPIWithInValidAithKey(Hashtable<String,String> data){
        Response response =
                given()
                        .auth()
                        .basic(config.getProperty("inValidSecretKey"),"")
                        .formParam("email",data.get("email"))
                        .formParam("description", data.get("description"))
                        .post(config.getProperty("customerAPIEndPoint"));
        return response;

    }
}
