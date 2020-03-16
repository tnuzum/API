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
import payloads.MemberPL;
import resources.ReusableMethods;
import resources.base;

public class CreateMember extends base {
	
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
	
	@Test (testName="Member Created",description="PBI:147807")
	public void memberCreated() { 
		
			int HomeClubId = 1;
			String FirstName = "Auto";
			String LastName = "Generated";
			int MembershipTypeId = 2;
			String Title = "Mr";
			String MiddleInitial = "Z";
			String Address1 = "1500 Main St.";
			String Address2 = "Apt B";
			String City = "Hometown";
			String State = "OH";
			String PostalCode = "43215";
			String Gender = "M";
			String DateOfBirth = "1980-01-01";
			String HomePhone = "6142009000";
			Boolean OkToContactHomePhone = true; 
			String MobilePhone = "6141009000";
			Boolean OkToContactMobilePhone = true; 
			String WorkPhone = "6143009000";
			Boolean OkToContactWorkPhone = true; 
			String PreferredPhoneType = "mobile";
			String EmailAddress = "compete.test@jonasfitness.com";
			Boolean OkToContactEmailAddress = true;
			Boolean DoNotMail = true;
			Boolean DoNotMarket = true;
			String SocialSecurityNumber = "000-00-0000";
			String DriverLicense = "OH1235467";
			String Occupation = "";
			String Employer = "";
			Boolean HeadOfHousehold = true;
			int IncomeChoiceId = 0;
			int PriorityId = 1;

		Response res1 = 
				
				given()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/member/getnextmemberid")
				.then()
//					.log().all()
					.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res1);
				int nextMemberId = js.getInt("Result.BarcodeId");
				
		Response res2	=	
				given()
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.body(MemberPL.CreateMember(nextMemberId, HomeClubId, FirstName, LastName, MembershipTypeId, Title, MiddleInitial, Address1, Address2, City, State, PostalCode, Gender, DateOfBirth, HomePhone, OkToContactHomePhone, MobilePhone, OkToContactMobilePhone, WorkPhone, OkToContactWorkPhone, PreferredPhoneType, EmailAddress, OkToContactEmailAddress, DoNotMail, DoNotMarket, SocialSecurityNumber, DriverLicense, Occupation, Employer, HeadOfHousehold, IncomeChoiceId, PriorityId))
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
		
		Response res3 = 
				given()
					// Get next member ID again
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
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
