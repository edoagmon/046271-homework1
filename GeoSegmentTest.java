package homework1;

public class GeoSegmentTest {
	
	private static final double tolerance = 0.01;
   
  	private GeoPoint gpZivSquare;
  	private GeoPoint gpWest;			// 1 km west to gpZivSquare
  	private GeoPoint gpEast;			// 1 km east to gpZivSquare 
  	private GeoPoint gpNorth;			// 1 km north to gpZivSquare
                                
  	private GeoSegment gsEast;
  	private GeoSegment gsWest;
  	private GeoSegment gsNorth;
  	private GeoSegment gsEast2; 
  	private GeoSegment gsWest2;
  	private GeoSegment gsDiag; 
  
  
  	public GeoSegmentTest() {
		gpZivSquare = new GeoPoint(32783098,35014528);
		gpWest = new GeoPoint(32783098,35003854);
		gpEast = new GeoPoint(32783098,35025202);
		gpNorth = new GeoPoint(32792115,35014528);
		
    	gsEast = new GeoSegment("East", gpZivSquare, gpEast);
    	gsWest = new GeoSegment("West", gpZivSquare, gpWest);
    	gsNorth = new GeoSegment("North", gpZivSquare, gpNorth);
    	gsEast2 = new GeoSegment("East", gpWest, gpZivSquare);
    	gsWest2 = new GeoSegment("West", gpWest, gpZivSquare);
    	gsDiag = new GeoSegment("NE", gpWest, gpNorth);
  	}
  	
  	
  	boolean same(double x, double y) {
  		return ((y >= x-tolerance) && (y <= x+tolerance));
  	}
  	
  	
	public void show(String str) {
		System.out.println();
		System.out.println("***** " + str + " *****");
	}
		
		
	public void show(String str, boolean ok) {
		if (ok)
			System.out.print("v ");
		else
			System.out.print("x ");
		System.out.println(str);	
	}
  	
  	
  	public void test() {
  		show("equals()");
		show("Self equality",
			gsNorth.equals(gsNorth));
		show("Equal to copy",
			 gsNorth.equals(new GeoSegment("North", gpZivSquare, gpNorth)));
		show("Totally different objects are not equal.",
			!gsNorth.equals(gsEast));
		show("Same points, different name are not equal.",
			!gsEast2.equals(gsWest2));
		show("Same name, different points are not equal.",
			!gsEast.equals(gsEast2));
			
		GeoPoint gpZivSquare2 = new GeoPoint(32783098,35014528);
		GeoPoint gpNorth2 = new GeoPoint(32792115,35014528);
		GeoSegment gsNorth2 = new GeoSegment("North", gpZivSquare2, gpNorth2);
		show("Segment equality should use value equality, not reference equality",
			gsNorth.equals(gsNorth2));
		show("equals(non-GeoSegment) should be false",
		   !gsNorth2.equals("aString"));
		show("equals(null) should be false",
		   !gsNorth2.equals(null));
		
		show("hashCode()");
		show(".equals() objects must have the same .hashCode()",
			gsNorth.hashCode() == gsNorth2.hashCode());
		   		
		show("reverse()");
		show("Reversed segment is equal to same segment reversed.",
			gsEast.reverse().equals(gsEast.reverse()));
		show("Twice reversed segment is equal to initial.",
			gsEast.reverse().reverse().equals(gsEast));
		show("New reversed item is equal to its reversal.",
			gsWest.equals(gsWest2.reverse()));
		show("New reversed item is equal to its reversal.",
			gsWest.reverse().equals(gsWest2));
		show("Segment is not equal to its reversal.",
			!gsEast.reverse().equals(gsEast));
		show("Segment reversed twice is not equal to its reversal.",
			!gsEast.reverse().reverse().equals(gsEast.reverse()));
		show("Reversed segment reversed is not equal to reversed.",
			!gsWest.reverse().equals(gsWest2.reverse()));
			
		show("getName()");
		show("getName() works.", gsEast.getName().equals("East"));
				
		show("getP1() & getP2()");
		show("getP1() works.", gsEast.getP1().equals(gpZivSquare));
		show("getP2() works.", gsEast2.getP2().equals(gpZivSquare));
		
		show("getLength()");
		show("East 1 km", same(gsEast.getLength(),1.0));
		show("West 1 km", same(gsWest.getLength(),1.0));
		show("North 1 km", same(gsNorth.getLength(),1.0));
		show("1.414 km", same(gsDiag.getLength(),1.414));
		
		show("getHeading()");
		show("East should be 90", same(gsEast.getHeading(), 90.0));
		show("West should be 270", same(gsWest.getHeading(), 270.0));
		double nh = gsNorth.getHeading();
		show("North heading (" + nh + ") is not less than zero.",
			!(nh < 0.0));
		show("North heading (" + nh + ") is not greater or equal to 360.",
			!(nh >= 360.0));
		show("North heading is expected to be 0 or 359.999", 
			!((nh > tolerance) && (Math.abs(360.0 - nh)> tolerance )));
		show("South heading should be 180",
		 	same(gsNorth.reverse().getHeading(), 180.0));		
  	}


	public static void main(String[] args) {
		GeoSegmentTest segmentTest = new GeoSegmentTest();
		segmentTest.test();
	}
}
