package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class VerifyClassEnrollmentCapability extends base{
	
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
	
	@Test (testName="Enrollment Not Allowed",description="PBI:150003")
	public void enrollmentNotAllowed() {
 
		String companyId 		= prop.getProperty("X-CompanyId");
		String clubId 			= prop.getProperty("X-Club1Id");
		int customerId 			= 223;
		String classBarcodeId 	= "BalanceItem";
		String classOccurrence 	= "2022-12-06";
		String displayedClassPrice	= "15.00";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice)
						.then()
						.log().body();
	}
}
