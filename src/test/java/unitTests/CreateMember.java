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
import resources.myGets;

public class CreateMember extends base {
	
			static String aPIKey;
			static String companyId;
			static String clubId;
			
			static int nextMemberId;
			static String HomeClubId = prop.getProperty("HomeClubId");
			static String FirstName = prop.getProperty("FirstName");
			static String LastName = prop.getProperty("LastName");
			static String MembershipTypeId = prop.getProperty("MembershipTypeId");
			static String Title = prop.getProperty("Title");
			static String MiddleInitial = prop.getProperty("MiddleInitial");
			static String Address1 = prop.getProperty("Address1");
			static String Address2 = prop.getProperty("Address2");
			static String City = prop.getProperty("City");
			static String State = prop.getProperty("State");
			static String PostalCode = prop.getProperty("PostalCode");
			static String Gender = prop.getProperty("Gender");
			static String DateOfBirth = prop.getProperty("DateOfBirth");
			static String HomePhone = prop.getProperty("HomePhone");
			static String OkToContactHomePhone = prop.getProperty("OkToContactHomePhone");
			static String MobilePhone = prop.getProperty("MobilePhone");
			static String OkToContactMobilePhone = prop.getProperty("OkToContactMobilePhone"); 
			static String WorkPhone = prop.getProperty("WorkPhone");
			static String OkToContactWorkPhone = prop.getProperty("OkToContactWorkPhone");
			static String PreferredPhoneType = prop.getProperty("PreferredPhoneType");
			static String EmailAddress = prop.getProperty("EmailAddress");
			static String OkToContactEmailAddress =prop.getProperty("OkToContactEmailAddress");
			static String DoNotMail = prop.getProperty("DoNotMail");
			static String DoNotMarket = prop.getProperty("DoNotMarket");
			static String SocialSecurityNumber = prop.getProperty("SocialSecurityNumber");
			static String DriverLicense = prop.getProperty("DriverLicense");
			static String Occupation = prop.getProperty("Occupation");
			static String Employer = prop.getProperty("Employer");
			static String HeadOfHousehold = prop.getProperty("HeadOfHousehold");
			static String IncomeChoiceId = prop.getProperty("IncomeChoiceId");
			static String PriorityId = prop.getProperty("PriorityId");
	
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
			
			nextMemberId = myGets.getNextMemberId(aPIKey, companyId, clubId);
				
		Response res2	=	
				given()
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.body(MemberPL.createMember(nextMemberId, HomeClubId, FirstName, LastName, MembershipTypeId, Title, MiddleInitial, Address1, Address2, City, State, PostalCode, Gender, DateOfBirth, HomePhone, OkToContactHomePhone, MobilePhone, OkToContactMobilePhone, WorkPhone, OkToContactWorkPhone, PreferredPhoneType, EmailAddress, OkToContactEmailAddress, DoNotMail, DoNotMarket, SocialSecurityNumber, DriverLicense, Occupation, Employer, HeadOfHousehold, IncomeChoiceId, PriorityId))
					.post("/api/v3/member/createmember")
				.then()
//						.log().body()
					.assertThat().statusCode(200)
					.time(lessThan(60L),TimeUnit.SECONDS)
					.body("Successful", equalTo(true))
					.body("CustomerId", not(nullValue()))
					.body("MemberId", not(nullValue()))
					.extract().response();
		
					// next, confirm member ID is incremented by 1
					JsonPath js2 = ReusableMethods.rawToJson(res2);
					int memberId = js2.getInt("MemberId");
					
					int nextMemberId2 = myGets.getNextMemberId(aPIKey, companyId, clubId);
					
					int memberId2 = (memberId + 1);
					Assert.assertEquals(nextMemberId2, memberId2);
				
	}
}
