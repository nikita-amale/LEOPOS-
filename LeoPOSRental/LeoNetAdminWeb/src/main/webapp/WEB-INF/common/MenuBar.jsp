<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>


<!-- Main Sidebar Container -->

<link rel="stylesheet" href="resources/css/style.css">
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="${pageContext.request.contextPath}/home" class="brand-link">
		<span class="brand-text font-weight:900">Exquisite Event Rentals
			</span> <!--  <span class="brand-text font-weight:900"> Co.Ltd.</span> -->

	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image"></div>
			<div class="info">
				<a href="#" class="d-block">${userName}</a>
			</div>
		</div>


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
						
						


					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-barcode"></i>
						<p>
							Products <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<!--  <a
							href="${pageContext.request.contextPath}/addProductDetails"
							class="nav-link">
								<p>Add Product</p>
						</a></li>-->
						<!--  <li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewProductDetails"
							class="nav-link">
								<p>List Products</p>
						</a></li>-->
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
					</ul></li>
				<!-- <li class="nav-item"><a href="#" class="nav-link"> <i
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
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/viewSales"
							class="nav-link">
								<p>List Sales</p>
						</a></li>
					</ul></li>-->
					
					<!--<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-list"></i>
						<p>
							Reports <i class="right fas fa-angle-down"></i>
						</p>
				</a></li>-->	
					
					<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Quotes<i class="right fas fa-angle-down"></i>
						</p>
				</a>	
					<ul class="nav nav-treeview">
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
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-heart"></i>
						<p>
							Rentals Sales <i class="right fas fa-angle-down"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
					<li class="nav-item"><a
							href="${pageContext.request.contextPath}/addRentalSale"
							class="nav-link">
								<p>Add Rentals Sale</p>
						</a></li>
					
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/listRentalSales"
							class="nav-link">
								<p>List Rentals Sales</p>
						</a></li>
						
					</ul></li>	
				
				
				</li>
				<!-- <li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-exchange-alt"></i>
						<p>
							Purchases <i class="right fas fa-angle-down"></i>
						</p>
				</a></li>-->
				<!--  <li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-star"></i>
						<p>
							Transfer <i class="right fas fa-angle-down"></i>
						</p>
				</a></li> -->
				<!-- <li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-random"></i>
						<p>
							Returns <i class="right fas fa-angle-down"></i>
						</p>
				</a></li>-->

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
	</div>
	<!-- /.sidebar -->
</aside>
