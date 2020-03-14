package resources;

public class Payloads extends base {
	
	public static String BookAppointmentByEmployeePL
		(
				String appointmentClubId,
				String itemId,
				String occurrence,
				String customerId,
				String requestedBooks,
				String userDisplayedPrice
				)
		{
		String payload = "{" + 
				"\"AppointmentClubId\": "+appointmentClubId+","+ 
				"\"ItemId\": "+itemId+","+ 
				"\"Occurrence\": \""+occurrence+"\","+
				"\"CustomerId\": "+customerId+","+ 
				"\"RequestedBooks\": ["+requestedBooks+"],"+ 
				"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
				"}";
				
				return payload;
		}
	
	public static String EnrollMemberInClassWithNewCreditCardPL
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
	
	public static String EnrollMemberInCourseWithNewCreditCardPL
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
	
	public static String PurchasePackageWithNewCreditCardPL	
			(
			String customerId,
			String itemId,
			int quantity,
			Double displayedGrandTotal,
			String cardNumber,
			String nameOnCard,
			String month,
			String year,
			String securityCode,
			String addressLine1,
			String city,
			String state,
			String postalCode
			) {
		
		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+itemId+"\"," + 
				"  \"Quantity\": \""+quantity+"\"," + 
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
				"  \"PostalCode\": \""+postalCode+"\"" +
				"}";

		return payload;
	}
	
	public static String PurchasePackageWithCardOnFilePL	
			(
			String customerId,
			String itemId,
			int quantity,
			Double displayedGrandTotal,
			int account
			) {

		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+itemId+"\"," + 
				"  \"Quantity\": \""+quantity+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"Account\": \""+account+"\"" +
				"}";

		return payload;
}
	

}
