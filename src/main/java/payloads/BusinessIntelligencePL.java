package payloads;

import resources.base;

public class BusinessIntelligencePL extends base {
	
	public static String SetBusinessIntelligenceConfiguration_AllParameters( 
			String clubId, 
			String clubName, 
			String isSelected,
			String timeFrame, 
			String timeFrameUnits, 
			String isActivated) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				"        \"ClubId\": "+clubId+",\r\n" + 
				"        \"ClubName\": \""+clubName+"\",\r\n" + 
				"        \"IsSelected\": true\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" +
				"    \"DataStorageTimeframe\": "+timeFrame+",\r\n" + 
				"    \"DataStorageTimeframeUnits\": "+timeFrameUnits+",\r\n" + 
				"    \"IsActivated\": "+isActivated+"\r\n" + 
				"  }\r\n" + 
				"}";
		
		return pl;
	}
	
	public static String SetBusinessIntelligenceConfiguration_MultipleClubs(
			String clubId, 
			String clubName, 
			String clubIsSelected,
			String club2Id, 
			String club2Name, 
			String club2IsSelected,
			String timeFrame, 
			String timeFrameUnits, 
			String isActivated) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				"        \"ClubId\": "+clubId+",\r\n" + 
				"        \"ClubName\": \""+clubName+"\",\r\n" + 
				"        \"IsSelected\": "+clubIsSelected+"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ClubId\": "+club2Id+",\r\n" + 
				"        \"ClubName\": \""+club2Name+"\",\r\n" + 
				"        \"IsSelected\": "+club2IsSelected+"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" +
				"    \"IsActivated\": true\r\n" + 
				"  }\r\n" + 
				"}";
		return pl;
	}
	
	public static String SetBusinessIntelligenceConfiguration_IsActivated(String isActivated) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" +
				"    \"IsActivated\": "+isActivated+"\r\n" + 
				"  }\r\n" + 
				"}";
		return pl;
	}
	
	public static String SetBusinessIntelligenceConfiguration_TimeFrame(String timeFrame) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				" }\r\n" + 
				"    ],\r\n" + 
				"    \"DataStorageTimeframe\": "+timeFrame+"\r\n" + 
				"  }\r\n" + 
				"}";
		return pl;
	}
	
	public static String SetBusinessIntelligenceConfiguration_TimeFrameUnits(String timeFrameUnits) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				"}\r\n" + 
				"    ],\r\n" +
				"    \"DataStorageTimeframeUnits\": "+timeFrameUnits+"\r\n" + 
				"  }\r\n" + 
				"}";
		return pl;
	}
	
	public static String SetBusinessIntelligenceConfiguration_ClubIsSelected(String clubId, String clubIsSelected) {
		
		String pl = "{\r\n" + 
				"  \"BusinessIntelligenceConfigurationDto\": {\r\n" + 
				"    \"ClubSelection\": [\r\n" + 
				"      {\r\n" + 
				"        \"ClubId\": "+clubId+",\r\n" + 
				"        \"IsSelected\": "+clubIsSelected+"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" +
			    "	 \"IsActivated\": "+true+"\r\n" + //must be true for clubIsSelected to be set to true
				"  }\r\n" + 
				"}";
		return pl;
	}
	
	
}