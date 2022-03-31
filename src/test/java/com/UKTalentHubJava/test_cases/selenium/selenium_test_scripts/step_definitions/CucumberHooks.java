package com.UKTalentHubJava.test_cases.selenium.selenium_test_scripts.step_definitions;

import com.UKTalentHubJava.screenshot_taker.ScreenshotTaker;
import com.UKTalentHubJava.utilities.Cucumber;
import io.cucumber.java.*;

import java.io.IOException;

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
    public void iInitialiseTheChromeBrowser(Scenario scenario) {
        base.driver = base.setup("chrome");
        base.logger.info("STARTED -- " + scenario.getName());
    }

    @After("@selenium")
    public void driverQuit() {
        base.driver.quit();
    }

    @BeforeStep("@selenium")
    public void beforeTest(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        currentStepDescr = Cucumber.getStepName(scenario, currentStepDefIndex);
    }

    @AfterStep("@selenium")
    public void afterStepCheck(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            new ScreenshotTaker(base.driver,
                    System.getProperty("user.dir") + "\\screenshots\\",
                    currentStepDescr);
        }
        base.logger.info("PASSED -- " + currentStepDescr);
        currentStepDefIndex += 1;
    }
}
