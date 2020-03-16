package payloads;

import resources.base;

public class MemberPL extends base {
	
	public static String AuthenticateMemberByUserCredentials
		(
		String username,
		String password
		) {
	
	String payload = "{"+
			  "\"Username\": \""+username+"\","+
			  "\"Password\": \""+password+"\""+
			"}";
	return payload;	
	}
	
	public static String CreateMember
		(
		int nextMemberId,
		int HomeClubId,
		String FirstName,
		String LastName,
		int MembershipTypeId,
		String Title,
		String MiddleInitial,
		String Address1,
		String Address2,
		String City,
		String State,
		String PostalCode,
		String Gender,
		String DateOfBirth,
		String HomePhone,
		Boolean OkToContactHomePhone,
		String MobilePhone,
		Boolean OkToContactMobilePhone,
		String WorkPhone,
		Boolean OkToContactWorkPhone,
		String PreferredPhoneType,
		String EmailAddress,
		Boolean OkToContactEmailAddress,
		Boolean DoNotMail,
		Boolean DoNotMarket,
		String SocialSecurityNumber,
		String DriverLicense,
		String Occupation,
		String Employer,
		Boolean HeadOfHousehold,
		int IncomeChoiceId,
		int PriorityId
		) {
	
	String payload = "{\r\n" + 
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
			"}";
	return payload;	
	}
	
}
