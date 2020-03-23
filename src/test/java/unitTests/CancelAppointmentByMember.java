package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class CancelAppointmentByMember extends base {
	
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
	
	/*
	 * See BookAppointmentByMember.FreeAppointment_SingleMember test case for the test that cancels
	 * an appointment by MEMBER. 
	*/
		
	@Test (testName="ApptNotFound",description="PBI:127168")
	public void ApptNotFound() { 
		
		String member = prop.getProperty("CardWithoutAgreementId");

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/cancelappointmentbymember/916375/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="NotCancelled_ApptDatePast",description="PBI:127168")
	public void NotCancelled_ApptDatePast() { 
		
				String member = "17";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/appointment/cancelappointmentbymember/10/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo("Failed"))
						.body("Result.ConfirmationCode", nullValue())
						.body("Result", hasKey("Reason"))
						.body("Result.Reason", equalTo("TimeRestriction"));
	}
	
	@Test (testName="NotCancelled_ApptDatePast",description="PBI:127168")
	public void ApptAlreadyCancelled() { 
		
		String member = prop.getProperty("availableId");

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/appointment/cancelappointmentbymember/24932/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo("Failed"))
						.body("Result.ConfirmationCode", nullValue())
						.body("Result", hasKey("Reason"))
						.body("Result.Reason", equalTo("AppointmentCancelled"));
	}

}
