package com.yungui.entity;

import java.util.Map;

public class GetTerminalPostmanPackByIdResult {
	private Object response;
	private String status;
	private String successMsg;
	private String errorCode;
	private String errorMsg;
	private String warningMsg;
	private Map<String,Object> result;
	private int id;
	private String out_trade_no;
	private long deviceId;
	private String terminalId;
	private String terminalCode;
	private String postmanTel;
	private String microaccount;
	private double packPrice;
	private int packDays;
	private int renewals;
	private int packMonths;
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
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
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getPostmanTel() {
		return postmanTel;
	}
	public void setPostmanTel(String postmanTel) {
		this.postmanTel = postmanTel;
	}
	public String getMicroaccount() {
		return microaccount;
	}
	public void setMicroaccount(String microaccount) {
		this.microaccount = microaccount;
	}
	public double getPackPrice() {
		return packPrice;
	}
	public void setPackPrice(double packPrice) {
		this.packPrice = packPrice;
	}
	public int getPackDays() {
		return packDays;
	}
	public void setPackDays(int packDays) {
		this.packDays = packDays;
	}
	public int getRenewals() {
		return renewals;
	}
	public void setRenewals(int renewals) {
		this.renewals = renewals;
	}
	public int getPackMonths() {
		return packMonths;
	}
	public void setPackMonths(int packMonths) {
		this.packMonths = packMonths;
	}
	
}
