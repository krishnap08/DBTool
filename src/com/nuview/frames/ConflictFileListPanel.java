package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.nuview.upgrade.util.FileUtil;

public class ConflictFileListPanel extends JPanel {

	public ConflictFileListPanel() {
	}

	public ConflictFileListPanel(final JPanel cardsPanel) {
		this.cardsPanel = cardsPanel;
		initComponents();
	}
	
	private void initComponents(){
		
	super.setLayout(new BorderLayout());
	clientDBPanel = new JPanel();
	myFont = new Font("sansserif",Font.BOLD,12);
	
	/*btnGenReport = new JButton("<html><b>Generate Report</b></html>");
	btnInitialUpgrade = new JButton("<html><b>Proceed for Initial Upgrade</b></html>");
	btnGenReport.setPreferredSize(new Dimension(210, 25));
	btnInitialUpgrade.setPreferredSize(new Dimension(210, 25));
	btnGenReport.setFont(myFont);
	btnInitialUpgrade.setFont(myFont);
	clientDBPanel.add(btnGenReport);
	clientDBPanel.add(btnInitialUpgrade);*/
	
    scrollpane = new JScrollPane();
    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    fileTable = new JTable();
    
    mainPanel = new JPanel(new BorderLayout());
    subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    lblPanelName = new JLabel("<html><b>Conflict File List</b></html>");
    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
    
    lblTableName = new JLabel("<html><h3>&emsp &emsp Custom Old File List</h3></html>");
    
    mainPanel.add(lblPanelName,BorderLayout.NORTH);
    //mainPanel.add(lblTableName);
    subPanel.add(scrollpane);
    
	super.add(mainPanel,BorderLayout.NORTH);
	super.add(subPanel, BorderLayout.CENTER);
	//super.add(clientDBPanel, BorderLayout.SOUTH);
    
  /*  btnGenReport.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {

		btnGenReportActionPerformed();
		}
	});
	 
	 btnInitialUpgrade.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInitialUpgradeActionPerformed();
					
			}
		});*/
	 
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
	 
	 fileTable.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent me) {
			 JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		        	System.out.println("double click event fired....Row:: "+row+" Value :: "+dataModel.getValueAt(row, 0).toString());
		        	try {
		        		
		        		String bcPath = "C:\\Program Files (x86)\\Beyond Compare 4\\BComp.exe";
		        		
		        		String workingDir = System.getProperty("user.dir")+"/output/conflict/";
		        	    String srcFile = workingDir+"Agent_CmpDir111.txt";
		        	    
		        		String trgFile = workingDir+dataModel.getValueAt(row, 0).toString();
		        		
						Runtime.getRuntime().exec(bcPath+" "+srcFile+" "+trgFile);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	 
	}
	
	
	/*private void btnGenReportActionPerformed(){
		
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
	private void btnInitialUpgradeActionPerformed(){
		
		 ClientDetailsBean.initialMergeButtonSelected = true;
		 DBUpgradeMain.dbUpgradeMain.initPanels(false);
		 System.out.println("FilelistPanel......");
		
	}*/
	
	public void getFileList(){
		FileUtil fileUtil = new FileUtil();
		
		Map<String, String> fileMap = fileUtil.getFileMap("conflict");
		dataModel = fileUtil.toTableModel(fileMap);
		fileTable.setModel(dataModel);
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		Dimension d = fileTable.getPreferredSize();
		scrollpane.setViewportView(fileTable);
		scrollpane.setPreferredSize(new Dimension(475,400));
	}
	
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane;
	private JLabel lblPanelName, lblTableName;
	private JTable fileTable;
	private TableModel dataModel;
	//private JButton btnGenReport, btnInitialUpgrade;
	private JPanel clientDBPanel, mainPanel, subPanel, cardsPanel;
	private Font myFont;
}