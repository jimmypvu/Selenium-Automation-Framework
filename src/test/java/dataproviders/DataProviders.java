package dataproviders;

import org.testng.annotations.DataProvider;
import utility.ExcelReader;

public class DataProviders {
    /*****************************
     * REGISTRATION DATA PROVIDERS
     ****************************/
    @DataProvider(name = "InvalidRegistrationProviders")
    public static Object[][] getInvalidRegistrationData(){
        Object[][] data = ExcelReader.getTestData("registration");
        return data;
    }

    /***********************
     * LOGIN DATA PROVIDERS
     **********************/
    @DataProvider(name = "InvalidLoginProviders")
    public static Object[][] getInvalidLogins(){
        Object[][] data = ExcelReader.getTestData("login");
        return data;
    }
}
