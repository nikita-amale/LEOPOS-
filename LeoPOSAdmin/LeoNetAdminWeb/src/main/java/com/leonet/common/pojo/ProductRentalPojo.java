/**
 * 
 */
package com.leonet.common.pojo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author Moninder
 *
 */
public class ProductRentalPojo {

	private long rproductId;
	private String rcode;
	private String name;
	private int unit;
	private float cost;
	private float rprice;
	private float alert_quantity;
	private String image;
	private long category_id;
	private long subcategory_id;
	private int tax_rate;
	private int quantity;
	private String product_details;
	private String tax_method;
	private Date creation_date;
	private String brand;
private String productFileName;
	
	private MultipartFile productImage ;
	
	private MultipartFile updatedImage ;
	
	
	public long getRproductId() {
		return rproductId;
	}
	public void setRproductId(long rproductId) {
		this.rproductId = rproductId;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getRprice() {
		return rprice;
	}
	public void setRprice(float rprice) {
		this.rprice = rprice;
	}
	public float getAlert_quantity() {
		return alert_quantity;
	}
	public void setAlert_quantity(float alert_quantity) {
		this.alert_quantity = alert_quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public long getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public int getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(int tax_rate) {
		this.tax_rate = tax_rate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct_details() {
		return product_details;
	}
	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}
	public String getTax_method() {
		return tax_method;
	}
	public void setTax_method(String tax_method) {
		this.tax_method = tax_method;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	public String getProductFileName() {
		return productFileName;
	}
	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}
	public MultipartFile getProductImage() {
		return productImage;
	}
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public MultipartFile getUpdatedImage() {
		return updatedImage;
	}
	public void setUpdatedImage(MultipartFile updatedImage) {
		this.updatedImage = updatedImage;
	}
	@Override
	public String toString() {
		return "ProductRentalPojo [rproductId=" + rproductId + ", rcode=" + rcode + ", name=" + name + ", unit=" + unit
				+ ", cost=" + cost + ", rprice=" + rprice + ", alert_quantity=" + alert_quantity + ", image=" + image
				+ ", category_id=" + category_id + ", subcategory_id=" + subcategory_id + ", tax_rate=" + tax_rate
				+ ", quantity=" + quantity + ", product_details=" + product_details + ", tax_method=" + tax_method
				+ ", creation_date=" + creation_date + ", brand=" + brand + ", productFileName=" + productFileName
				+ ", productImage=" + productImage + ", updatedImage=" + updatedImage + "]";
	}
	

 
}
