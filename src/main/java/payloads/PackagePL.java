package payloads;

import resources.base;

public class PackagePL extends base {
	
	public static String PurchasePackageWithNewCreditCard	
			(
			String customerId,
			String itemId,
			int quantity,
			Double displayedGrandTotal,
			String cardNumber,
			String nameOnCard,
			String month,
			String year,
			String securityCode,
			String addressLine1,
			String city,
			String state,
			String postalCode
			) {
		
		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+itemId+"\"," + 
				"  \"Quantity\": \""+quantity+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"CardNumber\": \""+cardNumber+"\"," + 
				"  \"NameOnCard\": \""+nameOnCard+"\"," + 
				"  \"ExpirationDate\": {" + 
				"    \"Month\": \""+month+"\"," + 
				"    \"Year\": "+year+"" + 
				"  }," + 
				"  \"SecurityCode\": \""+securityCode+"\"," + 
				"  \"AddressLine1\": \""+addressLine1+"\"," + 
				"  \"City\": \""+city+"\"," + 
				"  \"StateProvince\": \""+state+"\"," + 
				"  \"PostalCode\": \""+postalCode+"\"" +
				"}";

		return payload;
	}
	
	public static String PurchasePackageWithNewCreditCard_WithAddress2	
	(
	String customerId,
	String itemId,
	int quantity,
	Double displayedGrandTotal,
	String cardNumber,
	String nameOnCard,
	String month,
	String year,
	String securityCode,
	String addressLine1,
	String addressLine2,
	String city,
	String state,
	String postalCode
	) {

String payload = "{" + 
		"  \"CustomerId\": "+customerId+"," + 
		"  \"ItemId\": \""+itemId+"\"," + 
		"  \"Quantity\": \""+quantity+"\"," + 
		"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
		"  \"CardNumber\": \""+cardNumber+"\"," + 
		"  \"NameOnCard\": \""+nameOnCard+"\"," + 
		"  \"ExpirationDate\": {" + 
		"    \"Month\": \""+month+"\"," + 
		"    \"Year\": "+year+"" + 
		"  }," + 
		"  \"SecurityCode\": \""+securityCode+"\"," + 
		"  \"AddressLine1\": \""+addressLine1+"\"," + 
		"  \"AddressLine2\": \""+addressLine2+"\"," + 
		"  \"City\": \""+city+"\"," + 
		"  \"StateProvince\": \""+state+"\"," + 
		"  \"PostalCode\": \""+postalCode+"\"" +
		"}";

return payload;
}
	
	public static String PurchasePackageWithCardOnFile	
			(
			String customerId,
			String itemId,
			int quantity,
			Double displayedGrandTotal,
			int accountId
			) {

		String payload = "{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ItemId\": \""+itemId+"\"," + 
				"  \"Quantity\": \""+quantity+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"AccountId\": \""+accountId+"\"" +
				"}";

		return payload;
}

}
