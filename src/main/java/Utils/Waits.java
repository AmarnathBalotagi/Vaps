package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class Waits extends base  {

    private WebDriver driver;
    private WebDriverWait wait;

    
    public Waits(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Wait for element to be clickable
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(element)
        ));
    }

    // Wait for element to be visible
    public WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(element)
        ));
    }

    // Wait for presence of element (by locator)
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to disappear
    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for page title
    public boolean waitForTitle(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }

    // Wait for page URL
    public boolean waitForURL(String urlFraction) {
        return wait.until(ExpectedConditions.urlContains(urlFraction));
    }

    // Custom scroll + wait
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForVisible(element);
    }

}
