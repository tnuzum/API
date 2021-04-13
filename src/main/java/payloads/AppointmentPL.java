package payloads;

import resources.base;

public class AppointmentPL extends base {
	
	public static String BookAppointment_SingleMember
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
	
	public static String BookAppointment_MultiMember
	(
			String appointmentClubId,
			String itemId,
			String occurrence,
			String customerId,
			String additionalCustomerIds,
			String requestedBooks,
			String userDisplayedPrice
			)
	{
	String payload = "{" + 
			"\"AppointmentClubId\": "+appointmentClubId+","+ 
			"\"ItemId\": "+itemId+","+ 
			"\"Occurrence\": \""+occurrence+"\","+
			"\"CustomerId\": "+customerId+","+ 
			"\"AdditionalCustomerIds\": ["+additionalCustomerIds+"],"+
			"\"RequestedBooks\": ["+requestedBooks+"],"+ 
			"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
			"}";
			
			return payload;
	}
	
	public static String BookAppointment_NoBook
	(
			String appointmentClubId,
			String itemId,
			String occurrence,
			String customerId,
			String userDisplayedPrice
			)
	{
	String payload = "{" + 
			"\"AppointmentClubId\": "+appointmentClubId+","+ 
			"\"ItemId\": "+itemId+","+ 
			"\"Occurrence\": \""+occurrence+"\","+
			"\"CustomerId\": "+customerId+","+ 
			"\"RequestedBooks\": [],"+ 
			"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
			"}";
			
			return payload;
	}
	
	public static String BookAppointment_MultiBook
	(
			String appointmentClubId,
			String itemId,
			String occurrence,
			String customerId,
			String requestedBook1,
			String requestedBook2,
			String userDisplayedPrice
			)
	{
	String payload = "{" + 
			"\"AppointmentClubId\": "+appointmentClubId+","+ 
			"\"ItemId\": "+itemId+","+ 
			"\"Occurrence\": \""+occurrence+"\","+
			"\"CustomerId\": "+customerId+","+ 
			"\"RequestedBooks\": ["+requestedBook1+","+requestedBook2+"],"+ 
			"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
			"}";
			
			return payload;
	}
}
