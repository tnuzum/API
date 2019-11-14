package JonasFitness.API;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class AuthenticateMemberByUserCredentials extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/*
	 * Not using because currently the "failed login counter" does not reset after successful login
	 *  
	
	@Test (testName="WrongCredentials",description="PBI:139705")
	
	 // Sending wrong credentials before sending correct credentials
	 // This will prevent the account from being locked
	 
	public void wrongCredentials() {

			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \"timauto\","+
						  "\"Password\": \"WrongPassword\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(401)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("WrongCredentials"))
			.body("Result.CustomerId", equalTo(0));	
	}
	 */
	@Test (testName="MemberFound",description="PBI:139705")
	public void memberFound() {

			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \"timauto\","+
						  "\"Password\": \"Testing1!\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(200)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("Success"));	
	}
	
	@Test (testName="AccountLocked",description="PBI:139705")
	public void accountLocked() {

			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \"locked\","+
						  "\"Password\": \"Testing1!\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(401)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("AccountIsLocked"))
			.body("Result.CustomerId", equalTo(0));	
	}
	@Test (testName="ForcePasswordChange",description="PBI:139705")
	public void forcePasswordChange() {

			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \"change\","+
						  "\"Password\": \"1141121\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(401)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("ForceChangePassword"))
			.body("Result.CustomerId", not(nullValue()));	
	}
}
