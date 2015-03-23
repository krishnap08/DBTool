package com.nuview.upgrade.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nuview.frames.ClientDetailsBean;
import com.nuview.upgrade.common.Constancts.ACTION;

public class UpgradeAction {
	static {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (Exception ee) {
			System.out.println("Unable to initialize ODBC-JDBC driver.\n");
			System.exit(3);
		}
	}
	
	public boolean verifyDatabaseDetails(ACTION pAction,
			ClientDetailsBean dbDetails) {
		
		boolean flag = false;
		switch (pAction) {
		case VERIFY_CLIENT_DB:			
			String url = getDBConnUrl(dbDetails.getHostName(), dbDetails.getPortStr(), dbDetails.getDbName(), dbDetails.getUserName(), dbDetails.getPassword());
			Connection con = null;
			try {
				con = DriverManager.getConnection(url,getPrefix(dbDetails.getDbName()) + "_" + dbDetails.getUserName(), dbDetails.getPassword());
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
			
			break;

		default:
			break;
		}
		return flag;
	}
	
	private static String getDBConnUrl(String prm_sServer, String prm_sPort, String prm_sDBName, String prm_sUserName, String prm_sPassword) {
		String v_Return = "";
		v_Return = "jdbc:jtds:sqlserver://" + prm_sServer + ":" + prm_sPort + "/" + prm_sDBName + ";instance=SQLFULL;TDS=8.0;appName=NuView_Merge;" ;
		return v_Return;
	}
	
	private static String getPrefix(String prm_sDBName) {
		String v_Return = "";
		v_Return = prm_sDBName.substring(2);
		return v_Return;
	}
	
	public static void main(String args[]) {
		UpgradeAction ua = new UpgradeAction();
		ClientDetailsBean dbDetails = new ClientDetailsBean();
		dbDetails.setDbName("Nu4PT");
		dbDetails.setHostName("karunya-PC");
		dbDetails.setPortStr("1433");
		dbDetails.setUserName("nvsuperuser1");
		dbDetails.setPassword("nuview");
		ua.verifyDatabaseDetails(ACTION.VERIFY_CLIENT_DB, dbDetails);
	}
}
