package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import resources.base;

public class GetClubsByMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ClubsFound",description="PBI:127465")
	public void ClubsFound() {
		
		String member = prop.getProperty("availableId");

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"))
						.body("Result[1]", hasKey("Id"))
						.body("Result[1]", hasKey("Name"))
						.body("Result[0].Name", equalTo("Jonas Sports-Plex"))
						.body("Result[1].Name", equalTo("Studio Jonas"))
						.body("Result[2].Name", equalTo("Jonas Health and Wellness"))
						.body("Result[3].Name", equalTo("Jonas Fitness"));
	}
	
	@Test (testName="MemberNotFound",description="PBI:127465")
	public void MemberNotFound() {
		
		String member = prop.getProperty("availableId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getclubsbymember/9"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
}
