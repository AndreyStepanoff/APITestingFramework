package APITestingFramework.testcases;

import APITestingFramework.APIs.CreateCustomerAPI;
import APITestingFramework.listeners.ExtentListeners;
import APITestingFramework.setUp.BaseTest;
import APITestingFramework.utilities.DataUtil;
import APITestingFramework.utilities.TestUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static io.restassured.RestAssured.*;

public class CreateCustomerTest extends BaseTest   {


    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void CreateCustomerWithValidSecretKey(Hashtable<String,String> data){
    Response response = CreateCustomerAPI.sendPostReqCreateCustomerAPIWithValidAithKey(data);

    ExtentListeners.testReport.get().info(data.toString());
    response.prettyPrint();
    System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"), "ID is not present in response");
        System.out.println("New user with id " + TestUtil.getJsonKeyValue(response.asString(), "id") + " was created");
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void CreateCustomerWithInvalidSecretKey(Hashtable<String,String> data){
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAithKey(data);

        ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 401);
        System.out.println(response.prettyPrint());
    }



}
