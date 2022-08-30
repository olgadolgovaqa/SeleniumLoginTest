package pageObjectTests;

import enums.BrowserType;
import helper.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected String email;
    protected String password;

    @Parameters({"browser"})
    @BeforeMethod
    public void startUp(String browser) {

        BrowserType browserType;
        switch (browser) {
            case "chrome":
                browserType = BrowserType.CHROME;
                driver = BrowserFactory.getDriver(browserType);
                break;

            case "edge":
                browserType = BrowserType.EDGE;
                driver = BrowserFactory.getDriver(browserType);
                break;

            case "firefox":
                browserType = BrowserType.FIREFOX;
                driver = BrowserFactory.getDriver(browserType);
                break;

        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
