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

public class EnrollMemberInCourseWithPunchcard extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/*
	// !!! Disabled until an unenroll is created
	@Test (testName="Member Enrolled - Course Already Started",description="PBI:147820")
	public void memberEnrolledCourseStarted() {
		
				int customerId = 248;
				String courseBarcodeId = "alwaysAvailCo";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
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
	 
		@Test (testName="Member Enrolled - Course Already Not Started",description="PBI:147820")
	public void memberEnrolledCourseNotStarted() {
		
				int customerId = 248;
				String courseBarcodeId = "notStartedCo";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
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
		
	@Test (testName="Member Enrolled On Standby",description="PBI:147820")
	public void memberEnrolledOnStandby() {
		
				int customerId 			= 248;
				String courseBarcodeId 	= "standbyCo";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
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
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:147820")
	public void memberEnrolledFreeCourse() {
		
				int customerId = 248;
				String courseBarcodeId = "freeCo";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
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
	} */
	
	@Test (testName="Not Enough Punches",description="PBI:147820")
	public void notEnoughPunches() {
		
				int customerId = 247;
				String courseBarcodeId = "notStartedCo";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("NotEnoughPunches"));
		}
	
	@Test (testName="Punchcard Not Allowed",description="PBI:147820")
	public void punchcardNotAllowed() {
		
				int customerId = 248;
				String courseBarcodeId = "noPunchCo";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - Item 239 is not associated to a package item."))
						;
		}
	
	@Test (testName="Course Full - Standby Not Allowed",description="PBI:147820")
	public void standbyNotAllowed() {
		
				int customerId = 247;
				String courseBarcodeId = "standbyCo";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseBarcodeId+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"))
						;
		}
		
	}
