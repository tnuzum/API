package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SecurityPipeline extends base{
	
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
	
	@Test (testName="InactiveClub", description="PBI:144604")
	public void InactiveClub() {
		
				String member = prop.getProperty("availableId");
				String clubId = prop.getProperty("inactiveClubId");

					given()
//						.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials (Inactive Club)"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	
	@Test (testName="InvalidClub", description="PBI:132893")
	public void InvalidClub() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
						.header("X-ClubId", "3119860") // Club not on file
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials (Club Does Not Exist)"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	
	@Test (testName="InvalidCompany", description="PBI:132893")
	public void InvalidCompany() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", "1010") // Company not on file
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	
	@Test (testName="InvalidAPIKey", description="PBI:145817")
	public void InvalidAPIKey() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", "application/json")
						.header("X-Api-Key", "NOTVALIDB50A8F2BF7315812CF2A21690A7FF5FDA33A156C") // Not valid API Key
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	
	@Test (testName="NoAPIKey", description="PBI:145817")
	public void NoAPIKey() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", "application/json")
//					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
//						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("The ApiKey field is required."))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	
	@Test (testName="Endpoint Not Found", description="PBI:145817")
	public void endpointNotFound() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/NOTmember/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Endpoint was not found")) ;
	}
}
