package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class EnrollMemberInCourseWithPunchcard extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Enrolled",description="PBI:147820")
	public void memberEnrolled() {
		
				int customerId = 248;
				String courseId = "alwaysAvailCo";
				Boolean enrollCustomerAsStandby = true;

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberincoursewithpunchcard/"+customerId+"/"+courseId+"/"+enrollCustomerAsStandby+"")
						.then()
						.log().body();
	}
	 
}
