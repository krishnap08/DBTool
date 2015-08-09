package com.nuview.model;

import java.util.Observable;

/**
 * <p>
 * data Object, which holds the name of one student.<br>
 * Classic POJO which extends Observable. The setter notifies the
 * Observers.</br>
 * </p>
 */
public class ClientDetailsBean extends Observable {

	public static boolean downLoadSelectedFlag = false;
	
	public static boolean generateReportSuccessFlag = false;

	public static boolean reportSelectedFlag = false;

	public static boolean clientDetailsFlag = false;

	public static boolean newClientDetailsFlag = false;

	public static boolean oldClientDetailsFlag = false;
	
	public static boolean initialMergeButtonSelected = false;

	private boolean generateReportFlag, mergeFlag, initialMergeFlag,
			preProdFlag;

	private String sourceVersionStr, targetVersionStr;

	private String hostName;
	
	private String sqlInstanceName;
	
	private String dbName;

	private String portStr;

	private String userName;

	private String password;

	private String oldHostName;
	
	private String oldSqlInstanceName;

	private String oldDBName;

	private String oldPortStr;

	private String oldUserName;

	private String OldPassword;

	private String newHostName;

	private String newSqlInstanceName;
	
	private String newDBName;

	private String newPortStr;

	private String newUserName;

	private String newPassword;

	private String clientName;
	
	private String currentVersion;
	private String targetVersion;
	private String performedBy;
	
	public ClientDetailsBean() { 
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
		setChanged();
		notifyObservers();
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
		setChanged();
		notifyObservers();
	}

	public String getPortStr() {
		return portStr;
	}

	public void setPortStr(String portStr) {
		this.portStr = portStr;
		setChanged();
		notifyObservers();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		setChanged();
		notifyObservers();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		setChanged();
		notifyObservers();
	}

	public String getOldHostName() {
		return oldHostName;
	}

	public void setOldHostName(String oldHostName) {
		this.oldHostName = oldHostName;
		setChanged();
		notifyObservers();
	}

	public String getOldDBName() {
		return oldDBName;
	}

	public void setOldDBName(String oldDBName) {
		this.oldDBName = oldDBName;
		setChanged();
		notifyObservers();
	}

	public String getOldPortStr() {
		return oldPortStr;
	}

	public void setOldPortStr(String oldPortStr) {
		this.oldPortStr = oldPortStr;
		setChanged();
		notifyObservers();
	}

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
		setChanged();
		notifyObservers();
	}

	public String getOldPassword() {
		return OldPassword;
	}

	public void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
		setChanged();
		notifyObservers();
	}

	public String getNewHostName() {
		return newHostName;
	}

	public void setNewHostName(String newHostName) {
		this.newHostName = newHostName;
		setChanged();
		notifyObservers();
	}

	public String getNewDBName() {
		return newDBName;
	}

	public void setNewDBName(String newDBName) {
		this.newDBName = newDBName;
		setChanged();
		notifyObservers();
	}

	public String getNewPortStr() {
		return newPortStr;
	}

	public void setNewPortStr(String newPortStr) {
		this.newPortStr = newPortStr;
		setChanged();
		notifyObservers();
	}

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
		setChanged();
		notifyObservers();
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
		setChanged();
		notifyObservers();
	}

	public boolean isGenerateReportFlag() {
		return generateReportFlag;
	}

	public void setGenerateReportFlag(boolean generateReportFlag) {
		this.generateReportFlag = generateReportFlag;
		setChanged();
		notifyObservers();
	}

	public boolean isMergeFlag() {
		return mergeFlag;
	}

	public void setMergeFlag(boolean mergeFlag) {
		this.mergeFlag = mergeFlag;
		setChanged();
		notifyObservers();
	}

	public boolean isInitialMergeFlag() {
		return initialMergeFlag;
	}

	public void setInitialMergeFlag(boolean initialMergeFlag) {
		this.initialMergeFlag = initialMergeFlag;
		setChanged();
		notifyObservers();
	}

	public boolean isPreProdFlag() {
		return preProdFlag;
	}

	public void setPreProdFlag(boolean preProdFlag) {
		this.preProdFlag = preProdFlag;
		setChanged();
		notifyObservers();
	}

	public String getSourceVersionStr() {
		return sourceVersionStr;
	}

	public void setSourceVersionStr(String sourceVersionStr) {
		this.sourceVersionStr = sourceVersionStr;
		setChanged();
		notifyObservers();
	}

	public String getTargetVersionStr() {
		return targetVersionStr;
	}

	public void setTargetVersionStr(String targetVersionStr) {
		this.targetVersionStr = targetVersionStr;
		setChanged();
		notifyObservers();
	}

	public String getSqlInstanceName() {
		return sqlInstanceName;
	}

	public void setSqlInstanceName(String sqlInstanceName) {
		this.sqlInstanceName = sqlInstanceName;
	}

	public String getOldSqlInstanceName() {
		return oldSqlInstanceName;
	}

	public void setOldSqlInstanceName(String oldSqlInstanceName) {
		this.oldSqlInstanceName = oldSqlInstanceName;
	}

	public String getNewSqlInstanceName() {
		return newSqlInstanceName;
	}

	public void setNewSqlInstanceName(String newSqlInstanceName) {
		this.newSqlInstanceName = newSqlInstanceName;
	}

	@Override
	public String toString() {
		return "ClientDetailsBean [getDbName()=" + getDbName()
				+ ", getHostName()=" + getHostName() + ", getNewDBName()="
				+ getNewDBName() + ", getNewHostName()=" + getNewHostName()
				+ ", getNewPassword()=" + getNewPassword()
				+ ", getNewPortStr()=" + getNewPortStr()
				+ ", getNewUserName()=" + getNewUserName()
				+ ", getOldDBName()=" + getOldDBName() + ", getOldHostName()="
				+ getOldHostName() + ", getOldPassword()=" + getOldPassword()
				+ ", getOldPortStr()=" + getOldPortStr()
				+ ", getOldUserName()=" + getOldUserName() + ", getPassword()="
				+ getPassword() + ", getPortStr()=" + getPortStr()
				+ ", getSourceVersionStr()=" + getSourceVersionStr()
				+ ", getTargetVersionStr()=" + getTargetVersionStr()
				+ ", getUserName()=" + getUserName()
				+ ", isGenerateReportFlag()=" + isGenerateReportFlag()
				+ ", isInitialMergeFlag()=" + isInitialMergeFlag()
				+ ", isMergeFlag()=" + isMergeFlag() + ", isPreProdFlag()="
				+ isPreProdFlag() + "]";
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

}