package resources;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods extends base {
	
	public static int loopCount = 0;

	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	public static JsonPath rawToJson(Response r) {

		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
			myWait(1000);
		return x;
	}
	
	public static void unenroll(String companyId, int invoiceId, int enrollmentId, int customerId)
	{
		
		if(loopCount<5) // Counting loops so test will fail it unenroll fails 5 times
		{
			myWait(5000);
			base.getPropertyData();
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.baseURI = prop.getProperty("baseURI");
			
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteenrollment/"+companyId+"/"+enrollmentId+"")
					.then().log().body()
					;
	
				myWait(5000);
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
					.then().log().body()
					;
			
				myWait(5000);
			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/2020-01-01/2200-01-01")
						.then()
						.extract().response();
			
				JsonPath js = rawToJson(res);
	
					if(res.statusCode() != 404)	// Message: "Nothing found"
					{
						loopCount++;
						System.out.println("-----------------");
						System.out.println("[INFO]: Retrying Unenroll");
						System.out.println("[INFO]: Item Description: "+js.getString("Result[0].ItemDescription"));
						System.out.println("[INFO]: Item Description: "+js.getString("Result[0].StartDateTime"));
						System.out.println("[INFO]: customerId: "+customerId);
						System.out.println("[INFO]: enrollmentId: "+enrollmentId);
						System.out.println("[INFO]: invoiceId: "+invoiceId);
						System.out.println("[INFO]: loopCount: "+loopCount);
						System.out.println("-----------------");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
					}
			
			}
//			else
//	 		{
//				Assert.assertTrue(false); //failing test because loopCount exceeded 5
//			}
			loopCount = 0;
			return;
	}
	
	public static void deleteEnrollment(String companyId, int enrollmentId, int customerId)
	{
		
		if(loopCount<5)
		{
			myWait(1000);
			base.getPropertyData();
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.baseURI = prop.getProperty("baseURI");
			
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteenrollment/"+companyId+"/"+enrollmentId+"")
					.then()
					.assertThat().statusCode(200)
//					.log().body()
					;
	
				myWait(5000);

					if (isEnrolled(customerId) == true)
					{
						loopCount++;
						System.out.println("----------------------------------");
						System.out.println("[WARNING] Retry Delete Enrollment");
						System.out.println("[WARNING] customerId: "+customerId);
						System.out.println("[WARNING] enrollmentId: "+enrollmentId);
						System.out.println("[WARNING] loopCount: "+loopCount);
						System.out.println("----------------------------------");
						ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
					}
			
			}
			else
	 		{
				System.out.println("----------------------------------");
				System.out.println("[ERROR] Retry Loop Count Exceeded");
				System.out.println("[ERROR] customerId: "+customerId);
				System.out.println("[ERROR] enrollmentId: "+enrollmentId);
				System.out.println("[ERROR] loopCount: "+loopCount);
				System.out.println("----------------------------------");
				Assert.assertTrue(false); //failing test because loopCount exceeded 5
			}
			loopCount = 0;
			return;
	}
	
	public static void deleteInvoice(String companyId, int invoiceId, int customerId)
	{
		
		if(loopCount<5)
		{
			myWait(1000);
			base.getPropertyData();
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.baseURI = prop.getProperty("baseURI");
			
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
					.then()
					.assertThat().statusCode(200)
//					.log().body()
					;
			
				myWait(5000);

					if (isEnrolled(customerId) == true)
					{
						loopCount++;
						System.out.println("----------------------------------");
						System.out.println("[WARNING] Retry Delete Invoice");
						System.out.println("[WARNING] customerId: "+customerId);
						System.out.println("[WARNING] invoiceId: "+invoiceId);
						System.out.println("[WARNING] Retry Count: "+loopCount);
						System.out.println("----------------------------------");
						ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
					}
			}
			else
	 		{
				System.out.println("----------------------------------");
				System.out.println("[ERROR] Retry Loop Count Exceeded");
				System.out.println("[ERROR] customerId: "+customerId);
				System.out.println("[ERROR] enrollmentId: "+invoiceId);
				System.out.println("[ERROR] loopCount: "+loopCount);
				System.out.println("----------------------------------");
				Assert.assertTrue(false); //failing test because loopCount exceeded 5
			}
			loopCount = 0;
			return;
	}
	
	public static boolean isEnrolled(int customerId){
		
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		
		Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/2200-01-01")
						.then()
						.extract().response();
				
				if(res.statusCode() != 404)
				{
					return true;
				}
				else
					{
						return false;
					}
	}
	
	public static boolean hasAppointment(String customerId)
	{
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
			
		Response res = 			given()
//							.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam(customerId)
					.when()
							.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/2200-01-01")
							.then()
							.extract().response();
					if(res.statusCode() != 404)
					{
						return true;
					}
					else
						{
							return false;
						}
		
	}

	public static void myWait(int duration)
		{
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
}