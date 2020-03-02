package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.hasItem;
//import static org.hamcrest.Matchers.hasKey;
//import static org.hamcrest.Matchers.not;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAllPackagesForPurchaseByClub extends base {
	

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="PackageFound",description="PBI:143540")
	public void packageFound() { 
		
		String customerId = prop.getProperty("availableId");
		String clubId = prop.getProperty("club1Id");
		
					given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/"+customerId+"/"+clubId+"")
						.then()
//					    .log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BasePrice"))
						.body("Result[0]", hasKey("CategoryDescription"))
						.body("Result[0]", hasKey("DaysUntilExpiration"))
						.body("Result[0]", hasKey("ItemId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("ItemId"))
						.body("Result[0]", hasKey("LongDescription"))
						.body("Result[0]", hasKey("PriceRangeDtos"))
						.body("Result[0]", hasKey("PriceRangeDtos"));
	}
	
	@Test (testName="OnlineNotAllowed_PackageFound",description="PBI:143540")
	public void onlineNotAllowed_PackageFound() { 
		
		String customerId = prop.getProperty("availableId");
		String clubId = prop.getProperty("club1Id");
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getallpackagesforpurchasebyclub/"+customerId+"/"+clubId+"")
						.then()
//					.log().body()
						.assertThat().statusCode(200)
//				        .time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result.ItemDescription", anyOf(hasItem("Day Pass")));// assertion that a package that is not allowed for MSS purchase is contained in the response
				// use same package as negative test in getOnlinePackage...
	}
	
	@Test (testName="PackageNotFound",description="PBI:143540")
	public void packageNotFound() { 
		
		String member = prop.getProperty("availableId");
		String club = prop.getProperty("X-Club1Id");
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/"+member+"/"+club+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result.ItemDescription", anyOf(not(hasItem("Not A Real Package"))));// assertion that a specific package that is NOT available at club is NOT found
	}
	
	@Test (testName="Customer Not Found",description="PBI:143540")
	public void customerNotFound() { 
		
		int customerId = 2360000;
		String clubId = prop.getProperty("club1Id");
		
					given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getallpackagesforpurchasebyclub/"+customerId+"/"+clubId+"")
						.then()
//					    .log().body()
					    .assertThat().statusCode(500)
					    .body("Message", equalTo("Internal server error - Customer Not Found"));
	}
}
