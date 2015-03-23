package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
    public class VersionsMenuForm extends JPanel{

        private static final long serialVersionUID = 1L;
        JPanel panelNewUser;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        private JLabel lblPanelName;
        
        public VersionsMenuForm(final ClientDetailsBean clientDetailsBean, String lblPanelNameString) {

        	super(new BorderLayout());

        	        String[] sourceVersionStrings = { "1.1", "1.2", "1.3", "1.4", "1.5" };
        	        String[] targetVersionStrings = { "1.1", "1.2", "1.3", "1.4", "1.5" };

        	        
        	    	lblPanelName = new JLabel("<html><h3>"+lblPanelNameString+"</h3></html");
                    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
                    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
                	
                    
        	        GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.insets = new Insets(2, 2, 2, 2);
                    gbc.anchor = GridBagConstraints.WEST;
                    
        	        JLabel sourceLabel = new JLabel("Source Version");
        	        sourceLabel.setVisible(true);

        	      //Put the Label and combo boxes in a column in a panel.
                    JPanel comboPanel = new JPanel(new GridBagLayout());
                    
        	        comboPanel.add(sourceLabel,gbc);
        	        
        	        gbc.gridx++;
        	      
        	        //Create the combo box, select the item at index 4.
        	        //Indices start at 0, so 4 specifies the 1.5.
        	        JComboBox sourceVersionList = new JComboBox(sourceVersionStrings);
        	        sourceVersionList.setSelectedIndex(4);
        	        //Lay out the demo.
        	        comboPanel.add(sourceVersionList, gbc);
        	        
        	        
        	        JLabel targetLabel = new JLabel("Target Version");
        	        targetLabel.setVisible(true);
        	        
        	        gbc.gridx = 0;
                    gbc.gridy++;
        	        comboPanel.add(targetLabel,gbc);
        	      //Create the combo box, select the item at index 4.
        	        //Indices start at 0, so 4 specifies the 1.5.
        	        JComboBox targetVersionList = new JComboBox(targetVersionStrings);
        	        targetVersionList.setSelectedIndex(4);
        	        //targetVersionList.addActionListener(this);
        	        
        	        gbc.gridx++;
        	        comboPanel.add(targetVersionList, gbc);
        	       
        	        
        	        this.add(lblPanelName, BorderLayout.NORTH);
                    add(comboPanel, BorderLayout.CENTER);
                   
                    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        	    
                    
                    sourceVersionList.addActionListener(new ActionListener() {
                    	 
                        public void actionPerformed(ActionEvent e)
                        {
                        	JComboBox cb = (JComboBox)e.getSource();
                	        String sourceVersion = (String)cb.getSelectedItem();
                	        clientDetailsBean.setSourceVersionStr(sourceVersion);
                	        System.out.println("source version:: "+sourceVersion);
                        
                    }});
                    
                    targetVersionList.addActionListener(new ActionListener() {
                    	 
                        public void actionPerformed(ActionEvent e)
                        {
                        	JComboBox cb = (JComboBox)e.getSource();
                	        String targetVersion = (String)cb.getSelectedItem();
                	        clientDetailsBean.setTargetVersionStr(targetVersion);
                	        System.out.println("Target version:: "+targetVersion);
                        }
                    });
                    
        }// constructor closed

    }// class closed