package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentProductByCategory extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Product Category Found",description="PBI:127468")
	public void productCategoryFound() {
		
		String associatedClub = prop.getProperty("club1Id");
		String prodCategory = prop.getProperty("pTProductCategoryId");
		
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/GetAppointmentProductsByCategory/"+associatedClub+"/"+prodCategory)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("MaximumCustomersPerAppointment"))
						.body("Result[0]", hasKey("MinimumCustomersPerAppointment"));
	}
	
	@Test (testName="Product Category Not Found",description="PBI:127468")
	public void productCategoryNotFound() {
		
		String associatedClub = prop.getProperty("club1Id");
		String prodCategory = "99999";
		
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/GetAppointmentProductsByCategory/"+associatedClub+"/"+prodCategory)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Product Category Required",description="PBI:127468")
	public void productCategoryRequired() {
		
		String associatedClub = prop.getProperty("club1Id");
		String prodCategory = prop.getProperty("NOTpTProductCategoryId");
		
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/GetAppointmentProductsByCategory/"+associatedClub+"/"+prodCategory)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for CategoryId."));
	}
	
	
	
	
	
	
	
	
}
