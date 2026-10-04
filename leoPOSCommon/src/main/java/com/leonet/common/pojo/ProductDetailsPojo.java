/**
 * 
 */
package com.leonet.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



/**
 * @author Moninder
 *
 */
public class ProductDetailsPojo {

	private long productId;
	private String code;
	private String name;
	private int unit;
	private float cost;
	private BigDecimal price;
	private float rollprice;
	private float alert_quantity;
	private String image;
	private long category_id;
	private long subcategory_id;
	private String cf1;
	private int tax_rate;
	private int track_quantity;
	private String details;
	private int warehouse;
	private String barcode_symbology;
	private String file;
	private String product_details;
	private String tax_method;
	private String type;
	private int promotion;
	private float promo_price;
	private Date start_date;
	private Date end_date;
	private int sale_unit;
	private int purchase_unit;
	private int brand;
	private String slug;
	private int featured;
	private float weight;
	private int hsn_code;
	private int views;
	private int hide;
	private String second_name;
	private int quantity;
	
	private String productFileName;
	
	private MultipartFile productImage ;
	
	private MultipartFile updatedImage ;
	
	
	public MultipartFile getUpdatedImage() {
		return updatedImage;
	}
	public void setUpdatedImage(MultipartFile updatedImage) {
		this.updatedImage = updatedImage;
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
	public float getrollprice() {
		return rollprice;
	}

	public void setrollprice(float rollprice) {
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
		this.subcategory_id =subcategory_id;
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
		this.tax_rate =tax_rate;
	}
	
	public int gettrack_quantity() {
		return track_quantity;
	}

	public void settrack_quantity(int track_quantity) {
		this.track_quantity =track_quantity;
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
		this.warehouse =warehouse;
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
		this.promotion =promotion;
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
		this.hsn_code =hsn_code;
	}
	
	public int getviews() {
		return views;
	}

	public void setviews(int views) {
		this.views =views;
	}
	public int gethide() {
		return hide;
	}

	public void hide(int hide) {
		this.hide =hide;
	}
	
	public String getsecond_name() {
		return second_name;
	}
	public void second_name(String second_name) {
		this.second_name = second_name;
	}	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductDetailsPojo [productId=" + productId + ", code=" + code + ", name=" + name + ", unit=" + unit
				+ ", cost=" + cost + ", price=" + price + ", rollprice=" + rollprice + ", alert_quantity="
				+ alert_quantity + ", image=" + image + ", category_id=" + category_id + ", subcategory_id="
				+ subcategory_id + ", cf1=" + cf1 + ", tax_rate=" + tax_rate + ", track_quantity=" + track_quantity
				+ ", details=" + details + ", warehouse=" + warehouse + ", barcode_symbology=" + barcode_symbology
				+ ", file=" + file + ", product_details=" + product_details + ", tax_method=" + tax_method + ", type="
				+ type + ", promotion=" + promotion + ", promo_price=" + promo_price + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", sale_unit=" + sale_unit + ", purchase_unit=" + purchase_unit
				+ ", brand=" + brand + ", slug=" + slug + ", featured=" + featured + ", weight=" + weight
				+ ", hsn_code=" + hsn_code + ", views=" + views + ", hide=" + hide + ", second_name=" + second_name
				+ ", quantity=" + quantity + ", productFileName=" + productFileName + ", productImage=" + productImage
				+ ", updatedImage=" + updatedImage + "]";
	}
	

 
}
