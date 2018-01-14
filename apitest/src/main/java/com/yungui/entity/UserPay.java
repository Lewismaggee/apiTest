package com.yungui.entity;

import java.io.Serializable;

public class UserPay implements Serializable {
	private static final long serialVersionUID = -1L;
	private String name;
	private String card;
	private double money;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserPay [name=" + name + ", card=" + card + ", money=" + money + "]";
	}
	
	
}
