package resources;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Payload extends base {
		
	public static Response verifyClassEnrollmentCapability(String companyId, String clubId, String customerId, String classBarcodeId, String classOccurrence, String displayedGrandTotal)
	{
		
		// use this in methods with Payload.verifyClassEnrollmentCapability(companyId, clubId, customerId, classBarcodeId, classOccurrence, displayedGrandTotal)
		
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		Response res =	given()
		//		.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
				.then()
				.extract().response();
		
		return res;
	}
	
	public static JsonPath verifyClassEnrollmentCapability2(String companyId, String clubId, String customerId, String classBarcodeId, String classOccurrence, String displayedGrandTotal)
	{

		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		Response res =	given()
		//		.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/verifyclassenrollmentcapability/"+companyId+"/"+clubId+"/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal)
				.then()
				.log().all()
				.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		
		return js;
	}
	
	/*
	 Examples Only
	 
	 
	public static String getPostData()
	{
		String b = "{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}";
		return b;
	}
	public static String addbook(String isdn, String ailse)
	{
		String payload="{\r\n\r\n\"name\":\"Learn to Rule the World\",\r\n\"isbn\":\""+isdn+"\",\r\n\"aisle\":\""+ailse+"\",\r\n\"author\":\"Todd David\"\r\n}";
		return payload;
		
	}
	public static String getIssue()
	{
		String i = "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"RES\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"Feature not functioning as expected\",\r\n" + 
				"        \"description\": \"When I tried to use the feature the result is not what I expected\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        },\r\n" + 
				"        \"assignee\": {\r\n" + 
				"        	\"name\": \"toddnuzum\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
				return i;
	}
	public static String addComment()
	{
		String c = "{\r\n" + 
				"    \"body\": \"update to my earlier comment\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return c;
	}
	*/
}
