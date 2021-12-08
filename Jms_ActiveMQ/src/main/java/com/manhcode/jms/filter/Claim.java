package com.manhcode.jms.filter;

import java.io.Serializable;

public class Claim implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4226336967079209871L;
	
	private int hospitalId;
	private String doctorName;
	private String doctorType;
	private String insuaranceProvider;
	private double claimAmount;
	
	public Claim() {}

	public Claim(int hospitalId, String doctorName, String doctorType, String insuaranceProvider, double claimAmount) {
		this.hospitalId = hospitalId;
		this.doctorName = doctorName;
		this.doctorType = doctorType;
		this.insuaranceProvider = insuaranceProvider;
		this.claimAmount = claimAmount;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	public String getInsuaranceProvider() {
		return insuaranceProvider;
	}

	public void setInsuaranceProvider(String insuaranceProvider) {
		this.insuaranceProvider = insuaranceProvider;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Claim [hospitalId=" + hospitalId + ", doctorName=" + doctorName + ", doctorType=" + doctorType
				+ ", insuaranceProvider=" + insuaranceProvider + ", claimAmount=" + claimAmount + "]";
	}
	
	

}
