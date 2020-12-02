package homework1;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JList;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown
 * by RoutDirectionGUI.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class GeoSegmentsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// the RouteDirectionsGUI that this JDialog was opened from
	private RouteFormatterGUI parent;
	
	// a control contained in this 
	private JList<GeoSegment> lstSegments;
	
	/**
	 * Creates a new GeoSegmentsDialog JDialog.
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
	 * 			owner and parent pnlParent
	 */
	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
		// create a modal JDialog with the an owner Frame (a modal window
		// in one that doesn't allow other windows to be active at the
		// same time).
		super(owner, "Please choose a GeoSegment", true);
		
		this.parent = pnlParent;
		
		// TODO Write the body of this method
	}
}
