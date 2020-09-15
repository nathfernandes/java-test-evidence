package configurations;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class PageBase {
    private final int CALLER_METHOD_INDEX = 2;
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentTest logger;

    public PageBase(WebDriver driver) throws Exception {
        this.driver = driver;
        this.wait = (WebDriverWait) new WebDriverWait(driver, 600).ignoring(StaleElementReferenceException.class);
        setDefaultTimeout();
    }

    public void setLogger(ExtentTest logger){
        this.logger = logger;
    }

    private void setDefaultTimeout(){
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
    }

    public void waitForElement(By byElement){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
    }

    public void click(By locator) {
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    public void fillString(By locator, String value){
        waitForElement(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public boolean isPresent(By locator){
        try{
            driver.findElement(locator);
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }
}
