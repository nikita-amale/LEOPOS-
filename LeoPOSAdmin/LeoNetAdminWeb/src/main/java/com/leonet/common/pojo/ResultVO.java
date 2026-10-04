package com.leonet.common.pojo;


public class ResultVO {

	public String msgCode;
	public String msgDescr;
	public boolean isError;
	public String success;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgDescr() {
		return msgDescr;
	}

	public void setMsgDescr(String msgDescr) {
		this.msgDescr = msgDescr;
	}



	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	public ResultVO(String msgCode, String msgDescr, boolean isError, String success) {
		super();
		this.msgCode = msgCode;
		this.msgDescr = msgDescr;
		this.isError = isError;
		this.success = success;
	}
	
	

	public ResultVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Info [msgDescr=" + msgDescr + ", msgCode=" + msgCode + ", isError=" + isError + "]";
	}

}
