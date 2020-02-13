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

public class EnrollMemberInCourseWithRecurringDues extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Enrolled - Paid Course",description="PBI:154260")
	public void memberEnrolledPaidCourse() { 
		
			String c = prop.getProperty("availableId");
			int customerId = Integer.parseInt(c);
			String companyId = prop.getProperty("X-CompanyId");
			String courseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
			String enrollCustomerAsStandBy = "true";

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:154260")
	public void memberEnrolledFreeCourse() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("freeCoBarcodeId");
				String enrollCustomerAsStandBy = "true";

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled - Free Course - Collections Member",description="PBI:154260")
	public void memberEnrolledFreeCourseCollectionsMember() { 
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("freeCoBarcodeId");
				String enrollCustomerAsStandBy = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:154260")
	public void memberEnrolledOnStandby() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String enrollCustomerAsStandBy = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:154260")
	public void memberAlreadyEnrolled() { 
		
		String c = prop.getProperty("standbyAId");
		int customerId = Integer.parseInt(c);
		String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
		String enrollCustomerAsStandBy = "true";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Member Already Enrolled On Standby",description="PBI:154260")
	public void memberAlreadyEnrolledOnStandby() { 
		
				String c = prop.getProperty("standbyBId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String enrollCustomerAsStandBy = "true";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Enrolled Not On Standby",description="PBI:154260")
	public void memberEnrolledNotOnStandby() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("standbyCoBarcodeId");
				String enrollCustomerAsStandBy = "false";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	} 
	
	@Test (testName="Course Not Available Online",description="PBI:154260")
	public void courseNotAvailableOnline() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("noWebCoBarcodeId");
				String enrollCustomerAsStandBy = "false";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	} 
	
	@Test (testName="Course Ended",description="PBI:154260")
	public void courseEnded() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("endedCoBarcodeId");
				String enrollCustomerAsStandBy = "false";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:154260")
	public void customerNotFound() { 
		
				int customerId = 248000;
				String courseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
				String enrollCustomerAsStandBy = "false";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Course Not Found",description="PBI:154260")
	public void courseNotFound() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("NOTalwaysAvailCoBarcodeId");
				String enrollCustomerAsStandBy = "false";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Recurring Dues Not Accepted",description="PBI:154260")
	public void recurringDuesNotAccepted() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseBarcodeId = prop.getProperty("noPunchCoBarcodeId");
				String enrollCustomerAsStandBy = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithrecurringdues/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Course or customer configuration does not allow recurring dues enrollment"));
	}

	
}
