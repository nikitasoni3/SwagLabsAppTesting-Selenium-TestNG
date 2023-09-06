package AbstractComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbstractComponent {
    static WebDriver driver;
    String parentWindowHandle;
    List<String> window;
    public JavascriptExecutor js;
    public Logger logger = LogManager.getLogger(AbstractComponent.class);

    public AbstractComponent(WebDriver driver){
        this.driver=driver;
        parentWindowHandle = driver.getWindowHandle();
        window = new ArrayList<>(driver.getWindowHandles());
        js = (JavascriptExecutor) driver;
    }

    public void waitForElementToAppear(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }
    public static String takeScreenshot(String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\nikita.soni\\IdeaProjects\\PageObjectModel\\target\\Screenshots\\" + fileName));
        return fileName;
    }
    public void goToTab0(){
        driver.switchTo().window(window.get(0));
    }
    public void goToTab1() {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    public void goToTab2() {
        Set<String> windowHandles2 = driver.getWindowHandles();
        for (String handle : windowHandles2) {
            if (!handle.equals(parentWindowHandle) && !handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    public void goToTab3(){
        Set<String> windowHandles3 = driver.getWindowHandles();
        String lastHandle = "";
        for(String handle : windowHandles3){
            lastHandle = handle;
        }
        if(!lastHandle.isEmpty()){
            driver.switchTo().window(lastHandle);
        }
    }

    public void scrollPage(){
        js.executeScript("window.scrollTo(0, 500)");
    }

    }



