package com.vg.frames;

/*
 * Main container class holds control panel and all other panels will be initiated here.
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DBUpgradeMain {

	// Create and set up the window.
	static JFrame frame = new JFrame("NuViewHR®");

	JPanel cards;
	private static final String PANEL_PROPERTY = "PANEL_PROPERTY";
	JPanel controlPanel = null;
	JButton prevButton = null;
	JButton nextButton = null;
	JButton cancelButton = null;

	public void addComponentToPane(Container pane) {

		controlPanel = new JPanel();
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(controlPanel,
						"Do you really want to Cancel?", "Warning",
						dialogButton);

				if (dialogResult == JOptionPane.YES_OPTION) {
					frame.dispose();
				}

			}
		});

		// Add buttons to control panel
		controlPanel.add(prevButton);
		controlPanel.add(nextButton);
		controlPanel.add(cancelButton);

		// Create the panel to add list of panels.
		cards = new JPanel(new CardLayout());

		ClientDetailsBean clientDetailsBean = new ClientDetailsBean();

		JPanel welcomePanel = new WelcomePanel();

		ReportMenuForm reportMenuFormPanel = new ReportMenuForm(
				clientDetailsBean, nextButton,
				"<html><b>Steps in upgrade process</b></html>");

		// VersionsMenuForm versionMenuFormPanel = new
		// VersionsMenuForm(clientDetailsBean, "Select DB Versions");

		CleintDBDetailsForm clientDBDetailsFormPanel = new CleintDBDetailsForm(
				clientDetailsBean, nextButton, "c1", "Client DB Details");

		CleintDBDetailsForm stdOldVerDBDetailsFormPanel = new CleintDBDetailsForm(
				clientDetailsBean, nextButton, "c2",
				"Standard Old Version DB Details");

		CleintDBDetailsForm stdNewVerDBDetailsFormPanel = new CleintDBDetailsForm(
				clientDetailsBean, nextButton, "c3",
				"Standard New Version DB Details");

		ShowDBDetailsPanel showDBDetailsPanel = new ShowDBDetailsPanel(cards,
				nextButton, "All DB Version Details");

		OpenReportPanel openReportPanel = new OpenReportPanel("View Report");
		// ScrollListPanel scrollListPanel = new
		// ScrollListPanel("Review Files List");

		clientDetailsBean.addObserver(showDBDetailsPanel);

		cards.add(createPanel(welcomePanel, "FIRST_PANNEL"), "FIRST_PANNEL");
		cards.add(createPanel(reportMenuFormPanel, "SECOND_PANNEL"),
				"SECOND_PANNEL");
		// cards.add(createPanel(versionMenuFormPanel, "THIRD_PANNEL"),
		// "THIRD_PANNEL");
		cards.add(createPanel(clientDBDetailsFormPanel, "FOURTH_PANNEL"),
				"FOURTH_PANNEL");
		cards.add(createPanel(stdOldVerDBDetailsFormPanel, "FIFTH_PANNEL"),
				"FIFTH_PANNEL");
		cards.add(createPanel(stdNewVerDBDetailsFormPanel, "SIXTH_PANNEL"),
				"SIXTH_PANNEL");
		cards.add(createPanel(showDBDetailsPanel, "SEVENTH_PANNEL"),
				"SEVENTH_PANNEL");
		cards.add(createPanel(openReportPanel, "FINAL_PANNEL"), "FINAL_PANNEL");

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

			String name = (String) ((JComponent) source)
					.getClientProperty(PANEL_PROPERTY);

			if ("FIRST_PANNEL".equals(name)) {
				prevButton.setEnabled(false);
			} else {
				prevButton.setEnabled(true);
			}

			if ("SEVENTH_PANNEL".equals(name) || "FINAL_PANNEL".equals(name)) {
				nextButton.setEnabled(false);
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

			if (name == null) {
				throw new IllegalStateException(
						"No NUMBER_PROPERTY on component");
			}
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
		}
	};

	/**
	 * invocation method
	 */
	private static void createAndShowGUI() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setPreferredSize(new Dimension(500, 500));
		frame.setResizable(false);

		// Create and set up the content pane.
		DBUpgradeMain demo = new DBUpgradeMain();
		demo.addComponentToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
				createAndShowGUI();
			}
		});
	}
}
