package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.base;

public class GetPackageDetails extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="SinglePriceRange",description="PBI:143538")
	public void singlePriceRange() {
 
		String member = prop.getProperty("availableId");
		//String item = prop.getProperty("training24Id");
		int item = 36;
		String club = prop.getProperty("X-Club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+member+"/"+item+"/"+club)
						.then()
						.log().body();
	}
}
