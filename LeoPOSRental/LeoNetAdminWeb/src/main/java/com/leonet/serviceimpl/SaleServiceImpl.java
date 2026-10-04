/**
 * 
 */
package com.leonet.serviceimpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.util.MailSendingAPI;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Path;
import com.itextpdf.text.pdf.parser.clipper.Paths;
import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.entity.MemberUser;
import com.leonet.entity.QuotesEntity;
import com.leonet.entity.QuotesItemEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.RequestQuotePojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.repo.QuotesItemRepo;
import com.leonet.repo.QuotesRepo;
import com.leonet.repo.RequestQuoteItemRepo;
import com.leonet.repo.RequestQuoteRepo;
import com.leonet.repo.SalesItemRepo;
import com.leonet.repo.SalesRepo;
import com.leonet.service.SaleService;

/**
 * @author MONINDER
 *
 */
@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	SalesRepo salesRepo;

	@Autowired
	SalesItemRepo salesItemRepo;

	@Autowired
	MemberUserRepo memberUserRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;

	@Autowired
	RequestQuoteRepo requestQuoteRepo;
	
	@Autowired
	QuotesRepo quotesRepo;

	@Autowired
	QuotesItemRepo quotesItemRepo;

	@Autowired
	RequestQuoteItemRepo requestQuoteItemRepo;

	@Autowired
	Mapper mapper;

	@Override
	public ResultVO addSale(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			SalesEntity saleEntity = new SalesEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				saleEntity.setDate(new Date());
				saleEntity.setMemberid(memberPojo.getId());
				saleEntity.setMember_name(memberPojo.getName());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = productDetailsPojo.getprice();
				quantity = Long.parseLong(additem.getQuantity());

				saleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (unit_price * 0.125 * quantity);

				saleEntity.setReferenceno("POS");

				saleEntity.setTotal_discount(0);
				saleEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			saleEntity.setOrder_tax(0);
			saleEntity.setProduct_tax(tax_rate);
			saleEntity.setPaymentstatus("Due");
			saleEntity.setOrder_discount(0);
			saleEntity.setTotal(total);
			saleEntity.setGrand_total(grand_total);
			SalesEntity saleenty = salesRepo.save(saleEntity);

			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				double tax = 0;

				tax = (productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity())) * .125;

				SalesItemEntity salesItemEntity = new SalesItemEntity();
				salesItemEntity.setId(saleenty.getSaleId());
				salesItemEntity.setProduct_id(additem.getProductId());
				salesItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				salesItemEntity.setItem_tax(additem.getPrice() * 0.125);
				salesItemEntity.setGst("12.5");
				salesItemEntity.setItem_discount(0d);
				salesItemEntity.setProduct_code(additem.getProductId().toString());
				salesItemEntity.setProduct_name(additem.getProductName());
				salesItemEntity.setReal_unit_price(Double.valueOf(productDetailsEnt.getprice()));
				salesItemEntity.setSubtotal(Double.valueOf(productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity())));
				salesItemEntity.setTax(Double.toString(tax));

				salesItemRepo.save(salesItemEntity);

				// Subtracting Quantities here from products

				productDetailsEnt
						.setQuantity(productDetailsEnt.getQuantity() - Integer.parseInt(additem.getQuantity()));
				productDetailsRepo.save(productDetailsEnt);
			}

			resultVO.setMsgDescr("Sale Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<UserRegistrationPojo> getCustomerList() {

		List<MemberUser> memberEntityList = new ArrayList<MemberUser>();
		List<UserRegistrationPojo> customerPojoList = new ArrayList<UserRegistrationPojo>();

		try {
			memberEntityList = memberUserRepo.findAll();
			customerPojoList = mapper.map(memberEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerPojoList;
	}

	@Override
	public List<SalePojo> getSalesList() {
		List<SalesEntity> salesEntityList = new ArrayList<SalesEntity>();
		List<SalePojo> salePojoList = new ArrayList<SalePojo>();
		try {
			System.out.println("in Sales");
			salesEntityList = salesRepo.findAll();
			for (SalesEntity salesEntityEntityRes : salesEntityList) {

				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
				salePojoList.add(salePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salePojoList;
	}

	@Override
	public ResultVO addRequestquote(List<AddItemReqPojo> addItemReqPojos) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();

		try {

			RequestQuoteEntity rqentity = new RequestQuoteEntity();
			String Supplier_Email = "support@leonet.in", Supplier_Name = "";

			Document document = new Document();

			String path = new File("").getAbsolutePath();

			System.out.println(path);

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				rqentity.setDate(new Date());
			//	rqentity.set(memberPojo.getId());
			//	rqentity.setMember_name(memberPojo.getName());
				Supplier_Email = additem.getEmail();
				Supplier_Name = additem.getNote();

				System.out.println("Member Pojo ....." + memberPojo.toString());

				rqentity.setReferenceno("POS");

				// saleEntity.setUser_id();
			}

			RequestQuoteEntity rqenty = requestQuoteRepo.save(rqentity);

			PdfWriter.getInstance(document, new FileOutputStream(
					path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + rqenty.getRqId() + ".pdf"));
			document.open();
			PdfPTable table = new PdfPTable(3);
			addTableHeader(table);

			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				RequestQuoteItemEntity rqItemEntity = new RequestQuoteItemEntity();
				rqItemEntity.setRqid(rqenty.getRqId());
				rqItemEntity.setProduct_id(additem.getProductId());
				rqItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rqItemEntity.setProduct_code(additem.getProductId().toString());
				rqItemEntity.setProduct_name(additem.getProductName());
				rqItemEntity.setMpn(productDetailsEnt.getcf1());

				requestQuoteItemRepo.save(rqItemEntity);
				addRows(table, rqItemEntity);
				// Make PDF and send email attachment
			}

			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("PO Raised for " + Supplier_Name, font);

			document.add(chunk);

			document.add(table);
			document.close();

			MailSendingAPI api = new MailSendingAPI();
			String from = "support@leonet.in", pass = "2c;UFEvB90", cc = "moninder@leonet.in",
					subject = "Quote Request", bcc = "", body = "Dear Customer, "
							+ "\n\nAttached please find invoice for Account number r more information.\n\n\nRegards,";

			// api.MailDepartment(from, pass, Supplier_Email, cc, subject, body, document);

			resultVO.setMsgDescr("Request Quote Added Sucessfully and mail sent");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Product Name", "MPN", "Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table, RequestQuoteItemEntity rqItemEntity) {
		table.addCell(rqItemEntity.getProduct_name());
		table.addCell(rqItemEntity.getMpn());
		table.addCell(String.valueOf(rqItemEntity.getQuantity()));
	}

	@Override
	public List<RequestQuotePojo> getRequestQuoteList() {
		List<RequestQuoteEntity> requestQuoteEntityList = new ArrayList<RequestQuoteEntity>();
		List<RequestQuotePojo> requestQuotePojoList = new ArrayList<RequestQuotePojo>();
		try {
			System.out.println("in Request Quote Listing");
			requestQuoteEntityList = requestQuoteRepo.findAll();
			for (RequestQuoteEntity rqEntityRes : requestQuoteEntityList) {

				RequestQuotePojo requestQuotePojo = new RequestQuotePojo();
				requestQuotePojo = mapper.map(rqEntityRes, RequestQuotePojo.class);
				requestQuotePojoList.add(requestQuotePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestQuotePojoList;
	}

	@Override
	public ResultVO downloadPDF(long id) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();
		try {
			
			System.out.println("Downloading \'Maven, Eclipse and OSGi working together\' PDF document...");
			String path = new File("").getAbsolutePath();
			File my_file = new File(path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + id + ".pdf"); // We are downloading .txt file, in the format of doc with name check - check.doc
			
			saveFileFromUrlWithJavaIO(
					 path + "/src/main/webapp/resources/PO_Supplies_DOWNLOADED_" + id + ".pdf",my_file );
			 
			 System.out.println("Downloaded \'Maven, Eclipse and OSGi working together\' PDF document.");
	         
	   
//			 String fileUrl = path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + id + ".pdf";
			 
			
	     	resultVO.setMsgDescr("Requested Quote Downloaded !");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;
		} 
		catch (MalformedURLException e) {
			 e.printStackTrace();
			 } catch (IOException e) {
			 e.printStackTrace();
			 }catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;

	}
	
	// Using Java IO
	 public static void saveFileFromUrlWithJavaIO(String fileName, File my_file)
	 throws MalformedURLException, IOException {
     FileInputStream in = null;
	 FileOutputStream fout = null;
	 try {
		  in = new FileInputStream(my_file);
		  fout = new FileOutputStream(fileName);
	 
	byte data[] = new byte[1024];
	 int count;
	 while ((count = in.read(data, 0, 1024)) != -1) {
	 fout.write(data, 0, count);
	 }
	 } finally {
	 if (in != null)
	 in.close();
	 if (fout != null)
	 fout.close();
	 }
	 }

	@Override
	public ResultVO addQuotes(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			QuotesEntity quotesEntity = new QuotesEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

		
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				quotesEntity.setDate(new Date());
				quotesEntity.setMember_id(memberPojo.getId());
				quotesEntity.setMember_name(memberPojo.getName());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = productDetailsPojo.getprice();
				quantity = Long.parseLong(additem.getQuantity());

				quotesEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (unit_price * 0.125 * quantity);

				quotesEntity.setReferenceno("POS");

				quotesEntity.setTotal_discount(0);
				quotesEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			quotesEntity.setOrder_tax(0);
			quotesEntity.setProduct_tax(tax_rate);
			quotesEntity.setPayment_status("Due");
			quotesEntity.setOrder_discount(0);
			quotesEntity.setTotal(total);
			quotesEntity.setGrand_total(grand_total);
			QuotesEntity quotesenty = quotesRepo.save(quotesEntity);

			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				double tax = 0;

				tax = (productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity())) * .125;

				QuotesItemEntity quotesItemEntity = new QuotesItemEntity();
				quotesItemEntity.setSale_id(quotesenty.getQuotesId());
				quotesItemEntity.setProduct_id(additem.getProductId());
				quotesItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				quotesItemEntity.setItem_tax(additem.getPrice() * 0.125);
				quotesItemEntity.setGst("12.5");
				quotesItemEntity.setItem_discount(0);
				quotesItemEntity.setProduct_code(additem.getProductId().toString());
				quotesItemEntity.setProduct_name(additem.getProductName());
				quotesItemEntity.setReal_unit_price(productDetailsEnt.getprice());
				quotesItemEntity.setSubtotal(productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity()));
				quotesItemEntity.setTax(Double.toString(tax));

				quotesItemRepo.save(quotesItemEntity);

				// Subtracting Quantities here from products

				productDetailsEnt
						.setQuantity(productDetailsEnt.getQuantity() - Integer.parseInt(additem.getQuantity()));
				productDetailsRepo.save(productDetailsEnt);
			}

			resultVO.setMsgDescr("Quotes Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<QuotesPojo> getQuotesList() {
		List<QuotesEntity> quotesEntityList = new ArrayList<QuotesEntity>();
		List<QuotesPojo> quotesPojoList = new ArrayList<QuotesPojo>();
		try {
			System.out.println("in Quotes");
			quotesEntityList = quotesRepo.findAll();
			for (QuotesEntity quotesEntityEntityRes : quotesEntityList) {

				QuotesPojo quotesPojo = new QuotesPojo();
				quotesPojo = mapper.map(quotesEntityEntityRes, QuotesPojo.class);
				quotesPojoList.add(quotesPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quotesPojoList;
	}
	

}
