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
	
	@Test (priority=1, description="Inactive Club")
	public void PBI145817_Test1() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
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
	@Test (priority=2, description="Club Does Not Exist")
	public void PBI145817_Test2() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

					given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
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
	@Test (priority=3, description="Company Does Not Exist")
	public void PBI132893_Test1() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

					given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
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
	@Test (priority=4, description="API Key Invalid")
	public void PBI132893_Test2() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

					given()
						.header("accept", "application/json")
						.header("X-Api-Key", "NOTVALIDB50A8F2BF7315812CF2A21690A7FF5FDA33A156C") // Not valid API Key
						.header("X-CompanyId", "1010")
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
/*	@Test (priority=4, description="Rate-counter Limitation")
	public void PBI132893_Test3() {
		//** This is not working because it doesn't always send 3 requests within 1 second
		String member = prop.getProperty("activeMember1_CustomerId");

				for (int i=1; i<5; i++) {

					given()
					.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
						.log().all()
						.assertThat().header("X-Rate-Limit-Limit", "1s");	
				}
						
	}*/
	
	
	
	
	
	
	
}
