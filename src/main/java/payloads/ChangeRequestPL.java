package payloads;

import resources.base; 

public class ChangeRequestPL extends base {
	
	public static String updatePersonalInformation
		(
				String familyMemberCustomerIds,
				String customerId,
				String fieldName,
				String newValue,
				String submissionReason,
				String submissionReasonDetail,
				String signature
				)
		{
		String payload = "{\r\n" + 
				"  \"FamilyMemberCustomerIds\": ["+familyMemberCustomerIds+"],\r\n" + 
				"  \"CustomerId\": \""+customerId+"\",\r\n" + 
				"  \"ChangeDetails\": [\r\n" + 
				"    {\r\n" + 
				"      \"FieldName\": \""+fieldName+"\",\r\n" + 
				"      \"NewValue\": \""+newValue+"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"SubmissionReason\": \""+submissionReason+"\",\r\n" + 
				"  \"SubmissionReasonDetail\": \""+submissionReasonDetail+"\",\r\n" + 
				"  \"Signature\": \""+signature+"\"\r\n" + 
				"}";
				
				return payload;
		}
	
}
