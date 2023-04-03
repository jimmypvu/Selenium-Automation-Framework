package org.jvu.stepdefinitions.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty", "html:src/test/resources/reports/cucumberreport.html",
                "json:src/test/resources/reports/cucumberreport.json"},
        features = {"src/test/resources/features/"},
        glue = {"org.jvu.stepdefinitions"},
        tags = "@smoke or @regression or @e2e"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setupCucumber(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @DataProvider
    public Object[][] features(){
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        testNGCucumberRunner.finish();
    }
}