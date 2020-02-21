package resources;

import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class myActions extends base {
	
	public static int bookappointmentbymember(String occurrence, String customerId, int bookId, int itemId) { 
		
			int appointmentId;
				
			Response res = given()
//			.log().all()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
				.when()
				.body("{" + 
						"\"AppointmentClubId\": 1,"+ 
						"\"ItemId\": "+itemId+","+ 
						"\"Occurrence\": \""+occurrence+"\","+
						"\"CustomerId\": "+customerId+","+ 
						"\"RequestedBooks\": ["+bookId+"],"+ 
						"\"UserDisplayedPrice\": 0.00"+
						"}")	
					.post("/api/v3/appointment/bookappointmentbymember")
					.then()
//					.log().body()
					.extract().response();
			
			JsonPath js = ReusableMethods.rawToJson(res);
			appointmentId = js.getInt("Result.AppointmentId");
			
			return appointmentId;
	}	
	
	public static String cancelappointmentbyemployee(int appointmentId) { 
		
		String result;

		Response res = given()
//				.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
				.then()
//				.log().body()
				.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		result = js.getString("Status");

		return result;
		
}

	
}