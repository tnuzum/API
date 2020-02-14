package JonasFitness.API;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.not;
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
	
	@Test (priority=1,testName="WrongCredentials",description="PBI:139705")
	 
	public void wrongCredentials() {

			given()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \""+prop.getProperty("availableUserName")+"\","+
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
	
	@Test (priority=2,testName="MemberFound",description="PBI:139705")
	public void memberFound() {

			given()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \""+prop.getProperty("availableUserName")+"\","+
						  "\"Password\": \""+prop.getProperty("availablePassword")+"\","+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(200)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("Success"));	
	}
	
	@Test (priority=3,testName="AccountLocked",description="PBI:139705")
	public void accountLocked() {

			given()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \""+prop.getProperty("accountLockedUserName")+"\","+
						  "\"Password\": \""+prop.getProperty("accountLockedPassword")+"\","+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(401)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("AccountIsLocked"))
			.body("Result.CustomerId", equalTo(0));	
	}
	
	@Test (priority=4,testName="ForcePasswordChange",description="PBI:139705")
	public void forcePasswordChange() {

			given()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \""+prop.getProperty("passwordChangeUserName")+"\","+
						  "\"Password\": \""+prop.getProperty("passwordChangePassword")+"\","+
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
