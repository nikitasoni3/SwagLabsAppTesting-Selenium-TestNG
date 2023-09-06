package Tests;

import Pages.HomePage;
import Pages.HomeProductPage;
import Pages.LoginPage;
import Utilities.ConfigReader;
import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.IOException;


public class LoginTest {
    static WebDriver driver;
    LoginPage login;
    HomeProductPage homeProduct;
    HomePage home;
    ConfigReader con;
    ExcelReader excel;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        homeProduct = new HomeProductPage(driver, "Purchase1");
        home= new HomePage(driver);
        con = new ConfigReader();
        excel = new ExcelReader();
        login.goToUrl(con.getUrl());
    }

    @Test
    public void LoginTest() throws IOException {
        login.loginApplication(excel.getUsername(), excel.getPassword());
        login.takeLoginStatusScreenshot();
        System.out.println(homeProduct.getLoginConfirmationMessage());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
//Section 16