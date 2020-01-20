package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetPackageDetails extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Service - Online Sales Not Allowed",description="PBI:143538, 148154")
	public void service_OnlineSalesNotAllowed() {
 
		int member = 223;
		int item = 13;
		int club = 1;

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
	
	@Test (testName="Service - Online Sales Allowed",description="PBI:143538, 148154")
	public void service_OnlineSalesAllowed() {
 
		int member = 223;
		int itemId = 36;
		int club = 1;

				Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+member+"/"+itemId+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.AssociatedSessionDtos[0]", hasKey("ClubName"))
						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemBarcodeId"))
						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemDescription"))
						.body("Result.AssociatedSessionDtos[0].SessionType", equalTo("ClassCourse"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.AssociatedSessionDtos[0].BasePrice"), 15.00);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 15.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 1);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 15.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].EndRange"), 5);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[1].PricePerUnit"), 10.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].StartRange"), 5);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].EndRange"), 10);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[2].PricePerUnit"), 8.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].StartRange"), 10);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].EndRange"), 20);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[3].PricePerUnit"), 5.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].StartRange"), 15);
	}
	
	@Test (testName="Service - Inactive",description="PBI:143538, 148154")
	public void service_Inactive() {
 
		int member = 223;
		int item = 217;
		int club = 1;

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
	
	@Test (testName="Training - Online Sales Not Allowed",description="PBI:143538, 148154")
	public void training_OnlineSalesNotAllowed() {
 
		int member = 223;
		int item = 75;
		int club = 1;

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
	
	@Test (testName="Training - Online Sales Allowed",description="PBI:143538, 148154")
	public void training_OnlineSalesAllowed() {
 
		int member = 223;
		int item = 36;
		int club = 1;

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
	
	@Test (testName="Training - Inactive",description="PBI:143538, 148154")
	public void training_Inactive() {
 
		int member = 223;
		int item = 218;
		int club = 1;

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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
	
	@Test (testName="Single Price Range",description="PBI:143538, 148154")
	public void singlePriceRange() {
 
		String member = prop.getProperty("activeMember1_CustomerId");
		int itemId = 24;
		String club = prop.getProperty("X-Club1Id");

				Response res = given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+member+"/"+itemId+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.AssociatedSessionDtos", nullValue())
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 90.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 1);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 50.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
	}
	
	@Test (testName="MultiplePriceRanges",description="PBI:143538, 148154")
	public void multiplePriceRanges() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		int itemId = 25;
		
		int club = 1; 

			Response res = given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+member+"/"+itemId+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.AssociatedSessionDtos", nullValue())
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 60.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 99);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 45.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 15);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].EndRange"), 10);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[1].PricePerUnit"), 50.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].StartRange"), 10);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].EndRange"), 5);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[2].PricePerUnit"), 55.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].StartRange"), 5);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].EndRange"), 3);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[3].PricePerUnit"), 58.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].StartRange"), 3);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[4].EndRange"), 1);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[4].PricePerUnit"), 60.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[4].StartRange"), 1);
	}
	@Test (testName="NotServiceTypeV", description="PBI:143538, 148154")
	public void notServiceTypeV() {

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
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"))
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
	@Test (testName="InvalidPackageId", description="PBI:143538, 148154")// using Id of class instead of training or service
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
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"))
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
	@Test (testName="InvalidCustomerId", description="PBI:143538, 148154")
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
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"))
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
