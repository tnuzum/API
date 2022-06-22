package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetClubsByMember extends base{
	
	String valueAssertions;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		valueAssertions = prop.getProperty("valueAssertions");
	}
	
	@Test (testName="ClubsFound",description="PBI:127465")
	public void ClubsFound() {
		
		String member = prop.getProperty("availableId");

		Response res =
				
				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getclubsbymember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"))
						.extract().response();
				
				if (valueAssertions.equals("true")) {
					JsonPath js = ReusableMethods.rawToJson(res);
					
					Assert.assertEquals(js.getString("Result.Name"), "Jonas Sports-Plex");
					Assert.assertEquals(js.getString("Result.Name"), "Studio Jonas");
					Assert.assertEquals(js.getString("Result.Name"), "Jonas Health and Wellness");
					Assert.assertEquals(js.getString("Result.Name"), "Jonas Fitness");
				}
	}
	
	@Test (testName="MemberNotFound",description="PBI:127465")
	public void MemberNotFound() {
		
		String member = prop.getProperty("availableId");

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getclubsbymember/9"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
}
