package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

public class InitialMergeFileListPanel extends JPanel {

	public InitialMergeFileListPanel() {
	}

	public InitialMergeFileListPanel(final JPanel cardsPanel) {
		this.cardsPanel = cardsPanel;
		initComponents();
	}
	
	private void initComponents(){
		
	super.setLayout(new BorderLayout());
	clientDBPanel = new JPanel();
	myFont = new Font("sansserif",Font.BOLD,12);
	
	btnExecuteAutoMerge = new JButton("<html><b>Execute Automerge</b></html>");
	btnExecuteAutoMerge.setPreferredSize(new Dimension(210, 25));
	btnExecuteAutoMerge.setFont(myFont);
	clientDBPanel.add(btnExecuteAutoMerge);
	
    customOldScrollpane = new JScrollPane();
    customOldScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    stdOldScrollpane = new JScrollPane();
    stdOldScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
   
    stdNewScrollpane = new JScrollPane();
    stdNewScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
   
    mainPanel = new JPanel(new BorderLayout());
    subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    lblPanelName = new JLabel("<html><b>Review File List</b></html>");
    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
    
    lblTableName = new JLabel("<html><h3>Custom Old File List</h3></html>");
    lblTableName2 = new JLabel("<html><h3>Standard Old File List</h3></html>");
    lblTableName3 = new JLabel("<html><h3>Standard New File List</h3></html>");
    
    mainPanel.add(lblPanelName,BorderLayout.NORTH);
    
    subPanel.add(lblTableName);
    subPanel.add(customOldScrollpane);
    
    subPanel.add(lblTableName2);
    subPanel.add(stdOldScrollpane);
    
    subPanel.add(lblTableName3);
    subPanel.add(stdNewScrollpane);

    //super.add(mainPanel,BorderLayout.NORTH);
	super.add(subPanel, BorderLayout.CENTER);
	super.add(clientDBPanel, BorderLayout.SOUTH);
    
    btnExecuteAutoMerge.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {

		btnExecuteAutoMergeActionPerformed();
		}
	});
	 
	 super.addComponentListener(new ComponentListener() {
		
		@Override
		public void componentShown(ComponentEvent e) {
			System.out.println("Component shown....");
			getFileList();
			validate();
		}
		
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	
	private void btnExecuteAutoMergeActionPerformed(){
		
		try{
			//genrateReport();
			}catch(Exception e1){JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
			
			boolean testCompleted = true;
			if (testCompleted) {
				ClientDetailsBean.generateReportSuccessFlag = true;
				CardLayout cl = (CardLayout) cardsPanel.getLayout();
				cl.next(cardsPanel);
			}
	}
	
	public void getFileList(){
		FileUtil fileUtil = new FileUtil();
		
		customOldFileTable = new JTable(fileUtil.toTableModel(fileUtil.getFileMap("custom_old")));
		customOldFileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		customOldScrollpane.setViewportView(customOldFileTable);
		customOldScrollpane.setPreferredSize(new Dimension(475,80));
		
		stdOldFileTable = new JTable(fileUtil.toTableModel(fileUtil.getFileMap("standard_old")));
		stdOldFileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		stdOldScrollpane.setViewportView(stdOldFileTable);
		stdOldScrollpane.setPreferredSize(new Dimension(475,80));
		
		stdNewFileTable = new JTable(fileUtil.toTableModel(fileUtil.getFileMap("standard_new")));
		stdNewFileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		stdNewScrollpane.setViewportView(stdNewFileTable);
		stdNewScrollpane.setPreferredSize(new Dimension(475,80));
		
//stdOldScrollpane
	}
	
	private static final long serialVersionUID = 1L;
	private JScrollPane customOldScrollpane, stdOldScrollpane, stdNewScrollpane;
	private JLabel lblPanelName, lblTableName, lblTableName2, lblTableName3;
	private JTable customOldFileTable, stdOldFileTable, stdNewFileTable;
	private JButton btnExecuteAutoMerge;
	private JPanel clientDBPanel, mainPanel, subPanel, cardsPanel;
	private Font myFont;
}