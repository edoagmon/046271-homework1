package homework1;

/**
 * A GeoSegment models a straight line segment on the earth. GeoSegments 
 * are immutable.
 * <p>
 * A compass heading is a nonnegative real number less than 360. In compass
 * headings, north = 0, east = 90, south = 180, and west = 270.
 * <p>
 * When used in a map, a GeoSegment might represent part of a street,
 * boundary, or other feature.
 * As an example usage, this map
 * <pre>
 *  Trumpeldor   a
 *  Avenue       |
 *               i--j--k  Hanita
 *               |
 *               z
 * </pre>
 * could be represented by the following GeoSegments:
 * ("Trumpeldor Avenue", a, i), ("Trumpeldor Avenue", z, i),
 * ("Hanita", i, j), and ("Hanita", j, k).
 * </p>
 * 
 * </p>
 * A name is given to all GeoSegment objects so that it is possible to
 * differentiate between two GeoSegment objects with identical
 * GeoPoint endpoints. Equality between GeoSegment objects requires
 * that the names be equal String objects and the end points be equal
 * GeoPoint objects.
 * </p>
 *
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   name : String       // name of the geographic feature identified
 *   p1 : GeoPoint       // first endpoint of the segment
 *   p2 : GeoPoint       // second endpoint of the segment
 *   length : real       // straight-line distance between p1 and p2, in kilometers
 *   heading : angle     // compass heading from p1 to p2, in degrees
 * </pre>
 **/
public class GeoSegment  {	
  	private String segment_name;
  	private GeoPoint p1,p2;
  	private double heading;
  	private double dist;
		
  	/**
     * Constructs a new GeoSegment with the specified name and endpoints.
     * @requires name != null && p1 != null && p2 != null
     * @effects constructs a new GeoSegment with the specified name and endpoints.
     **/
  	public GeoSegment(String name, GeoPoint p1, GeoPoint p2) {
  		this.p1           = p1;
  		this.p2           = p2;
  		this.segment_name = name;
  		this.heading      = p1.headingTo(p2);
  		this.dist         = p1.distanceTo(p2);  //Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y,2));
  		this.checkRep(); 		
  	}


  	/**
     * Returns a new GeoSegment like this one, but with its endpoints reversed.
     * @return a new GeoSegment gs such that gs.name = this.name
     *         && gs.p1 = this.p2 && gs.p2 = this.p1
     **/
  	public GeoSegment reverse() {
  		this.checkRep();
  		GeoSegment rev_geo_seg = new GeoSegment(this.segment_name,this.p2,this.p1);
  		return rev_geo_seg;
  	}


  	/**
  	 * Returns the name of this GeoSegment.
     * @return the name of this GeoSegment.
     */
  	public String getName() {
  		this.checkRep();
  		return this.segment_name;
  	}


  	/**
  	 * Returns first endpoint of the segment.
     * @return first endpoint of the segment.
     */
  	public GeoPoint getP1() {
  		this.checkRep();
  		return this.p1;
  	}


  	/**
  	 * Returns second endpoint of the segment.
     * @return second endpoint of the segment.
     */
  	public GeoPoint getP2() {
  		this.checkRep();
  		return this.p2;
  	}


  	/**
  	 * Returns the length of the segment.
     * @return the length of the segment, using the flat-surface, near the
     *         Technion approximation.
     */
  	public double getLength() {
  		this.checkRep();
  		return this.dist;
  	}


  	/**
  	 * Returns the compass heading from p1 to p2.
     * @requires this.length != 0
     * @return the compass heading from p1 to p2, in degrees, using the
     *         flat-surface, near the Technion approximation.
     **/
  	public double getHeading() {
  		this.checkRep();
  		return this.heading;
  	}


  	/**
     * Compares the specified Object with this GeoSegment for equality.
     * @return gs != null && (gs instanceof GeoSegment)
     *         && gs.name = this.name && gs.p1 = this.p1 && gs.p2 = this.p2
   	 **/
  	public boolean equals(Object gs) {
  		this.checkRep();
  		if (gs == null || !(gs instanceof GeoSegment))
  			return false;
  		
  		GeoSegment other_segment = (GeoSegment)gs;
  		if (this.p1.equals(other_segment.p1) &&
  		    this.p2.equals(other_segment.p2) &&
  		    this.segment_name.equals(other_segment.segment_name))
  			return true;
  		else
  			return false;
  	}


  	/**
  	 * Returns a hash code value for this.
     * @return a hash code value for this.
     **/
  	public int hashCode() {
  		this.checkRep();
  		int hashcode = 11;
  		hashcode = 37 * hashcode + this.getName().hashCode();
  		hashcode = 37 * hashcode + this.getP1().hashCode();
  		hashcode = 37 * hashcode + this.getP2().hashCode();
  		this.checkRep();
    	return hashcode;
  	}


  	/**
  	 * Returns a string representation of this.
     * @return a string representation of this.
     **/
  	public String toString() {
  		this.checkRep();
  		String st = "name : " + this.segment_name + "coordnations"  + "("  + this.p1.getLongitude() + this.p1.getLatitude() +")"
  								+ "("  + this.p2.getLongitude() + this.p2.getLatitude() +")";
  		return st;
  		
  	}
  	
  	
  	/**
  	 * Check if the object is valid.
  	 * @throws assertion error representation invariant is violated.
  	 * 
  	 **/
  	private void checkRep() {
  		assert(this.dist >= 0):
  			"Error negetive distance";
  		assert(0 <= this.heading && 360 >= this.heading):
  			"fatal Error wrong coding for angel calcuation" ; 
  	}

}

