package pages.Home;

import com.aventstack.extentreports.ExtentTest;
import configurations.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends PageBase {

    private HomeElements webElements;

    private Home(WebDriver driver) throws Exception {
        super(driver);
        this.webElements = new HomeElements();
    }

    public static Home of(WebDriver driver) throws Exception{
        return new Home(driver);
    }

    public Home with(ExtentTest logger) throws Exception {
        setLogger(logger);
        return this;
    }

    //region Web Elements
    private By LoginButton() { return By.cssSelector(webElements.LOGIN_BUTTON); }
    //endregion

    //region Actions
    public Home clickLoginButton(){
        click(LoginButton());
        return this;
    }
    //endregion
}
