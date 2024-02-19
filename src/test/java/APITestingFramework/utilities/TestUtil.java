package APITestingFramework.utilities;

import APITestingFramework.listeners.ExtentListeners;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestUtil {

    public static boolean jsonHasKey(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the presence of key" + key);
        return jsonObject.has(key);
    }

    public static String getJsonKeyValue(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the key value" + key);
        return jsonObject.get(key).toString();
    }

    public static int countJsonKeys(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        Set<String> uniqueIds = new HashSet<>();
        for (int i=0;i<dataArray.length();i++){
            JSONObject dataObject = dataArray.getJSONObject(i);
            String id = dataObject.getString("id");
            uniqueIds.add(id);
        }
        int uniqIDcount = uniqueIds.size();
        return uniqIDcount;

    }
}
