package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getEmailField() {
        return driver.findElement(By.id("loginForm-eMail"));
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.id("loginForm-password"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.id("login-submit-btn"));
    }

    public void acceptCookies() {

        Wait wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);

        WebElement cookiesAcceptButton = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return ((WebElement) js.executeScript("return document.querySelector('#usercentrics-root').shadowRoot.querySelector('button[data-testid=\"uc-accept-all-button\"]')"));
            }
        });
        cookiesAcceptButton.click();
    }


    public void open() {
        driver.get("https://www.shop-apotheke.com/nx/login/");
    }

    public MainPage login(String email, String password) {
        getEmailField().sendKeys(email);
        getPasswordField().sendKeys(password);
        acceptCookies();
        getLoginButton().click();
        return new MainPage(driver);
    }

    public boolean isError() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".m-Notification--error")));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

}
