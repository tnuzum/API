package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentResourceTypeByProduct extends base{
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="ProductFoundSelectableResources",description="PBI:127470")
	public void ProductFoundSelectableResources() {
		
			String training = prop.getProperty("selectableResourceTrainingId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/bookview/getappointmentresourcetypebyproduct/"+training)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.ItemHasSelectableResourceTypes",equalTo(true))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("Id"))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("Name"))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("ResourceTypeId"));
	}
	
	@Test (testName="ProductFoundNoSelectableResources",description="PBI:127470")
	public void ProductFoundNoSelectableResources() {
		
				String service = prop.getProperty("freePId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/bookview/getappointmentresourcetypebyproduct/"+service)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.ItemHasSelectableResourceTypes",equalTo(false))
						.body("Result.PrimarySelectableResourceType", nullValue());
	}
	
	@Test (testName="ProductNotFound",description="PBI:127470")
	public void ProductNotFound() {
		
		String service = prop.getProperty("freePId");

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/bookview/getappointmentresourcetypebyproduct/9"+service) // '9' is passed to make Product Category Id = not on file
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.ItemHasSelectableResourceTypes",equalTo(false))
						.body("Result.PrimarySelectableResourceType.Books[0]", nullValue())
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("Id")))
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("Name")))
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("ResourceTypeId")));


	}
}
