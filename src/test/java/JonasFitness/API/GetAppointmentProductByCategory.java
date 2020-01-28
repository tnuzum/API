package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentProductByCategory extends base {

	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:127468")
	public void ValidInput() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String prodCategory = prop.getProperty("prodCategory7Id");
		
				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/GetAppointmentProductsByCategory/"+associatedClub+"/"+prodCategory)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("MaximumCustomersPerAppointment"))
						.body("Result[0]", hasKey("MinimumCustomersPerAppointment"));
	}
}
