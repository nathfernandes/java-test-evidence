package scenarios;

import configurations.DriverEnum;
import configurations.TestBase;
import org.testng.annotations.Test;
import utils.CRUD.User;
import utils.UserFactory;
import workflows.Workflow;

public class LoginTests extends TestBase {
    public LoginTests() { super(DriverEnum.CHROME, "LoginTests"); }

    @Test
    public void validateSuccessfulLogin() throws Exception {
        User user = UserFactory.createValidUser();
        Workflow
                .of(this.driver)
                .with(this.extentTest)
                .validateSuccessfulLogin(user);
    }

    @Test
    public void validateFailureExample() throws Exception {
        User user = UserFactory.createInvalidUser();
        Workflow
                .of(this.driver)
                .with(this.extentTest)
                .validateSuccessfulLogin(user);
    }
}
