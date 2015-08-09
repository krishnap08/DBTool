package com.nuview.upgrade.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nuview.generator.ReportGenerator;
import com.nuview.model.ClientDetailsBean;
import com.nuview.numerge.TextMerge;
import com.nuview.upgrade.common.Constancts.ACTION;
import com.nuview.upgrade.util.ConfigProperty;

public class UpgradeAction {
	
	static {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (Exception ee) {
			System.out.println("Unable to initialize ODBC-JDBC driver.\n");
			System.exit(3);
		}
	}
	
	/**
	 * This method will generate configuration files required to start the merge analysis process
	 */
	public static void generateCfgFilesForMergeAnalysis() {
		ConfigProperty propUtil = ConfigProperty.getInstance();
		propUtil.writeToFile();
	}
	
	/** 
	 * This method is used to generate 
	 */
	public static void generateMergeAnalysisReport() {
		try {	
			ConfigProperty propUtil = ConfigProperty.getInstance();
			String[] args = new String[1];
			args[0] = propUtil.getClientWorkingDir() + File.separator;
			ReportGenerator.main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void download() {
		System.setProperty("FROM_UI_TOOL", "TRUE");
		ConfigProperty propUtil = ConfigProperty.getInstance();
		String[] myParams = new String[2];
		myParams[0] = "download";
		myParams[1] = propUtil.getClientWorkingDir() + File.separator;
		TextMerge.main(myParams);
	}
	
	public static boolean verifyDatabaseDetails(ACTION pAction,
			ClientDetailsBean dbDetails) {
		
		boolean flag = false;
		String url = null;
		switch (pAction) {
		case VERIFY_CLIENT_DB:			
			url = getDBConnUrl(dbDetails.getHostName(), dbDetails.getPortStr(), dbDetails.getDbName(), dbDetails.getUserName(), dbDetails.getPassword());			
			break;
		case VERIFY_STD_OLD_DB:			
			url = getDBConnUrl(dbDetails.getOldHostName(), dbDetails.getOldPortStr(), dbDetails.getOldDBName(), dbDetails.getOldUserName(), dbDetails.getOldPassword());			
			break;
		case VERIFY_STD_NEW_DB:			
			url = getDBConnUrl(dbDetails.getNewHostName(), dbDetails.getNewPortStr(), dbDetails.getNewDBName(), dbDetails.getNewUserName(), dbDetails.getNewPassword());			
			break;
		default:
			break;
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return flag;
	}
	
	private static String getDBConnUrl(String prm_sServer, String prm_sPort, String prm_sDBName, String prm_sUserName, String prm_sPassword) {
		String v_Return = "";
		v_Return = "jdbc:jtds:sqlserver://" + prm_sServer + ":" + prm_sPort + "/" + prm_sDBName + ";TDS=8.0;appName=NuView_Merge;user=" + getPrefix(prm_sDBName) + "_"+prm_sUserName+";password="+prm_sPassword;
		return v_Return;
	}
	
	private static String getPrefix(String prm_sDBName) {
		String v_Return = "";
		v_Return = prm_sDBName.substring(2);
		return v_Return;
	}
	
	public static void main(String args[]) {
		UpgradeAction ua = new UpgradeAction();
		/*ClientDetailsBean dbDetails = new ClientDetailsBean();
		dbDetails.setDbName("NuCAA");
		dbDetails.setHostName("10.193.1.5");
		dbDetails.setPortStr("9133");
		dbDetails.setUserName("nvsuperuser1");
		dbDetails.setPassword("nuview");
		System.out.println(ua.verifyDatabaseDetails(ACTION.getAction("c1"), dbDetails));
		
		dbDetails.setDbName("Nu417");
		dbDetails.setHostName("karunya-PC");
		dbDetails.setPortStr("1933");
		dbDetails.setUserName("nvsuperuser1");
		dbDetails.setPassword("nuview_677855");
		//System.out.println(ua.verifyDatabaseDetails(ACTION.VERIFY_CLIENT_DB, dbDetails));
*/		
		
		String[] prams = new String[1];
		prams[0] = "DEV" + File.separator;
		try {
			ReportGenerator.main(prams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
