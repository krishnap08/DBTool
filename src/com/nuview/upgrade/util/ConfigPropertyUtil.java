package com.nuview.upgrade.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigPropertyUtil {
	Properties prop = new Properties();

	public void setProperty(String key, String value) {
		if (value != null) {
			prop.setProperty(key, value);
		}
	}
	
	public void writeToFile(String pVRootDir) {
		OutputStream output = null;
		try {
			File myDir = new File(pVRootDir);
			myDir.mkdir();
			output = new FileOutputStream(pVRootDir + "config.properties");
	 
			// save properties to project root folder
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String args[]) {
		ConfigPropertyUtil util = new ConfigPropertyUtil();
		util.writeToFile("output\\");
	}
}
