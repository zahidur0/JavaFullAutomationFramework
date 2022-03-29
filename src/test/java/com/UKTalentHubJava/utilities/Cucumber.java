package com.UKTalentHubJava.utilities;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Cucumber {
    public static String getStepName(Scenario scenario, int currentStepDefIndex) throws NoSuchFieldException, IllegalAccessException {
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
        return currentStepDef.getStep().getText();
    }
}
