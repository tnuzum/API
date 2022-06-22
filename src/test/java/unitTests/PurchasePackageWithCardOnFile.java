package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.PackagePL;
import resources.ReusableMethods;
import resources.base;
import resources.myGets;

public class PurchasePackageWithCardOnFile extends base{
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	int accountId = 1;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");	
	}
	
	@Test (testName="Paid Training",description="PBI:143542")
	public void paidTraining() {
 
				String customerId = prop.getProperty("CardWithoutAgreementId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 5;
				
				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
				
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()				
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().all()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));
	}
	
	@Test (testName="Paid ServiceV",description="PBI:143542")
	public void paidServiceV() {
 
				String customerId = prop.getProperty("CardWithoutAgreementId");
				String itemId = prop.getProperty("paidServiceVId");
				int quantity = 3;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));
	}
	
	@Test (testName="Paid Punchcard",description="PBI:143542")
	public void paidPunchcard() {
 
				String customerId = prop.getProperty("CardWithoutAgreementId");
				String itemId = prop.getProperty("paidPId");
				int quantity = 5;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));
	}
	
	@Test (testName="Free Training",description="PBI:143542")
	public void freeTraining() {
 
				String customerId = prop.getProperty("appointmentId");
				String itemId = prop.getProperty("freeTId");
				int quantity = 10;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="Free Service",description="PBI:143542")
	public void freeService() {
 
				String customerId = prop.getProperty("appointmentId");
				String itemId = prop.getProperty("freeSVId");
				int quantity = 10;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="Free Punchcard",description="PBI:143542")
	public void freePunchcard() {
 
				String customerId = prop.getProperty("appointmentId");
				String itemId = prop.getProperty("freePId");
				int quantity = 10;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="Tier Pricing Package - Tier 1",description="PBI:143542")
	public void tierPricingPackage_Tier1() {
 
				String customerId = prop.getProperty("CardWithoutAgreementId");
				String itemId = prop.getProperty("tierPricingId");
				int quantity = 1;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));
	}
	
	@Test (testName="Tier Pricing Package - Tier 2",description="PBI:143542")
	public void tierPricingPackage_Tier2() {
 
				String customerId = prop.getProperty("CardWithoutAgreementId");
				String itemId = prop.getProperty("tierPricingId");
				int quantity = 6;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="Taxed Item",description="PBI:143542")
	public void taxedItem() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				int quantity = 5;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));				
	}
	
	@Test (testName="Multiple Cards On File - AccountId 1",description="PBI:143542")
	public void multipleCardsOnFileAccountId1() {
 
				String customerId = prop.getProperty("MultipleAgreementsWithMultipleCardsId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				int accountId = 1;
				
				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="Multiple Cards On File - AccountId 2",description="PBI:143542")
	public void multipleCardsOnFileAccountId2() {
 
				String customerId = prop.getProperty("MultipleAgreementsWithMultipleCardsId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				int accountId = 2;
				
				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));		
	}
	
	@Test (testName="No Card On File",description="PBI:143542")
	public void noCardOnFile() {
 
				String customerId = prop.getProperty("noFOPId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;

				Response res = myGets.getPackagePrice(aPIKey, companyId, clubId, customerId, itemId, quantity);
				JsonPath js = ReusableMethods.rawToJson(res);
				double calcGrandTotal = js.getDouble("Result.GrandTotal");
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(500))
				.body("Message", containsString("Sequence contains no elements"));		
	}
	
	@Test (testName="Quantity Zero",description="PBI:143542")
	public void quantityZero() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 0;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("NonZeroQuantityRequired"));			
	}
	
	@Test (testName="Member Not Found",description="PBI:143542")
	public void memberNotFound() {
 
				String customerId = "99999";
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Terminated Member",description="PBI:143542")
	public void terminatedMember() {
 
				String customerId = prop.getProperty("terminatedId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(500))
				.body("Message", containsString("Sequence contains no elements"));		
	}
	
	@Test (testName="Collections Member",description="PBI:143542", enabled = true)
	public void collectionsMember() {
 
				String customerId = prop.getProperty("collectionsId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));			
	}
	
	@Test (testName="Frozen Member",description="PBI:143542", enabled = true)
	public void frozenMember() {
 
				String customerId = prop.getProperty("frozenId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				int accountId = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", equalTo("Success"));
	}
	
	@Test (testName="Prospect",description="PBI:143542", enabled = true)
	public void prospect() {
 
				String customerId = prop.getProperty("prospectId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(500))
				.body("Message", containsString("Sequence contains no elements"));			
	}
	
	@Test (testName="Credit Limit Exceeded",description="PBI:143542", enabled = true)
	public void creditLimitExceeded() {
		
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("paidTId");
				int quantity = 1;
				String dgt = prop.getProperty("paidTGrandTotal");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(500))
				.body("Message", containsString("Sequence contains no elements"));		
	}
	
	@Test (testName="Credit Limit Not Exceeded",description="PBI:143542")
	public void creditLimitNotExceeded() {
 
				String customerId = prop.getProperty("creditLimitId");
				String itemId = prop.getProperty("freeTId");
				int quantity = 1;
				String dgt = prop.getProperty("freeTPrice");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("Result", not(nullValue()));		
	}
	
	@Test (testName="Item Not Found",description="PBI:143542")
	public void itemNotFound() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = "99999";
				int quantity = 1;
				String dgt = prop.getProperty("freeTPrice");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("ItemNotFound"));			
	}
	
	@Test (testName="Item Not Package",description="PBI:143542")
	public void itemNotPackage() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("notServiceTypeVId");
				int quantity = 1;
				String dgt = prop.getProperty("freeTPrice");
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(404))
				.body("Message", equalTo("ItemNotFound"));			
	}
	
	@Test (testName="Product Price Changed",description="PBI:143542")
	public void productPriceChanged() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("taxSingleTId");
				int quantity = 1;
				String dgt = prop.getProperty("taxSingleTPrice");//using base price (Grand Total - taxes)
				double displayedGrandTotal = Double.parseDouble(dgt);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
				//.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
				//.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("ProductPriceChanged"));			
	}
	
	@Test (testName="Package Quantity Limit Exceeded",description="PBI:143542")
	public void packageQuantityLimitExceeded() {
 
				String customerId = prop.getProperty("availableId");
				String itemId = prop.getProperty("limit10PId");
				int quantity = 15;
				String dGT = prop.getProperty("limit10PBasePrice");
				double displayedGrandTotal = Double.parseDouble(dGT);
				double calcGrandTotal = (displayedGrandTotal * quantity);
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(PackagePL.PurchasePackageWithCardOnFile(customerId, itemId, quantity, calcGrandTotal, accountId))
				.post("/api/v3/package/purchasepackagewithcardonfile")
			.then()
//				.log().body()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", containsString("InvoiceError"));
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
