<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

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
							<h3 class="card-title">Purchase Details</h3>
						</div>

						<form autocomplete="off" name="purchase" method="post"
							action="${pageContext.request.contextPath}/purchase">
							<div class="card-body">
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>Select Supplier</label><font color="red">*</font> <select
											class="form-control select2bs4" name="vendorCode"
											id="vendorPojo" style="width: 100%;">
											</select>
									</div>

									<div class="col-md-6">
										<label>QTY</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip"
											name="qty" id="qty"  placeholder="Enter QTY" Required> 
									</div>
								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>US $$</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip" onkeyup="mul2();"
											name="usdollar" id="usdollar"placeholder="Enter US $$" Required> </select>
									</div>
									<div class="col-md-6">
										<label>BZD_price</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip"
											name="bzdprice" id="bzdprice"readonly  Required>
									
									</div>
								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>PercentageCost</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip" onkeyup="calcost();"
											name="percentagecost" id="percentagecost" readonly Required>
										
									</div>
									<div class="col-md-6">
										<label>COST</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip"
											name="cost" id="cost" readonly Required> 
									</div>
								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>Unit COST</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip"
											name="unitcost" id="unitcost" readonly Required>
										</required>
									</div>
								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>SellingPercentage</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip" onkeyup="calsell();"
											name="sellingpercentaget" id="sellingpercentaget"
											 Required> </required>
									</div>
								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>Selling Price</label><font color="red">*</font>
										<required> <input type="text" class="form-control input-tip"
											name="sellingprice" id="sellingprice" readonly
											Required> </required>
									</div>
									<button type="submit" onclick="addvalue()" id="purchase"
										class="btn btn-primary">Submit</button>
								</div>




							</div>
						</form>


					</div>
					<div class="col-md-12">
						<div class="fprom-group">

							<a class="btn btn-danger"
								href="<'${pageContext.request.contextPath}/admin/products/resetCsv'>">Reset</a>
							<a class="btn btn-primary"
								href="${pageContext.request.contextPath}/applypurchase">Apply
								Purchase</a>
						</div>
					</div>


				</div>
			</div>



		</div>
</div>

<!--  
<div class="table-responsive" style="font-size: 14px !important;">
	<table id="purchase"
		class="table table-bordered table-hover table-striped print-table order-table"
		width="100%" border="1" style="border-collapse: collapse !important;">
		<thead>

			<tr>
				<th>QTY</th>
				<th>US $</th>
				<th>OLDPRICE</th>
				<th>PRICE</th>
				<th>COST</th>
				<th>U COST</th>
				<th>S PERCENTAGE</th>
				<th>S PRICE</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>

		</thead>

		<tbody>
			<c:forEach var="importpurchasePojo" items="${importpurchasePojo}"
				varStatus="loop">
				<tr id="${loop.count}">
					<td>${loop.count}</td>
					<td id="qty${loop.count}">${importpurchasePojo. qty}</td>
					<td id="US $${loop.count}">${importpurchasePojo.usdollar}</td>
					<td id="OLDPRICE{loop.count}">${importpurchasePojo.cost}</td>
					<td id="PRICE${loop.count}">${importpurchasePojo.cost}</td>
					<td id="COST${loop.count}">${importpurchasePojo.cost}</td>
					<td id="U_COST${loop.count}">${importpurchasePojo.unitcost}</td>
					<td id="S_PERCENTAGE${loop.count}">${importpurchasePojo.sellingpercentaget}</td>
					<td id="S_PRICE${loop.count}">${importpurchasePojo.sellingprice}</td>
			</c:forEach>
		</tbody>
	</table>
</div>
-->
<script type="text/javascript">
	
$.ajax({
		url : '${pageContext.request.contextPath}/getVendor',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$('#vendorPojo').append(
					$("<option></option>").attr("value", "0").text("Select"));
			$.each(data, function(i, data) {
				$('#vendorPojo').append(
						'<option value="' + data.venderCode + '">'
								+ data.vendorName + '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	$("#vendorPojo").click(function() {
		var e = document.getElementById("vendorPojo");
		var text = e.options[e.selectedIndex].text;
		$('#vendorName').val(text);

	});
	function addvalue() {
		alert("Successfully saved")
	}
	
	  function mul2() {
          var dollarvalue = document.getElementById('usdollar').value;
          var quantity = document.getElementById('qty').value;
          var bzdvalue = ((dollarvalue) * 2.06).toFixed(2);
          document.getElementById('percentagecost').value = 10;

          if (!isNaN(bzdvalue)) {
              document.getElementById('bzdprice').value = bzdvalue;
          }
          var costpercent = document.getElementById('percentagecost').value;
          var costprice = ((costpercent * bzdvalue)/100) ;
          var totalcostprice = (parseFloat(costprice) + parseFloat(bzdvalue)).toFixed(2);
          var cost = ((bzdvalue) * 1.10).toFixed(2);
          var cost = totalcostprice;
          if (!isNaN(totalcostprice)) {
              document.getElementById('cost').value = totalcostprice;
          }
          var Ucost = (cost/quantity).toFixed(2);
          if (!isNaN(Ucost)) {
              document.getElementById('unitcost').value = Ucost;
          }
         
	  }
	  
	  function calcost() {
          var costpercent = document.getElementById('percentagecost').value;
          var bzprice = document.getElementById('bzdprice').value;
          var quantity = document.getElementById('qty').value;
          
          var sellingprice = ((costpercent * bzprice)/100) ;
           
          var totalprice = (parseFloat(sellingprice) + parseFloat(bzprice)).toFixed(2);
         
           if (!isNaN(totalprice)) {
              document.getElementById('cost').value = totalprice;
          }
          var Ucost = (parseFloat(totalprice)/parseFloat(quantity)).toFixed(2);
          if (!isNaN(Ucost)) {
              document.getElementById('unitcost').value = Ucost;
          }
          var percent = document.getElementById('sellingpercentaget').value;

          var percent = document.getElementById('sellingpercentaget').value;

          var sellingprice2 = ((percent * Ucost)/100) ;
          var totalsellingprice = (parseFloat(sellingprice2) + parseFloat(Ucost)).toFixed(2);
       
          if (!isNaN(totalsellingprice)) {
              document.getElementById('sellingprice').value = totalsellingprice;
          }

       }
	  
	  function calsell() {
          var percent = document.getElementById('sellingpercentaget').value;
          var bzdvalue = document.getElementById('unitcost').value;
         
        //  alert(percent);
          var sellingprice = ((percent * bzdvalue)/100) ;
           //   sellingprice = (Ucost + sellingprice).toFixed(4);
          // alert(sellingprice);
          var totalprice = (parseFloat(sellingprice) + parseFloat(bzdvalue)).toFixed(2);
         // alert(totalprice);
           if (!isNaN(totalprice)) {
              document.getElementById('sellingprice').value = totalprice;
          }
       }
</script>


</body>
</html>