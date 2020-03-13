package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class CreateMember extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Created",description="PBI:147807")
	public void memberCreated() { 

		Response res1 = given()

			.header("accept", "application/json")
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/member/getnextmemberid")
					.then()
//					.log().all()
					.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res1);
				int nextMemberId = js.getInt("Result.BarcodeId");
				
		Response res2	=	given()

				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{\r\n" + 
							"  \"MemberId\": \""+nextMemberId+"\",\r\n" + 
							"  \"HomeClubId\": 1,\r\n" + 
							"  \"FirstName\": \"Auto\",\r\n" + 
							"  \"LastName\": \"Generated\",\r\n" + 
							"  \"MembershipTypeId\": 2,\r\n" + 
							"  \"Title\": \"Mr\",\r\n" + 
							"  \"MiddleInitial\": \"\",\r\n" + 
							"  \"Address1\": \"1500 Main St.\",\r\n" + 
							"  \"Address2\": \"Apt B\",\r\n" + 
							"  \"City\": \"Hometown\",\r\n" + 
							"  \"State\": \"OH\",\r\n" + 
							"  \"PostalCode\": \"43215\",\r\n" + 
							"  \"Gender\": \"M\",\r\n" + 
							"  \"DateOfBirth\": \"1980-01-01\",\r\n" + 
							"  \"HomePhone\": \"6142009000\",\r\n" + 
							"  \"OkToContactHomePhone\": true,\r\n" + 
							"  \"MobilePhone\": \"6141009000\",\r\n" + 
							"  \"OkToContactMobilePhone\": true,\r\n" + 
							"  \"WorkPhone\": \"6143009000\",\r\n" + 
							"  \"OkToContactWorkPhone\": true,\r\n" + 
							"  \"PreferredPhoneType\": \"mobile\",\r\n" + 
							"  \"EmailAddress\": \"compete.test@jonasfitness.com\",\r\n" + 
							"  \"OkToContactEmailAddress\": true,\r\n" + 
							"  \"DoNotMail\": true,\r\n" + 
							"  \"DoNotMarket\": true,\r\n" + 
							"  \"SocialSecurityNumber\": \"000-00-0000\",\r\n" + 
							"  \"DriverLicense\": \"OH1235467\",\r\n" + 
							"  \"Occupation\": \"\",\r\n" + 
							"  \"Employer\": \"\",\r\n" + 
							"  \"HeadOfHousehold\": true,\r\n" + 
							"  \"IncomeChoiceId\": 0,\r\n" + 
							"  \"PriorityId\": 1,\r\n" + 
							"}")
						.post("/api/v3/member/createmember")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Successful", equalTo(true))
						.body("CustomerId", not(nullValue()))
						.body("MemberId", not(nullValue()))
//						.body("Messages", contains("Added customer record: Id = "))
						.extract().response();
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
						int memberId = js2.getInt("MemberId");
		
		Response res3 = given()
				// Get next member ID again
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getnextmemberid")
						.then()
						.extract().response();
			// next, confirm member ID is incremented by 1
			JsonPath js3 = ReusableMethods.rawToJson(res3);
				int nextMemberId2 = js3.getInt("Result.BarcodeId");
				int memberId2 = (memberId + 1);
				Assert.assertEquals(nextMemberId2, memberId2);
		
	}
}
