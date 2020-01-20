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
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class EnrollMemberInClassWithRecurringDues extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/*
// !!! Disabled in TestNG.xml until an unenroll call is created
	@Test (testName="Member Enrolled",description="PBI:154259")
	public void memberEnrolled() { 
		
		int customerId = 248;
		String classBarcodeId = "alwaysAvailCl";
		String classOccurrence = "2021-01-01";
		String enrollCustomerAsStandBy = "true";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}
// !!! Disabled in TestNG.xml until an unenroll call is created
	@Test (testName="Member Enrolled On Standby",description="PBI:154259")
	public void memberEnrolledOnStandby() { 
		
		int customerId = 248;
		String classBarcodeId = "standbyCl";
		String classOccurrence = "2022-12-06";
		String enrollCustomerAsStandBy = "true";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.EnrollmentStatus", equalTo("StandBy"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	} */
	
	@Test (testName="Member Already Enrolled",description="PBI:154259")
	public void memberAlreadyEnrolled() { 
		
		int customerId = 245;
		String classBarcodeId = "standbyCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "true";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Member Already Enrolled On Standby",description="PBI:154259")
	public void memberAlreadyEnrolledOnStandby() { 
		
		int customerId = 246;
		String classBarcodeId = "standbyCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "true";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	
	@Test (testName="Member Enrolled Not On Standby",description="PBI:154259")
	public void memberEnrolledNotOnStandby() { 
		
		int customerId = 223;
		String classBarcodeId = "standbyCl";
		String classOccurrence = "2022-12-06";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	} 
	
	@Test (testName="Class Not Available Online",description="PBI:154259")
	public void classNotAvailableOnline() { 
		
		int customerId = 248;
		String classBarcodeId = "noWebCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	} 
	
	@Test (testName="Class Ended",description="PBI:154259")
	public void classEnded() { 
		
		int customerId = 248;
		String classBarcodeId = "endedCl";
		String classOccurrence = "2019-12-13";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	} 
	
	@Test (testName="Customer Not Found",description="PBI:154259")
	public void customerNotFound() { 
		
		int customerId = 248000;
		String classBarcodeId = "alwaysAvailCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerNotFound"));
	} 
	
	@Test (testName="Class Not Found",description="PBI:154259")
	public void classNotFound() { 
		
		int customerId = 248;
		String classBarcodeId = "NOTalwaysAvailCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	} 
	
	@Test (testName="Occurrence Not Found",description="PBI:154259")
	public void occurrenceNotFound() { 
		
		int customerId = 248;
		String classBarcodeId = "alwaysAvailCl";
		String classOccurrence = "2125-12-31";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	} 
	/*
	 * This is enrolling a member even though the class doesn't take Recurring dues

	@Test (testName="Recurring Dues Not Accepted",description="PBI:154259")
	public void recurringDuesNotAccepted() { 
		
		int customerId = 247;
		String classBarcodeId = "noPunchCl";
		String classOccurrence = "2025-12-31";
		String enrollCustomerAsStandBy = "false";
		

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithrecurringdues/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandBy)
						.then()
						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	} */

	
}
