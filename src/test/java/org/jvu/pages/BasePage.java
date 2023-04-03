package org.jvu.pages;

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
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver threadDriver){
        this.driver = threadDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(500));
        wait.pollingEvery(Duration.ofMillis(500));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
//        PageFactory.initElements(driver, this);
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

    public void waitFor(WebElement element){
        waitForVis(element);
        waitForClk(element);
    }

    public WebElement waitAndGet(WebElement element){
        waitFor(element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVis(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClk(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void moveToElement(WebElement element){
        actions.moveToElement(element);
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollToEnd(){
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTop(){
        js.executeScript("window.scrollBy(0,0)");
    }

    public void scrollToMiddle(){
        js.executeScript("window.scrollBy(0,document.body.scrollHeight/2)");
    }

    public void refreshPage(){
        js.executeScript("history.go(0)");
    }

    public void navigateBack(){
        js.executeScript("history.go(-1)");
    }

    public void navigateForward(){
        js.executeScript("history.go(1)");
    }

    public String getPageTitle() {
        return js.executeScript("return document.title;").toString();
    }

    public void pause(int n){
        try{
            Thread.sleep(n);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doubleClick(By locator){
        actions.doubleClick(findElement(locator)).perform();
    }

    public WebElement findElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForPresence(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void moveToElement(By locator){
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).perform();
    }

    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

}
