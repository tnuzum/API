package JonasFitness.API;

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
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));			
	}
	
	@Test (testName="Class Full Standby Allowed",description="PBI:150003")
	public void classFullStandbyAllowed() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(true))
						.body("EnrollmentStatus", equalTo("ClassFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150003")
	public void customerAlreadyOnStandby() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyDId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150003")
	public void customerAlreadyEnrolled() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyCId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
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
		String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal	= "0.01";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:150003")
	public void schedulingConflict() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("standbyAId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("SchedulingConflict"));
	}
	
	@Test (testName="Invalid Class BarcodeId",description="PBI:150003")
	public void invalidClassBarcodeId() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String classBarcodeId = "NOTalwaysAvailClBarcodeId";
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid Occurrence",description="PBI:150003")
	public void invalidOccurrence() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence 	= "2119-12-12";
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
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
		String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.assertThat()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:150003")
	public void enrollmentNotAllowedItem() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classBarcodeId = prop.getProperty("noWebClBarcodeId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				String displayedGrandTotal = prop.getProperty("noWebClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("EnrollmentNotAllowed"))
						;
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:150003")
	public void enrollmentNotAllowedTerminatedMember() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("terminatedId");
		String classBarcodeId = prop.getProperty("taxSingleClBarcodeId");
		String classOccurrence = prop.getProperty("taxSingleClOccurrence");
		String displayedGrandTotal = prop.getProperty("taxSingleClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberTerminated"));
	}
	
	/*@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:150003")
	public void enrollmentNotAllowedCollectionsMember() {
	
!!! this test is not preventing enrollment, but enrollment tests fails with account problem
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 227;
		String classBarcodeId 	= "alwaysAvailCl";
		String classOccurrence 	= "2025-12-31";
		String displayedGrandTotal	= "10.00";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"))
						;
	}*/
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:150003")
	public void enrollmentNotAllowedFrozenMember() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("frozenId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("MemberFrozen"));
	}
	
	/*@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:150003")
	public void enrollmentNotAllowedProspectMember() {
		
!!! this test is not preventing enrollment, but enrollment tests fails with account problem
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("prospectId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"));
	}*/
	
	@Test (testName="Class Ended",description="PBI:150003")
	public void classEnded() {
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("availableId");
				String classBarcodeId = prop.getProperty("endedClBarcodeId");
				String classOccurrence = prop.getProperty("endedClOccurrence");
				String displayedGrandTotal = prop.getProperty("endedClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("ItemHasEnded"));
	}
	
	@Test (testName="Class Enrollment Closed",description="PBI:150003")
	public void classEnrollmentClosed() {
 
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String classBarcodeId = prop.getProperty("closedClBarcodeId");
		String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay();
		String displayedGrandTotal = prop.getProperty("closedClPrice");
		

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
//						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
						.body("Details", equalTo("ItemRestrictions"));
	}
	
	/*@Test (testName="Credit Limited Exceeded",description="PBI:150003")
	public void creditLimitedExceeded() {
	
!!! this test is not preventing enrollment, but enrollment tests fails with account problem
 
				String companyId = prop.getProperty("X-CompanyId");
				String clubId = prop.getProperty("X-Club1Id");
				String customerId = prop.getProperty("creditLimitId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.then()
						.log().body()
						.body("AllowedToEnroll", equalTo(false))
						.body("Message", equalTo("AccountProblem"));
	}*/

}
