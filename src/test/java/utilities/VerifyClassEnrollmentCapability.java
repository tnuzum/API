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
		String customerId = "248"; // always available
		String classId = "220"; // StandByOnly-Class
		String classOccurrence = "2020-03-05T01:00:00";
		String displayedGrandTotal = "150.00";

				given()
				.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
//						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classId+"/"+classOccurrence+"/"+displayedGrandTotal)
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/236/1/248/220/2025-12-31T08:00:00/150.00")
					.then().log().body();
	}
}
