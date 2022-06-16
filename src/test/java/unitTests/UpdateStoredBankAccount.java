package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.FinancialPL;
import resources.ReusableMethods;
import resources.base;

public class UpdateStoredBankAccount extends base {
	
	String aPIKey;
	String companyId;
	String clubId;
	static String customerId;
	static String accountId;
	static String routingNumber;
	static String bankAccountNumber;
	static String bankRoutingNumber;
	static String accountHolderName;
	static String bankAccountType;
	static int Seccode;
	static String setAsHouseAccount;
	static String agreementNumbers;
	static String agreementNumbers2;
	static String updateActiveAgreements;
	static int n;
	static int m;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		accountId = "1";
		customerId = prop.getProperty("changeFOPId");
		bankRoutingNumber = prop.getProperty("routingNumber");
		bankAccountNumber = prop.getProperty("accountNumber");
		accountHolderName = prop.getProperty("fullName");
		bankAccountType = "Checking";
		Seccode = 1;
		setAsHouseAccount = "";
		agreementNumbers = prop.getProperty("agreementToAddUpdate");
		agreementNumbers2 = prop.getProperty("agreementToAddUpdate2");
		updateActiveAgreements = "false";
	}
	
	@Test (priority = 1, testName="Update Checking Account", description="PBI:180171, 186201, 186201")
	public void updateCheckingAccount() {

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
					.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
							Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].BankAccountType.CurrentValue"), bankAccountType);
							
						}
						
					}
				
				
	}
	
	@Test (priority = 2, testName="Update Savings Account", description="PBI:180171, 186201")
	public void updateSavingsAccount() {
		
		String bankAccountType = "Savings";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
										
							Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].BankAccountType.CurrentValue"), bankAccountType);
						}
					}

					
	}
	
	@Test (priority = 3,testName="Update SecCode = Web", description="PBI:180171, 186201")
	public void updateSecCodeToWeb() {
		
		int SecCode = 2;

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
										
							Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),SecCode);
						}
					}
	}
	
	@Test (priority = 4,testName="Update House Account", description="PBI:180171, 186201")
	public void updateHouseAccount() {
		
		String setAsHouseAccount = "true";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
										
							Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].IsHouseAccount.CurrentValue"), setAsHouseAccount);
						}
					}
	}

	@Test (priority = 5, testName="Update Bank Account Number", description="PBI:180171, 186201")
	public void updateBankAccountNumber() {
		
		String bankAccountNumber = "4099999997";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(js.getInt("Status"), 200);
				Assert.assertNull(js.getString("Messages"));
				Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
				Assert.assertNull(js.getString("PendingConfirmationNumber"));
				
				JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

				n = storedAccountsResponse.getInt("Results.AccountId.size()");
				
				for (int i= 0; i<n; i++) {
					
					
					if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
						
						Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].TruncatedAccountNumber.CurrentValue"), "9997");
					}
				}
				
	}
	
	@Test (priority = 6, testName="Update Bank Routing Number", description="PBI:180171, 186201")
	public void updateBankRoutingNumber() {
		
		String bankRoutingNumber = "067812345";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(js.getInt("Status"), 200);
				Assert.assertNull(js.getString("Messages"));
				Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
				Assert.assertNull(js.getString("PendingConfirmationNumber"));
				
				JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

				n = storedAccountsResponse.getInt("Results.AccountId.size()");
				
				for (int i= 0; i<n; i++) {
					
					
					if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
						
									
						Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].RoutingNumber.CurrentValue"), "067812345");
					}
				}
				
	}
	
	@Test (priority = 7, testName="Update Account Holder Name", description="PBI:180171, 186201")
	public void updateAccountHolderName() {
		
		String accountHolderName = "John Doe";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(js.getInt("Status"), 200);
				Assert.assertNull(js.getString("Messages"));
				Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
				Assert.assertNull(js.getString("PendingConfirmationNumber"));
				
				JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

				n = storedAccountsResponse.getInt("Results.AccountId.size()");
				
				for (int i= 0; i<n; i++) {
					
					
					if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
						
									
						Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].BillingName.CurrentValue"), "John Doe");
					}
				}
				
	}
	
	@Test (priority = 8, testName="Update Agreements", description="PBI:180171, 186201")
	public void updateAgreements() {
		
		String updateActiveAgreements = "true";
		

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(js.getInt("Status"), 200);
				Assert.assertNull(js.getString("Messages"));
				Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
				Assert.assertNull(js.getString("PendingConfirmationNumber"));
				
				JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
				
				n = storedAccountsResponse.getInt("Results.AccountId.size()");
				
				for (int i= 0; i<n; i++) {
					
					
					if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
						
						Assert.assertNotNull(storedAccountsResponse.getString("Results["+m+"].AssignedAgreements[0].AgreementNumber"));
				
					}
				}
				
	}
	
	@Test (priority = 9, testName="Update Agreements to AccountId 2", description="PBI:180171, 186201")
	public void updateAgreementsToAccountId2() {
		
		String accountId = "2";
		String updateActiveAgreements = "true";
		

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(js.getInt("Status"), 200);
				Assert.assertNull(js.getString("Messages"));
				Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
				Assert.assertNull(js.getString("PendingConfirmationNumber"));
				
				JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
				
				n = storedAccountsResponse.getInt("Results.AccountId.size()");
				
				for (int i= 0; i<n; i++) {
					
					
					if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("2")){
						
						Assert.assertNotNull(storedAccountsResponse.getString("Results["+i+"].AssignedAgreements[0].AgreementNumber"));
						
					}
					
				}
				
				
	}
	
	@Test (priority = 10, testName="No Form of Payment", description="PBI:180171, 186201")
	public void noFOP() {

		String customerId = "247";
		
		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 500);
				Assert.assertEquals(js.getInt("Status"), 500);
				Assert.assertTrue(js.getString("Message").contains("Sequence contains no elements"));
				
	}
	
	@Test (priority = 11, testName="Bank Not Found", description="PBI:180171, 186201")
	public void bankNotFound() {
		
		String bankRoutingNumber = "123456789";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(res.statusCode(), 400);
				Assert.assertEquals(js.getInt("Status"), 400);
				Assert.assertEquals(js.getString("Message"), "NoChanges,BankDoesNotExist");
				
	}

	@Test (priority = 12, testName="Customer Not Found", description="PBI:180171, 186201")
	public void customerNotFound() {
		
		String customerId = "999999";
		String setAsHouseAccount = "true";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,Seccode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 500);
					Assert.assertEquals(js.getInt("Status"), 500);
					Assert.assertTrue(js.getString("Message").contains("Sequence contains no elements"));
				
	}
	
	@Test (priority = 13, testName="When no SecCode Sent then SecCode is set to 0", description="PBI:180171, 186201")
	public void updateWithNoSecCode() {
		
		
		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccountsNoSecCode(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
					Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),0);
						}
					}
	}
	
	@Test (priority = 14, testName="When invalid SecCode Sent then SecCode is set to 0", description="PBI:180171, 186201")
	public void updateWithInvalidSecCode() {
		
		int SecCode = 5;

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.updateStoredBankAccounts(customerId,accountId, bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode, setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/updatestoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertFalse(js.getString("AutoApprovedConfirmationNumbers").isBlank());
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					
					for (int i= 0; i<n; i++) {
						
						
						if (storedAccountsResponse.getString("Results.AccountId.CurrentValue").equals("1")){
							
										
					Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),0);
						}
					}
	}
	
	

}
