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

public class GetActiveBooksByResourceType extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="BookFound_NoResource",description="PBI:138964")
	public void BookFound_NoResource() {

			given()
//					.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/bookview/getactivebooksbyresourcetype")
						.then()
//					.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BookType"))
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"));
	}
	
	@Test (testName="BookFound_WithResource",description="PBI:138964")
	public void BookFound_WithResource() {
		
		String resourceTypeId = prop.getProperty("resourcePTId");

				given()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("/api/v3/bookview/getactivebooksbyresourcetype")
						.then()
//					.log().body()
						.assertThat().statusCode(200)
						.body("Result[0]", hasKey("BookType"))
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"));
	}
	
	@Test (testName="Resource Not Found",description="PBI:138964")
	public void resourceNotFound() {
		
		int resourceTypeId = 123000;

			given()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("/api/v3/bookview/getactivebooksbyresourcetype")
						.then()
//					.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Nothing found"));
	}
	
}
