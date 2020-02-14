package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetProductByBook extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ProductFound",description="PBI:138968")
	public void ProductFound() {
		
				String resourceId = prop.getProperty("demoBookId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/bookview/getproductbybook/"+resourceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ProductId"))
						.body("Result[0]", hasKey("BarcodeId"))
						.body("Result[0]", hasKey("ProductDescription"))
						.body("Result[0]", hasKey("ProductType"))
						.body("Result[0]", hasKey("ProductLongDescription"))
						.body("Result[0]", hasKey("ProductCategoryId"))
						.body("Result[0]", hasKey("ProductCategoryDescription"))
						.body("Result[0]", hasKey("AllowedStartTimes"))
						.body("Result[0]", hasKey("BookingDuration"))
						.body("Result[0]", hasKey("MemberLimit"))
						.body("Result[0].ProductId", not(nullValue()))
						.body("Result[0].BarcodeId", not(nullValue()))
						.body("Result[0].ProductType", not(nullValue()))
						.body("Result[0].ProductCategoryId", not(nullValue()))
						.body("Result[0].AllowedStartTimes", not(nullValue()))
						.body("Result[0].BookingDuration", not(nullValue()))
						.body("Result[0].MemberLimit", not(nullValue()));	
	}
	
	@Test (testName="ProductNotFound",description="PBI:138968")
	public void ProductNotFound() {
		
				int resourceId = 99999;

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/bookview/getproductbybook/"+resourceId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
}
