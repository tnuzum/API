package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class VerifyCourseEnrollmentCapability extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test()
	public void verifyCourseEnrollmentCapability() {

		int companyId = 236;
		int clubId = 1;
		int customerId = 248;
		String courseId = "noPunchCo";
		String displayedGrandTotal = "150.00";

		given()	
//			.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/" + companyId + "/" + clubId + "/"
						+ customerId + "/" + courseId + "/" + displayedGrandTotal)
				.then().log().body();
	}
}
