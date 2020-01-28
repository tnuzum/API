package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAvailableResourceTypesByClub extends base{

	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ResourceTypesFound",description="PBI:138961")
	public void ResourceTypesFound() {

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/bookview/getavailableresourcetypesbyclub")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0]", hasKey("ResourceTypeId"))
						.body("Result[0]", hasKey("ResourceTypeName"))
						.body("Result[1]", hasKey("ResourceTypeDescription"))
						.body("Result[1]", hasKey("ResourceTypeId"))
						.body("Result[1]", hasKey("ResourceTypeName"))
						.body("Result[2]", hasKey("ResourceTypeDescription"))
						.body("Result[2]", hasKey("ResourceTypeId"))
						.body("Result[2]", hasKey("ResourceTypeName"));
	}
	@Test (testName="ResourceTypesNotFound",description="PBI:138961")
	public void ResourceTypesNotFound() {

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club3Id"))
					.when()
						.get("/api/v3/bookview/getavailableresourcetypesbyclub")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
}
