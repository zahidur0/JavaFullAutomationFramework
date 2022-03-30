package com.UKTalentHubJava.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig() {
        File src = new File("./configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL() {
        String url = pro.getProperty("baseURL");
        return url;
    }

    public String getUsername() {
        String username = pro.getProperty("username");
        return username;
    }

    public String getPassword() {
        String password = pro.getProperty("password");
        return password;
    }

    public String getChromePath() {
        String chromepath = pro.getProperty("chromepath");
        return chromepath;
    }

    public String getIEPath() {
        String iepath = pro.getProperty("iepath");
        return iepath;
    }

    public String getFirefoxPath() {
        String firefoxpath = pro.getProperty("firefoxpath");
        return firefoxpath;
    }


    public String getDriverPath() {
        String driverPath = pro.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }

    public String getQualitestMainUrl() {
        String url = pro.getProperty("qualitestMainUrl");
        if (url != null) return url;
        else throw new RuntimeException("qualitestMainUrl not specified in the config.properties file.");
    }

    public String getContactUsButtonPath() {
        String contactUsButtonPath = pro.getProperty("contactUsButtonPath");
        if (contactUsButtonPath != null) return contactUsButtonPath;
        else throw new RuntimeException("contactUsButtonPath not specified in the config.properties file.");
    }

    public String getFirstNameId() {
        String firstNameId = pro.getProperty("firstNameId");
        if (firstNameId != null) return firstNameId;
        else throw new RuntimeException("firstNameId not specified in the config.properties file.");
    }

    public String getLastNameId() {
        String lastNameId = pro.getProperty("lastNameId");
        if (lastNameId != null) return lastNameId;
        else throw new RuntimeException("lastNameId not specified in the config.properties file.");
    }

    public String getCompanyNameId() {
        String companyNameId = pro.getProperty("companyNameId");
        if (companyNameId != null) return companyNameId;
        else throw new RuntimeException("companyNameId not specified in the config.properties file.");
    }

    public String phoneId() {
        String phoneId = pro.getProperty("phoneId");
        if (phoneId != null) return phoneId;
        else throw new RuntimeException("phoneId not specified in the config.properties file.");
    }

    public String getHelpTextboxId() {
        String helpTextboxId = pro.getProperty("helpTextboxId");
        if (helpTextboxId != null) return helpTextboxId;
        else throw new RuntimeException("helpTextboxId not specified in the config.properties file.");
    }

    public String getEmailId() {
        String emailId = pro.getProperty("emailId");
        if (emailId != null) return emailId;
        else throw new RuntimeException("emailId not specified in the config.properties file.");
    }

    public String getRadioButtonsId() {
        String radioButtonsId = pro.getProperty("radioButtonsId");
        if (radioButtonsId != null) return radioButtonsId;
        else throw new RuntimeException("radioButtonsId not specified in the config.properties file.");
    }

    public String getLocationId() {
        String locationId = pro.getProperty("locationId");
        if (locationId != null) return locationId;
        else throw new RuntimeException("locationId not specified in the config.properties file.");
    }

    public String getTrelloLoginPageUrl() {
        String trelloLoginPageUrl = pro.getProperty("trelloLoginPageUrl");
        if (trelloLoginPageUrl != null) return trelloLoginPageUrl;
        else throw new RuntimeException("trelloLoginPageUrl not specified in the config.properties file.");
    }

    public String getLoginId() {
        String loginId = pro.getProperty("loginId");
        if (loginId != null) return loginId;
        else throw new RuntimeException("loginId not specified in the config.properties file.");
    }

    public String getPasswordId() {
        String passwordId = pro.getProperty("passwordId");
        if (passwordId != null) return passwordId;
        else throw new RuntimeException("passwordId not specified in the config.properties file.");
    }

    public String getLoginButtonId() {
        String loginButtonId = pro.getProperty("loginButtonId");
        if (loginButtonId != null) return loginButtonId;
        else throw new RuntimeException("loginButtonId not specified in the config.properties file.");
    }

    public String getLoginErrorMessageXPath() {
        String loginErrorMessageXPath = pro.getProperty("loginErrorMessageXPath");
        if (loginErrorMessageXPath != null) return loginErrorMessageXPath;
        else throw new RuntimeException("loginErrorMessageXPath not specified in the config.properties file.");
    }
}


	





