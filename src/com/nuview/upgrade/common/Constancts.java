package com.nuview.upgrade.common;

public class Constancts {

	public final static String KEY_DB_HOST_NAME = "DB_HOST_NAME";
	public final static String KEY_DB_INSTANCE_NAME = "DB_INSTANCE_NAME";
	public final static String KEY_DB_PORT = "DB_PORT";
	public final static String KEY_DB_USER = "DB_USER";
	public final static String KEY_DB_PASSWORD = "DB_PASSWORD";
	
	public enum ACTION {
		VERIFY_CLIENT_DB("c1"),
		VERIFY_STD_OLD_DB("c2"),
		VERIFY_STD_NEW_DB("c3");	
		private String value = null;
		ACTION (String pValue) {
			value = pValue;
		}
		
		public static ACTION getAction(String pValue) {
		    if (pValue != null) {
		      for (ACTION action : ACTION.values()) {
		        if (pValue.equalsIgnoreCase(action.value)) {
		          return action;
		        }
		      }
		    }
		    return null;
		  }
	}
}
