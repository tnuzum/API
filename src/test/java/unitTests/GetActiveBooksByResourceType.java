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
	
	@Test (testName="BookFound_NoResource",description="PBI:138964")
	public void BookFound_NoResource() {

			given()
//					.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
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
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
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
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("/api/v3/bookview/getactivebooksbyresourcetype")
						.then()
//					.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Nothing found"));
	}
	
}
