package unitTests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.not;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import payloads.MemberPL;
import resources.base;

public class AuthenticateMemberByUserCredentials extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (priority=1,testName="WrongCredentials",description="PBI:139705")
	 
	public void wrongCredentials() {
		
		String username = prop.getProperty("availableUserName");
		String password = "Wrongpassword";

			given()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(MemberPL.authenticateMemberByUserCredentials(username, password))
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//				.log().all()
				.assertThat().statusCode(401)
				.time(lessThan(60L),TimeUnit.SECONDS)			
				.body("Result.AuthenticationResult", equalTo("WrongCredentials"))
				.body("Result.CustomerId", equalTo(0));	
	}
	
	@Test (priority=2,testName="MemberFound",description="PBI:139705")
	public void memberFound() {
		
			String username = prop.getProperty("availableUserName");
			String password = prop.getProperty("availablePassword");

			given()
//			.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
			.body(MemberPL.authenticateMemberByUserCredentials(username, password))
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)			
				.body("Result.AuthenticationResult", equalTo("Success"));	
	}
	
	@Test (priority=3,testName="AccountLocked",description="PBI:139705")
	public void accountLocked() {
		
			String username = prop.getProperty("accountLockedUserName");
			String password = prop.getProperty("accountLockedPassword");

			given()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(MemberPL.authenticateMemberByUserCredentials(username, password))
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
	//			.log().all()
				.assertThat().statusCode(401)
				.time(lessThan(60L),TimeUnit.SECONDS)			
				.body("Result.AuthenticationResult", equalTo("AccountIsLocked"))
				.body("Result.CustomerId", equalTo(0));	
	}
	
	@Test (priority=4,testName="ForcePasswordChange",description="PBI:139705")
	public void forcePasswordChange() {
		
			String username = prop.getProperty("passwordChangeUserName");
			String password = prop.getProperty("passwordChangePassword");

			given()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(MemberPL.authenticateMemberByUserCredentials(username, password))
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
	//			.log().all()
				.assertThat().statusCode(401)
				.time(lessThan(60L),TimeUnit.SECONDS)			
				.body("Result.AuthenticationResult", equalTo("ForceChangePassword"))
				.body("Result.CustomerId", not(nullValue()));	
	}
}
