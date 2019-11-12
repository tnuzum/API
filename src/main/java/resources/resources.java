package resources;

public class resources {

/*	public static String getHeader()
	{
		return null;
//		String header = RestAssured.given().header("accept", "application/json");
//		return header;
	}
	*/

	
	//Example Only - used by "_framework_Test.java"
	 
		public static String getnearbyDataJSON()
		{
			String res = "/maps/api/place/nearbysearch/json";
			return res;
		}
		
}
