package resources;

import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.MemberPL;

public class myActions extends base {
	
	public static int bookappointmentbymember(String occurrence, String customerId, int bookId, int itemId) { 
		
			int appointmentId;
				
			Response res = given()
//			.log().all()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
				.when()
				.body("{" + 
						"\"AppointmentClubId\": 1,"+ 
						"\"ItemId\": "+itemId+","+ 
						"\"Occurrence\": \""+occurrence+"\","+
						"\"CustomerId\": "+customerId+","+ 
						"\"RequestedBooks\": ["+bookId+"],"+ 
						"\"UserDisplayedPrice\": 0.00"+
						"}")	
					.post("/api/v3/appointment/bookappointmentbymember")
					.then()
//					.log().body()
					.extract().response();
			
			JsonPath js = ReusableMethods.rawToJson(res);
			appointmentId = js.getInt("Result.AppointmentId");
			
			return appointmentId;
	}	
	
	public static String cancelappointmentbyemployee(int appointmentId) { 
		
		String result;

		Response res = given()
//				.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
				.then()
//				.log().body()
				.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		result = js.getString("Status");

		return result;
		
}

	public static String createMember(String aPIKey, String companyId, String clubId) {
		
				int nextMemberId = myGets.getNextMemberId(aPIKey, companyId, clubId);
		
				String HomeClubId = prop.getProperty("HomeClubId");
				String FirstName = prop.getProperty("FirstName");
				String LastName = prop.getProperty("LastName");
				String MembershipTypeId = prop.getProperty("MembershipTypeId");
				String Title = prop.getProperty("Title");
				String MiddleInitial = prop.getProperty("MiddleInitial");
				String Address1 = prop.getProperty("Address1");
				String Address2 = prop.getProperty("Address2");
				String City = prop.getProperty("City");
				String State = prop.getProperty("State");
				String PostalCode = prop.getProperty("PostalCode");
				String Gender = prop.getProperty("Gender");
				String DateOfBirth = prop.getProperty("DateOfBirth");
				String HomePhone = prop.getProperty("HomePhone");
				String OkToContactHomePhone = prop.getProperty("OkToContactHomePhone");
				String MobilePhone = prop.getProperty("MobilePhone");
				String OkToContactMobilePhone = prop.getProperty("OkToContactMobilePhone"); 
				String WorkPhone = prop.getProperty("WorkPhone");
				String OkToContactWorkPhone = prop.getProperty("OkToContactWorkPhone");
				String PreferredPhoneType = prop.getProperty("PreferredPhoneType");
				String EmailAddress = prop.getProperty("EmailAddress");
				String OkToContactEmailAddress =prop.getProperty("OkToContactEmailAddress");
				String DoNotMail = prop.getProperty("DoNotMail");
				String DoNotMarket = prop.getProperty("DoNotMarket");
				String SocialSecurityNumber = prop.getProperty("SocialSecurityNumber");
				String DriverLicense = prop.getProperty("DriverLicense");
				String Occupation = prop.getProperty("Occupation");
				String Employer = prop.getProperty("Employer");
				String HeadOfHousehold = prop.getProperty("HeadOfHousehold");
				String IncomeChoiceId = prop.getProperty("IncomeChoiceId");
				String PriorityId = prop.getProperty("PriorityId");
		
		Response res = 
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
//				.log().body()
				.extract().response();
	
				JsonPath js2 = ReusableMethods.rawToJson(res);
				String customerId = js2.getString("CustomerId");
				System.out.println();
				return customerId;
	}
	
}