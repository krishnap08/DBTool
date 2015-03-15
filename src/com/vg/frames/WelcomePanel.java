package com.vg.frames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 970405194996409655L;

	public WelcomePanel() {
		super();
		addLabel();
	}

	private void addLabel() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html> <body>");
		sb.append("<div style=\"width:300px;font-size:12px\">");
		sb.append("<h3>Welcome To NuViewHR® Upgrade Process</h3> <br> <br>");
		sb.append("Upgrade process facilitates an existing customer to upgrade their NuViewHR® Product from an older version to the newer version. <br>");
		sb.append("<ol type=\"circle\"> <li>Without impacting client customizations.</li>");
		sb.append("<li>Directly from a previous version of 4.X to latest.</li> </ol>");
		sb.append("Follow the instructions on the UI to proceed. <br><br>");
		sb.append("Click<b> Next</b> to continue");
		sb.append("</div>");
		sb.append("");
		sb.append("</body>");
		sb.append("</html>");
		//System.out.println("Welcome str::  " + sb.toString());
		JTextArea area = new JTextArea(sb.toString());
		area.setEditable(false);
		area.setLineWrap(true);

		super.add(new JLabel(sb.toString()));
	}

}
