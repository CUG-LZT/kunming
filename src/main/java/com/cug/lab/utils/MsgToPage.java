package com.cug.lab.utils;

import java.util.List;

public class MsgToPage<T>{

	private int code;            //解析接口状态 --- 200 ok  400 error 
	private String msg;          //解析提示文本
	private int count;           //解析数据长度
	private List<T> data;        //解析数据列表
	
	public MsgToPage() {
	}
	
	public MsgToPage(int code, String msg, int count, List<T> data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MsgToPage [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	} 
}
