package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	static String projectPath = System.getenv("ECLIPSE_HOME");
	String userProfile = System.getenv("USERPROFILE");
	
// ** this block to getPropData isn't working **
/*	public static Properties prop = new Properties();
	public Properties getPropData() throws IOException {
		FileInputStream fis = new FileInputStream(
				projectPath + "\\API\\src\\main\\java\\resources\\rest.properties");
		prop.load(fis);
		return null;
	}*/

	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	public static JsonPath rawToJson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}

	// ** EXAMPLES ONLY FROM JIRA
	// Action Methods
	public static String getSessionKey() {
//		RestAssured.baseURI = prop.getProperty("hostJira");
		RestAssured.baseURI = "http://localhost:8090";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"toddnuzum\", \"password\": \"Working1!\" }").log().all().

				when().post("rest/auth/1/session").

				then().statusCode(200).

				extract().response();

		JsonPath js = ReusableMethods.rawToJson(res);
		String value = js.get("session.value");
		System.out.println(value);
		return value;
	}

	public static String createIssue() {
		RestAssured.baseURI = "http://localhost:8090";
		Response res1 = given().header("Content-Type", "application/json")
				.header("cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).

				body(Payload.getIssue()).

				when().post("/rest/api/2/issue/").

				then().statusCode(201).

				extract().response();

		JsonPath js = ReusableMethods.rawToJson(res1);
		String issueid = js.get("id");
		System.out.println(issueid);
		return issueid;
	}

	public static String addComment() {
		RestAssured.baseURI = "http://localhost:8090";

		Response res2 = given().header("Content-Type", "application/json")
				.header("cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).

				body(Payload.addComment()).

				when().post("/rest/api/2/issue/10107/comment").

				then().statusCode(201).extract().response();

		JsonPath js = ReusableMethods.rawToJson(res2);
		String newcommentid = js.get("id");
		System.out.println("Comment ID: " + newcommentid);
		return newcommentid;

	}

	public static String updateComment() {
		RestAssured.baseURI = "http://localhost:8090";

		Response res2 = given().header("Content-Type", "application/json")
				.header("cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).

				body(Payload.addComment()).

				when().put("/rest/api/2/issue/10107/comment/10116").

				then().statusCode(200).extract().response();

		JsonPath js = ReusableMethods.rawToJson(res2);
		String updateCommentID = js.get("id");
		System.out.println("Comment ID: " + updateCommentID);
		return updateCommentID;
	}

}
