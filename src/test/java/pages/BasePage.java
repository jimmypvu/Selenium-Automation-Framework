package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver threadDriver){
        this.driver = threadDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(500));
        wait.pollingEvery(Duration.ofMillis(500));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void doubleClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.doubleClick(element).perform();
    }

    public void contextClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick(element).perform();
    }

    public void moveToAndClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).click();
    }

    public void moveToElement(WebElement element){
        actions.moveToElement(element);
    }

    public void doubleClick(By locator){
        actions.doubleClick(findElement(locator)).perform();
    }

    public WebElement findElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void moveToElement(By locator){
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).perform();
    }

    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }







}
