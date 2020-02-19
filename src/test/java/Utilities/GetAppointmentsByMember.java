package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class GetAppointmentsByMember extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@DataProvider
	public Object[] getDataProvider(){
		
		Object[][] data = new Object[6][1];
		
				data[0][0]=prop.getProperty("availableId");
				data[1][0]=prop.getProperty("collectionsId");
				data[2][0]=prop.getProperty("noFOPId");
				data[3][0]=prop.getProperty("prospectId");
				data[4][0]=prop.getProperty("noWebId");
				data[5][0]=prop.getProperty("creditLimitId");
				return data;
	}
	
	@Test (testName="AppointmentsFound",description="PBI:124124",dataProvider="getDataProvider")
	public void AppointmentsFound(String customerId) {
		
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		
				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/2200-01-01")
						.then()
						.log().body();
	}
}
