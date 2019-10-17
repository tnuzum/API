package resources;

public class resources {

/*	public static String getHeader()
	{
		return null;
//		String header = RestAssured.given().header("accept", "application/json");
//		return header;
	}
	*/
	public static String placepostDataJSON()
		{
			String res = "/maps/api/place/add/json";
			return res;
		}
		public static String deletePostDataJSON()
		{
			String res = "/maps/api/place/delete/json";
			return res;
		}
		public static String getnearbyDataJSON()
		{
			String res = "/maps/api/place/nearbysearch/json";
			return res;
		}
		public static String placepostDataXML()
		{
			String res = "/maps/api/place/add/xml";
			return res;
		}
		public static String deletePostDataXML()
		{
			String res = "/maps/api/place/delete/xml";
			return res;
		}
		public static String getnearbyDataXML()
		{
			String res = "/maps/api/place/nearbysearch/xml";
			return res;
		}
}
