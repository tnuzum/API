package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SecurityPipeline extends base{

	@BeforeTest
	public void getData() {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/* ** currently no inactive club in company 236 **
	@Test (testName="InactiveClub", description="PBI:144604")
	public void InactiveClub() {
		
		String member = prop.getProperty("availableId");

					given()
//						.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", "311986")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials (Inactive Club)"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	*/
	@Test (testName="InvalidClub", description="PBI:132893")
	public void InvalidClub() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", "3119860") // Club not on file
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials (Club Does Not Exist)"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	@Test (testName="InvalidCompany", description="PBI:132893")
	public void InvalidCompany() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", "1010") // Company not on file
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	@Test (testName="InvalidAPIKey", description="PBI:145817")
	public void InvalidAPIKey() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", "NOTVALIDB50A8F2BF7315812CF2A21690A7FF5FDA33A156C") // Not valid API Key
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("Invalid authorization credentials"))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	@Test (testName="NoAPIKey", description="PBI:145817")
	public void NoAPIKey() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", prop.getProperty("accept"))
//					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(5L),TimeUnit.SECONDS)
//						.statusLine("HTTP/1.1 401 Unauthorized")
						.body("Message", equalTo("The ApiKey field is required."))
					    .body("Result", not(hasKey("Address")))
					    .body("Result", not( hasKey("Name")));
	}
	@Test (testName="Endpoint Not Found", description="PBI:145817")
	public void endpointNotFound() {
		
		String member = prop.getProperty("availableId");

					given()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/NOTmember/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Endpoint was not found")) ;
	}
}
