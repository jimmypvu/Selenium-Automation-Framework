# QA-Automation-Framework
Java / Selenium / TestNG framework built using POM & Page Factory design pattern, with custom Extent Reports, Excel reader for data-driven tests, Retry Analyzer and screenshot on failed tests, and LambdaTest cloud integration for automationteststore.com test project


Usage:

gradle clean test

gradle clean test -Dbrowser=browser

-Dbrowser=[chrome, firefox, MicrosoftEdge, grid-chrome, grid-firefox, grid-edge, cloud] to designate browser when running tests via terminal, if no browser is designated it will default to Chrome

Extent Reports are automatically generated after each run in ./reports folder

Can also run browser tests in parallel with TestNG XML configuration by passing {"browser"} parameters to @BeforeMethod launchBrowser (comment out 'systemProperty "browser" System.getProperty("browser")' in build.gradle file if running tests in parallel this way)

To run tests on LambdaTest cloud using different platform & browser configurations, enter your LambdaTest hub URL and access key and use https://www.lambdatest.com/capabilities-generator/ to define & build test environment and update desired capabilities under BaseTest.lambaTest();
