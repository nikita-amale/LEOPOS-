package com.leonet.constant;

public class CommonConstant {

	public enum ProfileConstant {
		REQUEST_QUOTE("REQUEST_QUOTE"), SALE("SALE");

		private String profile;

		private ProfileConstant(String profile) {
			this.profile = profile;
		}

		public String getProfile() {
			return profile;
		}
	}
}
