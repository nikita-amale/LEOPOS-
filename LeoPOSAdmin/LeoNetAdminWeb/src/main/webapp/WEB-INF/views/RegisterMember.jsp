
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row" style="margin-left: 20%;">
				<!-- left column -->
				<div class="col-md-10">
					<!-- general form elements -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Add Customer</h3>
						</div>
						<!-- /.card-header -->
						<!-- form start -->
						<form method="POST"
							action="${pageContext.request.contextPath}/memberRegiProccess"
							autocomplete="off" modelAttribute="memberDetails"
							name="memberDetails" id="memberDetails"
							enctype="multipart/form-data">
							<div class="card-body">
							
								<div class="form-group">
									<label for="exampleInputEmail1">Customer Name</label> <input
										type="text" class="form-control" name="name"
										placeholder="Enter Customer Full Name" Required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">DOB</label> <input
										type="date" class="form-control" name="DOB" id="DOB"
										placeholder="Enter Customer DOB" >
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Company</label> <input
										type="text" class="form-control" name="company"
										placeholder="Company" >
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Username</label> <input
										type="text" class="form-control" name="userName"
										placeholder="Enter User Name" Required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Contact Name</label> <input
										type="text" class="form-control" name="contactname"
										placeholder="Add Contact name" >
								</div>

								
								 <div class="form-group">
                    <label for="exampleInputPassword1">Member Main Phone number</label>
                    <input type="text" class="form-control" name="phonemain"  placeholder="Enter Member Main Phone number" Required>
                  </div> 
								<div class="form-group">
									<label for="exampleInputPassword1">Customer Whatsapp
										Number </label> <input type="number" class="form-control"
										name="phonealter"
										placeholder="Enter Customer Main Phone number">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">First Email
										</label> <input type="email" class="form-control"
										name="firstemail"
										placeholder="Enter Customer First Eamil ">
									
									
									<label for="exampleInputPassword1">Second Email
										</label> <input type="email" class="form-control"
										name="secondemail"
										placeholder="Enter Customer Second Eamil" >
									</div>
							
								<div class="form-group">
									<label for="exampleInputPassword1">Address</label> <input
										type="text" class="form-control" name="address"
										placeholder="Enter Address">
								</div>

								<div class="form-group">
									<label for="exampleInputPassword1">Customer City</label> <input
										type="text" class="form-control" name="city"
										placeholder="Enter Customer City">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">TIN Code</label> <input
										type="number" class="form-control" name="pincode"
										placeholder="Enter Pin Code" >
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Customer Type</label> <select required
										class="form-control select2bs4" name="ctype" id="ctype"
										style="width: 100%;"  >
								 	<option value="">Please Select Customer Type</option>
								 		<sec:authorize access="!hasAuthority('custom')"> 
										<option value="Special">Special</option>
											</sec:authorize>
										<option value="WholeSellers">WholeSellers</option>
										<option value="General">General</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Product Type</label> <select required
										class="form-control select2bs4" name="pricegroup" id="pricegroup"
										style="width: 100%;"  >
								 	<option value="">Please Select Customer Type</option> 
								 		<sec:authorize access="!hasAuthority('custom')">
										<option value="Special">Special</option>
											</sec:authorize>
										<option value="WholeSellers">WholeSellers</option>
										<option value="General">General</option>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Credit Facility</label> <select required
										class="form-control select2bs4" name="creditfacility" id="creditfacility"
										style="width: 100%;">
										<option value="">Please Select Credit Facility</option>
										<option value="YES">YES</option>
										<option value="NO">NO</option>
									</select>
								</div>

								<!-- /.card-body -->

								<div class="card-footer">
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
								<div class="form-group">
									<c:if test="${not empty Msg}">
										<jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
									</c:if>
								</div>
						</form>
					</div>
					<!-- /.card -->
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->	
	</section>
	<!-- /.content -->
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});
	document.getElementById('DOB').addEventListener('click', function() {
	    this.showPicker();
	});
</script>
