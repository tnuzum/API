package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class VerifyCourseEnrollmentCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */

	public static Boolean onlineEnrollment = false;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Enrollment Capability Verified",description="PBI:150004")
	public void enrollmentCapabilityVerified() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String courseId = prop.getProperty("alwaysAvailCoId");
		String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Course Full Standby Allowed",description="PBI:150004")
	public void courseFullStandbyAllowed() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("standbyCoId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("CourseFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150004")
	public void customerAlreadyOnStandby() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyBId");
				String courseId = prop.getProperty("standbyCoId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150004")
	public void customerAlreadyEnrolled() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyAId");
				String courseId = prop.getProperty("standbyCoId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:150004")
	public void productPriceChanged() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal	= "0.01";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Invalid Course Id",description="PBI:150004", enabled = true)
	public void invalidCourseId() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				int courseId = 99999;
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid CustomerId",description="PBI:150004")
	public void invalidCustomerId() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				int customerId = 99999;
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Online Sales Not Allowed - Member Context",description="PBI:150004", enabled = true)
	public void onlineSalesNotAllowedMember() {
		
		// 500 error
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("noWebCoId");
				String displayedGrandTotal = prop.getProperty("noWebCoPrice");
				Boolean onlineEnrollment = true;

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("NotAllowed"))
//						.body("EnrollmentStatus", equalTo("ItemNotFound"))
//						.body("Details", equalTo(""))
						;
	}
	
	@Test (testName="Online Sales Not Allowed - Employee Context",description="PBI:150004", enabled = true)
	public void onlineSalesNotAllowedEmployee() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("noWebCoId");
				String displayedGrandTotal = prop.getProperty("noWebCoPrice");
				Boolean onlineEnrollment = false;

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"))
						.body("Details", equalTo(""));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:150004", enabled = true)
	public void enrollmentNotAllowedTerminatedMember() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("terminatedId");
				String courseId = prop.getProperty("taxSingleCoId");
				String displayedGrandTotal = prop.getProperty("taxSingleCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberTerminated"));
	}
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:150004", enabled = true)
	public void enrollmentNotAllowedFrozenMember() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("frozenId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberFrozen"));
	}
	
	@Test (testName="Course Enrollment Closed",description="PBI:150004", enabled = true)
	public void courseEnrollmentClosed() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("closedCoId");
				String displayedGrandTotal = prop.getProperty("closedCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Course Ended",description="PBI:150004")
	public void courseEnded() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("endedCoId");
				String displayedGrandTotal = prop.getProperty("endedCoPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentHasEnded"));
	}
	
	@Test (testName="Enrollment Not Open",description="PBI:150004", enabled = true)
	public void enrollmentNotOpen() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("neverAvailClId");
				String displayedGrandTotal = prop.getProperty("neverAvailClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentNotOpen"));
	}
}
