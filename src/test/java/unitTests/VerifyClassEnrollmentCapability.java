package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;
import resources.myGets;

public class VerifyClassEnrollmentCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	Boolean onlineEnrollment = false;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="Enrollment Capability Verified - Member Context",description="PBI:150003")
	public void enrollmentCapabilityVerifiedMember() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));			
	}
	
	@Test (testName="Enrollment Capability Verified - Employee Context",description="PBI:150003")
	public void enrollmentCapabilityVerifiedEmployee() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));			
	}
	
	@Test (testName="Class Full Standby Allowed",description="PBI:150003", enabled = true)
	public void classFullStandbyAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("ClassFullStandbyAllowed"));
	}
	
	@Test (testName="Customer Already On Standby",description="PBI:150003", enabled = true)
	public void customerAlreadyOnStandby() {
 
				String customerId = prop.getProperty("standbyDId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Customer Already Enrolled",description="PBI:150003", enabled = true)
	public void customerAlreadyEnrolled() {
 
				String customerId = prop.getProperty("standbyCId");
				String classId = prop.getProperty("standbyClId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:150003")
	public void productPriceChanged() {
 
		String customerId = prop.getProperty("availableId");
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal	= "0.01";

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:150003", enabled = true)
	public void schedulingConflict() {
 
				String customerId = prop.getProperty("standbyCId");
				String classId = prop.getProperty("conflictClId");
				String classOccurrence = prop.getProperty("conflictClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().all()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("MemberSchedulingConflict"));
	}
	
	@Test (testName="Invalid Class Id",description="PBI:150003", enabled = true)
	public void invalidClassId() {
 
		String customerId = prop.getProperty("availableId");
		int classId = 99999;
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid Occurrence",description="PBI:150003")
	public void invalidOccurrence() {
		
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence 	= "2119-12-12";
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Invalid CustomerId",description="PBI:150003")
	public void invalidCustomerId() {
		
		int customerId = 99999;
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Online Sales Not Allowed - Member Context",description="PBI:150003", enabled = true)
	public void onlineSalesNotAllowedMember() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("noWebClId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				
				Boolean onlineEnrollment = true;
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200);
	}
	
	@Test (testName="Online Sales Not Allowed - Employee Context",description="PBI:150003", enabled = true)
	public void onlineSalesNotAllowedEmployee() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("noWebClId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				
				Boolean onlineEnrollment = false;
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"))
				.body("Details", equalTo(""));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:150003", enabled = true)
	public void enrollmentNotAllowedTerminatedMember() {
		
	// BO > Configuration > Corporate > Clubs > Settings > Allow Term Members to enroll...
 
		String customerId = prop.getProperty("terminatedId");
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

		Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
		JsonPath js = ReusableMethods.rawToJson(res);
		double grandTotal = js.getDouble("Result.GrandTotal");

		given()
//				.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
		.then()
//				.log().body()
//			.body("AllowedToEnroll", equalTo(false))
//			.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
//			.body("Details", equalTo("MemberTerminated"));
			.body("AllowedToEnroll", equalTo(true))
			.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}

	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:150003", enabled = true)		 
	
	public void enrollmentNotAllowedCollectionsMember() {
		
		// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
		
			Boolean onlineEnrollment = true;
			String customerId = prop.getProperty("collectionsId");
			String classId = prop.getProperty("alwaysAvailClId");
			String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
			
			Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
			JsonPath js = ReusableMethods.rawToJson(res);
			double grandTotal = js.getDouble("Result.GrandTotal");

		given()
//				.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
		.then()
//				.log().body()
			.assertThat().statusCode(200)
			.time(lessThan(60L),TimeUnit.SECONDS)
			.body("AllowedToEnroll", equalTo(true))
			.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}

	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:150003", enabled = true)
	
	public void enrollmentNotAllowedFrozenMember() {
		
	// BO > Configuration > Corporate > Clubs > Settings > Allow Freeze Members to enroll...
 
		String customerId = prop.getProperty("frozenId");
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		
		Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
		JsonPath js = ReusableMethods.rawToJson(res);
		double grandTotal = js.getDouble("Result.GrandTotal");

	given()
//		.log().all()
		.header("accept", "application/json")
		.header("X-Api-Key", aPIKey)
		.header("X-CompanyId", companyId)
		.header("X-ClubId", clubId)
	.when()
		.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
	.then()
//		.log().body()
//		.body("AllowedToEnroll", equalTo(false))
//		.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
//		.body("Details", equalTo("MemberFrozen"));
		.body("AllowedToEnroll", equalTo(true))
		.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Prospect - Employee Context",description="PBI:150003", enabled = true)

	public void prospectEmployeeContext() {
		
			String customerId = prop.getProperty("prospectId");
			String classId = prop.getProperty("alwaysAvailClId");
			String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

			Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
			JsonPath js = ReusableMethods.rawToJson(res);
			double grandTotal = js.getDouble("Result.GrandTotal");

		given()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
		.then()
//				.log().body()
			.assertThat().statusCode(200)
			.time(lessThan(60L),TimeUnit.SECONDS)
			.body("AllowedToEnroll", equalTo(true))
			.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Prospect - Member Context",description="PBI:150003", enabled = true)
	public void prospectMemberContext() {
		
				Boolean onlineEnrollment = true;
				String customerId = prop.getProperty("prospectId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"));
	}
	
	@Test (testName="Class Ended",description="PBI:150003")
	public void classEnded() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("endedClId");
				String classOccurrence = prop.getProperty("endedClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasEnded"));
	}
	
	@Test (testName="Enrollment Closed - Member Context",description="PBI:150003", enabled = true)
	public void enrollmentClosedMember() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("closedClId");
				String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay8AM();
				
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Enrollment Closed - Employee Context",description="PBI:150003", enabled = true)
	public void enrollmentClosedEmployee() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("closedClId");
				String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay8AM();
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentHasClosed"));
	}
	
	@Test (testName="Enrollment Not Open - Member Context",description="PBI:143588", enabled = true)
	public void enrollmentNotOpenMember() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("neverAvailClId");
				String classOccurrence = prop.getProperty("neverAvailClOccurrence");
				Boolean onlineEnrollment = true;
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentNotOpen"));
	}
	
	@Test (testName="Enrollment Not Open - Employee Context",description="PBI:143588", enabled = true)
	public void enrollmentNotOpenEmployee() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("neverAvailClId");
				String classOccurrence = prop.getProperty("neverAvailClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
					.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("AllowedToEnroll", equalTo(false))
				.body("EnrollmentStatus", equalTo("EnrollmentNotAllowed"))
				.body("Details", equalTo("EnrollmentNotOpen"));
	}
	
	@Test (testName="Credit Limited Exceeded - Member",description="PBI:150003", enabled = true)
	
	public void creditLimitedExceededMember() {
		// this call with return allowed to enroll = true, but the enrollment call returns allowed to enroll = false with 'AccountProblem' message
		
				Boolean onlineEnrollment = true;
				String customerId = prop.getProperty("creditLimitId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				
				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="Credit Limited Exceeded - Employee",description="PBI:150003", enabled = true)
	
	public void creditLimitedExceededEmployee() {
 
				String customerId = prop.getProperty("creditLimitId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

				Response res = myGets.getClassCoursePricing(aPIKey, companyId, clubId, customerId, classId);
				JsonPath js = ReusableMethods.rawToJson(res);
				double grandTotal = js.getDouble("Result.GrandTotal");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+grandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("AllowedToEnroll", equalTo(true))
				.body("EnrollmentStatus", equalTo("EnrollmentAllowed"));
	}
	
	@Test (testName="ItemId Required",description="PBI:150003", enabled = true)
	public void itemIdRequired() {
 
				String customerId = prop.getProperty("availabledId");
				String classId = prop.getProperty("NOTalwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for ItemId."));
	}
	
	@Test (testName="Class Occurrence Required",description="PBI:150003", enabled = true)
	public void customerOccurrenceRequired() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("NOTalwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for ClassOccurrence."));
	}
	
	@Test (testName="CustomerId Required",description="PBI:150003", enabled = true)
	public void customerIdRequired() {
 
				String customerId = prop.getProperty("NOTavailableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for CustomerId."));
	}
	
	@Test (testName="Displayed Grand Total Required",description="PBI:150003", enabled = true)
	public void displayedGrandTotalRequired() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("NOTalwaysAvailClPrice");

			given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("The value 'null' is not valid for DisplayedGrandTotal."));
	}

}
