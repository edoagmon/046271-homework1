package homework1;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A Route is a path that traverses arbitrary GeoSegments, regardless of their
 * names.
 * <p>
 * Routes are immutable. New Routes can be constructed by adding a segment to
 * the end of a Route. An added segment must be properly oriented; that is, its
 * p1 field must correspond to the end of the original Route, and its p2 field
 * corresponds to the end of the new Route.
 * <p>
 * Because a Route is not necessarily straight, its length - the distance
 * traveled by following the path from start to end - is not necessarily the
 * same as the distance along a straight line between its end points.
 * <p>
 * Lastly, a Route may be viewed as a sequence of geographical features, using
 * the <tt>getGeoFeatures()</tt> method which returns an Iterator of GeoFeature
 * objects.
 * <p>
 * <b>The following fields are used in the specification:</b>
 * 
 * <pre>
 *   start : GeoPoint            // location of the start of the route
 *   end : GeoPoint              // location of the end of the route
 *   startHeading : angle        // direction of travel at the start of the route, in degrees
 *   endHeading : angle          // direction of travel at the end of the route, in degrees
 *   geoFeatures : sequence      // a sequence of geographic features that make up this Route
 *   geoSegments : sequence      // a sequence of segments that make up this Route
 *   length : real               // total length of the route, in kilometers
 *   endingGeoSegment : GeoSegment  // last GeoSegment of the route
 * </pre>
 **/
public class Route {

	private final LinkedList<GeoFeature> geoFeatures;

	/**
	 * Constructs a new Route.
	 * 
	 * @requires gs != null
	 * @effects Constructs a new Route, r, such that r.startHeading = gs.heading &&
	 *          r.endHeading = gs.heading && r.start = gs.p1 && r.end = gs.p2
	 **/
	public Route(GeoSegment gs) {
		this.geoFeatures = new LinkedList<GeoFeature>();
		this.geoFeatures.add(new GeoFeature(gs));
		this.checkRep();
	}

	/**
	 * Returns location of the start of the route.
	 * 
	 * @return location of the start of the route.
	 **/
	public GeoPoint getStart() {
		this.checkRep();
		return this.geoFeatures.getFirst().getStart();
	}

	/**
	 * Returns location of the end of the route.
	 * 
	 * @return location of the end of the route.
	 **/
	public GeoPoint getEnd() {
		this.checkRep();
		return this.geoFeatures.getLast().getEnd();
	}

	/**
	 * Returns direction of travel at the start of the route, in degrees.
	 * 
	 * @return direction (in compass heading) of travel at the start of the route,
	 *         in degrees.
	 **/
	public double getStartHeading() {
		this.checkRep();
		return this.geoFeatures.getFirst().getStartHeading();
	}

	/**
	 * Returns direction of travel at the end of the route, in degrees.
	 * 
	 * @return direction (in compass heading) of travel at the end of the route, in
	 *         degrees.
	 **/
	public double getEndHeading() {
		this.checkRep();
		return this.geoFeatures.getLast().getEndHeading();
	}

	/**
	 * Returns total length of the route.
	 * 
	 * @return total length of the route, in kilometers. NOTE: this is NOT
	 *         as-the-crow-flies, but rather the total distance required to traverse
	 *         the route. These values are not necessarily equal.
	 **/
	public double getLength() {
		this.checkRep();
		Iterator<GeoFeature> geoFeaure = this.geoFeatures.iterator();
		double length = 0;
		while (geoFeaure.hasNext()) {
			length += geoFeaure.next().getLength();
		}
		this.checkRep();
		return length;
	}

	/**
	 * Creates a new route that is equal to this route with gs appended to its end.
	 * 
	 * @requires gs != null && gs.p1 == this.end
	 * @return a new Route r such that r.end = gs.p2 && r.endHeading = gs.heading &&
	 *         r.length = this.length + gs.length
	 **/
	public Route addSegment(GeoSegment gs) {
		this.checkRep();
		Route route = new Route(gs);
		route.geoFeatures.removeFirst();
		route.geoFeatures.addAll(this.geoFeatures);
		if (route.geoFeatures.getLast().getName().equals(gs.getName())) {
			route.geoFeatures.add(route.geoFeatures.getLast().addSegment(gs));
		} else {
			route.geoFeatures.add(new GeoFeature(gs));
		}
		this.checkRep();
		return route;
	}

	/**
	 * Returns an Iterator of GeoFeature objects. The concatenation of the
	 * GeoFeatures, in order, is equivalent to this route. No two consecutive
	 * GeoFeature objects have the same name.
	 * 
	 * @return an Iterator of GeoFeatures such that
	 * 
	 *         <pre>
	 *      this.start        = a[0].start &&
	 *      this.startHeading = a[0].startHeading &&
	 *      this.end          = a[a.length - 1].end &&
	 *      this.endHeading   = a[a.length - 1].endHeading &&
	 *      this.length       = sum(0 <= i < a.length) . a[i].length &&
	 *      for all integers i
	 *          (0 <= i < a.length - 1 => (a[i].name != a[i+1].name &&
	 *                                     a[i].end  == a[i+1].start))
	 *         </pre>
	 * 
	 *         where <code>a[n]</code> denotes the nth element of the Iterator.
	 * @see homework1.GeoFeature
	 **/
	public Iterator<GeoFeature> getGeoFeatures() {
		this.checkRep();
		@SuppressWarnings("unchecked")
		LinkedList<GeoFeature> copyOfList = (LinkedList<GeoFeature>) this.geoFeatures.clone();
		this.checkRep();
		return copyOfList.iterator();
	}

	/**
	 * Returns an Iterator of GeoSegment objects. The concatenation of the
	 * GeoSegments, in order, is equivalent to this route.
	 * 
	 * @return an Iterator of GeoSegments such that
	 * 
	 *         <pre>
	 *      this.start        = a[0].p1 &&
	 *      this.startHeading = a[0].heading &&
	 *      this.end          = a[a.length - 1].p2 &&
	 *      this.endHeading   = a[a.length - 1].heading &&
	 *      this.length       = sum (0 <= i < a.length) . a[i].length
	 *         </pre>
	 * 
	 *         where <code>a[n]</code> denotes the nth element of the Iterator.
	 * @see homework1.GeoSegment
	 **/
	public Iterator<GeoSegment> getGeoSegments() {
		this.checkRep();
		Iterator<GeoFeature> geoFeaure = this.geoFeatures.iterator();
		LinkedList<GeoSegment> copyOfList = new LinkedList<GeoSegment>();
		while (geoFeaure.hasNext()) {
			Iterator<GeoSegment> gs = geoFeaure.next().getGeoSegments();
			while (gs.hasNext()) {
				GeoSegment segment = gs.next();
				GeoSegment copyOfSeg = new GeoSegment(segment.getName(), segment.getP1(), segment.getP2());
				copyOfList.add(copyOfSeg);
			}
		}
		this.checkRep();
		return copyOfList.iterator();
	}

	/**
	 * Compares the specified Object with this Route for equality.
	 * 
	 * @return true iff (o instanceof Route) && (o.geoFeatures and this.geoFeatures
	 *         contain the same elements in the same order).
	 **/
	public boolean equals(Object o) {
		this.checkRep();
		if (o != null && o instanceof GeoFeature) {
			Iterator<GeoFeature> geoFeaure = this.geoFeatures.iterator();
			Iterator<GeoFeature> oGeoFeaure = ((Route) o).getGeoFeatures();
			while (geoFeaure.hasNext() || oGeoFeaure.hasNext()) {
				if (!(oGeoFeaure.hasNext() && geoFeaure.hasNext())) {
					if (!oGeoFeaure.next().equals(geoFeaure.next())) {
						this.checkRep();
						return false;
					}
				}
			}
			this.checkRep();
			return true;
		}
		this.checkRep();
		return false;
	}

	/**
	 * Returns a hash code for this.
	 * 
	 * @return a hash code for this.
	 **/
	public int hashCode() {
		this.checkRep();
		int hashcode = 1;
		hashcode = 37 * hashcode + this.getStart().hashCode();
		hashcode = 37 * hashcode + this.getEnd().hashCode();
		hashcode = 37 * hashcode + Double.valueOf(this.getLength()).hashCode();
		hashcode = 37 * hashcode + Double.valueOf(this.getEndHeading()).hashCode();
		hashcode = 37 * hashcode + Double.valueOf(this.getStartHeading()).hashCode();
		Iterator<GeoFeature> geoFeaure = this.getGeoFeatures();
		while (geoFeaure.hasNext()) {
			hashcode = 37 * hashcode + geoFeaure.next().hashCode();
		}
		this.checkRep();
		return hashcode;
	}

	/**
	 * Returns a string representation of this.
	 * 
	 * @return a string representation of this.
	 **/
	public String toString() {
		this.checkRep();
		return "Route from " + this.geoFeatures.getFirst().getName() + " to " + this.geoFeatures.getLast().getName();
	}

	private void checkRep() {
		assert (this.geoFeatures.size() > 0) : "Empty list of features";
		GeoPoint head = null;
		for (GeoFeature geoFeaure : this.geoFeatures) {
			if (head != null) {
				assert (geoFeaure.getStart().equals(head)) : "Non matching features";
			}
			head = geoFeaure.getEnd();
		}
	}
}
