package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OpenReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblFileLink, lblShowProgress1;
	private JButton btnInitialUpgrade;
	
	public OpenReportPanel(final JPanel cards, String lblPanelNameString) {

		setLayout(new BorderLayout());

		JPanel clientDBPanel = new JPanel();
		btnInitialUpgrade = new JButton("<html><b>Proceed for Initial Upgrade</b></html>");
		btnInitialUpgrade.setPreferredSize(new Dimension(220, 25));
		clientDBPanel.add(btnInitialUpgrade);
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		lblPanelName = new JLabel("<html><h3>"+lblPanelNameString+"</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		lblShowProgress1 = new JLabel("<html><b>Report generation Success</b></html");
		lblShowProgress1.setFont(lblPanelName.getFont().deriveFont(12.0f));

		mainPanel.add(lblShowProgress1);
		
		
		String workingDir = System.getProperty("user.dir");
		final String path = workingDir+"/src/batchfiles/TestReport.xlsx";
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html> <body>");
		sb.append("<div style=\"width:300px;font-size:12px\">");
		sb.append("<h3>"+path+"</h3> <br> <br>");
		sb.append("</div>");
		sb.append("");
		sb.append("</body>");
		sb.append("</html>");
		
		lblFileLink = new JLabel("<html><H4><u><font color='blue'>"+path+"</font></u></h4></html>");
		//lblFileLink = new JLabel(sb.toString());
		
		//lblFileLink.setFont(lblFileLink.getFont().deriveFont(16.0f)); 
		
		// To indicate the the link is clickable
		lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.add(mainPanel, BorderLayout.NORTH);
		/*JPanel mainPanel2 = new JPanel(new BorderLayout());
		mainPanel2.add(new JLabel("<html><H4>Link to Open Spreadsheet</h4></html>"),BorderLayout.CENTER);
		this.add(mainPanel2, BorderLayout.CENTER);
		*/
		this.add(lblFileLink, BorderLayout.WEST);
		this.add(clientDBPanel, BorderLayout.SOUTH);

		// lblFileLink.setEnabled(false);

		lblFileLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().open(
							new File(path));
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
		
		 btnInitialUpgrade.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//genrateReport();

					boolean testCompleted = true;
					if (testCompleted) {
						JPanel newPanel = new JPanel();
						newPanel.add(new JLabel("<html><h3>Welcome to Initial Merge</h3</html>"));
						newPanel.putClientProperty("PANEL_PROPERTY", "FINAL_PANNEL");
						 cards.add(newPanel, "FINAL_PANNEL");
						
						 CardLayout cl = (CardLayout) cards.getLayout();
						  cl.show(newPanel.getParent(), "FINAL_PANNEL");
						  //CardLayout cl = (CardLayout) cards.getLayout();
						  //cl.next(cards);  
						  
					}
				}
			});
		 
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				/*
				 * System.out.println("Display progress");
				 * lblShowProgress1.setEnabled(true);
				 * lblShowProgress2.setEnabled(false); // thread to sleep for
				 * 1000 milliseconds try { Thread.sleep(10000); } catch
				 * (InterruptedException e1) { e1.printStackTrace(); }
				 * lblShowProgress1.setEnabled(false);
				 * lblShowProgress2.setEnabled(true); //
				 * lblFileLink.setEnabled(true);
				 */

			}
		});

	}

}