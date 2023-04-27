package jpvu.tests.functional;

import jpvu.dataproviders.DataProviders;
import jpvu.pages.HomePage;
import jpvu.pages.SearchPage;
import framework.BaseTest;
import framework.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {
    @Test(description = "user should see matching search results for an item/keyword searched", groups = {"functional", "search", "smoke", "regression"})
    public void searchForValidItem(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem("shirt");

        Assert.assertTrue(sp.isItemInSearchResultsOrRedirectToItemPage("shirt"), "Item should be displayed in search results or user should be redirected to item page if only one exact match");
    }

    //site's search is odd in that it redirects you directly to the item page if there's only 1 match --> confirm requirements
    @Test(description = "searched item should be displayed in search results if only 1 result matched than user should be taken directly to item page", dataProvider = "ExistingProducts", dataProviderClass = DataProviders.class, groups = {"functional", "search", "regression"})
    public void searchForExistingItems(String item){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem(item);

        Assert.assertTrue(sp.isItemInSearchResultsOrRedirectToItemPage(item));
    }

    @Test(description = "search should not return any results if search term is not alphanumerical", groups = {"functional", "search", "regression"})
    public void searchForInvalidItem(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem(DataGenerator.getRandomString(20, false, false, true));

        Assert.assertTrue(sp.lblNoMatchingResults.isDisplayed());
    }

    //this example test will fail bc no partial match or close results are displayed if keyword is misspelled
    @Test(description = "search should still display some close results if some keywords match but some are mistyped", groups = {"functional", "search", "regression"}, enabled = false)
    public void searchItemWithATypo(){
        HomePage hp = new HomePage(getDriver());
        SearchPage sp = hp.searchItem("curl to straight shampoo"); //item is "curls to straight shampoo", even if "curls" is misspelled the search results should still give us matches for "shampoo"

        Assert.assertFalse(sp.lblNoMatchingResults.isDisplayed());
    }
}
