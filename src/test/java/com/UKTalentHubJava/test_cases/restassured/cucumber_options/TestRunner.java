package com.UKTalentHubJava.test_cases.restassured.cucumber_options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/UKTalentHubJava/test_cases/restassured/features",
        glue={"com/UKTalentHubJava/test_cases/step_definitions"})
public class TestRunner {
}
