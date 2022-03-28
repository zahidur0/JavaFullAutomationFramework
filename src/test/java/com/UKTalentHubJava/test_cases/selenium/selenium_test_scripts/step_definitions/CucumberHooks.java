package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class CucumberHooks extends BaseClass {
    private int currentStepDefIndex = 0;
    private BaseClass base;
    String currentStepDescr;

    // This constructor is required to pass the driver from
    // the base class to this hook class (see cucumber picocontainer)
    public CucumberHooks(BaseClass base) {
        this.base = base;
    }

    @Before("@selenium")
    public void iInitialiseTheChromeBrowser() {
        base.driver = base.setup("chrome");
    }

    @After("@selenium")
    public void driverQuit() {
        base.driver.quit();
    }

    @BeforeStep("@selenium")
    public void beforeTest(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {

        // We can get the class field "delegate" from the Scenario class
        // and make it accessible. We then cast this field variable
        // to being a TestCaseState object and then get the field variable
        // testCase of type class TestCase. We set the accessible of the testCase
        // field to true. Then we get the TestCaseState object in TestCase class
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        TestCaseState tcs = (TestCaseState) f.get(scenario);

        Field f2 = tcs.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r = (TestCase) f2.get(tcs);

        List<PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        PickleStepTestStep currentStepDef = stepDefs
                .get(currentStepDefIndex);
        currentStepDescr = currentStepDef.getStep().getText();
        System.out.println(currentStepDescr);
    }

    @AfterStep("@selenium")
    public void screenShot(Scenario scenario) throws IOException {
        if (!scenario.isFailed()) {
            new ScreenshotTaker(base.driver,
                    System.getProperty("user.dir") + "\\screenshots\\",
                    currentStepDescr);
        }
        currentStepDefIndex += 1;
    }
}
