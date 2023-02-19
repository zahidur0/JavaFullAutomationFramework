package com.UKTalentHubJava.test_cases.rest_assured.rest_assured_api_configs;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.oauth2;

public class GoRestUserConfig {
    public static String token;

    static {
        try {
            token = ReadToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String existentId = "486058";
    public static RequestSpecification goRestRequestSpec;

    @Before("@rest-assured")
    public static void setup() {
        AuthenticationScheme auth2 = oauth2(token);
        goRestRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public")
                .setBasePath("/v2/")
                .addHeader("Content-Type", "application/json")
                .setAuth(auth2)
                .build();

        RestAssured.requestSpecification = goRestRequestSpec;
    }

    private static String ReadToken() throws IOException {
        String propertyFilePath = "C:\\Users\\Zahidur\\Documents\\Coding\\Authentication Key for Go REST.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
            return reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Token file not found at " + propertyFilePath);
        }
    }
}
