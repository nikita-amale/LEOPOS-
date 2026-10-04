
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
							<h3 class="card-title">Sales Details</h3>
						</div>

					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
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
									<th>View Receipt</th>
									<th>View Sales Receipt</th>
									<sec:authorize access="hasAuthority('admin') || hasAuthority('cashier')">
									<th>Add Payment</th>
									</sec:authorize>
									<th>View Payment</th>
									<th>Edit</th>
									<sec:authorize access="hasAuthority('admin')">
										<th>Delete</th>
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

									<c:set var="balance"
										value="${salesPojo.grand_total - salesPojo.paid}" />
									<c:set var="paid" value="${salesPojo.paid}" />
									<c:set var="totaltax" value="${salesPojo.total_tax}" />
									<c:set var="grand_total" value="${salesPojo.grand_total}" />
									<c:set var="total" value="${salesPojo.total}" />
									<tr id="${loop.count}">
										<td>${loop.count}</td>
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

										<td>
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal" data-bs-target="#viewsales"
												onclick="ViewDetails(${loop.count})">View Sales</button>
										</td>
										<td>
											<!-- <a type="button" style="font-size: 12px;" class="btn btn-primary"
									          href="${pageContext.request.contextPath}/salesReceipt?id=${salesPojo.saleId}&ctype=${salesPojo.ctype}" target="_blank">View Sales 10%</a> -->
											<button type="button" class="btn btn-primary"
												data-bs-toggle="modal" data-bs-target="#viewsales10"
												onclick="ViewDetails10(${loop.count})">View Sales
												10%</button>
										</td>
										<sec:authorize access="hasAuthority('admin') || hasAuthority('cashier')">
									
										<td>
										<c:if test="${salesPojo.isActive == '0'}"><c:if
												test="${salesPojo.grand_total - salesPojo.paid > '0'}">
												<button
													style="font-size: 12px; padding: 0.375rem 0.5rem !important;"
													type="button" class="btn btn-primary" data-toggle="modal"
													onclick="loadPayment(${loop.count})"
													data-target="#modal-payment">Add Payment</button>
											</c:if>
											</c:if>
											
											
											</td>
											</sec:authorize>
											
											
										<td>
											<button type="button" style="font-size: 14px;"
												class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="ViewPayment(${loop.count})">View</button>
										</td>
										
										<td><c:if test="${salesPojo.isActive == '0'}">
										<c:if test="${salesPojo.paid <= '0'}">
												<a type="button" style="font-size: 12px;"
													class="btn btn-primary"
													href="${pageContext.request.contextPath}/editSales?id=${salesPojo.saleId}&ctype=${salesPojo.ctype}">Edit</a>
											</c:if>
											</c:if>
											</td>
										<sec:authorize access="hasAuthority('admin')">
											<td><c:if test="${salesPojo.isActive == '0'}">
												<button type="button" class="btn btn-primary"
													onclick="deleteDetails(${loop.count},${salesPojo.saleId})">Delete</button>
													</c:if>
											</td>
											
										
										</sec:authorize>
											
										<td hidden id="Createdby${loop.count}">${salesPojo.createdBy}</td>

										<td hidden id="note${loop.count}">${salesPojo.note}</td>
										<td hidden id="po${loop.count}">${salesPojo.purchaseorder}</td>

										<td hidden id="tax${loop.count}">${salesPojo.total_tax}</td>
										<td hidden id="caddress${loop.count}">${salesPojo.customeraddress}</td>
										<td hidden id="pincode${loop.count}">${salesPojo.pincode}</td>
										<td hidden id="phonemain${loop.count}">${salesPojo.phonemain}</td>
										
								</c:forEach>


							</tbody>

						</table>
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
									<td>TIN # 128693</td>
									<td align="right" id="po"></td>
								</tr>

								<tr>
									<td></td>
									<td align="right">Sales Person</td>
								</tr>

							</table>
						</div>
					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						Name: <label class="mb-0" id="cName"></label><br> Address: <label
							class="mb-0" id="cAddress"></label><br> <label class="mb-0"
							id="phonemain"></label><br> <label class="mb-0" id="pincode"></label><br>

					</p>
					<br>
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
							<table width="100%" style="font-size: 14px;">
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right">Tax Invoice</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date10"></td>
								</tr>
								<tr>
									<td>Tel: 501 207-0669</td>
									<td align="right" id="sRefno10"></td>
								</tr>
								<tr>
									<td>TIN # 128693</td>
									<td align="right" id="poten"></td>
								</tr>
								<tr>
									<td></td>
									<td align="right">Sales Person</td>
								</tr>

							</table>
						</div>
					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						Name: <label class="mb-0" id="cNamee"></label><br> Address: <label
							class="mb-0" id="cAddresss"></label><br> <label class="mb-0"
							id="pincodee"></label><br> <label class="mb-0"
							id="phonemainn"></label><br>
					</p>


					<!--<p style="margin-bottom: 0; font-size: 14px;">Sales Receipt</p>-->
					<br>
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="salesReceipt10"
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

							<tbody style="text-align: center !important;">

							</tbody>
						</table>
						<table
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total
									Amount (BZD)</td>
								<td id="totalten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total
									Tax</td>
								<td id="totaltaxten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total
									Paid</td>
								<td id="totalPaidten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Grand
									Total (BZD)</td>
								<td id="balTotten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
						</table>
					</div>


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
								<p style="font-size: 14px !important;">
									<strong>Invoice must be presented when making a
										return, 10% Restocking Fee will be charged on items returned
										after 7 days</strong>
								</p>

							</div>
						</div>

					</div>


				</div>

				<a href="javascript:void(0);" onclick="printData3()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> <a
					href="javascript:void(0);" onclick="exportPDF2()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
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
						<option val="other">Other</option>
					</select>
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
			<!-- <div class="modal-footer">
<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary">Save changes</button>
</div> -->
		</div>
	</div>
</div>
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

$(function () {
    $('#myTable').DataTable({      
      "paging": true,
      "pageLength": 20,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false,
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





  function ViewDetails(count){
	  var sTotal = $("#saleTotal"+count).text();
	  var saleId = $("#saleId"+count).text();
	  var ctype = $("#ctype"+count).text();
	  var paid = $("#salePaid"+count).text();
	  var tax =  $("#tax"+count).text();
	  var memberId = $("#memberId"+count).text();
	
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
						var rowCount = $('#salesReceipt tr').length ;
						var tr = $("<tr></tr>");

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt tbody').append(tr);
					});
					CalculateTotal(paid,tax);
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
					
					var rowCount = $('#salesReceipt tr').length;
					var tr = $('<tr></tr>');
					
					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.product_name));
					tr.append($('<td></td>').html(data.quantity));
					tr.append($('<td></td>').html(real_unit_price));
					tr.append($('<td></td>').html(unitname));
					tr.append($('<td style="text-align: right;"></td>').html(subtotal));
					tr.append($('<tr></tr>').html());
					
					$('#salesReceipt tbody').append(tr);
				});
				CalculateTotal(paid,tax);

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
						
						var rowCount = $('#salesReceipt10 tr').length ;
						var tr = $("<tr></tr>");

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt10 tbody').append(tr);
					});
					Calculate(paid,tax);

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
					if(unitname == "Box12"){
						unitname = "Box";
					  }
					var rowCount = $('#salesReceipt10 tr').length;
					var tr = $('<tr></tr>');
					
					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.product_name));
					tr.append($('<td></td>').html(data.quantity));
					tr.append($('<td></td>').html(real_unit_price));
					tr.append($('<td></td>').html(unitname));
					tr.append($('<td style="text-align: right;"></td>').html(subtotal));
					tr.append($('<tr></tr>').html());
					
					$('#salesReceipt10 tbody').append(tr);
				});
				Calculate(paid,tax);

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		
		}
		 
		 
	}
  
  function Calculate(paid,tax)
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
    if(tax==0) {
    	taxT=0;
    }else {
     taxT = grandT * .125;
     taxT=Math.round(taxT * 100) / 100;
    }
    grandT= grandT.toFixed(2);
     totalT = Number(grandT)+taxT;
     totalT=  Math.round(totalT * 100) / 100;
     $("#totalten").html(grandT);
     $("#totaltaxten").html(taxT.toFixed(2));
     $("#balTotten").html(totalT.toFixed(2));
     $("#totalPaidten").html(paid);
  }
  
  function CalculateTotal(paid,tax)
  {
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
    if(tax==0) {
    	taxT = 0;
    }else {
     taxT = grandT * .125;
     taxT=Math.round(taxT * 100) / 100;
    }
    
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
	 
	  
	  
	//  alert("Sale Balance: " + SaleBalance + "....... Sale ID: " + SaleId + "............MemberId:"+ memberId) ;
	  $("#amount").val(saleTotal);
	  $("#hidsaleid").val(SaleId);
	  $("#hidmemberId").val(memberId);
	  $("#paidamount").val(SalePaid);
	  $("#ctype").val(ctype);
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
		
		 var amt=amount-paid;
		
		
	 
		// alert(ptype);
		 var flag =0;
		 if(amounttopay =="") {
			 alert("The Amount cannot be blank  !");
			  flag =1;
		 }
		 
	      if (ptype!="Cash"&& amounttopay > amt )
			{
			  alert("The Amount cannot greater than the pending amount "
			  		+ "Please use BulkPayment!");
			  flag =1;
			  
			}
		 
		 if (ptype == "")
			{
			  alert("The Payment type must be selected !");
			  flag =1;
			}
		 
		 if (pref == "" && ptype!="Cash" && ptype!="Credit Card" && ptype!="Online" && ptype!="Other")
			{
			  alert("The Payment reference number must be entered !");
			  flag =1;
			}
		
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
				success : (function(response) {
					console.log(response);
				})
			});
			alert("Payment Added Succedfully !");
			 location.reload();
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
  function exportPDF1()
  {
      var element = document.getElementById('printTable2');
      var opt = {
          margin:       0.5,
          filename:     'sale'+<%=System.currentTimeMillis()%>+'.pdf',
          image:        { type: 'jpeg', quality: 1 },
          html2canvas:  { scale: 1 },
          jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
        };
      html2pdf().set(opt).from(element).save();
  }
  function exportPDF2()
  {
      var element = document.getElementById('printTable3');
      var opt = {
          margin:       0.5,
          filename:     'sale'+<%=System.currentTimeMillis()%>+'.pdf',
          image:        { type: 'jpeg', quality: 1 },
          html2canvas:  { scale: 1 },
          jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
        };
      html2pdf().set(opt).from(element).save();
  }



	function printData2()
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
}

function printData3()
{
   var divToPrint=document.getElementById("printTable3");
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
					const dt = new Date(paymentdate)
					//var d=paymentdate.Format("dd/MM/yyyy");
					//var paymentdate =(data.paymentdate)Date;
					//alert(dt);
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





</script>

</body>
</html>
