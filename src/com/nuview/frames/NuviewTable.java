package com.nuview.frames;

import javax.swing.table.DefaultTableModel;

public class NuviewTable extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int row, int column) {
		// all cells false
		return false;
	}

	public NuviewTable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}
}