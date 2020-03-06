package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class GetClassCoursePricing extends base {
	
	/*
	 * The assertions need changed to testng to extract response
	 * because hamcrest is not able to assert on the price values returned
	 */
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Item Found - Single Tax",description="PBI:155543")
	public void itemFound_SingleTax() { 
		
		int customerId = 248;
		int itemId = 229;

		given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
						.log().body();
	}
}
