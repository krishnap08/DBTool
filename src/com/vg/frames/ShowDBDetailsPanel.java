package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.TextMerge;
import com.nuview.upgrade.util.ConfigPropertyUtil;

/**
 * <p>
 * ShowPanel observes the data Object Student. <br>
 * When notified, it updates the Label to present the name.</br>
 * </p>
 */
public class ShowDBDetailsPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel oLabel, lblPanelName;
	private JButton btnExec;
	private ConfigPropertyUtil propUtil = new ConfigPropertyUtil();
	
	public ShowDBDetailsPanel(final JPanel cards,
			final JButton parentNextButton, String lblPanelNameString) {

		setLayout(new BorderLayout());

		JPanel clientDBPanel = new JPanel();

		oLabel = new JLabel();

		btnExec = new JButton("Execute");

		// btnExec.setBounds(180, 155, 100, 25);//setSize(new Dimension(100,
		// 25));
		btnExec.setPreferredSize(new Dimension(200, 25));

		clientDBPanel.add(btnExec);

		lblPanelName = new JLabel("<html><h3>" + lblPanelNameString
				+ "</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		// this.add(lblPanelName, BorderLayout.NORTH);
		// mainPanel.add(oLabel,BorderLayout.CENTER);

		this.add(mainPanel, BorderLayout.NORTH);

		this.add(oLabel, BorderLayout.CENTER);

		this.add(clientDBPanel, BorderLayout.SOUTH);

		btnExec.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executeReport();

				// Enable Next button once Test is Success
				boolean testCompleted = true;
				if (testCompleted) {
					// parentNextButton.setEnabled(true);

					CardLayout cl = (CardLayout) cards.getLayout();
					cl.next(cards);
				}
			}
		});
	}

	private void executeReport() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(this,
				"Would You Like to Generate Report?", "Warning", dialogButton);

		if (dialogResult == JOptionPane.YES_OPTION) {			
			String prm_sArgs[] = new String[2];
			prm_sArgs[0] = "download";
			prm_sArgs[1] = "output/";
			
			try {
				propUtil.writeToFile(prm_sArgs[1]);
				TextMerge.main(prm_sArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*String path = getClass().getResource("test.bat").toString();
			System.out.println("path :: " + path);
			String[] command = { "cmd.exe", "/C", "Start", path };
			try {
				Process p = Runtime.getRuntime().exec(command);
			} catch (IOException e1) {

				e1.printStackTrace();
			}*/
		}
	}

	@Override
	public void update(Observable oObservable, Object oObject) {
		// we do not figure who kicked us, we just repaint.
		ClientDetailsBean cleintDBDetailsBean = ((ClientDetailsBean) oObservable); // cast

		String selectedReport = "";

		if (cleintDBDetailsBean.isMergeFlag()) {
			selectedReport = "Merge Analysis";
		} else if (cleintDBDetailsBean.isInitialMergeFlag()) {
			selectedReport = "Initial Upgrade";
		} else if (cleintDBDetailsBean.isPreProdFlag()) {
			selectedReport = "Pre-Production Upgrade";
		}

		StringBuilder sbr = new StringBuilder();

		sbr.append("<html> <body>");

		sbr.append("<h3>" + selectedReport + "</h3>");

		sbr.append("<h4> Client DB details </h4>");

		sbr.append("<b>HostName : " + cleintDBDetailsBean.getHostName()
				+ "</b><br>");

		sbr.append("<b>DB Name  : " + cleintDBDetailsBean.getDbName()
				+ "</b><br>");

		sbr.append("<b>Port     : " + cleintDBDetailsBean.getPortStr()
				+ "</b><br>");

		sbr.append("<b>Username : " + cleintDBDetailsBean.getUserName()
				+ "</b>");

		sbr.append("<h4>Standard old vesrion DB details </h4>");

		sbr.append("<b>HostName : " + cleintDBDetailsBean.getOldHostName()
				+ "</b><br>");

		sbr.append("<b>DB Name  : " + cleintDBDetailsBean.getOldDBName()
				+ "</b><br>");

		sbr.append("<b>Port     : " + cleintDBDetailsBean.getOldPortStr()
				+ "</b><br>");

		sbr.append("<b>Username : " + cleintDBDetailsBean.getOldUserName()
				+ "</b>");

		sbr.append("<h4>Standard new vesrion DB details</h4>");

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
	}

}