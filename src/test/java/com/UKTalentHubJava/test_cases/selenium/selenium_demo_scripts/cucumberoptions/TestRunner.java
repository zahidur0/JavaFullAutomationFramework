package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.cucumberoptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/UKTalentHubJava/test_cases/selenium/selenium_demo_scripts/features",
        glue={"com/UKTalentHubJava/test_cases/selenium/selenium_demo_scripts/stepdefinitions"})
public class TestRunner {
}
