package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.model.ObjectsToMergeBean;
import com.nuview.upgrade.util.ConfigPropertyUtil;

public class ObjectsToMergeForm extends JPanel{

	public ObjectsToMergeForm(final ObjectsToMergeBean objectsToMergeBean, final JPanel cardsPanel, final JButton parentNextButton) {

		this.objectsToMergeBean = objectsToMergeBean;
		this.cardsPanel = cardsPanel;
		this.parentNextButton = parentNextButton;
		initComponents();

	}// constructor closed
	
	private void initComponents(){
		
		super.setLayout(new BorderLayout());
		myFont = new Font("sansserif",Font.BOLD,12);
		
		btnProcess = new JButton("<html><b>Process</b></html>");
		btnProcess.setFont(myFont);
		btnProcess.setPreferredSize(new Dimension(220, 25));
		
		lblPanelName = new JLabel("<html><h3>Select the Objects to Merge</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		lblForms = new JLabel("FORMS:");
		lblTables = new JLabel("TABLES:");
		lblAgents = new JLabel("AGENTS:");
		lblWorkflows = new JLabel("WORK FLOWS:");
		lblScripts = new JLabel("SCRIPTS:");
		lblFormControls = new JLabel("FORM CONTROLS:");
		lblProperties = new JLabel("PROPERTIES:");
		lblMenus = new JLabel("MENUS:");
		lblTableFields = new JLabel("TABLE FIELDS:");
		
		lblForms.setFont(myFont);
		lblTables.setFont(myFont);
		lblAgents.setFont(myFont);
		lblWorkflows.setFont(myFont);
		lblScripts.setFont(myFont);
		lblFormControls.setFont(myFont);
		lblProperties.setFont(myFont);
		lblMenus.setFont(myFont);
		lblTableFields.setFont(myFont);
		
		txtForms = new JTextField(12);
		txtForms.setText("ALL");
		txtTables = new JTextField(12);
		txtTables.setText("ALL");
		txtAgents = new JTextField(12);
		txtAgents.setText("ALL");
		txtWorkflows = new JTextField(12);
		txtWorkflows.setText("ALL");
		txtScripts = new JTextField(12);
		txtScripts.setText("ALL");
		txtFormControls = new JTextField(12);
		txtFormControls.setText("ALL");	
		txtProperties = new JTextField(12);
		txtProperties.setText("ALL");	
		txtMenus = new JTextField(12);
		txtMenus.setText("ALL");	
		txtTableFields = new JTextField(12);
		txtTableFields.setText("ALL");	
		
		// Put the Label and combo boxes in a column in a panel.
		clientDBPanel = new JPanel(new GridBagLayout());

		mainPanel = new JPanel(new BorderLayout());
		
		buttonPanel = new JPanel(new FlowLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor = GridBagConstraints.WEST;

		clientDBPanel.add(lblForms, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtForms, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblTables, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtTables, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblAgents, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtAgents, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblWorkflows, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtWorkflows, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblScripts, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtScripts, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblFormControls, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtFormControls, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblProperties, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtProperties, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblMenus, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtMenus, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblTableFields, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtTableFields, gbc);

		buttonPanel.add(btnProcess);
		mainPanel.add(lblPanelName, BorderLayout.SOUTH);
		
		super.add(mainPanel, BorderLayout.NORTH);
		super.add(clientDBPanel, BorderLayout.CENTER);
		super.add(buttonPanel, BorderLayout.SOUTH);
		
		addKeyListeners();
				
		btnProcess.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnProcessActionPerformed();
			}
		});
	}
	
	private void addKeyListeners(){
		txtForms.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});

		txtTables.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});

		txtAgents.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});

		txtWorkflows.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});

		txtScripts.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});
		
		txtFormControls.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});
		
		txtProperties.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});
		
		
		txtMenus.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});
		
		txtTableFields.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				textFieldKeyTyped(e);
			}
		});
	}
	
	private void textFieldKeyTyped(KeyEvent e){
		char c = e.getKeyChar();
		if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
				|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			getToolkit().beep();
			JOptionPane.showMessageDialog(null, "Invalid Character",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			e.consume();
		}
		
		// Disable Next button when text is changed
		parentNextButton.setEnabled(false);
	}
	
	private void btnProcessActionPerformed(){

		validateInput();
		
		setProperties();
		
		try{
			processCompleted = true;
		}catch(Exception e1){JOptionPane.showMessageDialog(getParent(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
		
		if (processCompleted) {
			/*System.out.println(
					  "#################### Objects to Merge #########################");
					  System.out.println(objectsToMergeBean.toString());
					  System.out.println
					  ("################################################################");
			 */			  
			
		 ClientDetailsBean.downLoadSelectedFlag = true;
		 CardLayout cl = (CardLayout) cardsPanel.getLayout();
		 cl.next(cardsPanel);
		}
	}
	
	private void validateInput(){
		// Input Validation
				if (txtForms.getText() == null
						|| txtForms.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Forms",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtForms.requestFocus();
					return;
				}

				if (txtTables.getText() == null
						|| txtTables.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Tables",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtTables.requestFocus();
					return;
				}

				if (txtAgents.getText() == null
						|| txtAgents.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Agents",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtAgents.requestFocus();
					return;
				}

				if (txtWorkflows.getText() == null
						|| txtWorkflows.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter WorkFlows",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtWorkflows.requestFocus();
					return;
				}
				
				if (txtScripts.getText() == null
						|| txtScripts.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Scripts",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtScripts.requestFocus();
					return;
				}
				
				if (txtFormControls.getText() == null
						|| txtFormControls.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Form Controls",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtFormControls.requestFocus();
					return;
				}
				
				if (txtProperties.getText() == null
						|| txtProperties.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Properties",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtProperties.requestFocus();
					return;
				}
				
				if (txtMenus.getText() == null
						|| txtMenus.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Menus",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtMenus.requestFocus();
					return;
				}
				
				if (txtTableFields.getText() == null
						|| txtTableFields.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Table fields",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtTableFields.requestFocus();
					return;
				}
	}
	
	private void setProperties(){
		//set inputs to bean
				objectsToMergeBean.setForms(txtForms.getText());
				objectsToMergeBean.setTables(txtTables.getText());
				objectsToMergeBean.setAgents(txtAgents.getText());
				objectsToMergeBean.setWorkflows(txtWorkflows.getText());
				objectsToMergeBean.setScripts(txtScripts.getText());
				objectsToMergeBean.setFormControls(txtFormControls.getText());
				objectsToMergeBean.setProperties(txtProperties.getText());
				objectsToMergeBean.setMenus(txtMenus.getText());
				objectsToMergeBean.setTableFields(txtTableFields.getText());
				
				
			/*	set Inputs to Prperties file
			 * propUtil.setProperty("Forms",objectsToMergeBean.getForms());
				propUtil.setProperty("Tables",objectsToMergeBean.getTables());
				propUtil.setProperty("Agents",objectsToMergeBean.getAgents());
				propUtil.setProperty("WorkFlows",objectsToMergeBean.getWorkflows());
				propUtil.setProperty("Scripts",objectsToMergeBean.getScripts());
				propUtil.setProperty("FormControls",objectsToMergeBean.getFormControls());
				propUtil.setProperty("Properties",objectsToMergeBean.getProperties());
				propUtil.setProperty("Menus",objectsToMergeBean.getMenus());
				propUtil.setProperty("TableFields",objectsToMergeBean.getTableFields());*/

	}
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblForms, lblTables, lblAgents, lblWorkflows, lblScripts, lblFormControls, lblProperties, lblMenus, lblTableFields;
	private JTextField txtForms, txtTables, txtAgents, txtWorkflows, txtScripts, txtFormControls, txtProperties, txtMenus, txtTableFields;
	private Font myFont;
	private JButton btnProcess;
	private boolean processCompleted = false;
	private ObjectsToMergeBean objectsToMergeBean;
	private JPanel cardsPanel, clientDBPanel, mainPanel, buttonPanel;
	private JButton parentNextButton;
	//private ConfigPropertyUtil propUtil = new ConfigPropertyUtil();

}// class closed