# QA-Automation-Framework
Java / Selenium / TestNG framework for web UI and functional testing built using POM & Page Factory design pattern, with custom Extent Reports, Excel reader with Apache Poi for data-driven tests, Retry Analyzer and screenshots on failed tests, LambdaTest cloud and Selenium Grid integration, and thread safe parallel testing

Smoke Suite Example/Demo: Smoke Suite - Watch Video
https://www.loom.com/share/f61c7468d99c434492bc0c1aafc0c1de

Regression Suite Demo: Regression Suite - Watch Video
https://www.loom.com/share/c842f2738b8e408b948798a5295449b7

LambdaTest Cloud Demo: Cloud Suite - Watch Video
https://www.loom.com/share/c30c519832174901ba7859571e52a7a6

Crossbrowser Tests in Parallel Demo: Crossbrowser Tests - Watch Video
https://www.loom.com/share/b7afa41c7c244fcf8be2761a7f74dd1c


Usage:
gradle clean test

gradle clean runSmoke

gradle clean runRegression

gradle clean runAPI

gradle clean runCucumber

to run tests on all browsers at once add "-Dallbrowsers=true" flag to gradle command

# if managing browser / config through system properties, add "-Dbrowser={browser}" flag to the gradle command, otherwise manage in the config properties file
where {browser} is one of [chrome, firefox, edge, grid-chrome, grid-firefox, grid-edge, lt-cloud] to designate browser when running tests via terminal. If no browser is designated in the config file or gradle flag it will default to Chrome

Can also run crossbrowser tests in parallel rather than test methods in parallel with ParallelCrossbrowser.xml configuration by passing {"browser"} parameters to @BeforeMethod BaseTest.launchBrowser(), remember to modify setDriver() method in launchBrowser() to allow crossbrowser tests to run in parallel

Timestamped Extent Reports with screenshots are automatically generated after each run in ./reports folder
Reports are attached with screenshot at failing test step along with logs and you can see which tests passed, failed, or were retried/skipped, can customize test graphs/charts as well

To run tests on LambdaTest cloud using different platform & browser configurations, update LambdaTestManager.lambdaTest() with your LambdaTest hub URL, username, and access key and use https://www.lambdatest.com/capabilities-generator/ to define & build test environment and update desired capabilities under LambdaTestManager.lambaTest()

To run tests via Selenium Grid, download selenium-server-4.8.0.jar file and start up a standalone Grid server with 'java -jar selenium-server-4.8.0.jar standalone', update the given grid URL in BaseTest.setDriver(), and run via gradle clean test -Dbrowser={grid-browser}

To pass data via an Excel .xlsx file for running tests with large datasets, ie. login combinations, payment card and billing information, zip codes and locality/localization information, etc., place your .xlsx file into the .src/test/resrouces/testdata/ directory, declare a data provider method in dataproviders.DataProviders class, and annotate the test using @Test (dataProvider = "DataProviderName" dataProviderClass = DataProviders.class)

