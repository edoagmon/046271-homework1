package homework1;

import java.util.Iterator;

/**
 * A RouteFormatter class knows how to create a textual description of
 * directions from one location to another. The class is abstract to
 * support different textual descriptions.
 */
public abstract class RouteFormatter {

	
	private enum Turn {
		 STRIGHT 
		,SLIGHT_RIGHT
		,RIGHT
		,SHARP_RIGHT
		,SLIGHT_LEFT
		,LEFT
		,SHARP_LEFT
		,U_TURN
	}
	/**
  	/**
     * Give directions for following this Route, starting at its start point
     * and facing in the specified heading.
     * @requires route != null && 
     * 			0 <= heading < 360
     * @param route the route for which to print directions.
   	 * @param heading the initial heading.
     * @return A newline-terminated directions <tt>String</tt> giving
     * 	       human-readable directions from start to end along this route.
     **/
  	public String computeDirections(Route route, double heading) {
  		String directions = "";
  		Iterator<GeoFeature> geoFeature = route.getGeoFeatures();
  		double routeHeading = heading;
  		while (geoFeature.hasNext()) {
  			GeoFeature gf = geoFeature.next();
  			directions = directions.concat(this.computeLine(gf, routeHeading));
  			routeHeading  = gf.getEndHeading();
  		}
  		return directions;
  	}


  	/**
     * Computes a single line of a multi-line directions String that
     * represents the instructions for traversing a single geographic
     * feature.
     * @requires geoFeature != null
     * @param geoFeature the geographical feature to traverse.
   	 * @param origHeading the initial heading.
     * @return A newline-terminated <tt>String</tt> that gives directions
     * 		   on how to traverse this geographic feature.
     */
  	public abstract String computeLine(GeoFeature geoFeature, double origHeading);


  	/**
     * Computes directions to turn based on the heading change.
     * @requires 0 <= oldHeading < 360 &&
     *           0 <= newHeading < 360
     * @param origHeading the start heading.
   	 * @param newHeading the desired new heading.
     * @return English directions to go from the old heading to the new
     * 		   one. Let the angle from the original heading to the new
     * 		   heading be a. The turn should be annotated as:
     * <p>
     * <pre>
     * Continue             if a < 10
     * Turn slight right    if 10 <= a < 60
     * Turn right           if 60 <= a < 120
     * Turn sharp right     if 120 <= a < 179
     * U-turn               if 179 <= a
     * </pre>
     * and likewise for left turns.
     */
  	protected String getTurnString(double origHeading, double newHeading) {
  		double angleDiff = this.getAnglesDiff(origHeading, newHeading);		
  		switch (this.getTurnKind(angleDiff)) {
		case STRIGHT:
			return "Continue";
		case SLIGHT_RIGHT:
			return "Turn slight right";
		case RIGHT:
			return "Turn right";
		case SHARP_RIGHT:
			return "Turn sharp right";
		case SLIGHT_LEFT:
			return "Turn slight left";	
		case LEFT:
			return "Turn left";
		case SHARP_LEFT:
			return "Turn sharp left";
		case U_TURN:
			return "U-turn";
		default:
			return "";
		}
  	}
  	
  	private double getAnglesDiff(double origHeading, double newHeading) {
  		 double angleDiff = newHeading - origHeading;
  		 return (((angleDiff >= -180) && (angleDiff<=180)) ? (angleDiff) : ((angleDiff-360*Math.signum(angleDiff)))); 
  		
  	}
  	
  	private Turn getTurnKind(double angleDiff) {
  		if (angleDiff > 0 ) {
  			return getTurnRight(angleDiff);
  		}
  		return getTurnLeft(angleDiff);
  	}
  	
  	private Turn getTurnLeft(double angleDiff) {
  		if (angleDiff >= -10) {
  			return Turn.STRIGHT;
  		}else if (angleDiff >= -60) {
  			return Turn.SLIGHT_LEFT;
  		}else if (angleDiff >= -120) {
  			return Turn.LEFT;
  		}else if (angleDiff >= -179) {
  			return Turn.SHARP_LEFT;
  		}else {
  			return Turn.U_TURN;
  		}
  	}
  	
	private Turn getTurnRight (double angleDiff) {
		if (angleDiff <= 10) {
  			return Turn.STRIGHT;
  		}else if (angleDiff <= 60) {
  			return Turn.SLIGHT_RIGHT;
  		}else if (angleDiff <= 120) {
  			return Turn.RIGHT;
  		}else if (angleDiff <= 179) {
  			return Turn.SHARP_RIGHT;
  		}else {
  			return Turn.U_TURN;
  		}
	}

}
