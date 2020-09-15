package pages.Login;

import com.aventstack.extentreports.ExtentTest;
import configurations.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends PageBase {
    private LoginElements webElements;

    private Login(WebDriver driver) throws Exception {
        super(driver);
        this.webElements = new LoginElements();
    }

    public static Login of(WebDriver driver) throws Exception{
        return new Login(driver);
    }

    public Login with(ExtentTest logger) throws Exception {
        setLogger(logger);
        return this;
    }

    //region Web Elements
    private By EmailInput() { return By.cssSelector(webElements.EMAIL_INPUT); }
    private By PasswordInput() { return By.cssSelector(webElements.PASSWORD_INPUT); }
    private By LoginButton() { return By.cssSelector(webElements.LOGIN_BUTTON); }
    //endregion

    //region Actions
    public Login fillEmail(String email){
        fillString(EmailInput(), email);
        return this;
    }
    public Login fillPassword(String password){
        fillString(PasswordInput(), password);
        return this;
    }
    public Login clickLoginButton(){
        click(LoginButton());
        return this;
    }
    //endregion
}
