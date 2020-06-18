package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
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
	public void outstandingInvoicesFound() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate")
//						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
					    .body("Result[0].OutstandingInvoices[0]", hasKey("NsfFeeApplied"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("CustomerId"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceId"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("ReceiptNumber"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceBalance"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceCategory"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceCreationDate"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceDueDate"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceDescription"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoicePaidAmount"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceTotal"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("NsfFeeApplied"));
	}
	
	@Test  (testName="Outstanding Invoices Found Without Date", description="PBI:153783")
	public void outstandingInvoicesFoundWithoutDate() {

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
						.time(lessThan(60L),TimeUnit.SECONDS)
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
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceDueDate"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceDescription"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoicePaidAmount"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("InvoiceTotal"))
					    .body("Result[0].OutstandingInvoices[0]", hasKey("NsfFeeApplied"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("CustomerId"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceId"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("ReceiptNumber"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceBalance"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceCategory"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceCreationDate"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceDueDate"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceDescription"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoicePaidAmount"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("InvoiceTotal"))
					    .body("Result[0].OutstandingInvoices[1]", hasKey("NsfFeeApplied"));
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
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.CustomerDemographics.Name.FirstName", hasItem("Terminated"))
						.body("Result.CustomerDemographics.MemberType", hasItem("InCollections"));
	}

	@Test  (testName="Outstanding Invoices Not Found", description="PBI:153783")
	public void outstandingInvoicesNotFound() {
		
					String asOfDate = "1980-06-01";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo(204))
						.body("Messages[0]", equalTo("No outstanding invoices found"));
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
						.get("/api/v3/financial/getmemberswithoutstandinginvoices?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollection)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value '"+asOfDate+"' is not valid."));
	}

}
