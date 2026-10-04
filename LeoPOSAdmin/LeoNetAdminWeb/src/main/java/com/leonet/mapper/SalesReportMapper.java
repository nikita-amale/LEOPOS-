package com.leonet.mapper;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.pojo.SaleInvoiceReportPojo;
import com.leonet.common.pojo.SalesItemReportPojo;

@Component
public class SalesReportMapper {

	public SaleInvoiceReportPojo mapToSaleDto(SalesEntity sale, List<SalesItemEntity> items) {
		SaleInvoiceReportPojo dto = new SaleInvoiceReportPojo();
		dto.setInvoiceNumber(sale.getSaleId());
		dto.setCode(sale.getReferenceno());
		dto.setCustomerName(sale.getMember_name());

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		dto.setDate(formatter.format(sale.getDate()));

		dto.setGross(sale.getTotal());
		dto.setTax(sale.getTotal_tax());
		dto.setTotal(sale.getGrand_total());
		dto.setAmountPaid(sale.getPaid());
		dto.setNetPay(sale.getGrand_total() - sale.getPaid());
		dto.setItems(mapToItemDtoList(items));

		return dto;
	}

	public List<SalesItemReportPojo> mapToItemDtoList(List<SalesItemEntity> items) {
		if (items == null || items.isEmpty()) {
			return Collections.emptyList();
		}

		return items.stream().map(this::mapToItemDto).collect(Collectors.toList());
	}

	public SalesItemReportPojo mapToItemDto(SalesItemEntity item) {
		SalesItemReportPojo dto = new SalesItemReportPojo();
		dto.setDepartment("01");
		dto.setType("Neg Inv");
		dto.setItemCode(item.getProduct_code());
		dto.setDescription(item.getProduct_name());
	//	dto.setUnits(item.getQuantity());
		dto.setAverage(item.getSubtotal());
		dto.setSalePrice(item.getSubtotal());
		dto.setAmount(item.getReal_unit_price());
		dto.setQuantity(item.getQuantity());
		return dto;
	}

}
