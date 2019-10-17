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
	}
	@Test (testName="Authentication - Success",description="PBI:139705")
	public void ValidInput() {
		String activeMemberString = prop.getProperty("activeMember1_CustomerId");
		int member = Integer.parseInt(activeMemberString);
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

			given()
//			.log().all()
			.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
			.header("X-CompanyId", "101")
			.header("X-ClubId", "1")
			.header("Content-Type", "application/json")
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
