package homework1;

/**
 * This class contains two static arrays representing GeoPoints and
 * GeoSegments near the Technion, as shown in the figure in homework
 * assignment #1. These arrays can be used as an example for testing
 * the code correctness. 
 */
public class ExampleGeoSegments {
	
	/**
	 * Array of GeoPoints near the Technion.
	 */
	public static final GeoPoint[] points = {
		new GeoPoint(32782269,35013820),	//  0. Hankin Road, Natan Komoi & A.D. Gordon Intersection
		new GeoPoint(32783098,35014528),	//  1. Ziv Square (Hankin Road, Trumpledor Avenue, Shalom Aleichem & Berl Intersection)
		new GeoPoint(32785295,35017833),	//  2. Trumpeldor Avenue & Hanita Intersection
		new GeoPoint(32787081,35020735),	//  3. Trumpeldor Avenue & Hagalil Intersection
		new GeoPoint(32789768,35018578),	//  4. Water Tower (on Hagalil)
		new GeoPoint(32795631,35010296),	//  5. Hagalil & Hanita Intersection
		new GeoPoint(32786801,35016991),	//  6. Hanita & Simha Golan Road Intersection
		new GeoPoint(32789205,35008467),	//  7. Grand Canyon Shopping Center (on Simha Golan Road)
		new GeoPoint(32790170,35003944),	//  8. Simha Golan Road & Ruppin Road Intersection	
		new GeoPoint(32787419,34999041),	//  9. Hankin Road & Ruppin Road Intersection 	
		new GeoPoint(32784334,35003381),	// 10. Hankin Road & Moshe Got Levin Intersection	
		new GeoPoint(32782241,35008821),	// 11. International & Hankin Road Intersection 	
		new GeoPoint(32778200,35010097),	// 12. International & Me'ir Ya'ari Intersection	
		new GeoPoint(32779939,35013248),	// 13. Me'ir Ya'ari & Natan Komoi Intersection
	};
	
	/**
	 * Array of GeoSegments near the Technion.
	 */
	public static final GeoSegment[] segments = {
		new GeoSegment("Hankin Road",       points[0], points[1]),
		new GeoSegment("Trumpeldor Avenue", points[1], points[2]),
		new GeoSegment("Trumpeldor Avenue", points[2], points[3]),
		new GeoSegment("Hagalil", 			points[3], points[4]),
		new GeoSegment("Hagalil", 			points[4], points[5]),
		new GeoSegment("Hanita", 			points[5], points[6]),
		new GeoSegment("Hanita", 			points[6], points[2]),
		new GeoSegment("Simha Golan Road",  points[6], points[7]),
		new GeoSegment("Simha Golan Road",  points[7], points[8]),
		new GeoSegment("Ruppin Road", 		points[8], points[9]),
		new GeoSegment("Hankin Road", 		points[9], points[10]),
		new GeoSegment("Hankin Road", 		points[10], points[11]),
		new GeoSegment("Hankin Road", 		points[11], points[0]),
		new GeoSegment("International", 	points[11], points[12]),
		new GeoSegment("Me'ir Ya'ari", 		points[12], points[13]),
		new GeoSegment("Natan Komoi", 		points[13], points[0])
	};
}
