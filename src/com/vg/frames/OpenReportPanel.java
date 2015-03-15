package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OpenReportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblFileLink, lblShowProgress1,
			lblShowProgress2;

	public OpenReportPanel(String lblPanelNameString) {

		setLayout(new BorderLayout());

		String categories[] = { "DBdetails.txt", "testreport.txt",
				"TestReport.xlsx" };
		JList list = new JList(categories);
		list.setVisibleRowCount(2);

		JPanel mainPanel = new JPanel(new BorderLayout());

		lblPanelName = new JLabel("<html><h3>"+lblPanelNameString+"</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		lblShowProgress1 = new JLabel("Execution in Progress.............");

		lblShowProgress2 = new JLabel("Execution completed.............");

		mainPanel.add(lblShowProgress1);
		mainPanel.add(lblShowProgress2);

		lblFileLink = new JLabel("<html></h2><u>Open Test Report<u></h2></html>");
		
		lblFileLink.setFont(lblFileLink.getFont().deriveFont(20.0f));
		
		// To indicate the the link is clickable
		lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.add(mainPanel, BorderLayout.NORTH);
		this.add(lblFileLink, BorderLayout.CENTER);

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