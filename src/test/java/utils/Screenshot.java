package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Screenshot {
    private final String STEP_MESSAGE = "Screenshot Step on method : %s";
    private final String ERROR_MESSAGE = "Error Screenshot :";
    private final int CALLER_METHOD_INDEX = 2;
    private String fileLocation;
    private WebDriver driver;
    private ExtentTest logger;

    private Screenshot(WebDriver driver){
        this.driver = driver;
        this.fileLocation = System.getProperty("envScreenshotLocation");
    }

    public static Screenshot of(WebDriver driver){
        return new Screenshot(driver);
    }

    public Screenshot with(ExtentTest logger){
        this.logger = logger;
        return this;
    }

    public String takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = UUID.randomUUID().toString() + ".png";

        File targetFile = new File(fileLocation, fileName);
        FileUtils.copyFile(scrFile, targetFile);

        if(logger != null){
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String caller = stackTraceElements[CALLER_METHOD_INDEX].getMethodName();

            if(caller.equals("getResult")){
                logger.fail(ERROR_MESSAGE, MediaEntityBuilder.createScreenCaptureFromPath(targetFile.getPath()).build());
            } else {
                caller = stackTraceElements[CALLER_METHOD_INDEX + 1].getMethodName();
                logger.pass(String.format(STEP_MESSAGE, caller), MediaEntityBuilder.createScreenCaptureFromPath(targetFile.getPath()).build());
            }

        }
        return targetFile.getPath();
    }
}
