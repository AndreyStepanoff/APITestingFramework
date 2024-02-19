package APITestingFramework.testcases;

import APITestingFramework.APIs.GetCustomerAPI;
import APITestingFramework.setUp.BaseTest;
import APITestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCustomerTest extends BaseTest {

    @Test
    public void getCustomerWithValidSecretKey(){
        Response response = GetCustomerAPI.sendGetReqGetCustomerAPI(2);
        response.prettyPrint();

        System.out.println("Responce status code --" + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println(TestUtil.countJsonKeys(response.asString(), "id") + " number of users that the API returns with queryParam limit");
        int actualIdAmount = TestUtil.countJsonKeys(response.asString(), "id");
        Assert.assertEquals(actualIdAmount, 2);

    }

}
