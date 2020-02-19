package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

public class EnrollMemberInCourseWithCardOnFile extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test (testName="Member Enrolled - Paid Course",description="PBI:146578")
	public void memberEnrolled_PaidCourse() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

			Response res =	given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
								"  \"AccountId\": \""+accountId+"\"," + 
								"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
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
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
//							ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
						}		
	}
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:146578")
	public void memberEnrolled_FreeCourse() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("freeCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("freeCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
//							ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
						}	
	}
	
	@Test (testName="Member Enrolled - Free Course - Collections Member",description="PBI:146578")
	public void memberEnrolledFreeCourseCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("freeCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("freeCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
//							ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
						}	
	}

	@Test (testName="Member Enrolled On Standby",description="PBI:146578")
	public void memberEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
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
//							ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
						}			
	} 
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:146578")
	public void memberNotEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}

	@Test (testName="Member Already Enrolled",description="PBI:146578")
	public void memberAlreadyEnrolled() {
		
				String c = prop.getProperty("standbyAId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:146578")
	public void memberAlreadyOnStandby() {
		
				String c = prop.getProperty("standbyBId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Course Not Available Online",description="PBI:146578")
	public void courseNotAvailableOnline() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("noWebCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("noWebCoPrice");
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat()
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"))
						.statusCode(400);
	}
	
	@Test (testName="Course Ended",description="PBI:146578")
	public void courseEnded() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("endedCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("endedCoPrice");
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:146578")
	public void customerNotFound() {
		
				int customerId = 245000;
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Course Not Found",description="PBI:146578")
	public void courseNotFound() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("NOTalwaysAvailCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:146578")
	public void productPriceChanged() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
				String displayedGrandTotal 		= "10.01";
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:146578")
	public void schedulingConflict() {
		
				String c = prop.getProperty("standbyCId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
	@Test (testName="No FOP - Account Problem",description="PBI:146578")
	public void noFOP_AccountProblem() {
		
		String c = prop.getProperty("noFOPId");
		int customerId = Integer.parseInt(c);
		String courseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
		String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				int accountId = 1;
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						// this returns "Sequence contains no elements" because there is no card on file
						.assertThat().statusCode(500)
						.body("Message", equalTo("Internal server error - Sequence contains no elements"));
	}
	
}
