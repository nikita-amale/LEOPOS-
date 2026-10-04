/**
 * 
 */
package com.leonet.common.pojo;

/**
 * @author Moninder
 *
 */
public class AddPaymentReqPojo {

	private Long saleId;
	private Float amount;
	private String ptype;
	private String pref;
	private String note;
	private Long memberId;
	private String membername;
	private Float  payamount;
	private String ctype;
	
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPref() {
		return pref;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	
	public Float getPayamount() {
		return payamount;
	}
	public void setPayamount(Float payamount) {
		this.payamount = payamount;
	}
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	@Override
	public String toString() {
		return "AddPaymentReqPojo [saleId=" + saleId + ", amount=" + amount + ", ptype=" + ptype + ", pref=" + pref
				+ ", note=" + note + ", memberId=" + memberId + ", membername=" + membername + ", payamount="
				+ payamount + ", ctype=" + ctype + "]";
	}
		 
}
