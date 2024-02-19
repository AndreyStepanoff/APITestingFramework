package APITestingFramework.APIs;

import APITestingFramework.setUp.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class GetCustomerAPI extends BaseTest {

    public static Response sendGetReqGetCustomerAPI(int limit) {
        Response response =
                        given()
                        .auth()
                        .basic(config.getProperty("validSecretKey"), "")
                        .formParam(String.valueOf(limit)).queryParam("limit",limit)
                        .get(config.getProperty("customerAPIEndPoint"));
        return response;
    }
}
