package com.leonet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author t
 *
 */
@Entity
@Table(name = "users")
public class MemberUser {

	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String username;
    
    private String password;
    private String role;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "dob")
    private String DOB;
    
    @Column(name = "tgpts")
    private int tgpts;
    
    @Column(name = "bpts")
    private int bpts;
    
    @Column(name = "deposit")
    private double deposit;

        
	@Column(name = "firstemail")
	private String email;
	
    
	@Column(name = "secondemail")
	private String secondemail;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "phonemain")
	private String phonemain;
	
	@Column(name = "phonealter")
    private String phonealter;
	
	@Column(name = "phonewhatsapp")
    private String phonewhatsapp;
	
	@Column(name = "pincode")
    private String pincode;
	
	@Column(name = "plan_id")
    private long plan_id;
    
	@Column(name = "company")
	private String company;
	
	@Column(name = "child1age")
	private String child1age;
	
	@Column(name = "child2")
	private String child2;
	
	@Column(name = "child2age")
	private String child2age;
	
	@Column(name = "creation_date")
	private Date creation_date;
	
	@Column(name = "ctype")
	private String ctype;
	
	@Column(name = "pricegroup")
	private String pricegroup;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "creditfacility")
	private String creditfacility;
	@Column(name = "creditpayment")
	private double creditpayment;
	
	@Column(name = "threshholdamount ")
	private float threshholdamount;
	@Column(name = "threshholddays  ")
	private int threshholddays;
	@Column(name = "blocked  ")
	private int blocked;
	@Column(name = "contactname")
	 private String contactname;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
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
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
		
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	public String getSecondemail() {
		return secondemail;
	}
	public void setSecondemail(String secondemail) {
		this.secondemail = secondemail;
	}
	
	public String getCreditfacility() {
		return creditfacility;
	}
	public void setCreditfacility(String creditfacility) {
		this.creditfacility = creditfacility;
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
		return "MemberUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", name=" + name + ", gender=" + gender + ", city=" + city + ", country=" + country + ", address="
				+ address + ", DOB=" + DOB + ", tgpts=" + tgpts + ", bpts=" + bpts + ", deposit=" + deposit + ", email="
				+ email + ", secondemail=" + secondemail + ", otp=" + otp + ", phonemain=" + phonemain + ", phonealter="
				+ phonealter + ", phonewhatsapp=" + phonewhatsapp + ", pincode=" + pincode + ", plan_id=" + plan_id
				+ ", company=" + company + ", child1age=" + child1age + ", child2=" + child2 + ", child2age="
				+ child2age + ", creation_date=" + creation_date + ", ctype=" + ctype + ", pricegroup=" + pricegroup
				+ ", status=" + status + ", creditfacility=" + creditfacility + ", creditpayment=" + creditpayment
				+ ", threshholdamount=" + threshholdamount + ", threshholddays=" + threshholddays + ", blocked="
				+ blocked + ", contactname=" + contactname + "]";
	}
	
	
	
	
	
    
}
