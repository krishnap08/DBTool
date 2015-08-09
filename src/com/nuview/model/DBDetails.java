package com.nuview.model;

public class DBDetails {

	private String hostName;
	private String sqlInstanceName;
	private String dbName;
	private String portStr;
	private String userName;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getSqlInstanceName() {
		return sqlInstanceName;
	}
	public void setSqlInstanceName(String sqlInstanceName) {
		this.sqlInstanceName = sqlInstanceName;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getPortStr() {
		return portStr;
	}
	public void setPortStr(String portStr) {
		this.portStr = portStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;

}
