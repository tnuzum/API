package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableMethods;
import resources.base;

public class VerifyPackagePurchaseCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */
	
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
	
	@Test (testName="Paid Training",description="PBI:159118")
	public void paidTraining() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));
	}
	
	@Test (testName="Paid Punchcard",description="PBI:159118", enabled = false)
	public void paidPunchcard() {
		
		// !! Bug 168874 created for missing error message
		
				ReusableMethods.myWait(250); // waiting to avoid 429 rate counter exceeded when tests execute too fast
 
				String customerId = "236";//prop.getProperty("availableId");
				String itemId = prop.getProperty("paidPId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidPBasePrice");
				
			given()
				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Paid ServiceV",description="PBI:159118")
	public void paidServiceV() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidServiceVId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));
	}
	
	@Test (testName="Free Training",description="PBI:159118")
	public void freeTraining() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Free Punchcard",description="PBI:159118")
	public void freePunchcard() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freePId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freePPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Free Service",description="PBI:159118")
	public void freeService() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeSVId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeSVPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Tier Pricing Package - Tier 1",description="PBI:159118")
	public void tierPricingPackage_Tier1() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("tierPricingId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("tierPriceingGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));
	}
	
	@Test (testName="Tier Pricing Package - Tier 2",description="PBI:159118")
	public void tierPricingPackage_Tier2() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("tierPricingId");
				int quantity = 6;
				String dGT = prop.getProperty("tierPricingTier2Price");
				String tr = prop.getProperty("tierPricingClub1TaxRate");
				double grandTotal = Double.parseDouble(dGT);
				double taxRate = Double.parseDouble(tr);
				double calcTotal = (grandTotal * quantity);
				double calcTaxTotal =  (calcTotal * taxRate);
				double displayedGrandTotal = (calcTotal + calcTaxTotal);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Club Pricing - Club 1",description="PBI:159118")
	public void clubPricingClub1() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidSVClubPriceId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidSVClubPriceClub1Price");
				
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Club Pricing - Club 2",description="PBI:159118")
	public void clubPricingClub2() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidSVClubPriceId");
				int quantity = 1;
				int clubId = 2;
				String displayedGrandTotal = prop.getProperty("paidSVClubPriceClub2Price");
				
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club2Id"))
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Club Pricing - Club 3",description="PBI:159118")
	public void clubPricingClub3() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidSVClubPriceId");
				int quantity = 1;
				int clubId = 3;
				String displayedGrandTotal = prop.getProperty("paidSVClubPriceClub3Price");
				
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club3Id"))
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Taxed Item",description="PBI:159118")
	public void taxedItem() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				int quantity = 1;
				String dGT = prop.getProperty("taxSingleTPrice");
				String tr = prop.getProperty("taxSingleTClub1TaxRate");
				double grandTotal = Double.parseDouble(dGT);
				double taxRate = Double.parseDouble(tr);
				double calcTotal = (grandTotal * quantity);
				double calcTaxTotal =  (calcTotal * taxRate);
				double displayedGrandTotal = (calcTotal + calcTaxTotal);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));				
	}

	@Test (testName="Quantity Zero",description="PBI:159118")
	public void quantityZero() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "0";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("NonZeroQuantityRequired"));				
	}
	
	@Test (testName="Member Not Found",description="PBI:159118")
	public void memberNotFound() {
 
				String customerId = "99999";
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("CustomerNotFound"));				
	}
	
	@Test (testName="Terminated Member",description="PBI:159118")
	public void terminatedMember() {
 
				String customerId = prop.getProperty("terminatedId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Collections Member",description="PBI:159118")
	public void collectionsMember() {
 
				String customerId = prop.getProperty("collectionsId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Frozen Member",description="PBI:159118")
	public void frozenMember() {
 
				String customerId = prop.getProperty("frozenId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Prospect",description="PBI:159118")
	public void prospect() {
 
				String customerId = prop.getProperty("prospectId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Credit Limit Exceeded",description="PBI:159118")
	public void creditLimitExceeded() {
		
				// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));			
	}
	
	@Test (testName="Credit Limit Not Exceeded",description="PBI:159118")
	public void creditLimitNotExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("freeTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(true))
				.body("PackageStatus", equalTo("PurchaseAllowed"));		
	}
	
	@Test (testName="Item Not Found",description="PBI:159118")
	public void itemNotFound() {
 
				String customerId = prop.getProperty("availableId");
				int itemId = 99999;
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().all()
				.assertThat().statusCode(200)// purchase call returns 404 this 200 is not used
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Item Not Package",description="PBI:159118")
	public void itemNotPackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("notServiceTypeVId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)// purchase call returns 404 this 200 is not used
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("ItemNotFound"));			
	}
	
	@Test (testName="Product Price Changed",description="PBI:159118")
	public void productPriceChanged() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				String quantity = "1";
				String displayedGrandTotal = prop.getProperty("taxSingleTPrice");//using base price (Grand Total - taxes)
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("ProductPriceChanged"));			
	}
	
	@Test (testName="Package Quantity Limit Exceeded",description="PBI:159118", enabled = false)
	public void packageQuantityLimitExceeded() {
		
		// researching - this call does not currently prevent purchase
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("limit10PId");
				int quantity = 15;
				String dGT = prop.getProperty("limit10PBasePrice");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
			.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToPurchase", equalTo(false))
				.body("PackageStatus", equalTo("TBD"));		
	}
	
	@Test (testName="CustomerId Required",description="PBI:159118")
	public void customerIdRequired() {
 
				String customerId = prop.getProperty("NOTavailableId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for CustomerId."));
	}
	
	@Test (testName="ItemId Required",description="PBI:159118")
	public void itemIdRequired() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("NOTpaidTId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for ItemId."));
	}
	
	@Test (testName="Quantity Required",description="PBI:159118")
	public void quantityRequired() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				String quantity = prop.getProperty("NULLValue");
				String calcGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for Quantity."));
	}
	
	@Test (testName="Displayed Grand Total Required",description="PBI:159118")
	public void displayedGrandTotalRequired() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 10;
				String calcGrandTotal = prop.getProperty("NULLValue");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/packagedetails/verifypackagedetailsforpurchase/"+companyId+"/"+clubId+"/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for DisplayedGrandTotal."));
	}
	
	
	
	
	
	
	
	
	
}
