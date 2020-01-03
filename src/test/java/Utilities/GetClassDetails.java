package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class GetClassDetails extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="Class Found - Online Sale Allowed",description="PBI:143544")
	public void classFoundOnlineSaleAllowed() {
 
		int customerId 			= 231;
		String ClassBarcodeId 	= "alwaysAvail";
		String ClassDateTime 	= "2022-12-13";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
						.log().body();
	}
}