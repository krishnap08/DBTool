package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import com.nuview.upgrade.util.ConfigProperty;
import com.nuview.upgrade.util.FileUtil;

public class ConflictFileListPanel extends JPanel {

	public ConflictFileListPanel() {
	}

	public ConflictFileListPanel(final JPanel cardsPanel,
			JButton parentNextButton) {
		this.cardsPanel = cardsPanel;
		this.parentNextButton = parentNextButton;
		initComponents();
	}

	private void initComponents() {

		super.setLayout(new BorderLayout());
		clientDBPanel = new JPanel();
		myFont = new Font("sansserif", Font.BOLD, 12);

		btnRefreshTable = new JButton("<html><b>Refresh</b></html>");
		btnRefreshTable.setPreferredSize(new Dimension(210, 25));
		btnRefreshTable.setFont(myFont);
		clientDBPanel.add(btnRefreshTable);

		scrollpane = new JScrollPane();
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		fileTable = new JTable();

		mainPanel = new JPanel(new BorderLayout());
		subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		lblPanelName = new JLabel("<html><b>Conflict File List</b></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		lblMessage = new JLabel(
				"<html><b><font color='red'>Note: Resolve conflicts and Click Refresh to continue.</font></b></html>");

		mainPanel.add(lblPanelName, BorderLayout.NORTH);
		// mainPanel.add(lblTableName);
		subPanel.add(scrollpane);
		subPanel.add(lblMessage);
		super.add(mainPanel, BorderLayout.NORTH);
		super.add(subPanel, BorderLayout.CENTER);
		super.add(clientDBPanel, BorderLayout.SOUTH);

		btnRefreshTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnRefreshTableActionPerformed();
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

		fileTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent me) {
				ConfigProperty config = ConfigProperty.getInstance();
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2) {
					String fileName = dataModel.getValueAt(row, 0).toString();
					System.out.println("double click event fired....Row:: "
							+ row + " Value :: " + fileName);

					try {

						String bcPath = "C:\\Program Files (x86)\\Beyond Compare 4\\BComp.exe";

						String conflictDir = config.getClientWorkingDir()
								+ File.separator + "conflict" + File.separator;
						String standardOldDir = config.getClientWorkingDir()
								+ File.separator + "standard_old"
								+ File.separator;
						String standardNewDir = config.getClientWorkingDir()
								+ File.separator + "standard_new"
								+ File.separator;
						String customNewDir = config.getClientWorkingDir()
								+ File.separator + "custom_new"
								+ File.separator;

						String conflictFile = conflictDir + fileName;
						String standardOld = standardOldDir + fileName;
						String standardNew = standardNewDir + fileName;
						String customNew = customNewDir + fileName;

						Runtime.getRuntime().exec(
								bcPath + " " + conflictFile + " " + standardNew
										+ " " + standardOld + " " + customNew);

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

	private void btnRefreshTableActionPerformed() {

		int rowCount = getFileList();
		validate();
		System.out.println("Refresh table.... Row count: " + rowCount);
		parentNextButton.setEnabled(true);
	}

	public int getFileList() {
		FileUtil fileUtil = new FileUtil();

		Map<String, String> fileMap = fileUtil.getFileMap("conflict");
		dataModel = fileUtil.toTableModel(fileMap);
		fileTable.setModel(dataModel);
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		Dimension d = fileTable.getPreferredSize();
		scrollpane.setViewportView(fileTable);
		scrollpane.setPreferredSize(new Dimension(475, 325));

		return dataModel.getRowCount();
	}

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane;
	private JLabel lblPanelName, lblMessage;
	private JTable fileTable;
	private TableModel dataModel;
	// private JButton btnGenReport, btnInitialUpgrade;
	private JButton btnRefreshTable, parentNextButton;
	private JPanel clientDBPanel, mainPanel, subPanel, cardsPanel;
	private Font myFont;
	private boolean isConflictsResolved = false;
}