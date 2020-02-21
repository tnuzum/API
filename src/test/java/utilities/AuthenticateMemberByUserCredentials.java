package utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


import io.restassured.RestAssured;
import resources.base;

public class AuthenticateMemberByUserCredentials extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

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
						  "\"Username\": \"1141138\","+
						  "\"Password\": \"1141138\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
			.log().all();	
	}

}
