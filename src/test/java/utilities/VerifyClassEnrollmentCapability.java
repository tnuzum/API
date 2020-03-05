package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class VerifyClassEnrollmentCapability extends base{
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test ()
	public void verifyClassEnrollmentCapability() {

		int companyId = 236;
		int clubId = 1;
		String customerId = "247"; // always available
		String classId = "284"; // StandByOnly-Class
		String classOccurrence = "2025-12-25T08:00:00";
		String displayedGrandTotal = "10.00";
		Boolean onlineEnrollment = false;

				given()
				.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+onlineEnrollment)
					.then().log().body();
	}
}
