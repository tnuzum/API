package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.base;

public class GetMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test  (testName="MemberFound - Active HOH", description="PBI:124934")
	public void MemberFoundActiveHOH() {
		
					String customerId = prop.getProperty("availableId");

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"));
	}
	
	@Test  (testName="MemberFound - Account Locked", description="PBI:124934")
	public void MemberFoundAccountLocked() {
		
					String customerId = prop.getProperty("accountLockedId");

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"));
	}
	
	@Test  (testName="MemberFound - Terminated Member", description="PBI:124934")
	public void MemberFoundTerminatedMember() {
		
					String customerId = prop.getProperty("terminatedId");

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"))
						;
	}
	
	@Test  (testName="MemberFound - Prospect Member", description="PBI:124934")
	public void MemberFoundProspectMember() {
		
				String customerId = prop.getProperty("prospectId");

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"))
						;
	}
	
	@Test  (testName="MemberFound - Collections Member", description="PBI:124934")
	public void MemberFoundCollectionsMember() {
		
					String customerId = prop.getProperty("collectionsId");

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"))
						;
	}
	
	@Test  (testName="MemberFound - Frozen Member", description="PBI:124934")
	public void MemberFoundFrozenMember() {
		
					String customerId = prop.getProperty("frozenId");

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"))
						;
	}
	
	@Test  (testName="MemberNotFound", description="PBI:124934")
	public void MemberNotFound() {
		
					int customerId = 99999;

					given()

						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
					    .body("Message", equalTo("Nothing found"));
	}
	
	@Test  (testName="Restrict Member From Search - False", description="PBI:124934")
	public void restrictMemberFromSearchFalse() {
		
					String customerId = prop.getProperty("availableId");

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result.RestrictMemberFromSearch", equalTo(false));
	}
	
	@Test  (testName="Restrict Member From Search - True", description="PBI:124934")
	public void restrictMemberFromSearchTrue() {
		
					String customerId = prop.getProperty("restrictSearchId");

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
					    .body("Result.RestrictMemberFromSearch", equalTo(true));
	}
	
	@Test  (testName="Allow Online Search - True", description="PBI:124934", enabled = false)
	public void allowOnlineSearchTrue() {
		
		// this property was added to GetCustomerInfo CORE call but not yet added to this API call
		
					String customerId = prop.getProperty("availableId");

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getmember/"+customerId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
					    .body("Result.AllowOnlineSearch", equalTo(true));
	}
}
