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

public class GetDeclineDetailsByMember extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	static String format;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = "101"; // prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		
		format = prop.getProperty("format");
		
		
	}
	
	@Test (testName="Return - Declined",description="PBI:150150")
	public void returnDeclined() {
		
				String customerId = prop.getProperty("declineMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	
	    		Assert.assertNotNull(js.getDouble("Result[0].Amount"));
	    		Assert.assertNotNull(js.getString("Result[0].Attendant"));
	    		Assert.assertNotNull(js.getString("Result[0].Code"));
	    		Assert.assertNotNull(js.getString("Result[0].CustomerId"));
	    		Assert.assertNotNull(js.getString("Result[0].Date"));
	    		Assert.assertTrue(js.getString("Result[0]").contains("FormOfPayment"));
	    		Assert.assertNotNull(js.getString("Result[0].Format"));
	    		Assert.assertNotNull(js.getString("Result[0].LateFee"));
	    		Assert.assertNotNull(js.getString("Result[0].Reason"));
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("declineCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("declineMessage"));
	}
	
	@Test (testName="Return - Invalid CC Number",description="PBI:150150")
	public void returnInvalidCCNo() {
		
				String customerId = prop.getProperty("invalidCCNoMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("invalidCCNoCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("invalidCCNoMessage"));
	}
	
	@Test (testName="Return - Transaction Exceeds Floor Limit",description="PBI:150150")
	public void returnTransactionExceedsFloorLimit() {
		
				String customerId = prop.getProperty("exceedsFloorLimitMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("exceedsFloorLimitCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("exceedsFloorLimitMessage"));
	}
	
	@Test (testName="Return - Warning Bulletin",description="PBI:150150")
	public void returnWarningBulletin() {
		
				String customerId = prop.getProperty("warningBulletinMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("warningBulletinCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("warningBulletinMessage"));
	}
	
	@Test (testName="Return - No Account/Unable to Locate",description="PBI:150150")
	public void returnNoAmtUnableToLocate() {
		
				String customerId = prop.getProperty("noAmountMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("noAmountCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("noAmountMessage"));
	}
	
	@Test (testName="Return - Customer Disputes Charge",description="PBI:150150")
	public void returnCustomerDisputesCharge() {
		
				String customerId = prop.getProperty("customerDisputesChargeMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("customerDisputesChargeCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("customerDisputesChargeMessage"));
	}
	
	@Test (testName="Return - Frozen Status",description="PBI:150150")
	public void returnFrozenStatus() {
		
				String customerId = prop.getProperty("frozenStatusMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("frozenStatusCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("frozenStatusMessage"));
	}
	
	@Test (testName="Return - Delete Status",description="PBI:150150")
	public void returnDeleteStatus() {
		
				String customerId = prop.getProperty("deleteStatusMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("deleteStatusCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("deleteStatusMessage"));
	}
	
	@Test (testName="Return - NSF",description="PBI:150150")
	public void returnNSF() {
		
				String customerId = prop.getProperty("nFSMemberId");
		
		Response res = 	
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
				
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 200);
	    		
	    		Assert.assertEquals(js.getString("Result[0].CustomerId"), customerId);
	    		Assert.assertEquals(js.getString("Result[0].Code"), prop.getProperty("nFSCode"));
	    		Assert.assertEquals(js.getString("Result[0].Format"), format);
	    		Assert.assertEquals(js.getString("Result[0].Reason"), prop.getProperty("nFSMessage"));
	}
	
	@Test (testName="No History Found",description="PBI:150150")
	public void noHistoryFound() {
		
				String customerId = prop.getProperty("noFOPId");
		Response res = 
						
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(400)
				.statusLine("HTTP/1.1 400 Bad Request")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
		
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 204);
				Assert.assertEquals(js.getString("Message"), "No results returned");
	}
	
	@Test (testName="Member Not Found",description="PBI:150150")
	public void memberNotFound() {
		
				String customerId = "99999";
		Response res = 
						
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("api/v3/member/getdeclinedetailsbymember?customerId="+customerId+"")
			.then()
//			    .log().all()
				.statusCode(400)
				.statusLine("HTTP/1.1 400 Bad Request")
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);		
		
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertEquals(js.getInt("Status"), 500);
				Assert.assertEquals(js.getString("Message"), "Customer not found");
	}
	
	
	
}
