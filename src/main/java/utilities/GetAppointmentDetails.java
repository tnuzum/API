package utilities;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentDetails extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
		
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="AppointmentsFound",description="PBI:139310")
	public void AppointmentsFound() {
		
		int appointment = 10;

			given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
						.log().body();
}
}
