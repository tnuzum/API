package Utilities;

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
		int customerId = 223;
		String classBarcodeId 	= "taxCityCl";
		String classOccurrence 	= "2022-12-06";
		String displayedGrandTotal	= "10.25";

				given()
				.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
						.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"
								+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
					.then().log().body();
	}
}
