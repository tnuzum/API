package JonasFitness.API;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class AuthenticateMemberByUserCredentials extends base {

	/*
	 * Other tests from PBI are...
	 * 1. Wrong Credentials - send bad password
	 * 2. Force Password Change - name 5670, pw 5670
	 * 3. Account Locked - setup locked user
	 * 
	 */
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="Authentication - Success",description="PBI:139705")
	public void ValidInput() {
		String activeMemberString = prop.getProperty("activeMember1_CustomerId");
		int member = Integer.parseInt(activeMemberString);// int conversation is required for use in assertion below

			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-ClubId"))
			.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
				.body("{"+
						  "\"Username\": \"rauto\","+
						  "\"Password\": \"Testing1!\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(200)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("Success"))
			.body("Result.CustomerId", equalTo(member));
			
	}
}
