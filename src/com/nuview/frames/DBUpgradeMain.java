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
			JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addComponentToPane(Container pane) {

		controlPanel = new JPanel();
		cards = new JPanel();
		intialMergePanel = new JPanel();
		welcomePanel = new WelcomePanel();
		clientDetailsBean = new ClientDetailsBean();
		objectsToMergeBean = new ObjectsToMergeBean();
		
		myFont = new Font("sansserif",Font.BOLD,12);
		
		cards.setLayout(new CardLayout());
		
		prevButton = new JButton(new AbstractAction("\u22b2Prev") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.previous(cards);
			}
		});
		
		nextButton = new JButton(new AbstractAction("Next\u22b3") {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.next(cards);
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
		clientDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c1");
	    stdOldVerDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c2");
		stdNewVerDBDetailsFormPanel = new CleintDBDetailsForm(clientDetailsBean, nextButton, "c3");
		showDBDetailsPanel = new ShowDBDetailsPanel(cards, nextButton);
		fileListPanel = new FileListPanel(cards, "Review Files List");
		objectsToMergeForm = new ObjectsToMergeForm(objectsToMergeBean, cards, nextButton);
		openReportPanel = new OpenReportPanel(cards, "View Report");
		intialMergePanel.add(new JLabel("<html><h3>Welcome to Initial Merge</h3</html>"));

		
		clientDetailsBean.addObserver(showDBDetailsPanel);
		//objectsToMergeBean.addObserver(objectsToMergeForm);

		//Add panels to Cards layout
		cards.add(createPanel(welcomePanel, "FIRST_PANNEL"), "FIRST_PANNEL");
		cards.add(createPanel(reportMenuFormPanel, "SECOND_PANNEL"),"SECOND_PANNEL");
		cards.add(createPanel(clientDBDetailsFormPanel, "FOURTH_PANNEL"),"FOURTH_PANNEL");
		cards.add(createPanel(stdOldVerDBDetailsFormPanel, "FIFTH_PANNEL"),"FIFTH_PANNEL");
		cards.add(createPanel(stdNewVerDBDetailsFormPanel, "SIXTH_PANNEL"),"SIXTH_PANNEL");
		cards.add(createPanel(showDBDetailsPanel, "SEVENTH_PANNEL"),"SEVENTH_PANNEL");
		cards.add(createPanel(objectsToMergeForm, "EIGHTH_PANNEL"),"EIGHTH_PANNEL");
		cards.add(createPanel(fileListPanel, "NINTH_PANNEL"),"NINTH_PANNEL");
		cards.add(createPanel(openReportPanel, "REPORT_PANNEL"), "REPORT_PANNEL");
		cards.add(createPanel(intialMergePanel, "FINAL_PANNEL"), "FINAL_PANNEL");
		
		pane.add(controlPanel, BorderLayout.SOUTH);
		pane.add(cards, BorderLayout.CENTER);
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
		
		if ("FIRST_PANNEL".equals(name)) {
			prevButton.setEnabled(false);
			nextButton.setEnabled(true);
		} else {
			prevButton.setEnabled(true);
		}

		if ("FINAL_PANNEL".equals(name)) {
			nextButton.setEnabled(false);
		}

		if ("REPORT_PANNEL".equals(name)) {
			nextButton.setEnabled(false);
			//prevButton.setEnabled(false);
		}
		
		if ("SECOND_PANNEL".equals(name)) {

			System.out.println("report flag... "
					+ ClientDetailsBean.reportSelectedFlag);
			if (ClientDetailsBean.reportSelectedFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}

		if ("FOURTH_PANNEL".equals(name)) {

			System.out.println("Test flag... "
					+ ClientDetailsBean.clientDetailsFlag);
			if (ClientDetailsBean.clientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		if ("FIFTH_PANNEL".equals(name)) {

			System.out.println("old Test flag... "
					+ ClientDetailsBean.oldClientDetailsFlag);
			if (ClientDetailsBean.oldClientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		if ("SIXTH_PANNEL".equals(name)) {

			System.out.println("new Test flag... "
					+ ClientDetailsBean.newClientDetailsFlag);
			if (ClientDetailsBean.newClientDetailsFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("SEVENTH_PANNEL".equals(name)) {
			nextButton.setEnabled(true);
		}
		
		if ("EIGHTH_PANNEL".equals(name)) {

			if (ClientDetailsBean.downLoadSelectedFlag) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
			}
		}
		
		if ("NINTH_PANNEL".equals(name)) {

			if (ClientDetailsBean.downLoadSelectedFlag) {
				System.out.println("Load file list");
				fileListPanel.getFileList();
				fileListPanel.validate();
				}
			
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
				new DBUpgradeMain();
			}
		});
	}
	
	
	private JFrame frame;
	private JPanel cards, controlPanel, intialMergePanel, welcomePanel;
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
	
}
