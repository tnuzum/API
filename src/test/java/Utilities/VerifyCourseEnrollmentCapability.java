package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class VerifyCourseEnrollmentCapability extends base{
	
	/*
	 * This call is not shown in Swagger
	 * because it's hidden from integrators
	 */

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Enrollment Capability Verified",description="PBI:150003")
	public void enrollmentCapabilityVerified() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		int customerId 			= 223;
		String courseBarcodeId 	= "standby002";
		String displayedCoursePrice	= "1500.00";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/"+companyId+"/"+customerId+"/"+courseBarcodeId+"/"+displayedCoursePrice)
						.then()
						.log().body();
	}
}
