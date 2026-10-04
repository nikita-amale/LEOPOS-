package com.leonet.common.pojo;

public class UserDtlVO {

	    private Long id;
	    private String userUuid;
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
		@Override
		public String toString() {
			return "UserDtlVO [id=" + id + ", userUuid=" + userUuid + ", username=" + username + ", password="
					+ password + ", name=" + name + ", email=" + email + ", gender=" + gender + ", city=" + city
					+ ", country=" + country + ", address=" + address + ", DOB=" + DOB + ", role=" + role + ", otp="
					+ otp + "]";
		}
		 
	    
	    
	    
}
