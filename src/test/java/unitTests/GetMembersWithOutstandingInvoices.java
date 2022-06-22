package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetMembersWithOutstandingInvoices extends base{
	
	String aPIKey;
	String companyId;
	String clubId;
	Boolean includeMembersInCollection;
	Boolean includeTerminatedMembers;
	String asOfDate;
	String customerId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		
		includeMembersInCollection = true;
		includeTerminatedMembers= true;
		asOfDate = ReusableDates.getCurrentDate();
	}
	
	@Test  (testName="Outstanding Invoices Found", description="PBI:153783")
	
	/* 
	 * This call should always be sent with a customerId,
	 * because it could take a long time to return without one.
	 * Best Practice is to use the GetDelinquentMembers call first	
	 */
	public void outstandingInvoicesFound() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result[0].CustomerDemographics", hasKey("CustomerId"))
					    .body("Result[0].CustomerDemographics.Name", hasKey("FirstName"))
					    .body("Result[0].CustomerDemographics.Name", hasKey("MiddleInitial"))
					    .body("Result[0].CustomerDemographics.Name", hasKey("LastName"))
					    .body("Result[0].CustomerDemographics.Name", hasKey("PreferredName"))
					    .body("Result[0].CustomerDemographics.Name", hasKey("DisplayName"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("AddressLine1"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("AddressLine2"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("City"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("Country"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("PostalCode"))
					    .body("Result[0].CustomerDemographics.Address", hasKey("StateProvince"))
					    .body("Result[0].CustomerDemographics", hasKey("MemberID"))
					    .body("Result[0].CustomerDemographics", hasKey("DoNotMail"))
					    .body("Result[0].CustomerDemographics", hasKey("DoNotMarket"))					    
					    .body("Result[0].CustomerDemographics", hasKey("EmailAddress"))
					    .body("Result[0].CustomerDemographics", hasKey("EmailContactConsent"))
					    .body("Result[0].CustomerDemographics", hasKey("EmergencyContactName"))
					    .body("Result[0].CustomerDemographics", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result[0].CustomerDemographics", hasKey("HomePhoneContactConsent"))
					    .body("Result[0].CustomerDemographics.HomePhoneNumber", hasKey("Extension"))
					    .body("Result[0].CustomerDemographics.HomePhoneNumber", hasKey("Number"))
					    .body("Result[0].CustomerDemographics.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result[0].CustomerDemographics", hasKey("MobilePhoneContactConsent"))
					    .body("Result[0].CustomerDemographics.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result[0].CustomerDemographics.MobilePhoneNumber", hasKey("Number"))
					    .body("Result[0].CustomerDemographics.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result[0].CustomerDemographics", hasKey("WorkPhoneContactConsent"))
					    .body("Result[0].CustomerDemographics.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result[0].CustomerDemographics.WorkPhoneNumber", hasKey("Number"))
					    .body("Result[0].CustomerDemographics.WorkPhoneNumber", hasKey("PhoneType"))
					    .body("Result[0].CustomerDemographics.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result[0].CustomerDemographics.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result[0].CustomerDemographics.PreferredPhoneNumber", hasKey("PhoneType"))
						.body("Result[0].CustomerDemographics", hasKey("PreferredPhoneType"))
					    .body("Result[0].CustomerDemographics", hasKey("DateOfBirth"))
					    .body("Result[0].CustomerDemographics", hasKey("HomeClubNumber"))
					    .body("Result[0].CustomerDemographics", hasKey("HomeClubName"))
					    .body("Result[0].CustomerDemographics", hasKey("MemberType"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("CustomerId"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceId"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("ReceiptNumber"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceBalance"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceCategory"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceCreationDate"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceDescription"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoicePaidAmount"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceTotal"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("NsfFeeApplied"));
	}
	
	@Test  (testName="Specific Customer With Card Found", description="PBI:153783")
	public void specificCustomerWithCardFound() {
		
						String customerId = prop.getProperty("outstandingInvoiceCardOnFileMemberId");
						int customerIdInt =  Integer.parseInt(customerId);

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getmemberswithoutstandinginvoices?customerId="+customerId+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
					    .body("Result[0].CustomerDemographics.CustomerId", equalTo(customerIdInt))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardNumber", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardExpirationDate.Month", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardExpirationDate.Year", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardType", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CustomerId", equalTo(customerIdInt))
					    .body("Result[0].CreditCardsOnFile[0].AccountId", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].PaymentType", equalTo("CreditCard"));
	}
	
	@Test  (testName="Specific Customer With Bank Account Found", description="PBI:153783")
	public void specificCustomerWithBankAccountFound() {
		
						String customerId = prop.getProperty("outstandingInvoiceBankOnFileMemberId");
						int customerIdInt =  Integer.parseInt(customerId);

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getmemberswithoutstandinginvoices?customerId="+customerId+"")
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
					    .body("Result[0].CustomerDemographics.CustomerId", equalTo(customerIdInt))
					    .body("Result[0].BankAccountsOnFile[0].BankAccountType", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].BankAccountNumber", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].BankName", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0]", hasKey("BillingName"))
					    .body("Result[0].BankAccountsOnFile[0].IsBusiness", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].RoutingNumber", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].CustomerId", equalTo(customerIdInt))
					    .body("Result[0].BankAccountsOnFile[0].AccountId", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].PaymentType", equalTo("Draft"));
	}
	
	@Test  (testName="Specific Customer With Card and Bank Account Found", description="PBI:153783")
	public void specificCustomerWithCardAndBankAccountFound() {
		
						String customerId = prop.getProperty("bothfopMemberId");
						int customerIdInt =  Integer.parseInt(customerId);

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getmemberswithoutstandinginvoices?customerId="+customerId+"")
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
					    .body("Result[0].CustomerDemographics.CustomerId", equalTo(customerIdInt))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardNumber", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardExpirationDate.Month", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardExpirationDate.Year", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CreditCardType", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].CustomerId", equalTo(customerIdInt))
					    .body("Result[0].CreditCardsOnFile[0].AccountId", not(nullValue()))
					    .body("Result[0].CreditCardsOnFile[0].PaymentType", equalTo("CreditCard"))
//					    .body("Result[0].CreditCardsOnFile[1].CreditCardNumber", not(nullValue()))
//					    .body("Result[0].CreditCardsOnFile[1].CreditCardExpirationDate.Month", not(nullValue()))
//					    .body("Result[0].CreditCardsOnFile[1].CreditCardExpirationDate.Year", not(nullValue()))
//					    .body("Result[0].CreditCardsOnFile[1].CreditCardType", not(nullValue()))
//					    .body("Result[0].CreditCardsOnFile[1].CustomerId", equalTo(customerIdInt))
//					    .body("Result[0].CreditCardsOnFile[1].AccountId", not(nullValue()))
//					    .body("Result[0].CreditCardsOnFile[1].PaymentType", equalTo("CreditCard"))
					    .body("Result[0].BankAccountsOnFile[0].BankAccountType", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].BankAccountNumber", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].BankName", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0]", hasKey("BillingName"))
					    .body("Result[0].BankAccountsOnFile[0].IsBusiness", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].RoutingNumber", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].CustomerId", equalTo(customerIdInt))
					    .body("Result[0].BankAccountsOnFile[0].AccountId", not(nullValue()))
					    .body("Result[0].BankAccountsOnFile[0].PaymentType", equalTo("Draft"))
//					    .body("Result[0].BankAccountsOnFile[1].BankAccountType", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1].BankAccountNumber", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1].BankName", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1]", hasKey("BillingName"))
//					    .body("Result[0].BankAccountsOnFile[1].IsBusiness", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1].RoutingNumber", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1].CustomerId", equalTo(customerIdInt))
//					    .body("Result[0].BankAccountsOnFile[1].AccountId", not(nullValue()))
//					    .body("Result[0].BankAccountsOnFile[1].PaymentType", equalTo("Draft"))
					    ;
					
	}
	
	@Test  (testName="Collections Member Included", description="PBI:153783")
	public void collectionsMemberIncluded() {
		
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CustomerDemographics.MemberType", hasItem("InCollections"));
	}
	
	@Test  (testName="Collections Member Not Included", description="PBI:153783")
	public void collectionsMemberNotIncluded() {
		
					Boolean includeMembersInCollection = false;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CustomerDemographics.MemberType", not(hasItem("InCollections")));
	}
	
	@Test  (testName="Terminated Member Included", description="PBI:153783")
	public void terminatedMemberIncluded() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CustomerDemographics.Name.FirstName", hasItem("Terminated"));
	}
	
	@Test  (testName="Terminated Member Not Included", description="PBI:153783")
	public void terminatedMemberNotIncluded() {
		
					Boolean includeTerminatedMembers= false;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CustomerDemographics.Name.FirstName", not(hasItem("Terminated")));
	}
	
	@Test  (testName="Collections and Terminated Members Included", description="PBI:153783")
	public void collectionsAndTerminatedMembersIncluded() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeTerminatedMembers="+includeTerminatedMembers+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CustomerDemographics.Name.FirstName", hasItem("Terminated"))
						.body("Result.CustomerDemographics.MemberType", hasItem("InCollections"));
	}

	@Test  (testName="Outstanding Invoices Not Found", description="PBI:153783")
	public void outstandingInvoicesNotFound() {
		
					String asOfDate = "1899-12-31";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"")
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo(204))
						.body("Messages[0]", equalTo("No outstanding invoices found"));
	}

	@Test  (testName="Customer Not Found", description="PBI:153783")
	public void customerNotFound() {
		
						String customerId = "99999";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getmemberswithoutstandinginvoices?customerId="+customerId+"")
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS);
//					    .body("Result[0].CustomerDemographics.CustomerId", equalTo(customerIdInt));
	}
	
	@Test  (testName="Invalid Date", description="PBI:153783")
	public void invalidDate() {
		
					String asOfDate = "2020-13-01";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value '"+asOfDate+"' is not valid."));
	}

}
