package stepDefinitions;


import java.io.IOException;

import com.pages.baseapi.BaseTestApi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class APIStepDefinitions{

	BaseTestApi baseTestAPi;
	
	@Given("I hit the base uri with string users")
	public void i_hit_the_uri_with_string() throws IOException {
		baseTestAPi=new BaseTestApi();
		baseTestAPi.setup();
	}

	@When("I get non empty response")
	public void i_get_non_empty_response() {
		baseTestAPi.responseNotNull();
	}

	@Then("I verify status code is {int}")
	public void i_verify_status_code_is(Integer int1) {
		baseTestAPi.verifyResponeStatusCode(int1);
	}

}