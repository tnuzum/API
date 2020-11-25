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

public class GetDelinquentMembers extends base{
	
	String aPIKey;
	String companyId;
	String clubId;
	Boolean includeMembersInCollections;
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
		
		includeMembersInCollections = true;
		includeTerminatedMembers= true;
		asOfDate = ReusableDates.getCurrentDate();
	}
	
	@Test  (testName="Delinquent Members Found", description="PBI:170148", enabled = true)

	public void delinquentMembersFound() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results[0]", hasKey("CustomerId"))
						.body("Results[0].AccountSummary", hasKey("CustomerId"))
						.body("Results[0].AccountSummary", hasKey("AccountBalance"))
						.body("Results[0].AccountSummary", hasKey("DelinquencyBeginDate"))
					    .body("Results[0].CustomerDemographics", hasKey("CustomerId"))
					    .body("Results[0].CustomerDemographics.Name", hasKey("FirstName"))
					    .body("Results[0].CustomerDemographics.Name", hasKey("MiddleInitial"))
					    .body("Results[0].CustomerDemographics.Name", hasKey("LastName"))
					    .body("Results[0].CustomerDemographics.Name", hasKey("PreferredName"))
					    .body("Results[0].CustomerDemographics.Name", hasKey("DisplayName"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("AddressLine1"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("AddressLine2"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("City"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("Country"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("PostalCode"))
					    .body("Results[0].CustomerDemographics.Address", hasKey("StateProvince"))
					    .body("Results[0].CustomerDemographics", hasKey("MemberID"))
					    .body("Results[0].CustomerDemographics", hasKey("DoNotMail"))
					    .body("Results[0].CustomerDemographics", hasKey("DoNotMarket"))					    
					    .body("Results[0].CustomerDemographics", hasKey("EmailAddress"))
					    .body("Results[0].CustomerDemographics", hasKey("EmailContactConsent"))
					    .body("Results[0].CustomerDemographics", hasKey("EmergencyContactName"))
					    .body("Results[0].CustomerDemographics", hasKey("EmergencyContactPhoneNumber"))
					    .body("Results[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Results[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Results[0].CustomerDemographics.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Results[0].CustomerDemographics", hasKey("HomePhoneContactConsent"))
					    .body("Results[0].CustomerDemographics.HomePhoneNumber", hasKey("Extension"))
					    .body("Results[0].CustomerDemographics.HomePhoneNumber", hasKey("Number"))
					    .body("Results[0].CustomerDemographics.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Results[0].CustomerDemographics", hasKey("MobilePhoneContactConsent"))
					    .body("Results[0].CustomerDemographics.MobilePhoneNumber", hasKey("Extension"))
					    .body("Results[0].CustomerDemographics.MobilePhoneNumber", hasKey("Number"))
					    .body("Results[0].CustomerDemographics.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Results[0].CustomerDemographics", hasKey("WorkPhoneContactConsent"))
					    .body("Results[0].CustomerDemographics.WorkPhoneNumber", hasKey("Extension"))
					    .body("Results[0].CustomerDemographics.WorkPhoneNumber", hasKey("Number"))
					    .body("Results[0].CustomerDemographics.WorkPhoneNumber", hasKey("PhoneType"))
					    .body("Results[0].CustomerDemographics.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Results[0].CustomerDemographics.PreferredPhoneNumber", hasKey("Number"))
					    .body("Results[0].CustomerDemographics.PreferredPhoneNumber", hasKey("PhoneType"))
						.body("Results[0].CustomerDemographics", hasKey("PreferredPhoneType"))
					    .body("Results[0].CustomerDemographics", hasKey("DateOfBirth"))
					    .body("Results[0].CustomerDemographics", hasKey("HomeClubNumber"))
					    .body("Results[0].CustomerDemographics", hasKey("HomeClubName"))
					    .body("Results[0].CustomerDemographics", hasKey("MemberType"))
					    .body("Results[0].CustomerDemographics", hasKey("CurrentStatusId"))
					    .body("Results[0].CustomerDemographics", hasKey("CurrentStatusDescription"));
	}
	
	@Test  (testName="Collections Member Included", description="PBI:170148")
	public void collectionsMemberIncluded() {
		
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeMembersInCollections="+includeMembersInCollections)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.MemberType", hasItem("InCollections"));
	}
	
	@Test  (testName="Collections Member Not Included", description="PBI:170148")
	public void collectionsMemberNotIncluded() {
		
					Boolean includeMembersInCollections = false;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeMembersInCollection="+includeMembersInCollections)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.MemberType", not(hasItem("InCollections")));
	}
	
	@Test  (testName="Terminated Member Included", description="PBI:170148")
	public void terminatedMemberIncluded() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.Name.FirstName", hasItem("Terminated"));
	}
	
	@Test  (testName="Terminated Member Not Included", description="PBI:170148")
	public void terminatedMemberNotIncluded() {
		
					Boolean includeTerminatedMembers= false;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.Name.FirstName", not(hasItem("Terminated")));
	}
	
	@Test  (testName="Collections and Terminated Members Included", description="PBI:170148")
	public void collectionsAndTerminatedMembersIncluded() {

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeMembersInCollections="+includeMembersInCollections+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.Name.FirstName", hasItem("Terminated"))
						.body("Results.CustomerDemographics.MemberType", hasItem("InCollections"));
	}
	
	@Test  (testName="Collections and Terminated Members Not Included", description="PBI:170148")
	public void collectionsAndTerminatedMembersNotIncluded() {
			
					Boolean includeMembersInCollections = false;
					Boolean includeTerminatedMembers= false;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate+"&includeMembersInCollections="+includeMembersInCollections+"&includeTerminatedMembers="+includeTerminatedMembers)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Results.CustomerDemographics.Name.FirstName", not(hasItem("Terminated")))
						.body("Results.CustomerDemographics.MemberType", not(hasItem("InCollections")));
	}

	@Test  (testName="Delinquent Members Not Found", description="PBI:170148")
	public void delinquentMembersNotFound() {
		
					String asOfDate = "1980-06-01";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate)
						.then()
//						.log().all()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo(204))
						.body("Message", equalTo("No Delinquent Acccounts found"));
	}
	
	@Test  (testName="Invalid Date", description="PBI:170148")
	public void invalidDate() {
		
					String asOfDate = "2020-13-01";

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/financial/getdelinquentmembers?invoiceAsOfDate="+asOfDate)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value '"+asOfDate+"' is not valid."));
	}

}
