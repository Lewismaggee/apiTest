package com.yungui.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class GetDeviceIntendListResult {
	private Object dubboResponse;
	private String status;
	private String successMsg;
	private String errorCode;
	private String errorMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	private String warningMsg;
	private Map<String,Object> result;
	private int total;
	private List<Map<String,Object>> list;
	private long id;
	private long deviceid;
	private String postmantel;
	private String microaccount;
	private long create_id;
	private Date create_time;
	private int isRemaind;
	public Object getDubboResponse() {
		return dubboResponse;
	}
	public void setDubboResponse(Object dubboResponse) {
		this.dubboResponse = dubboResponse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getWarningMsg() {
		return warningMsg;
	}
	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(long deviceid) {
		this.deviceid = deviceid;
	}
	public String getPostmantel() {
		return postmantel;
	}
	public void setPostmantel(String postmantel) {
		this.postmantel = postmantel;
	}
	public String getMicroaccount() {
		return microaccount;
	}
	public void setMicroaccount(String microaccount) {
		this.microaccount = microaccount;
	}
	public long getCreate_id() {
		return create_id;
	}
	public void setCreate_id(long create_id) {
		this.create_id = create_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getIsRemaind() {
		return isRemaind;
	}
	public void setIsRemaind(int isRemaind) {
		this.isRemaind = isRemaind;
	}
}
