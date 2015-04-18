package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class FileListPanel extends JPanel {

	public FileListPanel() {
	}

	public FileListPanel(final JPanel cardsPanel) {
		this.cardsPanel = cardsPanel;
		initComponents();
	}

	private void initComponents() {

		super.setLayout(new BorderLayout());
		clientDBPanel = new JPanel();
		myFont = new Font("sansserif", Font.BOLD, 12);

		btnGenReport = new JButton("<html><b>Generate Report</b></html>");
		btnInitialUpgrade = new JButton(
				"<html><b>Proceed for Initial Upgrade</b></html>");
		btnGenReport.setPreferredSize(new Dimension(210, 25));
		btnInitialUpgrade.setPreferredSize(new Dimension(210, 25));
		btnGenReport.setFont(myFont);
		btnInitialUpgrade.setFont(myFont);
		clientDBPanel.add(btnGenReport);
		clientDBPanel.add(btnInitialUpgrade);

		scrollpane = new JScrollPane();
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel = new JPanel(new BorderLayout());
		subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		lblPanelName = new JLabel("<html><b>Review File List</b></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		lblTableName = new JLabel(
				"<html><h3>&emsp &emsp Custom Old File List</h3></html>");

		mainPanel.add(lblPanelName, BorderLayout.NORTH);
		mainPanel.add(lblTableName);
		subPanel.add(scrollpane);

		super.add(mainPanel, BorderLayout.NORTH);
		super.add(subPanel, BorderLayout.CENTER);
		super.add(clientDBPanel, BorderLayout.SOUTH);

		btnGenReport.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnGenReportActionPerformed();
			}
		});

		btnInitialUpgrade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnInitialUpgradeActionPerformed();

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

	private void btnGenReportActionPerformed() {

		try {
			// genrateReport();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getParent(), e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		boolean testCompleted = true;
		if (testCompleted) {
			ClientDetailsBean.generateReportSuccessFlag = true;
			CardLayout cl = (CardLayout) cardsPanel.getLayout();
			cl.next(cardsPanel);
		}
	}

	private void btnInitialUpgradeActionPerformed() {

		ClientDetailsBean.initialMergeButtonSelected = true;
		DBUpgradeMain.dbUpgradeMain.initPanels(false);
		System.out.println("FilelistPanel......");

	}

	public void getFileList() {
		FileUtil fileUtil = new FileUtil();

		Map<String, String> fileMap = fileUtil.getFileMap("custom_old");
		fileTable = new JTable(fileUtil.toTableModel(fileMap));
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		Dimension d = fileTable.getPreferredSize();
		scrollpane.setViewportView(fileTable);
		scrollpane.setPreferredSize(new Dimension(425, 325));
	}

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane;
	private JLabel lblPanelName, lblTableName;
	private JTable fileTable;
	private JButton btnGenReport, btnInitialUpgrade;
	private JPanel clientDBPanel, mainPanel, subPanel, cardsPanel;
	private Font myFont;
}