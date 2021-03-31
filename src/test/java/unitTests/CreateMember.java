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
			
			int nextMemberId;
			static String HomeClubId;
			static String FirstName;
			static String LastName;
			static String MembershipTypeId;
			static String Title;
			static String MiddleInitial;
			static String Address1;
			static String Address2;
			static String City;
			static String State;
			static String PostalCode;
			static String Gender;
			static String DateOfBirth;
			static String HomePhone;
			static String OkToContactHomePhone;
			static String MobilePhone;
			static String OkToContactMobilePhone;
			static String WorkPhone;
			static String OkToContactWorkPhone;
			static String PreferredPhoneType;
			static String EmailAddress;
			static String OkToContactEmailAddress;
			static String DoNotMail;
			static String DoNotMarket;
			static String SocialSecurityNumber;
			static String DriverLicense;
			static String Occupation;
			static String Employer;
			static String HeadOfHousehold;
			static String IncomeChoiceId;
			static String PriorityId;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");

		HomeClubId = prop.getProperty("HomeClubId");
		FirstName = prop.getProperty("FirstName");
		LastName = prop.getProperty("LastName");
		MembershipTypeId = prop.getProperty("MembershipTypeId");
		Title = prop.getProperty("Title");
		MiddleInitial = prop.getProperty("MiddleInitial");
		Address1 = prop.getProperty("Address1");
		Address2 = prop.getProperty("Address2");
		City = prop.getProperty("City");
		State = prop.getProperty("State");
		PostalCode = prop.getProperty("PostalCode");
		Gender = prop.getProperty("Gender");
		DateOfBirth = prop.getProperty("DateOfBirth");
		HomePhone = prop.getProperty("HomePhone");
		OkToContactHomePhone = prop.getProperty("OkToContactHomePhone");
		MobilePhone = prop.getProperty("MobilePhone");
		OkToContactMobilePhone = prop.getProperty("OkToContactMobilePhone"); 
		WorkPhone = prop.getProperty("WorkPhone");
		OkToContactWorkPhone = prop.getProperty("OkToContactWorkPhone");
		PreferredPhoneType = prop.getProperty("PreferredPhoneType");
		EmailAddress = prop.getProperty("EmailAddress");
		OkToContactEmailAddress =prop.getProperty("OkToContactEmailAddress");
		DoNotMail = prop.getProperty("DoNotMail");
		DoNotMarket = prop.getProperty("DoNotMarket");
		SocialSecurityNumber = prop.getProperty("SocialSecurityNumber");
		DriverLicense = prop.getProperty("DriverLicense");
		Occupation = prop.getProperty("Occupation");
		Employer = prop.getProperty("Employer");
		HeadOfHousehold = prop.getProperty("HeadOfHousehold");
		IncomeChoiceId = prop.getProperty("IncomeChoiceId");
		PriorityId = prop.getProperty("PriorityId");

	}
	
	@Test (testName="Member Created",description="PBI:147807")
	public void memberCreated() { 
			
			nextMemberId = myGets.getNextMemberId(aPIKey, companyId, clubId);

			System.out.println("TestObject: "+FirstName);
				
		Response res2	=	
				given()
//					.log().all()
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
