package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OpenReportPanel extends JPanel {

	public OpenReportPanel(final JPanel cardsPanel) {

		this.cardsPanel = cardsPanel;
		initComponents();
		
	}

	private void initComponents(){
		
		super.setLayout(new BorderLayout());

		clientDBPanel = new JPanel();
		btnInitialUpgrade = new JButton("<html><b>Proceed for Initial Upgrade</b></html>");
		btnInitialUpgrade.setPreferredSize(new Dimension(220, 25));
		btnInitialUpgrade.setFont(new Font("sansserif",Font.BOLD,12));
		clientDBPanel.add(btnInitialUpgrade);
		
		mainPanel = new JPanel(new BorderLayout());

		lblPanelName = new JLabel("<html><h3>View Report</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblPanelName, BorderLayout.NORTH);

		lblShowProgress1 = new JLabel("<html><b>Report generation Success</b></html");
		lblShowProgress1.setFont(lblPanelName.getFont().deriveFont(12.0f));

		mainPanel.add(lblShowProgress1);
		
		
		workingDir = System.getProperty("user.dir");
	    filePath = workingDir+"/src/batchfiles/TestReport.xlsx";
		
		lblFileLink = new JLabel("<html><H4><u><font color='blue'>"+filePath+"</font></u></h4></html>");
		// To indicate the the link is clickable
		lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		this.add(mainPanel, BorderLayout.NORTH);
		/*JPanel mainPanel2 = new JPanel(new BorderLayout());
		mainPanel2.add(new JLabel("<html><H4>Link to Open Spreadsheet</h4></html>"),BorderLayout.CENTER);
		this.add(mainPanel2, BorderLayout.CENTER);
		*/
		this.add(lblFileLink, BorderLayout.WEST);
		this.add(clientDBPanel, BorderLayout.SOUTH);

		lblFileLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblFileLinkMouseClicked();
			}
		});
		
		 btnInitialUpgrade.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnInitialUpgradeActionPerformed();
				}
			});
	}
	
	private void btnInitialUpgradeActionPerformed(){
		  CardLayout cl = (CardLayout) cardsPanel.getLayout();
		  cl.next(cardsPanel); 
	}
	
	private void lblFileLinkMouseClicked(){
		
		try {
			Desktop.getDesktop().open(new File(filePath));
		} catch (NullPointerException e1) {
		    JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
		catch (IllegalArgumentException e1) {
		    JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
		catch (UnsupportedOperationException e1) {
		    JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e1) {
		    JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblFileLink, lblShowProgress1;
	private JButton btnInitialUpgrade;
	private JPanel cardsPanel, clientDBPanel, mainPanel;
	private String workingDir, filePath;
	
}