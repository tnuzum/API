package payloads;

import resources.base;

public class ClassCoursePL extends base {
	
	public static String EnrollMemberInClassWithNewCreditCard
			(
			int customerId,
			String classId,
			String classOccurrence,
			String displayedGrandTotal,
			String cardNumber,
			String nameOnCard,
			String month,
			String year,
			String securityCode,
			String addressLine1,
			String city,
			String state,
			String postalCode,
			Boolean enrollCustomerAsStandby,
			Boolean onlineEnrollment
			) {
		
		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+classId+"\"," + 
				"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"CardNumber\": \""+cardNumber+"\"," + 
				"  \"NameOnCard\": \""+nameOnCard+"\"," + 
				"  \"ExpirationDate\": {" + 
				"    \"Month\": \""+month+"\"," + 
				"    \"Year\": "+year+"" + 
				"  }," + 
				"  \"SecurityCode\": \""+securityCode+"\"," + 
				"  \"AddressLine1\": \""+addressLine1+"\"," + 
				"  \"City\": \""+city+"\"," + 
				"  \"StateProvince\": \""+state+"\"," + 
				"  \"PostalCode\": \""+postalCode+"\"," + 
				"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
				"  \"onlineEnrollment\": \""+onlineEnrollment+"\""+
				"}";
		return payload;	
	}
	
	public static String EnrollMemberInCourseWithNewCreditCard
			(
			int customerId,
			String courseId,
			String displayedGrandTotal,
			String cardNumber,
			String nameOnCard,
			String month,
			String year,
			String securityCode,
			String addressLine1,
			String city,
			String state,
			String postalCode,
			Boolean enrollCustomerAsStandby,
			Boolean onlineEnrollment
			) {
		
		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+courseId+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"CardNumber\": \""+cardNumber+"\"," + 
				"  \"NameOnCard\": \""+nameOnCard+"\"," + 
				"  \"ExpirationDate\": {" + 
				"    \"Month\": \""+month+"\"," + 
				"    \"Year\": "+year+"" + 
				"  }," + 
				"  \"SecurityCode\": \""+securityCode+"\"," + 
				"  \"AddressLine1\": \""+addressLine1+"\"," + 
				"  \"City\": \""+city+"\"," + 
				"  \"StateProvince\": \""+state+"\"," + 
				"  \"PostalCode\": \""+postalCode+"\"," + 
				"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
				"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
				"}";
		
		return payload;
	}

	public static String EnrollMemberInClassWithCardOnFile
			(
			int customerId,
			String classId,
			String classOccurrence,
			String displayedGrandTotal,
			int accountId,
			Boolean enrollCustomerAsStandby,
			Boolean onlineEnrollment
			) {

		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+classId+"\"," + 
				"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"AccountId\": \""+accountId+"\"," + 
				"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
				"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+
				"}";
		return payload;	
	}
	
	public static String EnrollMemberInCourseWithCardOnFile
			(
			int customerId,
			String courseId,
			String displayedGrandTotal,
			int accountId,
			Boolean enrollCustomerAsStandby,
			Boolean onlineEnrollment
			) {

		String payload = "{" + 
			"  \"CustomerId\": "+customerId+"," + 
			"  \"ItemId\": \""+courseId+"\"," +
			"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
			"  \"AccountId\": \""+accountId+"\"," + 
			"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
			"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+
			"}";
		return payload;	
	}
}
