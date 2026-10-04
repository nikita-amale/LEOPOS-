
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<style>
.button__border {
	border: none;
	background: transparent;
}

button:focus {
	outline: transparent !important;
	outline: transparent !important;
}
.show-div {
	display: flex;
	padding-bottom: 10px;
  }
.main-sidebar, .main-sidebar::before {
    transition: margin-left .3s ease-in-out,width .3s ease-in-out;
    width: 250px;
}

@media (max-width: 767px) {
	.search-quote{
		max-width: 170px;
		right: 1rem;
	}
	.search-quote input{
		max-width: 130px;
	}
	  }

.layout-fixed .brand-link {
    width: 250px;
}

@media (min-width: 768px) {
body:not(.sidebar-mini-md) .content-wrapper, body:not(.sidebar-mini-md) .main-footer, body:not(.sidebar-mini-md) .main-header {
    transition: margin-left .3s ease-in-out;
    margin-left: 250px;
}
}
.layout-fixed .brand-link {
    width: 250px;
}
.main-footer {
    background-color: #f8f9fc;
    border-top: none;
    color: #869099;
    padding: 0.25rem 1rem;
}
@media (min-width: 100px) {
    .d-sm-inline-block {
        display: inline-block !important;
    }
}

@media (max-width: 767px) {
	.search-return{
		max-width: 170px;
		right: 1rem;
	}
	.search-return input{
		max-width: 130px;
	}
	  }
/* Style the container div */
.search-image-container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgb(0 0 0 / 39%);
    z-index: 999;
}

/* Style the image */
#search-image {
    display: block;
    filter: brightness(0.8); /* Adjust the brightness value as needed */
    width: 200px;
}
</style>
<!-- Content Wrapper. Contains page content -->
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
							<h3 class="card-title open-sans">Sales Details</h3>
						</div>

					<div class="card-body">
					<div class="show-div">
						<label for="exampleInputEmail1">Show</label> <select id="show">
							<option
								href="${pageContext.request.contextPath}/getSalesListpage?pageSize=10"
								value="10">10</option>
							<option
								href="${pageContext.request.contextPath}/getSalesListpage?pageSize=25"
								value="25">25</option>
							<option
								href="${pageContext.request.contextPath}/getSalesListpage?pageSize=50"
								value="50">50</option>
						</select>
				
							<div class="search-quote" style="position: absolute; right: 1rem;">
							<form action="${pageContext.request.contextPath}/searchSale">
								<input type="text" placeholder="Searchsale" name="search" id="salesSearchInput"  onkeyup="searchSalesNew()"> 
								<button type="submit">
									<i class="fa fa-search"></i>
								</button>
							</form>
						</div>

					</div>
					
				
					<div class="form-group overflow-auto" style="min-height: 500px;">
				
						<table id="myTable" class="table table-bordered table-hover mb-3">
							<thead class="open-sans">
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th hidden>Saleid</th>
									<th>Reference_no</th>
									<th hidden>memberid</th>
									<th>Customer</th>
									<sec:authorize access="hasAuthority('admin')">
										<th>Customer Type</th>
									</sec:authorize>
									<th hidden>Customer Type</th>
									<th>Grand_Total</th>
									<th>Paid</th>
									<th>Balance</th>
									<th>Status</th>
									<th>Delete Status</th>
									<th hidden>tax</th>
									<th hidden>total</th>
									<th></th>
									<th hidden>View Sales Receipt</th>
									<sec:authorize access="hasAuthority('admin')">
									<th hidden>View Sales 10% Receipt</th>
									<th hidden>View Sales Without MPN Receipt</th>
									</sec:authorize>
									<sec:authorize
										access="hasAuthority('admin') || hasAuthority('cashier')">
										<th hidden>Add Payment</th>
									</sec:authorize>
									<th hidden>View Payment</th>
									<th hidden>Edit</th>
									<th hidden>Credit Pay</th>
									<sec:authorize access="hasAuthority('admin')">
										<th hidden>Delete</th>
									</sec:authorize>
									<th hidden>creadtedby</th>
									<th hidden>Note</th>
									<th hidden>PO</th>
									<th hidden>tax</th>
									<th hidden>caddress</th>
									<th hidden>pinno</th>
									<th hidden>phoneno</th>


								</tr>
							</thead>
							<tbody>
								<c:forEach var="salesPojo" items="${salesPojo}" varStatus="loop">
								<c:set var="currentPage" value="${page}" />
									<c:set var="pageSize" value="${pageSize}" />
									<c:set var="serialNumber"
										value="${(currentPage) * pageSize + loop.index}" />

									<c:set var="balance"
										value="${salesPojo.grand_total - salesPojo.paid}" />
									<c:set var="paid" value="${salesPojo.paid}" />
									<c:set var="totaltax" value="${salesPojo.total_tax}" />
									<c:set var="grand_total" value="${salesPojo.grand_total}" />
									<c:set var="total" value="${salesPojo.total}" />
									<c:set var="specialGrandTotal" value="${salesPojo.grand_total * 0.125 +salesPojo.grand_total }" />
										<c:set var="specialbalance"
										value="${specialGrandTotal - salesPojo.paid}" />
									<tr id="${loop.count}">
										<c:choose>
											<c:when test="${currentPage == '0'}">
										<td>${serialNumber+1}</td>
										</c:when>
										<c:otherwise>
										<td>${serialNumber+2}</td>
										</c:otherwise>
										</c:choose>
										<td id="saleDate${loop.count}">${salesPojo.date}</td>
										<td hidden id="saleId${loop.count}">${salesPojo.saleId}</td>
										<td id="referenceno${loop.count}">${salesPojo.referenceno}</td>
										<td hidden id="memberId${loop.count}">${salesPojo.memberid}</td>
										<td id="customer${loop.count}">${salesPojo.member_name}</td>
										<sec:authorize access="hasAuthority('admin')">
											<td id="ctype${loop.count}">${salesPojo.ctype}</td>
										</sec:authorize>
										<td hidden id="ctype${loop.count}">${salesPojo.ctype}</td>
										
												<td id="saleTotal${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${grand_total}" /></td>
										<td id="salePaid${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${paid}" /></td>
												
												<td id="saleBalance${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${balance}" /></td>
										<td id="SaleStatus${loop.count}">${salesPojo.paymentstatus}</td>

										<c:choose>
											<c:when test="${salesPojo.isActive == '0'}">
												<td id="active${loop.count}">active</td>
											</c:when>
											<c:otherwise>
												<td id="active${loop.count}">delete</td>
											</c:otherwise>
										</c:choose>

										<td hidden id="totaltax${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${totaltax}" /></td>
										<td hidden id="total${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${total}" /></td>
												
												<td hidden> </td>
												
												<!-----Drop down starts------>
												<td>
												<div class="dropdown d-flex justify-content-center">
													<button class="btn border-0 dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
														<i class="fas fa-ellipsis-v"></i>
													</button>
													<ul class="dropdown-menu py-0" aria-labelledby="dropdownMenuButton1">
													  <li>
														<button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#viewsales" 
																onclick="ViewDetails(${loop.count})">
														View Sales
														</button>
														<!-- <a class="dropdown-item" href="#">View Receipt</a> -->
														</li>
														
														<sec:authorize access="hasAuthority('admin') ">
														<li>
														<button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#viewsales" 
																onclick="ViewDetailsMPN(${loop.count})">
														View Sales Without MPN
														</button>
														<!-- <a class="dropdown-item" href="#">View Receipt</a> -->
														</li>
														 <li>
															<button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#viewsales10" 
																	onclick="ViewDetails10(${loop.count})">
															View Sales 10%
															</button>
															<!-- <a class="dropdown-item" href="#">View Receipt</a> -->
															</li>
											
													  <li>
													  </sec:authorize>
													  <sec:authorize
										access="hasAuthority('admin') || hasAuthority('cashier')">
													  <c:if test="${salesPojo.isActive == '0'}">
													  <c:if
														test="${salesPojo.grand_total - salesPojo.paid > '0'}">
														 <a type="button" class="dropdown-item" 
																 data-toggle="modal" onclick="loadPayment(${loop.count})"
																	data-target="#modal-payment" >Add Payment</a>
														</c:if>
														</c:if>
														</sec:authorize>
													  </li>
													
													  <li>
														<button type="button" class="dropdown-item" data-toggle="modal"
																data-target="#viewpayment"
																onclick="ViewPayment(${loop.count})">
														View Payment
														</button>
														<!-- <a class="dropdown-item" href="#">View Payment</a> -->
														</li>
														 <sec:authorize
											access="hasAuthority('admin') ">
														
														 <li>
														<button type="button" class="dropdown-item" data-toggle="modal"
																data-target="#deletePaymentView"
																onclick="deletePaymentView(${loop.count})">
														Delete Payment
														</button>
													
														</li>
														</sec:authorize>
													
														 <li>
														<sec:authorize
										access="hasAuthority('admin') || hasAuthority('cashier') || hasAuthority('sales')">
														  <c:if test="${salesPojo.isActive == '0'}">
														  <c:if test="${salesPojo.paid <= '0'}">
														 
															 <a type="button" class="dropdown-item" 
																	 href="${pageContext.request.contextPath}/editSales?id=${salesPojo.saleId}&ctype=${salesPojo.ctype}" >
															 Edit Sale</a>
															 </c:if>
															</c:if>
															</sec:authorize>
														
														  </li>
													  <li hidden id="CreditPay${loop.count}">${salesPojo.creditpay}</li>
													  <sec:authorize
											access="hasAuthority('admin') ">

													  <li>
													  
													  <c:if test="${salesPojo.isActive == '0'}">
														 <a type="button" class="dropdown-item" onclick="deleteDetails(${loop.count},${salesPojo.saleId})" >Delete</a>
														</c:if>
														<!-- <a class="dropdown-item" href="#">Delete</a> -->
													  </li>
													  </sec:authorize>
													 
													  <li hidden   id="Createdby${loop.count}">${salesPojo.createdBy}</li>
													  <li hidden id="note${loop.count}">${salesPojo.note}</li>
													  <li hidden id="po${loop.count}">${salesPojo.purchaseorder} </li>
													  <li hidden id="tax${loop.count}">${salesPojo.total_tax} </li>
													  <li hidden id="caddress${loop.count}">${salesPojo.customeraddress}  </li>
													  <li hidden id="pincode${loop.count}">${salesPojo.pincode}</li>
													  <li hidden id="phonemain${loop.count}">${salesPojo.phonemain}</li>
													</ul>
												  </div>
											</td>
											<!-----Drop down ends------>
											</tr>
								</c:forEach>


							</tbody>

						</table>
						<div id="salesShowingText">	Showing ${currentRecords} of ${totalRecords} entries</div>

					</div>
					<div class=".bottom-right" id="salesPaginationSection"
						style="position: absolute; bottom: 0; right: 0;">
						<table border="1" cellpadding="5" cellspacing="5">

							<c:set var="currentPage" value="${page}" />
							<c:set var="pageSize" value="${pageSize}" />

							<tr>
								<c:if test="${previous}">
									<td><a href="getSalesListNew?page=${currentPage - 1}"
										style="color: black;">Previous</a></td>
								</c:if>
								<c:if test="${next}">
									<c:forEach begin="${currentPage}" end="${currentPage+4}"
										var="i">

										<c:choose>
											<c:when test="${currentPage == i}">
												<td style="background-color: blue;"><a
													href="getSalesListNew?page=${i}" style="color: white;">${i}</a></td>
											</c:when>
											<c:otherwise>
												<td><a href="getSalesListNew?page=${i}">${i}</a></td>
											</c:otherwise>

										</c:choose>


									</c:forEach>
								</c:if>



								<c:if test="${next}">
									<td><a href="getSalesListNew?page=${currentPage +1}"
										style="color: black;">Next</a></td>
								</c:if>

							</tr>
						</table>
					</div>
					</div>
					</div>

					<!-- /.card-body -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			
			
		<!-- /.row -->
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
	</div>
<div class="modal fade" id="viewsales" tabindex="-1"
	aria-labelledby="viewsales" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body" style="z-index: 9999; margin: 7.5px;">
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

					<div id="pdfContent" style="margin-top:0px;"	>
					  <div>
					    <img
					      id="logoImage"
					      src="${pageContext.request.contextPath}/resources/images/logo_s.png"
					      width="290px"
					    	  
					    />
					  </div>

					<div class="row" style="justify-content: space-between;">
						
						<div id="headerDiv"
						style="justify-content: space-between;position: relative;width: 100%;">

				<div class="row" style="justify-content: space-between;font-size: 12px;width: 100%;margin-bottom: 0px;">
				  <div class="col col-left">
				    <!-- Left content goes here -->
				    3754 Central American Blvd.<br>
				    Belize City Belize<br>
				    Tel: 501 207-0669<br>
				    TIN # 128693
				  </div>
				  <div class="col col-right" style="
						    float: right;
				    text-align: right;
				    margin-right: 2px;">
				    <!-- Right content goes here -->
				    <span>Tax Invoice</span><br>
				     <span id="date"></span><br>
				     <span id="sRefno"></span><br>
				     <span id="po"></span><br>
				     <span>Sales Person</span><br>
				  </div>
				</div>
					</div>
					</div>
					<div  id="billToSection" class="excludeFromPdf" style=" margin-left: -1.5px;">
					<p style="margin-bottom: 0 !important; font-size: 13px;">Bill To:</p>
				<p style="margin-bottom: 0; font-size: 12px;">
				
					Name: <label class="mb-0" id="cName"></label><br>
					Address: <label class="mb-0" id="cAddress"></label><br>
					<label class="mb-0" id="phonemain"></label><br>
					<label class="mb-0" id="pincode"></label><br>
					
				</p><br>
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
									<th style="text-align: center !important;">QTY</th>
									
									<th style="text-align: center !important;">U.O.M</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">Subtotal</th>
								    <th style="text-align: center !important;">T</th>
								</tr>

							</thead>

							<tbody id="tableBody" style="text-align: center !important">

							</tbody>
						</table>
						<table
							class="table table-bordered table-hover table-striped print-table order-table totalTable"
						    id="fullWidthTable"
							width="100%" border="1"
							style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Total Amount (BZD)</td>
								<td id="totalt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Tax (GST 12.5%)</td>
								<td id="totaltaxt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Total Paid</td>
								<td id="totalPaidt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5"
									style="text-align: right !important; font-weight: bold;">Grand Total (BZD)</td>
								<td id="balTott"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

						</table>

					</div>
					<div id="notes">
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
								<p class="bold-text" style="font-size: 14px !important;">
									<strong>Invoice must be presented when making a return, 10% Restocking Fee will be charged on items returned after 7 days</strong>
								</p>

							
						</div>
						
					</div>
					</div>
					</div>
				</div>
				<a href="javascript:void(0);" onclick="printData2()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- View receipt 10% -->

<!-- Modal -->
<div class="modal fade" id="viewsales10" tabindex="-1"
	aria-labelledby="viewsales10" aria-hidden="true">
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
				<div id="printTable3">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>
					<div id="pdfContent2" style="margin-top:0px;">
					  <div>
					    <img
					      id="logoImage1"
					      src="${pageContext.request.contextPath}/resources/images/logo_s.png"
					      width="290px"
					    />
					  </div>
					<div class="row"
						style="justify-content: space-between;">
					
					<div id="headerDiv"
					style="justify-content: space-between;position: relative;width: 100%;">

			<div class="row" style="justify-content: space-between;font-size: 12px;width: 100%;margin-bottom: 0px;">
			<div class="col col-left">
		    <!-- Left content goes here -->
		    3754 Central American Blvd.<br>
		    Belize City Belize<br>
		    Tel: 501 207-0669<br>
		    TIN # 128693
		  </div>
			  <div class="col col-right" style="
					    float: right;
			    text-align: right;
			    padding-right: 2px;">
			    <!-- Right content goes here -->
			    <span>Tax Invoice</span><br>
			     <span id="date10"></span><br>
			     <span id="sRefno10"></span><br>
			     <span id="poten"></span><br>
			     <span>Sales Person</span><br>
			  </div>
			</div>
				</div>
				</div>
				<div  id="billToSection2" class="excludeFromPdf" style=" margin-left: -1.5px;">
				<p style="margin-bottom: 0 !important; font-size: 13px;">Bill To:</p>
			<p style="margin-bottom: 0; font-size: 12px;">
			
				Name: <label class="mb-0" id="cNamee"></label><br>
				Address: <label class="mb-0" id="cAddresss"></label><br>
				<label class="mb-0" id="phonemainn"></label><br>
				<label class="mb-0" id="pincodee"></label><br>
				
			</p>
				</div>
				</div>
				

					<!--<p style="margin-bottom: 0; font-size: 14px;">Sales Receipt</p>-->
					<br>
					<div class="table-responsive" style="font-size: 12px !important;">
						<table id="salesReceipt10"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>

								<tr>
									<th style="text-align: center !important;">S.No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">QTY</th>
								
									<th style="text-align: center !important;">U.O.M</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">Subtotal</th>
									<th style="text-align: center !important;">T</th>
								</tr>

							</thead>

							<tbody id="tableBody2" style="text-align: center !important;">

							</tbody>
						</table>
						<table
							class="table table-bordered table-hover table-striped print-table order-table totalTable" 
							id="fullWidthTable2"
							width="100%" border="1"
							style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total Amount (BZD)</td>
								<td id="totalten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Tax (GST 12.5%)</td>
								<td id="totaltaxten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total Paid</td>
								<td id="totalPaidten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Grand Total (BZD)</td>
								<td id="balTotten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
						</table>
					</div>

					<div id="notes2">
					<div class="row">
						<div class="col pull-right">
							<p id="noteeten" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col pull-right">
							<div class="print-flex well well-sm">

								<p style="font-size: 14px !important;" id="Createdbyten"></p>



							</div>

						</div>

					</div>
					
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right">
							<div class="print-flex well well-sm">
								<p class="bold-text" style="font-size: 14px !important;">
									<strong>Invoice must be presented when making a return, 10% Restocking Fee will be charged on items returned after 7 days</strong>
								</p>

							</div>
						</div>

					</div>
					</div>
					</div>

				

				<a href="javascript:void(0);" onclick="printData3()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF2()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>
			</div>
			<!-- <div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		  <button type="button" class="btn btn-primary">Save changes</button>
		</div> -->
		</div>
	</div>
</div>

<!-- View receipt 10% ends -->


<div class="modal fade" id="modal-payment">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-close"
						style="font-size: 16px; color: #1eb53a !important;">close</i>
				</button>
				<div class="form-group">
					<label for="exampleInputEmail1">Total Amount</label> <input
						type="text" class="form-control" id="amount" readonly="true";>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Paid Amount</label> <input
						type="text" class="form-control" id="paidamount" readonly="true";>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Pay Amount</label> <input
						type="text" class="form-control" id="payamount"
						placeholder="Enter Amount to be paid" Required>
				</div>
				<div class="form-group" hidden>
					<label for="exampleInputEmail1">Customer type</label> <input
						type="text" class="form-control" id="ctype" Required>
				</div>
				<input type="hidden" class="form-control" id="hidsaleid"> <input
					type="hidden" class="form-control" id="hidmemberId">
				<div class="form-group">
					<label for="exampleInputEmail1">Payment Type</label> <select
						class="form-control select2bs4" id="ptype" style="width: 100%;"
						onchange="diff();">
						<option val="">Please Select Payment Type</option>
						<option val="Cash">Cash</option>
						<option val="Cheque">Cheque</option>
						<option val="Online">Online</option>
						<option val="CreditCard">Credit Card</option>
						<option val="Bank">Bank Transfer</option>
						<option val="Deposit">Deposit</option>
						<option val="Refund">Refund</option>
						<option val="other">Other</option>
					</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">CreditAmount</label> <input type="text"
						class="form-control" id="Deposit" readonly="true";>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Cash To Return</label> <input
						type="text" class="form-control" id="diff" readonly="true";>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Cheque No/CC No</label> <input
						type="text" class="form-control" id="chequecc"
						placeholder="Enter Cheque / Receipt">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Note</label> <input type="text"
						class="form-control" id="paymentnote" placeholder="Enter Note">
				</div>
				<div class="card-footer">
					<button type="button" onclick="addPayment()"
						class="btn btn-primary">Add Payment</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>

<!--View payment-->
<div class="modal fade" id="viewpayment" tabindex="-1"
	aria-labelledby="viewpayment" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
						fill="currentColor" class="bi bi-x-square-fill text-secondary"
						viewBox="0 0 16 16">
			<path
							d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
		  </svg>
				</button>
				<div id="printTable">

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

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;" id="viewPaymentTbl">


								<tr>
									<td>3754 Central American Blvd.</td>

								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date10"></td>
								</tr>
								<tr>
								<tr>
									<td>Tel: 501 207-0669</td>
								</tr>

								<tr>
									<td align="left" id="customerName"></td>
								</tr>
								<tr>
									<td align="left" id="pAddress"></td>
								</tr>

								<c:forEach var="paymentPojo" items="${paymentPojo}"
									varStatus="loop">
									<tr>
										<td align="left">Date Received:${paymentPojo.paymentdate}</td>
									</tr>
								</c:forEach>

							</table>
						</div>


					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="paymentreceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>

								<tr>

									<th>Serial no.</th>
									<th>Payment Method</th>
									<th>#</th>
									<th>Payment Amount</th>
									<th>Invoice id</th>
									<th>Date Recevied</th>
									<th>Note</th>


								</tr>

							</thead>

							<tbody style="text-align: center !important;">



							</tbody>
						</table>

					</div>


					<div class="row">
						<div class="col pull-right">
							<p id="paynote" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col pull-right">
							<div class="print-flex well well-sm">

								<p style="font-size: 14px !important;" id="Createdbyten"></p>



							</div>

						</div>

					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right"></div>

					</div>


				</div>

				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>
			</div>
			
		</div>
	</div>
</div>




  <!--Delete payment-->
<div class="modal fade" id="deletePaymentView" tabindex="-1"
	aria-labelledby="deletePaymentView" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
						fill="currentColor" class="bi bi-x-square-fill text-secondary"
						viewBox="0 0 16 16">
			<path
							d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
		  </svg>
				</button>
				<div id="printTable">

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

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;" id="deletePaymentViewTbl">


								<tr>
									<td>3754 Central American Blvd.</td>

								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date10"></td>
								</tr>
								<tr>
								<tr>
									<td>Tel: 501 207-0669</td>
								</tr>

								<tr>
									<td align="left" id="customerName"></td>
								</tr>
								<tr>
									<td align="left" id="pAddress"></td>
								</tr>

								<c:forEach var="paymentPojo" items="${paymentPojo}"
									varStatus="loop">
									<tr>
										<td align="left">Date Received:${paymentPojo.paymentdate}</td>
									</tr>
								</c:forEach>

							</table>
						</div>


					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="deletepaymentreceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>

								<tr>

									<th>Serial no.</th>
									<th>Payment Method</th>
									<th>#</th>
									<th>Payment Amount</th>
									<th>Invoice id</th>
									<th>Date Received</th>
									<th>Note</th>
									<th>Delete</th>


								</tr>

							</thead>

							<tbody style="text-align: center !important;">



							</tbody>
						</table>

					</div>


					<div class="row">
						<div class="col pull-right">
							<p id="paynote" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col pull-right">
							<div class="print-flex well well-sm">

								<p style="font-size: 14px !important;" id="Createdbyten"></p>



							</div>

						</div>

					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right"></div>

					</div>


				</div>

				
			</div>
	
		</div>
	</div>
</div>


<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js" integrity="sha512-qZvrmS2ekKPF2mSznTQsxqPgnpkI4DNTlrdUmTzrDgektczlKNRRhy5X5AAOnx5S09ydFYWWNSfcEqDTTHgtNA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/vfs_fonts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/pdf-lib@3.0.2/dist/pdf-lib.js"></script>


<script type="text/javascript">

let debounceTimer;

var searchInput = document.getElementById('search-input');
var searchImageContainer = document.querySelector('.search-image-container');
var searchImage = document.getElementById('search-image');

var keyupTimer; // Initialize a timer variable

// Check if a value is saved in localStorage
var savedSearchValue = localStorage.getItem('searchValue');

// If a value is saved, set it in the input field
if (savedSearchValue) {
  searchInput.value = savedSearchValue;
  searchInput.focus();
}

// Add an event listener for input changes (keyup event with a delay)
searchInput.addEventListener('keyup', function (event) {
  var inputValue = this.value;

  if (inputValue.length >= 2) {
    // Save the search value to local storage
    event.preventDefault();
    localStorage.setItem('searchValue', inputValue);
    searchImage.style.display = 'flex';
    searchImageContainer.style.display = 'flex';
    
  } else {
    // Clear the saved value from localStorage when input is cleared
    localStorage.removeItem('searchValue');
    searchImage.style.display = 'none';
    searchImageContainer.style.display = 'none';
  }

  // Clear any previous timer and start a new one
  clearTimeout(keyupTimer);

  // Set a new timer for 500 milliseconds (adjust the delay as needed)
  keyupTimer = setTimeout(function () {
    // Your code to submit the form or redirect goes here
    if (inputValue.length >= 2) {
      event.preventDefault();
      document.getElementById('search-form').submit();
    } else if (inputValue.length == 0) {
    	window.location.href = window.location.href;
    }
  }, 500); // 500 milliseconds delay
});





$(function () {
    $('#myTable').DataTable({      
      "paging": false,
      "pageLength": 100,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": false,
      "autoWidth": true,
      "responsive": true,
      "scrollX": true,
    });
   
  });
 

    //if (!window.location.hash) {
       // window.location = window.location + '#loaded';
       // window.location.reload();
	const reloadUsingLocationHash = () => {
	      window.location.hash = "";
	    }
	    window.onload = reloadUsingLocationHash();
	    //var show = $('#show :selected').val();
	    //alert(show);
	  //  getSalesListNew();
	   document.getElementById('show').onchange = function() {
    window.location.href = this.children[this.selectedIndex].getAttribute('href');
}

  function getSalesListNew(){
	  var show = $('#show :selected').val();
	  alert(show);
	  $.ajax({
			type : 'Get',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/getSalesListpage?pageSize='+show,
			contentType : 'application/json; charset=utf-8',
			success : (function(response) {
				console.log(response);
			})
		});
		//alert("Payment Added Succedfully !");
		// location.reload();
	  }

  $("#show").onchange(
		  function() {
			  var show = $('#show :selected').val();
			  if( show == "10" ){
				  alert("reached 10");
					  $("#show option:contains(10)").attr('selected', true);
					  $("#show option:contains(25)").attr('selected', false);
					  $("#show option:contains(50)").attr('selected', false);
			  }
			  else if(show == "25"){
				  alert("reached 25");
				  $("#show option:contains(10)").attr('selected', false);
				  $("#show option:contains(25)").attr('selected', true);
				  $("#show option:contains(50)").attr('selected', false);
			  }
		  });
	



  function ViewDetails(count){
	  var sTotal = $("#saleTotal"+count).text();
	  var saleId = $("#saleId"+count).text();
	  var ctype = $("#ctype"+count).text();
	  var paid = $("#salePaid"+count).text();
	  var tax =  $("#tax"+count).text();
	  var memberId = $("#memberId"+count).text();
	  
	  let saleSubtotal = 0;
	  let saleGrandTotal = 0 ;
	  let saleTotalTax = 0;
	  
	  let specialSaleSubtotal = 0;
	  let specialSaleGrandTotal = 0 ;
	  let specialSaleTotalTax = 0;
	
	  if(!isNaN(paid)){
		  paid = parseFloat(paid).toFixed(2); 
	  }
	 // alert(sTotal);
		
		 $("#salesReceipt  tbody").empty();
		 $("#tax").html($("#tax"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#total").html($("#total"+count).text());
		 $("#notee").html("Note: " + $("#note"+count).text());
		 $("#po").html("Purchase Order: " + $("#po"+count).text());

		 $("#balTot").html($("#saleTotal"+count).text());
		 $("#saleTotal").html($("#saleTotal"+count).text());
		 $("#sRefno").html("Sale Reference No. " + $("#referenceno"+count).text());
		 $("#cName").html( $("#customer"+count).text());
		 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
		 $("#date").html("Date :" + $("#saleDate"+count).text());
		 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#saleDate"+count).text()));
		 
		 
		 
	if (ctype != "Special")
	  {	
		 $.ajax({
				url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId=' + saleId ,
				type : "GET",
				dataType : "json",
				success : function(data) {
				
					var ajaxCallData = JSON.stringify(data);
					
					$.each(data, function(i, data) {
						
						  saleSubtotal = data.saleSubtotal ;
						  saleGrandTotal = data.saleGrandTotal ;
						  saleTotalTax = data.saleTotalTax ;
						  
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
						if(unitname == "Box 1607" || unitname == "Box 1608" || unitname == "Box 1609" || unitname == "Box 1610"){
							unitname = "Box";
						  }
						if(unitname == "Box lb" ){
							unitname = "lb";
						  }
						if(unitname == "Box 25lb" ){
							unitname = "Box";
						  }
						var rowCount = $('#salesReceipt tr').length ;
						var tr = $("<tr></tr>");
						
						//var productName = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td style="text-align: left;"></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td></td>').html(real_unit_price.toFixed(2)));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal.toFixed(2)));
						tr.append($('<td></td>').html('T'));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt tbody').append(tr);
					});
					// CalculateTotal(paid,tax,ctype);
					
					     $("#totalt").html(saleSubtotal.toFixed(2));
					     $("#totaltaxt").html(saleTotalTax.toFixed(2));
					     $("#balTott").html(saleGrandTotal.toFixed(2));
					     $("#totalPaidt").html(paid);
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
	  	}
	else
		{
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleId?saleId=' + saleId,
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
					if(unitname == "Box 1607" || unitname == "Box 1608" || unitname == "Box 1609" || unitname == "Box 1610"){
						unitname = "Box";
					  }
					if(unitname == "Box lb" ){
						unitname = "lb";
					  }
					if(unitname == "Box 25lb" ){
						unitname = "Box";
					  }
					
					
					var rowCount = $('#salesReceipt tr').length;
					var tr = $('<tr></tr>');
					
				//	var productName = data.product_name.split(' - ')[0];
					
					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td style="text-align: left;"></td>').html(data.product_name));
					tr.append($('<td></td>').html(data.quantity));
					
					tr.append($('<td></td>').html(unitname));
					tr.append($('<td></td>').html(real_unit_price.toFixed(2)));
					tr.append($('<td style="text-align: right;"></td>').html(subtotal.toFixed(2)));
					tr.append($('<td></td>').html('T'));
					tr.append($('<tr></tr>').html());
					
					$('#salesReceipt tbody').append(tr);
				});
			//	CalculateTotal(paid,tax,ctype);
			
				     $("#totalt").html(specialSaleSubtotal.toFixed(2));
				     $("#totaltaxt").html(specialSaleTotalTax.toFixed(2));
				     $("#balTott").html(specialSaleGrandTotal.toFixed(2));
				     $("#totalPaidt").html(paid);

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		
		}
		 
		 
	}
  
  
  
  function ViewDetailsMPN(count){
	  var sTotal = $("#saleTotal"+count).text();
	  var saleId = $("#saleId"+count).text();
	  var ctype = $("#ctype"+count).text();
	  var paid = $("#salePaid"+count).text();
	  var tax =  $("#tax"+count).text();
	  var memberId = $("#memberId"+count).text();
	  
	  let saleSubtotal = 0;
	  let saleGrandTotal = 0 ;
	  let saleTotalTax = 0;
	  
	  let specialSaleSubtotal = 0;
	  let specialSaleGrandTotal = 0 ;
	  let specialSaleTotalTax = 0;
	
	  if(!isNaN(paid)){
		  paid = parseFloat(paid).toFixed(2); 
	  }
	 // alert(sTotal);
		
		 $("#salesReceipt  tbody").empty();
		 $("#tax").html($("#tax"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#total").html($("#total"+count).text());
		 $("#notee").html("Note: " + $("#note"+count).text());
		 $("#po").html("Purchase Order: " + $("#po"+count).text());

		 $("#balTot").html($("#saleTotal"+count).text());
		 $("#saleTotal").html($("#saleTotal"+count).text());
		 $("#sRefno").html("Sale Reference No. " + $("#referenceno"+count).text());
		 $("#cName").html( $("#customer"+count).text());
		 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
		 $("#date").html("Date :" + $("#saleDate"+count).text());
		 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#saleDate"+count).text()));
		 
		 
		 
	if (ctype != "Special")
	  {	
		 $.ajax({
				url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId=' + saleId ,
				type : "GET",
				dataType : "json",
				success : function(data) {
				
					var ajaxCallData = JSON.stringify(data);
					
					$.each(data, function(i, data) {
						
						  saleSubtotal = data.saleSubtotal ;
						  saleGrandTotal = data.saleGrandTotal ;
						  saleTotalTax = data.saleTotalTax ;
						  
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
						if(unitname == "Box 1607" || unitname == "Box 1608" || unitname == "Box 1609" || unitname == "Box 1610"){
							unitname = "Box";
						  }
						if(unitname == "Box lb" ){
							unitname = "lb";
						  }
						if(unitname == "Box 25lb" ){
							unitname = "Box";
						  }
						var rowCount = $('#salesReceipt tr').length ;
						var tr = $("<tr></tr>");
						
						var productName = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td style="text-align: left;"></td>').html(productName));
						tr.append($('<td></td>').html(data.quantity));
						
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td></td>').html(real_unit_price.toFixed(2)));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal.toFixed(2)));
						tr.append($('<td></td>').html('T'));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt tbody').append(tr);
					});
					// CalculateTotal(paid,tax,ctype);
					
					     $("#totalt").html(saleSubtotal.toFixed(2));
					     $("#totaltaxt").html(saleTotalTax.toFixed(2));
					     $("#balTott").html(saleGrandTotal.toFixed(2));
					     $("#totalPaidt").html(paid);
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
	  	}
	else
		{
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleId?saleId=' + saleId,
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
					if(unitname == "Box 1607" || unitname == "Box 1608" || unitname == "Box 1609" || unitname == "Box 1610"){
						unitname = "Box";
					  }
					if(unitname == "Box lb" ){
						unitname = "lb";
					  }
					if(unitname == "Box 25lb" ){
						unitname = "Box";
					  }
					
					var rowCount = $('#salesReceipt tr').length;
					var tr = $('<tr></tr>');
					
					var productName = data.product_name.split(' - ')[0];
					
					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td style="text-align: left;"></td>').html(productName));
					tr.append($('<td></td>').html(data.quantity));
					tr.append($('<td></td>').html(real_unit_price.toFixed(2)));
					tr.append($('<td></td>').html(unitname));
					tr.append($('<td style="text-align: right;"></td>').html(subtotal.toFixed(2)));
					tr.append($('<td></td>').html('T'));
					tr.append($('<tr></tr>').html());
					
					$('#salesReceipt tbody').append(tr);
				});
			//	CalculateTotal(paid,tax,ctype);
			
				     $("#totalt").html(specialSaleSubtotal.toFixed(2));
				     $("#totaltaxt").html(specialSaleTotalTax.toFixed(2));
				     $("#balTott").html(specialSaleGrandTotal.toFixed(2));
				     $("#totalPaidt").html(paid);

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		
		}
		 
		 
	}
  
  
  
  function ViewDetails10(count){
	  
	  var sTotal = $("#saleTotal"+count).text();
	  var saleId = $("#saleId"+count).text();
	  var ctype = $("#ctype"+count).text();
	  var paid = $("#salePaid"+count).text();
	  var tax =  $("#tax"+count).text();
	  if(!isNaN(paid)){
		  paid = parseFloat(paid).toFixed(2); 
	  }
	  //alert(ctype);
		
		 $("#salesReceipt10  tbody").empty();
		 $("#tax").html($("#tax"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#total").html($("#total"+count).text());
		 $("#noteeten").html("Note: " + $("#note"+count).text());
		 $("#poten").html("Purchase Order: " + $("#po"+count).text());
		 $("#cAddresss").html( $("#caddress"+count).text());
		 $("#pincodee").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemainn").html("Tel. " + $("#phonemain"+count).text());
		 $("#balTot").html($("#saleTotal"+count).text());
		 $("#saleTotal").html($("#saleTotal"+count).text());
		 $("#sRefno10").html("Sale Reference No. " + $("#referenceno"+count).text());
		 $("#cNamee").html( $("#customer"+count).text());
		 $("#date10").html("Date :" + $("#saleDate"+count).text());
		 $("#Createdbyten").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#saleDate"+count).text()));
		
		 
		 
	if (ctype != "Special")
	  {	
		 $.ajax({
				url : '${pageContext.request.contextPath}/getSaleitembysaleIdten?saleId=' + saleId +"&ctype=" + ctype ,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					
					$.each(data, function(i, data) {
						
						var real_unit_price = data.real_unit_price;
						var subtotal =  data.subtotal;
						var unitname = data.roll;
						
						if(!isNaN(real_unit_price)){
							real_unit_price = parseFloat(real_unit_price).toFixed(2); 
						  }
						if(!isNaN(subtotal)){
							subtotal = parseFloat(subtotal).toFixed(2); 
						  }
						if(unitname == "Piece"){
							unitname = "Pc";
						  }
						if(unitname == "Wire Roll 328 Ft"){
							unitname = "Roll 328'";
						  }
						
						var rowCount = $('#salesReceipt10 tr').length ;
						var tr = $("<tr></tr>");
						
						//var productName = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td style="text-align: left;"></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<td></td>').html('T'));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt10 tbody').append(tr);
					});
					Calculate(paid,tax,ctype);

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
	  	}
	else
		{
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleIdten?saleId=' + saleId +"&ctype=" + ctype ,
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				
				$.each(data, function(i, data) {
					
					var real_unit_price = data.real_unit_price;
					var subtotal =  data.subtotal;
					var unitname = data.roll;
					
					if(!isNaN(real_unit_price)){
						real_unit_price = parseFloat(real_unit_price).toFixed(2); 
					  }
					if(!isNaN(subtotal)){
						subtotal = parseFloat(subtotal).toFixed(2); 
					  }
					if(unitname == "Piece"){
						unitname = "Pc";
					  }
					if(unitname == "Wire Roll 328 Ft"){
						unitname = "Roll 328'";
					  }
					
					  
					var rowCount = $('#salesReceipt10 tr').length;
					var tr = $('<tr></tr>');
					
					
					//var product_name = data.product_name.split(' - ')[0];
					
					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.product_name));
					tr.append($('<td></td>').html(data.quantity));
					tr.append($('<td></td>').html(real_unit_price));
					tr.append($('<td></td>').html(unitname));
					tr.append($('<td style="text-align: right;"></td>').html(subtotal));
					tr.append($('<td></td>').html('T'));
					tr.append($('<tr></tr>').html());
					
					$('#salesReceipt10 tbody').append(tr);
				});
				Calculate(paid,tax,ctype);

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		
		}
		 
		 
	}
  
  function Calculate(paid,tax,ctype)
  {
    var grandT = 0;
    var taxT = 0;
    var totalT = 0;
    $("#salesReceipt10 > tbody > tr").each(function() {
     var t5 = $(this).find("td:eq(5)").text();
     if (!isNaN(t5)) {
      grandT += parseFloat(t5);
     }
    });
    if(ctype!="special"){
        if(tax==0) {
        	taxT = 0;
        	 
        }else {
         taxT = grandT * .125;
         taxT=+(Math.round(taxT + "e+2")  + "e-2");
        }
        }else{
        	taxT = 0;
        }
        	/*if(tax==0) {
            	//taxT = 0;
            	 taxT = grandT * .125;
                 taxT=+(Math.round(taxT + "e+2")  + "e-2");
            }else {
             taxT = grandT * .125;
             taxT=+(Math.round(taxT + "e+2")  + "e-2");
            }*/
    grandT= grandT.toFixed(2);
     totalT = Number(grandT)+taxT;
     totalT=  Math.round(totalT * 100) / 100;
     $("#totalten").html(grandT);
     $("#totaltaxten").html(taxT.toFixed(2));
     $("#balTotten").html(totalT.toFixed(2));
     $("#totalPaidten").html(paid);
  }
  
  function CalculateTotal(paid,tax,ctype)
  {
	  
	  //alert(ctype);
    var grandT = 0;
    var taxT = 0;
    var totalT = 0;
    
    $("#salesReceipt > tbody > tr").each(function() {
     var t5 = $(this).find("td:eq(5)").text();
     if (!isNaN(t5)) {
      grandT += parseFloat(t5); 
     }
    });
    grandT= grandT.toFixed(2);
  
    if (ctype != "Special") {
        if (tax == 0) {
           // alert("0");
            taxT = 0;
        } else {
            //alert("not special");
            taxT = grandT * 0.125;
            taxT = +(Math.round(taxT + "e+2") + "e-2");
        }
    } else {
        taxT = 0;
    }
    	
        /*if (tax == 0) {
           // alert("tax");
            taxT = grandT * 0.125;
            taxT = +(Math.round(taxT + "e+2") + "e-2");
        } else {
            taxT = grandT * 0.125;
            taxT = +(Math.round(taxT + "e+2") + "e-2");
        }*/

    
    
    totalT = Number(grandT)+taxT;
    totalT=  Math.round(totalT * 100) / 100;
     $("#totalt").html(grandT);
     $("#totaltaxt").html(taxT.toFixed(2));
     $("#balTott").html(totalT.toFixed(2));
     $("#totalPaidt").html(paid);
    // Math.round(12.345 * 100) / 100;
  }
  
  
  
  function loadPayment(count){
	  var SaleBalance = $("#saleBalance"+count).text();
	  var saleTotal = $("#saleTotal"+count).text();
	  var ctype= $("#ctype"+count).text();
	 // alert(ctype);
	  
	  var SaleId = $("#saleId"+count).text();
	  var memberId = $("#memberId"+count).text();
	  var SalePaid = $("#salePaid"+count).text();
	  var Deposit=  $("#CreditPay"+count).text();
	  
	  if(Deposit!=0){
		  const paymentOption = prompt("Customer has a deposit of $" + Deposit + "\nSelect an option:\n1. Retain as available deposit\n2. Give a refund\n3. Apply to invoice");
	  if(paymentOption == "3"){
		  let text = "Customer has a deposit of $" + Deposit + ", do you want to apply the credit as payment ?";
		 // if (confirm(text) == true) {
			var amount=saleTotal-SalePaid;
			amount=amount.toFixed(2);
		  
			  $("#amount").val(saleTotal);
			  $("#hidsaleid").val(SaleId);
			  $("#hidmemberId").val(memberId);
			  $("#paidamount").val(SalePaid);
			  $("#ctype").val(ctype);
			  $("#Deposit").val(Deposit);
			  $("#payamount").val(amount);
			  $("#ptype").val("Deposit");
			  // Hide other payment types
			    $("select#ptype option[value!='Deposit']").hide();
			  //$('#ptype :selected').val("Deposit");
			  //}
	  }else if(paymentOption == "1"){ 
				  $("#amount").val(saleTotal);
				  $("#hidsaleid").val(SaleId);
				  $("#hidmemberId").val(memberId);
				  $("#paidamount").val(SalePaid);
				  $("#ctype").val(ctype);
				  $("#Deposit").val(Deposit);
				 
			 
	  }else if(paymentOption == "2"){ 
		  $("#amount").val(saleTotal);
		  $("#hidsaleid").val(SaleId);
		  $("#hidmemberId").val(memberId);
		  $("#paidamount").val(SalePaid);
		  $("#ctype").val(ctype);
		  $("#Deposit").val(Deposit);
		  $("#payamount").val(Deposit);
		  $("#ptype").val("Refund");
		  $("select#ptype option[value!='Refund']").hide();
	 
}
	  }else{
		  $("#amount").val(saleTotal);
		  $("#hidsaleid").val(SaleId);
		  $("#hidmemberId").val(memberId);
		  $("#paidamount").val(SalePaid);
		  $("#ctype").val(ctype);
		  $("#Deposit").val(Deposit);
	
	  }
	  
		  
	  
	  
	  
	
	//  alert("Sale Balance: " + SaleBalance + "....... Sale ID: " + SaleId + "............MemberId:"+ memberId) ;
	/*  $("#amount").val(saleTotal);
	  $("#hidsaleid").val(SaleId);
	  $("#hidmemberId").val(memberId);
	  $("#paidamount").val(SalePaid);
	  $("#ctype").val(ctype);
	  $("#Deposit").val(Deposit);*/
	// $("#amount").html(SaleBalance).text();
	// $("#totaltax").html($("#totaltax"+count).text());
		 
  }
  
  function addPayment(){
	  
	  	 var saleId = $('#hidsaleid').val();
	  	 var memberId = $('#hidmemberId').val();
		 var amount = $('#amount').val();
		 var note = $('#paymentnote').val();
		 var paid = $('#paidamount').val();
		 var amounttopay = $('#payamount').val();
		 var pref = $('#chequecc').val();
		 var ptype = $('#ptype :selected').val();
		 var payamount= $('#payamount').val();
		 var paymentnote=$('#paymentnote').val();
		 var ctype=$('#ctype').val();
		 var deposit=$('#Deposit').val();
		
		 var amt=amount-paid;
		// amt=amt.toFixed(2);
		
		// alert(amt);
		
		
	 
		// alert(ptype);
		 var flag =0;
		 if(amounttopay =="") {
			 alert("The Amount cannot be blank  !");
			  flag =1;
		 }
		 
	      //if (ptype!="Cash"&& amounttopay > amt.toFixed(2) &&ptype!="Deposit"&&ptype!="Refund"&&ptype!="Online" )
	       if (ptype!="Cash" && parseFloat(amounttopay) > amt.toFixed(2))
			{
	    	  alert(amounttopay);
	    	  alert(amt);
			
			  alert("The Amount cannot greater than the pending amount "
			  		+ "Please use BulkPayment!");
			  flag =1;
			  
			}
		 
		 if (ptype == "")
			{
			  alert("The Payment type must be selected !");
			  flag =1;
			}
		 
		 if (pref == "" && ptype!="Cash" && ptype!="Credit Card" && ptype!="Online" && ptype!="Other" && ptype!="Deposit"&& ptype!="Refund")
			{
			  alert("The Payment reference number must be entered !");
			  flag =1;
			}
		 /*if(ptype=="Deposit" && parseFloat(amounttopay) > parseFloat(deposit)){
			// alert(amounttopay);
				//alert(deposit);
				// alert(ptype);
		
			 alert("Amount can't be greater than deposit !");
			  flag=1;
		 }*/
		 amt=amt.toFixed(2);
		
		 if(flag ==0)
		 
		 {
		 
		// alert("Sale Amount: " + amount + "....... Sale ID: " + saleId + "....... Payment Note: " + note);
		 
		
		 item = {}
	     item ["saleId"] = saleId;
		 item ["amount"] = amounttopay;
		 item ["note"] = note;
		 item ["ptype"] = ptype;
		 item ["pref"] = pref;
		 item["memberId"] = memberId;
		 item["payamount"]=payamount;
		 item["note"]=paymentnote;
		 item["ctype"]=ctype;
		 
		 jsonObj = JSON.stringify(item);
		 console.log("JSON Object",jsonObj);
		
	
	//	 alert(myJSON);
		 
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/addPayment',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : jsonObj,
			    success : function(response) {
			        alert(response.msgDescr);
			        location.reload();
			    },
			    error : function(xhr, status, error) {
			        alert("Error: " + error);
			    }
			});
			//alert("Payment Added Succedfully !");
	    //  location.reload();
				
		  }
  		}
  
  function diff() {
	 
		 var saleId = $('#hidsaleid').val();
	  	 var memberId = $('#hidmemberId').val();
		 var amount = $('#amount').val();
		 var note = $('#paymentnote').val();
		 var paid = $('#paidamount').val();
		 var amounttopay = $('#payamount').val();
		 var pref = $('#chequecc').val();
		 var ptype = $('#ptype :selected').val();
		
		 if(ptype=="Cash"&&amounttopay>=(amount-paid)) {
			 //var diff=amounttopay-paid-amount;
			 var pamount=amount-paid;
			 var diff=amounttopay-pamount;
			 //alert(pamount);
			 //alert(diff);
			// alert("Cash to give back="+diff.toFixed(2));
		 }if(ptype=="Cash"&&amounttopay<(amount-paid)) {
			 var cash=0;
			 var diff=cash;
			 
		 }
		 if (!isNaN(diff)) {
				document.getElementById('diff').value = diff.toFixed(2);
			}
		 
  }
  $("#payamount").change(function() {
	  diff();
	  
  });

  function searchTable() {
	    var input, filter, found, table, tr, td, i, j;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    table = document.getElementById("myTable");
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
  
  function printData()
  {
     var divToPrint=document.getElementById("printTable");
     newWin= window.open("");
     newWin.document.write(divToPrint.outerHTML);

     	setTimeout(function () { // wait until all resources loaded 
  		newWin.document.close(); // necessary for IE >= 10
  		newWin.focus(); // necessary for IE >= 10
  		newWin.print();  // change window to winPrint
  		newWin.close();// change window to winPrint
         	}, 350);
    	return true;
  }
  
  function exportPDF()
  {
      var element = document.getElementById('printTable');
      var opt = {
          margin:       0.5,
          filename:     'payment'+<%=System.currentTimeMillis()%>+'.pdf',
          image:        { type: 'jpeg', quality: 1 },
          html2canvas:  { scale: 1 },
          jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
        };
      html2pdf().set(opt).from(element).save();
  }
  
  //without 10% pdf function starts here
  
  function generatePDF1(pdfContent) {
	  // Add custom CSS styles to the pdfmake definition
	 var styles = {
			    customFontSize: 11,
			    tableCell: {
			      margin: [0, 2, 0, 2],
			      fillColor: "#ffffff", // Set background color to white
			      color:  "#111111", // Set text color to a darker color (replace "#333333" with the desired color)
			    },
			    tableCellBold: {
			      bold: true,
			      fillColor: "#ffffff",
			      color:  "#111111",
			    },
			    tableHeader: {
			      bold: true,
			      fillColor: "#f3f3f3",
			      color:  "#111111",
			    },
			    tableHeaderBold: {
			      bold: true,
			      fillColor: "#f3f3f3",
			      color: "#111111",
			    },
			  };

	  // Set the custom styles and default font size in the pdfDefinition
	  var pdfDefinition = {
	    content: pdfContent,
	    pageMargins: [40, 60, 40, 60],
	    pageSize: { width: 700, height: 900 },
	    styles: styles,
	    defaultStyle: {
	      fontSize: styles.customFontSize,
	    },
	  };
	      	  
	  // Reset fonts to default (remove any custom font registration)
	  pdfMake.fonts = null;

	  var pdfDocGenerator = pdfMake.createPdf(pdfDefinition);
	  pdfDocGenerator.download('Sales' + Date.now() + '.pdf');
	}



  function exportPDF1() {
	    // Find the logo image element
	    var logoImage = document.getElementById('logoImage');

	    // Clone the content inside the printTable1 div
	    var printTable1Clone = document.getElementById('printTable2').cloneNode(true);

	    // Remove the specific <div> element with the ID "headerDiv" from the cloned content
	    var headerDiv = printTable1Clone.querySelector('#headerDiv');
	    if (headerDiv) {
	        headerDiv.remove();
	    }

	    // Get the date and quoteRefNo from the corresponding elements
	    var date = document.getElementById('date').textContent.trim();
	    var saleRefNo = document.getElementById('sRefno').textContent.trim();
	    var po = document.getElementById('po').textContent.trim();

	    // Update the placeholders with the correct values
	    var datePlaceholder = printTable1Clone.querySelector('#datePlaceholder');
	    if (datePlaceholder) {
	        datePlaceholder.textContent = 'Date: ' + date;
	    }

	    var sRefnoPlaceholder = printTable1Clone.querySelector('#sRefnoPlaceholder');
	    if (sRefnoPlaceholder) {
	        sRefnoPlaceholder.textContent = 'Sale Reference No.: ' + saleRefNo;
	    }

	    // Convert the cloned content to pdfMake format
	    var pdfContent = convertContainerToPdfMake1(printTable1Clone);

	    // Filter out any empty elements from pdfContent array
	    pdfContent = pdfContent.filter(function (element) {
	        return element !== undefined;
	    });

	    // Get the "Bill To" section from the form
	    var billToSection = document.getElementById('billToSection');
	    var billToContent = addBillToSection1(billToSection);

	    // Add the "Bill To" section manually to the pdfContent
	    pdfContent.unshift(billToContent);

	    // Get the content of the "notes" div and convert it to PDFMake format
	    var notesContent = document.getElementById('notes');
	    var notesPdfContent = convertContainerToPdfMake1(notesContent);

	    // Add the "notes" content to the pdfContent array
	    pdfContent.push(notesPdfContent);

	    // Add an empty space (as padding) after the second table's content
	    var emptySpace = { text: '', margin: [0, 20, 0, 0] };
	    pdfContent.push(emptySpace);

	    // Convert the logo image to a data URL
	    convertImageToPdfMake1(logoImage)
	        .then(function (pdfImage) {
	            // Construct logo content
	            var logoContent = [
	                { image: pdfImage.image, width: 220, alignment: 'left' },
	                { text: '\n' },
	                '3754 Central American Blvd.',
	                'Belize City Belize',
	                'Tel: 501 207-0669',
	                'TIN # 128693',
	                { text: '\n' },
	                { text: '\n' },
	            ];

	            // Construct right content
	            var rightContent = [
	                { text: '' }, // Add an empty space to ensure proper alignment
	                { text: '' },
	                { text: 'Tax Invoice',alignment: 'right' },
	                { text: date, alignment: 'right'},
	                { text: saleRefNo, alignment: 'right' },
	                { text: po, alignment: 'right'},
	                { text: 'Sales Person',alignment: 'right' },
	            ];

	            // Combine logo content and right content
	            var combinedContent = [];
	            for (var i = 0; i < Math.max(logoContent.length, rightContent.length); i++) {
	                combinedContent.push([logoContent[i] || '', rightContent[i] || '']);
	            }

	            // Add combined content to pdfContent
	            pdfContent.unshift({
	                table: {
	                    widths: ['*', '*'],
	                    body: combinedContent,
	                },
	                layout: 'noBorders',
	            });

	            // Generate the PDF with the updated content
	            generatePDF1(pdfContent);
	        })
	        .catch(function (error) {
	            console.error(error);
	        });
	}


	function convertContainerToPdfMake1(container) {
		  var pdfElements = [];

		  for (var i = 0; i < container.childNodes.length; i++) {
		    var childNode = container.childNodes[i];

		    // Skip the specific <div> element with the ID "notes"
		    if (childNode.nodeType === Node.ELEMENT_NODE && childNode.id === 'notes') {
		      continue;
		    }

		    // Skip the specific <div> element with the class "excludeFromPdf"
		    if (childNode.nodeType === Node.ELEMENT_NODE && childNode.classList.contains('excludeFromPdf')) {
		      continue;
		    }

		    var pdfElement = convertNodeToPdfMake1(childNode);
		    if (pdfElement) {
		      pdfElements.push(pdfElement);
		    }
		  }

		  return pdfElements;
		}

	function addBillToSection1(billToSection) {
		  var billToContent = [];

		  // Extract name, address, and TIN# from the "Bill To" section
		  var nameElement = billToSection.querySelector('#cName');
		  var addressElement = billToSection.querySelector('#cAddress');
		  var telElement = billToSection.querySelector('#phonemain'); // Update the ID to match the "Tel." element
		  var tinElement = billToSection.querySelector('#pincode');
		  var nameText = nameElement ? nameElement.textContent.trim() : '';
		  var addressText = addressElement ? addressElement.textContent.trim() : '';
		  var telText = telElement ? telElement.textContent.trim() : ''; // Use "telElement" to get the "Tel." value
		  var tinText = tinElement ? tinElement.textContent.trim() : '';

		  // Add the "Bill To" section content
		  billToContent.push({ text: 'Bill To:', bold: false });
		  billToContent.push({ text: 'Name: ' + nameText, noWrap: true });
		  billToContent.push({ text: 'Address: ' + addressText, noWrap: true });
		  billToContent.push({ text: '' + telText }); // Include "Tel." value with the label
		  billToContent.push({ text: tinText }); // Add the TIN# dynamically
		  billToContent.push({ text: '\n\n' });

		  return { stack: billToContent };
		}

	function convertNodeToPdfMake1(node) {
	    if (node.nodeType === Node.TEXT_NODE) {
	        return { text: node.textContent.trim() };
	    } else if (node.nodeType === Node.ELEMENT_NODE) {
	        if (node.tagName.toLowerCase() === 'table') {
	            return convertTableToPdfMake1(node);
	        } else if (node.tagName.toLowerCase() === 'img') {
	            return convertImageToPdfMake1(node);
	        } else if (node.classList.contains('col-left')) {
	            return convertColContentToPdfMake1(node);
	        } else if (node.classList.contains('excludeFromPdf')) {
	            return undefined; // Skip this element from PDF generation
	        } else if (node.classList.contains('bold-text')) { // Handle the bold-text class
	            return { text: node.textContent.trim(), bold: true, margin: [0, 5, 0, 0] };
	        } else if (node.classList.contains('col') && node.classList.contains('pull-right')) {
	            return {
	                stack: [{ text: '', margin: [0, 10, 0, 5] }, convertContainerToPdfMake1(node)]
	            };
	        } else {
	            var pdfElement = { stack: [] };
	            for (var i = 0; i < node.childNodes.length; i++) {
	                var childNode = node.childNodes[i];
	                var childContent = convertNodeToPdfMake1(childNode);
	                pdfElement.stack.push(childContent);
	            }
	            return pdfElement;
	        }
	    }
	}


	function convertColContentToPdfMake1(colElement) {
	  var pdfElement = { stack: [] };
	  for (var i = 0; i < colElement.childNodes.length; i++) {
	    var childNode = colElement.childNodes[i];
	    var childContent = convertNodeToPdfMake1(childNode);
	    pdfElement.stack.push(childContent);
	  }
	  return pdfElement;
	}

	function convertTableToPdfMake1(table) {
		  var pdfTable = {
		    table: {
		      widths: [],
		      body: [],
		    },
		  };

		  // Check if this is the second table (totalTable with ID "fullWidthTable")
		  var isSecondTable = table.id === "fullWidthTable";

		  // Set the table column widths based on the widths of the original table
		  var colWidths = [];
		  var colCount = table.rows[0].cells.length;

		  if (isSecondTable) {
		    // Set the widths of the second table's columns to 80% - 20%
		    colWidths.push("80%", "20%");
		  } else {
		    // Set the widths of the first table's columns as 10% - 45% - 10% - 10% - 15% - 10%
		    colWidths.push("8%", "48%", "7%", "8%", "14%", "10%", "5%");
		  }

		  pdfTable.table.widths = colWidths;

		  // Process all rows of the table
		  var rows = table.querySelectorAll("tr");
		  rows.forEach(function (row, rowIndex) {
		    var pdfRow = [];
		    var cells = row.querySelectorAll("th, td");
		    cells.forEach(function (cell, index) {
		      var cellText = cell.textContent.trim();
		      var style = cell.tagName.toLowerCase() === "th" ? "tableHeader" : "tableCell";
		      var alignment = isSecondTable ? "right" : "center"; // Align all items in the second table at the right side

		      // Make the first row (headings) of the first table bold
		      if (!isSecondTable && rowIndex === 0) {
		        style = "tableHeaderBold";
		      }

		      // Apply bold style to all cells in the second table
		      if (isSecondTable) {
		        style = "tableCellBold";
		        // Wrap the cell content in a stack and set margin for padding
		        pdfRow.push({
		          stack: [
		            { text: cellText, style: style, alignment: alignment, margin: [0, 4, 0, 5] }
		          ]
		        });
		      } else {
		        // Handle images
		        if (cell.tagName.toLowerCase() === "td" && cell.querySelector("img")) {
		          var imgElement = cell.querySelector("img");
		          convertImageToPdfMake1(imgElement)
		            .then(function (pdfImage) {
		              pdfRow.push({ image: pdfImage.image, width: pdfImage.width, alignment: alignment });
		            })
		            .catch(function (error) {
		              console.error(error);
		            });
		        } else {
		          // Align text based on the table type (first or second)
		          if (index === colCount - 1) {
		            // Align the content in the last column to the right
		            alignment = "right";
		          }
		          pdfRow.push({ text: cellText, style: style, alignment: alignment });
		        }
		      }
		    });
		    pdfTable.table.body.push(pdfRow);
		  });
		  return pdfTable;
		}

	function convertImageToPdfMake1(img) {
	  return new Promise(function (resolve, reject) {
	    var canvas = document.createElement('canvas');
	    var ctx = canvas.getContext('2d');

	    img.crossOrigin = 'Anonymous'; // Add this line to handle CORS issues with images

	    img.onload = function () {
	      canvas.width = img.width;
	      canvas.height = img.height;
	      ctx.drawImage(img, 0, 0, img.width, img.height);
	      var dataURL = canvas.toDataURL('image/png');
	      resolve({ image: dataURL, width: img.width });
	    };

	    img.onerror = function () {
	      reject(new Error('Failed to load image'));
	    };

	    img.src = img.src; // Set the 'src' attribute again to trigger the onload event
	  });
	}

	function convertImageToDataURL1(url) {
	  return new Promise(function (resolve, reject) {
	    var xhr = new XMLHttpRequest();
	    xhr.open('GET', url, true);
	    xhr.responseType = 'blob';
	    xhr.onload = function () {
	      if (xhr.status === 200) {
	        var reader = new FileReader();
	        reader.onloadend = function () {
	          resolve(reader.result);
	        };
	        reader.readAsDataURL(xhr.response);
	      } else {
	        reject(new Error('Failed to load image'));
	      }
	    };
	    xhr.onerror = function () {
	      reject(new Error('Failed to load image'));
	    };
	    xhr.send();
	  });
	}

	//without 10%pdf function ends here

	//10% pdf function starts here 
  
	  function generatePDF2(pdfContent2) {
		  // Add custom CSS styles to the pdfmake definition
		 var styles = {
				    customFontSize: 11,
				    tableCell: {
				      margin: [0, 2, 0, 2],
				      fillColor: "#ffffff", // Set background color to white
				      color:  "#111111", // Set text color to a darker color (replace "#333333" with the desired color)
				    },
				    tableCellBold: {
				      bold: true,
				      fillColor: "#ffffff",
				      color:  "#111111",
				    },
				    tableHeader: {
				      bold: true,
				      fillColor: "#f3f3f3",
				      color:  "#111111",
				    },
				    tableHeaderBold: {
				      bold: true,
				      fillColor: "#f3f3f3",
				      color: "#111111",
				    },
				  };

		  // Set the custom styles and default font size in the pdfDefinition
		  var pdfDefinition = {
		    content: pdfContent2,
		    pageMargins: [40, 60, 40, 60],
		    pageSize: { width: 700, height: 900 },
		    styles: styles,
		    defaultStyle: {
		      fontSize: styles.customFontSize,
		    },
		  };
		      	  
		  // Reset fonts to default (remove any custom font registration)
		  pdfMake.fonts = null;

		  var pdfDocGenerator = pdfMake.createPdf(pdfDefinition);
		  pdfDocGenerator.download('Sales' + Date.now() + '.pdf');
		}



		function exportPDF2() {
		  // Find the logo image element
		  var logoImage = document.getElementById('logoImage1');

		  // Clone the content inside the printTable1 div
		  var printTable1Clone = document.getElementById('printTable3').cloneNode(true);

		  // Remove the specific <div> element with the ID "headerDiv" from the cloned content
		  var headerDiv = printTable1Clone.querySelector('#headerDiv');
		  if (headerDiv) {
		    headerDiv.remove();
		  }
		  // Get the date and quoteRefNo from the corresponding elements
		  var date = document.getElementById('date10').textContent.trim();
		  var saleRefNo = document.getElementById('sRefno10').textContent.trim();
		  var po = document.getElementById('poten').textContent.trim();

		  // Update the placeholders with the correct values
		  var datePlaceholder = printTable1Clone.querySelector('#datePlaceholder');
		  if (datePlaceholder) {
		    datePlaceholder.textContent = 'Date: ' + date;
		  }

		  var sRefnoPlaceholder = printTable1Clone.querySelector('#sRefnoPlaceholder');
		  if (sRefnoPlaceholder) {
		    sRefnoPlaceholder.textContent = 'Sale Reference No.: ' + saleRefNo;
		  }

		  // Convert the cloned content to pdfMake format
		  var pdfContent2 = convertContainerToPdfMake2(printTable1Clone);
		    
		  // Filter out any empty elements from pdfContent array
		  pdfContent2 = pdfContent2.filter(function (element) {
		    return element !== undefined;
		  });

		  // Get the "Bill To" section from the form
		  var billToSection = document.getElementById('billToSection2');
		  var billToContent = addBillToSection2(billToSection);

		  // Add the "Bill To" section manually to the pdfContent
		  pdfContent2.unshift(billToContent);

		  // Add an empty space (as padding) after the second table's content
		  var emptySpace = { text: '', margin: [0, 20, 0, 0] };
		  pdfContent2.push(emptySpace);
		  
		  // Convert the logo image to a data URL
		  convertImageToPdfMake2(logoImage)
		    .then(function (pdfImage) {
		      // Construct logo content
		      var logoContent = [
		        { image: pdfImage.image, width: 220, alignment: 'left' },
		        { text: '\n' },
		        '3754 Central American Blvd.',
		        'Belize City Belize',
		        'Tel: 501 207-0669',
		        'TIN # 128693',
		        { text: '\n' },
		        { text: '\n' },
		      ];

		      // Construct right content
		      var rightContent = [
		        { text: '' }, // Add an empty space to ensure proper alignment
		        { text: '' },
		        { text: 'Tax Invoice',alignment: 'right' },
		        { text: date, alignment: 'right'},
		        { text: saleRefNo, alignment: 'right' },
		        { text: po, alignment: 'right'},
		        { text: 'Sales Person',alignment: 'right' },
		      ];

		      // Combine logo content and right content
		      var combinedContent = [];
		      for (var i = 0; i < Math.max(logoContent.length, rightContent.length); i++) {
		        combinedContent.push([logoContent[i] || '', rightContent[i] || '']);
		      }

		      // Add combined content to pdfContent
		      pdfContent2.unshift({
		        table: {
		          widths: ['*', '*'],
		          body: combinedContent,
		        },
		        layout: 'noBorders',
		      });

		      // Generate the PDF with the updated content
		      generatePDF2(pdfContent2);
		    })
		    .catch(function (error) {
		      console.error(error);
		    });
		}


		function convertContainerToPdfMake2(container) {
			  var pdfElements = [];

			  for (var i = 0; i < container.childNodes.length; i++) {
			    var childNode = container.childNodes[i];

			    // Skip the specific <div> element with the ID "notes"
			    if (childNode.nodeType === Node.ELEMENT_NODE && childNode.id === 'notes') {
			      continue;
			    }

			    // Skip the specific <div> element with the class "excludeFromPdf"
			    if (childNode.nodeType === Node.ELEMENT_NODE && childNode.classList.contains('excludeFromPdf')) {
			      continue;
			    }

			    var pdfElement = convertNodeToPdfMake2(childNode);
			    if (pdfElement) {
			      pdfElements.push(pdfElement);
			    }
			  }

			  return pdfElements;
			}

		function addBillToSection2(billToSection2) {
			  var billToContent = [];

			  // Extract name, address, and TIN# from the "Bill To" section
			  var nameElement = billToSection2.querySelector('#cNamee');
			  var addressElement = billToSection2.querySelector('#cAddresss');
			  var telElement = billToSection2.querySelector('#phonemainn'); // Update the ID to match the "Tel." element
			  var tinElement = billToSection2.querySelector('#pincodee');
			  var nameText = nameElement ? nameElement.textContent.trim() : '';
			  var addressText = addressElement ? addressElement.textContent.trim() : '';
			  var telText = telElement ? telElement.textContent.trim() : ''; // Use "telElement" to get the "Tel." value
			  var tinText = tinElement ? tinElement.textContent.trim() : '';

			  // Add the "Bill To" section content
			  billToContent.push({ text: 'Bill To:', bold: false });
			  billToContent.push({ text: 'Name: ' + nameText, noWrap: true });
			  billToContent.push({ text: 'Address: ' + addressText, noWrap: true });
			  billToContent.push({ text: '' + telText }); // Include "Tel." value with the label
			  billToContent.push({ text: tinText }); // Add the TIN# dynamically
			  billToContent.push({ text: '\n\n' });

			  return { stack: billToContent };
			}

		function convertNodeToPdfMake2(node) {
		    if (node.nodeType === Node.TEXT_NODE) {
		        return { text: node.textContent.trim() };
		    } else if (node.nodeType === Node.ELEMENT_NODE) {
		        if (node.tagName.toLowerCase() === 'table') {
		            return convertTableToPdfMake2(node);
		        } else if (node.tagName.toLowerCase() === 'img') {
		            return convertImageToPdfMake2(node);
		        } else if (node.classList.contains('col-left')) {
		            return convertColContentToPdfMake2(node);
		        } else if (node.classList.contains('excludeFromPdf')) {
		            return undefined; // Skip this element from PDF generation
		        } else if (node.classList.contains('bold-text')) { // Handle the bold-text class
		            return { text: node.textContent.trim(), bold: true, margin: [0, 5, 0, 0] };
		        } else if (node.classList.contains('col') && node.classList.contains('pull-right')) {
		            return {
		                stack: [{ text: '', margin: [0, 10, 0, 5] }, convertContainerToPdfMake2(node)]
		            };
		        } else {
		            var pdfElement = { stack: [] };
		            for (var i = 0; i < node.childNodes.length; i++) {
		                var childNode = node.childNodes[i];
		                var childContent = convertNodeToPdfMake2(childNode);
		                pdfElement.stack.push(childContent);
		            }
		            return pdfElement;
		        }
		    }
		}


		function convertColContentToPdfMake2(colElement) {
		  var pdfElement = { stack: [] };
		  for (var i = 0; i < colElement.childNodes.length; i++) {
		    var childNode = colElement.childNodes[i];
		    var childContent = convertNodeToPdfMake2(childNode);
		    pdfElement.stack.push(childContent);
		  }
		  return pdfElement;
		}

		function convertTableToPdfMake2(table) {
			  var pdfTable = {
			    table: {
			      widths: [],
			      body: [],
			    },
			  };

			  // Check if this is the second table (totalTable with ID "fullWidthTable")
			  var isSecondTable = table.id === "fullWidthTable";

			  // Set the table column widths based on the widths of the original table
			  var colWidths = [];
			  var colCount = table.rows[0].cells.length;

			  if (isSecondTable) {
			    // Set the widths of the second table's columns to 80% - 20%
			    colWidths.push("80%", "20%");
			  } else {
			    // Set the widths of the first table's columns as 10% - 45% - 10% - 10% - 15% - 10%
				  colWidths.push("8%", "48%", "7%", "8%", "14%", "10%", "5%");
			  }

			  pdfTable.table.widths = colWidths;

			  // Process all rows of the table
			  var rows = table.querySelectorAll("tr");
			  rows.forEach(function (row, rowIndex) {
			    var pdfRow = [];
			    var cells = row.querySelectorAll("th, td");
			    cells.forEach(function (cell, index) {
			      var cellText = cell.textContent.trim();
			      var style = cell.tagName.toLowerCase() === "th" ? "tableHeader" : "tableCell";
			      var alignment = isSecondTable ? "right" : "center"; // Align all items in the second table at the right side

			      // Make the first row (headings) of the first table bold
			      if (!isSecondTable && rowIndex === 0) {
			        style = "tableHeaderBold";
			      }

			      // Apply bold style to all cells in the second table
			      if (isSecondTable) {
			        style = "tableCellBold";
			        // Wrap the cell content in a stack and set margin for padding
			        pdfRow.push({
			          stack: [
			            { text: cellText, style: style, alignment: alignment, margin: [0, 4, 0, 5] }
			          ]
			        });
			      } else {
			        // Handle images
			        if (cell.tagName.toLowerCase() === "td" && cell.querySelector("img")) {
			          var imgElement = cell.querySelector("img");
			          convertImageToPdfMake2(imgElement)
			            .then(function (pdfImage) {
			              pdfRow.push({ image: pdfImage.image, width: pdfImage.width, alignment: alignment });
			            })
			            .catch(function (error) {
			              console.error(error);
			            });
			        } else {
			          // Align text based on the table type (first or second)
			          if (index === colCount - 1) {
			            // Align the content in the last column to the right
			            alignment = "right";
			          }
			          pdfRow.push({ text: cellText, style: style, alignment: alignment });
			        }
			      }
			    });
			    pdfTable.table.body.push(pdfRow);
			  });
			  return pdfTable;
			}

		function convertImageToPdfMake2(img) {
		  return new Promise(function (resolve, reject) {
		    var canvas = document.createElement('canvas');
		    var ctx = canvas.getContext('2d');

		    img.crossOrigin = 'Anonymous'; // Add this line to handle CORS issues with images

		    img.onload = function () {
		      canvas.width = img.width;
		      canvas.height = img.height;
		      ctx.drawImage(img, 0, 0, img.width, img.height);
		      var dataURL = canvas.toDataURL('image/png');
		      resolve({ image: dataURL, width: img.width });
		    };

		    img.onerror = function () {
		      reject(new Error('Failed to load image'));
		    };

		    img.src = img.src; // Set the 'src' attribute again to trigger the onload event
		  });
		}

		function convertImageToDataURL2(url) {
		  return new Promise(function (resolve, reject) {
		    var xhr = new XMLHttpRequest();
		    xhr.open('GET', url, true);
		    xhr.responseType = 'blob';
		    xhr.onload = function () {
		      if (xhr.status === 200) {
		        var reader = new FileReader();
		        reader.onloadend = function () {
		          resolve(reader.result);
		        };
		        reader.readAsDataURL(xhr.response);
		      } else {
		        reject(new Error('Failed to load image'));
		      }
		    };
		    xhr.onerror = function () {
		      reject(new Error('Failed to load image'));
		    };
		    xhr.send();
		  });
		}


	//10% pdf function ends here 


function deleteDetails(count, purchaseid){
	var ctype = $("#ctype"+count).text();
	  var x = confirm("Are you sure you want to delete?");
  if (x) {
	  if (ctype != "Special") {
	  $.ajax({
			url : '${pageContext.request.contextPath}/deleteSale',
			type : "POST",
			dataType : "json",
			data:{id:purchaseid},
			success : function(data) {
	 			alertify
				  .alert(data.msgDescr, function(){
					 location.reload();
				  }); 
				 
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
  }
		else
		{
		
			  $.ajax({
					url : '${pageContext.request.contextPath}/deleteSpecialSale',
					type : "POST",
					dataType : "json",
					data:{id:purchaseid},
					success : function(data) {
			 			alertify
						  .alert(data.msgDescr, function(){
							 location.reload();
						  }); 
						 
					},
					error : function(error) {
						console.log(`Error ${error}`);
					}

				});
		
		
		}
  }
  
}

function ViewPayment(count) {
	
	
	 var memberId = $("#memberId"+count).text();
	 var SaleId = $("#saleId"+count).text();
	 var ctype = $("#ctype"+count).text();
	// alert(SaleId);
	 $('#paymentreceipt tbody').empty();
	 
	 $("#customerName").html("Customer Name: " + $("#customer"+count).text());
	 $("#pAddress").html("Customer Address:"+ $("#caddress"+count).text());
	

	 $.ajax({
			url : '${pageContext.request.contextPath}/getPaymentsaleid?SaleId=' + SaleId+ "&ctype=" + ctype ,
			
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				
				$.each(data, function(i, data) {
					
					//String paymentdate = json.getString(data.paymentdate);//"2013-03-26"
					//DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
					const paymentdate =(data.paymentdate);
					const dt = new Date(paymentdate);
			
					//var d=paymentdate.Format("dd/MM/yyyy");
					//var paymentdate =(data.paymentdate)Date;
					//alert(formattedDate);
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");

					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.ptype));
					tr.append($('<td></td>').html(data.pref));
					tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
					tr.append($('<td></td>').html(data.referenceno));
					tr.append($('<td></td>').html(dt));
					tr.append($('<td></td>').html(data.note));
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
				//$('#paymentreceipt tbody').empty();
				

			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
	
	
}
  
function deletePaymentView(count) {
	
	
	 var memberId = $("#memberId"+count).text();
	 var SaleId = $("#saleId"+count).text();
	 var ctype = $("#ctype"+count).text();
	
	 $('#deletepaymentreceipt tbody').empty();
	 
	 $("#customerName").html("Customer Name: " + $("#customer"+count).text());
	 $("#pAddress").html("Customer Address:"+ $("#caddress"+count).text());
	

	 $.ajax({
			url : '${pageContext.request.contextPath}/getPaymentsaleid?SaleId=' + SaleId+ "&ctype=" + ctype ,
			
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				
				$.each(data, function(i, data) {
				
					const paymentdate =(data.paymentdate);
					const dt = new Date(paymentdate);
			
					var rowCount = $('#deletepaymentreceipt tr').length ;
					var tr = $("<tr></tr>");

					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.ptype));
					tr.append($('<td></td>').html(data.pref));
					tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
					tr.append($('<td></td>').html(data.referenceno));
					tr.append($('<td></td>').html(dt));
					tr.append($('<td></td>').html(data.note));
					
					tr.append($('<td> <button type="button" onclick="deletePayment('+data.id +')" class="btn btn-primary">Delete Payment</button></td>'));
					tr.append($('<tr></tr>').html());
					
					$('#deletepaymentreceipt tbody').append(tr);
				});
				

			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
	
	
}


  function deletePayment(paymentid) {
	  
	  let conform = confirm("Are you sure you want to delete this payment?" );
	  if (conform) {
		
		  $.ajax({
				url : '${pageContext.request.contextPath}/deletePayment',
				type : "POST",
				dataType : "json",
				data:{id:paymentid},
				success : function(data) {		 				
					
					alert(data.msgDescr)
					location.reload();
				 
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});
	  }
  
  }



	/*	function printData2()
{
var divToPrint=document.getElementById("printTable2");
newWin= window.open("");
newWin.document.write(divToPrint.outerHTML);

	setTimeout(function () { // wait until all resources loaded 
	newWin.document.close(); // necessary for IE >= 10
	newWin.focus(); // necessary for IE >= 10
	newWin.print();  // change window to winPrint
	newWin.close();// change window to winPrint
 	}, 350);
return true;
} */

  function printData2() {
	    var headerContent = document.getElementById("pdfContent").innerHTML;
	    var tbodyRows = document.getElementById("tableBody").getElementsByTagName("tr");
	    var theadContent = document.querySelector("#tableBody").previousElementSibling.outerHTML;
	    var tbodyToPrint = document.getElementById("tableBody").innerHTML;
	    var totalTableToPrint = document.getElementById("fullWidthTable").outerHTML;
	    var notesToPrint = document.getElementById("notes").innerHTML;

	    var columnCount = document.querySelector("#tableBody tr:first-child").children.length;
	    var secondColumnWidth = 28; // Keeping the width of the second column
	    var thirdColumnWidth = secondColumnWidth / 3.7;
	    var firstColumnWidth = secondColumnWidth / 3; // Calculating the width of the first column based on the second column width
	    var remainingColumnWidth = (100 - secondColumnWidth - firstColumnWidth) / (columnCount - 2); // Calculating the width for the remaining columns

	    var newWin = window.open("");
	    newWin.document.write('<html><head><title>Print</title>');
	    newWin.document.write('<style>@page { margin: 0; }</style>'); // Adjust page size and margins for A3 paper
	    newWin.document.write('<style>');
	    newWin.document.write('body {margin: 1mm; padding: 0; box-sizing: border-box;font-size: 14px; }'); // Reset body margins, padding, and increase font size
	    newWin.document.write('.content-wrapper { font-size: 14px; }'); // Set margin for the content wrapper
	    newWin.document.write('.well well-sm { font-size: 14px; }'); 
	    newWin.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 0; font-size: 12px; table-layout: fixed;}'); // Ensure table fits within its container with fixed layout and increase font size
	    newWin.document.write('table, th, td { border: 1px solid black; padding: 1px; text-align: center; }');
	    newWin.document.write('th { background-color: #f2f2f2; }');
	    newWin.document.write('.hide-on-subsequent-pages { display: none; }'); // CSS to hide thead on subsequent pages

	    // Set width for columns dynamically
	    newWin.document.write('#printTable1 th:first-child, #printTable1 td:first-child, #printTable1 th:nth-child(3), #printTable1 td:nth-child(3), #printTable1 th:nth-child(4), #printTable1 td:nth-child(4), #printTable1 th:nth-child(5), #printTable1 td:nth-child(5) { width: ' + firstColumnWidth + '%; word-wrap: break-word; }'); // Set width for the 1st, 3rd, 4th, and 5th columns of the second table
	    newWin.document.write('#printTable1 th:nth-child(2), #printTable1 td:nth-child(2) { width: ' + secondColumnWidth + '%; word-wrap: break-word; }'); // Set width for the second column of the second table
	    newWin.document.write('#printTable1 th:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)), #printTable1 td:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)) { width: ' + remainingColumnWidth + '%; word-wrap: break-word; }'); // Set width for the remaining columns of the second table

	    newWin.document.write('</style>');
	    newWin.document.write('</head><body>');

	    // Write header content
	    newWin.document.write('<div class="well well-sm" style="font-size: 25px;margin-top:1px !important;">');
	    newWin.document.write(headerContent);
	    newWin.document.write('</div>');

	    // Write the content wrapper div
	    newWin.document.write('<div class="content-wrapper">');

	    // Write table with thead for the first page
	    newWin.document.write('<table id="printTable1">');
	    newWin.document.write('<thead>');
	    newWin.document.write(theadContent);
	    newWin.document.write('</thead>');
	    newWin.document.write('<tbody>');
	    newWin.document.write(tbodyToPrint);
	    newWin.document.write('</tbody>');
	    newWin.document.write('</table>');

	    // Close the content wrapper div
	    newWin.document.write('</div>');

	    newWin.document.write('<div class="content-wrapper">');
	    // Write totalTable content
	    newWin.document.write(totalTableToPrint);
	    newWin.document.write('</div>');

	    // Write notes content
	    newWin.document.write('<div id="notes">');
	    newWin.document.write(notesToPrint);
	    newWin.document.write('</div>');

	    // Hide thead on subsequent pages
	    newWin.document.querySelectorAll('.thead-first-page th').forEach(function(th) {
	        th.classList.add('hide-on-subsequent-pages');
	    });

	    newWin.document.write('</body></html>');

	    // Print the document
	    setTimeout(function () {
	        newWin.document.close();
	        newWin.focus();
	        newWin.print();
	        newWin.close();
	    }, 350);
	}


	function printData3() {
	    var headerContent = document.getElementById("pdfContent2").innerHTML;
	    var tbodyRows = document.getElementById("tableBody2").getElementsByTagName("tr");
	    var theadContent = document.querySelector("#tableBody2").previousElementSibling.outerHTML;
	    var tbodyToPrint = document.getElementById("tableBody2").innerHTML;
	    var totalTableToPrint = document.getElementById("fullWidthTable2").outerHTML;
	    var notesToPrint = document.getElementById("notes2").innerHTML;

	    var columnCount = document.querySelector("#tableBody2 tr:first-child").children.length;
	    var secondColumnWidth = 28; // Keeping the width of the second column
	    var firstColumnWidth = secondColumnWidth / 3; // Calculating the width of the first column based on the second column width
	    var thirdColumnWidth = secondColumnWidth / 3.7;
	    var remainingColumnWidth = (100 - secondColumnWidth - firstColumnWidth) / (columnCount - 2); // Calculating the width for the remaining columns

	    var newWin = window.open("");
	    newWin.document.write('<html><head><title>Print</title>');
	    newWin.document.write('<style>@page { size: auto; margin: 0; }</style>'); // Adjust page margins as needed
	    newWin.document.write('<style>');
	    newWin.document.write('body {margin: 1mm; padding: 0; box-sizing: border-box;font-size: 10px; }'); // Reset body margins and padding
	    newWin.document.write('.content-wrapper { font-size: 10px; }'); // Set margin for the content wrapper
	    newWin.document.write('.well well-sm { font-size: 10px; }'); 
	    newWin.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 0;font-size: 12px; table-layout: fixed;}'); // Ensure table fits within its container with fixed layout
	    newWin.document.write('table, th, td { border: 1px solid black; padding: 1px; text-align: center; }');
	    newWin.document.write('th { background-color: #f2f2f2; }');
	    newWin.document.write('.hide-on-subsequent-pages { display: none; }'); // CSS to hide thead on subsequent pages


	    newWin.document.write('#printTable2 th:first-child, #printTable2 td:first-child, #printTable2 th:nth-child(3), #printTable2 td:nth-child(3), #printTable2 th:nth-child(4), #printTable2 td:nth-child(4), #printTable2 th:nth-child(5), #printTable2 td:nth-child(5) { width: ' + firstColumnWidth + '%; word-wrap: break-word; }'); // Set width for the 1st, 3rd, 4th, and 5th columns of the second table
	    newWin.document.write('#printTable2 th:nth-child(2), #printTable2 td:nth-child(2) { width: ' + secondColumnWidth + '%; word-wrap: break-word; }'); // Set width for the second column of the second table
	    newWin.document.write('#printTable2 th:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)), #printTable2 td:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)) { width: ' + remainingColumnWidth + '%; word-wrap: break-word; }'); // Set width for the remaining columns of the second table

	    newWin.document.write('</style>');
	    newWin.document.write('</head><body>');

	    // Write header content
	    newWin.document.write('<div class="well well-sm" style="font-size: 25px; margin-top:1px;">');
	    newWin.document.write(headerContent);
	    newWin.document.write('</div>');

	    // Write the content wrapper div
	    newWin.document.write('<div class="content-wrapper">');

	    // Write table with thead for the first page
	    newWin.document.write('<table id="printTable2">');
	    newWin.document.write('<thead>');
	    newWin.document.write(theadContent);
	    newWin.document.write('</thead>');
	    newWin.document.write('<tbody>');
	    newWin.document.write(tbodyToPrint);
	    newWin.document.write('</tbody>');
	    newWin.document.write('</table>');

	    // Close the content wrapper div
	    newWin.document.write('</div>');

	    newWin.document.write('<div class="content-wrapper">');
	    // Write totalTable content
	    newWin.document.write(totalTableToPrint);
	    newWin.document.write('</div>');

	    // Write notes content
	    newWin.document.write('<div id="notes">');
	    newWin.document.write(notesToPrint);
	    newWin.document.write('</div>');

	    // Hide thead on subsequent pages
	    newWin.document.querySelectorAll('.thead-first-page th').forEach(function(th) {
	        th.classList.add('hide-on-subsequent-pages');
	    });

	    newWin.document.write('</body></html>');

	    // Print the document
	    setTimeout(function () {
	        newWin.document.close();
	        newWin.focus();
	        newWin.print();
	        newWin.close();
	    }, 350);
	}
		
	
	function searchSalesNew() {
	    clearTimeout(debounceTimer);
	    debounceTimer = setTimeout(() => {
	    	actualSearchSalesNewCall();
	    }, 1000);
	}


	function actualSearchSalesNewCall() {
	    const searchValue = document.getElementById('salesSearchInput').value; // Get the sales search value

	    // Ensure search is not empty to avoid unnecessary calls
	    if (searchValue.length === 0) {
	        console.warn("Search value is empty.");
	        // Reload the page if the search is empty
	        location.reload();
	        return;
	    }

	    console.log("Sales Search==", searchValue);

	    const url = '${pageContext.request.contextPath}/searchSalesNew'; // The endpoint for sales search

	    fetch(url, {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({ search: searchValue }),
	    })
	    .then(response => response.json()) // Expect JSON response
	    .then(data => {
	        console.log("Fetched sales data:", data); // Debugging log

	        // Inject the fetched sales rows into the table body
	        const tableBody = document.querySelector('#myTable tbody');
	        if (tableBody) {
	            tableBody.innerHTML = data.salesRows;
	        }

	        // Update the "Showing ${currentRecords} of ${totalRecords} entries" text
	        const showingText = document.querySelector('#salesShowingText');  // Assuming you have a span with this ID
	        if (showingText) {
	            showingText.textContent = 'Showing ' + data.totalRecords  + ' entries';
	        }

	        // Hide pagination after search
	        const paginationSection = document.querySelector('#salesPaginationSection');
	        if (paginationSection) {
	            paginationSection.style.display = 'none';  // Hide pagination
	        }
	    })
	    .catch(error => {
	        console.error('Error during sales search:', error);
	    });
	}

	// Ensure the search input for sales gets focus and cursor blinks once the page loads
	document.addEventListener('DOMContentLoaded', function() {
	    const salesSearchInput = document.getElementById('salesSearchInput');
	    if (salesSearchInput) {
	        salesSearchInput.focus(); // Focus on the input field
	        salesSearchInput.selectionStart = salesSearchInput.selectionEnd = salesSearchInput.value.length; // Ensure cursor is at the end
	    }
	});
	

	

</script>

</body>
</html>
