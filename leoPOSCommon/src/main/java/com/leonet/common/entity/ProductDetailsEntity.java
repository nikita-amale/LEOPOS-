/**

 * 
 */
package com.leonet.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author YOGESH
 *
 */
@Entity
@Table(name = "product_Details")
public class ProductDetailsEntity {

	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private long productId;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "unit")
	private int unit;

	@Column(name = "cost")
	private float cost;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "rollprice")
	private Float rollprice;

	@Column(name = "alert_quantity")
	private float alert_quantity;

	@Column(name = "image")
	private String image;

	@Column(name = "category_id")
	private long category_id;

	@Column(name = "subcategory_id")
	private long subcategory_id;

	@Column(name = "cf1")
	private String cf1;

	@Column(name = "tax_rate")
	private int tax_rate;

	@Column(name = "track_quantity")
	private int track_quantity;

	@Column(name = "details")
	private String details;

	@Column(name = "warehouse")
	private int warehouse;

	@Column(name = "barcode_symbology")
	private String barcode_symbology;

	@Column(name = "file")
	private String file;

	@Column(name = "product_details")
	private String product_details;

	@Column(name = "tax_method")
	private String tax_method;

	@Column(name = "type")
	private String type;

	@Column(name = "promotion")
	private int promotion;

	@Column(name = "promo_price")
	private float promo_price;

	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "end_date")
	private Date end_date;

	@Column(name = "sale_unit")
	private int sale_unit;

	@Column(name = "purchase_unit")
	private int purchase_unit;

	@Column(name = "brand")
	private int brand;

	@Column(name = "slug")
	private String slug;

	@Column(name = "featured")
	private int featured;

	@Column(name = "weight")
	private float weight;

	@Column(name = "hsn_code")
	private int hsn_code;

	@Column(name = "views")
	private int views;

	@Column(name = "hide")
	private int hide;

	@Column(name = "second_name")
	private String second_name;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "product_file_name")
	private String productFileName;

	@Column(name = "product_file_path")
	private String productFilePath;

	public String getProductFileName() {
		return productFileName;
	}

	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}

	public String getProductFilePath() {
		return productFilePath;
	}

	public void setProductFilePath(String productFilePath) {
		this.productFilePath = productFilePath;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getunit() {
		return unit;
	}

	public void setunit(int unit) {
		this.unit = unit;
	}

	public float getcost() {
		return cost;
	}

	public void setcost(float cost) {
		this.cost = cost;
	}

	public Float getrollprice() {
		return rollprice;
	}

	public void setrollprice(Float rollprice) {
		this.rollprice = rollprice;
	}

	public BigDecimal getprice() {
		return price;
	}

	public void setprice(BigDecimal price) {
		this.price = price;
	}

	public float getalert_quantity() {
		return alert_quantity;
	}

	public void setalert_quantity(float alert_quantity) {
		this.alert_quantity = alert_quantity;
	}

	public String getimage() {
		return image;
	}

	public void setimage(String image) {
		this.image = image;
	}

	public long getcategory_id() {
		return category_id;
	}

	public void setcategory_id(long category_id) {
		this.category_id = category_id;
	}

	public long getsubcategory_id() {
		return subcategory_id;
	}

	public void setsubcategory_id(long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public String getcf1() {
		return cf1;
	}

	public void setcf1(String cf1) {
		this.cf1 = cf1;
	}

	public int gettax_rate() {
		return tax_rate;
	}

	public void settax_rate(int tax_rate) {
		this.tax_rate = tax_rate;
	}

	public int gettrack_quantity() {
		return track_quantity;
	}

	public void settrack_quantity(int track_quantity) {
		this.track_quantity = track_quantity;
	}

	public String getdetails() {
		return details;
	}

	public void setdetails(String details) {
		this.details = details;
	}

	public int getwarehouse() {
		return warehouse;
	}

	public void setwarehouse(int warehouse) {
		this.warehouse = warehouse;
	}

	public String getbarcode_symbology() {
		return barcode_symbology;
	}

	public void setbarcode_symbology(String barcode_symbology) {
		this.barcode_symbology = barcode_symbology;
	}

	public String getproduct_details() {
		return product_details;
	}

	public void setproduct_details(String product_details) {
		this.product_details = product_details;
	}

	public String getfile() {
		return file;
	}

	public void setfile(String file) {
		this.file = file;
	}

	public String gettax_method() {
		return tax_method;
	}

	public void tax_method(String tax_method) {
		this.tax_method = tax_method;
	}

	public String gettype() {
		return type;
	}

	public void type(String type) {
		this.type = type;
	}

	public int getpromotion() {
		return promotion;
	}

	public void setpromotion(int promotion) {
		this.promotion = promotion;
	}

	public float getpromo_price() {
		return promo_price;
	}

	public void setpromo_price(float promo_price) {
		this.promo_price = promo_price;
	}

	public Date getstart_date() {
		return start_date;
	}

	public void setstart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getend_date() {
		return end_date;
	}

	public void setend_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getsale_unit() {
		return sale_unit;
	}

	public void setsale_unit(int sale_unit) {
		this.sale_unit = sale_unit;
	}

	public int getpurchase_unit() {
		return purchase_unit;
	}

	public void setpurchase_unit(int purchase_unit) {
		this.purchase_unit = purchase_unit;
	}

	public int getbrand() {
		return brand;
	}

	public void setbrand(int brand) {
		this.brand = brand;
	}

	public int getfeatured() {
		return featured;
	}

	public void setfeatured(int featured) {
		this.featured = featured;
	}

	public String getslug() {
		return slug;
	}

	public void setslug(String slug) {
		this.slug = slug;
	}

	public float getweight() {
		return weight;
	}

	public void setweight(float weight) {
		this.weight = weight;
	}

	public int gethsn_code() {
		return hsn_code;
	}

	public void sethsn_code(int hsn_code) {
		this.hsn_code = hsn_code;
	}

	public int getviews() {
		return views;
	}

	public void setviews(int views) {
		this.views = views;
	}

	public int gethide() {
		return hide;
	}

	public void hide(int hide) {
		this.hide = hide;
	}

	public String getsecond_name() {
		return second_name;
	}

	public void second_name(String second_name) {
		this.second_name = second_name;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductDetailsEntity [productId=" + productId + ", code=" + code + ", name=" + name + ", unit=" + unit
				+ ", cost=" + cost + ", price=" + price + ", rollprice=" + rollprice + ", alert_quantity="
				+ alert_quantity + ", image=" + image + ", category_id=" + category_id + ", subcategory_id="
				+ subcategory_id + ", cf1=" + cf1 + ", tax_rate=" + tax_rate + ", track_quantity=" + track_quantity
				+ ", details=" + details + ", warehouse=" + warehouse + ", barcode_symbology=" + barcode_symbology
				+ ", file=" + file + ", product_details=" + product_details + ", tax_method=" + tax_method + ", type="
				+ type + ", promotion=" + promotion + ", promo_price=" + promo_price + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", sale_unit=" + sale_unit + ", purchase_unit=" + purchase_unit
				+ ", brand=" + brand + ", slug=" + slug + ", featured=" + featured + ", weight=" + weight
				+ ", hsn_code=" + hsn_code + ", views=" + views + ", hide=" + hide + ", second_name=" + second_name
				+ ", quantity=" + quantity + ", productFileName=" + productFileName + ", productFilePath="
				+ productFilePath + "]";
	}

}
