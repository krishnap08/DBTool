package com.nuview.model;

import java.util.Observable;

/**
 * <p>
 * data Object, which holds the name of one student.<br>
 * Classic POJO which extends Observable. The setter notifies the
 * Observers.</br>
 * </p>
 */
public class ObjectsToMergeBean extends Observable {

	public String getForms() {
		return forms;
	}

	public void setForms(String forms) {
		this.forms = forms;
	}

	public String getTables() {
		return tables;
	}

	public void setTables(String tables) {
		this.tables = tables;
	}

	public String getAgents() {
		return agents;
	}

	public void setAgents(String agents) {
		this.agents = agents;
	}

	public String getWorkflows() {
		return workflows;
	}

	public void setWorkflows(String workflows) {
		this.workflows = workflows;
	}

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public String getFormControls() {
		return formControls;
	}

	public void setFormControls(String formControls) {
		this.formControls = formControls;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getTableFields() {
		return tableFields;
	}

	public void setTableFields(String tableFields) {
		this.tableFields = tableFields;
	}

	private String forms;
	
	private String tables;
	
	private String agents;

	private String workflows;

	private String scripts;

	private String formControls;

	private String properties;
	
	private String menus;

	private String tableFields;

	@Override
	public String toString() {
		return "ObjectsToMergeBean [getForms()=" + getForms()
				+ ", getTables()=" + getTables() + ", getAgents()="
				+ getAgents() + ", getWorkflows()=" + getWorkflows()
				+ ", getScripts()=" + getScripts() + ", getFormControls()="
				+ getFormControls() + ", getProperties()=" + getProperties()
				+ ", getMenus()=" + getMenus() + ", getTableFields()="
				+ getTableFields() + "]";
	}

}