package com.nuview.upgrade.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.nuview.frames.NuviewTable;

public class FileUtil {
public Map<String, String> getFileMap(String folderName) {
	
	Map<String, String> fileMap = new HashMap<String, String>();
	
	String workingDir = System.getProperty("user.dir");
	String path = workingDir+"/output/"+folderName;
	
	//System.out.println("PATH:: "+path);
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles();
	
	for(File file : listOfFiles){
		
		if(file.getName().startsWith("Agent")){
			fileMap.put(file.getName(), "Agent");
		}
		else if(file.getName().startsWith("Form")){
			fileMap.put(file.getName(), "Form");
		}
		
	}
	return fileMap;
	
}

public TableModel toTableModel(Map<?,?> map) {
    DefaultTableModel model = new NuviewTable(new Object[] { "Key", "Value" }, 0);
    
    for (Map.Entry<?,?> entry : map.entrySet()) {
        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
    }
    
    return model;
}

}

