package com.UKTalentHubJava.test_cases.restassured.rest_assured_api_configs;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.oauth2;

public class GoRestUserConfig {
    public static String token = "b1e92e56efbf49ff974ef0b51f4ca1b104361754a95c48dee13951b7f8b3517d";
    public static String existentId = "19388";
    public static RequestSpecification goRestRequestSpec;

    @Before
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
}
