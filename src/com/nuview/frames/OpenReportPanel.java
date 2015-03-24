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
		btnInitialUpgrade = new JButton("Proceed for Initial Upgrade");
		btnInitialUpgrade.setPreferredSize(new Dimension(200, 25));
		clientDBPanel.add(btnInitialUpgrade);
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		lblPanelName = new JLabel("<html><h3>"+lblPanelNameString+"</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		lblShowProgress1 = new JLabel("Report generation Success");


		mainPanel.add(lblShowProgress1);
		
		String filePath = "";
		try {
		 filePath = getClass().getResource("/batchfiles/TestReport.xlsx").toURI().toString();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		
		lblFileLink = new JLabel("<html></h2><u>"+filePath+"<u></h2></html>");
		
		lblFileLink.setFont(lblFileLink.getFont().deriveFont(20.0f));
		
		// To indicate the the link is clickable
		lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.add(mainPanel, BorderLayout.NORTH);
		this.add(lblFileLink, BorderLayout.CENTER);
		this.add(clientDBPanel, BorderLayout.SOUTH);

		// lblFileLink.setEnabled(false);

		lblFileLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().open(
							new File(getClass().getResource(
									"/batchfiles/TestReport.xlsx").toURI()));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
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