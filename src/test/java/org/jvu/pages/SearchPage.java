package org.jvu.pages;

import org.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//h4[contains(text(),'Products meeting the search criteria')]")
    WebElement hdrSearchResults;
    @FindBy(xpath = "//div[contains(text(),'There is no product that matches the search criteria')]")
    WebElement lblNoMatchingResults;
    @FindBy(css = "h1.productname")
    WebElement hdrProductName;
    @FindBy(css = "select#sort")
    WebElement sddSortResults;

    public static By byNoMatchingResultsLbl = By.xpath("//div[contains(text(),'There is no product that matches the search criteria')]");
    public static By bySearchResultsHdr = By.xpath("//h4[contains(text(),'Products meeting the search criteria')]");
    public static By bySortResultsSel = By.cssSelector("select#sort");

    public SearchPage (WebDriver givenDriver){
        super(givenDriver);
    }

    public boolean isItemInSearchResultsOrAreWeOnItemPage(String item){
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
            String itemName = ip.waitAndGet(ItemPage.byProductNameHdr).getText().toString().trim().toUpperCase();
            return itemName.contains(item.toUpperCase());
        }else{
            return correctSearchResultsCount > 0;
        }
    }

}
