package resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class myGets extends base {
	
	public static String getCustomerId(String userName, String password) {
		
		String customerId;

		Response res = given()
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
				.when()
					.body("{"+
							  "\"Username\": \""+userName+"\","+
							  "\"Password\": \""+password+"\","+
							"}")
					.post("/api/v3/member/authenticatememberbyusercredentials").
				then()
				.body("Result.AuthenticationResult", equalTo("Success"))
				.extract().response();	
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				customerId = js.getString("Result.CustomerId");
				
				return customerId;
}

	public static String getavailableappointments_Occurrence(String customerId, String sDateTimeNoOffset, String eDateTimeNoOffset, int itemId){
		
		String occurrence;
		
		Response res = given()
//					.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
//							.queryParam("ResourceId", resourceId)
						.when()
						.get("/api/v3/appointment/getavailableappointments/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+itemId)
							.then()
//							.log().body()
							.extract().response();
					
					JsonPath js = ReusableMethods.rawToJson(res);
					occurrence = js.getString("Result.BooksAndAvailability[0].StartingTimes[0]");
					
					return occurrence;
	}
	
	public static int getNextMemberId(String aPIKey, String companyId, String clubId) {
		
		Response res = 
				
				given()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/member/getnextmemberid")
				.then()
//					.log().all()
					.extract().response();
		
					JsonPath js = ReusableMethods.rawToJson(res);
					int nextMemberId = js.getInt("Result.BarcodeId");
				
					return nextMemberId;
	}
	
	public static int getavailableappointments_BookId(Object customerId, String sDateTimeNoOffset, String eDateTimeNoOffset, int itemId){
		
		int bookId;
		
		Response res = given()

					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
//							.queryParam("ResourceId", resourceId)
						.when()
						.get("/api/v3/appointment/getavailableappointments/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+itemId)
							.then()
//							.log().body()
							.extract().response();
					
					JsonPath js = ReusableMethods.rawToJson(res);
					bookId = js.get("Result.BooksAndAvailability[0].Books[0].Id");
					
					return bookId;
	}

	public static int getAppointmentsByMember(String customerId) {
		
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();
		int appointmentId;
				
			Response res = given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
					appointmentId = js.getInt("Result[0].Id");
				
				return appointmentId;
	}
	
	public static Response getCardsOnFileByMember(String aPIKey,String companyId,String clubId,String customerId) {
		
		Response res = 
				
		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/member/getCardsOnFileByMember/"+customerId)
		.then()
//			.log().body()
			.extract().response();
			
			return res;	
	}
	
	public static Response getMember(String aPIKey,String companyId,String clubId,String customerId) {
		
		Response res = 
				
		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/member/getMember/"+customerId)
		.then()
//			.log().body()
			.extract().response();
			
			return res;	
	}
	
	public static Response getProjectConfiguration(String aPIKey,String companyId,String clubId) {
		
		Response res = 
				
		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
		.get("/api/v3/businessintelligence/getbusinessintelligenceconfiguration")
		.then()
//			.log().body()
			.extract().response();
			
			return res;	
	}
	
	public static Response getPackagePrice(String aPIKey,String companyId,String clubId, String customerId, String itemId, int quantity) {
		
		Response res = 
				
		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key",aPIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/package/getpackagepricing/"+customerId+"/"+itemId+"/"+quantity)
		.then()
//			.log().body()
			.extract().response();
			
			return res;	
	}
	
	public static Response getClassCoursePricing(String aPIKey,String companyId,String clubId, String customerId, String courseId) {
		
		Response res = 
				
			given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+courseId)
			.then()
//				.log().body()
				.extract().response();
			
			return res;	
	}
}