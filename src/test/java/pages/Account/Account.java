package pages.Account;

import com.aventstack.extentreports.ExtentTest;
import configurations.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Account extends PageBase {
    private AccountElements webElements;

    private Account(WebDriver driver) throws Exception {
        super(driver);
        this.webElements = new AccountElements();
    }

    public static Account of(WebDriver driver) throws Exception{
        return new Account(driver);
    }

    public Account with(ExtentTest logger) throws Exception {
        setLogger(logger);
        return this;
    }

    //region Web Elements
    private By AccountButton() { return By.cssSelector(webElements.ACCOUNT_BUTTON); }
    private By LogoutButton() { return By.cssSelector(webElements.LOGOUT_BUTTON); }
    //endregion

    //region Actions
    public Account validateLoginSuccess(){
        Assert.assertTrue(isPresent(AccountButton()));
        return this;
    }
    public Account clickLogoutButton(){
        click(LogoutButton());
        return this;
    }
    //endregion
}
