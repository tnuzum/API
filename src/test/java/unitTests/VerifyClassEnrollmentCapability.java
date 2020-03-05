package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class VerifyClassEnrollmentCapability extends base{
	
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
	
	@Test (testName="Enrollment Capability Verified",description="PBI:150003")
	public void enrollmentCapabilityVerified() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				Boolean onlineEnrollment = false;
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().all()
						.assertThat()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));			
	}
	
	@Test (testName="Class Full Standby Allowed",description="PBI:150003", enabled = true)
	public void classFullStandbyAllowed() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("ClassFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150003", enabled = true)
	public void customerAlreadyOnStandby() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyDId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150003", enabled = true)
	public void customerAlreadyEnrolled() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyCId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:150003")
	public void productPriceChanged() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal	= "0.01";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:150003", enabled = false)
	public void schedulingConflict() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyAId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("SchedulingConflict"));
	}
	
	@Test (testName="Invalid Class Id",description="PBI:150003", enabled = false)
	public void invalidClassId() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		int classId = 99999;
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid Occurrence",description="PBI:150003")
	public void invalidOccurrence() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence 	= "2119-12-12";
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid CustomerId",description="PBI:150003")
	public void invalidCustomerId() {
		
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		int customerId = 99999;
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Online Sales Not Allowed - Member Context",description="PBI:150003", enabled = true)
	public void onlineSalesNotAllowedMember() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("noWebClId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				String displayedGrandTotal = prop.getProperty("noWebClPrice");
				Boolean onlineEnrollment = true;

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("NotAllowed"));
	}
	
	@Test (testName="Online Sales Not Allowed - Employee Context",description="PBI:150003", enabled = true)
	public void onlineSalesNotAllowedEmployee() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("noWebClId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				String displayedGrandTotal = prop.getProperty("noWebClPrice");
				Boolean onlineEnrollment = false;

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"))
						.body("Details", equalTo(""));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:150003", enabled = true)
	
	public void enrollmentNotAllowedTerminatedMember() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("terminatedId");
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberTerminated"));
	}

	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:150003", enabled = false)
			 
	public void enrollmentNotAllowedCollectionsMember() {

				String companyId  = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"));
	}

	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:150003", enabled = true)
	
	// disabled while research why AllowedToEnroll = true now for terminated member
	
	public void enrollmentNotAllowedFrozenMember() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("frozenId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberFrozen"));
	}
	
	@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:150003", enabled = false)

	public void enrollmentNotAllowedProspectMember() {

				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("prospectId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Class Ended",description="PBI:150003")
	public void classEnded() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("endedClId");
				String classOccurrence = prop.getProperty("endedClOccurrence");
				String displayedGrandTotal = prop.getProperty("endedClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentHasEnded"));
	}
	
	@Test (testName="Enrollment Closed",description="PBI:150003", enabled = true)
	public void enrollmentClosed() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String classId = prop.getProperty("closedClId");
		String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay8AM();
		String displayedGrandTotal = prop.getProperty("closedClPrice");
		

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Enrollment Not Open",description="PBI:143588", enabled = true)
	public void enrollmentNotOpen() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("neverAvailClId");
				String classOccurrence = prop.getProperty("neverAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("neverAvailClPrice");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentNotOpen"));
	}
	
	@Test (testName="Credit Limited Exceeded - Member",description="PBI:150003", enabled = false)
	
	public void creditLimitedExceededMember() {
		
		Boolean onlineEnrollment = false;
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("creditLimitId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Credit Limited Exceeded - Employee",description="PBI:150003", enabled = true)
	
	public void creditLimitedExceededEmployee() {
		
		Boolean onlineEnrollment = false;
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("creditLimitId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}

}
