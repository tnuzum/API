package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class VerifyClassEnrollmentCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Enrollment Capability Verified",description="PBI:150003")
	public void enrollmentCapabilityVerified() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "alwaysAvailCl";
		String classOccurrence 	= "2024-12-12";
		String displayedClassPrice	= "10.00";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"))
						;
	}
	@Test (testName="Class Full Standby Allowed",description="PBI:150003")
	public void classFullStandbyAllowed() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "standbyCl";
		String classOccurrence 	= "2022-12-06";
		String displayedClassPrice	= "150.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("ClassFullStandbyAllowed"));
	}
	@Test (testName="Customer Already On Standby",description="PBI:150003")
	public void customerAlreadyOnStandby() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 246;
		String classBarcodeId 	= "standbyCl";
		String classOccurrence 	= "2023-01-02";
		String displayedClassPrice	= "150.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	@Test (testName="Customer Already Enrolled",description="PBI:150003")
	public void customerAlreadyEnrolled() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 245;
		String classBarcodeId 	= "standbyCl";
		String classOccurrence 	= "2023-01-02";
		String displayedClassPrice	= "150.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"))
						;
	}
	@Test (testName="Product Price Changed",description="PBI:150003")
	public void productPriceChanged() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "alwaysAvailCl";
		String classOccurrence 	= "2024-12-12";
		String displayedClassPrice	= "0.01";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ProductPriceChanged"))
						;
	}
	@Test (testName="Invalid Class BarcodeId",description="PBI:150003")
	public void invalidClassBarcodeId() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 241;
		String classBarcodeId 	= "NOTstandbyCl";
		String classOccurrence 	= "2022-12-06";
		String displayedClassPrice	= "150.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"))
						;
	}
	@Test (testName="Invalid Occurrence",description="PBI:150003")
	public void invalidOccurrence() {
		
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "Balance44";
		String classOccurrence 	= "2119-12-12";
		String displayedClassPrice	= "0.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"))
						;
	}
	@Test (testName="Invalid CustomerId",description="PBI:150003")
	public void invalidCustomerId() {
		
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223000;
		String classBarcodeId 	= "Balance44";
		String classOccurrence 	= "2019-12-12";
		String displayedClassPrice	= "0.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerNotFound"))
						;
	}
	@Test (testName="Enrollment Not Allowed",description="PBI:150003")
	public void enrollmentNotAllowed() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "BalanceItem";
		String classOccurrence 	= "2022-12-06";
		String displayedClassPrice	= "15.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentNotAllowed"))
						;
	}
}
