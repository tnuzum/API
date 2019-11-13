package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class SecurityPipeline extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/* ** currently no inactive club in company 236 **
	@Test (testName="InactiveClub", description="PBI:144604")
	public void InactiveClub() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

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
		
		String member = prop.getProperty("activeMember1_CustomerId");

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
		
		String member = prop.getProperty("activeMember1_CustomerId");

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
		
		String member = prop.getProperty("activeMember1_CustomerId");

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
		
		String member = prop.getProperty("activeMember1_CustomerId");

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
}
