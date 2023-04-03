package org.jvu.dataproviders;

import org.testng.annotations.DataProvider;
import org.jvu.utils.ExcelFileReader;

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
}
