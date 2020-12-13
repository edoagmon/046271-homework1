package homework1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown by
 * RoutDirectionGUI.
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
	 * 
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame owner and
	 *          parent pnlParent
	 */
	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
		// create a modal JDialog with the an owner Frame (a modal window
		// in one that doesn't allow other windows to be active at the
		// same time).
		super(owner, "Please choose a GeoSegment", true);
		this.parent = pnlParent;
		this.getContentPane().add(this.getPanel());
		this.pack();
	}

	
	/**
	 * @effects  adds all segments
	 * 			
	 */
	private void initSegmentList() {
		DefaultListModel<GeoSegment> model =
				(DefaultListModel<GeoSegment>)(this.lstSegments.getModel());
		model.removeAllElements();
		for (GeoSegment segment : ExampleGeoSegments.segments) {
				model.addElement(segment);
		}
	}
	
	private JComponent getPanel() {
		JPanel panel = new JPanel();

		// Create list component
		lstSegments = new JList<>(new DefaultListModel<GeoSegment>());
		this.initSegmentList();
		lstSegments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrlSegments = new JScrollPane(lstSegments);
		scrlSegments.setPreferredSize(new Dimension(700, 300));
		JLabel lblSegments = new JLabel("Geo Segments:");
		lblSegments.setLabelFor(lstSegments);

		JButton btnAddSegment = new JButton("Add");
		btnAddSegment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeoSegment segment = lstSegments.getSelectedValue();
				if (segment == null) {
					System.out.println("Please select a segment\n");
					setVisible(false);
					return;
				}
				if (parent.getRoute() == null) {
					parent.addSegment(segment);
					setVisible(false);
					return;
				}
				GeoPoint endRoute = parent.getRoute().getEnd();
				if (!(endRoute.equals(segment.getP1()))){
					System.out.println("Selected segment is not valid\n");
					setVisible(false);
					return;
				}
				parent.addSegment(segment);
				setVisible(false);
			}
		});
		
		JButton btnCancelSegment = new JButton("Cancel");
		btnCancelSegment.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
		// arrange components on grid
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(lblSegments, c);
		panel.add(lblSegments);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 10;
		c.gridheight = 5;
		c.insets = new Insets(0, 0, 0, 0);
		gridbag.setConstraints(scrlSegments, c);
		panel.add(scrlSegments);

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(btnAddSegment, c);
		panel.add(btnAddSegment);
		
		c.gridx = 9;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,500,0,0);
		gridbag.setConstraints(btnCancelSegment, c);
		panel.add(btnCancelSegment);
		return panel;
		
		
		
//		private bool validSegment(GeoPoint p1,GeoPoint p1) {
			
			
			
		
	}

}
