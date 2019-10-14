package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class Draft_GetPackageDetails extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	@Test (priority=1, description="Valid Inputs - Single Price Range")
	public void PBI143538_Test1() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/package/getPackageDetails/29947/1/4474")
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
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("StartRange"))
						.body("Result.RedeemableClubs[0]", hasKey("string"));
	}
	@Test (priority=2, description="Valid Inputs - Multiple Price Ranges")
	public void PBI143538_Test2() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/package/getPackageDetails/29947/1/4485")
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
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[0].PriceRangeDto", hasKey("StartRange"))
						.body("Result.PriceRangeDtos[1].PriceRangeDto", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[1].PriceRangeDto", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[1].PriceRangeDto", hasKey("StartRange"))
						.body("Result.PriceRangeDtos[2].PriceRangeDto", hasKey("EndRange"))
						.body("Result.PriceRangeDtos[2].PriceRangeDto", hasKey("PricePerUnit"))
						.body("Result.PriceRangeDtos[2].PriceRangeDto", hasKey("StartRange"))
						.body("Result.RedeemableClubs[0]", hasKey("string"));
	}
	@Test (priority=3, description="Package Not Available for Online Purchases")
	public void PBI143538_Test3() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/package/getPackageDetails/29947/1/4478")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
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
	@Test (priority=4, description="Invalid Package Id")// using Id of class instead of training or service
	public void PBI143538_Test4() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/package/getPackageDetails/29947/1/4473")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
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
	@Test (priority=5, description="Invalid Customer Id")
	public void PBI143538_Test5() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/package/getPackageDetails/2994700/1/4474")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
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
