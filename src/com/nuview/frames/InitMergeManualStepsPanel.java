package com.nuview.frames;

import java.awt.BorderLayout;
import java.util.Properties;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.nuview.upgrade.util.FileUtil;

public class InitMergeManualStepsPanel extends JPanel {

	private static final long serialVersionUID = 970405194996409655L;
	private Properties properties;
	private JLabel lblPanelName, lblStepsList;
	private JPanel mainPanel;
	
	public InitMergeManualStepsPanel() {
		super();
		loadProperties();
		addLabel();
	}

	private void addLabel() {
		 lblPanelName = new JLabel("<html><b>Manual Steps to perform</b></html>");
		    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
		    mainPanel = new JPanel(new BorderLayout());
		    mainPanel.add(lblPanelName,BorderLayout.NORTH);
		
		    lblStepsList = new JLabel();
		    
		StringBuilder sb = new StringBuilder();
		sb.append("<html> <body>");
		sb.append("<div style=\"width:300px;font-size:12px\">");
		sb.append("<br>");
		
		
		for(int key=1; key<6; key++){
            sb.append("<b>"+properties.getProperty(""+key+"") +"</b>");
            sb.append("<br>");
            sb.append("<br>");
            System.out.println(key+": "+properties.getProperty(""+key+""));
        }
		
		
		sb.append("</div>");
		sb.append("");
		sb.append("</body>");
		sb.append("</html>");
		lblStepsList.setText(sb.toString());
		lblStepsList.setHorizontalAlignment( SwingConstants.CENTER );
		mainPanel.add(lblStepsList, BorderLayout.CENTER);
		
		super.setLayout(new BorderLayout());
		super.add(mainPanel, BorderLayout.NORTH);
	}
	
	private void loadProperties(){
		properties = FileUtil.loadProperties("initialMerge");
	}

}
