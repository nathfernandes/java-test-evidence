package configurations;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.Constants;
import utils.Screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public DriverEnum testType;
    private String filePath;

    private ExtentReports extentReports;
    public ExtentTest extentTest;
    private ExtentSparkReporter extentHtmlReporter;

    public TestBase(DriverEnum type, String method){
        this.filePath = Constants.CURRENT_PATH + "/reports/";
        System.setProperty("envScreenshotLocation", filePath);

        this.testType = type;

        this.extentHtmlReporter = new ExtentSparkReporter(filePath + method + "_Report.html");
        this.extentReports = new ExtentReports();
        this.extentReports.attachReporter(extentHtmlReporter);
    }

    @BeforeClass
    public void setup() throws Exception {
        driver = testType.create();
        driver.get(Constants.URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
    }
    @BeforeMethod
    public void initializeReport(Method method){
        this.extentTest = extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE) {
            try{
                Screenshot.of(this.driver).with(extentTest).takeScreenshot();
            }catch (Exception err){}

            extentTest.fail(result.getThrowable());
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
    }

    @AfterClass
    public void tearDown(){
        try{
            driver.close();
            driver.quit();
            driver = null;
        }catch (Exception err){
            System.out.println(err.getMessage());
        }
        extentReports.flush();
    }
}
