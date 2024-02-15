package APITestingFramework.utilities;

import APITestingFramework.setUp.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    @Test(dataProvider = "data")
    public void testData(Hashtable<String,String> data){
        System.out.println(data.get("name")+" "+data.get("email")+data.get("description"));
       // System.out.println(data);
    }

    @DataProvider(name = "data", parallel = true)  //, parallel = true
    public Object[][] getData(Method m){
        int rows = excel.getRowCount(Constants.TESTDATA_SHEET);
        System.out.println("Total rows in excel "+rows);
        String testName = m.getName();
        int testCaseRowNub = 1;

        for (testCaseRowNub = 1; testCaseRowNub<=rows; testCaseRowNub++){
            String testCaseName = excel.getCellData(Constants.TESTDATA_SHEET, 0, testCaseRowNub);
            if (testCaseName.equalsIgnoreCase(testName))
                break;
        }
        System.out.println("Testcase "+testName+" starts from row num "+testCaseRowNub);
        int dataStartRowNum = testCaseRowNub+2;
        int testRows = 0;
        while (!excel.getCellData(Constants.TESTDATA_SHEET,0,dataStartRowNum+testRows).equals("")){
            testRows++;
        }
        System.out.println("Total number of rows in test are "+testRows);

        int colStartColNum = testCaseRowNub+1;
        int testCols = 0;
        while (!excel.getCellData(Constants.TESTDATA_SHEET, testCols,colStartColNum).equals("")){
            testCols++;
        }
        System.out.println("Total number of columns are "+testCols);
        Object[][] data = new Object[testRows][1];
        int i = 0;
        for (int rNum=dataStartRowNum; rNum<(dataStartRowNum+testRows);rNum++){
            Hashtable<String,String> table = new Hashtable<String, String>();
            for (int cNum=0; cNum<testCols; cNum++){
                 System.out.print(" | " + excel.getCellData(Constants.TESTDATA_SHEET, cNum, rNum));
                String testData = excel.getCellData(Constants.TESTDATA_SHEET, cNum, rNum);
                String colName = excel.getCellData(Constants.TESTDATA_SHEET, cNum, colStartColNum);
                table.put(colName, testData);
            }
            data[i][0] = table;
            i++;
        }
        return data;
    }


}
