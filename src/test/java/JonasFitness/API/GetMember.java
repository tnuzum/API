package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetMember extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test
	public void Test1() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
				
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
					    .body("Result", hasKey("Address"))
					    .body("Result.Address", hasKey("AddressLine1"))
					    .body("Result.Address", hasKey("AddressLine2"))
					    .body("Result.Address", hasKey("City"))
					    .body("Result.Address", hasKey("Country"))
					    .body("Result.Address", hasKey("PostalCode"))
					    .body("Result.Address", hasKey("StateProvince"))
					    .body("Result", hasKey("DateOfBirth"))
					    .body("Result", hasKey("DriversLicenseNumber"))
					    .body("Result", hasKey("EmailAddress"))
					    .body("Result", hasKey("EmailContactConsent"))
					    .body("Result", hasKey("EmergencyContactName"))
					    .body("Result", hasKey("EmergencyContactPhoneNumber"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Extension"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("Number"))
					    .body("Result.EmergencyContactPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("HeadOfHousehold"))
					    .body("Result", hasKey("HomeClubNumber"))
					    .body("Result", hasKey("HomePhoneContactConsent"))
					    .body("Result", hasKey("HomePhoneNumber"))
					    .body("Result.HomePhoneNumber", hasKey("Extension"))
					    .body("Result.HomePhoneNumber", hasKey("Number"))
					    .body("Result.HomePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Interests"))
					    .body("Result", hasKey("MemberExpireDate"))
					    .body("Result", hasKey("MemberID"))
					    .body("Result", hasKey("MobilePhoneContactConsent"))
					    .body("Result", hasKey("MobilePhoneNumber"))
					    .body("Result.MobilePhoneNumber", hasKey("Extension"))
					    .body("Result.MobilePhoneNumber", hasKey("Number"))
					    .body("Result.MobilePhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("Name"))
					    .body("Result.Name", hasKey("DisplayName"))
					    .body("Result.Name", hasKey("FirstName"))
					    .body("Result.Name", hasKey("LastName"))
					    .body("Result.Name", hasKey("MiddleInitial"))
					    .body("Result.Name", hasKey("PreferredName"))
					    .body("Result", hasKey("PreferredPhoneNumber"))
					    .body("Result.PreferredPhoneNumber", hasKey("Extension"))
					    .body("Result.PreferredPhoneNumber", hasKey("Number"))
					    .body("Result.PreferredPhoneNumber", hasKey("PhoneType"))
					    .body("Result", hasKey("PreferredPhoneType"))
					    .body("Result", hasKey("RestrictMemberFromSearch"))
					    .body("Result", hasKey("ValidBarcode"))
					    .body("Result", hasKey("WorkPhoneContactConsent"))
					    .body("Result", hasKey("WorkPhoneNumber"))
					    .body("Result.WorkPhoneNumber", hasKey("Extension"))
					    .body("Result.WorkPhoneNumber", hasKey("Number"))
					    .body("Result.WorkPhoneNumber", hasKey("PhoneType"));
//					** Assert values **					
//					.body("Result.Address.AddressLine1", equalTo("7965 N High St"))
//					.body("Result.Address.AddressLine2", equalTo("Ste 360"))
	}
}
