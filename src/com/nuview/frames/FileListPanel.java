package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.upgrade.util.FileUtil;

public class FileListPanel extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollpane, scrollpane2, scrollpane3;
	private JLabel lblPanelName;
	private JTable fileTable;
	private JButton btnGenReport, btnInitialUpgrade;
	
	
	public FileListPanel(final JPanel cards, String lblPanelNameString) {

	setLayout(new BorderLayout());

	JPanel clientDBPanel = new JPanel();
	btnGenReport = new JButton("Generate Report");
	btnGenReport.setPreferredSize(new Dimension(200, 25));
	clientDBPanel.add(btnGenReport);
	
	btnInitialUpgrade = new JButton("Proceed for Initial Upgrade");
	btnInitialUpgrade.setPreferredSize(new Dimension(200, 25));
	clientDBPanel.add(btnInitialUpgrade);
	
	
	
	FileUtil fileUtil = new FileUtil();
	
	Map<String, String> fileMap = fileUtil.getFileMap("custom_old");
	fileTable = new JTable(fileUtil.toTableModel(fileMap));
	
	fileTable.setBounds(new Rectangle(10, 10, 395, 250));
	fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	
    scrollpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   // scrollpane.add(fileTable);
  
    
   // scrollpane = new JScrollPane(fileTable);
    scrollpane.setBounds(10, 100, 200, 80);
    
    /*JViewport viewport = new JViewport();
    viewport.setView(fileTable);
    viewport.setPreferredSize(fileTable.getPreferredSize());
   */
    scrollpane.setRowHeaderView(fileTable);
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel subPanel = new JPanel(new BorderLayout());
    
    lblPanelName = new JLabel("<html><b>" + lblPanelNameString+ "</b></html>");
    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
    
    mainPanel.add(lblPanelName,BorderLayout.NORTH);
    
    mainPanel.add(new JLabel("<html><h3>Custom Old File List</h3></html>"));
    
    subPanel.add(scrollpane);
    
	 this.add(mainPanel,BorderLayout.NORTH);
	 this.add(subPanel, BorderLayout.CENTER);
	 this.add(clientDBPanel, BorderLayout.SOUTH);
    
	 
	 btnGenReport.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {

			//genrateReport();

			boolean testCompleted = true;
			if (testCompleted) {
				ClientDetailsBean.generateReportSuccessFlag = true;
				/* OpenReportPanel openReportPanel = new OpenReportPanel(cards, "View Report"); 
				 
				 openReportPanel.putClientProperty("PANEL_PROPERTY", "FINAL_PANNEL");
				 cards.add(openReportPanel, "FINAL_PANNEL");
				
				 CardLayout cl = (CardLayout) cards.getLayout();
				  cl.show(openReportPanel.getParent(), "FINAL_PANNEL");*/
				
				CardLayout cl = (CardLayout) cards.getLayout();
				  cl.next(cards);
				
			}
		}
	});
	 
	 btnInitialUpgrade.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//genrateReport();

				boolean testCompleted = true;
				if (testCompleted) {
					JPanel newPanel = new JPanel();
					newPanel.add(new JLabel("<html><h3>Welcome to Initial Merge</h3></html>"));
					newPanel.putClientProperty("PANEL_PROPERTY", "FINAL_PANNEL");
					 cards.add(newPanel, "FINAL_PANNEL");
					
					 CardLayout cl = (CardLayout) cards.getLayout();
					  cl.show(newPanel.getParent(), "FINAL_PANNEL");
				}
			}
		});
	 
  }
  
}