package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentProductCategoriesByMember extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ProductCategoryFound",description="PBI:127467")
	public void ProductCategoryFound() {
		
		String member = prop.getProperty("availableId");

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/getappointmentproductcategoriesbymember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0].Description", equalTo("Personal Training"))
						.body("Result[1].Description", equalTo("Tennis Lessons"));

	}
	
	@Test (testName="MemberNotFound",description="PBI:127467")
	public void MemberNotFound() {
		
		String member = prop.getProperty("availableId");

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/product/getappointmentproductcategoriesbymember/9"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(500)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
}
