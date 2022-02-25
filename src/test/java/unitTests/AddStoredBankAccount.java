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

public class AddStoredBankAccount extends base {
	
	String aPIKey;
	String companyId;
	String clubId;
	static String customerId;
	static String routingNumber;
	
	static String bankAccountNumber;
	static String bankRoutingNumber;
	static String accountHolderName;
	static String bankAccountType;
	static int SecCode;
	static String setAsHouseAccount;
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
		
		customerId = prop.getProperty("changeFOPId");
		bankRoutingNumber = prop.getProperty("routingNumber");
		bankAccountNumber = prop.getProperty("accountNumber");
		accountHolderName = prop.getProperty("fullName");
		bankAccountType = "Checking";
		SecCode = 1;
		setAsHouseAccount = "false";
		updateActiveAgreements = "false";
	}
	
	@Test (testName="Add Checking Account", description="PBI:180170, 186201")
	public void addCheckingAccount() {

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
					.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].BankAccountType.CurrentValue"), bankAccountType);
					

				
	}
	
	@Test (testName="Add Savings Account", description="PBI:180170, 186201")
	public void addSavingsAccount() {
		
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
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].BankAccountType.CurrentValue"), bankAccountType);
					
	}
	
	@Test (testName="Add Account Update Active Agreements", description="PBI:180170, 186201")
	public void addAccountUpdateActiveAgreements() {
		
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
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					m = n-1;
										
					Assert.assertNotNull(storedAccountsResponse.getString("Results["+m+"].AssignedAgreements[0].AgreementNumber"));

				
	}
	
	@Test (testName="Add SecCode = Web", description="PBI:180170, 186201")
	public void addSecCodeWeb() {
		
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
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					int m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),SecCode);
	}
	
	@Test (testName="Add House Account", description="PBI:180170, 186201")
	public void addHouseAccount() {
		
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
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getString("Results["+m+"].IsHouseAccount.CurrentValue"), setAsHouseAccount);

	}

	@Test (testName="Customer Not Found", description="PBI:180170, 186201")
	public void customerNotFound() {
		
		String customerId = "999999";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 500);
					Assert.assertEquals(js.getInt("Status"), 500);
					Assert.assertTrue(js.getString("Message").contains("Sequence contains no elements"));
				
	}
	
	@Test (testName="Bank Account Number Missing", description="PBI:180170, 186201")
	public void bankAccountNumberMissing() {
		
		String bankAccountNumber = "";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 400);
					Assert.assertEquals(js.getInt("Status"), 400);
					Assert.assertEquals(js.getString("Message"),"The BankAccountNumber field is required.");
				
	}
	
	@Test (testName="Bank Routing Number Missing", description="PBI:180170, 186201")
	public void bankRoutingNumberMissing() {
		
		String bankRoutingNumber = "";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 400);
					Assert.assertEquals(js.getInt("Status"), 400);
					Assert.assertEquals(js.getString("Message"),"The BankRoutingNumber field is required.");
				
	}
	
	@Test (testName="Account Holder Name Missing", description="PBI:180170, 186201")
	public void accountHolderNameMissing() {
		
		String accountHolderName = "";

		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 400);
					Assert.assertEquals(js.getInt("Status"), 400);
					Assert.assertEquals(js.getString("Message"),"The AccountHolderName field is required.");
				
	}
	
	@Test (testName="When no SecCode Sent then SecCode is set to 0", description="PBI:180170, 186201")
	public void noSecCodeSent() {
		
		
		Response res = 
				
				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
					.header("Content-Type", "application/json")
				.when()
				.body(FinancialPL.addStoredBankAccountsNoSecCode(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);

					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					int m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),0);

	}
	
	@Test (testName="When Invalid SecCode Sent then SecCode is set to 0", description="PBI:180170, 186201")
	public void noinValidSecCodeSent() {
		
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
				.body(FinancialPL.addStoredBankAccounts(customerId,bankAccountNumber,bankRoutingNumber,accountHolderName,bankAccountType,SecCode,setAsHouseAccount, updateActiveAgreements))
					.post("/api/v3/financial/addstoredbankaccount")
				.then()
//					.log().body()
					.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
					Assert.assertEquals(res.statusCode(), 200);
					Assert.assertEquals(js.getInt("Status"), 200);
					Assert.assertNull(js.getString("Messages"));
					Assert.assertTrue(js.getInt("AutoApprovedConfirmationNumber") > 0);
					Assert.assertNull(js.getString("PendingConfirmationNumber"));
					
					JsonPath storedAccountsResponse = ReusableMethods.getStoredAccountsResponse(aPIKey, companyId, clubId, customerId);
					
					n = storedAccountsResponse.getInt("Results.AccountId.size()");
					int m = n-1;
										
					Assert.assertEquals(storedAccountsResponse.getInt("Results["+m+"].SECCode.CurrentValue"),0);

					
	}


}
