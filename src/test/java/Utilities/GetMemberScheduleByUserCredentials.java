package Utilities;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class GetMemberScheduleByUserCredentials extends base {
	
//	public static String username = "Betsy";
//	public static String password = "Abcd1234!";
	public static int companyId = 224;
	public static int clubId = 1;
	public static String username = "greg.manny";
	public static String password = "Working1!";
//	public static String username = "available";
//	public static String password = "Testing1!";
	static int customerId;
	public static String sDateTimeNoOffset = ReusableDates.getCurrentDate();

	@BeforeClass
	public void getData(){
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		System.out.println("[INFO] Test Environment: "+prop.getProperty("environment"));
	}

	@Test (priority = 1)
	public void findCustomerId() {
		
		customerId = ReusableMethods.getCustomerId(companyId, clubId, username, password);
		System.out.println("[INFO] CustomerId: "+customerId);

	}
	
	@Test (priority = 2)
	public void findClassesCourses() {
		
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/2200-01-01")
						.then()
						.log().body();

	}
	
	@Test (priority = 3)
	public void findAppointments() {
		
			String c = Integer.toString(customerId);
		
				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(c)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/2200-01-01")
						.then()
						.log().body();
	}
	
}
