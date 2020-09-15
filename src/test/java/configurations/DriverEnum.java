package configurations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public enum DriverEnum {
    CHROME{
        @Override
        public WebDriver create() throws Exception {
            WebDriverManager.chromedriver().setup();
            ChromeOptions copts = new ChromeOptions();
            copts.addArguments("--disable-site-isolation-trials");
            return new ChromeDriver(copts);
        }
    };
    public abstract WebDriver create() throws Exception;
}
