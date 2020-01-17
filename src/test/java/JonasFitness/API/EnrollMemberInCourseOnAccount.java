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
import resources.base;

public class EnrollMemberInCourseOnAccount extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	
	/*
	// !!! Disabled until an unenroll is created
	@Test (testName="Member Enrolled - Course Already Started",description="PBI:143589")
	public void memberEnrolledCourseStarted() {
		
				int customerId = 229;
				String courseBarcodeId = "alwaysAvailCo";
				String displayedGrandTotal = "100.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
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
	
		@Test (testName="Member Enrolled - Course Not Started",description="PBI:143589")
	public void memberEnrolledCourseNotStarted() {
		
				int customerId = 248;
				String courseBarcodeId = "notStartedCo";
				String displayedGrandTotal = "150.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
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

	@Test (testName="Member Enrolled On Standby",description="PBI:143589")
	public void memberEnrolledOnStandby() {
		
				int customerId 			= 245;
				String courseBarcodeId 	= "standbyCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
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
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:143589")
	public void memberEnrolledFreeCourse() {
		
				int customerId = 248;
				String courseBarcodeId = "freeCo";
				String displayedGrandTotal = "0.00";
				String enrollCustomerAsStandby = "true";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
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
	} */
	
	@Test (testName="No FOP - Account Problem",description="PBI:143589") // failed to create invoice because member's billing info not setup
	public void noFOP_AccountProblem() {
		
				int customerId = 247;
				String courseBarcodeId = "alwaysAvailCo";
				String displayedGrandTotal = "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:143589")
	public void memberAlreadyEnrolled() {
		
				int customerId 			= 241;
				String courseBarcodeId 	= "standbyCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:143589")
	public void productPriceChanged() {
		
				int customerId = 247;
				String courseBarcodeId = "alwaysAvailCo";
				String displayedGrandTotal = "10.01";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	
	@Test (testName="Member Already On Standby",description="PBI:143589")
	public void memberAlreadyOnStandby() {
		
				int customerId 			= 242;
				String courseBarcodeId 	= "standbyCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143589")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 			= 247;
				String courseBarcodeId 	= "standbyCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:143589")
	public void customerNotFound() {
		
				int customerId 			= 245000;
				String courseBarcodeId 	= "standbyCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Course Not Found",description="PBI:143589")
	public void courseNotFound() {
		
				int customerId 			= 245;
				String courseBarcodeId 	= "NOTstandbyCo";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:143589")
	public void enrollmentNotAllowed_Item() {
		
				int customerId 			= 237;
				String courseBarcodeId 	= "noWebCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	}
	
	@Test (testName="Enrollment Not Open",description="PBI:143589")
	public void enrollmentNotOpen() {
		
				int customerId 			= 237;
				String courseBarcodeId 	= "neverAvailCo";
				String displayedGrandTotal	= "1500.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Enroll Terminated Member",description="PBI:143589")
	public void enrollTerminatedMember() {
		
				int customerId 			= 249;
				String courseBarcodeId 	= "alwaysAvailCo";
				String displayedGrandTotal	= "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - MemberTerminated"));
	}
	
	@Test (testName="Enroll Collections Member",description="PBI:143589")
	public void enrollCollectionsMember() {
		
				int customerId 			= 227;
				String courseBarcodeId 	= "alwaysAvailCo";
				String displayedGrandTotal	= "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Enroll Frozen Member",description="PBI:143589")
	public void enrollFrozenMember() {
		
				int customerId 			= 250;
				String courseBarcodeId 	= "alwaysAvailCo";
				String displayedGrandTotal	= "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - MemberFrozen"));
	}
	
	@Test (testName="Enroll Prospect Member",description="PBI:143589")
	public void enrollProspectMember() {
		
				int customerId 			= 228;
				String courseBarcodeId 	= "alwaysAvailCo";
				String displayedGrandTotal	= "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Credit Limited Exceeded",description="PBI:143589")
	public void creditLimitedExceeded() {
		
				int customerId 			= 251;
				String courseBarcodeId 	= "alwaysAvailCo";
				String displayedGrandTotal	= "100.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Course Enrollment Closed",description="PBI:143589")
	public void courseEnrollmentClosed() {
		
				int customerId 			= 248;
				String courseBarcodeId 	= "closedCo";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Course Ended",description="PBI:143589")
	public void courseEnded() {
		
				int customerId 			= 248;
				String courseBarcodeId 	= "endedCo";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseBarcodeId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
}
