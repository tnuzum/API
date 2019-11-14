package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetPackageDetails extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="SinglePriceRange",description="PBI:143538")
	public void singlePriceRange() {
 
		String member = prop.getProperty("activeMember1_CustomerId");
		String item = prop.getProperty("training24Id");
		String club = prop.getProperty("X-Club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+member+"/"+item+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("PriceRangeDtos"))
						.body("Result.PriceRangeDtos[0]", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[0]", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[0]", hasKey("StartRange"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.BasePrice", not(nullValue()))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()));
	}
	@Test (testName="MultiplePriceRanges",description="PBI:143538")
	public void multiplePriceRanges() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String item = prop.getProperty("training25Id");
		String club = prop.getProperty("X-Club1Id"); 

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+member+"/"+item+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("PriceRangeDtos"))
						.body("Result.PriceRangeDtos[0]", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[0]", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[0]", hasKey("StartRange"))
						.body("Result.PriceRangeDtos[1]", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[1]", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[1]", hasKey("StartRange"))
						.body("Result.PriceRangeDtos[2]", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[2]", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[2]", hasKey("StartRange"))
						.body("Result", hasKey("RedeemableClubs"));
	}
	@Test (testName="NotAvailableforOnlinePurchases", description="PBI:143538")
	public void notAvailableforOnlinePurchases() {

		String member = prop.getProperty("activeMember1_CustomerId");
		int item = 76;
		String club = prop.getProperty("X-Club1Id"); 
		
				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+member+"/"+item+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(500)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	@Test (testName="InvalidPackageId", description="PBI:143538")// using Id of class instead of training or service
	public void invalidPackageId() {

		String member = prop.getProperty("activeMember1_CustomerId");
		String item = prop.getProperty("class1Id");
		String club = prop.getProperty("X-Club1Id"); 
		
				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+member+"/"+item+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(500)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	@Test (testName="InvalidCustomerId", description="PBI:143538")
	public void invalidCustomerId() {
	
		String member = prop.getProperty("activeMember1_CustomerId");
		String item = prop.getProperty("service1Id");
		String club = prop.getProperty("X-Club1Id");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/9"+member+"/"+item+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(500)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	
}
