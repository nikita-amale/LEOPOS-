
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
@media print {
	.thead-first-page {
		display: table-header-group; /* Display on the first page */
	}
}

/* Use CSS Flexbox to control layout */
.table-container-right {
	text-align: right;
}

.print-flex {
	display: flex;
	flex-direction: column;
}

/* Add spacing between the lines */
.print-flex p {
	margin: 0;
	padding: 0;
	line-height: 1.5;
}

.btn-primary {
	font-size: 12px;
}

.btn {
	border-radius: 4px !important;
	padding: 0.375rem 0.5rem !important;
}

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
	transition: margin-left .3s ease-in-out, width .3s ease-in-out;
	width: 250px;
}

@media ( min-width : 768px) {
	body:not(.sidebar-mini-md) .content-wrapper, body:not(.sidebar-mini-md) .main-footer,
		body:not(.sidebar-mini-md) .main-header {
		transition: margin-left .3s ease-in-out;
		margin-left: 250px;
	}
}

@media ( min-width : 100px) {
	.d-sm-inline-block {
		display: inline-block !important;
	}
}

@media ( max-width : 767px) {
	.search-quote {
		max-width: 170px;
		right: 1rem;
	}
	.search-quote input {
		max-width: 130px;
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
							<h3 class="card-title open-sans">Quotes List</h3>
						</div>
						<div class="card-body">
							<div class="show-div">
								<label for="exampleInputEmail1">Show</label> <select id="show">
									<option
										href="${pageContext.request.contextPath}/getQuotesListpage?pageSize=10"
										value="10">10</option>
									<option
										href="${pageContext.request.contextPath}/getQuotesListpage?pageSize=25"
										value="25">25</option>
									<option
										href="${pageContext.request.contextPath}/getQuotesListpage?pageSize=50"
										value="50">50</option>
								</select>
								<div class="search-quote"
									style="position: absolute; right: 1rem;">
									<form action="${pageContext.request.contextPath}/searchQuote">
										<input type="text" placeholder="Searchquote" name="search">
										<button type="submit">
											<i class="fa fa-search"></i>
										</button>
									</form>
								</div>
							</div>
							<div class="form-group overflow-auto" style="min-height: 500px;">

								<table id="myTable"
									class="table table-bordered table-hover mb-3">
									<thead class="open-sans">
										<tr>
											<th>Serial No</th>
											<th>Date</th>
											<th hidden>quotesid</th>
											<th>Reference_no</th>
											<th>Customer</th>
											<sec:authorize access="hasAuthority('admin')">
												<th>Customer Type</th>
											</sec:authorize>
											<th hidden>Customer Type</th>
											<th>Grand_Total</th>
											<th hidden>tax</th>
											<th hidden>total</th>
											<th hidden>View Receipt</th>
											<sec:authorize access="hasAuthority('admin')">
											<th hidden>View Without MPN Receipt</th>
											</sec:authorize>
											<th></th>
											<!-- <th>View Receipt</th> -->
											<!-- <th>View Receipt</th>
											<th>Convert To Sale</th> -->
											<th hidden>Note</th>
											<th hidden>Edit</th>
											<th hidden>creadtedby</th>
											<th hidden>caddress</th>
											<th hidden>pinno</th>
											<th hidden>phoneno</th>
											<th hidden>Credit Payemnt</th>
											<th hidden>blocked</th>
											<th hidden>cash name </th>
											<th hidden>cash tin</th>


										</tr>
									</thead>
									<tbody>
										<c:forEach var="quotesPojo" items="${quotesPojo}"
											varStatus="loop">
											<c:set var="currentPage" value="${page}" />
											<c:set var="pageSize" value="${pageSize}" />
											<c:set var="serialNumber"
												value="${(currentPage) * pageSize + loop.index}" />
											<c:set var="quotesTotal" value="${quotesPojo.grandtotal}" />
											<c:set var="total_tax" value="${quotesPojo.total_tax}" />
											<c:set var="total" value="${quotesPojo.total}" />
											<tr id="${loop.count}">
												<td>${serialNumber+1}</td>
												<td id="quotesDate${loop.count}">${quotesPojo.date}</td>
												<td id="quoteId${loop.count}" hidden>${quotesPojo.quotesId}</td>
												<td id="referenceno${loop.count}">${quotesPojo.referenceno}</td>
												<td id="customer${loop.count}">${quotesPojo.member_name}</td>
												<sec:authorize access="hasAuthority('admin')">
													<td id="ctype${loop.count}">${quotesPojo.ctype}</td>
												</sec:authorize>
												<td hidden id="ctype${loop.count}">${quotesPojo.ctype}</td>
												<td id="quotesTotal${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${quotesTotal}" /></td>
												<td hidden id="totaltax${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${total_tax}" /></td>
												<td hidden id="total${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${total}" /></td>

												<td hidden>
													<!-- <button type="button" class="btn btn-primary"
														data-toggle="modal" data-target="#modal-lg"
														onclick="EditDetails(${loop.count})">View Receipt</button> -->
												</td>

												<!-----Drop down starts------>

												<td>
													<div class="dropdown d-flex justify-content-center">
														<button class="btn border-0 dropdown-toggle" type="button"
															id="dropdownMenuButton1" data-bs-toggle="dropdown"
															aria-expanded="false">
															<i class="fas fa-ellipsis-v"></i>
														</button>
														<ul class="dropdown-menu py-0"
															aria-labelledby="dropdownMenuButton1">
															<li>
																<button type="button" class="dropdown-item"
																	data-bs-toggle="modal" data-bs-target="#modal-lg"
																	onclick="ViewDetails(${loop.count})">View
																	Quotes</button> <!-- <a class="dropdown-item" href="#">View Quotes</a> -->
															</li>
															<sec:authorize access="hasAuthority('admin')">
															<li>
																<button type="button" class="dropdown-item"
																	data-bs-toggle="modal" data-bs-target="#modal-lg"
																	onclick="ViewDetailsMPN(${loop.count})">View
																	Without MPN Quotes</button> <!-- <a class="dropdown-item" href="#">View Quotes</a> -->
															</li>
															<li>
																<button type="button" class="dropdown-item"
																	data-bs-toggle="modal" data-bs-target="#exampleModal"
																	onclick="ViewDetails10(${loop.count})">View
																	Quotes 10%</button> <!-- <a class="dropdown-item" href="#">View Quotes 10%</a> -->
															</li>
															</sec:authorize>
															<li><c:if
																	test="${quotesPojo.quotes_status == 'Available'}">

																	<button type="button" class="dropdown-item" id="hide"
																		onclick="ConvertToSale(${loop.count})">
																		Convert To Sale</button>
																</c:if> <!-- <a class="dropdown-item" href="#">Convert to Sale</a> -->
															</li>

															<li hidden id="note${loop.count}">${quotesPojo.note}</li>
															<li>
																<sec:authorize
										access="hasAuthority('admin') || hasAuthority('cashier') || hasAuthority('sales')">
															<c:if
																	test="${quotesPojo.quotes_status == 'Available'}">

																	<a type="button" class="dropdown-item"
																		href="${pageContext.request.contextPath}/editQuotes?id=${quotesPojo.quotesId}">Edit Quotes
																		</a>
																		
																</c:if></sec:authorize> </li>
															<li hidden id="Createdby${loop.count}">${quotesPojo.createdBy}</li>
															<li hidden id="caddress${loop.count}">${quotesPojo.customeraddress}</li>
															<li hidden id="pincode${loop.count}">${quotesPojo.pincode}</li>
															<li hidden id="phonemain${loop.count}">${quotesPojo.phonemain}</li>

														</ul>
													</div>
												</td>

												<td id="creditpayment${loop.count}" hidden>${quotesPojo.creditpayment}</td>
												<td id="blocked${loop.count}" hidden>${quotesPojo.blocked}</td>
												<td hidden id="cashName${loop.count}">${quotesPojo.cashName}</td>
												<td hidden id="cashTin${loop.count}">${quotesPojo.cashTin}</td>



												<!-----Drop down ends------>


												<!-- <td> -->
												<!-- <a type="button" style="font-size: 12px;" class="btn btn-primary"
												  href="${pageContext.request.contextPath}/viewQuotesreceipt?id=${quotesPojo.quotesId}&ctype=${quotesPojo.ctype}" target="_blank">View Receipt</a> -->

												<!-- <button type="button" class="btn btn-primary d-block mx-auto w-100" data-bs-toggle="modal" data-bs-target="#modal-lg" onclick="ViewDetails(${loop.count})">
														View Quotes
													</button>	 -->
												<!-- </td> -->
												<!-- <td> -->
												<!-- <a type="button" style="font-size: 12px;" class="btn btn-primary"
											  href="${pageContext.request.contextPath}/quotesReceipt?id=${quotesPojo.quotesId}&ctype=${quotesPojo.ctype}" target="_blank">View Quotes 10%</a> -->

												<!-- <button type="button" class="btn btn-primary d-block mx-auto w-100" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="ViewDetails10(${loop.count})" >
												View Quotes 10%
											  </button> -->
												<!-- </td> -->
												<!-- <td>
													 <c:if test= "${quotesPojo.quotes_status == 'Available'}">
													 
													<button type="button" class="btn btn-primary d-block mx-auto w-100"  id="hide"
														onclick="ConvertToSale(${loop.count})">Convert To
														Sale</button>
													</c:if>
												</td> -->
												<!-- <td hidden id="note${loop.count}">${quotesPojo.note}</td> -->
												<!-- <td>
													<c:if test="${quotesPojo.quotes_status=='Available'}">
														 <a type="button"
														class="btn btn-primary d-block mx-auto w-100" href="${pageContext.request.contextPath}/editQuotes?id=${quotesPojo.quotesId}" >Edit</a>
														</c:if>
										</td> -->
												<!-- <td hidden  id="Createdby${loop.count}">${quotesPojo.createdBy}</td>
											
											<td hidden id="caddress${loop.count}">${quotesPojo.customeraddress}</td>
											<td hidden id="pincode${loop.count}">${quotesPojo.pincode}</td>
											<td hidden id="phonemain${loop.count}">${quotesPojo.phonemain}</td> -->
											</tr>
										</c:forEach>
									</tbody>

								</table>
								Showing ${currentRecords} of ${totalRecords} entries
							</div>
							<div class="bottom-right"
								style="position: absolute; bottom: 0; right: 0;">
								<table border="1" cellpadding="5" cellspacing="5">

									<c:set var="currentPage" value="${page}" />
									<c:set var="pageSize" value="${pageSize}" />

									<tr>
										<c:if test="${previous}">
											<td><a href="getQuotesListNew?page=${currentPage - 1}"
												style="color: black;">Previous</a></td>
										</c:if>
										<c:if test="${next}">
											<c:forEach begin="${currentPage}" end="${currentPage+4}"
												var="i">

												<c:choose>
													<c:when test="${currentPage == i}">
														<td style="background-color: blue;"><a
															href="getQuotesListNew?page=${i}" style="color: white;">${i}</a></td>
													</c:when>
													<c:otherwise>
														<td><a href="getQuotesListNew?page=${i}">${i}</a></td>
													</c:otherwise>

												</c:choose>


											</c:forEach>
										</c:if>



										<c:if test="${next}">
											<td><a href="getQuotesListNew?page=${currentPage +1}"
												style="color: black;">Next</a></td>
										</c:if>

									</tr>
								</table>
								<!---------->


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
<div class="modal fade" id="modal-lg" tabindex="-1"
	aria-labelledby="modal-lg" aria-hidden="true">
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
				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
    <i class="fa fa-print"></i> Print            </button> -->

				<!-- <a href="javascript:void(0);" onclick="printData()"
		class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
		class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a> -->

				<div id="printTable1">


					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>
					<div id="pdfContent2">

						<div class="logo-print">
							<img id="logoImage"
								src="${pageContext.request.contextPath}/resources/images/logo_s.png"
								width="280px" />
						</div>

						<br> <br>

						<div class="" style="justify-content: space-between;">

							<div id="headerDiv" class="row"
								style="justify-content: space-between; position: relative; width: 100%;">

								<div class="row"
									style="justify-content: space-between; font-size: 12px; width: 100%; margin-top: -30px;">
									<div class="col col-left">
										<!-- Left content goes here -->
										3754 Central American Blvd.<br> Belize City Belize<br>
										Tel: 501 207-0669<br> TIN # 128693
									</div>
									<div class="col col-right"
										style="float: right; text-align: right;">
										<!-- Right content goes here -->
										<span id="date"></span><br> <span id="qRefno"></span><br>
									</div>
								</div>

							</div>
						</div>
						<br>
						<div id="billToSection" class="excludeFromPdf">
							<p style="margin-bottom: 0 !important; font-size: 13px;">Bill
								To:</p>
							<p style="margin-bottom: 0; font-size: 12px;">

								Name: <label class="mb-0" id="cName"></label><br> Address:
								<label class="mb-0" id="cAddress"></label><br> <label
									class="mb-0" id="phonemain"></label><br> <label
									class="mb-0" id="pincode"></label><br>

							</p>

						</div>
					</div>
					<!--<p style="margin-bottom: 0; font-size: 14px;">Quotes Receipt</p>-->
					<br>
					<div class="table-responsive" style="font-size: 14px !important;">
						<span class="hidden-watermark"
							style="z-index: -1; font-size: 10rem; position: absolute; transform: rotate(-45deg); text-align: center; width: 100%; color: rgba(255, 99, 71, 0.1); vertical-align: middle;">Quotation</span>
						<table id="quotesReceipt"
							class="table table-bordered table-hover table-striped print-table order-table mb-0"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th style="text-align: center !important;">S.No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">QTY</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">U.O.M</th>
									<th style="text-align: center !important;">Subtotal</th>
									<th style="text-align: center !important;">T</th>
								</tr>

							</thead>

							<tbody id="tableBody" style="text-align: center !important;">

							</tbody>
							<table
								class="table table-bordered table-hover table-striped print-table order-table totalTable"
								id="fullWidthTable" width="100%" border="1"
								style="border-collapse: collapse !important;">
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="totalt"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Tax (GST 12.5%)</td>
									<td id="totaltaxt"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTott"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

							</table>
						</table>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right" id="notes">
							<div class="print-flex well well-sm">
								<p id="notee" style="font-size: 14px !important;">Note:</p>
								<br>
								<p
									style="font-size: 14px !important; margin-top: 0; margin-bottom: 10px;"
									id="Createdby"></p>
							</div>
						</div>
					</div>
				</div>


				<a href="javascript:void(0);" onclick="printData1()"
					class="no-print d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf">
					<i class="fa fa-print fa-sm text-white-50"></i> Print Invoice
				</a> <a href="javascript:void(0);" onclick="exportPDF1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf">
					<i class="fa fa-print fa-sm text-white-50"></i> Export to PDF
				</a>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- View receipt 10% starts -->

<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-modal="true" role="dialog">
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
				<div id="printTable">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>
					<div id="pdfContent">
						<div>
							<img id="logoImage1"
								src="${pageContext.request.contextPath}/resources/images/logo_s.png"
								width="270px" />
						</div>

						<br>

						<div class="" style="justify-content: space-between;">

							<div id="headerDiv" class="row"
								style="justify-content: space-between; position: relative; width: 100%;">

								<div class="row"
									style="justify-content: space-between; font-size: 12px; width: 100%; margin-bottom: 0px;">
									<div class="col col-left">
										<!-- Left content goes here -->
										3754 Central American Blvd.<br> Belize City Belize<br>
										Tel: 501 207-0669<br> TIN # 128693
									</div>
									<div class="col col-right"
										style="float: right; text-align: right;">
										<!-- Right content goes here -->
										<span id="dateten"></span><br> <span id="qRefnoten"></span>
									</div>
								</div>
							</div>

						</div>
						<br>
						<div id="billToSection1" class="excludeFromPdf">
							<p style="margin-bottom: 0 !important; font-size: 13px;">Bill
								To:</p>
							<p style="margin-bottom: 0; font-size: 12px;">

								Name: <label class="mb-0" id="cNameten"></label><br>
								Address: <label class="mb-0" id="cAddresss"></label><br> <label
									class="mb-0" id="phonemainn"></label><br> <label
									class="mb-0" id="pincodee"></label><br>

							</p>
						</div>
					</div>
					<!--<p style="margin-bottom: 0; font-size: 14px;">Quotes Receipt</p>-->
					<br>
					<div class="table-responsive" style="font-size: 14px !important;">
						<span class="hidden-watermarkk"
							style="z-index: -1; font-size: 10rem; position: absolute; color: rgba(255, 99, 71, 0.1); transform: rotate(-45deg); text-align: center; width: 100%; vertical-align: middle;">Quotation</span>


						<table id="quotesReceipt10"
							class="table table-bordered table-hover table-striped print-table order-table mb-0"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th style="text-align: center !important;">S.No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">QTY</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">U.O.M</th>
									<th astyle="text-align: center !important;">Subtotal</th>
									<th style="text-align: center !important;">T</th>
								</tr>

							</thead>

							<tbody id="tbody2" style="text-align: center !important;">

							</tbody>
							<table
								class="table table-bordered table-hover table-striped print-table order-table totalTable"
								id="fullWidthTable2" width="100%" border="1"
								style="border-collapse: collapse !important;">
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="totalten"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Tax (GST 12.5%)</td>
									<td id="totaltaxten"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTotten"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

							</table>
						</table>
					</div>


					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right" id="notes">
							<div class="print-flex well well-sm">
								<p id="noteten" style="font-size: 14px !important;">Note:</p>
								<br>
								<p
									style="font-size: 14px !important; margin-top: 0; margin-bottom: 10px;"
									id="Createdbyten"></p>
							</div>
						</div>
					</div>
				</div>



				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right excludeFromPdf"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>
			</div>
		</div>
	</div>
</div>

<!-- View receipt 10% ends -->
<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"
	integrity="sha512-qZvrmS2ekKPF2mSznTQsxqPgnpkI4DNTlrdUmTzrDgektczlKNRRhy5X5AAOnx5S09ydFYWWNSfcEqDTTHgtNA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/pdfmake.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.68/vfs_fonts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/pdf-lib@3.0.2/dist/pdf-lib.js"></script>



<script type="text/javascript">
 
    $(function () {
      $('#myTable').DataTable({      
        "paging": false,
        "pageLength": 20,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": true,
        "responsive": true,
        "scrollX": true,
      });
      //window.location.reload(true);
    });
    

	   document.getElementById('show').onchange = function() {
  window.location.href = this.children[this.selectedIndex].getAttribute('href');
}
   
    //10%function starts
    
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
  	  pdfDocGenerator.download('quotes' + Date.now() + '.pdf');
  	}



	function exportPDF() {
  	  // Find the logo image element
  	  var logoImage1 = document.getElementById('logoImage1');

  	  // Clone the content inside the printTable1 div
  	  var printTable1Clone = document.getElementById('printTable').cloneNode(true);

  	  // Remove the specific <div> element with the ID "headerDiv" from the cloned content
  	  var headerDiv = printTable1Clone.querySelector('#headerDiv');
  	  if (headerDiv) {
  	    headerDiv.remove();
  	  }
  	  var hiddenWatermark = printTable1Clone.querySelector('.hidden-watermarkk');
	    if (hiddenWatermark) {
	        hiddenWatermark.remove();
	    }
  	  // Get the date and quoteRefNo from the corresponding elements
  	  var date = document.getElementById('dateten').textContent.trim();
  	  var quoteRefNo = document.getElementById('qRefnoten').textContent.trim();

  	  // Update the placeholders with the correct values
  	  var datePlaceholder = printTable1Clone.querySelector('#datePlaceholder');
  	  if (datePlaceholder) {
  	    datePlaceholder.textContent = 'Date: ' + date;
  	  }

  	  var qRefnoPlaceholder = printTable1Clone.querySelector('#qRefnoPlaceholder');
  	  if (qRefnoPlaceholder) {
  	    qRefnoPlaceholder.textContent = 'Quote Reference No.: ' + quoteRefNo;
  	  }

  	  // Convert the cloned content to pdfMake format
  	  var pdfContent = convertContainerToPdfMake1(printTable1Clone);
  	  
	  // Create a transparent watermark image using HTML5 Canvas
	  var canvas = document.createElement('canvas');
	  var ctx = canvas.getContext('2d');
	  canvas.width = 900; // Adjust the canvas dimensions as needed
	  canvas.height = 700; // Adjust the canvas dimensions as needed
	  ctx.font = 'bold 120px Arial'; // Adjust font and size as needed
	  ctx.fillStyle = 'rgba(255, 99, 71, 0.1)'; // Watermark color with opacity

	  // Rotate the canvas by 45 degrees
	  ctx.translate(canvas.width / 2, canvas.height / 2); // Move the origin to the center
	  ctx.rotate(-Math.PI / 4);

	  // Add watermark text at the desired position after rotation
	  ctx.fillText('Quotation', -300, -180); // Adjust the position as needed

	  // Convert the canvas to a data URL
	  var watermarkDataURL = canvas.toDataURL();

	  // Construct watermark content with rotations
	  var watermarkContent = {
	    image: watermarkDataURL,
	    absolutePosition: { x: 150, y: 250 }, // Adjust position as needed (increased y value)
	    width: 700, // Adjust dimensions as needed
	    height: 700, // Adjust dimensions as needed
	  };


	  // Prepend the rotated watermark content to pdfContent
	  pdfContent.unshift(watermarkContent);
	    

  	  // Filter out any empty elements from pdfContent array
  	  pdfContent = pdfContent.filter(function (element) {
  	    return element !== undefined;
  	  });

  	  // Get the "Bill To" section from the form
  	  var billToSection = document.getElementById('billToSection1');
  	  var billToContent = addBillToSection1(billToSection);

  	  // Add the "Bill To" section manually to the pdfContent
  	  pdfContent.unshift(billToContent);
  	  

  	  // Add an empty space (as padding) after the second table's content
  	  var emptySpace = { text: '', margin: [0, 20, 0, 0] };
  	  pdfContent.push(emptySpace);
  	  
  	  // Convert the logo image to a data URL
  	  convertImageToPdfMake1(logoImage1)
  	    .then(function (pdfImage) {
  	      // Construct logo content
  	      var logoContent = [
  	        { image: pdfImage.image, width: 200, alignment: 'left' },
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
  	        { text: date, alignment: 'right'},
  	        { text: quoteRefNo, alignment: 'right' },
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
  		  var nameElement = billToSection.querySelector('#cNameten');
  		  var addressElement = billToSection.querySelector('#cAddresss');
  		  var telElement = billToSection.querySelector('#phonemainn'); // Update the ID to match the "Tel." element
  		  var tinElement = billToSection.querySelector('#pincodee');
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
  		    }else if (node.classList.contains('col') && node.classList.contains('pull-right')) {
	            return {
	                stack: [{ text: '', margin: [0, 10, 0, 5] }, convertContainerToPdfMake1(node)]
	            };
  		    }else {
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
  	  var isSecondTable = table.id === "fullWidthTable2";

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
  	            { text: cellText, style: style, alignment: alignment, margin: [0, 5, 0, 5] }
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


 // Inside the function that converts the logo image to PDFMake format
  	function convertImageToPdfMake1(img) {
  	  return new Promise(function (resolve, reject) {
  	    var canvas = document.createElement('canvas');
  	    var ctx = canvas.getContext('2d');

  	    img.crossOrigin = 'Anonymous';

  	    img.onload = function () {
  	      console.log('Original Image Width:', img.width); // Log the original width
  	      
  	      canvas.width = 200; // Set the desired width to 200
  	      console.log('Canvas Width:', canvas.width); // Log the canvas width
  	      
  	      canvas.height = img.height * (200 / img.width); // Maintain aspect ratio
  	      ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
  	      var dataURL = canvas.toDataURL('image/png');
  	      resolve({ image: dataURL, width: 200 }); // Set the width to 200
  	    };

  	    img.onerror = function () {
  	      reject(new Error('Failed to load image'));
  	    };

  	    img.src = img.src;
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

    
    //10% ends

    function generatePDF(pdfContent) {
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
    	  pdfDocGenerator.download('quotes' + Date.now() + '.pdf');
    	}



    	function exportPDF1() {
    	  // Find the logo image element
    	  var logoImage = document.getElementById('logoImage');

    	  // Clone the content inside the printTable1 div
    	  var printTable1Clone = document.getElementById('printTable1').cloneNode(true);

    	  // Remove the specific <div> element with the ID "headerDiv" from the cloned content
    	  var headerDiv = printTable1Clone.querySelector('#headerDiv');
    	  if (headerDiv) {
    	    headerDiv.remove();
    	  }
    	  var hiddenWatermark = printTable1Clone.querySelector('.hidden-watermark');
    	    if (hiddenWatermark) {
    	        hiddenWatermark.remove();
    	    }
    	  // Get the date and quoteRefNo from the corresponding elements
    	  var date = document.getElementById('date').textContent.trim();
    	  var quoteRefNo = document.getElementById('qRefno').textContent.trim();

    	  // Update the placeholders with the correct values
    	  var datePlaceholder = printTable1Clone.querySelector('#datePlaceholder');
    	  if (datePlaceholder) {
    	    datePlaceholder.textContent = 'Date: ' + date;
    	  }

    	  var qRefnoPlaceholder = printTable1Clone.querySelector('#qRefnoPlaceholder');
    	  if (qRefnoPlaceholder) {
    	    qRefnoPlaceholder.textContent = 'Quote Reference No.: ' + quoteRefNo;
    	  }

    	  // Convert the cloned content to pdfMake format
    	  var pdfContent = convertContainerToPdfMake(printTable1Clone);
    	  

    	  // Create a transparent watermark image using HTML5 Canvas
    	  var canvas = document.createElement('canvas');
    	  var ctx = canvas.getContext('2d');
    	  canvas.width = 900; // Adjust the canvas dimensions as needed
    	  canvas.height = 700; // Adjust the canvas dimensions as needed
    	  ctx.font = 'bold 120px Arial'; // Adjust font and size as needed
    	  ctx.fillStyle = 'rgba(255, 99, 71, 0.1)'; // Watermark color with opacity

    	  // Rotate the canvas by 45 degrees
    	  ctx.translate(canvas.width / 2, canvas.height / 2); // Move the origin to the center
    	  ctx.rotate(-Math.PI / 4);

    	  // Add watermark text at the desired position after rotation
    	  ctx.fillText('Quotation', -300, -180); // Adjust the position as needed

    	  // Convert the canvas to a data URL
    	  var watermarkDataURL = canvas.toDataURL();

    	  // Construct watermark content with rotations
    	  var watermarkContent = {
    	    image: watermarkDataURL,
    	    absolutePosition: { x: 150, y: 250 }, // Adjust position as needed (increased y value)
    	    width: 700, // Adjust dimensions as needed
    	    height: 700, // Adjust dimensions as needed
    	  };


    	  // Prepend the rotated watermark content to pdfContent
    	  pdfContent.unshift(watermarkContent);
    	    
    	  // Filter out any empty elements from pdfContent array
    	  pdfContent = pdfContent.filter(function (element) {
    	    return element !== undefined;
    	  });

    	  // Get the "Bill To" section from the form
    	  var billToSection = document.getElementById('billToSection');
    	  var billToContent = addBillToSection(billToSection);

    	  // Add the "Bill To" section manually to the pdfContent
    	  pdfContent.unshift(billToContent);

    	  // Add an empty space (as padding) after the second table's content
    	  var emptySpace = { text: '', margin: [0, 20, 0, 0] };
    	  pdfContent.push(emptySpace);
    	  
    	  // Convert the logo image to a data URL
    	  convertImageToPdfMake(logoImage)
    	    .then(function (pdfImage) {
    	      // Construct logo content
    	      var logoContent = [
    	        { image: pdfImage.image, width: 200, alignment: 'left' },
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
    	        { text: date, alignment: 'right'},
    	        { text: quoteRefNo, alignment: 'right' },
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
    	      generatePDF(pdfContent);
    	    })
    	    .catch(function (error) {
    	      console.error(error);
    	    });
    	}


    	function convertContainerToPdfMake(container) {
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

    		    var pdfElement = convertNodeToPdfMake(childNode);
    		    if (pdfElement) {
    		      pdfElements.push(pdfElement);
    		    }
    		  }

    		  return pdfElements;
    		}

    	function addBillToSection(billToSection) {
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

    	function convertNodeToPdfMake(node) {
    		  if (node.nodeType === Node.TEXT_NODE) {
    		    return { text: node.textContent.trim() };
    		  } else if (node.nodeType === Node.ELEMENT_NODE) {
    		    if (node.tagName.toLowerCase() === 'table') {
    		      return convertTableToPdfMake(node);
    		    } else if (node.tagName.toLowerCase() === 'img') {
    		      return convertImageToPdfMake(node);
    		    } else if (node.classList.contains('col-left')) {
    		      return convertColContentToPdfMake(node);
    		    } else if (node.classList.contains('excludeFromPdf')) {
    		      return undefined; // Skip this element from PDF generation
    		    } else if (node.classList.contains('col') && node.classList.contains('pull-right')) {
    	            return {
    	                stack: [{ text: '', margin: [0, 10, 0, 5] }, convertContainerToPdfMake1(node)]
    	            };
      		    }else {
    		      var pdfElement = { stack: [] };
    		      for (var i = 0; i < node.childNodes.length; i++) {
    		        var childNode = node.childNodes[i];
    		        var childContent = convertNodeToPdfMake(childNode);
    		        pdfElement.stack.push(childContent);
    		      }
    		      return pdfElement;
    		    }
    		  }
    		}

    	function convertColContentToPdfMake(colElement) {
    	  var pdfElement = { stack: [] };
    	  for (var i = 0; i < colElement.childNodes.length; i++) {
    	    var childNode = colElement.childNodes[i];
    	    var childContent = convertNodeToPdfMake(childNode);
    	    pdfElement.stack.push(childContent);
    	  }
    	  return pdfElement;
    	}

    	function convertTableToPdfMake(table) {
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
    		          convertImageToPdfMake(imgElement)
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

    	function convertImageToPdfMake(img) {
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

    	function convertImageToDataURL(url) {
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


    function EditDetails(count){
  	  var sTotal = $("#quotesTotal"+count).text();
  	  var quoteId = $("#quoteId"+count).text();
  		
  		 $("#quotesReceipt  tbody").empty();
  		 $("#tax").html($("#tax"+count).text());
  		 $("#totaltax").html($("#totaltax"+count).text());
  		 $("#total").html($("#total"+count).text());

  		 $("#balTot").html($("#quotesTotal"+count).text());
  		 $("#quotesTotal").html($("#quotesTotal"+count).text());
  		 $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
  		 $("#notee").html("Note: " + $("#note"+count).text());
  		 $("#cName").html($("#customer"+count).text());
  		 $("#date").html("Date :" + $("#quotesDate"+count).text());
  		 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
  		 
  		 
		
  		 $.ajax({
  				url : '${pageContext.request.contextPath}/getQuoteitembyquoteId?quoteId=' + quoteId,
  				type : "GET",
  				dataType : "json",
  				success : function(data) {
  					var ajaxCallData = JSON.stringify(data);
  					
  					$.each(data, function(i, data) {
  						var real_unit_price = data.real_unit_price;
						var subtotal =  data.subtotal;
						if(!isNaN(real_unit_price)){
							real_unit_price = parseFloat(real_unit_price).toFixed(2); 
						  }
						if(!isNaN(subtotal)){
							subtotal = parseFloat(subtotal).toFixed(2); 
						  }
						
						var rowCount = $('#quotesReceipt tr').length ;
  						
  						var tr = $('<tr></tr>');
  						tr.append($('<td></td>').html(data.id));
  						tr.append($('<td></td>').html(data.product_name));
  						tr.append($('<td></td>').html(data.quantity));
  						tr.append($('<td></td>').html(real_unit_price));
  						tr.append($('<td></td>').html(data.roll));
  						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
  						tr.append($('<tr></tr>').html());
  						$('#quotesReceipt tbody').append(tr);
  					});

  				},
  				error : function(error) {
  					console.log(`Error ${error}`);
  				}
  			});
  		 }
    
    // View Normal Receipt
    //==========================
    function ViewDetails(count){
		 // var sTotal = $("#quotesTotal"+count).text();
	  	  var quoteId = $("#quoteId"+count).text();
	  	  var ctype = $("#ctype"+count).text();
	  	
	  	  $("#quotesReceipt  tbody").empty();
	  	  $("#tax").html($("#tax"+count).text());
 		  $("#totaltax").html($("#totaltax"+count).text());
 		  $("#total").html($("#total"+count).text());
	  		
    	  $("#balTot").html($("#quotesTotal"+count).text());
	  	  $("#quotesTotal").html($("#quotesTotal"+count).text());
	  	  $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
	  	  $("#notee").html("Note: " + $("#note"+count).text());
	  	  $("#cName").html($("#customer"+count).text());
	  	 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
	  	 $("#date").html("Date :" + $("#quotesDate"+count).text());
	  	 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
	  	 var cname = $("#customer"+count).text();
	  	 
	     //  Check using customer name
	     if(cname === "cash customer"){

	         var cashName = $("#cashName"+count).text();
	         var cashTin  = $("#cashTin"+count).text();

	         $("#cName").html(cashName);
	         $("#pincode").html("TIN# " + cashTin);

	     } 
	  	 
	    	let quoteSubtotal = 0;
			let quoteGrandTotal = 0 ;
			let quoteTotalTax = 0;
	  //	 alert(cname);
	  	 
	  	 $.ajax({
	  			url : '${pageContext.request.contextPath}/getQuoteitembyquoteId?quoteId=' + quoteId,
	  			type : "GET",
	  			dataType : "json",
	  			success : function(data) {
	  				var ajaxCallData = JSON.stringify(data);
	  					
	  				$.each(data, function(i, data) {
	  					
	  					 quoteSubtotal = data.quoteSubtotal ;
	 	  				 quoteGrandTotal = data.quoteGrandTotal ;
	 	  				 quoteTotalTax = data.quoteTotalTax ;
	  		   
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
						
						
						var rowCount = $('#quotesReceipt tr').length;																
						var tr = $("<tr></tr>");
						
						//var product_name = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<td></td>').html('T'));
						
						tr.append($('<tr></tr>').html());
	  					
	  						$('#quotesReceipt tbody').append(tr);
	  					});
	  				//Calculate(cname);
	  			
 	  			   $("#totalt").html(quoteSubtotal.toFixed(2));
	  		       $("#totaltaxt").html(quoteTotalTax.toFixed(2));
	  		       $("#balTott").html(quoteGrandTotal.toFixed(2));

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		
	  }
    // View without MPN
    
    function ViewDetailsMPN(count){
		 // var sTotal = $("#quotesTotal"+count).text();
	  	  var quoteId = $("#quoteId"+count).text();
	  	  var ctype = $("#ctype"+count).text();
	  	
	  	  $("#quotesReceipt  tbody").empty();
	  	  $("#tax").html($("#tax"+count).text());
 		  $("#totaltax").html($("#totaltax"+count).text());
 		  $("#total").html($("#total"+count).text());
	  		
    	  $("#balTot").html($("#quotesTotal"+count).text());
	  	  $("#quotesTotal").html($("#quotesTotal"+count).text());
	  	  $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
	  	  $("#notee").html("Note: " + $("#note"+count).text());
	  	  $("#cName").html($("#customer"+count).text());
	  	 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
	  	 $("#date").html("Date :" + $("#quotesDate"+count).text());
	  	 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
	  	 var cname = $("#customer"+count).text();
	  	 
	  	 
	    	let quoteSubtotal = 0;
			let quoteGrandTotal = 0 ;
			let quoteTotalTax = 0;
	  //	 alert(cname);
	  	 
	  	 $.ajax({
	  			url : '${pageContext.request.contextPath}/getQuoteitembyquoteId?quoteId=' + quoteId,
	  			type : "GET",
	  			dataType : "json",
	  			success : function(data) {
	  				var ajaxCallData = JSON.stringify(data);
	  					
	  				$.each(data, function(i, data) {
	  					
	  					 quoteSubtotal = data.quoteSubtotal ;
	 	  				 quoteGrandTotal = data.quoteGrandTotal ;
	 	  				 quoteTotalTax = data.quoteTotalTax ;
	  		   
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
						if(unitname == "Box12"){
							unitname = "Box";
						  }
						if(unitname == "Wire Roll 328 Ft"){
							unitname = "Roll 328'";
						  }
						if(unitname == "Box 25lb" ){
							unitname = "Box";
						  }
						var rowCount = $('#quotesReceipt tr').length;																
						var tr = $("<tr></tr>");
						
						var product_name = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<td></td>').html('T'));
						
						tr.append($('<tr></tr>').html());
	  					
	  						$('#quotesReceipt tbody').append(tr);
	  					});
	  				//Calculate(cname);
	  			
 	  			   $("#totalt").html(quoteSubtotal.toFixed(2));
	  		       $("#totaltaxt").html(quoteTotalTax.toFixed(2));
	  		       $("#balTott").html(quoteGrandTotal.toFixed(2));

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		
	  }
    
    
    // View 10% Addition receipt
    //===========================
 	
    function ViewDetails10(count){
		 // var sTotal = $("#quotesTotal"+count).text();
	  	  var quoteId = $("#quoteId"+count).text();
	  	  var ctype = $("#ctype"+count).text();
	  	//  alert(quoteId);
	  	
	  	  $("#quotesReceipt10  tbody").empty();
	  	 
   	 
	  	  $("#qRefnoten").html("Quote Reference No. " + $("#referenceno"+count).text());
	  	  $("#noteten").html("Note: " + $("#note"+count).text());
	  	  $("#cNameten").html($("#customer"+count).text());
	  	 $("#dateten").html("Date :" + $("#quotesDate"+count).text());
	  	 $("#cAddresss").html( $("#caddress"+count).text());
		 $("#pincodee").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemainn").html("Tel. " + $("#phonemain"+count).text());
		 $("#Createdbyten").html("CreatedBy :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
		 var cname = $("#customer"+count).text();
	  //	  alert("reaching here 10%");
	  //	  alert("Date :" + $("#quotesDate"+count).text());
		//	 
	  	 $.ajax({
	  			url : '${pageContext.request.contextPath}/getQuoteitembyquoteIdTen?quoteId=' + quoteId + "&ctype=" + ctype,
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
						if(unitname == "Box12"){
							unitname = "Box";
						  }
						if(unitname == "Wire Roll 328 Ft"){
							unitname = "Roll 328'";
						  }
						if(unitname == "Box 25lb" ){
							unitname = "Box";
						  }
						var rowCount = $('#quotesReceipt10 tr').length ;		
																						
						var tr = $("<tr></tr>");
						
						//var product_name = data.product_name.split(' - ')[0];

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<td></td>').html('T'));
						
						tr.append($('<tr></tr>').html());
						
	  					
	  						$('#quotesReceipt10 tbody').append(tr);
	  						
	  					});
	  				 CalculateTotal(cname);  
	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  	
	  		
	  }
    
    function CalculateTotal(cname)
    {
      var grandT = 0;
      var taxT = 0;
      var totalT = 0;
      var origtotalT = 0;
      $("#quotesReceipt10 > tbody > tr").each(function() {
       var t5 = $(this).find("td:eq(5)").text();
       if (!isNaN(t5)) {
        grandT += parseFloat(t5);
        origtotalT += parseFloat(t5);
       }
      });

       taxT = grandT * .125;
       taxT=+(Math.round(taxT + "e+2")  + "e-2");
       totalT = grandT + taxT;
       totalT=  Math.round(totalT * 100) / 100;
       if(cname == "Def. Infra. Org. Oper. Training")
	   {
	   //  alert(cname);
	     taxT =0;
	     totalT = origtotalT;
	   }
       $("#totalten").html(grandT.toFixed(2));
       $("#totaltaxten").html(taxT.toFixed(2));
       $("#balTotten").html(totalT.toFixed(2));
    }
    function Calculate(cname)
    {
      var grandT = 0;
      var taxT = 0;
      var totalT = 0;
      var origtotalT = 0;
      $("#quotesReceipt > tbody > tr").each(function() {
       var t5 = $(this).find("td:eq(5)").text();
       if (!isNaN(t5)) {
        grandT += parseFloat(t5);
        origtotalT += parseFloat(t5);
       }
      });
       taxT = grandT * .125;
       taxT=+(Math.round(taxT + "e+2")  + "e-2");
       totalT = grandT + taxT;
       totalT=  Math.round(totalT * 100) / 100;
       if(cname == "Def. Infra. Org. Oper. Training")
    	   {
    	   //  alert(cname);
    	     taxT =0;
    	     totalT = origtotalT;
    	   }
       $("#totalt").html(grandT.toFixed(2));
       $("#totaltaxt").html(taxT.toFixed(2));
       $("#balTott").html(totalT.toFixed(2));
    }
    
	
	function computeTableColumnTotal()
	{
	  // find the table with id attribute tableId
	  // return the total of the numerical elements in column colNumber
	  // skip the top row (headers) and bottom row (where the total will go)
			
	  var result = 0;
			
	  try
	  {
	    var tableElem = window.document.getElementById("#quotesReceipt10"); 		   
	    var tableBody = tableElem.getElementsByTagName("tbody").item(0);
	    var i;
	    var howManyRows = tableBody.rows.length;
	    for (i=1; i<(howManyRows-1); i++) // skip first and last row (hence i=1, and howManyRows-1)
	    {
	       var thisTrElem = tableBody.rows[i];
	       var thisTdElem = thisTrElem.cells[5];			
	       var thisTextNode = thisTdElem.childNodes.item(0);
	       if (debugScript)
	       {
	     //     alert("text is " + thisTextNode.data);
	       } // end if

	       // try to convert text to numeric
	       var thisNumber = parseFloat(thisTextNode.data);
	       // if you didn't get back the value NaN (i.e. not a number), add into result
	       if (!isNaN(thisNumber))
	         result += thisNumber;
		 } // end for
			 
	  } // end try
	  catch (ex)
	  {
	     window.alert("Exception in function computeTableColumnTotal()\n" + ex);
	     result = 0;
	  }
	  finally
	  {
	     return result;
	  }
		
	}
    
    
    function ConvertToSale(count){
  	  
		
		 var quoteId = $("#quoteId"+count).text();
		 var quoteData = {
		            "quoteId" : quoteId
		        }
		 
		 var creditpayment =$("#creditpayment"+count).text();
		 var blocked =$("#blocked"+count).text();
		  <sec:authorize access="hasAuthority('sales')">
		  if(blocked==1){
			 alert("Customer is blocked.Quote cannot be converted to sale");
			  
			  window.location.href = "${pageContext.request.contextPath}/viewQuotesNew";
			  return;
		  }
		  </sec:authorize>
        
		  /*
		        $.ajax({
		            type: "POST",
		            url: "${pageContext.request.contextPath}/converquotestToSale?" + $.param(quoteData),
		            dataType : 'json',
		            contentType: 'application/json'  
	
		        });
		        
		       if(creditpayment=="0.0"){
		        alert("Quote converted to Sale Sucessfully !");
		        }else{
		        	 alert("Customer has a creditamount.Payment will be done by creditamount.Quote converted to Sale Sucessfully !");
		        }
		        
		      
				 
			window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
			
			*/
			
		    $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/converquotestToSale?" + $.param(quoteData),
	            dataType : 'json',	         
	            success: function(response) {
	                if (response.isError) {
	                    alert(response.msgDescr); // backend error message
	                } else {
	                    if (creditpayment === "0.0") {
	                        alert("Quote converted to Sale Successfully!");
	                    } else {
	                        alert("Customer has a credit amount. Payment will be done by credit amount. Quote converted to Sale Successfully!");
	                    }
	                }
	        		window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
	            },
	            error: function(xhr, status, error) {
	                alert(" Something went wrong: " + error);
	            }

	        });
			
		//	document.location.reload(true);      
 	
    }

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



  function printData() {
    var headerContent = document.getElementById("pdfContent").innerHTML;
    var tbodyRows = document.getElementById("tableBody").getElementsByTagName("tr");
    var theadContent = document.getElementById("quotesReceipt10").getElementsByTagName("thead")[0].outerHTML;
    var tbodyToPrint = document.getElementById("tbody2").innerHTML;
    var totalTableToPrint = document.getElementById("fullWidthTable2").outerHTML;
    var notesToPrint = document.getElementById("notes").innerHTML;

    var columnCount = document.querySelector("#tbody2 tr:first-child").children.length;
    var secondColumnWidth = 28; // Keeping the width of the second column
    var thirdColumnWidth = secondColumnWidth / 3.7;
    var firstColumnWidth = secondColumnWidth / 3; // Calculating the width of the first column based on the second column width
    var remainingColumnWidth = (100 - secondColumnWidth - firstColumnWidth) / (columnCount - 2); // Calculating the width for the remaining columns

    var newWin = window.open("");
    newWin.document.write('<html><head><title>Print</title>');
    newWin.document.write('<style>@page { size: auto; margin: 0; }</style>'); // Adjust page margins as needed
    newWin.document.write('<style>');
    newWin.document.write('body { margin: 1mm; padding: 0; box-sizing: border-box; }'); // Reset body margins and padding
    newWin.document.write('.content-wrapper {;font-size: 12px; }');
    newWin.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 0; font-size: 12px; table-layout: fixed; }'); // Ensure table fits within its container
    newWin.document.write('table, th, td { border: 1px solid black; padding: 1px; text-align: center; }');
    newWin.document.write('th { background-color: #f2f2f2; }');
    newWin.document.write('.hide-on-subsequent-pages { display: none; }'); // CSS to hide thead on subsequent pages
    // Set width for the columns
    // Set width for columns dynamically
    newWin.document.write('#printTable1 th:first-child, #printTable1 td:first-child, #printTable1 th:nth-child(3), #printTable1 td:nth-child(3), #printTable1 th:nth-child(4), #printTable1 td:nth-child(4), #printTable1 th:nth-child(5), #printTable1 td:nth-child(5) { width: ' + firstColumnWidth + '%; word-wrap: break-word; }'); // Set width for the 1st, 3rd, 4th, and 5th columns of the second table
    newWin.document.write('#printTable1 th:nth-child(2), #printTable1 td:nth-child(2) { width: ' + secondColumnWidth + '%; word-wrap: break-word; }'); // Set width for the second column of the second table
    newWin.document.write('#printTable1 th:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)), #printTable1 td:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)) { width: ' + remainingColumnWidth + '%; word-wrap: break-word; }'); // Set width for the remaining columns of the second table
   
    newWin.document.write('</style>');
    newWin.document.write('</head><body>');

    // Write header content
    newWin.document.write('<div class="well well-sm">');
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





function printData1() {
    var headerContent = document.getElementById("pdfContent2").innerHTML;
    var tbodyRows = document.getElementById("tableBody").getElementsByTagName("tr");
    var theadContent = document.querySelector("#tableBody").previousElementSibling.outerHTML;
    var tbodyToPrint = document.getElementById("tableBody").innerHTML;
    var totalTableToPrint = document.getElementById("fullWidthTable").outerHTML;
    var notesToPrint = document.getElementById("notes").innerHTML;

    var columnCount = document.querySelector("#tableBody tr:first-child").children.length;
    var secondColumnWidth = 28; // Keeping the width of the second column
    var firstColumnWidth = secondColumnWidth / 3; // Calculating the width of the first column based on the second column width
    var thirdColumnWidth = secondColumnWidth / 3.7;
    var remainingColumnWidth = (100 - secondColumnWidth - firstColumnWidth) / (columnCount - 2); // Calculating the width for the remaining columns

    var newWin = window.open("");
    newWin.document.write('<html><head><title>Print</title>');
    newWin.document.write('<style>@page { size: auto; margin: 0; }</style>');
    newWin.document.write('<style>');
    newWin.document.write('body {margin: 1mm; padding: 0; box-sizing: border-box;font-size: 10px; }'); 
    newWin.document.write('.content-wrapper { font-size: 10px; }'); 
    newWin.document.write('.well well-sm { font-size: 10px; }'); 
    newWin.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 0;font-size: 12px; table-layout: fixed;}');
    newWin.document.write('table, th, td { border: 1px solid black; padding: 1px; text-align: center; }');
    newWin.document.write('th { background-color: #f2f2f2; }');
    newWin.document.write('.hide-on-subsequent-pages { display: none; }');

    // Set max-width for each column dynamically
    newWin.document.write('#printTable2 th:first-child, #printTable2 td:first-child, #printTable2 th:nth-child(3), #printTable2 td:nth-child(3), #printTable2 th:nth-child(4), #printTable2 td:nth-child(4), #printTable2 th:nth-child(5), #printTable2 td:nth-child(5) { width: ' + firstColumnWidth + '%; word-wrap: break-word; }'); // Set width for the 1st, 3rd, 4th, and 5th columns of the second table
    newWin.document.write('#printTable2 th:nth-child(2), #printTable2 td:nth-child(2) { width: ' + secondColumnWidth + '%; word-wrap: break-word; }'); // Set width for the second column of the second table
    newWin.document.write('#printTable2 th:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)), #printTable2 td:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)) { width: ' + remainingColumnWidth + '%; word-wrap: break-word; }'); // Set width for the remaining columns of the second table

    newWin.document.write('</style>');
    newWin.document.write('</head><body>');

    // Write header content
    newWin.document.write('<div class="well well-sm" style="font-size: 25px">');
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


</script>

</body>
</html>
