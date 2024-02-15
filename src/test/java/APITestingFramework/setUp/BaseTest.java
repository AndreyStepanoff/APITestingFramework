package APITestingFramework.setUp;

import APITestingFramework.utilities.ExcelReader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static Properties config = new Properties();
    public FileInputStream fis;
    public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\Book.xlsx");

    @BeforeSuite
    public void setUp() throws IOException {
        fis = new FileInputStream("C:\\Users\\admin\\Desktop\\APIFramework\\APITestingFramework\\src\\test\\resources\\properties\\config.properties");
        config.load(fis);

        RestAssured.baseURI=config.getProperty("baseURI");
        RestAssured.basePath=config.getProperty("basePath");
    }
    @AfterSuite
    public void tearDown(){

    }

}
