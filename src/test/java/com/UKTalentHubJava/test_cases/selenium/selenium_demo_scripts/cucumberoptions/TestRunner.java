package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.cucumberoptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", glue={"stepdefinitions"}, monochrome = true,
        plugin={"pretty", "json:target/JSONReports/report.json"})
public class TestRunner {
}
