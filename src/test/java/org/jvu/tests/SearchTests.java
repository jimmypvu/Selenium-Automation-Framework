package org.jvu.tests;

import org.jvu.dataproviders.DataProviders;
import org.jvu.pages.HomePage;
import org.jvu.pages.SearchPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.jvu.pages.RegistrationPage.genString;

public class SearchTests extends BaseTest{
    @Test(description = "user should see matching search results for an item/keyword searched", groups = {"web", "search", "smoke", "regression"})
    public void searchForValidItem(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem("shirt");
        sp.isItemInSearchResultsOrAreWeOnItemPage("shirt");
    }

    //site's search is odd in that it redirects you directly to the item page if there's only 1 match --> confirm requirements
    @Test(description = "searched item should be displayed in search results" +
            "if only 1 result matched than user should be taken directly to item page", dataProvider = "ExistingProducts", dataProviderClass = DataProviders.class,
            groups = {"web", "search", "regression"})
    public void searchForExistingItems(String item){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem(item);

        Assert.assertTrue(sp.isItemInSearchResultsOrAreWeOnItemPage(item));
    }

    @Test(description = "search should not return any results if search term is not alphanumerical", groups = {"web", "search", "regression"})
    public void searchForInvalidItem(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem(genString(20, false, false, true));
        WebElement noMatchingResults = sp.waitAndGet(SearchPage.byNoMatchingResultsLbl);

        Assert.assertTrue(noMatchingResults.isDisplayed());
    }

    //this example test will fail bc no partial match or close results are displayed if keyword is misspelled
    @Test(description = "search should still display some close results if some keywords match but some are mistyped", enabled = false,
            groups = {"web", "search", "regression"})
    public void searchItemWithATypo(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem("curl to straight shampoo");

        WebElement sortResultsDropdown = sp.waitAndGet(SearchPage.bySearchResultsHdr);

        Assert.assertTrue(sortResultsDropdown.isDisplayed());
    }
}
