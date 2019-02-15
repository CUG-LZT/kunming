package com.cug.lab.utils;

import java.util.List;

public class EasyuiData<T> {

	@Override
	public String toString() {
		return "[total=" + total + ", rows=" + rows + "]";
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	private int total;
	private List<T> rows;
	
 }
