package com.leonet.common.pojo;

public class MemberDtlVO {

	private Long id;
	 
    private String userName;
    private String password;
    private String name;
    private String email;
    private String gender;
    private String city;
    private String country;
    private String address;
    private String DOB;
    private String role;
    private String otp;
    private int tgpts;
    private int bpts;
    private float deposit;
    
    private String phonemain;
    private String phonealter;
    private String phonewhatsapp;
    private String pincode;
    private double creditpayment;
    private int plan_id;
        
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
		
	public int getTgpts() {
		return tgpts;
	}
	public void setTgpts(int tgpts) {
		this.tgpts = tgpts;
	}
	public int getBpts() {
		return bpts;
	}
	public void setBpts(int bpts) {
		this.bpts = bpts;
	}
	
	public float getDeposit() {
		return deposit;
	}
	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}
	
	
	public String getPhonemain() {
		return phonemain;
	}
	public void setPhonemain(String phonemain) {
		this.phonemain = phonemain;
	}
	public String getPhonealter() {
		return phonealter;
	}
	public void setPhonealter(String phonealter) {
		this.phonealter = phonealter;
	}
	public String getPhonewhatsapp() {
		return phonewhatsapp;
	}
	public void setPhonewhatsapp(String phonewhatsapp) {
		this.phonewhatsapp = phonewhatsapp;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	
	
	
	public double getCreditpayment() {
		return creditpayment;
	}
	public void setCreditpayment(double creditpayment) {
		this.creditpayment = creditpayment;
	}
	@Override
	public String toString() {
		return "MemberDtlVO [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", gender=" + gender + ", city=" + city + ", country=" + country + ", address="
				+ address + ", DOB=" + DOB + ", role=" + role + ", otp=" + otp + ", tgpts=" + tgpts + ", bpts=" + bpts
				+ ", deposit=" + deposit + ", phonemain=" + phonemain + ", phonealter=" + phonealter
				+ ", phonewhatsapp=" + phonewhatsapp + ", pincode=" + pincode + ", creditpayment=" + creditpayment
				+ ", plan_id=" + plan_id + "]";
	}
	
	   
	    
	    
}
