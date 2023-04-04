# QA-Automation-Framework
Java / Selenium / TestNG framework for web UI and functional testing built using POM & Page Factory design pattern, with custom Extent Reports, Excel reader with Apache Poi for data-driven tests, Retry Analyzer and screenshots on failed tests, and LambdaTest cloud and Selenium Grid integration


Usage:

gradle clean test

gradle clean test -Dbrowser={browser}

where {browser} is one of [chrome, firefox, MicrosoftEdge, grid-chrome, grid-firefox, grid-edge, ltCloud] to designate browser when running tests via terminal. If no browser is designated it will default to Chrome

Can also run browser tests in parallel with TestNG XML configuration by passing {"browser"} parameters to @BeforeMethod BaseTest.launchBrowser() (comment out 'systemProperty "browser" System.getProperty("browser")' in build.gradle file if running tests in parallel this way)

Timestamped Extent Reports with screenshots are automatically generated after each run in ./reports folder. If locally saved screenshots are required you can uncomment Screenshotter.takeScreenshot() method in onTestFailure listener and timestamped screenshots will be saved into ./screenshots folder

To run tests on LambdaTest cloud using different platform & browser configurations, update BaseTest.lambdaTest() with your LambdaTest hub URL, username, and access key and use https://www.lambdatest.com/capabilities-generator/ to define & build test environment and update desired capabilities under BaseTest.lambaTest()

To run tests via Selenium Grid, download selenium-server-4.8.0.jar file and start up a standalone Grid server with 'java -jar selenium-server-4.8.0.jar standalone', update the given grid URL in BaseTest.setDriver(), and run via gradle clean test -Dbrowser={grid-browser}

To pass data via an Excel .xlsx file for running tests with large datasets, ie. login combinations, payment card and billing information, zip codes and locality/localization information, etc., place your .xlsx file into the .src/test/resrouces/testdata/ directory, declare a data provider method in dataproviders.DataProviders class, and annotate the test using @Test (dataProvider = "DataProviderName" dataProviderClass = DataProviders.class)

Smoke Suite Example/Demo: <div style="position: relative; padding-bottom: 56.25%; height: 0;"><iframe src="https://www.loom.com/embed/070c01d7eb3d431da6d0ef5260956312" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;"></iframe></div>
Regression Suite Demo: <a href="https://www.loom.com/share/007bf31f02e4432b920cf9e9ec577da7">
    <p>Regression Suite - Watch Video</p>
    <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/007bf31f02e4432b920cf9e9ec577da7-with-play.gif">
  </a>
