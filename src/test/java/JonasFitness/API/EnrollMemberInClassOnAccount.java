package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import java.io.IOException;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class EnrollMemberInClassOnAccount extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	/* !!! Disabled until an unenroll is created
	@Test (testName="Member Enrolled - Class Already Started",description="PBI:143588")
	public void memberEnrolledClassStarted() {
		
				int customerId = 229;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2025-12-31";
				String displayedGrandTotal = "10.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}
	
		@Test (testName="Member Enrolled - Class Already Not Started",description="PBI:143588")
	public void memberEnrolledClassNotStarted() {
		
				int customerId = 248;
				String classBarcodeId = "notStartedCl";
				String classOccurrence = "2119-12-25";
				String displayedGrandTotal = "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:143588")
	public void memberEnrolledOnStandby() {
		
				int customerId 			= 223;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2022-12-06";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.Enrolled", equalTo(false))
						.body("Result.EnrollmentStatus", equalTo("StandBy"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}
	
	@Test (testName="Member Enrolled - Free Class",description="PBI:143588")
	public void memberEnrolledFreeClass() {
		
				int customerId = 248;
				String classBarcodeId = "freeCl";
				String classOccurrence = "2025-12-31";
				String displayedGrandTotal = "0.00";
				String enrollCustomerAsStandby = "true";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}*/
	
	@Test (testName="No FOP - Account Problem",description="PBI:143588") // failed to create invoice because member's billing info not setup
	public void noFOP_AccountProblem() {
		
				int customerId = 247;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2025-12-31";
				String displayedGrandTotal = "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:143588")
	public void memberAlreadyEnrolled() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:143588")
	public void productPriceChanged() {
		
				int customerId = 247;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2025-12-31";
				String displayedGrandTotal = "10.01";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:143588")
	public void memberAlreadyOnStandby() {
		
				int customerId 			= 246;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143588")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:143588")
	public void customerNotFound() {
		
				int customerId 			= 245000;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Class Not Found",description="PBI:143588")
	public void classNotFound() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2122-12-06";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:143588")
	public void enrollmentNotAllowed_Item() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "noWebCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	}
	
	@Test (testName="Enrollment Not Open",description="PBI:143588")
	public void enrollmentNotOpen() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "neverAvailCl";
				String classOccurrence 	= "2119-12-25";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:143588")
	public void schedulingConflict() {
		
				int customerId 			= 241;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:143588")
	public void enrollmentNotAllowedTerminatedMember() {
		
				int customerId 			= 249;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - MemberTerminated"));
	}
	
	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:143588")
	public void enrollmentNotAllowedCollectionsMember() {
		
				int customerId 			= 227;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:143588")
	public void enrollmentNotAllowedFrozenMember() {
		
				int customerId 			= 250;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - MemberFrozen"));
	}
	
	@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:143588")
	public void enrollmentNotAllowedProspectMember() {
		
				int customerId 			= 228;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Class Ended",description="PBI:143588")
	public void classEnded() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "endedCl";
				String classOccurrence 	= "2019-12-13";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Class Enrollment Closed",description="PBI:143588")
	public void classEnrollmentClosed() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "closedCl";
				String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay();
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Credit Limited Exceeded",description="PBI:143588")
	public void creditLimitedExceeded() {
		
				int customerId 			= 251;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
}
