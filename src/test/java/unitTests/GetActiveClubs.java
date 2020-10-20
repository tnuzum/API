package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetActiveClubs extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ClubsFound",description="PBI:138959")
	public void ClubsFound() {

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getactiveclubs")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"))
						.body("Result[1]", hasKey("Id"))
						.body("Result[1]", hasKey("Name"));
//						.body("Result[0].Name", equalTo(prop.getProperty("club1Name")))
//						.body("Result[1].Name", equalTo(prop.getProperty("club2Name")))
//						.body("Result[2].Name", equalTo(prop.getProperty("club3Name")))
//						.body("Result[3].Name", equalTo(prop.getProperty("club4Name")));
	}
}
