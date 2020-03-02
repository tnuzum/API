package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetPackageDetails extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Service - Online Sales Not Allowed",description="PBI:143538, 148154")
	public void service_OnlineSalesNotAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("noWebServiceId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("PriceRangeDtos"))
//						.body("Result.PriceRangeDtos[0]", hasKey("EndRange"))
//						.body("Result.PriceRangeDtos[0]", hasKey("PricePerUnit"))
//						.body("Result.PriceRangeDtos[0]", hasKey("StartRange"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.BasePrice", not(nullValue()))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						;
	}
	
	@Test (testName="Service - Online Sales Allowed",description="PBI:143538, 148154")
	public void service_OnlineSalesAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("paidServiceVId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");
		
				Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
//						.body("Result.AssociatedSessionDtos[0]", hasKey("ClubName"))
//						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemId"))
//						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemDescription"))
//						.body("Result.AssociatedSessionDtos[0].SessionType", equalTo("ClassCourse"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
//						Assert.assertEquals(js.getDouble("Result.AssociatedSessionDtos[0].BasePrice"), 15.00);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 15.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 5);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 15.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].EndRange"), 10);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[1].PricePerUnit"), 10.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[1].StartRange"), 6);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].EndRange"), 20);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[2].PricePerUnit"), 8.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[2].StartRange"), 11);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].EndRange"), 999);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[3].PricePerUnit"), 5.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[3].StartRange"), 21);
	}
	
	@Test (testName="Service - Inactive",description="PBI:143538, 148154")
	public void service_Inactive() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("inactiveServiceId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
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
						.body("Result.ItemId", not(nullValue()))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result.RedeemableClubs[0]", not(nullValue()));
	}
	
	@Test (testName="Training - Online Sales Not Allowed",description="PBI:143538, 148154")
	public void training_OnlineSalesNotAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("noWebTId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
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
						.body("Result.ItemId", not(nullValue()))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result.RedeemableClubs[0]", not(nullValue()));
	}
	
	@Test (testName="Training - Online Sales Allowed",description="PBI:143538, 148154")
	public void training_OnlineSalesAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("paidTId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
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
						.body("Result.ItemId", not(nullValue()))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result.RedeemableClubs[0]", not(nullValue()));
	}
	
	@Test (testName="Training - Inactive",description="PBI:143538, 148154")
	public void training_Inactive() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("inactiveTrainingId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result", hasKey("AssociatedSessionDtos"))
						.body("Result", hasKey("BasePrice"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
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
						.body("Result.ItemId", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()));
	}
	
	@Test (testName="Single Price Range",description="PBI:143538, 148154")
	public void singlePriceRange() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("selectableResourceTrainingId");
				int itemId = Integer.parseInt(i);
				String clubId = prop.getProperty("club1Id");

				Response res = given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.AssociatedSessionDtos", nullValue())
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
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
		
			String customerId = prop.getProperty("availableId");
			String i = prop.getProperty("tierPricingId");
			int itemId = Integer.parseInt(i);
			String clubId = prop.getProperty("club1Id");

			Response res = given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
	//					.log().body()
						.assertThat().statusCode(200)
						.body("Result.AssociatedSessionDtos", nullValue())
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.RedeemableClubs[0]", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getString("Result.BasePrice"), prop.getProperty("tierPricingBasePrice"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[0].EndRange"), prop.getProperty("tierPricingTier1EndRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[0].PricePerUnit"), prop.getProperty("tierPricingTier1Price"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[0].StartRange"), prop.getProperty("tierPricingTier1StartRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[1].EndRange"), prop.getProperty("tierPricingTier2EndRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[1].PricePerUnit"), prop.getProperty("tierPricingTier2Price"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[1].StartRange"), prop.getProperty("tierPricingTier2StartRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[2].EndRange"), prop.getProperty("tierPricingTier3EndRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[2].PricePerUnit"), prop.getProperty("tierPricingTier3Price"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[2].StartRange"), prop.getProperty("tierPricingTier3StartRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[3].EndRange"), prop.getProperty("tierPricingTier4EndRange"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[3].PricePerUnit"), prop.getProperty("tierPricingTier4Price"));
						Assert.assertEquals(js.getString("Result.PriceRangeDtos[3].StartRange"), prop.getProperty("tierPricingTier4StartRange"));
	}
	
	@Test (testName="NotServiceTypeV", description="PBI:143538, 148154")
	public void notServiceTypeV() {

		String customerId = prop.getProperty("availableId");
		String itemId = prop.getProperty("notServiceTypeVId");
		String clubId = prop.getProperty("club1Id");
		
				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"))
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	
	@Test (testName="InvalidPackageId", description="PBI:143538, 148154")// using Id of class instead of training or service
	public void invalidPackageId() {

				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeClId");
				String clubId = prop.getProperty("club1Id");
		
				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"))
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	
	@Test (testName="InvalidCustomerId", description="PBI:143538, 148154")
	public void invalidCustomerId() {
	
				int customerId = 999999;
				String itemId = prop.getProperty("paidServiceVId");
				String clubId = prop.getProperty("club1Id");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId+"/"+clubId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"))
						.body("Result", not(hasKey("AssociatedSessionDtos")))
						.body("Result", not(hasKey("BasePrice")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("DaysUntilExpiration")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("PriceRangeDtos")))
						.body("Result.RedeemableClubs[0]", not(hasKey("string")));
	}
	
}
