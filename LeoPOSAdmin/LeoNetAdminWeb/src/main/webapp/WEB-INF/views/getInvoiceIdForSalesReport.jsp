<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>


<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid"></div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Product Sale Report</h3>
						</div>


						<div class="card-body">
							<table id="purchasetable"
								class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>

									<tr>
										<th>SaleId</th>
										<th hidden>Refernceno</th>
										<th hidden>Date</th>
										<th>Customer name</th>
										<th>Customer Type</th>
										<th hidden>Paid</th>

										<th>Productname</th>
										<th>Unit</th>
										<th>Qty</th>
										<th hidden>Sales Price</th>
										<th hidden>Total</th>
										<th hidden>Balance</th>
										<th>View</th>

									</tr>

								</thead>

								<tbody>
									<c:set var="totalQuantity" value="0" />
									<c:set var="totalamount" value="0" />

									<c:forEach items="${salesItemListPojo}" var="salesItem"
										varStatus="loop">
										<c:set var="totalQuantity"
											value="${totalQuantity + salesItem.quantity}" />
										<c:set var="totalamount"
											value="${totalamount + salesItem.subtotal}" />

										<c:forEach items="${salePojoList}" var="salePojo">
											<c:set var="totalbalance" value="0" />


											<c:set var="balance"
												value="${salePojo.grand_total - salePojo.paid}" />
											<c:set var="subtotal" value="${salesItem.subtotal}" />
											<c:set var="saleprice" value="${salesItem.real_unit_price}" />
											<c:if test="${salesItem.saleid == salePojo.saleId}">
												<c:if test="${salesItem.product_code == productId}">
													<tr id="${loop.count}">
														<td id="saleId${loop.count}">${salesItem.saleid}</td>
														<td hidden id="referenceno${loop.count}">${salePojo.referenceno}</td>
														<td hidden id="date${loop.count}">${salePojo.date}</td>
														<td id="member_name${loop.count}">${salePojo.member_name}</td>
														<td id="ctype${loop.count}">${salePojo.ctype}</td>
														<td hidden id="paid${loop.count}">${salePojo.paid}</td>
														<td id="product_name${loop.count}">${salesItem.product_name}</td>
														<td id="unit${loop.count}">${salesItem.roll}</td>
														<td id="quantity${loop.count}">${salesItem.quantity}</td>
														<td hidden id="real_unit_price${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${saleprice}" /></td>
														<td hidden id="subtotal${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${subtotal}" /></td>
														<td hidden id="subtotal${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${totalamount}" /></td>
														<td>
															<button type="button" class="btn btn-primary"
																data-bs-toggle="modal" data-bs-target="#viewsales"
																onclick="ViewDetails(${loop.count})">View Sales</button>
														</td>
													</tr>
												</c:if>
											</c:if>
										</c:forEach>
									</c:forEach>

									<c:set var="totalQuantity" value="0" />
									<c:set var="totalamount" value="0" />
									<c:forEach items="${specialSalesItemListPojo}" var="ssalesItem"
										varStatus="loop">
										<c:set var="totalQuantity"
											value="${totalQuantity + ssalesItem.quantity}" />
										<c:set var="totalamount"
											value="${totalamount + ssalesItem.subtotal}" />

										<c:forEach items="${specialSalePojoList}" var="ssalePojo">
											<c:set var="totalbalance" value="0" />


											<c:set var="balance"
												value="${ssalePojo.grand_total - ssalePojo.paid}" />
											<c:set var="subtotal" value="${ssalesItem.subtotal}" />
											<c:set var="saleprice" value="${ssalesItem.real_unit_price}" />
											<c:if test="${ssalesItem.saleid == ssalePojo.saleId}">
												<c:if test="${ssalesItem.product_code== productId}">
													<tr id="${loop.count}">
														<td id="saleId${loop.count}">${ssalesItem.saleid}</td>
														<td hidden id="referenceno${loop.count}">${ssalePojo.referenceno}</td>
														<td hidden id="date${loop.count}">${ssalePojo.date}</td>
														<td id="member_name${loop.count}">${ssalePojo.member_name}</td>
														<td id="ctype${loop.count}">${ssalePojo.ctype}</td>
														<td hidden id="paid${loop.count}">${ssalePojo.paid}</td>
														<td id="product_name${loop.count}">${ssalesItem.product_name}</td>
														<td id="unit${loop.count}">${ssalesItem.roll}</td>
														<td id="quantity${loop.count}">${ssalesItem.quantity}</td>
														<td hidden id="real_unit_price${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${ssaleprice}" /></td>
														<td hidden id="subtotal${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${subtotal}" /></td>
														<td hidden id="subtotal${loop.count}"><fmt:formatNumber
																pattern="0.00" value="${totalamount}" /></td>
														<td>
															<button type="button" class="btn btn-primary"
																data-bs-toggle="modal" data-bs-target="#viewsales"
																onclick="ViewDetailspecial(${loop.count})">View Sales</button>
														</td>
													</tr>
												</c:if>
												</c:if>
											
										</c:forEach>
									</c:forEach>









								</tbody>
							</table>
						</div>



					</div>
				</div>
			</div>
		</div>

	</section>
</div>






<div class="modal fade" id="viewsales" tabindex="-1"
	aria-labelledby="viewsales" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button"
					class="btn-close button__border float-right close"
					data-bs-dismiss="modal" aria-label="Close">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
						fill="currentColor" class="bi bi-x-square-fill text-secondary"
						viewBox="0 0 16 16">
						<path
							d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
					</svg>
				</button>



				<!-- <a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fa fa-print fa-sm text-white-50"></i>Print Invoice</a> -->

				<div id="printTable2">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<table width="100%" style="font-size: 14px;">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/resources/images/logo_s.png"
								width="300px" /></td>
							<td></td>
						</tr>
					</table>

					<br>
					<div class="row" style="justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;">
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right">Tax Invoice</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date"></td>
								</tr>
								<tr>
									<td>Tel: 501 207-0669</td>
									<td align="right" id="sRefno"></td>

								</tr>

								<tr>
									<td></td>
									<td align="right">Sales Person</td>
								</tr>

							</table>
						</div>
					</div>




					<!-- <p style="margin-bottom: 0; font-size: 14px;">Sales Receipt</p>
					<br> -->

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="salesReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>

								<tr>
									<th style="text-align: center !important;">S.No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">Quantity</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">U.O.M</th>
									<th style="text-align: center !important;">Subtotal</th>
								</tr>

							</thead>

							<tbody style="text-align: center !important">

							</tbody>
						</table>
						<table
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Total
									Amount (BZD)</td>
								<td id="totalt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Total
									Tax</td>
								<td id="totaltaxt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Total
									Paid</td>
								<td id="totalPaidt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Grand
									Total (BZD)</td>
								<td id="balTott"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

						</table>



					</div>
					<div class="row">
						<div class="col pull-right">
							<p id="notee" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right">
							<div class="print-flex well well-sm">
								<p style="font-size: 14px !important;" id="Createdby"></p>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right">
							<div class="print-flex well well-sm">
								<p style="font-size: 14px !important;">
									<strong>Invoice must be presented when making a
										return, 10% Restocking Fee will be charged on items returned
										after 7 days</strong>
								</p>

							</div>
						</div>

					</div>
				</div>
				<a href="javascript:void(0);" onclick="printData2()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.umd.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>




<script type="text/javascript">
	src = "https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js" >

	$(function() {
		$('#purchasetable').DataTable({
			"paging" : false,
			"pageLength" : 20,
			"lengthChange" : false,
			"searching" : true,
			"ordering" : true,
			"info" : false,
			"autoWidth" : false,
			"responsive" : true,
			"scrollX" : true,
		});
		//window.location.reload();
	});

	$("#startDate").click(function() {
		$("#datepicker").datepicker();

	});

	function reset() {
		window.location.reload(true);

	}

	function searchTable() {
		var input, filter, found, table, tr, td, i, j;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("purchasetable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td");
			for (j = 0; j < td.length; j++) {
				if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
					found = true;
				}	
			}
			if (found) {
				tr[i].style.display = "";
				found = false;
			} else {
				tr[i].style.display = "none";
			}
		}
	}

	function fnExcelReport() {
		var htmls = "";
		var uri = 'data:application/vnd.ms-excel;base64,';
		var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><><body><table>{table}</table></body></html>';
		var base64 = function(s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		};

		var format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		};

		//htmls = document.getElementById('tablepaging').innerHTML;
		var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
		var textRange;
		var j = 0;
		tab = document.getElementById('purchasetable');

		for (j = 0; j < tab.rows.length; j++) {
			tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
		}

		tab_text = tab_text + "</table>";
		tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
		tab_text = tab_text.replace(/<img[^>]*>/gi, "");
		tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");

		var ctx = {
			worksheet : 'Worksheet',
			table : tab_text
		}

		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();

		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}
		var link = document.createElement("a");
		document.body.appendChild(link);
		link.download = "CustomerPurchase Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}

	function View(count) {
		//alert("Reached");
		var SaleId = $("#saleId" + count).text();
		// $("#saleid").val(SaleId);
		$("#saleid").html($("#saleId" + count).text());
	}
	function ViewDetails(count) {
		
		var SaleId = $("#saleId" + count).text();
		var ctype = $("#ctype"+count).text();
		var paid = $("#paid"+count).text();
		  if(!isNaN(paid)){
			  paid = parseFloat(paid).toFixed(2); 
		  }
		
		$("#salesReceipt  tbody").empty();
		
		 
	
		 $("#sRefno").html("Sale Reference No. " + $("#referenceno"+count).text());
		 $("#cName").html( $("#member_name"+count).text());
		
		 $("#date").html("Date :" + $("#date"+count).text());
				
		$
				.ajax({
					url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId='
							+ SaleId,
					type : "GET",
					dataType : "json",
					success : function(data) {

						var ajaxCallData = JSON.stringify(data);

						$.each(data, function(i, data) {

							var real_unit_price = data.real_unit_price;
							var subtotal = data.subtotal;
							var unitname = data.roll;
							if (!isNaN(real_unit_price)) {
								real_unit_price = parseFloat(real_unit_price)
										.toFixed(2);
							}
							if (!isNaN(subtotal)) {
								subtotal = parseFloat(subtotal).toFixed(2);
							}
							if (unitname == "Piece") {
								unitname = "Pc";
							}
							if (unitname == "Box12") {
								unitname = "Box";
							}
							var rowCount = $('#salesReceipt tr').length;
							var tr = $("<tr></tr>");

							tr.append($('<td></td>').html(rowCount));
							tr.append($('<td></td>').html(data.product_name));
							tr.append($('<td></td>').html(data.quantity));
							tr.append($('<td></td>').html(real_unit_price));
							tr.append($('<td></td>').html(unitname));
							tr.append($('<td style="text-align: right;"></td>')
									.html(subtotal));
							tr.append($('<tr></tr>').html());

							$('#salesReceipt tbody').append(tr);
						});

						CalculateTotal(paid);

					},
					error : function(error) {
						console.log(`Error ${error}`);
					}
				});
			

	}
	
function ViewDetailspecial(count) {
		
		var SaleId = $("#saleId" + count).text();
		var ctype = $("#ctype"+count).text();
		var paid = $("#paid"+count).text();
		  if(!isNaN(paid)){
			  paid = parseFloat(paid).toFixed(2); 
		  }
		
		$("#salesReceipt  tbody").empty();
		
		 
	
		 $("#sRefno").html("Sale Reference No. " + $("#referenceno"+count).text());
		 $("#cName").html( $("#member_name"+count).text());
		
		 $("#date").html("Date :" + $("#date"+count).text());
			
				
				$.ajax({
					url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleId?saleId=' + SaleId,
					type : "GET",
					dataType : "json",
					success : function(data) {
						var ajaxCallData = JSON.stringify(data);
						
						$.each(data, function(i, data) {
							
							
							  specialSaleSubtotal = data.specialSaleSubtotal ;
							  specialSaleGrandTotal = data.specialSaleGrandTotal;
							  specialSaleTotalTax = data.specialSaleTotalTax ;
							  
							var real_unit_price = data.real_unit_price;
							var subtotal =  data.subtotal;
							var unitname = data.roll;
							if(!isNaN(real_unit_price)){
								real_unit_price = parseFloat(real_unit_price); 
								real_unit_price=+(Math.round(real_unit_price + "e+2")  + "e-2");
							  }
							if(!isNaN(subtotal)){
								subtotal = parseFloat(subtotal); 
								subtotal=+(Math.round(subtotal + "e+2")  + "e-2");
							  }
							if(unitname == "Piece"){
								unitname = "Pc";
							  }
							if(unitname == "Box12"){
								unitname = "Box";
							  }
							if(unitname == "Wire Roll 328 Ft"){
								unitname = "Roll 328'";
							  }
							
							var rowCount = $('#salesReceipt tr').length;
							var tr = $('<tr></tr>');
							
							tr.append($('<td></td>').html(rowCount));
							tr.append($('<td></td>').html(data.product_name));
							tr.append($('<td></td>').html(data.quantity));
							tr.append($('<td></td>').html(real_unit_price.toFixed(2)));
							tr.append($('<td></td>').html(unitname));
							tr.append($('<td style="text-align: right;"></td>').html(subtotal.toFixed(2)));
							tr.append($('<tr></tr>').html());
							
							$('#salesReceipt tbody').append(tr);
						});
						CalculateTotal(paid);
					

					},
					error : function(error) {
						console.log(`Error ${error}`);
					}
				});
				
			

	}
	function CalculateTotal(paid) {

		var grandT = 0;
		var taxT = 0;
		var totalT = 0;

		$("#salesReceipt > tbody > tr").each(function() {
			var t5 = $(this).find("td:eq(5)").text();
			if (!isNaN(t5)) {
				grandT += parseFloat(t5);
				// alert(grandT);
			}
		});
		grandT = grandT.toFixed(2);

		taxT = grandT * .125;
		taxT = +(Math.round(taxT + "e+2") + "e-2");

		totalT = Number(grandT) + taxT;
		totalT = Math.round(totalT * 100) / 100;
		$("#totalt").html(grandT);
		$("#totaltaxt").html(taxT.toFixed(2));
		$("#balTott").html(totalT.toFixed(2));
		 $("#totalPaidt").html(paid);
		// Math.round(12.345 * 100) / 100;
	}
</script>


</body>
</html>