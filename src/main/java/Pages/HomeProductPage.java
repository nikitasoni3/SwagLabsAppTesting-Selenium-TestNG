package Pages;

import AbstractComponents.AbstractComponent;
import Utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomeProductPage extends AbstractComponent {
    WebDriver driver;
    String itemNameString;
    ExcelReader excel;
    ArrayList ls;

    public HomeProductPage(WebDriver driver, String orderNum) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        excel = new ExcelReader();
        ls = excel.getData(orderNum);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @FindBy(css = "a[class='shopping_cart_link']")
    WebElement cartButton;
    @FindBy(css = "span[class='title']")
    WebElement loginConfirmationElement;
    @FindBy(css = "div[class='inventory_item']")
    List<WebElement> products;
    @FindBy(css = "div[class='inventory_item_name']")
    List<WebElement> productName;
    @FindBy(css = "button[class='btn btn_primary btn_small btn_inventory']")
    WebElement addToCartBtn;
    @FindBy(xpath = "//button[contains(text(), 'Remove')]")
    WebElement removeBtn;
    public String getLoginConfirmationMessage() {
        return loginConfirmationElement.getText();
    }

    public void removeOneItemFromCart() throws IOException {
        for (int i = 0; i < productName.size(); i++) {
            String productNameString = productName.get(i).getText();
            String rmItemName = (String) ls.get(6);
            if (productNameString.contains(rmItemName)) {
                logger.info("Removed the matched item with excel sheet successfully");
                productName.get(i).click();
                removeBtn.click();
            }
        }
    }
    public void selectItemAddToCart() {
        ExcelReader excel = new ExcelReader();
        String itemName = (String) ls.get(2);
        String[] items = itemName.split(",");
            for (int j=0; j<items.length; j++) {
                for (int i = 0; i < productName.size(); i++) {
                    String productNameString = productName.get(i).getText();
                    if (productNameString.contains(items[j].trim())) {
                        logger.info("Added the matched product with excel sheet to the cart");
                        productName.get(i).click();
                        addToCartBtn.click();
                    }
                }
                logger.info("Navigated back");
                driver.navigate().back();
            }

    }
    public void goToCartPage() {
        logger.info("Clicked the cart button and went to the cart page");
        cartButton.click();

    }
}