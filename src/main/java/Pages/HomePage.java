package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends AbstractComponent {
    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    @FindBy(id="react-burger-menu-btn")
    WebElement hamBurgerIcon;
    By hbIcon = By.id("react-burger-menu-btn");
    @FindBy(xpath = "//a[contains(text(), 'All Items')]")
    WebElement hamBurger_AllItems;
    @FindBy(xpath = "//a[contains(text(), 'About')]")
    WebElement hamBurger_About;
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement hamBurger_Logout;
    @FindBy(xpath = "//a[contains(text(), 'Reset App State')]")
    WebElement hamBurger_ResetAppState;
    By hbList = By.cssSelector(".bm-menu");
    @FindBy(css=".product_sort_container")
    WebElement productsFilter;
    @FindBy(css=".inventory_item")
    List<WebElement> products;
    @FindBy(css=".inventory_item_name")
    List<WebElement> productName;
    @FindBy(xpath = "//button[contains(text(),'OK')]")
    WebElement okBtn;
    By okButton = By.xpath("//button[contains(text(),'OK')]");
    @FindBy(xpath = "//span[contains(text(), 'Resources')]")
    WebElement Resources;
    @FindBy(xpath = "//span[contains(text(), 'Documentation')]")
    WebElement documentation;

    @FindBy(xpath = "//button[contains(text(), 'Add to cart')]")
    WebElement AddToCartBtn;
    @FindBy(xpath = "//button[contains(text(), 'Get Started')]")
    WebElement getStarted;
    @FindBy(css = "a[href='https://www.linkedin.com/company/sauce-labs/']")
    WebElement linkedInBtn;
    @FindBy(css = "div[class='footer_copy']")
    WebElement text;
    public void clickOntHamBurgerIcon(){
        logger.info("Waited for visibility of Hamburger Icon and then clicked");
        waitForElementToAppear(hbIcon);
        hamBurgerIcon.click();
    }
    public void getHowToUseSauceDemoApplication() throws IOException {
        logger.info("Waited for Hamburger List");
        waitForElementToAppear(hbList);
        Actions action = new Actions(driver);
        logger.info("Clicked on About button with control key");
        action.keyDown(Keys.CONTROL).click(hamBurger_About).build().perform();
        logger.info("Switched to next tab");
        goToTab1();
        logger.info("waited for visibility of ok button and then clicked");
        waitForElementToAppear(okButton);
        okBtn.click();
        logger.info("Clicked on Documentation after moved the arrow on Resources");
        action.moveToElement(Resources).perform();
        documentation.click();
        logger.info("Switched to next tab");
        goToTab2();
        getStarted.click();
        logger.info("Switched to next tab");
        goToTab3();
        logger.info("Taken the screenshot of How to use Sauce Demo website with scrolling");
        takeScreenshot("HowToUseSauceDemo.png");
        scrollPage();
    }
       public void selectFilterForProduct(){
        logger.info("Filtered the products with Price Low to High");
        Select select = new Select(productsFilter);
        select.selectByVisibleText("Price (low to high)");
    }

    public void getCheapestProduct(){
        List<Double> prices = new ArrayList<>();

        for(WebElement element : products){
            String priceText = element.findElement(By.cssSelector(".inventory_item_price")).getText();
            Double price = Double.parseDouble(priceText.replace("$", ""));
            prices.add(price);
        }
        logger.info("Selected the cheapest item and added to the cart");
        int lowestPrice = prices.indexOf(Collections.min(prices));
        productName.get(lowestPrice).click();
        AddToCartBtn.click();

    }
    public void clickOnLinkedInBtn() throws IOException {
        logger.info("Scrolled to the Linked in button and then clicked it");
        js.executeScript("arguments[0].scrollIntoView(true);", linkedInBtn);
        linkedInBtn.click();
        logger.info("Switched to next tab");
        goToTab1();
        logger.info("Taken the ss of Linked in page");
        takeScreenshot("LinkedIn.png");
    }
    public void getTextMessage(){
        logger.info("Scrolled till the bottom of home page and printed the text");
        js.executeScript("arguments[0].scrollIntoView(true);", text);
        String texts = text.getText();
        String getText = texts.replace("2023 Sauce Labs.", "");
        System.out.println(getText);
    }
}
