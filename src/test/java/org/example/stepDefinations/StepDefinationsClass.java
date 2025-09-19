package org.example.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;

public class StepDefinationsClass {
    RequestSpecification reSpec;
    ResponseSpecification responseSpec;
    Response responseMsg;

    @Given("Add place payload")
    public void add_place_payload() {
        reSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                        .build();
              responseSpec=  given().log().all().spec(reSpec).formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                        .formParam("grant_type","client_credentials")
                        .formParam("scope","trust").response();

    }
    @When("user calls {string} with post http request")
    public void user_calls_with_post_http_request(String string) {
       responseMsg =responseSpec.when().post("oauthapi/oauth2/resourceOwner/token")
                .then().spec(responseSpec).extract().response();


    }
    @Then("the api call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer stausCode) {
          assertEquals(stausCode,((Integer) responseMsg.getStatusCode()));

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        JsonPath js = new JsonPath(String.valueOf(responseMsg));
        assertEquals(js.get(keyValue).toString(),expectedValue);

    }
}
