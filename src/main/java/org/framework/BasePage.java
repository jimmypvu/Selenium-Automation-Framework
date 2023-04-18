package org.framework;

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

    public WebElement waitAndGet(By locator){
        waitFor(locator);
        return driver.findElement(locator);
    }

    public void waitForVis(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClk(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void mouseToElement(WebElement element){
        actions.moveToElement(element);
    }

    public void clickAndDragTo(WebElement clickTarget, WebElement dropTarget){
        actions.clickAndHold(clickTarget).release(dropTarget).perform();
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
        js.executeScript("window.scrollTo(0,0)");
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
    public boolean pageIsLoadedCompletely(){
        return js.executeScript("return document.readyState").toString().equals("complete");
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

    public WebElement waitForPresAndGet(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitFor(By locator){
        waitForPres(locator);
        waitForVis(locator);
//        waitForClk(locator);
    }

    public void waitForPres(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForVis(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClk(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForVisAndGet(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void mouseToElement(By locator){
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(locator))).perform();
    }

    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

}
