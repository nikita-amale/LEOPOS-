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
						<h1 class="h3 mb-0 text-gray-800">Customer Dashboard (${memberPojo.name})</h1>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: #1eb53a;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-white text-uppercase mb-1">
												Total Invoices</div>
											<div
												class="h5 mb-0 font-weight-bold text-white-800 text-white">
												<fmt:formatNumber pattern="0.00" value="${grand_total}" />
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-white-300 text-white"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-green text-uppercase mb-1">
												Total Payments</div>
											<div
												class="h5 mb-0 font-weight-bold text-gray-800 text-green">
												<fmt:formatNumber pattern="0.00" value="${payment_total}" />
												
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300 text-green"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: #0072c6;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1 text-white">Total
												Remaining</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div
														class="h5 mb-0 mr-3 font-weight-bold text-gray-800 text-white">
														<fmt:formatNumber pattern="0.00" value="${salepaid}" />
														
													</div>
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


						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card shadow h-100 py-2"
								style="background-color: orange;">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1 text-white">Overdue</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div
														class="h5 mb-0 mr-3 font-weight-bold text-gray-800 text-white">0.00</div>
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
											aria-controls="One" aria-selected="true">List Sales</a></li>

										<li class="nav-item"><a class="nav-link" id="three-tab"
											data-toggle="tab" href="#three" role="tab"
											aria-controls="Three" aria-selected="false">Payment</a></li>

									</ul>
								</div>

								<div class="tab-content" id="myTabContent">
									<div class="tab-pane fade show active p-3" id="one"
										role="tabpanel" aria-labelledby="one-tab">
										<div class="form-group">

											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>
														<th>Reference_no</th>
														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>


													</tr>
												</thead>
												<tbody>
													<c:forEach var="rsalePojo" items="${rsalesPojo}"
														varStatus="loop">
														<c:set var="balance"
															value="${rsalePojo.grand_total - rsalePojo.paid}" />
														<c:set var="saleGtotal" value="${rsalePojo.grand_total}" />
														<c:set var="saletax" value="${rsalePojo.total_tax}" />
														<c:set var="saletotal" value="${rsalePojo.total}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="rsaleDate${loop.count}">${rsalePojo.date}</td>
															<td id="rsaleId${loop.count}">${rsalePojo.rsaleId}</td>
															<td id="customer${loop.count}">${rsalePojo.member_name}</td>
															<td id="rsaleGtotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${saleGtotal}" /></td>
															
															<td id="rsaleStatus${loop.count}">${rsalePojo.paid}</td>
															<td id="rsaleTotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${balance}" /></td>
															<td id="rsaleStatus${loop.count}">${rsalePojo.payment_status}</td>

														</tr>
													</c:forEach>
												</tbody>

											</table>
										</div>
									</div>

									<div class="tab-pane fade p-3" id="three" role="tabpanel"
										aria-labelledby="three-tab">
										<div class="form-group">

											<table id="myTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>Serial No</th>
														<th>Date</th>

														<th>Customer</th>
														<th>Grand_Total</th>
														<th>Status</th>




													</tr>
												</thead>
												<tbody>
													<c:forEach var="paymentPojo" items="${paymentPojo}"
														varStatus="loop">
														<c:set var="paymentGtotal" value="${paymentPojo.grand_total}" />
														<tr id="${loop.count}">
															<td>${loop.count}</td>
															<td id="saleDate${loop.count}">${paymentPojo.date}</td>

															<td id="customer${loop.count}">${paymentPojo.member_name}</td>
															<td id="saleTotal${loop.count}"><fmt:formatNumber
																	pattern="0.00" value="${paymentGtotal}" /></td>
															<td id="SalePaid${loop.count}">${paymentPojo.status}</td>
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

	<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>

	<script type="text/javascript">
		
	</script>