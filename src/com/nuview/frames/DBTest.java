package com.nuview.frames;

import java.io.File;

public class DBTest {
public static void main(String[] args) {
	
	String workingDir = System.getProperty("user.dir");
	String path = workingDir+"/output/custom_old";
	
	System.out.println("PATH:: "+path);
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles();
	System.out.println("listOfFiles:: ");
	
	for(File file : listOfFiles){
		
		if(file.getName().startsWith("Agent")){
			System.out.println(file.getName());	
		}
	}
	
}
}
