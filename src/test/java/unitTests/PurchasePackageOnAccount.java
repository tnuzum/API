package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.base;

public class PurchasePackageOnAccount extends base{
	
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
	
	@Test (testName="Paid Training",description="PBI:143539")
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
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));
	}
	
	@Test (testName="Paid ServiceV",description="PBI:143539")
	public void paidServiceV() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidServiceVId");
				int quantity = 5;
				String dGT = prop.getProperty("paidServiceVGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));
	}
	
	@Test (testName="Free Training",description="PBI:143539")
	public void freeTraining() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeTId");
				int quantity = 1;
				String dGT = prop.getProperty("freeTPrice");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Free Service",description="PBI:143539")
	public void freeService() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeSVId");
				int quantity = 1;
				String dGT = prop.getProperty("freeSVPrice");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Tier Pricing Package - Tier 1",description="PBI:143539")
	public void tierPricingPackage_Tier1() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("tierPricingId");
				int quantity = 1;
				String dGT = prop.getProperty("tierPricingTier1Price");
				String tr = prop.getProperty("tierPricingClub1TaxRate");
				double grandTotal = Double.parseDouble(dGT);
				double taxRate = Double.parseDouble(tr);
				double calcTotal = (grandTotal * quantity);
				double calcTaxTotal =  (calcTotal * taxRate);
				double calcGrandTotal = (calcTotal + calcTaxTotal);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));
	}
	
	@Test (testName="Tier Pricing Package - Tier 2",description="PBI:143539")
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
				double calcGrandTotal = (calcTotal + calcTaxTotal);
				
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Taxed Item",description="PBI:143539")
	public void taxedItem() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				int quantity = 10;
				String dGT = prop.getProperty("taxSingleTPrice");
				String tr = prop.getProperty("taxSingleTClub1TaxRate");
				double grandTotal = Double.parseDouble(dGT);
				double taxRate = Double.parseDouble(tr);
				double calcTotal = (grandTotal * quantity);
				double calcTaxTotal =  (calcTotal * taxRate);
				double calcGrandTotal = (calcTotal + calcTaxTotal);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));				
	}
	
	@Test (testName="Free Package",description="PBI:143539")
	public void freePackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("freeTId");
				int quantity = 1;
				String dGT = prop.getProperty("freeTPrice");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Quantity Zero",description="PBI:143539")
	public void quantityZero() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				String quantity = "0";
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("NonZeroQuantityRequired"));			
	}
	
	@Test (testName="Member Not Found",description="PBI:143539")
	public void memberNotFound() {
 
				String customerId = "99999";
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("CustomerNotFound"));					
	}
	
	@Test (testName="Terminated Member",description="PBI:143539")
	public void terminatedMember() {
 
				String customerId = prop.getProperty("terminatedId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Collections Member",description="PBI:143539")
	public void collectionsMember() {
 
				String customerId = prop.getProperty("collectionsId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("Account Problem"));			
	}
	
	@Test (testName="Frozen Member",description="PBI:143539")
	public void frozenMember() {
 
				String customerId = prop.getProperty("frozenId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Prospect",description="PBI:143539")
	public void prospect() {
 
				String customerId = prop.getProperty("prospectId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("Account Problem"));			
	}
	
	@Test (testName="Credit Limit Exceeded",description="PBI:143539")
	public void creditLimitExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("paidTGrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("Account Problem"));			
	}
	
	@Test (testName="Credit Limit Not Exceeded",description="PBI:143539")
	public void creditLimitNotExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("freeTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Item Not Found",description="PBI:143539")
	public void itemNotFound() {
 
				String customerId = prop.getProperty("availableId");
				int itemId = 99999;
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
//				.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("ItemNotFound"));			
	}
	
	@Test (testName="No FOP On File",description="PBI:143539")
	public void noFOP() {
 
				String customerId = prop.getProperty("noFOPId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
//				.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Item Not Package",description="PBI:143539")
	public void itemNotPackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("notServiceTypeVId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("freeTPrice");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("ItemNotFound"));			
	}
	
	@Test (testName="Product Price Changed",description="PBI:143539")
	public void productPriceChanged() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				int quantity = 1;
				String displayedGrandTotal = prop.getProperty("taxSingleTPrice");//using base price (Grand Total - taxes)
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+displayedGrandTotal)
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("ProductPriceChanged"));			
	}
	
	@Test (testName="OnAccount Payment Not Allowed",description="PBI:143539", enabled = false)
	public void onAccountPaymentNotAllowed() {
		
				String clubId = prop.getProperty("X-Club3Id");
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 10;
				String dGT = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/package/purchasepackageonaccount/"+customerId+"/"+itemId+"/"+quantity+"/"+calcGrandTotal)
			.then()
				.log().body()
//				.statusCode(200)
//				.time(lessThan(60L),TimeUnit.SECONDS)
//				.body("Status", equalTo(200))
//				.body("Result", not(nullValue()))
				;
	}
	
		
	
	
	
	
	
}
