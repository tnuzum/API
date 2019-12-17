package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class VerifyCourseEnrollmentCapability extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test()
	public void verifyCourseEnrollmentCapability() {

		int companyId = 236;
		int clubId = 1;
		int customerId = 223;
		String courseBarcodeId = "PBoot430";
		String displayedCoursePrice = "0.00";

		given()	
			.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/verifycourseenrollmentcapability/" + companyId + "/" + clubId + "/"
						+ customerId + "/" + courseBarcodeId + "/" + displayedCoursePrice)
				.then().log().body();
	}
}
