package Tests;

import Pages.*;
import Utilities.ConfigReader;
import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class OrderItem{
    static WebDriver driver;
    LoginPage login;
    HomeProductPage homeProduct;
    HomePage home;
    CheckoutPage checkOut;
    OrderCompletePage odrComplete;
    ConfigReader con;
    ExcelReader excel;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        homeProduct = new HomeProductPage(driver, "Purchase1");
        home= new HomePage(driver);
        checkOut = new CheckoutPage(driver);
        odrComplete = new OrderCompletePage(driver);
        con = new ConfigReader();
        excel = new ExcelReader();
        login.goToUrl(con.getUrl());
    }

    @Test(priority = 1)
    public void orderItems() throws IOException {
        login.loginApplication(excel.getUsername(), excel.getPassword());
        login.takeLoginStatusScreenshot();
        homeProduct.selectItemAddToCart();
        homeProduct.goToCartPage();
        checkOut.goToCheckOutPage();
        checkOut.enterUserInformationAndPlaceOrder(excel.getFirstName(), excel.getLastName(), excel.getPostalCode());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}


