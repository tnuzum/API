package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.base;

public class _Draft_VerifyPackagePurchaseCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */
	
	public static String companyId;
	public static String clubId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Tier Pricing Package - Tier 1",description="PBI:159118")
	public void tierPricingPackage_Tier1() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("tierPricingId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("tierPriceingGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));
	}
	
	@Test (testName="Tier Pricing Package - Tier 2",description="PBI:159118")
	public void tierPricingPackage_Tier2() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("tierPricingId");
				int quantity = 6;
				double displayedGrandTotal = 55.35;
				
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Tier Pricing Package - Taxed Item",description="PBI:159118")
	public void tierPricingPackage_TaxedItem() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("taxSingleTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Free Package",description="PBI:159118")
	public void freePackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Quantity Zero",description="PBI:159118")
	public void quantityZero() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "0";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Quantity Zero Not Allowed"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	@Test (testName="Member Not Found",description="PBI:159118")
	public void memberNotFound() {
 
				String customerId = "99999";
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer Not Found"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	@Test (testName="Terminated Member",description="PBI:159118")
	public void terminatedMember() {
 
				String customerId = prop.getProperty("terminatedId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Collections Member",description="PBI:159118")
	public void collectionsMember() {
 
				String customerId = prop.getProperty("collectionsId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Frozen Member",description="PBI:159118")
	public void frozenMember() {
 
				String customerId = prop.getProperty("frozenId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Prospect",description="PBI:159118")
	public void prospect() {
 
				String customerId = prop.getProperty("prospectId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Credit Limit Exceeded",description="PBI:159118")
	public void creditLimitExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("TBD"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	@Test (testName="Credit Limit Not Exceeded",description="PBI:159118")
	public void creditLimitNotExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("freeTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Package Purchase Allowed"))
						.body("Result.AllowedToEnroll", equalTo(true));			
	}
	
	@Test (testName="Item Not Found",description="PBI:159118")
	public void itemNotFound() {
 
				String customerId = prop.getProperty("availableId");
				int itemId = 99999;
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Item Not Found"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	@Test (testName="Item Not Package",description="PBI:159118")
	public void itemNotPackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("notServiceTypeVId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Item Not Found"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	@Test (testName="Product Price Changed",description="PBI:159118")
	public void productPriceChanged() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("taxSingleTPrice");//using base price (Grand Total - taxes)
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/purchasecapability/verifypackagepurchasecapability/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Product Price Changed"))
						.body("Result.AllowedToEnroll", equalTo(false));			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
