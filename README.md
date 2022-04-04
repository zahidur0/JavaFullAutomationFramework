<h1> <u>Java Automation Framework </u> </h1>

This project uses the following tools:

<ul>
    <li>Cucumber</li>
    <li>TestNG</li>
    <li>Junit</li>
    <li>REST Assured</li>
    <li>Selenium</li>
    <li>Log4j</li>
    <li>Allure Reports</li>
    <li>Extent Reports</li>
    <li>Maven</li>
</ul>

Cucumber tests
that use REST Assured and Selenium can be found in `../test_cases/rest_assured` and `../test_cases/selenium` respectively. TestNG tests that can be found in `../test_cases/testng`.

`src/test/java/com/UKTalentHubJava/test_data` contains Excel spreadsheets and SQL databases used by test classes found in `../test_cases/testng`. `src/test/java/com/UKTalentHubJava/utilities` contains classes that are used in configuration of variables and provide methods that are used by test classes. In `resources` we have XML and JSON schema used in REST Assured schema validation and the Log4j XML file used for logging configuration.

<h3><u>How to run tests</u></h3>

There are multiple methods that can be used to run tests. Tests can be run:

- Directly from the feature file
- Using the Cucumber test runner (only for Cucumber tests)
- Using Maven (i.e. `mvn clean test`)

<h3><u>How to view reports</u></h3>

To view the Allure report you will need to first install Allure (https://docs.qameta.io/allure/). Then navigate to the project directory where the `allure-results` folder exists and enter `allure serve allure-reports`.

Extent reports are automatically generated after Testng tests are ran. They can be found in the `test-output` 

<h3><u>REST Assured Codebase</u></h3>

Official REST Assured documentation can be found at: https://github.com/rest-assured/rest-assured/wiki/Usage.

<h4> <u>
What is REST Assured?
</u> </h4>

REST Assured is a Java domain-specific language that allows you to easily test REST based services. It provides the capability to easily validate and verify information returned by API responses.

<h4><u>Codebase Prerequisites</u></h2>

A large number of these tests make use of the GoRest API (https://gorest.co.in/).

Before running the tests you will need to get a bearer token (OAuth2) from https://gorest.co.in/consumer/login. Set the `token` in `GoRestUserConfig` class to your token string value (do not share your token with anyone and ensure you remove it if you make the code publicly accessible). Your token will give you access to a personal set of data that can be fetched using a GET request and modified using a POST/PUT request etc. If you wish to add/update entries in the database, the email you add must be unique across the whole database. Some entries in the database may not be visible to you because they are associated with another token. Thus, your response may return as `[{"field":"email","message":"has already been taken"}]`, meaning the email you posted was not unique.


You will also find a variable named `existentId` in the `GoRestUserConfig` class (found in `src/test/java/rest_assured_api_configs/GoRestUserConfig.java`). Some Cucumber tests require `existentId` in order to successfully run. `existentId` needs to be set to an id of an entry in the GoRest database.

If you wish to run all test cases sequentially then you can navigate to the project directory in the terminal and run `mvn clean test`. For the build to be successfully there is some setup required:

- Add an initial test entry with a unique email
- Change `existentId` to the id of the initial entry test entry you just created
- Navigate to the `TCR_04` class. In the `I am able to validate the entry has been deleted` step, uncomment the final line and set `GoRestUserConfig.existentId` to a new already existing id in the GoRest database.

<h4> <u> 
POM Requirements
</u> </h4>
The following dependencies are used to implement to cucumber framework:

```
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.2.3</version>
</dependency>

<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>7.2.3</version>
    <scope>test</scope>
</dependency>
```
In order to use REST Assured you will need:
```
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>4.5.1</version>
    <scope>test</scope>
</dependency>
```
If you want to validate a JSON response against a JSON schema then you will need:
```
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>4.5.1</version>
</dependency>
```

To verify data from a response, we use the assertion matching framework- Hamcrest:
```
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest-all</artifactId>
    <version>1.3</version>
    <scope>test</scope>
</dependency>
```

To create JSON from a POJO the following dependency is required:
```
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.9.0</version>
</dependency>
```
To create XML from a POJO the following dependency is required:
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.1</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.13.1</version>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.13.1</version>
</dependency>
```
To create a POJO from XML you will need:
```
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>3.0.1</version>
</dependency>
```






