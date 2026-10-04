/**
 * 
 */
package com.leonet.common.pojo;

import java.util.Date;

/**
 * @author Moninder
 *
 */
public class UserRegistrationPojo {

	private Long id;
	private String email;
	private String userName;
	private String password;
	private String name;
	private String firstemail;
	private String secondemail;
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
	private long plan_id;
	private String ctype;
	private String creditfacility;
	private String pricegroup;

	private String company;
	private String child1age;
	private String child2;
	private String child2age;
	private Date creation_date;
	private int status;
	private double creditpayment;
	private float threshholdamount;
	private int threshholddays;
	private int blocked;
	private String contactname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getFirstemail() {
		return firstemail;
	}

	public void setFirstemail(String firstemail) {
		this.firstemail = firstemail;
	}

	public String getSecondemail() {
		return secondemail;
	}

	public void setSecondemail(String secondemail) {
		this.secondemail = secondemail;
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

	public long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	public String getCreditfacility() {
		return creditfacility;
	}

	public void setCreditfacility(String creditfacility) {
		this.creditfacility = creditfacility;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getChild1age() {
		return child1age;
	}

	public void setChild1age(String child1age) {
		this.child1age = child1age;
	}

	public String getChild2() {
		return child2;
	}

	public void setChild2(String child2) {
		this.child2 = child2;
	}

	public String getChild2age() {
		return child2age;
	}

	public void setChild2age(String child2age) {
		this.child2age = child2age;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public double getCreditpayment() {
		return creditpayment;
	}

	public void setCreditpayment(double creditpayment) {
		this.creditpayment = creditpayment;
	}
	

	public String getPricegroup() {
		return pricegroup;
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}

	public float getThreshholdamount() {
		return threshholdamount;
	}

	public void setThreshholdamount(float threshholdamount) {
		this.threshholdamount = threshholdamount;
	}

	public int getThreshholddays() {
		return threshholddays;
	}

	public void setThreshholddays(int threshholddays) {
		this.threshholddays = threshholddays;
	}
	

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}
	

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	@Override
	public String toString() {
		return "UserRegistrationPojo [id=" + id + ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", name=" + name + ", firstemail=" + firstemail + ", secondemail=" + secondemail
				+ ", gender=" + gender + ", city=" + city + ", country=" + country + ", address=" + address + ", DOB="
				+ DOB + ", role=" + role + ", otp=" + otp + ", tgpts=" + tgpts + ", bpts=" + bpts + ", deposit="
				+ deposit + ", phonemain=" + phonemain + ", phonealter=" + phonealter + ", phonewhatsapp="
				+ phonewhatsapp + ", pincode=" + pincode + ", plan_id=" + plan_id + ", ctype=" + ctype
				+ ", creditfacility=" + creditfacility + ", pricegroup=" + pricegroup + ", company=" + company
				+ ", child1age=" + child1age + ", child2=" + child2 + ", child2age=" + child2age + ", creation_date="
				+ creation_date + ", status=" + status + ", creditpayment=" + creditpayment + ", threshholdamount="
				+ threshholdamount + ", threshholddays=" + threshholddays + ", blocked=" + blocked + ", contactname="
				+ contactname + "]";
	}

	

	

	


	
	


}
