package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetClubsByMember extends base{
	/*
	 * Other test from PBI is customer NOF 
	 * 
	 */
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test (testName="SearchMembers_LastName",description="PBI:127465")
	public void Test1() {
		String member = prop.getProperty("activeMember1_CustomerId");
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getclubsbymember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"))
						.body("Result[1]", hasKey("Id"))
						.body("Result[1]", hasKey("Name"));

	}
}
