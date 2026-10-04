package com.leonet.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import com.leonet.common.pojo.CustomerReportPojo;
import com.leonet.common.pojo.SaleReportSummaryPojo;

@Entity
@Table(name = "users")
@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "find_customer_report", query = "SELECT U.user_id User_id \r\n"
				+ "				,U.name NAME\r\n"
				+ "				,U.email Email \r\n"
				+ "				,U.phonemain Phone \r\n"
				+ "				,U.ctype TYPE\r\n"
				+ "				,COUNT(*) Total_Sales,ROUND(SUM(grand_total),2) Total_Amount\r\n"
				+ "						,ROUND(SUM(paid),2) Total_Paid, ROUND(SUM(grand_total),2)-ROUND(SUM(paid),2) Balance\r\n"
				+ "						 FROM users U, sales S\r\n"
				+ "						WHERE S.memberid = U.user_id AND S.is_active=0\r\n"
				+ "						GROUP BY U.user_id\r\n"
				+ "				 UNION ALL SELECT UU.user_id User_id ,UU.name NAME ,\r\n"
				+ "				UU.email Email ,\r\n"
				+ "				UU.phonemain Phone ,\r\n"
				+ "				UU.ctype TYPE,\r\n"
				+ "				COUNT(*) Total_Sales,ROUND(SUM(grand_total),2) Total_Amount,\r\n"
				+ "				ROUND(SUM(paid),2) Total_Paid, ROUND(SUM(grand_total),2)-ROUND(SUM(paid),2)-ROUND(UU.creditpayment,2) Balance\r\n"
				+ "				 FROM users UU, specialsales SS\r\n"
				+ "					WHERE SS.memberid = UU.user_id AND SS.is_active=0 \r\n"
				+ "				 GROUP BY UU.user_id" , resultSetMapping = "report_customer_dto"),

		})
@SqlResultSetMappings(value = {
	
		@SqlResultSetMapping(name = "report_customer_dto", classes = @ConstructorResult(targetClass = CustomerReportPojo.class, columns = {
				@ColumnResult(name = "User_id", type = Long.class),
				@ColumnResult(name = "Name", type = String.class),
				@ColumnResult(name = "Email", type = String.class),
				@ColumnResult(name = "Phone", type = String.class),
				@ColumnResult(name = "type", type = String.class),
				@ColumnResult(name = "Total_Sales", type = Double.class),
				@ColumnResult(name = "Total_Amount", type = Double.class),
				@ColumnResult(name = "Total_Paid", type = Double.class), 
		        @ColumnResult(name = "Balance", type = Double.class) })) 
		})
public class User  {

	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String userUuid=UUID.randomUUID().toString();
    private String username;
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
    private int plan_id;
    
    private String child1;
    private String child1age;
    private String child2;
    private String child2age;
    private String ctype; // Special /Wholesale / General
    private Date creation_date;
    private int status;
	@Column(name = "pricegroup")
	private String pricegroup;
	@Column(name = "threshholdamount ")
	private float threshholdamount;
	@Column(name = "threshholddays  ")
	private int threshholddays;
	 private String contactname;
		@Column(name = "creditpayment")
		private double creditpayment;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
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
	public String getChild1() {
		return child1;
	}
	public void setChild1(String child1) {
		this.child1 = child1;
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
	
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	
	public double getCreditpayment() {
		return creditpayment;
	}
	public void setCreditpayment(double creditpayment) {
		this.creditpayment = creditpayment;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userUuid=" + userUuid + ", username=" + username + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", gender=" + gender + ", city=" + city + ", country="
				+ country + ", address=" + address + ", DOB=" + DOB + ", role=" + role + ", otp=" + otp + ", tgpts="
				+ tgpts + ", bpts=" + bpts + ", deposit=" + deposit + ", phonemain=" + phonemain + ", phonealter="
				+ phonealter + ", phonewhatsapp=" + phonewhatsapp + ", pincode=" + pincode + ", plan_id=" + plan_id
				+ ", child1=" + child1 + ", child1age=" + child1age + ", child2=" + child2 + ", child2age=" + child2age
				+ ", ctype=" + ctype + ", creation_date=" + creation_date + ", status=" + status + ", pricegroup="
				+ pricegroup + ", threshholdamount=" + threshholdamount + ", threshholddays=" + threshholddays
				+ ", contactname=" + contactname + ", creditpayment=" + creditpayment + "]";
	}
  
}
