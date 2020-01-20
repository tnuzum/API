package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class VerifyCourseEnrollmentCapability extends base{
	
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
	
	@Test (testName="Enrollment Capability Verified",description="PBI:150004")
	public void enrollmentCapabilityVerified() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 248;
		String courseBarcodeId 	= "taxCityCo";
		String displayedGrandTotal	= "10.25";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Course Full Standby Allowed",description="PBI:150004")
	public void courseFullStandbyAllowed() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String courseBarcodeId 	= "standbyCo";
		String displayedGrandTotal	= "1500.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("CourseFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150004")
	public void customerAlreadyOnStandby() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 242;
		String courseBarcodeId 	= "standbyCo";
		String displayedGrandTotal	= "1500.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150004")
	public void customerAlreadyEnrolled() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 241;
		String courseBarcodeId 	= "standbyCo";
		String displayedGrandTotal	= "1500.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:150004")
	public void productPriceChanged() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 241;
		String courseBarcodeId 	= "PBoot430";
		String displayedGrandTotal	= "60.01";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Invalid Course BarcodeId",description="PBI:150004")
	public void invalidCourseBarcodeId() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 241;
		String courseBarcodeId 	= "NOTPBoot430";
		String displayedGrandTotal	= "60.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid CustomerId",description="PBI:150004")
	public void invalidCustomerId() {
		
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 241000;
		String courseBarcodeId 	= "PBoot430";
		String displayedGrandTotal	= "60.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:150004")
	public void enrollmentNotAllowedItem() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 237;
		String courseBarcodeId 	= "noWebCo";
		String displayedGrandTotal	= "1500.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentNotAllowed"));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:150004")
	public void enrollmentNotAllowedTerminatedMember() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 249;
		String courseBarcodeId 	= "alwaysAvailCo";
		String displayedGrandTotal	= "100.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberTerminated"));
	}
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:150004")
	public void enrollmentNotAllowedFrozenMember() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 250;
		String courseBarcodeId 	= "alwaysAvailCo";
		String displayedGrandTotal	= "100.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberFrozen"));
	}
	
	@Test (testName="Course Enrollment Closed",description="PBI:150004")
	public void courseEnrollmentClosed() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 248;
		String courseBarcodeId 	= "closedCo";
		String displayedGrandTotal	= "10.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("ItemRestrictions"));
	}
	
	@Test (testName="Course Ended",description="PBI:150004")
	public void courseEnded() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 248;
		String courseBarcodeId 	= "endedCo";
		String displayedGrandTotal	= "10.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("ItemHasEnded"));
	}
}
