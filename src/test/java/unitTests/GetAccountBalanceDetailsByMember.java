package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetAccountBalanceDetailsByMember extends base {
	
			static String aPIKey;
			static String companyId;
			static String clubId;
			static String altCompanyId;
			static String format;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		format = prop.getProperty("format");

	}
	
	@Test (testName="History Found - No Declines", description="PBI:149846", enabled = true)
	
	public void historyFoundNoDeclines() {
				
		String customerId = prop.getProperty("noFOPId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertEquals(js.getString("Result.CustomerId"),customerId);
				Assert.assertNotNull(js.getString("Result.Name"));
				Assert.assertNotNull(js.getString("Result.CustomerBarcodeId"));
				Assert.assertNotNull(js.getString("Result.BaseDate"));
				Assert.assertNotNull(js.getString("Result.CurrentCharges"));
				Assert.assertNotNull(js.getString("Result.Past30"));
				Assert.assertNotNull(js.getString("Result.Past60"));
				Assert.assertNotNull(js.getString("Result.Past90"));
				Assert.assertNotNull(js.getString("Result.Past120"));
				Assert.assertNotNull(js.getString("Result.FutureCharges"));
				Assert.assertNotNull(js.getString("Result.UnappliedPayments"));
				Assert.assertNotNull(js.getString("Result.TotalDue"));
				Assert.assertNotNull(js.getString("Result.CurrentBalance"));
				Assert.assertNotNull(js.getString("Result.AccountValue"));
				
				Assert.assertNull(js.getString("Result.LastDeclineDate"));
				Assert.assertNull(js.getString("Result.DeclineAmount"));
				Assert.assertNull(js.getString("Result.ReturnCode"));
				Assert.assertNull(js.getString("Result.DeclineReason"));
				Assert.assertNull(js.getString("Result.FormOfPayment"));
				
				Assert.assertTrue(js.getString("Result").contains("LastDeclineDate"));
				Assert.assertTrue(js.getString("Result").contains("DeclineAmount"));
				Assert.assertTrue(js.getString("Result").contains("ReturnCode"));
				Assert.assertTrue(js.getString("Result").contains("DeclineReason"));
				Assert.assertTrue(js.getString("Result").contains("FormOfPayment"));
	}
	
	@Test (testName="Return - Declined", description="PBI:149846")
	public void returnDeclined() {
		
				String customerId = "120";//prop.getProperty("declineMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getString("Result").contains("BaseDate"));
//	    		Assert.assertNotNull(js.getString("Result.BaseDate"));
//	    		Assert.assertNotNull(js.getString("Result.CustomerBarcodeId"));
//	    		Assert.assertNotNull(js.getString("Result.CustomerId"));
//	    		Assert.assertNotNull(js.getString("Result.DeclineAmount"));
//	    		Assert.assertNotNull(js.getString("Result.DeclineReason"));
////	    		Assert.assertNotNull(js.getString("Result.FormOfPayment"));
//	    		Assert.assertNotNull(js.getString("Result.LastDeclineDate"));
//	    		Assert.assertNotNull(js.getString("Result.Name"));
//	    		Assert.assertNotNull(js.getString("Result.ReturnCode"));
	    		
	    		Assert.assertTrue(js.getDouble("Result.AccountValue") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.CurrentBalance") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.CurrentCharges") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.FutureCharges") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.Past30") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.Past60") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.Past90") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.Past120") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.TotalDue") >= 0.00);
	    		Assert.assertTrue(js.getDouble("Result.UnappliedPayments") >= 0.00);
	    		
	    		//Assert.assertTrue(js.getDouble("Result.DeclineAmount") >= 0.00);
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("declineMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("declineCode"));
	    		
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);
	}
	
	@Test (testName="Return - Invalid CC Number", description="PBI:149846")
	public void returnInvalidCCNo() {
		
				String customerId = prop.getProperty("invalidCCNoMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("invalidCCNoMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("invalidCCNoCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - Transaction Exceeds Floor Limit", description="PBI:149846")
	public void returnTransactionExceedsFloorLimit() {
		
				String customerId = prop.getProperty("exceedsFloorLimitMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("exceedsFloorLimitMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("exceedsFloorLimitCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - Warning Bulletin", description="PBI:149846")
	public void returnWarningBulletin() {
		
				String customerId = prop.getProperty("warningBulletinMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("warningBulletinMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("warningBulletinCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - No Account/Unable to Locate", description="PBI:149846")
	public void returnNoAmtUnableToLocate() {
		
				String customerId = prop.getProperty("noAmountMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("noAmountMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("noAmountCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - Customer Disputes Charge", description="PBI:149846")
	public void returnCustomerDisputesCharge() {
		
				String customerId = prop.getProperty("customerDisputesChargeMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("customerDisputesChargeMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("customerDisputesChargeCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - Frozen Status", description="PBI:149846")
	public void returnFrozenStatus() {
		
				String customerId = prop.getProperty("frozenStatusMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("frozenStatusMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("frozenStatusCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - Delete Status", description="PBI:149846")
	public void returnDeleteStatus() {
		
				String customerId = prop.getProperty("deleteStatusMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("deleteStatusMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("deleteStatusCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Return - NSF", description="PBI:149846")
	public void returnNSF() {
		
				String customerId = prop.getProperty("nFSMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertEquals(js.getString("Result.DeclineReason"), prop.getProperty("nFSMessage"));
	    		Assert.assertEquals(js.getString("Result.ReturnCode"), prop.getProperty("nFSCode"));
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);		
	}
	
	@Test (testName="Negative Balances", description="PBI:149846", enabled = false)
		// this needs a new member created because the one used now doesn't always have negative balances
	public void negativeBalances() {
		
				String companyId = altCompanyId;
				String customerId = prop.getProperty("negativeAcctBalMemberId");
		
		Response res =
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.assertThat()
				.statusCode(200)
				.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
	    		Assert.assertTrue(js.getDouble("Result.AccountValue") < 0.00);
	    		Assert.assertTrue(js.getDouble("Result.CurrentBalance") < 0.00);
//	    		Assert.assertTrue(js.getDouble("Result.CurrentCharges") < 0.00);
	    		Assert.assertTrue(js.getDouble("Result.TotalDue") < 0.00);
	    		Assert.assertEquals(js.getString("Result.CustomerId"), customerId);	
	}
	
	@Test (testName="Customer Not Found", description="PBI:149846")
	public void customerNotFound() {
		
				String customerId = "99999";
		
		Response res = 
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/member/getaccountbalancedetailsbymember?customerId="+customerId+"")
			.then()
//				.log().all()
				.statusCode(500)
				.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Message").contains("CustomerId '"+customerId+"' not found"));
	}
	
	
	
	
	
	
	
	
}






















