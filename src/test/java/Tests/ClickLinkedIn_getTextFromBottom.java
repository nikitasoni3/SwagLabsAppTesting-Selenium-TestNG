package Tests;

import AbstractComponents.AbstractComponent;
import Pages.HomePage;
import Pages.HomeProductPage;
import Pages.LoginPage;
import Utilities.ConfigReader;
import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ClickLinkedIn_getTextFromBottom{
    static WebDriver driver;
    LoginPage login;
    HomeProductPage homeProduct;
    HomePage home;
    ConfigReader con;
    AbstractComponent abs;
    ExcelReader excel;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        homeProduct = new HomeProductPage(driver, "Purchase1");
        home= new HomePage(driver);
        abs = new AbstractComponent(driver);
        con = new ConfigReader();
        excel = new ExcelReader();
        login.goToUrl(con.getUrl());
    }

    @Test
    public void clickOnLinkedinBtnAndGetTextFromBottom() throws IOException {
        login.loginApplication(excel.getUsername(), excel.getPassword());
        home.clickOnLinkedInBtn();
        abs.goToTab0();
        home.getTextMessage();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
