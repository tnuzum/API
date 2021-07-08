package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import resources.base;

public class GetStoredBankAccount extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	static String customerId;
	static String bankName;
	static String routingNumber;
	static String truncatedAccountNumber;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		
		bankName = prop.getProperty("bankName");
		routingNumber = prop.getProperty("routingNumber");
		truncatedAccountNumber = prop.getProperty("truncatedAccountNumber");
	}
	
	@Test (testName="Checking Account", description="PBI:180172")
	public void checkAccount() {
		
				String member = prop.getProperty("checkingId");
				String memberName = prop.getProperty("checkingName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Checking"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Savings Account", description="PBI:180172")
	public void savingsAccount() {
		
				String member = prop.getProperty("savingsId");
				String memberName = prop.getProperty("savingsName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Savings"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Business Checking Account", description="PBI:180172")
	public void businessCheckingAccount() {
		
				String member = prop.getProperty("businessCheckingAccountId");
				String memberName = prop.getProperty("businessCheckingAccountName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Checking"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(true))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Business Savings Account", description="PBI:180172")
	public void businessSavingsAccount() {
		
				String member = prop.getProperty("businessSavingsAccountId");
				String memberName = prop.getProperty("businessSavingsAccountName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Savings"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(true))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Draft Limit Checking Account", description="PBI:180172")
	public void draftLimitCheckingAccount() {
		
				String member = prop.getProperty("draftLimitCheckingAccountId");
				String memberName = prop.getProperty("draftLimitCheckingAccountName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Checking"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Draft Limit Savings Account", description="PBI:180172")
	public void draftLimitSavingsAccount() {
		
				String member = prop.getProperty("draftLimitSavingsAccountId");
				String memberName = prop.getProperty("draftLimitSavingsAccountName");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Savings"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Multiple Banks Account", description="PBI:180172")
	public void multipleBanksAccount() {
		
				String member = prop.getProperty("multipleBankAccountsId");
				String memberName = prop.getProperty("multipleBankAccountsName");
				String truncatedAccountNumber1 = "9993";
				String truncatedAccountNumber2 = "9994";

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("1"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Checking"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber1))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false))
						.body("Results[1].AccountId.CurrentValue", equalTo("2"))
						.body("Results[1].AccountId.PendingChange", equalTo(false))
						.body("Results[1].BankAccountType.CurrentValue", equalTo("Savings"))
						.body("Results[1].BankAccountType.PendingChange", equalTo(false))
						.body("Results[1].BankName.CurrentValue", equalTo(bankName))
						.body("Results[1].BankName.PendingChange", equalTo(false))
						.body("Results[1].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[1].BillingName.PendingChange", equalTo(false))
						.body("Results[1].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[1].IsBusiness.PendingChange", equalTo(false))
						.body("Results[1].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[1].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[1].RoutingNumber.CurrentValue", equalTo(routingNumber))
						.body("Results[1].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[1].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber2))
						.body("Results[1].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="Bank 2nd Stored Account", description="PBI:180172")
	public void bank2ndStoredAccount() {
		
				String member = prop.getProperty("bothfopMemberId");
				String memberName = prop.getProperty("bothfopMemberName");
				String bankName2 = prop.getProperty("bankName2");
				String routingNumber2 =  prop.getProperty("routingNumber2");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Status", equalTo(200))
						.body("Results[0].AccountId.CurrentValue", equalTo("2"))
						.body("Results[0].AccountId.PendingChange", equalTo(false))
						.body("Results[0].BankAccountType.CurrentValue", equalTo("Checking"))
						.body("Results[0].BankAccountType.PendingChange", equalTo(false))
						.body("Results[0].BankName.CurrentValue", equalTo(bankName2))
						.body("Results[0].BankName.PendingChange", equalTo(false))
						.body("Results[0].BillingName.CurrentValue", equalTo(memberName))
						.body("Results[0].BillingName.PendingChange", equalTo(false))
						.body("Results[0].IsBusiness.CurrentValue", equalTo(false))
						.body("Results[0].IsBusiness.PendingChange", equalTo(false))
						.body("Results[0].IsHouseAccount.CurrentValue", equalTo(false))
						.body("Results[0].IsHouseAccount.PendingChange", equalTo(false))
						.body("Results[0].RoutingNumber.CurrentValue", equalTo(routingNumber2))
						.body("Results[0].RoutingNumber.PendingChange", equalTo(false))
						.body("Results[0].TruncatedAccountNumber.CurrentValue", equalTo(truncatedAccountNumber))
						.body("Results[0].TruncatedAccountNumber.PendingChange", equalTo(false));
	}
	
	@Test (testName="No Bank Account", description="PBI:180172")
	public void noBankAccount() {
		
				String member = prop.getProperty("noFOPId");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/financial/getstoredbankaccounts?customerId="+member)
				.then()
//						.log().all()
						//.assertThat().statusCode(204)
						.body("Status", equalTo(204))
						.body("Message", equalTo("No stored bank accounts found."));
	}

}
