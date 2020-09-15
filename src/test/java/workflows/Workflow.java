package workflows;

import com.aventstack.extentreports.ExtentTest;
import configurations.PageBase;
import org.openqa.selenium.WebDriver;
import pages.Account.Account;
import pages.Home.Home;
import pages.Login.Login;
import utils.CRUD.User;

public class Workflow extends PageBase {
    private ExtentTest logger;

    private Workflow(WebDriver driver) throws Exception {
        super(driver);
    }

    public static Workflow of(WebDriver driver) throws Exception{
        return new Workflow(driver);
    }

    public Workflow with(ExtentTest logger) throws Exception{
        this.logger = logger;
        return this;
    }

    //region Flow
    public void validateSuccessfulLogin(User user) throws Exception {
        Home
                .of(this.driver)
                .with(this.logger)
                .clickLoginButton();

        Login
                .of(this.driver)
                .with(this.logger)
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickLoginButton();

        Account
                .of(this.driver)
                .with(this.logger)
                .validateLoginSuccess()
                .clickLogoutButton();
    }
    //endregion
}
