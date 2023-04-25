package jpvu.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//h4[contains(text(),'Products meeting the search criteria')]")
    public WebElement hdrSearchResults;
    @FindBy(xpath = "//div[contains(text(),'There is no product that matches the search criteria')]")
    public WebElement lblNoMatchingResults;
    @FindBy(css = "h1.productname")
    public WebElement hdrProductName;
    @FindBy(css = "select#sort")
    public WebElement selSortResults;

    public SearchPage (WebDriver givenDriver){
        super(givenDriver);
    }

    public boolean isItemInSearchResultsOrRedirectToItemPage(String item){
        List<WebElement> resultElements = driver.findElements(By.cssSelector("div.fixed a.prdocutname"));
        List<String> searchResults = new ArrayList<>();
        for(WebElement element : resultElements){
            searchResults.add(element.getText().toString().trim().toUpperCase());
        }

        int correctSearchResultsCount = 0;
        for(String result : searchResults){
            if(result.contains(item.trim().toUpperCase())){
                correctSearchResultsCount++;
            }
        }

        if(searchResults.isEmpty()){
            ItemPage ip = new ItemPage(driver);
            String itemName = ip.hdrProductName.getText().toString().trim().toUpperCase();
            return itemName.contains(item.toUpperCase());
        }else{
            return correctSearchResultsCount > 0;
        }
    }

}
