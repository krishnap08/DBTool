package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.upgrade.util.FileUtil;

public class CustomNewFileListPanel extends JPanel {

	public CustomNewFileListPanel() {
	}

	public CustomNewFileListPanel(final JPanel cardsPanel) {
		this.cardsPanel = cardsPanel;
		initComponents();
	}

	private void initComponents() {

		super.setLayout(new BorderLayout());
		clientDBPanel = new JPanel();
		myFont = new Font("sansserif", Font.BOLD, 12);

		scrollpane = new JScrollPane();
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel = new JPanel(new BorderLayout());
		subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		lblPanelName = new JLabel("<html><b>Custom New File List</b></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		// lblTableName = new
		// JLabel("<html><h3>&emsp &emsp Custom New File List</h3></html>");

		mainPanel.add(lblPanelName, BorderLayout.NORTH);
		// mainPanel.add(lblTableName);
		subPanel.add(scrollpane);

		super.add(mainPanel, BorderLayout.NORTH);
		super.add(subPanel, BorderLayout.CENTER);
		// super.add(clientDBPanel, BorderLayout.SOUTH);

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

		Map<String, String> fileMap = fileUtil.getFileMap("custom_new");
		fileTable = new JTable(fileUtil.toTableModel(fileMap));
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		Dimension d = fileTable.getPreferredSize();
		scrollpane.setViewportView(fileTable);
		scrollpane.setPreferredSize(new Dimension(475, 350));
	}

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane;
	private JLabel lblPanelName, lblTableName;
	private JTable fileTable;
	// private JButton btnGenReport, btnInitialUpgrade;
	private JPanel clientDBPanel, mainPanel, subPanel, cardsPanel;
	private Font myFont;
}