package com.manhcode.jms.p2p;

import java.io.Serializable;

public class Patienttttt implements Serializable{
	private int id;
	private String name;
	private String insuaranceProvider;
	private Double copay;
	private Double amountToBePayed;
	
	public Patienttttt() {}

	public Patienttttt(int id, String name, String insuaranceProvider, Double copay, Double amountToBePayed) {
		this.id = id;
		this.name = name;
		this.insuaranceProvider = insuaranceProvider;
		this.copay = copay;
		this.amountToBePayed = amountToBePayed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInsuaranceProvider() {
		return insuaranceProvider;
	}

	public void setInsuaranceProvider(String insuaranceProvider) {
		this.insuaranceProvider = insuaranceProvider;
	}

	public Double getCopay() {
		return copay;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public Double getAmountToBePayed() {
		return amountToBePayed;
	}

	public void setAmountToBePayed(Double amountToBePayed) {
		this.amountToBePayed = amountToBePayed;
	}

	@Override
	public String toString() {
		return "Patienttttt [id=" + id + ", name=" + name + ", insuaranceProvider=" + insuaranceProvider + ", copay="
				+ copay + ", amountToBePayed=" + amountToBePayed + "]";
	}
	
	
}
