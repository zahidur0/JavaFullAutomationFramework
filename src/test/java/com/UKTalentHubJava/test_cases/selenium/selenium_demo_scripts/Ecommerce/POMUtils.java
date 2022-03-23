package com.UKTalentHubJava.test_cases.selenium.selenium_demo_scripts.Ecommerce;

public class POMUtils {
    public static String formatProductName(String unformattedName){
        return unformattedName.split("-")[0].trim();
    }
}
