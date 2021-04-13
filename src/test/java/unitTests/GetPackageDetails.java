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
	
	@Test (testName="Paid Service",description="PBI:143538, 148154")
	public void paidService() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("paidServiceVId");
				int itemId = Integer.parseInt(i);
		
				Response res = given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
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
	
	@Test (testName="Paid Training",description="PBI:143538, 148154")
	public void paidTraining() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("paidTId");
				int itemId = Integer.parseInt(i);

				Response res = given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				Assert.assertEquals(js.getDouble("Result.ItemId"), itemId);
	}
	
	@Test (testName="Free Training",description="PBI:143538, 148154")
	public void freeTraining() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("freeTId");
				int itemId = Integer.parseInt(i);

				Response res = given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 999);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
						Assert.assertEquals(js.getString("Result.RedeemableClubs[0]"), "Jonas Sports-Plex");
	}
	
	@Test (testName="Free Service",description="PBI:143538, 148154")
	public void freeService() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("freeSVId");
				int itemId = Integer.parseInt(i);

				Response res = given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 999);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
						Assert.assertEquals(js.getString("Result.RedeemableClubs[0]"), "Jonas Sports-Plex");
	}
	
	@Test (testName="Free Punchcard",description="PBI:143538, 148154")
	public void freePunchcard() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("freePId");
				int itemId = Integer.parseInt(i);

				Response res = given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 999);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 0.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
						Assert.assertEquals(js.getString("Result.RedeemableClubs[0]"), "Jonas Sports-Plex");
	}
	
	@Test (testName="Paid Punchcard",description="PBI:143538, 148154")
	public void paidPunchcard() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("paidPId");
				int itemId = Integer.parseInt(i);

				Response res = given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
					.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.AssociatedSessionDtos[0]", hasKey("ClubName"))
						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemBarcodeId"))
						.body("Result.AssociatedSessionDtos[0]", hasKey("ItemDescription"))
						.body("Result.AssociatedSessionDtos[0].SessionType", equalTo("ClassCourse"))
						.body("Result", hasKey("CategoryDescription"))
						.body("Result", hasKey("DaysUntilExpiration"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result.ItemId", equalTo(itemId))
						.body("Result", hasKey("LongDescription"))
						.body("Result", hasKey("RedeemableClubs"))
						.body("Result.DaysUntilExpiration", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
					Assert.assertEquals(js.getDouble("Result.ItemId"), itemId)
					;
	}
	
	@Test (testName="Single Price Range",description="PBI:143538, 148154")
	public void singlePriceRange() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("selectableResourceTrainingId");
				int itemId = Integer.parseInt(i);

				Response res = given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BasePrice"), 90.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].EndRange"), 1);
						Assert.assertEquals(js.getDouble("Result.PriceRangeDtos[0].PricePerUnit"), 50.00);
						Assert.assertEquals(js.getInt("Result.PriceRangeDtos[0].StartRange"), 1);
	}
	
	@Test (testName="Tier Pricing",description="PBI:143538, 148154")
	public void tierPricing() {
		
			String customerId = prop.getProperty("availableId");
			String i = prop.getProperty("tierPricingId");
			int itemId = Integer.parseInt(i);

			Response res = given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()))
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
	
	@Test (testName="Service - Online Sales Not Allowed",description="PBI:143538, 148154", enabled = true)
	public void service_OnlineSalesNotAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("noWebServiceId");
				int itemId = Integer.parseInt(i);

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()));
	}
	
	@Test (testName="Training - Online Sales Not Allowed",description="PBI:143538, 148154", enabled = true)
	public void training_OnlineSalesNotAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("noWebTId");
				int itemId = Integer.parseInt(i);

				given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()));
	}
	
	@Test (testName="Service - Inactive",description="PBI:143538, 148154", enabled = true)
	public void service_Inactive() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("inactiveServiceId");
				int itemId = Integer.parseInt(i);

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()));
	}
	
	@Test (testName="Training - Inactive",description="PBI:143538, 148154", enabled = true)
	public void training_Inactive() {
 
				String customerId = prop.getProperty("availableId");
				String i = prop.getProperty("inactiveTrainingId");
				int itemId = Integer.parseInt(i);

				given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
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
						.body("Result.RedeemableClubs", not(nullValue()));
	}
	
	@Test (testName="NotServiceTypeV", description="PBI:143538, 148154")
	public void notServiceTypeV() {

		String customerId = prop.getProperty("availableId");
		String itemId = prop.getProperty("notServiceTypeVId");
		
				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"));
	}
	
	@Test (testName="InvalidPackageId", description="PBI:143538, 148154")// using Id of class instead of training or service
	public void invalidPackageId() {

				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeClId");
		
				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"));
	}
	
	@Test (testName="InvalidCustomerId", description="PBI:143538, 148154")
	public void invalidCustomerId() {
	
				int customerId = 999999;
				String itemId = prop.getProperty("paidServiceVId");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"));
	}
	
	@Test (testName="InvalidClubId", description="PBI:143538, 148154", enabled = true)
	public void invalidClubId() {
	
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidServiceVId");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", 99999)
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(401)
						.body("Message", equalTo("Invalid authorization credentials (Club Does Not Exist)"));
	}
	
	@Test (testName="TrainingNotAvailableAtClub", description="PBI:143538, 148154", enabled = true)
	public void trainingNotAvailableAtClub() {
	
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTClub1Id");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club2Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"));
	}
	
	@Test (testName="ServiceNotAvailableAtClub", description="PBI:143538, 148154", enabled = true)
	public void serviceNotAvailableAtClub() {
	
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidServiceVClub1Id");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club2Id"))
					.when()
					.get("/api/v3/package/getPackageDetails/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Package not found"));
	}
	
}
