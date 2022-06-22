package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
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

public class EnrollMemberInCourseOnAccount extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	static Boolean onlineEnrollment = true;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="Member Enrolled - Paid Course Already Started",description="PBI:143589")
	public void memberEnrolledPaidCourseStarted() {
		
				String c = prop.getProperty("availableBId");
				int customerId = Integer.parseInt(c);				
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().all()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");	
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
		@Test (testName="Member Enrolled - Paid Course Not Started",description="PBI:143589")
	public void memberEnrolledPaidCourseNotStarted() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				
				String courseId = prop.getProperty("notStartedCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();

				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
		
	@Test (testName="Member Enrolled On Standby",description="PBI:143589")
	public void memberEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				
				String courseId = prop.getProperty("standbyCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}	
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:143589")
	public void memberEnrolledFreeCourse() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				
				String courseId = prop.getProperty("freeCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}	
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	} 
	
	@Test (testName="Member Enrolled - Free Course - Collections Member",description="PBI:143589")
	public void memberEnrolledFreeCourseCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				
				String courseId = prop.getProperty("freeCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}	
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	} 
	
	@Test (testName="No FOP - Account Problem",description="PBI:143589") // failed to create invoice because member's billing info not setup
	public void noFOP_AccountProblem() {
		
				String c = prop.getProperty("noFOPId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:143589")
	public void memberAlreadyEnrolled() {
		
				String c = prop.getProperty("standbyAId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("standbyCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:143589")
	public void productPriceChanged() {
		
				String c = prop.getProperty("availableBId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = "10.01";
				Boolean enrollCustomerAsStandby = true;

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:143589")
	public void memberAlreadyOnStandby() {
		
				String c = prop.getProperty("standbyBId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("standbyCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143589")
	public void memberNotEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("standbyCoId");
				String enrollCustomerAsStandby = "false";
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:143589")
	public void customerNotFound() {
		
				int customerId 	= 245000;
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				Boolean enrollCustomerAsStandby = true;

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Course Not Found",description="PBI:143589", enabled = true)
	public void courseNotFound() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				int courseId = 99999;
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				Boolean enrollCustomerAsStandby = true;

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Course Not Available Online - Member",description="PBI:143589", enabled = true)
	public void courseNotAvailableOnlineMember() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("noWebCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
			.then()
//				.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Course Not Available Online - Employee",description="PBI:143589", enabled = true)
	public void courseNotAvailableOnlineEmployee() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				Boolean enrollCustomerAsStandby = true;
				
				Boolean onlineEnrollment = false;
				String courseId = prop.getProperty("noWebCoId");
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				Response res =
						given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.body("Status", equalTo(500))
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						Assert.assertTrue(js.getString("Message").contains("Internal server error"));
				
						/*
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");	
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						} */

	}
	
	@Test (testName="Enrollment Not Open",description="PBI:143589", enabled = true)
	public void enrollmentNotOpen() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("neverAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotOpen"));
	}
	
	@Test (testName="Enrollment Allowed - Terminated Member",description="PBI:143589", enabled = true)
	public void enrollmentAllowedTerminatedMember() {
		
				String c = prop.getProperty("terminatedId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

				//Response res = 
						
				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - NotAllowed"));
					/*
					.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
					int enrollmentId = js.getInt("Result.EnrollmentId");
					int invoiceId = js.getInt("Result.InvoiceId");
				
					if (res.statusCode() == 200) {
						ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
						ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
					}	*/
		}
		else{
			Assert.assertTrue(false); //failing test because isEnrolled() == true
		}
	}
	
	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:143589")
	public void enrollmentNotAllowedCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Enrollment Allowed - Frozen Member",description="PBI:143589", enabled = true)
	public void enrollmentAllowedFrozenMember() {
		
				String c = prop.getProperty("frozenId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

				Response res = given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
//					.assertThat().statusCode(400)
//					.body("Message", equalTo("EnrollmentNotAllowed - MemberFrozen"));
					.extract().response();
					
					JsonPath js = ReusableMethods.rawToJson(res);
					int enrollmentId = js.getInt("Result.EnrollmentId");
					int invoiceId = js.getInt("Result.InvoiceId");
				
					if (res.statusCode() == 200) {
						ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
						ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}	
					}
					else{
						Assert.assertTrue(false); //failing test because isEnrolled() == true
					}
	}
	
	@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:143589")
	public void enrollmentNotAllowedProspectMember() {
		
				String c = prop.getProperty("prospectId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");
				
				// BO > Configuration > Corporate > Clubs > Settings > Allow Term Members to enroll...

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - NotAllowed"));
	}
	
	@Test (testName="Credit Limited Exceeded",description="PBI:143589", enabled = true)
	public void creditLimitedExceeded() {
		
				String c = prop.getProperty("creditLimitId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("noAlternatePaymentCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Course Enrollment Closed",description="PBI:143589")
	public void courseEnrollmentClosed() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("closedCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentHasClosed"));
	}
	
	@Test (testName="Course Ended",description="PBI:143589")
	public void courseEnded() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("endedCoId");
				Boolean enrollCustomerAsStandby = true;
				
				Response response = myGets.getClassCoursePricing(aPIKey, companyId, clubId, c, courseId);
				JsonPath json = ReusableMethods.rawToJson(response);
				double grandTotal = json.getDouble("Result.GrandTotal");

				given()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/classcourse/enrollmemberincourseonaccount/"+customerId+"/"+courseId+"/"+grandTotal+"/"+enrollCustomerAsStandby+"/"+onlineEnrollment)
					.then()
//					.log().body()
					.assertThat().statusCode(400)
					.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentHasEnded"));
	}
}
