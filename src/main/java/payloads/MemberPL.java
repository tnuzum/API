package payloads;

import resources.base;

public class MemberPL extends base {
	
	public static String authenticateMemberByUserCredentials
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
	
	public static String createMember
		(
		int nextMemberId,
		String HomeClubId,
		String FirstName,
		String LastName,
		String MembershipTypeId,
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
		String OkToContactHomePhone,
		String MobilePhone,
		String OkToContactMobilePhone,
		String WorkPhone,
		String OkToContactWorkPhone,
		String PreferredPhoneType,
		String EmailAddress,
		String OkToContactEmailAddress,
		String DoNotMail,
		String DoNotMarket,
		String SocialSecurityNumber,
		String DriverLicense,
		String Occupation,
		String Employer,
		String HeadOfHousehold,
		String IncomeChoiceId,
		String PriorityId
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
	
	public static String modifyCardOnFileByMemberAllFields(
			String accountId,
			String customerId,
			String cardNumber,
			String expirationMonth,
			String expirationYear,
			String cardHolderName,
			String addressIsSameAsMemberAddress,
			String addressLine1,
			String addressLine2,
			String city,
			String stateProvince,
			String postalCode,
			String useInPos,
			String setAsHouseAccount
			) {
		
		String payload = "	{\r\n" + 
				"  \"AccountId\": \""+accountId+"\",\r\n" + 
				"  \"CustomerId\": \""+customerId+"\",\r\n" + 
				"  \"CardNumber\": \""+cardNumber+"\",\r\n" + 
				"  \"ExpirationDate\": {\r\n" + 
				"    \"Month\": "+expirationMonth+",\r\n" + 
				"    \"Year\": "+expirationYear+"\r\n" + 
				"  },\r\n" + 
				"  \"CardHolderName\": \""+cardHolderName+"\",\r\n" + 
				"  \"AddressIsSameAsMemberAddress\": "+addressIsSameAsMemberAddress+",\r\n" + 
				"  \"Address\": {\r\n" + 
				"    \"AddressLine1\": \""+addressLine1+"\",\r\n" + 
				"    \"AddressLine2\": \""+addressLine2+"\",\r\n" + 
				"    \"City\": \""+city+"\",\r\n" + 
				"    \"StateProvince\": \""+stateProvince+"\",\r\n" + 
				"    \"PostalCode\": \""+postalCode+"\",\r\n" + 
				"    \"Country\": \"null\"\r\n" + 
				"  },\r\n" + 
				"  \"UseInPos\": "+useInPos+",\r\n" + 
				"  \"SetAsHouseAccount\": "+setAsHouseAccount+"\r\n" + 
				"}";
		
		return payload;	
	}
	
	public static String modifyCardOnFileByMemberCardNumberOnly(
			String accountId,
			String customerId,
			String cardNumber,
			String addressIsSameAsMemberAddress
			) {
		
		String payload = "	{\r\n" + 
				"  \"AccountId\": "+accountId+",\r\n" + 
				"  \"CustomerId\": "+customerId+",\r\n" + 
				"  \"CardNumber\": \""+cardNumber+"\",\r\n" + 
				"  \"AddressIsSameAsMemberAddress\": "+addressIsSameAsMemberAddress+"\r\n" + 
				"}";
		
		return payload;	
	}
	
	public static String addCardOnFileByMemberCardNumberOnly(
			String updateActiveAgreements,
			String customerId,
			String cardNumber,
			String expirationMonth,
			String expirationYear,
			String cardHolderName,
			String addressIsSameAsMemberAddress,
			String addressLine1,
			String addressLine2,
			String city,
			String stateProvince,
			String postalCode,
			String useInPos,
			String setAsHouseAccount
			) {
		
		String payload = "{\r\n" + 
				"  \"UpdateActiveAgreements\": \""+updateActiveAgreements+"\",\r\n" + 
				"  \"CustomerId\": \""+customerId+"\",\r\n" + 
				"  \"CardNumber\": \""+cardNumber+"\",\r\n" + 
				"  \"ExpirationDate\": {\r\n" + 
				"    \"Month\": \""+expirationMonth+"\"," + 
				"    \"Year\": "+expirationYear+"\r\n" + 
				"  },\r\n" + 
				"  \"CardHolderName\": \""+cardHolderName+"\",\r\n" + 
				"  \"AddressIsSameAsMemberAddress\": "+addressIsSameAsMemberAddress+",\r\n" + 
				"  \"Address\": {\r\n" + 
				"    \"AddressLine1\": \""+addressLine1+"\",\r\n" + 
				"    \"AddressLine2\": \""+addressLine2+"\",\r\n" + 
				"    \"City\": \""+city+"\",\r\n" + 
				"    \"StateProvince\": \""+stateProvince+"\",\r\n" + 
				"    \"PostalCode\": \""+postalCode+"\",\r\n" + 
				"    \"Country\": \"null\"\r\n" + 
				"  },\r\n" + 
				"  \"UseInPos\": "+useInPos+",\r\n" + 
				"  \"SetAsHouseAccount\": "+setAsHouseAccount+"\r\n" + 
				"}";
		
		return payload;	
	}
	
}
