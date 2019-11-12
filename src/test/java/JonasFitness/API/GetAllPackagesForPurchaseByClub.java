package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class GetAllPackagesForPurchaseByClub extends base {
	

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="PackageFound",description="PBI:143540")
	public void PackageFound() { 
		String member = prop.getProperty("activeMember1_CustomerId");
		String club = prop.getProperty("X-ClubId");
		
					given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/"+member+"/"+club+"")
						.then()
//					    .log().body()
						.assertThat().statusCode(200)

//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BasePrice"))
						.body("Result[0]", hasKey("CategoryDescription"))
						.body("Result[0]", hasKey("DaysUntilExpiration"))
						.body("Result[0]", hasKey("ItemBarcodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("ItemId"))
						.body("Result[0]", hasKey("LongDescription"))
						.body("Result[0]", hasKey("PriceRangeDtos"))
						.body("Result[0]", hasKey("PriceRangeDtos"))
//						.body("Result.PriceRangeDtos", anyOf(anyOf(hasKey("EndRange"))))
//						.body("Result.PriceRangeDtos", anyOf(anyOf(hasKey("PricePerUnit"))))
//						.body("Result.PriceRangeDtos", anyOf(anyOf(hasKey("StartRange"))))
						.body("Result.ItemDescription", anyOf(hasItem("Golf Improvement Center")));// assertion that a specific package that is available at club is found

//						.time(lessThan(5L),TimeUnit.SECONDS)// bug reported for slow performance; un-comment once bug is fixed
						;
						// assert that a specific package that is available at club is found

	}
	@Test (testName="OnlineNotAllowed_PackageFound",description="PBI:143540")
	public void OnlineNotAllowed_PackageFound() { 
		String member = prop.getProperty("activeMember1_CustomerId");
		String club = prop.getProperty("X-ClubId");
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/"+member+"/"+club+"")
						.then()
//					.log().body()
						.assertThat().statusCode(200)
//				        .time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result.ItemDescription", anyOf(hasItem("Golf Club Fitting")));// assertion that a package that is not allowed for MSS purchase is contained in the response
				// use same package as negative test in getOnlinePackage...
	}
	@Test (testName="PackageNotFound",description="PBI:143540")
	public void PackageNotFound() { 
		String member = prop.getProperty("activeMember1_CustomerId");
		String club = prop.getProperty("X-ClubId");
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/"+member+"/"+club+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result.ItemDescription", anyOf(not(hasItem("Bhagya's Spa Service"))));// assertion that a specific package that is NOT available at club is NOT found
	}
}
