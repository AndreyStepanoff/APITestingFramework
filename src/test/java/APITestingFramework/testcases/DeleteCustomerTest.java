package APITestingFramework.testcases;

import APITestingFramework.APIs.CreateCustomerAPI;
import APITestingFramework.APIs.DeleteCustomerAPI;
import APITestingFramework.listeners.ExtentListeners;
import APITestingFramework.setUp.BaseTest;
import APITestingFramework.utilities.DataUtil;
import APITestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void deleteCustomer(Hashtable<String,String> data){
        Response response = DeleteCustomerAPI.sendDeleteReqDeleteCustomerAPIWithValidID(data);
        response.prettyPrint();
        ExtentListeners.testReport.get().info(data.toString());

        System.out.println("Responce status code --" + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("id key is present in response " + TestUtil.jsonHasKey(response.asString(), "id"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"), "ID is not present in response");
        String actualID = TestUtil.getJsonKeyValue(response.asString(), "id");
        Assert.assertEquals(actualID, data.get("id"));
        System.out.println("object key is present in response " + TestUtil.jsonHasKey(response.asString(), "object"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "object"), "object is not present in response");
        System.out.println("deleted key is present in response " + TestUtil.jsonHasKey(response.asString(), "deleted"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "deleted"), "deleted is not present in response");
    }
}