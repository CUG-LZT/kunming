package com.cug.lab.utils;

public class Page {
	private int page;
	private int rows;
	private int courent;
	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", courent=" + courent + "]";
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCourent() {
		return courent;
	}
	public void setCourent() {
		this.courent  = (page-1)*rows;
	}

}
