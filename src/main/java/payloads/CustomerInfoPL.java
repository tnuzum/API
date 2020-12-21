package payloads;

import resources.base;

public class CustomerInfoPL extends base {
	
	public static String GetMemberActions()
		{
		String payload = "";
				
				return payload;
		}
	
	public static String CreateMemberNotes(
			
			String customerId,
			String clubId,
			String employeeId,
			String actionId,
			String note,
			String alertOnCheckIn,
			String doNotDisplayInFrontDesk) {
		
		String payload = "{\r\n" + 
				"  \"CustomerId\": "+customerId+",\r\n" + 
				"  \"ClubId\": "+clubId+",\r\n" + 
				"  \"EmployeeId\": "+employeeId+",\r\n" + 
				"  \"ActionId\": "+actionId+",\r\n" + 
				"  \"Note\": \""+note+"\",\r\n" + 
				"  \"AlertOnCheckIn\": "+alertOnCheckIn+",\r\n" + 
				"  \"DoNotDisplayInFrontDesk\": "+doNotDisplayInFrontDesk+"\r\n" + 
				"}";
		
		return payload; 
	}
	
}
