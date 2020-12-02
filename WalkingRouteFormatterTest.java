package homework1;

public class WalkingRouteFormatterTest {
	
	private WalkingRouteFormatter mDirections;
  	private Route mShortRoute;

  
  	public WalkingRouteFormatterTest() {
    	mDirections = new WalkingRouteFormatter();
    	mShortRoute = new Route(new GeoSegment("Trumpeldor Avenue",
    			new GeoPoint(32783098,35014528), new GeoPoint(32787081,35020735)));
    		mShortRoute = mShortRoute.addSegment(new GeoSegment("Hagalil",
    			new GeoPoint(32787081,35020735), new GeoPoint(32795631,35010296)));
  	}
  	
  	
  	public void test() {
		String directions =
			"Turn slight right onto Trumpeldor Avenue and walk for 15 minutes.\n" +
    		"Turn left onto Hagalil and walk for 27 minutes.\n";
    
		if (mDirections.computeDirections(mShortRoute, 0).equals(directions))
			System.out.println("Test passed correctly");
		else
			System.out.println("Test not passed correctly");
	}
  	
  	
	public static void main(String[] args) {
		WalkingRouteFormatterTest directionsTest = new WalkingRouteFormatterTest();
		directionsTest.test();
	}
}
