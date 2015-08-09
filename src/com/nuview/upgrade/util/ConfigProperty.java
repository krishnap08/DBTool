package com.nuview.upgrade.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

public class ConfigProperty {

	private Properties prop = new Properties();
	private static ConfigProperty mConfig = null;

	private String clientDir = null;
	private String workingDir = null;
	
	private ConfigProperty() {
		prop.setProperty("RTM", "Customization to Return to Master - code is the same as the new version but the object was marked as custom");
		prop.setProperty("Small", "Simple customization that will be merged into the new code version");
		prop.setProperty("Large", "Complex customization that will be merged into the new code version");
		prop.setProperty("Custom","Customization that will be brought over \"as is\" into the new code version (no merge needed)");
	
		File file = null;
		try {
			file = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		workingDir = file.getParent();
		System.setProperty("user.dir",workingDir);
		
	}

	public static ConfigProperty getInstance() {
		if (mConfig == null) {
			mConfig = new ConfigProperty();
		}
		return mConfig;
	}

	public void setProperty(String key, String value) {
		if (value != null) {
			prop.setProperty(key, value);
		}
	}

	public String getProperty(String key, String value) {
		return prop.getProperty(key);
	}
	
	public void writeToFile() {
		OutputStream outputConnFile = null;
		OutputStream outputConfigFile = null;
		try {
			File myDir = new File(workingDir + File.separator + prop.getProperty("Client"));
			myDir.mkdir();
			clientDir = myDir.getAbsolutePath();
			
			prop.setProperty("MergeFolderLocation",FilenameUtils.normalize(clientDir + File.separator));
			prop.setProperty("CsvFileLocation",FilenameUtils.normalize( clientDir + File.separator));
			
			outputConnFile = new FileOutputStream(myDir.getAbsoluteFile()
					+ File.separator + "connection.properties");
			prop.store(outputConnFile, null);

			outputConfigFile = new FileOutputStream(myDir.getAbsoluteFile()
					+ File.separator + "config.properties");
			prop.store(outputConfigFile, null);
			
		} catch (IOException io) {
			io.printStackTrace();
		} finally {

			try {
				if (outputConnFile != null) {
					outputConnFile.flush();
					outputConnFile.close();
				}
				if (outputConfigFile != null) {
					outputConnFile.flush();
					outputConfigFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getClientWorkingDir() {
		return clientDir;
	}
	
	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}

	public static void main(String args[]) {
		ConfigProperty util = new ConfigProperty();
		util.setProperty("Client","TEST");
		util.writeToFile();
		
		System.out.println("D\\:".replace("\\:", ":"));
		
	}
}
