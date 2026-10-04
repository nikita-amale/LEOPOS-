package com.leonet.entity;

import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.leonet.common.pojo.PaymentReportPojo;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;



@Entity
@Table(name = "payment")
@NamedNativeQueries(value = {
	    @NamedNativeQuery(name = "find_Payment_Report", query =
	        "SELECT\n" +
	        "    id,\n" +
	        "    bulkid,\n" +
	        "    grand_total,\n" +
	        "    ptype,\n" +
	        "    memberid,\n" +
	        "    member_name,\n" +
	        "    DATE\n" +
	        "FROM (\n" +
	        "    SELECT\n" +
	        "        p.id,\n" +
	        "        p.bulkid,\n" +
	        "        p.grand_total,\n" +
	        "        p.ptype,\n" +
	        "        p.memberid,\n" +
	        "        p.member_name,\n" +
	        "        p.paymentdate AS DATE\n" +
	        "    FROM\n" +
	        "        payment p\n" +
	        "    WHERE\n" +
	        "        p.bulkid = 0\n" +
	        "    UNION ALL\n" +
	        "\n" +
	        "    SELECT\n" +
	        "        bp.paymentid AS id,\n" +
	        "        bp.bulk_id AS bulkid,\n" +
	        "        bp.amount AS grand_total,\n" +
	        "        bp.ptype,\n" +
	        "        bp.member_id AS memberid,\n" +
	        "        bp.member_name,\n" +
	        "        bp.date\n" +
	        "    FROM\n" +
	        "        bulkpayment bp\n" +
	        ") AS combined_results\n" +
	        "ORDER BY DATE DESC\n" ,
	    
	        resultSetMapping = "report_Payment_Summary_dto"
	    )
	})

@SqlResultSetMappings(value = {
	    @SqlResultSetMapping(name = "report_Payment_Summary_dto", classes = @ConstructorResult(targetClass = PaymentReportPojo.class, columns = {
	        @ColumnResult(name = "id", type = Long.class),
	        @ColumnResult(name = "bulkid", type = Long.class),
	        @ColumnResult(name = "grand_total", type = double.class),
	        @ColumnResult(name = "ptype", type = String.class),
	        @ColumnResult(name = "memberid", type = Long.class),
	        @ColumnResult(name = "member_name", type = String.class),
	        @ColumnResult(name = "DATE", type = Date.class)
	    }))
	})







public class PaymentEntity {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "rsaleId")
	private long rsaleId;
	
    @Column(name = "paymentdate")
	private Date paymentdate;
    
    @Column(name = "referenceno")
	private String referenceno;
    
    @Column(name = "salesreferenceno")
	private String salesreferenceno;
	
    @Column(name = "memberid")
	private long memberid;
	
    @Column(name = "member_name")
	private String member_name;
	
    @Column(name = "grand_total")
	private double grand_total;
    
    @Column(name = "ptype")
	private String ptype;
    
    @Column(name = "pref")
	private String pref;
	
    @Column(name = "status")
	private String status;
    
	@Column(name = "bulkid")
	private long bulkid;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "ctype")
	private String ctype;

	    
    public long getRsaleId() {
		return rsaleId;
	}

	public void setRsaleId(long rsaleId) {
		this.rsaleId = rsaleId;
	}
	
	
	
	public String getSalesreferenceno() {
		return salesreferenceno;
	}

	public void setSalesreferenceno(String salesreferenceno) {
		this.salesreferenceno = salesreferenceno;
	}

	public long getMemberid() {
		return memberid;
	}

	public void setMember_id(long memberid) {
		this.memberid = memberid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}
	
	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}
	
	public long getBulkid() {
		return bulkid;
	}

	public void setBulkid(long bulkid) {
		this.bulkid = bulkid;
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

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}
	

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + ", rsaleId=" + rsaleId + ", paymentdate=" + paymentdate + ", referenceno="
				+ referenceno + ", salesreferenceno=" + salesreferenceno + ", memberid=" + memberid + ", member_name="
				+ member_name + ", grand_total=" + grand_total + ", ptype=" + ptype + ", pref=" + pref + ", status="
				+ status + ", bulkid=" + bulkid + ", note=" + note + ", ctype=" + ctype + "]";
	}

	

	

	

	
	


}
