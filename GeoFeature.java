package homework1;

import java.util.*;


/**
 * A GeoFeature represents a route from one location to another along a
 * single geographic feature. GeoFeatures are immutable.
 * <p>
 * GeoFeature abstracts over a sequence of GeoSegments, all of which have
 * the same name, thus providing a representation for nonlinear or nonatomic
 * geographic features. As an example, a GeoFeature might represent the
 * course of a winding river, or travel along a road through intersections
 * but remaining on the same road.
 * <p>
 * GeoFeatures are immutable. New GeoFeatures can be constructed by adding
 * a segment to the end of a GeoFeature. An added segment must be properly
 * oriented; that is, its p1 field must correspond to the end of the original
 * GeoFeature, and its p2 field corresponds to the end of the new GeoFeature,
 * and the name of the GeoSegment being added must match the name of the
 * existing GeoFeature.
 * <p>
 * Because a GeoFeature is not necessarily straight, its length - the
 * distance traveled by following the path from start to end - is not
 * necessarily the same as the distance along a straight line between
 * its endpoints.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * <pre>
 *   start : GeoPoint       // location of the start of the geographic feature
 *   end : GeoPoint         // location of the end of the geographic feature
 *   startHeading : angle   // direction of travel at the start of the geographic feature, in degrees
 *   endHeading : angle     // direction of travel at the end of the geographic feature, in degrees
 *   geoSegments : sequence	// a sequence of segments that make up this geographic feature
 *   name : String          // name of geographic feature
 *   length : real          // total length of the geographic feature, in kilometers
 * </pre>
 **/
public class GeoFeature {
	
	private String name_feature;
	private GeoPoint start;
	private GeoPoint end;
	private double start_heading;
	private double end_heading;
	private final LinkedList<GeoSegment> segment_list;
	// Implementation hint:
	// When asked to return an Iterator, consider using the iterator() method
	// in the List interface. Two nice classes that implement the List
	// interface are ArrayList and LinkedList. If comparing two Lists for
	// equality is needed, consider using the equals() method of List. More
	// info can be found at:
	//   http://docs.oracle.com/javase/8/docs/api/java/util/List.html
	
	
  	// TODO Write abstraction function and representation invariant

	
	/**
     * Constructs a new GeoFeature.
     * @requires gs != null
     * @effects Constructs a new GeoFeature, r, such that
     *	        r.name = gs.name &&
     *          r.startHeading = gs.heading &&
     *          r.endHeading = gs.heading &&
     *          r.start = gs.p1 &&
     *          r.end = gs.p2
     **/
  	public GeoFeature(GeoSegment gs) {
  		this.name_feature       = gs.getName();
  		this.addSegment(gs);
  		this.start              = this.segment_list.getFirst().getP1();
  		this.end		        = this.segment_list.getLast().getP1();
  		this.start_heading	    = this.segment_list.getFirst().getHeading();
  		this.end_heading	    = this.segment_list.getLast().getHeading();
  		this.checkRep();
  		
  	}
  

 	/**
 	  * Returns name of geographic feature.
      * @return name of geographic feature
      */
  	public String getName() {
  		this.checkRep();
  		return this.name_feature;
  	}


  	/**
  	 * Returns location of the start of the geographic feature.
     * @return location of the start of the geographic feature.
     */
  	public GeoPoint getStart() {
  		this.checkRep();
  		return this.start;
  	}


  	/**
  	 * Returns location of the end of the geographic feature.
     * @return location of the end of the geographic feature.
     */
  	public GeoPoint getEnd() {
  		this.checkRep()
  		return this.end;
  	}


  	/**
  	 * Returns direction of travel at the start of the geographic feature.
     * @return direction (in standard heading) of travel at the start of the
     *         geographic feature, in degrees.
     */
  	public double getStartHeading() {
  		this.checkRep();
  		return this.start_heading;
  	}


  	/**
  	 * Returns direction of travel at the end of the geographic feature.
     * @return direction (in standard heading) of travel at the end of the
     *         geographic feature, in degrees.
     */
  	public double getEndHeading() {
  		this.checkRep();
  		return this.end_heading;
  	}


  	/**
  	 * Returns total length of the geographic feature, in kilometers.
     * @return total length of the geographic feature, in kilometers.
     *         NOTE: this is NOT as-the-crow-flies, but rather the total
     *         distance required to traverse the geographic feature. These
     *         values are not necessarily equal.
     */
  	public double getLength() {
  		this.checkRep()
  		double sum = 0;
  		
  		for (int i = 0 ; i<this.segment_list.size() ; i++) {
  			sum+= this.segment_list.get(i).getLength();
  		}
  		return sum;
  	}


  	/**
   	 * Creates a new GeoFeature that is equal to this GeoFeature with gs
   	 * appended to its end.
     * @requires gs != null && gs.p1 = this.end && gs.name = this.name.
     * @return a new GeoFeature r such that
     *         r.end = gs.p2 &&
     *         r.endHeading = gs.heading &&
     *    	   r.length = this.length + gs.length
     **/
  	public GeoFeature addSegment(GeoSegment gs) {
  		this.checkRep()
  		GeoFeature newGeoFtr = new GeoFeature(gs);
  		newGeoFtr.segment_list.clear();
  		newGeoFtr.segment_list.addAll(this.segment_list);
  		newGeoFtr.segment_list.add(gs);
  		
  		
  		newGeoFtr.end		        = newGeoFtr.segment_list.getLast().getP1();
  		newGeoFtr.end_heading	    = newGeoFtr.segment_list.getLast().getHeading();
  		return newGeoFtr;

  	}


  	/**
     * Returns an Iterator of GeoSegment objects. The concatenation of the
     * GeoSegments, in order, is equivalent to this GeoFeature. All the
     * GeoSegments have the same name.
     * @return an Iterator of GeoSegments such that
     * <pre>
     *      this.start        = a[0].p1 &&
     *      this.startHeading = a[0].heading &&
     *      this.end          = a[a.length - 1].p2 &&
     *      this.endHeading   = a[a.length - 1].heading &&
     *      this.length       = sum(0 <= i < a.length) . a[i].length &&
     *      for all integers i
     *          (0 <= i < a.length-1 => (a[i].name == a[i+1].name &&
     *                                   a[i].p2d  == a[i+1].p1))
     * </pre>
     * where <code>a[n]</code> denotes the nth element of the Iterator.
     * @see homework1.GeoSegment
     */
  	public Iterator<GeoSegment> getGeoSegments() {
  		this.checkRep()
  		LinkedList<GeoSegment> copy = this.segment_list.clone();
  		return copy.iterator();  		
  	}


  	/**
     * Compares the argument with this GeoFeature for equality.
     * @return o != null && (o instanceof GeoFeature) &&
     *         (o.geoSegments and this.geoSegments contain
     *          the same elements in the same order).
     **/
  	public boolean equals(Object o) {
  		this.checkRep()
  		if (o != null && o instanceof GeoFeature) {
  			
  			if (this.segment_list.size() == ((GeoFeature)o).segment_list.size()) {
  				for (int i = 0; i < this.segment_list.size() ; i++) {
  					if (this.segment_list.get(i).equals(((GeoFeature)o).segment_list.get(i))) {continue;}else{return false;}
  				}
  					return true; // if the loop ended with no break so the linklists equals
  			}
  		}
  		return false;
  	}
  	
  	


  	/**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
  	public int hashCode() {
  		this.checkRep()
    	return 1;
  	}


  	/**
  	 * Returns a string representation of this.
   	 * @return a string representation of this.
     **/
  	public String toString() {
  		this.checkRep()
  		return this.name_feature;
  	}
  	
  	
  	private checkRep() {
  		assert(this.geoSegments.size() > 0) : "negetive size segments list";
  	}
}
