package com.yungui.entity;

public class Authorization {
	private String status;
	private String token;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Authorization [status=" + status + ", token=" + token + "]";
	}
	
	
}
