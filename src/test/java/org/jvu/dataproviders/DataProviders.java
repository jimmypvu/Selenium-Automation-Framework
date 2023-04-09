package org.jvu.dataproviders;

import org.framework.utils.ExcelFileReader;
import org.testng.annotations.DataProvider;

public class DataProviders {
    /*****************************
     * REGISTRATION DATA PROVIDERS
     ****************************/
    @DataProvider(name = "InvalidRegistrations")
    public static Object[][] getInvalidRegistrationData(){
        Object[][] data = ExcelFileReader.getTestData("registration");
        return data;
    }

    /***********************
     * LOGIN DATA PROVIDERS
     **********************/
    @DataProvider(name = "InvalidLogins")
    public static Object[][] getInvalidLogins(){
        Object[][] data = ExcelFileReader.getTestData("login");
        return data;
    }

    @DataProvider(name = "ExistingProducts")
    public static Object[][] getExistingProducts(){
        Object[][] data = ExcelFileReader.getTestData("products");
        return data;
    }
}
