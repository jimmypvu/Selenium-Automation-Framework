package framework;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jse;

    public BasePage(WebDriver threadDriver){
        this.driver = threadDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofMillis(250));
        wait.pollingEvery(Duration.ofMillis(250));
        actions = new Actions(driver);
        jse = (JavascriptExecutor) driver;

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
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollToEnd(){
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTop(){
        jse.executeScript("window.scrollTo(0,0)");
    }

    public void scrollToMiddle(){
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight/2)");
    }

    public void refreshPage(){
        jse.executeScript("history.go(0)");
    }

    public void navigateBack(){
        jse.executeScript("history.go(-1)");
    }

    public void navigateForward(){
        jse.executeScript("history.go(1)");
    }

    public String getPageTitle() {
        return jse.executeScript("return document.title;").toString();
    }

    public boolean isPageLoadComplete(){
        return jse.executeScript("return document.readyState").equals("complete");
    }

    public void waitForPageLoad(){
        boolean pageLoadComplete = false;

        do{
            pageLoadComplete = isPageLoadComplete();
        }while(!pageLoadComplete);

        System.out.println("Page load complete");
    }

    public void pause(int millis){
        try{
            Thread.sleep(millis);
        }catch(Exception ignored){
        }
    }

    public void pause(){
        try{
            Thread.sleep(2000);
        }catch(Exception ignored){
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



    public boolean doesElementContainClass(WebElement element, String value){
        try{
            List<String> classes = Arrays.asList(element.getAttribute("class").split(" "));
            System.out.println(classes);
            if(classes.contains(value)){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAttributePresent(WebElement element, String attribute){
        try{
            String attributeValue = element.getAttribute(attribute);
            if(attributeValue == null || attributeValue.isEmpty()){
                System.out.println("attribute not present");
                return false;
            }else{
                System.out.println(attribute + " " + attributeValue);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean doesElementAttributeContainValuePartialMatch(WebElement element, String attribute, String value){
        try{
            int trueCount = 0;
            List<String> attributeValues = Arrays.stream(element.getAttribute(attribute).split(" ")).collect(Collectors.toList());
            for(String atrVal : attributeValues){
                if(atrVal.contains(value)) trueCount++;
            }
            if(trueCount > 0)
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean doesElementAttributeContainValue(WebElement element, String attribute, String value){
        try{
            List<String> attributeValues = Arrays.stream(element.getAttribute(attribute).split(" ")).collect(Collectors.toList());
            if(attributeValues.contains(value))
                return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
