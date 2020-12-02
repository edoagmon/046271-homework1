package homework1;

public class DrivingRouteFormatterTest {
	
	private DrivingRouteFormatter mDirections;
  	private Route mShortRoute;

  
  	public DrivingRouteFormatterTest() {
    	mDirections = new DrivingRouteFormatter();
		mShortRoute = new Route(new GeoSegment("Trumpeldor Avenue",
			new GeoPoint(32783098,35014528), new GeoPoint(32787081,35020735)));
		mShortRoute = mShortRoute.addSegment(new GeoSegment("Hagalil",
			new GeoPoint(32787081,35020735), new GeoPoint(32795631,35010296)));
  	}
  	
  	
  	public void test() {
		String directions =
			"Turn slight right onto Trumpeldor Avenue and go 0.7 kilometers.\n" +
			"Turn left onto Hagalil and go 1.4 kilometers.\n";
    
		if (mDirections.computeDirections(mShortRoute, 0).equals(directions))
			System.out.println("Test passed correctly");
		else
			System.out.println("Test not passed correctly");
	}
  	
  	
	public static void main(String[] args) {
		DrivingRouteFormatterTest directionsTest = new DrivingRouteFormatterTest();
		directionsTest.test();
	}
}
