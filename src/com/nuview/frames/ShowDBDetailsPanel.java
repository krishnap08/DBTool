package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
//import com.TextMerge;
import com.nuview.upgrade.util.ConfigPropertyUtil;

/**
 * <p>
 * ShowPanel observes the data Object Student. <br>
 * When notified, it updates the Label to present the name.</br>
 * </p>
 */
public class ShowDBDetailsPanel extends JPanel implements Observer {
	
	public ShowDBDetailsPanel() {
		initComponents();
	}

	private void initComponents(){
		
		super.setLayout(new BorderLayout());
		oLabel = new JLabel();

		lblPanelName = new JLabel("<html><h3>All DB Version Details</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		lblPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;

		lblPanel.add(lblPanelName);

		lblPanel.add(oLabel, gbc);

		super.add(lblPanel, BorderLayout.NORTH);
		super.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
	}

	@Override
	public void update(Observable oObservable, Object oObject) {
		// we do not figure who kicked us, we just repaint.
		ClientDetailsBean cleintDBDetailsBean = ((ClientDetailsBean) oObservable); // cast

		/*String selectedReport = "";

		if (cleintDBDetailsBean.isMergeFlag()) {
			selectedReport = "Merge Analysis";
		} else if (cleintDBDetailsBean.isInitialMergeFlag()) {
			selectedReport = "Initial Upgrade";
		} else if (cleintDBDetailsBean.isPreProdFlag()) {
			selectedReport = "Pre-Production Upgrade";
		}*/

		StringBuilder sbr = new StringBuilder();

		sbr.append("<html> <body>");

		//sbr.append("<h3>" + selectedReport + "</h3>");

		sbr.append("<h3> Client DB details </h3>");

		sbr.append("<b>HostName : " + cleintDBDetailsBean.getHostName()
				+ "</b><br>");

		sbr.append("<b>DB Name  : " + cleintDBDetailsBean.getDbName()
				+ "</b><br>");

		sbr.append("<b>Port     : " + cleintDBDetailsBean.getPortStr()
				+ "</b><br>");

		sbr.append("<b>Username : " + cleintDBDetailsBean.getUserName()
				+ "</b>");

		sbr.append("<h3>Standard old vesrion DB details </h3>");

		sbr.append("<b>HostName : " + cleintDBDetailsBean.getOldHostName()
				+ "</b><br>");

		sbr.append("<b>DB Name  : " + cleintDBDetailsBean.getOldDBName()
				+ "</b><br>");

		sbr.append("<b>Port     : " + cleintDBDetailsBean.getOldPortStr()
				+ "</b><br>");

		sbr.append("<b>Username : " + cleintDBDetailsBean.getOldUserName()
				+ "</b>");

		sbr.append("<h3>Standard new vesrion DB details</h3>");

		sbr.append("<b>HostName : " + cleintDBDetailsBean.getNewHostName()
				+ "</b><br>");

		sbr.append("<b>DB Name  : " + cleintDBDetailsBean.getNewDBName()
				+ "</b><br>");

		sbr.append("<b>Port     : " + cleintDBDetailsBean.getNewPortStr()
				+ "</b><br>");

		sbr.append("<b>Username : " + cleintDBDetailsBean.getNewUserName()
				+ "</b>");

		sbr.append("</body></html>");

		propUtil.setProperty("ClientDBServer",cleintDBDetailsBean.getHostName());
		propUtil.setProperty("ClientDBPort",cleintDBDetailsBean.getPortStr());
		propUtil.setProperty("ClientDBName",cleintDBDetailsBean.getDbName());
		propUtil.setProperty("ClientUserName",cleintDBDetailsBean.getUserName());
		propUtil.setProperty("ClientPassword",cleintDBDetailsBean.getPassword());

		propUtil.setProperty("OldStandardDBServer",cleintDBDetailsBean.getOldHostName());
		propUtil.setProperty("OldStandardDBPort",cleintDBDetailsBean.getOldPortStr());
		propUtil.setProperty("OldStandardDBName",cleintDBDetailsBean.getOldDBName());
		propUtil.setProperty("OldStandardUserName",cleintDBDetailsBean.getOldUserName());
		propUtil.setProperty("OldStandardPassword",cleintDBDetailsBean.getOldPassword());
				
		propUtil.setProperty("NewStandardDBServer",cleintDBDetailsBean.getNewHostName());
		propUtil.setProperty("NewStandardDBPort",cleintDBDetailsBean.getNewPortStr());
		propUtil.setProperty("NewStandardDBName",cleintDBDetailsBean.getNewDBName());
		propUtil.setProperty("NewStandardUserName",cleintDBDetailsBean.getNewUserName());
		propUtil.setProperty("NewStandardPassword",cleintDBDetailsBean.getNewPassword());
		
		this.oLabel.setText(sbr.toString());

		/*
		 * System.out.println(
		 * "#################### Client DB Details #########################");
		 * System.out.println(cleintDBDetailsBean.toString());
		 * System.out.println
		 * ("################################################################");
		 */
	}//Update method closed
	
	private static final long serialVersionUID = 1L;
	private JLabel oLabel, lblPanelName;
	private ConfigPropertyUtil propUtil = new ConfigPropertyUtil();
	private JPanel mainPanel, lblPanel;

}