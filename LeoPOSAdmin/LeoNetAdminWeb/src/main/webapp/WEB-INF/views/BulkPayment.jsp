<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<!-- /.container-fluid -->
		</div>
		<!-- /.content-header -->

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Customer Dashboard
							(${memberPojo.name} - ${memberPojo.ctype})</h1>

						<a href="javascript:void(0);" onclick="fnExcelReport()" id="btnExport"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Export to Excel</a> <a href="javascript:void(0);" onclick="exportOpenBalancePDF()"
							class="d-none d-sm-inline-block btn btn-sm btn-danger shadow-sm">
							<i class="fas fa-file-pdf"></i> Export Open Balance PDF
						</a>


					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-2 col-md-4 mb-4">
							<div class="card shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-green text-uppercase mb-1">
												Total Invoices</div>
											<div
												class="h5 mb-0 font-weight-bold text-green-800 text-green">${grand_total}</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-green-300 text-green"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-2 col-md-6 mb-4">
							<div class="card shadow h-100 py-2" style="background-color: #1eb53a;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-white text-uppercase mb-1">
												Total Payments</div>
											<div
												class="h5 mb-0 font-weight-bold text-gray-800 text-white">-${payment_total}</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300 text-white"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-xl-2 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: #2E8BC0;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-white text-uppercase mb-1">
												Total Credit Return</div>
											<div
												class="h5 mb-0 font-weight-bold text-white-800 text-white">-${creditreturn}</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-white-300 text-white"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-2 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: #2E8BC0;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-white text-uppercase mb-1">
												Total Credit Refund</div>
											<div
												class="h5 mb-0 font-weight-bold text-white-800 text-white">-${refund}</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-white-300 text-white"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						
						


						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-2 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: white;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1 text-green">Total
												Credit Amount</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div
														class="h5 mb-0 mr-3 font-weight-bold text-gray-800 text-green">-${creditpayment}</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-green-300 text-green"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-2 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: ${balance > 10000 ? 'red' : (balance >= 0 ? 'orange' : 'blue')}">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1 text-white">Total
												Remaining</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div
														class="h5 mb-0 mr-3 font-weight-bold text-gray-800 text-white">${balance}</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i
												class="fas fa-clipboard-list fa-2x text-gray-300 text-white"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Content Column -->
						<div class="col-xl-12 col-lg-7">


							<div class="card mt-3 tab-card">
								<div class="card-header tab-card-header">
									<ul class="nav nav-tabs card-header-tabs" id="myTab"
										role="tablist">
										<li class="nav-item"><a class="nav-link active"
											id="one-tab" data-toggle="tab" href="#one" role="tab"
											aria-controls="One" aria-selected="true">Emailers</a></li>
										<li class="nav-item"><a class="nav-link" id="two-tab"
											data-toggle="tab" href="#two" role="tab" aria-controls="Two"
											aria-selected="false">Apply Bulk/Deposit Payment</a></li>
										<li class="nav-item"><a class="nav-link" id="three-tab"
											data-toggle="tab" href="#three" role="tab"
											aria-controls="Three" aria-selected="false">View Bulk
												Payments</a></li>
										<li class="nav-item"><a class="nav-link" id="four-tab"
											data-toggle="tab" href="#four" role="tab"
											aria-controls="Four" aria-selected="false">View Payments</a></li>
										<li class="nav-item"><a class="nav-link" id="five-tab"
											data-toggle="tab" href="#five" role="tab"
											aria-controls="Five" aria-selected="false">Financials</a></li>
										<li class="nav-item"><a class="nav-link" id="six-tab"
											data-toggle="tab" href="#six" role="tab" aria-controls="Six"
											aria-selected="false">Statement</a></li>
											 <li class="nav-item"><a class="nav-link" id="seven-tab"
											data-toggle="tab" href="#seven" role="tab" aria-controls="Seven"
											aria-selected="false">Open Balance</a></li> 
											 <li class="nav-item"><a class="nav-link" id="eight-tab"
											data-toggle="tab" href="#eight" role="tab" aria-controls="Eight"
											aria-selected="false">View CR</a></li> 
												<button type="button" class="btn btn-primary mb-3"
												data-toggle="modal" data-target="#modal-creditrefund">
												Credit Amount Refund</button>
											
									</ul>
								</div>

								<div class="tab-content" id="myTabContent">

									<div class="tab-pane fade show active p-3" id="one"
										role="tabpanel" aria-labelledby="one-tab">
										<div class="form-group">
											<button type="button" class="btn btn-primary mb-3"
												id="bulkEmail">Bulk Email</button>

											<table id="saleTable"
												class="table table-bordered table-hover">
												<thead>
													<tr>
														<th></th>
														<th>Serial No</th>
														<th>Date</th>
														<th hidden>Saleid</th>
														<th >Reference no</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>
														<th>Send Mail</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach var="salesPojo" items="${salesPojo}"
														varStatus="loop">
														<c:if test="${salesPojo.isActive == '0'}">
															<c:set var="balance"
																value="${salesPojo.grand_total - salesPojo.paid}" />
															<c:set var="paid" value="${salesPojo.paid}" />
															<tr id="${loop.count}">
																<td>
																	<div class="form-check">
																		<input class="form-check-input sale" type="checkbox"
																		 value="${salesPojo.saleId}"
																			id="flexCheckDefault${loop.count}">
																	</div>

																</td>

																<td>${loop.count}</td>
																<td id="saleDate${loop.count}">${salesPojo.date}</td>
																<td id="saleId${loop.count}" hidden>${salesPojo.saleId}</td>
																<td id="referenceno${loop.count}">${salesPojo.referenceno}</td>
																<td id="customer${loop.count}">${salesPojo.member_name}</td>
																<td id="saleTotal${loop.count}">${salesPojo.grand_total}</td>
																<td id="SalePaid${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${paid}" /></td>
																<td id="SaleBalance${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${balance}" /></td>
																<td id="SaleStatus${loop.count}">${salesPojo.paymentstatus}</td>
																<td id="SendMail${loop.count}">
																	<button type="button" class="btn btn-primary"
																		onclick="sendDetails(${loop.count})">Send
																		Email</button>
																</td>
														</c:if>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="two" role="tabpanel"
										aria-labelledby="one-two">
										<div class="form-group">
											<!--  <button type="button" class="btn btn-primary mb-3"
												data-toggle="modal" data-target="#modal-bulkpaymentselected" >
												Add Bulk Payment Selected </button>
											<button type="button" class="btn btn-primary mb-3"
												data-toggle="modal" data-target="#modal-bulkpayment">Add
												Automated Bulk Payment</button>
											<button type="button" class="btn btn-primary mb-3"
												id="applyCreditPayment">Apply Credit Payment</button>-->

											<table id="saleTable"
												class="table table-bordered table-hover">
												<thead>
													<tr>
														<th></th>
														<th>Serial No</th>
														<th>Date</th>
														<th hidden>Saleid</th>
														<th>Reference no</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>
														<th>Print</th>
														<th hidden>memeberid</th>
														<th hidden>tax</th>
														<th hidden>Createdby</th>
														<th hidden>note</th>
														<th hidden>po</th>
														<th hidden>caddress</th>
														<th hidden>pincode</th>
														<th hidden>phonemain</th>
														

													</tr>
												</thead>
												<tbody>
													<c:forEach var="salesPojo" items="${salesPojo}"
														varStatus="loop">
														<c:if test="${salesPojo.isActive == '0'}">
															<c:set var="paid" value="${salesPojo.paid}" />
															<c:set var="balance"
																value="${salesPojo.grand_total - salesPojo.paid}" />

															<tr id="${loop.count}">
																<td><c:if test="${balance <= '0'}">
																		<div class="form-check">
																			<input class="form-check-input sale" type="checkbox"
																				disabled="disabled" value="${salesPojo.saleId}"
																				id="flexCheckDefault${loop.count}" >
																		</div>
																	</c:if> <c:if test="${balance > '0'}">
																		<div class="form-check">
																			<input class="form-check-input sale" type="checkbox"
																				value="${salesPojo.saleId}"
																				id="flexCheckDefault${loop.count}" >
																		</div>
																	</c:if></td>

																<td>${loop.count}</td>
																<td id="saleDate${loop.count}">${salesPojo.date}</td>
																<td id="saleId${loop.count}" hidden>${salesPojo.saleId}</td>
																<td id="referenceno${loop.count}">${salesPojo.referenceno}</td>
																<td id="customer${loop.count}">${salesPojo.member_name}</td>
																<td id="saleTotal${loop.count}">${salesPojo.grand_total}</td>
																<td id="SalePaid${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${paid}" /></td>
																<td id="SaleBalance${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${balance}" /></td>
																<td id="SaleStatus${loop.count}">${salesPojo.paymentstatus}</td>
																<td>
											<!-- Button trigger modal -->
											<button type="button"  style="font-size: 14px;"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#viewsales" onclick="ViewDetails(${loop.count})">
												View Sales
											</button>  
										</td>
											<td hidden id="memberId${loop.count}">${salesPojo.memberid}</td>
												<td hidden id="tax${loop.count}">${salesPojo.total_tax}</td>
											<td hidden  id="Createdby${loop.count}">${salesPojo.createdBy}</td>
								
								<td hidden id="note${loop.count}">${salesPojo.note}</td>
								<td hidden id="po${loop.count}">${salesPojo.purchaseorder}</td>
							
							
								<td hidden id="caddress${loop.count}">${salesPojo.customeraddress}</td>
								<td hidden id="pincode${loop.count}">${salesPojo.pincode}</td>
								<td hidden id="phonemain${loop.count}">${salesPojo.phonemain}</td>
														</c:if>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="three" role="tabpanel"
										aria-labelledby="two-three">
										<div class="form-group">


											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>
														<th>Customer</th>
														<th>Customer ID</th>
														<th>Amount</th>
														<th hidden>Bulk IDs</th>
														<th>View</th>
														<th>Delete</th>
														<th hidden>note</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach var="bulkpaymentPojo" items="${bulkpaymentPojo}"
														varStatus="loop">
														  <c:set var = "amountb" value = "${bulkpaymentPojo.amount}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="bulkDate${loop.count}">${bulkpaymentPojo.date}</td>
															<td id="customer${loop.count}">${bulkpaymentPojo.memberName}</td>
															<td id="memberId${loop.count}">${bulkpaymentPojo.memberId}</td>
															<td id="amountb${loop.count}"><fmt:formatNumber pattern="0.00" value="${amountb}" /></td>
															<td hidden id="bulkId${loop.count}">${bulkpaymentPojo.bulkId}</td>
															<td>
																<button type="button" style="font-size: 14px;"
																	class="btn btn-primary" data-toggle="modal"
																	data-target="#view" onclick="View(${loop.count})">View</button>
															</td>
															<td id="DeleteBulkPayment${loop.count}">
																<button type="button" class="btn btn-primary"
																	onclick="deleteBulkPayment(${loop.count},${bulkpaymentPojo.bulkId})">Delete Bulk
																	Payment</button>
															</td>
															<td hidden id="bulknote${loop.count}">${bulkpaymentPojo.note}</td>
													</c:forEach>

												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="four" role="tabpanel"
										aria-labelledby="four-tab">
										<div class="form-group">

											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>
														<th>Reference No.</th>
														<th>Method</th>
														<th>#</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Status</th>
														<th>View</th>
														<th>Delete</th>
														<th hidden>note
														<th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="paymentPojo" items="${paymentPojo}"
														varStatus="loop">
														<c:set var="grand_total"
															value="${paymentPojo.grand_total}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="paymentDate${loop.count}">${paymentPojo.paymentdate}</td>
															<td id="referenceNo${loop.count}">${paymentPojo.referenceno}</td>
															<td id="paymentMethod${loop.count}">${paymentPojo.ptype}</td>
															<td id="paymentMethodNo${loop.count}">${paymentPojo.pref}</td>
															<td id="customer${loop.count}">${paymentPojo.member_name}</td>
															<td id="paymentTotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${grand_total}" /></td>
															<td id="SalePaid${loop.count}">${paymentPojo.status}</td>
															<td>
																<button type="button" style="font-size: 14px;"
																	class="btn btn-primary" data-toggle="modal"
																	data-target="#viewpayment"
																	onclick="ViewPayment(${loop.count})">Print
																	Receipt</button>
															</td>
															<td id="DeletePayment${loop.count}">
																<button type="button" class="btn btn-primary"
																	onclick="deletePayment(${loop.count},${paymentPojo.id})">Delete
																	Payment</button>
															</td>
															<td hidden id="paymentnote${loop.count}">${paymentPojo.note}</td>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="five" role="tabpanel"
										aria-labelledby="five-tab">
										<div class="form-group">

											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>
														<th>Customer</th>
														<th>Amount</th>
														<th>Balance</th>

														<th>InvoiceID</th>
														<th>Type</th>


													</tr>
												</thead>
												<tbody>
													<c:forEach var="financialtransactionPojo"
														items="${financialtransactionPojo}" varStatus="loop">
														<c:set var="amt"
															value="${financialtransactionPojo.amount}" />
														<c:set var="balance"
															value="${financialtransactionPojo.balance}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="saleDate${loop.count}">${financialtransactionPojo.date}</td>
															<td id="customer${loop.count}">${financialtransactionPojo.customerName}</td>
															<td id="saleTotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${amt}" /></td>
															<td id="SalePaid${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${balance}" /></td>
															<td id="Ivoice${loop.count}">${financialtransactionPojo.invoideId}</td>
															<td id="Type${loop.count}">${financialtransactionPojo.type}</td>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="six" role="tabpanel"
										aria-labelledby="six-tab">
										<div class="form-group">
											<div class="form-group" style="display: flex">
												<div class="col-md-6">
													<label>Start Date</label><input type="date"
														class="form-control input-tip" name="startDate"
														id="startDate">
												</div>
												<div class="col-md-6">
													<label>End Date</label><input type="date"
														class="form-control input-tip" name="endDate" id="endDate">
												</div>
											</div>


											<button type="submit" id="submitbtn" onclick="myFunction()"
												class="btn btn-primary btn-font-size">Submit</button>
											<button class="btn btn-primary btn-font-size"
												onclick="reset()">Reset</button>

											<table id="myTable" class="table table-bordered table-hover">
												<thead>

												</thead>
												<tbody>

												</tbody>

											</table>
										</div>

									</div>
									
									 <div class="tab-pane fade p-3" id="seven" role="tabpanel"
										aria-labelledby="seven-tab">
								<div class="form-group">
							
											<button type="button" class="btn btn-primary mb-3"
												data-toggle="modal" data-target="#modal-bulkpaymentselected" onclick="handleClick(this)" >
												Add Bulk Payment Selected </button>
											<button type="button" class="btn btn-primary mb-3"
												data-toggle="modal" data-target="#modal-bulkpayment">Add
												Automated Bulk Payment</button>
											<button type="button" class="btn btn-primary mb-3"
												id="applyCreditPayment">Apply Credit Payment</button>
											

											<table id="openbalance"
												class="table table-bordered table-hover">
												<thead>
													<tr>
													    <th></th>
														<th>Serial No</th>
														<th>Date</th>
														<th >Saleid</th>
														<th >Reference no</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>
													

													</tr>
												</thead>
												<tbody>
												
													<c:forEach var="salesPojo" items="${salesPojoo}"
														varStatus="loop" >
														<c:if test="${salesPojo.isActive == '0'}">
															<c:set var="balance"
																value="${salesPojo.grand_total - salesPojo.paid}" />
															<c:set var="paid" value="${salesPojo.paid}" />
															<tr id="${loop.count}">
															<td><c:if test="${balance <= '0'}">
																		<div class="form-check">
																			<input class="form-check-input sale" type="checkbox"
																				disabled="disabled" value="${salesPojo.saleId}"
																				id="flexCheckDefault${loop.count}" >
																		</div>
																	</c:if> <c:if test="${balance > '0'}">
																		<div class="form-check">
																			<input class="form-check-input sale" type="checkbox"
																				value="${salesPojo.saleId}"
																				id="flexCheckDefault${loop.count}">
																		</div>
																	</c:if></td>
																

																<td>${loop.count}</td>
																<td id="saleDate${loop.count}">${salesPojo.date}</td>
																<td id="saleid${loop.count}" >${salesPojo.saleId}</td>
																<td id="referenceno${loop.count}">${salesPojo.referenceno}</td>
																<td id="customer${loop.count}">${salesPojo.member_name}</td>
																<td id="saleTotal${loop.count}">${salesPojo.grand_total}</td>
																<td id="SalePaid${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${paid}" /></td>
																<td id="saleBalance${loop.count}"><fmt:formatNumber
																		pattern="0.00" value="${balance}" /></td>
																<td id="SaleStatus${loop.count}">${salesPojo.paymentstatus}</td>
															</tr>	
														</c:if>
														
													</c:forEach>
												</tbody>

											</table>
										</div>
										

									</div>
									
									
									<div class="tab-pane fade p-3" id="eight" role="tabpanel"
										aria-labelledby="eight-tab">
										<div class="form-group">

											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>
														<th hidden>Return Id</th>
														<th hidden>Amount</th>
									                    <th hidden>Tax</th>
									                    <th hidden>total</th>
									<th hidden>CreatedBy</th>
										<th hidden>returntype</th>
														<th>Reference No.</th>
														<th>Method</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Appiled to SaleId</th>
														 <th>Status</th>
														<th>View</th>
															<th>Delete</th>
														<th hidden>note</th>
														
														<th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="returnPojo" items="${crrpaymentPojo}"
														varStatus="loop">
															<c:if test="${returnPojo.isActive == '0'}">
														<c:set var="grand_total"
															value="${returnPojo.amount+returnPojo.tax}" />
																<c:set var = "amount" value = "${returnPojo.amount}" />
									<c:set var = "tax" value = "${returnPojo.tax}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="crpaymentDate${loop.count}">${returnPojo.date}</td>
															<td hidden id="returnId${loop.count}">${returnPojo.returnId}</td>
															<td hidden id="amount${loop.count}"><fmt:formatNumber pattern="0.00" value="${amount}" /></td>
										<td hidden id="returnTaxFromList${loop.count}"><fmt:formatNumber pattern="0.00" value="${tax}" /></td>
										<td hidden id="rettotal${loop.count}">${returnPojo.userid}</td>
										<td hidden id="purchaseCreatedby${loop.count}">${returnPojo.createdBy}</td>
										<td hidden id="returntype${loop.count}">${returnPojo.returnType}</td>
															<td id="crreferenceNo${loop.count}">${returnPojo.referenceno}</td>
															<td id="crpaymentMethod${loop.count}">CR</td>
															<td id="crpaymentMethodNo${loop.count}">${returnPojo.member_name}</td>
															<td id="crpaymentTotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${grand_total}" /></td>
																	<c:choose>
										<c:when test="${returnPojo.returnType == '1'}">
										<td id="crsaleid${loop.count}">${returnPojo.saleid}</td>
										</c:when>
										<c:otherwise>
										<td id="crsaleid${loop.count}">Store as Credit</td>
										</c:otherwise>
										</c:choose>
																
															
															<c:choose>
										<c:when test="${returnPojo.returnType == '1'}">
										<td id="crSalePaid${loop.count}">Credit Return--Payment</td>
										</c:when>
										<c:otherwise>
										<td id="crSalePaid${loop.count}">Credit Return--Store</td>
										</c:otherwise>
										</c:choose>
															<td>
															
															<button type="button" style="font-size: 14px;"
																	class="btn btn-primary" data-toggle="modal"
																	data-target="#viewcr"
																	onclick="ViewReturnDetails(${loop.count})">Print
																	Receipt</button>
																
															</td>
															<td id="DeletePayment${loop.count}">
																<button type="button" class="btn btn-primary"
																	onclick="deletereturnDetails(${loop.count},${returnPojo.returnId})">Delete
																	Payment</button>
															</td>
															<td hidden id="crpaymentnote${loop.count}">${returnPojo.note}</td>
															</tr>
															</c:if>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

								</div>
							</div>


						</div>

					</div>



				</div>

			</div>
			<!-- /.container-fluid -->
		</section>
		<!-- /.content -->
	</div>
	<div class="modal fade" id="modal-bulkpayment">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<i class="fa fa-close">X</i>
					</button>
					<div class="form-group">
						<label for="exampleInputEmail1">Amount</label> <input type="text"
							class="form-control" id="amount" placeholder="Payment Amount">
					</div>
					<input type="hidden" class="form-control" value="${memberId}"
						id="hidsaleid"> <input type="hidden" class="form-control"
						value="${memberPojo.ctype}" id="ctype"> <input
						type="hidden" class="form-control" value="${creditpayment}"
						id="cApplypay">
					<div class="form-group">
						<label for="exampleInputEmail1">Note</label> <input type="text"
							class="form-control" id="paymentnote" placeholder="Enter Note">
							<small id="noteErrors" style="color: red; display: none;">Note format is invalid: '/' is not allowed.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Payment Type</label> <select
							class="form-control select2bs4" id="ptype" style="width: 100%;">
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
						<label for="exampleInputEmail1">Cheque No/CC No</label> <input
							type="text" class="form-control" id="chequecc"
							placeholder="Enter Cheque / Receipt">
					</div>
					<div class="card-footer">
						<button type="button" onclick="addBulkPayment()"
							class="btn btn-primary">Add Bulk Payment</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>

	<!--  Select Modal for Bulk payment -->

	<div class="modal fade" id="modal-bulkpaymentselected">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<i class="fa fa-close">X</i>
					</button>
					<div class="form-group">
						<label for="exampleInputEmail1">Amount</label> <input type="text"
							class="form-control" id="amountbulk" placeholder="Payment Amount">
					</div>
					<input type="hidden" class="form-control" value="${memberId}"
						id="hidsaleidbulk">
					<div class="form-group">
						<label for="exampleInputEmail1">Note</label> <input type="text"
							class="form-control" id="paymentnotebulk"
							placeholder="Enter Note">
							<small id="noteError" style="color: red; display: none;">Note format is invalid: '/' is not allowed.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Payment Type</label> <select
							class="form-control select2bs4" id="ptypebulk"
							style="width: 100%;">
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
						<label for="exampleInputEmail1">Cheque No/CC No</label> <input
							type="text" class="form-control" id="chequeccbulk"
							placeholder="Enter Cheque / Receipt">
					</div>
					<div class="card-footer">
						<button type="button" id="bulkPay" class="btn btn-primary">Add
							Bulk Payment</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!-- Credit amount refund -->
	
		<div class="modal fade" id="modal-creditrefund">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<i class="fa fa-close">X</i>
					</button>
					<div class="form-group">
						<label for="exampleInputEmail1">Amount</label> <input type="text"
							class="form-control" id="amountrefund" onchange="diff();" placeholder="Refund Amount" >
					</div>
					<div class="form-group">
					<label for="exampleInputEmail1">Cash To Return</label> <input
						type="text" class="form-control" id="diff" readonly="true";>
				</div>
					<input type="hidden" class="form-control" value="${memberId}"
						id="hidsaleidbulk">
						<input type="hidden" class="form-control" value="${creditpayment}"
						id="hidecreditpayment">
				
				
					
					<div class="card-footer">
						<button type="button"  onclick="Refund()"class="btn btn-primary">Add Refund</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>


	<div class="modal fade" id="view">
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
							style="margin-bottom: 15px; justify-content: space-between; padding: 0rem 1rem;">

							<div class="col">
								<table width="100%" style="font-size: 14px;">

									<tr>
										<td>3754 Central American Blvd.</td>
										<td align="right">BulkPayment Receipt</td>
									</tr>
									<tr>
										<td>Belize City Belize</td>
										<td align="right" id="date"></td>
									</tr>
									<tr>
										<td></td>
										<td align="right" id="amountbulk"></td>
									</tr>

									<tr>
										<td>TIN # 128693</td>
										<td align="right">Sales Person: SalesUser Sales</td>
									</tr>
								</table>
							</div>

						</div>

						<p style="margin-bottom: 0 !important; font-size: 14px;"></p>
						<p style="margin-bottom: 0; font-size: 14px;">
							<label id="customerbulk"></label><br>
						<p style="margin-bottom: 0; font-size: 14px;">
							<strong><label id="amountt"></label><br></strong>


						</p>


						<p style="margin-bottom: 0; font-size: 14px;">BulkPayment
							Receipt</p>

						<br>

						<div class="table-responsive" style="font-size: 14px !important;">
							<table id="bulkreceipt"
								class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>
									<tr>
										<th>Serial No</th>
										<th>Payment Method</th>
										<th>#</th>
										<th>Amount</th>
										<th>InvoiceID</th>
										<th>Sale Referenceno</th>

									</tr>
								</thead>
								<tbody>
								</tbody>

							</table>
						</div>

						<div class="row">
							<div class="col-xs-12"></div>


							<div class="col-xs-5 pull-right">

								<div class="well well-sm">
									<p style="font-size: 14px !important;" id="notebulk">
								</div>

							</div>
						</div>

					</div>


					<a href="javascript:void(0);" onclick="printData()"
						class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
						class="fa fa-print fa-sm text-white-50"></i> Print Receipt </a> <a
						href="javascript:void(0);" onclick="exportPDF()"
						class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
						class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>


				</div>
				<!-- /.modal-dialog -->
			</div>
		</div>
	</div>
	<!-- /.modal -->


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
					<div id="print-Table">

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
										<td align="left">Customer Name:${memberPojo.name}</td>
									</tr>
									<tr>
										<td>Date Received:
											<div class="text-left" id="paidDate">
										</td>
									</tr>
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

										<th>Payment Method</th>
										<th>#</th>
										<th>Payment Amount</th>
										<th>Invoice Id</th>


									</tr>

								</thead>

								<tbody style="text-align: center !important;">
									
									<td><div class="text-left" id="paidmethod"></div></td>
									<td><div class="text-left" id="methodno"></div></td>
									<td><div class="text-left" id="paidtotal"></div></td>
									<td><div class="text-left"id="payreference"></div></td>


								</tbody>
							</table>

						</div>


						<div class="row">
							<div class="col pull-right">
								<p id="notpay" style="font-size: 14px !important;"></p>
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

					<a href="javascript:void(0);" onclick="print_Data()"
						class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
						class="fa fa-print fa-sm text-white-50"></i> Print Receipt </a> <a
						href="javascript:void(0);" onclick="exportPDF1()"
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
	<!-- Viewpayemnt -->
	<!-- ViewCR -->
	<div class="modal fade" id="viewcr" tabindex="-1"
		aria-labelledby="viewcr" aria-hidden="true">
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
					<div id="print-RTable">

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
										<td align="left">Customer Name:${memberPojo.name}</td>
									</tr>
									<tr>
										<td>Date Received:
											<div class="text-left" id="crpaidDate">
										</td>
									</tr>
										
								
									
								</table>
							</div>


						</div>

						
						<p style="margin-bottom: 0 !important; font-size: 14px;" id="returnsaleid"></p>

						<div class="table-responsive" style="font-size: 14px !important;">
						<table id="returnReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th style="text-align: center !important;">No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">Unit Price</th>
									<th style="text-align: center !important;">Unit of Measure</th>
									<th style="text-align: center !important;">Quantity</th>
									<th style="text-align: center !important;">Subtotal</th>
								</tr>

							</thead>

							<tbody style="text-align: center !important;">

							</tbody>
							<tfoot>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="total"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Tax</td>
									<td align="center" id="returnTaxAmount"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

							</tfoot>
						</table>
					</div>


						<div class="row">
							<div class="col pull-right">
								<p id="notpay" style="font-size: 14px !important;"></p>
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

					<a href="javascript:void(0);" onclick="print_Data_return()"
						class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
						class="fa fa-print fa-sm text-white-50"></i> Print Receipt </a> <a
						href="javascript:void(0);" onclick="rexportPDF()"
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
	
	<div class="modal fade" id="viewsales" tabindex="-1" aria-labelledby="viewsales" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="btn-close button__border float-right close" data-bs-dismiss="modal" aria-label="Close">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
						<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
					</svg>
				</button>



				<!-- <a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fa fa-print fa-sm text-white-50"></i>Print Invoice</a> -->

				<div id="print-Table">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<table width="100%" style="font-size: 14px;">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/resources/images/logo_s.png"
								width="300px" /></td>
							<td>
								
							</td>
						</tr>
					</table>

					<br>

					<div class="row"
						style="justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;">
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right" >Tax Invoice</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right"  id="date"></td>
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
									<td align="right" >Sales Person</td>
								</tr>
								
							</table>
						</div>
					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						Name: <label class="mb-0" id="cName"></label><br>
						Address: <label class="mb-0" id="cAddress"></label><br>
						<label class="mb-0" id="phonemain"></label><br>
						<label class="mb-0" id="pincode"></label><br>
						
					</p>
					
					<br>
					<!-- <p style="margin-bottom: 0; font-size: 14px;">Sales Receipt</p>
					<br> -->

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="salesReceipt"
						class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1" style="border-collapse: collapse !important; margin-bottom: 0rem;">

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
						<table  class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1"	style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5" style="text-align: right !important; font-weight: bold; ">Total
									Amount (BZD)</td>
								<td id="totalt"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5" style="text-align: right !important; font-weight: bold;">Total
									Tax</td>
								<td id="totaltaxt"
									style="text-align: right; font-weight: bold;width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right !important; font-weight: bold;">Total
									Paid</td>
								<td id="totalPaidt"
									style="text-align: right; font-weight: bold;width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right !important; font-weight: bold;">Grand
									Total (BZD)</td>
								<td id="balTott" style="text-align: right; font-weight: bold;width: 17%;"></td>
							</tr>

						</table>
					
					</div>
					<div class="row">
						<div class="col pull-right">
							<p  id ="notee"style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right">
							<div class="print-flex well well-sm">
								<p style="font-size: 14px !important;" id="Createdby">
								</p>
								
							</div>
						</div>
					</div>
					<div class="row">
					<div class="col-xs-12"></div>
					<div class="col pull-right">
					<div class="print-flex well well-sm">
					<p style="font-size: 14px !important;"><strong>Invoice must be presented when making a return, 10% Restocking Fee will be charged on items returned after 7 days</strong>
					</p>
					
					</div>
					</div>
			
			</div>
				</div>
				<a href="javascript:void(0);" onclick="printData()"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
				class="fa fa-print fa-sm text-white-50"></i> Print Invoice
			</a>
				<a href="javascript:void(0);" onclick="exportPDF1()"
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
	
	$(document).ready(function () {            
        $("td.contribution").each(function () {
            $(this).text($(this).text().toLocaleString('en-US'));
        })
    });
	
	document.getElementById('startDate').addEventListener('click', function() {
	    this.showPicker();
	});

	document.getElementById('endDate').addEventListener('click', function() {
	    this.showPicker();
	});

	
	function handleClick(checkbox) {
	    // Your code here
	  //  alert("Checkbox clicked");
	    var grandTotal = calculateGrandTotal();
	 
	}
	
	function calculateGrandTotal() {
	    // Calculate the grand total
		var total = 0;
	    var checkboxes = document.querySelectorAll('.sale');
	    var saleid;
	  
	    
	    checkboxes.forEach(function(checkbox) {
	        if (checkbox.checked) {
	            var rowNumber = checkbox.id.replace('flexCheckDefault', ''); // Extract the row number
	            var grandTotalValue = parseFloat(document.getElementById("saleBalance" + rowNumber).innerText);
	            var saleid = parseFloat(document.getElementById("saleid" + rowNumber).innerText);
	            //alert("saleid:"+saleid)
	            if (!isNaN(grandTotalValue)) {
	                total += grandTotalValue;
	      
	            }
	        }
	    }); 
	    total=parseFloat(total.toFixed(2));
	   // alert("Total for selected sales is:"+total);
	    document.getElementById("amountbulk").value = total.toFixed(2);
	    return total;
	    
	}
	
	function myFunction() {
		
		var flag =0;
		
		var sdate=  document.getElementById("startDate").value ;
		var edate=  document.getElementById("endDate").value ;
		 
		 if (sdate == "" || edate == "" )
			{
			  alert("Start or End dates must be Selected !");
			  flag =1;
			}
		
		if(flag == 0){
			newWin= window.open("${pageContext.request.contextPath}/newstatementReport?MemberId=${memberPojo.id}&Ctype=${memberPojo.ctype}&startDate="+sdate+"&endDate=" + edate);
		}
		
		//window.location.href="${pageContext.request.contextPath}/statementReport?MemberId=${memberPojo.id}&startDate="+sdate+"&endDate=" + edate;
		 
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
       	}, 250);
  	return true;
}

function print_Data()
{
   var divToPrint=document.getElementById("print-Table");
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

function print_Data_return()
{
   var divToPrint=document.getElementById("print-RTable");
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

function rexportPDF()
{
    var element = document.getElementById('print-RTable');
    var opt = {
        margin:       0.5,
        filename:     'bulkpayment'+<%=System.currentTimeMillis()%>+'.pdf',
        image:        { type: 'jpeg', quality: 1 },
        html2canvas:  { scale: 1},
        jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
      };
    html2pdf().set(opt).from(element).save();
}
function exportPDF()
{
    var element = document.getElementById('printTable');
    var opt = {
        margin:       0.5,
        filename:     'bulkpayment'+<%=System.currentTimeMillis()%>+'.pdf',
        image:        { type: 'jpeg', quality: 1 },
        html2canvas:  { scale: 1},
        jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
      };
    html2pdf().set(opt).from(element).save();
}
function exportPDF1()
{
    var element = document.getElementById('print-Table');
    var opt = {
        margin:       0.5,
        filename:     'payment'+<%=System.currentTimeMillis()%>+'.pdf',
        image:        { type: 'jpeg', quality: 1 },
        html2canvas:  { scale: 1 },
        jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
      };
    html2pdf().set(opt).from(element).save();
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
	tab = document.getElementById('openbalance');

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
	link.download = "Customer balance Report" + yyyy + "-" + mm + "-" + dd + ".xls";
	link.href = uri + base64(format(template, ctx));
	link.click();
}
	 
	function addBulkPayment(){
		  
	  	 var memberId = $('#hidsaleid').val();
		 var amount = $('#amount').val();
		 var note = $('#paymentnote').val();
		 var pref = $('#chequecc').val();
		 var ptype = $('#ptype :selected').val();
		 var ctype = $('#ctype').val();
		// alert(ctype);
		 var flag =0;
		 
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
		 
		 
		 alert("Sale Amount: " + amount + "....... Member ID: " + memberId + "....... Payment Note: " + note);
		 
		
		 item = {}
	     item ["memberId"] = memberId;
		 item ["amount"] = amount;
		 item ["note"] = note;
		 item ["ptype"] = ptype;
		 item ["pref"] = pref;
		 item ["ctype"] = ctype;
		 
		 jsonObj = JSON.stringify(item);
		 console.log("JSON Object",jsonObj);
		
	
	//	 alert(myJSON);
		 
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/addBulkPayment',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : jsonObj,
				success : (function(response) {
					 location.reload();
					console.log(response);
				})
			});
		//	location.reload();
			
		 }
		 reload();
	 }
	 
	 function sendDetails(count){
		  
		  var x = confirm("Mail send");
	      if (x) { 
		  var saleId = $("#saleId"+count).text();
		 
	   $.ajax({
			url : '${pageContext.request.contextPath}/sale/sendmail/'+saleId,
			type : "GET",
			success : function(data) {
				alertify
				  .alert(data.msgDescr, function(){  
				  });
				  
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	      }
	      else {
	       //Action for cancel
	          return false;
	      }
	 }
	 
	 document.getElementById('paymentnotebulk').addEventListener('input', function () {
		    const noteInput = this.value;
		    const errorMessage = document.getElementById('noteError');
		    
		    // Check if the note contains '/'
		    if (noteInput.includes('/')) {
		        errorMessage.style.display = 'block'; // Show error message
		    } else {
		        errorMessage.style.display = 'none'; // Hide error message
		    }
		});
	 
	 
	 document.getElementById('paymentnote').addEventListener('input', function () {
		    const noteInput = this.value;
		    const errorMessage = document.getElementById('noteErrors');
		    
		    // Check if the note contains '/'
		    if (noteInput.includes('/')) {
		        errorMessage.style.display = 'block'; // Show error message
		    } else {
		        errorMessage.style.display = 'none'; // Hide error message
		    }
		});
	
		
	
   // Send Selected bulk email
	 
	$("#bulkEmail").on("click",function(e){
		
				
		var arr = [];
		var memberId = <%=request.getParameter("MemberId") != null && !request.getParameter("MemberId").isEmpty()
		? request.getParameter("MemberId")
		: 0%>;
		$(this).attr("disabled", true);         
		$("#saleTable tbody tr:has(input:checked)").each(function(){
			arr.push({
				saleId :$(this).closest("tr").find("td:eq(3)").text()
			});
		});
		if(arr.length > 0){
		 $.ajax({
				url : "${pageContext.request.contextPath}/sale/"+memberId+"/bulkSendMail",
				type : "POST",
				dataType: "json",
				contentType:"application/json",
				data : JSON.stringify(arr),
				success : function(data) {
					alertify
					  .alert(data.msgDescr, function(){
						  $("#bulkEmail").removeAttr("disabled");
					  });
					  
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});
		}
	   
		$("#bulkEmail").removeAttr("disabled");
		reload();
	});
   
   	
	// Selected Bulk Payment apply
	
	$("#bulkPay").on("click",function(e){
		
		//alert("Test bulk pay");
	
		
	//	var memberId = $('#hidsaleidbulk').val();
		var amount = $('#amountbulk').val();
		 var note = $('#paymentnotebulk').val();
		 var pref = $('#chequeccbulk').val();
		 var ptype = $('#ptypebulk :selected').val();
		 //var ctype = $('#ctype').val();
		 var flag =0;
		
		
		 
			 
		 if (ptype == "")
			{
			  alert("The Payment type must be selected !");
			  flag =1;
			 
			}
		 
		 if (pref == ""&& ptype!="Cash"&&ptype!="Online"&&ptype!="Credit Card"&& ptype!="Other")
			{
			  alert("The Payment reference number must be entered !");
			  flag =1;
			  
			}
		 
		 if (amount == "")
			{
			  alert("The Bulk Payment amount must be entered !");
			  flag =1;
			 
			}
		 if(ptype=="Cash"){
			 pref="0";
			 
		 }
		 if(ptype=="Online"){
			 pref="0";
			 
		 } if(ptype=="Credit Card"){
			 pref="0";
			 
		 }
		 if(ptype=="Other"){
			 pref="0";
			 
		 
	 }
		 if(note==""){
			 note="No note";
		 }
		 alert("Amount is " + amount + "..... note is " + note + " ....Flag is    " +flag);
		 
		 if(flag ==0)
		 
		 {
		
		var arr = [];
		var memberId = <%=request.getParameter("MemberId") != null && !request.getParameter("MemberId").isEmpty()
		? request.getParameter("MemberId")
		: 0%>;
		$(this).attr("disabled", true);         
		$("#openbalance tbody tr:has(input:checked)").each(function(){
			arr.push({
				saleId :$(this).closest("tr").find("td:eq(3)").text()
			});
		});
		if(arr.length > 0){
		 $.ajax({
				url : "${pageContext.request.contextPath}/sale/"+memberId+"/"+amount+"/"+note+"/"+ptype+"/"+pref+"/bulkPaySelected",
				type : "POST",
				dataType: "json",
				contentType:"application/json",
				data : JSON.stringify(arr),
				success : function(data) {
					alertify
					  .alert(data.msgDescr, function(){
						  $("#bulkEmail").removeAttr("disabled");
					  });
					  
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});
		}else{
			alert("Please select the invoice");
		}
		
		$("#bulkPay").removeAttr("disabled");
	  }
          reload();
      	
		 
	});
	
	 // Apply Credit Payment
	 
	$("#applyCreditPayment").on("click",function(e){
		
				
		 var creditamount = $('#cApplypay').val();
		 alert(creditamount);
		 var note = "Credit Payment";
		 var pref = "CC";
		 var ptype = "CC Payment";
		 var flag =0;
		 
			 
		 if (ptype == "")
			{
			  alert("The Payment type must be selected !");
			  flag =1;
			}
		 
		 if (pref == ""&& ptype!="Cash")
			{
			  alert("The Payment reference number must be entered !");
			  flag =1;
			}
		 if (creditamount == "" || creditamount <= 0)
			{
			  alert("The Credit Amount must be greater than 0 !");
			  flag =1;
			}
		 
		 alert("Credit Amount is " + creditamount + "..... note is " + note + " ....Flag is    " +flag);
		 if(ptype="Cash"){
			 pref="0";
		 }
		 if(flag ==0)
		 
		 {
		
		var arr = [];
		var memberId = <%=request.getParameter("MemberId") != null && !request.getParameter("MemberId").isEmpty()
		? request.getParameter("MemberId")
		: 0%>;
		$(this).attr("disabled", true);         
		$("#openbalance tbody tr:has(input:checked)").each(function(){
			arr.push({
				saleId :$(this).closest("tr").find("td:eq(3)").text()
			});
		});
		
		if(arr.length > 0){
		 $.ajax({
				url : "${pageContext.request.contextPath}/sale/"+memberId+"/"+creditamount+"/"+note+"/"+ptype+"/"+pref+"/applyCreditPayment",
				type : "POST",
				dataType: "json",
				contentType:"application/json",
				data : JSON.stringify(arr),
				success : function(data) {
					alertify
					  .alert(data.msgDescr, function(){
						  $("#applyCreditPayment").removeAttr("disabled");
					  });
					  
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});
		}
		$("#applyCreditPayment").removeAttr("disabled");
	  }
          reload();
	});
	 
	 //Credit amount refund
	 
	function Refund(){

	  	 var memberId = $('#hidsaleid').val();
	  	var amountrefund = $("#amountrefund").val();
	  	var creditpayment = $("#hidecreditpayment").val();
	 // 	alert(creditpayment);
	  	
	  	if((amountrefund)>(creditpayment)){
  		 amountrefund=creditpayment;
	  		
	  	}else{
	  	 	var amountrefund = $("#amountrefund").val();
	  	 	
	  	}
	  
	  	
	

	 	  
  		 $.ajax({
	  				url : '${pageContext.request.contextPath}/refundcreditamount?MemberId='+memberId+ "&Amount="+amountrefund ,
	  				type : "POST",
	  				dataType : "json",
	  				success : function(data) {
	  					var ajaxCallData = JSON.stringify(data);
	  					console.log(data); 
	  		
	  				
	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
  		 reload();
	  		 }
	 
	function diff() {
		// alert("Reached diff");
		
		 var memberId = $('#hidsaleid').val();
		  	var amountrefund = $("#amountrefund").val();
		  	var creditpayment = $("#hidecreditpayment").val();
			
		
		 if(parseFloat(amountrefund)>parseFloat(creditpayment)) {
		
			var diff =parseFloat(amountrefund)-parseFloat(creditpayment);
		
		 }else{
			
			 var diff = 0; 
		 }
		 if (!isNaN(diff)) {
				document.getElementById('diff').value = diff.toFixed(2);
			}
		 
 }
	 
	 
	 
	 
	 
	// Apply Credit payment end ************
	
	
	function View(count){
		
	  	  var sTotal = $("#amount"+count).text();
	  	  var returnId = $("#returnId"+count).text();
	  	 var memberId = $('#hidsaleid').val();
	  	var bulkId = $("#bulkId"+count).text();
	  	 var paidDate = $("#bulkDate"+count).text();
		
	  	//bulkDate
	  
	  	
	  	 $("#bulkreceipt  tbody").empty();
	  	
	  	 $("#notebulk").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note: " + $("#bulknote"+count).text());
		 $("#amountt").html("Total Bulk Amount Paid: $" + $("#amountb"+count).text());
		 $("#customerbulk").html("Customer Name: " + $("#customer"+count).text());
		 $("#date").html("Date: " + $("#bulkDate"+count).text());
		
		 
	  	  //alert(sTotal);
	 	  
  		 $.ajax({
	  				url : '${pageContext.request.contextPath}/getpaymentbymemberId?MemberId='+memberId + "&bulkId="+bulkId ,
	  				type : "GET",
	  				dataType : "json",
	  				success : function(data) {
	  					var ajaxCallData = JSON.stringify(data);
	  					console.log(data); 
	  					$.each(data, function(i, data) {
	  					
	  						var rowCount = $('#bulkreceipt tr').length ;
	  					var tr = $('<tr></tr>');
	  					
	  					tr.append($('<td></td>').html(rowCount));
	  					tr.append($('<td></td>').html(data.ptype));
	  					tr.append($('<td></td>').html(data.pref));
					
						tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
						tr.append($('<td></td>').html(data.referenceno));
						tr.append($('<td></td>').html(data.salesreferenceno));
					
						
						tr.append($('<tr></tr>').html());
						
						$('#bulkreceipt tbody').append(tr);
	  					});
	  					
	  				
	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		 }
	
	
	function ViewPayment(count) {
		
		
		  $("#notpay").html("Note: " + $("#paymentnote"+count).text());
		
		 var payreference = $("#referenceNo"+count).text();
		 $("#payreference").html(payreference);
		 var paidtotal = $("#paymentTotal"+count).text();
		 $("#paidtotal").html(paidtotal);
		 var paidmethod = $("#paymentMethod"+count).text();
		 $("#paidmethod").html(paidmethod);
		 var methodno = $("#paymentMethodNo"+count).text();
		 $("#methodno").html(methodno);
		 var paidDate = $("#paymentDate"+count).text();
		 $("#paidDate").html(paidDate);	
		// alert(payreference)
		// var payment = $("#paymentnote"+count).text();
		 //$("#payment").html(payment);	
	}
	
	function ViewCR(count) {
		
		
		  $("#notpay").html("Note: " + $("#crpaymentnote"+count).text());
		
		 var crpayreference = $("#crreferenceNo"+count).text();
		 $("#crpayreference").html(crpayreference);
		 var crpaidtotal = $("#crpaymentTotal"+count).text();
		 $("#crpaidtotal").html(crpaidtotal);
		 var crpaidmethod = $("#crpaymentMethod"+count).text();
		 $("#crpaidmethod").html(crpaidmethod);
		 var crmethodno = $("#crpaymentMethodNo"+count).text();
		 $("#crmethodno").html(crmethodno);
		 var crpaidDate = $("#crpaymentDate"+count).text();
		 $("#crpaidDate").html(crpaidDate);	
		 //alert(crpayreference)
		// var payment = $("#paymentnote"+count).text();
		 //$("#payment").html(payment);	
	}
	
	function ViewDetails(count){
		  var sTotal = $("#saleTotal"+count).text();
		  var saleId = $("#saleId"+count).text();
		  var ctype = $('#ctype').val();
		  var paid = $("#SalePaid"+count).text();
		  var tax =  $("#tax"+count).text();
		  var memberId = $("#memberId"+count).text();
		
		  if(!isNaN(paid)){
			  paid = parseFloat(paid).toFixed(2); 
		  }
		//  alert(ctype);
			
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
							console.log(data);
							
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
							var rowCount = $('#salesReceipt tr').length ;
							var tr = $("<tr></tr>");
							//alert(rowCount);
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
	    if(tax==0) {
	    	taxT = 0;
	    }else {
	     taxT = grandT * .125;
	    }
	   
	    totalT = grandT + taxT;
	     $("#totalt").html(grandT.toFixed(2));
	     $("#totaltaxt").html(taxT.toFixed(2));
	     $("#balTott").html(totalT.toFixed(2));
	     $("#totalPaidt").html(paid);
	    // Math.round(12.345 * 100) / 100;
	  }
	  
	
	function deletePayment(count, paymentid){
			
			 
			  var x = confirm("Are you sure you want to delete?" + paymentid);
			  if (x) {
				
				  $.ajax({
						url : '${pageContext.request.contextPath}/deletePayment',
						type : "POST",
						dataType : "json",
						data:{id:paymentid},
						success : function(data) {
				 			//alertify
							//  .alert(data.msgDescr, function(){
								 location.reload();
							//  }); 
							 
						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
			  }
			}
	
	function deleteBulkPayment(count, bulkid){
		
		 
		  var x = confirm("Are you sure you want to delete bulk payment ?" + bulkid);
		  if (x) {
			
			  $.ajax({
					url : '${pageContext.request.contextPath}/deleteBulkPayment',
					type : "POST",
					dataType : "json",
					data:{id:bulkid},
					success : function(data) {
			 			//alertify
						//  .alert(data.msgDescr, function(){
							 location.reload();
						//  }); 
						 
					},
					error : function(error) {
						console.log(`Error ${error}`);
					}

				});
		  }
		}
	
	 function ViewReturnDetails(count){
	  	  var sTotal = $("#amount"+count).text();
	  	  var returnId = $("#returnId"+count).text();
	  	var returnsaleid = $("#crsaleid"+count).text();
		var returntype = $("#returntype"+count).text();


		let returnTaxText = $("#returnTaxFromList" + count).text().trim();
		let returnTaxAmount = parseFloat(returnTaxText);

		if (!isNaN(returnTaxAmount)) {
		  $("#returnTaxAmount").html(returnTaxAmount.toFixed(2));
		} else {
		  $("#returnTaxAmount").html("0.00"); 
		}
	  	 
	  	  //alert(returnsaleid);
	 	  
	  		 $("#returnReceipt  tbody").empty();
	  		 
	  		 // commenting below tax due to it is taking value from wrong place.
	  		// $("#tax").html($("#tax"+count).text());
	  		 
	  		 
	  		// $("#totaltax").html($("#totaltax"+count).text());
	  		 $("#total").html($("#amount"+count).text());
	  		 
	  		// $("#returnsaleid").html("Appiled To SaleId: " + $("#crsaleid"+count).text());
	  		 if (returntype == 1) {
	  	        $("#returnsaleid").html("Appiled To SaleId: " + $("#crsaleid"+count).text());
	  	    } else {
	  	        // Set to blank if return_type is not 1
	  	        $("#returnsaleid").html(("Appiled To SaleId: "+ "Store as Credit"));
	  	    }
	  	

	  		var rettotalValue = parseFloat($("#rettotal" + count).text()); 
	  		var formattedValue = rettotalValue.toFixed(2); 
	  		$("#balTot").html(formattedValue);
	  	// $("#saleTotal").html($("#saleTotal"+count).text());
	  		 $("#sRefno").html("Returns Reference No. " + $("#returnRefno"+count).text());
	  		 $("#salereferenceno").html("Sales Reference No. " + $("#salereferenceno"+count).text());
	  		
	  		 $("#cName").html( $("#customer"+count).text());
	 		 $("#date").html("Date :" + $("#quotesDate"+count).text());
	  		 $("#purchaseCreatedby").html("Created By :" + $("#purchaseCreatedby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
	  		 $("#customer").html("Bill To :" + $("#customer"+count).text());
	  		$("#note").html("Note :" + $("#note"+count).text());
	  		 
	  		 
	  		 
//	  		 alert(rquoteId);
	  		 $.ajax({
	  				url : '${pageContext.request.contextPath}/getReturnitembyreturnId?returnId=' + returnId,
	  				type : "GET",
	  				dataType : "json",
	  				success : function(data) {
	  					var ajaxCallData = JSON.stringify(data);
	  					
	  					$.each(data, function(i, data) {
	  						var unitname = data.unitname;
	  						if(unitname == "Piece"){
								unitname = "Pc";
							  }
	  						if(unitname == "Wire Roll 328 Ft"){
								unitname = "Roll 328'";
							  }
	  						var rowCount = $('#returnReceipt tr').length ;
	  						
	  						var tr = $('<tr></tr>');
	  						tr.append($('<td></td>').html(rowCount-3));
	  						tr.append($('<td></td>').html(data.product_name));
	  						tr.append($('<td></td>').html(data.real_unit_price.toFixed(2)));
	  						tr.append($('<td></td>').html(unitname));
	  						tr.append($('<td></td>').html(data.quantity));
	  						tr.append($('<td style="text-align: right;"></td>').html(data.subtotal.toFixed(2)));
	  						tr.append($('<tr></tr>').html());
	  						$('#returnReceipt tbody').append(tr);
	  					});

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		 }
	 
	 function deletereturnDetails(count, returnid){
			var ctype = $("#ctype"+count).text();
			  var x = confirm("Are you sure you want to delete?");
		  if (x) {
			
			  $.ajax({
					url : '${pageContext.request.contextPath}/newDeleteReturn',
					type : "POST",
					dataType : "json",
					data:{id:returnid},
					success : function(data) {
			 			//alertify
						//  .alert(data.msgDescr, function(){
							 location.reload();
						//  }); 
						 
					},
					error : function(error) {
						console.log(`Error ${error}`);
					}

				});
		  }
		}
	
	function reload(){
	setTimeout(() => {
		  document.location.reload();
		}, 1000);

	}
	
	function exportOpenBalancePDF() {

	    var element = document.getElementById('openbalance');

	    var opt = {
	        margin:       0.5,
	        filename:     'OpenBalance_' + new Date().getTime() + '.pdf',
	        image:        { type: 'jpeg', quality: 1 },
	        html2canvas:  { scale: 2 }, // better quality
	        jsPDF:        { unit: 'in', format: 'letter', orientation: 'landscape' }
	    };

	    html2pdf().set(opt).from(element).save();
	}
	</script>