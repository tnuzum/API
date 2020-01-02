package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetActiveBooksByResourceType extends base{
	/*
	 * need a test with resource type id (query parm)
	 * 
	 * 
	 */
	@BeforeTest
	public void getData() throws IOException{
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
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BookType"))
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"));
	}
	
	@Test (testName="BookFound_WithResource",description="PBI:138964")
	public void BookFound_WithResource() {
		
		String resourceTypeId = prop.getProperty("resourceType4Id");

				given()
//					.log().all()
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
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BookType"))
						.body("Result[0]", hasKey("Description"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("Name"))
//					    .body("Result[0].Name", equalTo("Kalle, Bhagya") )
//					    .body("Result[1].Name", equalTo("Kalle1, Bhagya1") )
//					    .body("Result.Name", anyOf(hasItem("Kalle, Bhagya")))
//					    .body("Result.Name", anyOf(not(hasItem("Kalle, Prabhat"))))
						;
	}
	
}
