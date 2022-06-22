package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;
import resources.myGets;

public class VerifyCourseEnrollmentCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */

	static String aPIKey;
	static String companyId;
	static String clubId;
	static Boolean onlineEnrollment = false;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="Enrollment Capability Verified - Member Context",description="PBI:150004")
	public void enrollmentCapabilityVerifiedMember() {
 
				String customerId = prop.getProperty("availableBId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");


			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Enrollment Capability Verified - Employee Context",description="PBI:150004")
	public void enrollmentCapabilityVerifiedEmployee() {
 

				String customerId = prop.getProperty("availableBId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Course Full Standby Allowed",description="PBI:150004")
	public void courseFullStandbyAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("standbyCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("CourseFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150004")
	public void customerAlreadyOnStandby() {
 
				String customerId = prop.getProperty("standbyBId");
				String courseId = prop.getProperty("standbyCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150004")
	public void customerAlreadyEnrolled() {
 
				String customerId = prop.getProperty("standbyAId");
				String courseId = prop.getProperty("standbyCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:150004")
	public void productPriceChanged() {
 
				String customerId = prop.getProperty("availableBId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal	= "0.01";

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:150004")
	public void schedulingConflict() {
 
				String customerId = prop.getProperty("standbyAId");
				String courseId = prop.getProperty("conflictCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("MemberSchedulingConflict"));;
	}
	
	@Test (testName="Invalid Course Id",description="PBI:150004", enabled = true)
	public void invalidCourseId() {
 
				String customerId = prop.getProperty("availableId");
				int courseId = 99999;
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid CustomerId",description="PBI:150004")
	public void invalidCustomerId() {
		
				int customerId = 99999;
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Online Sales Not Allowed - Member Context",description="PBI:150004", enabled = true)
	public void onlineSalesNotAllowedMember() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("noWebCoId");
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200);
	}
	
	@Test (testName="Online Sales Not Allowed - Employee Context",description="PBI:150004", enabled = true)
	public void onlineSalesNotAllowedEmployee() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("noWebCoId");
				
				Boolean onlineEnrollment = false;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"))
				.body("Details", equalTo(""));
	}
	
	@Test (testName="Enrollment Allowed - Terminated Member",description="PBI:150004", enabled = true)
	public void enrollmentAllowedTerminatedMember() {
		
		// BO > Configuration > Corporate > Clubs > Settings > Allow Term Members to enroll...
 
				String customerId = prop.getProperty("terminatedId");
				String courseId = prop.getProperty("taxSingleCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
//				.body("AllowedToEnroll", equalTo(false))
//				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
//				.body("Details", equalTo("MemberTerminated"));
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Enrollment Allowed - Frozen Member",description="PBI:150004", enabled = true)
	public void enrollmentAllowedFrozenMember() {
		
		// BO > Configuration > Corporate > Clubs > Settings > Allow Freeze Members to enroll...
 
				String customerId = prop.getProperty("frozenId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
//				.body("AllowedToEnroll", equalTo(false))
//				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
//				.body("Details", equalTo("MemberFrozen"));
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Collections Member - Paid Course",description="PBI:150004", enabled = true)		 
	
	public void collectionsMemberPaidCourse() {
		
		// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
		
				Boolean onlineEnrollment = true;
				String customerId = prop.getProperty("collectionsId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Collections Member - Free Course",description="PBI:150004", enabled = true)		 
	
	public void collectionsMemberFreeCourse() {
		
		// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
		
				Boolean onlineEnrollment = true;
				String customerId = prop.getProperty("collectionsId");
				String courseId = prop.getProperty("freeCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Prospect - Employee Context",description="PBI:150004", enabled = true)

	public void prospectEmployeeContext() {
		
				Boolean onlineEnrollment = false;
				String customerId = prop.getProperty("prospectId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Prospect - Member Context",description="PBI:150004", enabled = true)

	public void prospectMemberContext() {

				String customerId = prop.getProperty("prospectId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"));
	}
	
	@Test (testName="Enrollment Closed - Member Context",description="PBI:150004", enabled = true)
	public void enrollmentClosedMember() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("closedCoId");
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Enrollment Closed - Employee Context",description="PBI:150004", enabled = true)
	public void enrollmentClosedEmployee() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("closedCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Course Ended",description="PBI:150004")
	public void courseEnded() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("endedCoId");
				//String displayedGrandTotal = prop.getProperty("endedCoPrice");
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasEnded"));
	}
	
	@Test (testName="Enrollment Not Open - Member Context",description="PBI:150004", enabled = true)
	public void enrollmentNotOpenMember() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("neverAvailCoId");
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentNotOpen"));
	}
	
	@Test (testName="Enrollment Not Open - Employee Context",description="PBI:150004", enabled = true)
	public void enrollmentNotOpenEmployee() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("neverAvailCoId");

				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentNotOpen"));
	}
	
	@Test (testName="Credit Limited Exceeded - Member Context",description="PBI:150004", enabled = true)
	
	public void creditLimitedExceededMemberContext() {
		// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
		
		Boolean onlineEnrollment = true;
 
				String customerId = prop.getProperty("creditLimitId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Credit Limited Exceeded - Employee Context",description="PBI:150004", enabled = true)
	
	public void creditLimitedExceededEmployeeContext() {
 
				String customerId = prop.getProperty("creditLimitId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, courseId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="ItemId Required",description="PBI:150004")
	public void itemIdRequired() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("NOTalwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				Boolean onlineEnrollment = true;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for ItemId."));
	}
	
	@Test (testName="CustomerId Required",description="PBI:150004")
	public void customerIdRequired() {
 
				String customerId = prop.getProperty("NULLValue");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				Boolean onlineEnrollment = true;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for CustomerId."));
	}
	
	@Test (testName="Displayed Grand Total Required",description="PBI:150004")
	public void displayedGrandTotalRequired() {
 
				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("NULLValue");
				Boolean onlineEnrollment = true;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for DisplayedGrandTotal."));
	}
	
	
}
