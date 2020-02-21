package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;
import resources.myGets;

public class CancelAllAppointments extends base {
	
	static String customerId;
	static int appointmentId;
	public static String sDateTimeNoOffset = ReusableDates.getCurrentDate();
	
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

	@Test (priority = 3, dataProvider="getDataProvider")
	public void findAppointments(String customerId) {
		
			try {
				appointmentId = myGets.getAppointmentsByMember(customerId);
			} catch (Exception e) {
//				e.printStackTrace();
				return;
			}
				
	}
	
	@Test (testName="ApptCancelled",description="PBI:141862")
	public void ApptCancelled() { 
		
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
						.then()
						.log().body();
	}
	
	@Test (testName="ApptCheck",description="PBI:141862")
	public void ApptCheck() { 
		
		try {
			System.out.println(appointmentId);
			if (appointmentId >= 0) {
				findAppointments(customerId);
				ApptCancelled();
			}
			System.out.println("No Appointments Found");
		} catch (Exception e) {
//			e.printStackTrace();
			return;
		}
	}
	
}
