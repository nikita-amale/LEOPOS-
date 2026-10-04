<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!-- Main Sidebar Container -->

<link rel="stylesheet" href="resources/css/style.css">
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="${pageContext.request.contextPath}/home" class="brand-link">
		<img
		src="${pageContext.request.contextPath}/resources/images/logo-white.png"
		width="100%" /> <!-- <span class="brand-text font-weight:900">Suppliers Plus
			Distributor Co.Ltd.</span>  <span class="brand-text font-weight:900"> Co.Ltd.</span> -->
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image"></div>
			<div class="info">
			<sec:authorize access="!hasAuthority('custom')">
				<a href="#" class="d-block">${userName}</a>
				</sec:authorize>
			</div>
		</div>
		<sec:authorize access="hasAuthority('admin')">


			<!-- Sidebar Menu -->

			<nav class="mt-2">

				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/registerMember"
								class="nav-link">

									<p>Add Customer</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-barcode"></i>
							<p>
								Products <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addProduct"
								class="nav-link">
									<p>Add Product</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetailsPage"
								class="nav-link">
									<p>List Products</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
											<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSpecialRegister"
								class="nav-link">
									<p>View Special Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/paymentReport"
								class="nav-link">
									<p>Payment Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Product Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerPurchaseReport"
								class="nav-link">
									<p>Customer Purchase Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthsalesReport"
								class="nav-link">
									<p>Monthly Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthspecialsalesReport"
								class="nav-link">
									<p>Monthly SpecialSales Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/replenishmentReport"
								class="nav-link">
									<p>Replenishment Report</p>
							</a></li> 
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/sysAuditReport"
								class="nav-link">
									<p>Audit report</p>
							</a></li> 
							
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/specialcustomerPurchaseReport"
								class="nav-link">
									<p>Customer(Special) Purchase Report</p>
							</a></li> -->
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales</p>
							</a></li>
						<!-- 	
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesInvoiceReport"
								class="nav-link">
									Sales By Invoice</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSalesNew"
								class="nav-link">
									<p>Add Sales New</p>
							</a></li>
							<sec:authorize access="hasAuthority('admin')">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalePdf"
								class="nav-link">
									<p>Sales Migration</p>
							</a></li>
							</sec:authorize>   -->
						</ul></li>
					<!-- 		
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Product Rentals <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addProductRental"
							class="nav-link">
								<p>Add Rental Product</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewProductRental"
							class="nav-link">
								<p>List Rental Products</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addRentalQuote"
							class="nav-link">
								<p>Add Rental Quote</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalQuotes"
							class="nav-link">
								<p>List Rentals Quotes</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalSales"
							class="nav-link">
								<p>List Rentals Sales</p>
						</a></li>
					</ul></li>
					
				 -->
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>


						</ul></li>

					</li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Purchases <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/importPurchase"
								class="nav-link">
									<p>Import Purchase Products</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addPurchase"
								class="nav-link">
									<p>Add Purchase</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewPurchase"
								"
							class="nav-link">
									<p>View Purchases</p>
							</a></li>
						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>
						</ul></li>


					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-file"></i>
							<p>
								Settings <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addCatagory"
								class="nav-link">
									<p>Add Category</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSubCatagory"
								class="nav-link">
									<p>Add Sub-Category</p>
							</a></li>

						</ul></li>



					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</sec:authorize>

		<!--Sales-->
		<sec:authorize access="hasAuthority('sales')">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<nav class="mt-2">
					<!--<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						 <ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li> -->
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
						<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li> -->
							
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns New</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								"
							class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscashNew"
								"
							class="nav-link">
									<p>View Returns(cash) New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>
							


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link" hidden> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
						</ul></li>

					</li>
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
			</ul>


			</nav>
		</sec:authorize>
		
		<!-- salesplus -->
		<sec:authorize access="hasAuthority('salesplus')">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<nav class="mt-2">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
					
						<ul class="nav nav-treeview">
						<li class="nav-item"><a
								href="${pageContext.request.contextPath}/registerMember"
								class="nav-link">

									<p>Add Customer</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
						<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li> -->
							
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns New</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								"
							class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscashNew"
								"
							class="nav-link">
									<p>View Returns(cash) New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
						</ul></li>

					</li>
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
			</ul>


			</nav>
		</sec:authorize>
		


		<!--cashier-->
		<sec:authorize access="hasAuthority('cashier')">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<nav class="mt-2">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>
						<!--  	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>-->


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-barcode"></i>
							<p>
								Products <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetails"
								class="nav-link">
									<p>List Products</p>
							</a></li>
							
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetailsPage"
								class="nav-link">
									<p>List Products New </p>
							</a></li>
						</ul></li>


					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales New</p>
							</a></li>
						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Purchases <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/importPurchase"
								class="nav-link">
									<p>Import Purchase Products</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addPurchase"
								class="nav-link">
									<p>Add Purchase</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewPurchase"
								"
							class="nav-link">
									<p>View Purchases</p>
							</a></li>

						</ul></li>
							



					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
		class="nav-link">
									<p>View Returns</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
		class="nav-link">
									<p>View Returns New</p>
							</a></li>
							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								"
							class="nav-link">
									<p>View Returns(cash)</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscashNew"
								"
		class="nav-link">
									<p>View Returns (cash)New</p>
							</a></li>
						</ul></li>
					</li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">


							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>


						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReportForCashier"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Sales Report</p>
							</a></li>   -->
							
						<!--	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/paymentReport"
								class="nav-link">
									<p>Payment Report</p>
							</a></li>    -->
						

						</ul></li>


					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
			</ul>
			</nav>
		</sec:authorize>
		
		
		<!-- Aleisha -->
		<sec:authorize access="hasAuthority('employee')">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<nav class="mt-2">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li> -->

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>--?
						<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>-->
						</ul>

						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li> -->
							
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns New</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								"
							class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscashNew"
								"
							class="nav-link">
									<p>View Returns(cash) New</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>-->
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<!-- <li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>
							


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/paymentReport"
								class="nav-link">
									<p>Payment Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Product Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerPurchaseReport"
								class="nav-link">
									<p>Customer Purchase Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthsalesReport"
								class="nav-link">
									<p>Monthly Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthspecialsalesReport"
								class="nav-link">
									<p>Monthly SpecialSales Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/replenishmentReport"
								class="nav-link">
									<p>Replenishment Report</p>
							</a></li> 
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/specialcustomerPurchaseReport"
								class="nav-link">
									<p>Customer(Special) Purchase Report</p>
							</a></li> -->
						</ul></li>

					</li>
					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
			</ul>


			</nav>
		</sec:authorize>
		
		
		<sec:authorize access="hasAuthority('custom')">


			<!-- Sidebar Menu -->

			<nav class="mt-2">

				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/registerMember"
								class="nav-link">

									<p>Add Customer</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-barcode"></i>
							<p>
								Products <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addProduct"
								class="nav-link">
									<p>Add Product</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetailsPage"
								class="nav-link">
									<p>List Products</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							
							<!-- 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/paymentReport"
								class="nav-link">
									<p>Payment Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Product Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerPurchaseReport"
								class="nav-link">
									<p>Customer Purchase Report</p>
							</a></li>   -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthsalesReport"
								class="nav-link">
									<p>Monthly Sales Report</p>
							</a></li>  
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthspecialsalesReport"
								class="nav-link">
									<p>Monthly SpecialSales Report</p>
							</a></li> 
							
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/replenishmentReport"
								class="nav-link">
									<p>Replenishment Report</p>
							</a></li> 
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/sysAuditReport"
								class="nav-link">
									<p>Audit report</p>
							</a></li>   -->
							
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/specialcustomerPurchaseReport"
								class="nav-link">
									<p>Customer(Special) Purchase Report</p>
							</a></li> -->
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales</p>
							</a></li>
								<!-- 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesInvoiceReport"
								class="nav-link">
									Sales By Invoice</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSalesNew"
								class="nav-link">
									<p>Add Sales New</p>
							</a></li>
							<sec:authorize access="hasAuthority('admin')">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalePdf"
								class="nav-link">
									<p>Sales Migration</p>
							</a></li>
							</sec:authorize> -->
						</ul></li>
					<!-- 		
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Product Rentals <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addProductRental"
							class="nav-link">
								<p>Add Rental Product</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewProductRental"
							class="nav-link">
								<p>List Rental Products</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addRentalQuote"
							class="nav-link">
								<p>Add Rental Quote</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalQuotes"
							class="nav-link">
								<p>List Rentals Quotes</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalSales"
							class="nav-link">
								<p>List Rentals Sales</p>
						</a></li>
					</ul></li>
					
				 -->
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>


						</ul></li>

					</li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Purchases <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/importPurchase"
								class="nav-link">
									<p>Import Purchase Products</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addPurchase"
								class="nav-link">
									<p>Add Purchase</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewPurchase"
								"
							class="nav-link">
									<p>View Purchases</p>
							</a></li>
						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>
						</ul></li>


					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-file"></i>
							<p>
								Settings <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addCatagory"
								class="nav-link">
									<p>Add Category</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSubCatagory"
								class="nav-link">
									<p>Add Sub-Category</p>
							</a></li>

						</ul></li>



					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</sec:authorize>
		
		<sec:authorize access="hasAuthority('aleisha')">


			<!-- Sidebar Menu -->

			<nav class="mt-2">

				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/registerMember"
								class="nav-link">

									<p>Add Customer</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-barcode"></i>
							<p>
								Products <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addProduct"
								class="nav-link">
									<p>Add Product</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetailsPage"
								class="nav-link">
									<p>List Products</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-list"></i>
							<p>
								Reports <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRegister"
								class="nav-link">
									<p>View Register</p>
							</a></li>
											<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSpecialRegister"
								class="nav-link">
									<p>View Special Register</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerReport"
								class="nav-link">
									<p>Customer Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/paymentReport"
								class="nav-link">
									<p>Payment Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesReport"
								class="nav-link">
									<p>Product Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSalesReport"
								class="nav-link">
									<p>Total Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/totalSpecialSalesReport"
								class="nav-link">
									<p>Total Special Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/profitlossReport"
								class="nav-link">
									<p>Profit Loss Report</p>
							</a></li>
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/customerPurchaseReport"
								class="nav-link">
									<p>Customer Purchase Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthsalesReport"
								class="nav-link">
									<p>Monthly Sales Report</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/monthspecialsalesReport"
								class="nav-link">
									<p>Monthly SpecialSales Report</p>
							</a></li> 
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/replenishmentReport"
								class="nav-link">
									<p>Replenishment Report</p>
							</a></li> 
								<li class="nav-item"><a
								href="${pageContext.request.contextPath}/sysAuditReport"
								class="nav-link">
									<p>Audit report</p>
							</a></li> 
							
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/specialcustomerPurchaseReport"
								class="nav-link">
									<p>Customer(Special) Purchase Report</p>
							</a></li> -->
						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales</p>
							</a></li>
						<!-- 	
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesInvoiceReport"
								class="nav-link">
									Sales By Invoice</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSalesNew"
								class="nav-link">
									<p>Add Sales New</p>
							</a></li>
							<sec:authorize access="hasAuthority('admin')">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalePdf"
								class="nav-link">
									<p>Sales Migration</p>
							</a></li>
							</sec:authorize>   -->
						</ul></li>
					<!-- 		
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Product Rentals <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addProductRental"
							class="nav-link">
								<p>Add Rental Product</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewProductRental"
							class="nav-link">
								<p>List Rental Products</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addRentalQuote"
							class="nav-link">
								<p>Add Rental Quote</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalQuotes"
							class="nav-link">
								<p>List Rentals Quotes</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalSales"
							class="nav-link">
								<p>List Rentals Sales</p>
						</a></li>
					</ul></li>
					
				 -->
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>


						</ul></li>

					</li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Purchases <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/importPurchase"
								class="nav-link">
									<p>Import Purchase Products</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addPurchase"
								class="nav-link">
									<p>Add Purchase</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewPurchase"
								"
							class="nav-link">
									<p>View Purchases</p>
							</a></li>
						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>
						</ul></li>


					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-file"></i>
							<p>
								Settings <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addCatagory"
								class="nav-link">
									<p>Add Category</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSubCatagory"
								class="nav-link">
									<p>Add Sub-Category</p>
							</a></li>

						</ul></li>



					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</sec:authorize>
		
		
		<sec:authorize access="hasAuthority('itamar')">


			<!-- Sidebar Menu -->

			<nav class="mt-2">

				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-users"></i>
							<p>
								People <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/registerMember"
								class="nav-link">

									<p>Add Customer</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewMemberDetails"
								class="nav-link">
									<p>List Customers</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addvendor"
								class="nav-link">
									<p>Add Suppliers</p>
							</a></li>

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/getVendorList"
								class="nav-link">
									<p>List Suppliers</p>
							</a></li>


						</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-barcode"></i>
							<p>
								Products <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addProduct"
								class="nav-link">
									<p>Add Product</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewProductDetailsPage"
								class="nav-link">
									<p>List Products</p>
							</a></li>
						</ul></li>
	
	
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-heart"></i>
							<p>
								Sales <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSales"
								class="nav-link">
									<p>Add Sales</p>
							</a></li>
							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSales"
								class="nav-link">
									<p>List Sales</p>
							</a></li>-->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalesNew"
								class="nav-link">
									<p>List Sales</p>
							</a></li>
						<!-- 	
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/salesInvoiceReport"
								class="nav-link">
									Sales By Invoice</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSalesNew"
								class="nav-link">
									<p>Add Sales New</p>
							</a></li>
							<sec:authorize access="hasAuthority('admin')">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewSalePdf"
								class="nav-link">
									<p>Sales Migration</p>
							</a></li>
							</sec:authorize>   -->
						</ul></li>
					<!-- 		
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Product Rentals <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addProductRental"
							class="nav-link">
								<p>Add Rental Product</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewProductRental"
							class="nav-link">
								<p>List Rental Products</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addRentalQuote"
							class="nav-link">
								<p>Add Rental Quote</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalQuotes"
							class="nav-link">
								<p>List Rentals Quotes</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalSales"
							class="nav-link">
								<p>List Rentals Sales</p>
						</a></li>
					</ul></li>
					
				 -->
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="far fa-heart"></i>
							<p>
								Quotation <i class="right fas fa-angle-down"></i>
							</p>

					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addQuotes"
								class="nav-link">
									<p>Add Quotes</p>
							</a></li>
						<!-- 	<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotes"
								class="nav-link">
									<p>List Quotes</p>
							</a></li> -->
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewQuotesNew"
								class="nav-link">
									<p>List Quotes New</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/requestQuote"
								class="nav-link">
									<p>Request Quotation</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewRequestQuote"
								class="nav-link">
									<p>View Requested Quotes</p>
							</a></li>


						</ul></li>

					</li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Purchases <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/importPurchase"
								class="nav-link">
									<p>Import Purchase Products</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addPurchase"
								class="nav-link">
									<p>Add Purchase</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewPurchase"
								"
							class="nav-link">
									<p>View Purchases</p>
							</a></li>
						</ul></li>

					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-exchange-alt"></i>
							<p>
								Returns <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturns"
								class="nav-link">
									<p>Add Returns</p>
							</a></li>
						</ul>

						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturns"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnNew"
								"
							class="nav-link">
									<p>View Returns</p>
							</a></li>
						</ul>
						<ul class="nav nav-treeview">

							<!--  <li class="nav-item"><a
								href="${pageContext.request.contextPath}/addReturnscash"
								class="nav-link">
									<p>Add Returns(Cash)</p>
							</a></li>-->
						</ul>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/viewReturnscash"
								class="nav-link">
									<p>View Returns(cash)</p>
							</a></li>
						</ul></li>


					<li class="nav-item"><a href="#" class="nav-link"> <i
							class="fas fa-file"></i>
							<p>
								Settings <i class="right fas fa-angle-down"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">

							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addCatagory"
								class="nav-link">
									<p>Add Category</p>
							</a></li>
							<li class="nav-item"><a
								href="${pageContext.request.contextPath}/addSubCatagory"
								class="nav-link">
									<p>Add Sub-Category</p>
							</a></li>

						</ul></li>



					<li class="nav-item"><a
						href="${pageContext.request.contextPath}/logout" class="nav-link">
							<p>Logout</p>
					</a></li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</sec:authorize>
		
		
		



	</div>

	<!-- /.sidebar -->
</aside>
