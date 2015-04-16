package com.nuview.frames;

/*
 * Main container class holds control panel and all other sub panels will be initiated here.
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.nuview.model.ClientDetailsBean;
import com.nuview.model.ObjectsToMergeBean;

public class DBUpgradeMain {

	
	public DBUpgradeMain(){
		
		initComponents();
	}
	
	private void initComponents() {

		frame = new JFrame("NuViewHR®");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setResizable(false);
		
		try{
		// Create and set up the content pane.
		addComponentToPane(frame.getContentPane());
		}catch(Exception e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addComponentToPane(Container pane) {

		controlPanel = new JPanel();
		cardsPanel = new JPanel();
		welcomePanel = new WelcomePanel();
		clientDetailsBean = new ClientDetailsBean();
		objectsToMergeBean = new ObjectsToMergeBean();
		
		myFont = new Font("sansserif",Font.BOLD,12);
		
		cardsPanel.setLayout(new CardLayout());
		
		prevButton = new JButton(new AbstractAction("\u22b2Prev") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardsPanel.getLayout();
				cl.previous(cardsPanel);
			}
		});
		
		nextButton = new JButton(new AbstractAction("Next\u22b3") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardsPanel.getLayout();
				cl.next(cardsPanel);
			}
		});

		cancelButton = new JButton(new AbstractAction("Cancel") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(controlPanel,
						"Do you really want to Cancel?", "Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		
		prevButton.setFont(myFont);
		nextButton.setFont(myFont);
		cancelButton.setFont(myFont);
		
		// Add buttons to control panel
		controlPanel.add(prevButton);
		controlPanel.add(nextButton);
		controlPanel.add(cancelButton);

		// Create the list of panels.
		reportMenuFormPanel = new ReportMenuForm(clientDetailsBean, nextButton);
		
		//objectsToMergeBean.addObserver(objectsToMergeForm);

		//Add panels to Cards layout
		cardsPanel.add(createPanel(welcomePanel, "welcomePanel"), "welcomePanel");
		cardsPanel.add(createPanel(reportMenuFormPanel, "reportMenuFormPanel"),"reportMenuFormPanel");
		/*cardsPanel.add(createPanel(clientDBDetailsFormPanel, "clientDBDetailsFormPanel"),"clientDBDetailsFormPanel");
		cardsPanel.add(createPanel(stdOldVerDBDetailsFormPanel, "stdOldVerDBDetailsFormPanel"),"stdOldVerDBDetailsFormPanel");
		cardsPanel.add(createPanel(stdNewVerDBDetailsFormPanel, "stdNewVerDBDetailsFormPanel"),"stdNewVerDBDetailsFormPanel");
		cardsPanel.add(createPanel(showDBDetailsPanel, "showDBDetailsPanel"),"showDBDetailsPanel");
		cardsPanel.add(createPanel(objectsToMergeForm, "objectsToMergeForm"),"objectsToMergeForm");
		cardsPanel.add(createPanel(fileListPanel, "fileListPanel"),"fileListPanel");
		cardsPanel.add(createPanel(openReportPanel, "openReportPanel"), "openReportPanel");
		cardsPanel.add(createPanel(intialMergePanel, "intialMergePanel"), "intialMergePanel");*/
		
		pane.add(controlPanel, BorderLayout.SOUTH);
		pane.add(cardsPanel, BorderLayout.CENTER);
	}
	
	public void initPanels(boolean mergeFlag){
		
		ClientDetailsBean.clientDetailsFlag = false;
		ClientDetailsBean.downLoadSelectedFlag = false;
		ClientDetailsBean.generateReportSuccessFlag = false;
		ClientDetailsBean.newClientDetailsFlag = false;
		ClientDetailsBean.oldClientDetailsFlag = false;
		
		if(mergeFlag){
		System.out.println("initPanels()..."+mergeFlag);
			try{
				cardsPanel.remove(intialMergePanel);
				cardsPanel.revalidate();
				cardsPanel.repaint();
			}catch(NullPointerException e){
				//e.printStackTrace();
				System.out.println("TO DO :: NullPointerException");
			}
			
		clientDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c1");
	    stdOldVerDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c2");
		stdNewVerDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c3");
		showDBDetailsPanel = new ShowDBDetailsPanel();
		fileListPanel = new FileListPanel(cardsPanel);
		objectsToMergeForm = new ObjectsToMergeForm(objectsToMergeBean, cardsPanel, nextButton);
		openReportPanel = new OpenReportPanel(cardsPanel);
		
		clientDetailsBean.addObserver(showDBDetailsPanel);
		
		cardsPanel.add(createPanel(clientDBDetailsFormPanel, "clientDBDetailsFormPanel"),"clientDBDetailsFormPanel");
		cardsPanel.add(createPanel(stdOldVerDBDetailsFormPanel, "stdOldVerDBDetailsFormPanel"),"stdOldVerDBDetailsFormPanel");
		cardsPanel.add(createPanel(stdNewVerDBDetailsFormPanel, "stdNewVerDBDetailsFormPanel"),"stdNewVerDBDetailsFormPanel");
		cardsPanel.add(createPanel(showDBDetailsPanel, "showDBDetailsPanel"),"showDBDetailsPanel");
		cardsPanel.add(createPanel(objectsToMergeForm, "objectsToMergeForm"),"objectsToMergeForm");
		cardsPanel.add(createPanel(fileListPanel, "fileListPanel"),"fileListPanel");
		cardsPanel.add(createPanel(openReportPanel, "openReportPanel"), "openReportPanel");
		
		}else{
			System.out.println("initPanels()..."+mergeFlag);
			try{
				cardsPanel.remove(clientDBDetailsFormPanel);
				cardsPanel.remove(stdOldVerDBDetailsFormPanel);
				cardsPanel.remove(stdNewVerDBDetailsFormPanel);
				cardsPanel.remove(showDBDetailsPanel);
				cardsPanel.remove(objectsToMergeForm);
				cardsPanel.remove(fileListPanel);
				cardsPanel.remove(openReportPanel);
				cardsPanel.revalidate();
				cardsPanel.repaint();
			}catch(NullPointerException e){
				//e.printStackTrace();
				System.out.println("TO DO :: NullPointerException");
			}
			
			intialMergePanel = new JPanel();
			intialMergePanel.add(new JLabel("<html><h3>Welcome to Initial Merge</h3</html>"));
			
			initialMergeFileListPanel = new InitialMergeFileListPanel(cardsPanel);
			conflictFileListPanel = new ConflictFileListPanel(cardsPanel);
			
			cardsPanel.add(createPanel(intialMergePanel, "intialMergePanel"), "intialMergePanel");
			cardsPanel.add(createPanel(initialMergeFileListPanel, "initialMergeFileListPanel"), "initialMergeFileListPanel");
			cardsPanel.add(createPanel(conflictFileListPanel, "conflictFileListPanel"), "conflictFileListPanel");
			
			if(ClientDetailsBean.initialMergeButtonSelected){
			reportMenuFormPanel.getInitialMergeButton().setSelected(true);
			clientDetailsBean.setMergeFlag(false);
			clientDetailsBean.setInitialMergeFlag(true);
			
			CardLayout cl = (CardLayout) cardsPanel.getLayout();
			cl.show(cardsPanel, "intialMergePanel");
			ClientDetailsBean.initialMergeButtonSelected = false;
			}
		}
		
		return;
	}

	public JPanel createPanel(JPanel jPanel, String panelName) {

		jPanel.putClientProperty(PANEL_PROPERTY, panelName);
		jPanel.addAncestorListener(getPanelName);
		return jPanel;
	}

	private final AncestorListener getPanelName = new AncestorListener() {
		@Override
		public void ancestorAdded(AncestorEvent event) {
			Component source = (JComponent) event.getSource();
			String name = (String) ((JComponent) source).getClientProperty(PANEL_PROPERTY);
			updateControlPanel(name);
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
		}
	};
	
	
	private void updateControlPanel(String name){
		
		if ("welcomePanel".equals(name)) {
			prevButton.setEnabled(false);
			nextButton.setEnabled(true);
		} else {
			prevButton.setEnabled(true);
		}

		if ("intialMergePanel".equals(name)) {
			nextButton.setEnabled(true);
		}

		if ("openReportPanel".equals(name)) {
			nextButton.setEnabled(false);
			//prevButton.setEnabled(false);
		}
		
		if ("reportMenuFormPanel".equals(name)) {

			System.out.println("report flag... "
					+ ClientDetailsBean.reportSelectedFlag+ "  Merge report: "+clientDetailsBean.isMergeFlag());
			if (ClientDetailsBean.reportSelectedFlag) {
				
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}

		if ("clientDBDetailsFormPanel".equals(name)) {

			System.out.println("Test flag... "
					+ ClientDetailsBean.clientDetailsFlag);
			if (ClientDetailsBean.clientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		if ("stdOldVerDBDetailsFormPanel".equals(name)) {

			System.out.println("old Test flag... "
					+ ClientDetailsBean.oldClientDetailsFlag);
			if (ClientDetailsBean.oldClientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		if ("stdNewVerDBDetailsFormPanel".equals(name)) {

			System.out.println("new Test flag... "
					+ ClientDetailsBean.newClientDetailsFlag);
			if (ClientDetailsBean.newClientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("showDBDetailsPanel".equals(name)) {
			nextButton.setEnabled(true);
		}
		
		if ("objectsToMergeForm".equals(name)) {

			if (ClientDetailsBean.downLoadSelectedFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("fileListPanel".equals(name)) {

			if (ClientDetailsBean.generateReportSuccessFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("initialMergeFileListPanel".equals(name)) {

			if (ClientDetailsBean.generateReportSuccessFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("conflictFileListPanel".equals(name)) {

			if (ClientDetailsBean.generateReportSuccessFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dbUpgradeMain = new DBUpgradeMain();
			}
		});
	}
	
	
	private JFrame frame;
	private JPanel cardsPanel, controlPanel, intialMergePanel, welcomePanel;
	private static final String PANEL_PROPERTY = "PANEL_PROPERTY";
	private JButton prevButton, nextButton, cancelButton;
	private Font myFont;
	private ClientDetailsBean clientDetailsBean;
	private ObjectsToMergeBean objectsToMergeBean;
	private ReportMenuForm reportMenuFormPanel;
	private CleintDBDetailsForm clientDBDetailsFormPanel, stdOldVerDBDetailsFormPanel, stdNewVerDBDetailsFormPanel;
	private ShowDBDetailsPanel showDBDetailsPanel;
	private FileListPanel fileListPanel;
	private ObjectsToMergeForm objectsToMergeForm;
	private OpenReportPanel openReportPanel;
	private InitialMergeFileListPanel initialMergeFileListPanel;
	private ConflictFileListPanel conflictFileListPanel;
	public static DBUpgradeMain dbUpgradeMain;
}
