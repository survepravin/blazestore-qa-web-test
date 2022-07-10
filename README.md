# QA Automation

## Framework architecture and design
Page Object Model(POM) with Cucumber. According to the Page Object Model, you should keep the tests and element locators separately. This will keep the code clean and easy to understand and maintain.The Page Object approach makes automation framework in a testing programmer friendly, more durable and comprehensive. Another important advantage is our Page Object Repository is Independent of Automation Tests. If you keep a separate repository for page objects, it helps us to use this repository for different purposes with different frameworks like you will be able to integrate this repository with other tools like JUnit/TestNG/Cucumber/etc.

## To run the automated tests
This automation framework is using *Selenium Webdriver* tool with *Cucumber*. To run the tests on your windows machine do the following.

#### STEPS
##### Follow commands to run tests via cmd
1. `git clone https://github.com/survepravin/blazestore-qa-web-test.git`
2. `cd backbase-qa-automation`
3. `mvn install -DskipTests`
4. `mvn test -Dheadless=on` or `mvn test` (will take values from config.properties)

##### Follow commands to run tests from eclipse or intelliJ
1. `git clone https://github.com/survepravin/blazestore-qa-web-test.git`
2. Import project in IDE as a maven project
3. Right click on pom.xml -> Run As -> Maven install
or 
Navigate to ``\src\test\java\runner`` and Run As _**TestRunner.java**_ as JUnit

**Note:** You can individually run each feature by navigating to _\src\test\java\features_ and Run As Cucumber feature. Local report won't be created via this approach.

#### REPORTS
Local report will be generated in `\target\cucumber-html-report.html`

Report is also published on cucumber cloud, once execution is completed. Unique URL will be generated, open that url in browser.
``` 
Example:
? View your Cucumber Report at:                                            ?
? https://reports.cucumber.io/reports/2957216a-6940-4392-9acc-e16fa7598746 ?
?                                                                          ?
? This report will self-destruct in 24h.                                   ?
? Keep reports forever: https://reports.cucumber.io/profile                ?
```

## Project Structure

### src/test/resources/config.properties
Web and Api configurations are stored in this file as key value pair. _**EnvironmentVariables.java**_ class will access all the values.

### src/main/java/utils
Utility classes that can be used across the project. *BrowserFactory.java* all browser can be configured here. *WebdriverWrapper.java* has common selenium common methods like explicit waits, etc.

### src/test/java/pages
Pages represents Application page, UI elements and methods are defined in these classes.

### src/test/java/testRunner
This includes Runner class. Run As JUnit to execute tests based on tags.You can configure cucumber options like report name, features, stepDefinitions, etc.

### src/test/java/features
Feature files as per functionality, includes '*.feature*' files.

### src/test/java/stepDefinitions
Cucumber Steps are implemented in this package.
