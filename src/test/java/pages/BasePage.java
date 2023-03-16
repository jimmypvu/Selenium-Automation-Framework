package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;
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
}
