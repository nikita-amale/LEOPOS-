<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



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
							<h3 class="card-title">Import Purchase</h3>
						</div>
						<form method="POST"
							action="${pageContext.request.contextPath}/upload"
							autocomplete="off" modelAttribute="purchasefilePojo"
							name="upload" enctype="multipart/form-data">
							<div class="card-body">
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>Addpdf(upload the pdf just before doing apply
											purchase)</label> <input id="fileupload" type="file"
											name="fileupload" />
										<button id="upload-button" onclick="uploadFile()">
											Upload</button>

									</div>
								</div>

							</div>
						</form>

						<form autocomplete="off" name="purchase" id="itemForm"
							enctype="multipart/form-data	">
							<div class="card-body">
								<div class="form-group" style="display: flex">
									<c:forEach var="importPurchasePojo"
										items="${importPurchasePojo}" varStatus="loop">
										<c:if test="${importPurchasePojo.applied == '0'}">
											<input type="hidden" class="form-control" id="supplier"
												name="supplier" value="${importPurchasePojo.supplier}">
										</c:if>

									</c:forEach>
									<div class="col-md-6">
										<label>Select Supplier</label><font color="red">*</font> <select
											class="form-control select2bs4" name="vendorname"
											id="supplierPojo" style="width: 100%;">
										</select>
									</div>
									<div class="col-md-6">
										<label>Select Product</label><font color="red">*</font> <select
											class="form-control select2bs4" name="productCode"
											id="productDetailsPojo" style="width: 100%;">
										</select>
									</div>

								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>QTY</label><font color="red">*</font> <input
											type="text" class="form-control input-tip" onkeyup="mul2();"
											name="qty" id="qty" placeholder="Enter QTY">
									</div>
									<div class="col-md-6">
										<label>US $$</label><font color="red">*</font> <input
											type="text" class="form-control input-tip" onkeyup="mul2();"
											name="usdollar" id="usdollar" placeholder="Enter US $$">
									</div>

								</div>
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>BZD_price</label><font color="red">*</font> <input
											type="text" class="form-control input-tip" name="bzdprice"
											id="bzdprice" readonly>
									</div>
									<div class="col-md-6">
										<label>PercentageCost</label><font color="red">*</font>
										<c:forEach var="importPurchasePojo"
											items="${importPurchasePojo}" varStatus="loop">
											<c:if test="${loop.last}">
												<c:if test="${importPurchasePojo.applied == '0'}">
													<input type="text" class="form-control input-tip"
														onkeyup="calcost();" name="percentagecost"
														id="percentagecost"
														value="${importPurchasePojo.percentagecost}">
												</c:if>
												<c:if test="${importPurchasePojo.applied != '0'}">
													<input type="text" class="form-control input-tip"
														onkeyup="calcost();" name="percentagecost"
														id="percentagecost">
												</c:if>
												
											</c:if>
											
										</c:forEach>
									</div>



								</div>
								<div class="form-group" style="display: flex">

									<div class="col-md-6">
										<label>COST</label><font color="red">*</font> <input
											type="text" class="form-control input-tip" name="cost"
											id="cost" readonly>
									</div>
									<div class="col-md-6">
										<label>Unit COST</label><font color="red">*</font> <input
											type="text" class="form-control input-tip" name="unitcost"
											id="unitcost" readonly>
									</div>


								</div>

								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>SellingPercentage</label><font color="red">*</font> <input
											type="text" class="form-control input-tip"
											onkeyup="calsell();" name="sellingpercentage"
											id="sellingpercentage">

									</div>
									<div class="col-md-6">
										<label>Selling Price</label><font color="red">*</font> <input
											type="text" class="form-control input-tip"
											name="sellingprice" id="sellingprice" readonly>
									</div>

								</div>


								<button type="button" onclick="addPurchasecsv()"
									class="btn btn-primary">Submit</button>
								<button class="btn btn-primary" onclick="reset()">Reset</button>

								<a class="btn btn-primary" onclick=" applyPurchase()">Apply
									Purchase</a>
									<input type="button" id="btnExport"
									class="btn btn-primary btn-font-size" onclick="fnExcelReport()"
									value="Export To Excel">

							</div>

							<div class="card-body">
								<table id="purchasetable"
									class="table table-bordered table-hover table-striped print-table order-table"
									width="100%" border="1"
									style="border-collapse: collapse !important;">
									<thead>

										<tr>
											<th hidden>Purchase Id</th>
											<th>Product Code</th>
											<th>Product Name</th>
											<th>MPN</th>
											<th>QTY</th>
											<th>US $</th>
											<th>BZ $</th>
											<th>PERCENT COST</th>
											<th>COST</th>
											<th>Unit COST</th>
											<th>Old Price</th>
											<th>S PERCENTAGE</th>
											<th>S PRICE</th>
											<th>EDIT</th>
											<th>DELETE</th>
										</tr>

									</thead>

									<tbody>
										<c:forEach var="importPurchasePojo"
											items="${importPurchasePojo}" varStatus="loop">
											<c:if test="${importPurchasePojo.applied == '0'}">
												<c:set var="cost" value="${importPurchasePojo.cost}" />
												<c:set var="sprice"
													value="${importPurchasePojo.sellingprice}" />
												<c:set var="bzdprice" value="${importPurchasePojo.bzdprice}" />
												<tr id="${loop.count}"
													data-id="${importPurchasePojo.purchaseId}">
													<td hidden id="purchaseId${loop.count}">${importPurchasePojo.purchaseId}</td>
													<td id="productid${loop.count}">${importPurchasePojo.productId}</td>
													<td id="productname${loop.count}">${importPurchasePojo.productName}</td>
													<td id="mpn${loop.count}">${importPurchasePojo.mpn}</td>

													<td id="qty${loop.count}">${importPurchasePojo.qty}</td>
													<td id="US$ ${loop.count}">${importPurchasePojo.usdollar}</td>
													<td id="BZDPRICE{loop.count}"><fmt:formatNumber
															pattern="0.00" value="${bzdprice}" /></td>
													<td id="PERCOST${loop.count}">${importPurchasePojo.percentagecost}</td>
													<td id="COST${loop.count}"><fmt:formatNumber
															pattern="0.00" value="${cost}" /></td>
													<td id="U_COST${loop.count}">${importPurchasePojo.unitcost}</td>
													<td id="Oldprice${loop.count}">${importPurchasePojo.oldprice}</td>
													<td id="S_PERCENTAGE${loop.count}" contenteditable="true"
														onkeyup="javascript:editcalsell1(event,${loop.count});">${importPurchasePojo.sellingpercentage}</td>
													<td id="S_PRICE${loop.count}"><fmt:formatNumber
															pattern="0.00" value="${sprice}" /></td>
													<td>
														<button type="button" class="btn btn-primary"
															data-toggle="modal" data-target="#modal-lg" id="editBtn"
															onclick="EditDetails(${loop.count},${importPurchasePojo.purchaseId})">Edit</button>
													</td>
													<td>
														<button type="button" class="btn btn-primary"
															onclick="deleteDetails(${loop.count},${importPurchasePojo.purchaseId})">Delete</button>
													</td>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>


						</form>


					</div>
				</div>
			</div>
		</div>




		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card"></div>
				</div>
			</div>
		</div>



	</section>
</div>

<div class="modal fade" id="modal-lg">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Edit Import Purchase</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- general form elements -->

				<form method="POST"
					action="${pageContext.request.contextPath}/updateImportPurchase"
					autocomplete="off" modelAttribute="updateimportPurchase"
					name="updateimportPurchase">
					<div class="card-body">
						<input type="hidden" class="form-control" name="purchaseId"
							id="purchaseid">

						<div class="form-group">
							<label>Select Product</label><font color="red">*</font> <select
								class="form-control select2bs4" name="productId"
								id="productDetails" style="width: 100%;">
							</select>
						</div>

						<div class="form-group">
							<label for="quantity">Qty</label><font color="red">*</font> <input
								type="text" class="form-control" name="qty" id="quantity"
								placeholder="Qty">
						</div>

						<div class="form-group">
							<label for="US$">US $$</label><font color="red">*</font><input
								type="text" class="form-control input-tip" onkeyup="mul();"
								name="usdollar" id="USD" placeholder="Enter US $$">
						</div>

						<div class="form-group">
							<label>BZD_price</label><font color="red">*</font> <input
								type="text" class="form-control input-tip" name="bzdprice"
								id="bzdPrice" readonly>
						</div>

						<div class="form-group">
							<label>PercentageCost</label><font color="red">*</font> <input
								type="text" class="form-control input-tip" onkeyup="calcost1();"
								name="percentagecost" id="percentage">
						</div>

						<div class="form-group">
							<label>COST</label><font color="red">*</font> <input type="text"
								class="form-control input-tip" name="cost" id="editcost"
								readonly>
						</div>

						<div class="form-group">
							<label>Unit COST</label><font color="red">*</font> <input
								type="text" class="form-control input-tip" name="unitcost"
								id="unit" readonly>
						</div>

						<div class="form-group">
							<label for="S_PERCENTAGE">Selling Percentage</label><font
								color="red">*</font> <input type="text" class="form-control"
								name="sellingpercentage" id="S_PERCENTAGE"
								placeholder="Selling Percentage" onkeyup="calsell1();">
						</div>

						<div class="form-group">
							<label>Selling Price</label><font color="red">*</font> <input
								type="text" class="form-control input-tip" name="sellingprice"
								id="sellingPrice" readonly>
						</div>

					</div>
					<div class="modal-footer justify-content-between">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>


<!-- Select2 JS -->
<script
	src="https://cdn.tiny.cloud/1/no-api-key/tinymce/6/tinymce.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

<script type="text/javascript">

var productDetails;
// ************ Windows on load function Starts ***********************
window.onload = function() {
	var supplier = $('#supplier').val();
	//alert(supplier);
	//$("#supplierPojo option:contains(Fimex)").attr('selected', true);
	//$('#supplierPojo').attr('selected', 'selected');
   $.ajax({
		
		url : '${pageContext.request.contextPath}/getVendor',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$('#supplierPojo').append(
					$("<option></option>").attr("value", "0").text("Select"));
			$.each(data, function(i, data) {
				if (supplier == data.vendorName){
				$('#supplierPojo').append(
						'<option value="' + data.venderCode +'"'+'data-vendorname="'+data.vendorname+'"selected>'
								+ data.vendorName + '</option>');
			}else {
				
				$('#supplierPojo').append(
						'<option value="' + data.venderCode +'  ">'
								+ data.vendorName + '</option>');
			}
			});

	},
	error : function(error) {
		console.log(`Error ${error}`);
	}

});
}
// ************ Windows on load function ends ***********************

async function uploadFile() {
	alert("Upload pdf");
	var flag=0;
	/*var fileupload= document.getElementById('fileupload').value;
	alert(file);
	if(fileupload==''){
		alert("Please enter a pdf");
		'fileupload'.focus();
	}*/
  let formData = new FormData(); 
  formData.append("file", fileupload.files[0]);
  let response = await fetch('/upload', {
    method: "POST", 
    body: formData
  }); 
}

function EditDetails(count,purchaseId){
	var purchaseData;
	$.ajax({
		url : '${pageContext.request.contextPath}/getPurchase/'+purchaseId,
		type : "POST",
		success : function(res) {
			$('#productDetails').append(
					$("<option></option>").attr("value", "1").text("Select"));
			
			$.each(productDetails, function(key, data) {
				var selected = '';
				if(data.productId == res.productId){
					selected = 'selected';
				}
				$('#productDetails').append(
						"<option data-price='"+data.price+ + "' data-cost='" + data.cost + "' value=' "+ data.productId + "' "+selected+">"
								+ data.name + '</option>')
			});
			
			 $("#quantity").val(res.qty);
		     $("#purchaseid").val(purchaseId);
		     $("#USD").val(res.usdollar);
		     $("#bzdPrice").val(res.bzdprice);
		     $("#percentage").val(res.percentagecost);
		     $("#editcost").val(res.cost);
		     $("#unit").val(res.unitcost);
		     $("#editcost").val(res.cost);
		     $("#S_PERCENTAGE").val(res.sellingpercentage);				
		     $("#sellingPrice").val(res.sellingprice);				
		}

	});

}
	  
	  
function deleteDetails(count, purchaseid){
	  var x = confirm("Are you sure you want to delete?");
    if (x) {
  	  $.ajax({
  			url : '${pageContext.request.contextPath}/deleteImportPurchase',
  			type : "POST",
  			dataType : "json",
  			data:{id:purchaseid},
  			success : function(data) {
  	 			alertify
  				  .alert(data.msgDescr, function(){
  					reset();
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



function addPurchasecsv() {
   
    
	// alert("Recahed here");
	 var supplier = $('#supplierPojo :selected').text();
	 var supplierval = $('#supplierPojo :selected').val();
	 var productId = $('#productDetailsPojo :selected').val();
	 var extractedProductName = $('#productDetailsPojo :selected').text();
	 var productName = extractedProductName.split(' - ')[0];

	
	 var qty = document.getElementById('qty').value;

	 var usdollar = document.getElementById('usdollar').value;
	
	 var bzdprice = document.getElementById('bzdprice').value;
	 var percentagecost = document.getElementById('percentagecost').value;
	 var cost = document.getElementById('cost').value;
	 var unitcost = document.getElementById('unitcost').value;
	 var sellingpercentage = document.getElementById('sellingpercentage').value;
	 var sellingprice = document.getElementById('sellingprice').value;
	 
	 
	 
	$.ajax({
		url : "${pageContext.request.contextPath}/importPurchase",
		type : 'POST',
		"dataType" : "json",
		 async : false,
		"contentType" : "application/json; charset=utf-8",
		"data" : JSON.stringify({

			"qty" : qty,
			"productId" : productId,
			"productName" : productName,
			"usdollar" : usdollar,
			"bzdprice" : bzdprice,
			"percentagecost" : percentagecost,
			"cost" : cost,
			"unitcost" : unitcost,
			"sellingpercentage" : sellingpercentage,
			"sellingprice" : sellingprice,
			"supplier" : supplier

		}),
		/*"success":function(data){
			alertify
			  .alert(data.msgDescr, function(){
				  reset();
				 $('#supplierPojo').attr('selected', supplier);
			  });
		}*/
	});
	// alert(supplier);
	//$("#productDetailsPojo").empty();
	 //$("#productDetailsPojo").val('');
	 //window.location.replace('${pageContext.request.contextPath}/importPurchase');
	reset();
	// document.getElementById("supplierPojo").text = supplier;
	// reset();
	//$("#supplierPojo option:contains(Fimex)").attr('selected', true);
}


function applyPurchase(){
	
	var supplierId = $('#supplierPojo :selected').val();
	
	 var _table = document.getElementById("purchasetable");
	 var _trLength = _table.getElementsByTagName("tr").length;
	 var _jsonData = [];
	 var _obj = {};

	 var _htmlToJSON = function(index){
	     var _tr = _table.getElementsByTagName("tr")[index];
	     var _td = _tr.getElementsByTagName("td");
	  //   var quantity = ($(_data[4]).text().trim()) ? parseInt($(this).text().trim()) : $(this).text().trim();
	 //    alert(quantity);
	     var _arr = [].map.call( _td, function( td ) {
	         return td.innerHTML;
	     }).join( ',' );
	     var _data = _arr.split(",");
	     
	     _obj = {
	    	  purchaseId   : _data[0]
	         ,qty   	   : _data[2]
	     	 ,bzdprice     : _data[4]
	    	 ,productId    : _data[0]
	    	 ,productName  : _data[1]
	         ,usdollar     : _data[3]
	     	 ,percentagecost    : _data[5]
         	 ,cost         : _data[6]
    	 	 ,unitcost	   : _data[7]
	    	 ,sellingpercentage     : _data[8]
	 		 ,sellingprice  : _data[9]
	 
	    	 
	     };
	     
	     _jsonData.push(_obj);
	     
	 };

	 
	 for(var i = 1; i < _trLength; i++){
	     _htmlToJSON(i);
	 }
	 console.log("html to JSON",_jsonData);
	 
	 var form = $('#itemForm')[0];
	 var data = new FormData(form);
	 var myJSON = JSON.stringify(_jsonData);
	 myJSON = myJSON.split(']}]"}').join(']}]}');
	 myJSON = myJSON.replace(/\\/g, "");
	 myJSON = myJSON.split('"[{').join('[{');
	 myJSON = myJSON.split('"}]"').join('"}]');
	 console.log("myJSON======" + myJSON);

	 data.set("myJSON", "{\"importpurchasePojo\":" + myJSON + "}");

	 
	 
	$.ajax({
		url : "${pageContext.request.contextPath}/applypurchase",
		type : 'POST',
		"dataType" : "json",
		 async : false,
		"contentType" : "application/json; charset=utf-8",
		crossDomain : true,
		data : myJSON,
		"success" : function(response) {
			alertify.alert(response.msgDescr, function(){
				  reset();
			  }); 
		}
	});
	
}



	
	
	 $("#supplierPojo").select2();
	 $("#supplierPojo option:contains(Fimex)").attr('selected', true);

	$("#supplierPojo").click(function() {
		var e = document.getElementById("supplierPojo");
		var text = e.options[e.selectedIndex].text;
		$('#vendorName').val(text);

	});

	$.ajax({
		url : '${pageContext.request.contextPath}/getProducts',
		type : "GET",
		dataType : "json",
		success : function(data) {
		   productDetails = data;
			$('#productDetailsPojo').append(
					$("<option></option>").attr("value", "1").text("Select"));
			$.each(data, function(i, data) {
				$('#productDetailsPojo').append(
						'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-promotion="' + data.promotion + 
						 '" data-cost="' + data.cost + '" value="' + data.productId + '">'+ data.name + ' - ' + data.cf1 + ' - ' + data.code +  '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	 $("#productDetailsPojo").select2();
		

	 


	function mul2() {
		
		var dollarvalue = document.getElementById('usdollar').value;
		var quantity = document.getElementById('qty').value;
		var bzdvalue = ((dollarvalue) * 2.06).toFixed(2);
		//var percost =document.getElementById('percentagecost').value;
				
		//document.getElementById('percentagecost').value = 16.34;

		if (!isNaN(bzdvalue)) {
			document.getElementById('bzdprice').value = bzdvalue;
		}
		var costpercent = document.getElementById('percentagecost').value;
		var costprice = ((costpercent * bzdvalue) / 100);
		var totalcostprice = (parseFloat(costprice) + parseFloat(bzdvalue))
				.toFixed(2);
		var cost = ((bzdvalue) * 1.10).toFixed(2);
		var cost = totalcostprice;
		if (!isNaN(totalcostprice)) {
			document.getElementById('cost').value = totalcostprice;
		}
		var Ucost = (cost / quantity).toFixed(2);
		if (!isNaN(Ucost)) {
			document.getElementById('unitcost').value = Ucost;
		}
		
		var oldcost =   $('#productDetailsPojo :selected').data('cost');
		var sellingprice = $('#productDetailsPojo :selected').data('price');
		
	//	alert(oldcost);
	//	alert(sellingprice);
		
		var sellingpercentage = (((sellingprice - oldcost ) / oldcost)*100).toFixed(2);
	//	alert(sellingpercentage);
		
		if (!isNaN(sellingpercentage)) {
			document.getElementById('sellingpercentage').value = sellingpercentage;
		}
		
		var sellingprice = ((sellingpercentage * Ucost) / 100);
		//   sellingprice = (Ucost + sellingprice).toFixed(4);
		// alert(sellingprice);
		var totalprice = (parseFloat(sellingprice) + parseFloat(Ucost))
				.toFixed(2);
		// alert(totalprice);
		if (!isNaN(totalprice)) {
			document.getElementById('sellingprice').value = totalprice;
		}
		
		
	}

	function calcost() {
		//alert("Reached");
		var costpercent = document.getElementById('percentagecost').value;
		var bzprice = document.getElementById('bzdprice').value;
		var quantity = document.getElementById('qty').value;

		var sellingprice = ((costpercent * bzprice) / 100);

		var totalprice = (parseFloat(sellingprice) + parseFloat(bzprice))
				.toFixed(2);

		if (!isNaN(totalprice)) {
			document.getElementById('cost').value = totalprice;
		}
		var Ucost = (parseFloat(totalprice) / parseFloat(quantity)).toFixed(2);
		if (!isNaN(Ucost)) {
			document.getElementById('unitcost').value = Ucost;
		}
		var percent = document.getElementById('sellingpercentage').value;

		//var percent = document.getElementById('sellingpercentage').value;
		

		var sellingprice2 = ((percent * Ucost) / 100);
		var totalsellingprice = (parseFloat(sellingprice2) + parseFloat(Ucost))
				.toFixed(2);
		
		if (!isNaN(totalsellingprice)) {
			document.getElementById('sellingprice').value = totalsellingprice;
		}
		
		
		
	}

	function calsell() {
		var percent = document.getElementById('sellingpercentage').value;
		var bzdvalue = document.getElementById('unitcost').value;

		//  alert(percent);
		var sellingprice = ((percent * bzdvalue) / 100);
		//   sellingprice = (Ucost + sellingprice).toFixed(4);
		// alert(sellingprice);
		var totalprice = (parseFloat(sellingprice) + parseFloat(bzdvalue))
				.toFixed(2);
		// alert(totalprice);
		if (!isNaN(totalprice)) {
			document.getElementById('sellingprice').value = totalprice;
		}
	}
	
	function reset(){
		window.location.reload(true);	
	}
	
	function mul() {
		var dollarvalue = document.getElementById('USD').value;
		var quantity = document.getElementById('quantity').value;
		var bzdvalue = ((dollarvalue) * 2.06).toFixed(2);
		document.getElementById('percentage').value = 7.8;

		if (!isNaN(bzdvalue)) {
			document.getElementById('bzdPrice').value = bzdvalue;
		}
		var costpercent = document.getElementById('percentage').value;
		var costprice = ((costpercent * bzdvalue) / 100);
		var totalcostprice = (parseFloat(costprice) + parseFloat(bzdvalue))
				.toFixed(2);
		var cost = ((bzdvalue) * 1.10).toFixed(2);
		var cost = totalcostprice;
		if (!isNaN(totalcostprice)) {
			document.getElementById('editcost').value = totalcostprice;
		}
		var Ucost = (cost / quantity).toFixed(2);
		if (!isNaN(Ucost)) {
			document.getElementById('unit').value = Ucost;
		}
		
		
	}

	function calcost1() {
		var costpercent = document.getElementById('percentage').value;
		var bzprice = document.getElementById('bzdPrice').value;
		var quantity = document.getElementById('quantity').value;

		var sellingprice = ((costpercent * bzprice) / 100);

		var totalprice = (parseFloat(sellingprice) + parseFloat(bzprice))
				.toFixed(2);

		if (!isNaN(totalprice)) {
			document.getElementById('editcost').value = totalprice;
		}
		var Ucost = (parseFloat(totalprice) / parseFloat(quantity)).toFixed(2);
		if (!isNaN(Ucost)) {
			document.getElementById('unit').value = Ucost;
		}
		var percent = document.getElementById('S_PERCENTAGE').value;

		var sellingprice2 = ((percent * Ucost) / 100);
		var totalsellingprice = (parseFloat(sellingprice2) + parseFloat(Ucost))
				.toFixed(2);

		if (!isNaN(totalsellingprice)) {
			document.getElementById('sellingPrice').value = totalsellingprice;
		}

	}
	
	function calsell1() {
		var percent = document.getElementById('S_PERCENTAGE').value;
		var bzdvalue = document.getElementById('unit').value;

		//  alert(percent);
		var sellingprice = ((percent * bzdvalue) / 100);
		//   sellingprice = (Ucost + sellingprice).toFixed(4);
		// alert(sellingprice);
		var totalprice = (parseFloat(sellingprice) + parseFloat(bzdvalue))
				.toFixed(2);
		// alert(totalprice);
		if (!isNaN(totalprice)) {
			document.getElementById('sellingPrice').value = totalprice;
		}
	}
	
	function editcalsell1(event,loopcount) {
		  var percent = $('#S_PERCENTAGE' + loopcount).html();
		  var bzdvalue=$('#U_COST' + loopcount).html();
		  var   purchaseId=$('#purchaseId' + loopcount).html();

		 
		 // var id=$('#id' + loopcount).html();
		// alert(loopcount);
		//var percent = document.getElementById('S_PERCENTAGE').value;
	//	var bzdvalue = document.getElementById('unit').value;
		//alert(bzdvalue);

		//  alert(percent);
		var sellingprice = ((percent * bzdvalue) / 100);
		//   sellingprice = (Ucost + sellingprice).toFixed(4);
		// alert(sellingprice);
		var totalprice = (parseFloat(sellingprice) + parseFloat(bzdvalue))
				.toFixed(2);
		//alert(totalprice);
		// alert(totalprice);
		if (!isNaN(totalprice)) {
			$('#S_PRICE' + loopcount).html(totalprice);
			//document.getElementById('sellingPrice').value = totalprice;
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/editsellingpercentage',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				purchaseId   : purchaseId
				,sellingpercentage     :percent
	 		 ,sellingprice  : totalprice
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	}
	
	
	function fnExcelReport() {
	    var htmls = "";
	    var uri = 'data:application/vnd.ms-excel;base64,';
	    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>';
	    var base64 = function(s) {
	        return window.btoa(unescape(encodeURIComponent(s)))
	    };

	    var format = function(s, c) {
	        return s.replace(/{(\w+)}/g, function(m, p) {
	            return c[p];
	        })
	    };

	    var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
	    var tab = document.getElementById('purchasetable');

	    for (var j = 0; j < tab.rows.length; j++) {
	        tab_text += "<tr>";
	        for (var k = 0; k < tab.rows[j].cells.length - 2; k++) { // Exclude last 2 columns
	            tab_text += tab.rows[j].cells[k].outerHTML;
	        }
	        tab_text += "</tr>";
	    }

	    tab_text += "</table>";
	    tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
	    tab_text = tab_text.replace(/<img[^>]*>/gi, "");
	    tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");

	    var ctx = {
	        worksheet: 'Worksheet',
	        table: tab_text
	    };

	    var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth() + 1;
	    var yyyy = today.getFullYear();

	    if (dd < 10) {
	        dd = '0' + dd;
	    }

	    if (mm < 10) {
	        mm = '0' + mm;
	    }
	    var link = document.createElement("a");
	    document.body.appendChild(link);
	    link.download = "ImportPurchase Report " + yyyy + "-" + mm + "-" + dd + ".xls";
	    link.href = uri + base64(format(template, ctx));
	    link.click();
	}

	
</script>


</body>
</html>