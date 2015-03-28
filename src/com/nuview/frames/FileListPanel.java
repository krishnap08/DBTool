package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	JScrollPane scrollpane;
	private JLabel lblPanelName;
	private JTable fileTable;
	private JButton btnGenReport, btnInitialUpgrade;
	
	
	public FileListPanel(final JPanel cards, String lblPanelNameString) {

	setLayout(new BorderLayout());
		

	JPanel clientDBPanel = new JPanel();
	btnGenReport = new JButton("<html><b>Generate Report</b></html>");
	btnGenReport.setPreferredSize(new Dimension(200, 25));
	btnGenReport.setFont(new Font("sansserif",Font.BOLD,12));
	clientDBPanel.add(btnGenReport);
	
	btnInitialUpgrade = new JButton("<html><b>Proceed for Initial Upgrade</b></html>");
	btnInitialUpgrade.setPreferredSize(new Dimension(200, 25));
	btnInitialUpgrade.setFont(new Font("sansserif",Font.BOLD,12));
	clientDBPanel.add(btnInitialUpgrade);
	
    scrollpane = new JScrollPane();
    //scrollpane.setBounds(10, 100, 200, 80);
    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    //get the file List
  	//getFileList();
     
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
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

			//try{
			//genrateReport();
			//}catch(Exception e1){JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
			
			boolean testCompleted = true;
			if (testCompleted) {
				ClientDetailsBean.generateReportSuccessFlag = true;
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.next(cards);
			}
		}
	});
	 
	 btnInitialUpgrade.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 CardLayout cl = (CardLayout) cards.getLayout();
					  cl.show(getParent(), "FINAL_PANNEL");
				
			}
		});
	 
  }
  
	public FileListPanel() {
	}

	public void getFileList(){
		FileUtil fileUtil = new FileUtil();
		
		Map<String, String> fileMap = fileUtil.getFileMap("custom_old");
		fileTable = new JTable(fileUtil.toTableModel(fileMap));
		
		//fileTable.setBounds(new Rectangle(10, 10, 395, 250));
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Dimension d = fileTable.getPreferredSize();
		//fileTable.setPreferredScrollableViewportSize(new Dimension(d.width,fileTable.getRowHeight()*fileTable.getRowCount()+1));
		scrollpane.setViewportView(fileTable);
		
	    //scrollpane.setPreferredSize(new Dimension(d.width,fileTable.getRowHeight()*fileTable.getRowCount()+1));
		scrollpane.setPreferredSize(new Dimension(350,150));
		
	    System.out.println("Scrollpane size:: "+scrollpane.size().height+"   "+scrollpane.size().width);
	}
	
	
	
}