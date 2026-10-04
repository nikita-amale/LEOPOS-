<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />

<style>
tbody, thead {
	display: contents;
}

tr {
	display: table-row-group;
}

td.row {
	display: table-row;
	/* background: rgb(200 197 255 / 63%); */
}

td.row div {
	display: table-cell;
}
</style>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



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
				<div class="col-md-10" style="padding-top: 40px">
					<!-- general form elements -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Add Purchase</h3>
						</div>
						
						<!-- /.card-header -->
						<!-- form start 
            <form  method="POST" action="${pageContext.request.contextPath}/addPurchase" autocomplete="off" name="purchase" id="purchase" enctype="multipart/form-data">
            -->
						<form autocomplete="off" name="purchase" id="itemForm"
							enctype="multipart/form-data">
							<div class="card-body">
							<c:forEach var="purchasePojo"
											items="${purchasePojo}" varStatus="loop">
									
											<c:if test="${purchasePojo.file == '0'}">
									<input type="hidden" class="form-control" id="supplier"
						
									name="supplier" value="${purchasePojo.suppliername}">
									</c:if>
									</c:forEach>
								
									


								<div class="form-group">
									<label>Select Supplier</label><font color="red">*</font> <select
										class="form-control select2bs4" name="vendorCode"
										id="vendorPojo" style="width: 100%;">
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Select Product</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="productCode" id="productDetailsPojo" Required>
									</select>
								</div>

								<!--<div class="form-group">
							<label>COST</label><font color="red">*</font> <input
							type="text" class="form-control input-tip" name="unitcost"
							id="unitcost">
							</div>
							<div class="form-group">
							<label>Price</label><font color="red">*</font> <input
							type="text" class="form-control input-tip" name="sellingprice"
							id="sellingPrice">
							</div> -->


								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>
									<button class="btn btn-primary" onclick="reset()">Reset</button>

								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="purchasetable"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Net Unit Cost</th>
												<th>Quantity</th>
												<th>Sub Total (BZD )</th>
												<th>Delete</th>

											</tr>


										</thead>
										<tbody>

											<c:forEach var="purchasePojo" items="${purchasePojo}"
												varStatus="loop">
												<c:if test="${purchasePojo.file == 0}">
													<tr id='${loop.count}'>
														<td>${loop.count}</td>
														<td id="productid${loop.count}">${purchasePojo.product_id}</td>
														<td id="purchaseId${loop.count}">${purchasePojo.product_name}</td>
														<td id="price${loop.count}" contenteditable="true"
															onkeyup="javascript:dotest(event,${loop.count});">${purchasePojo.sellingprice}</td>
														<td id="quantity${loop.count}" contenteditable="true"
															onkeyup="javascript:dotest(event,${loop.count});">${purchasePojo.quantity}</td>
														<td id="subtotal${loop.count}" contenteditable="true"
															onkeyup="javascript:unitcal(event,${loop.count});">${purchasePojo.unitcost}</td>
														<td>
															<button type="button" class="btn btn-primary"
																onclick="deleteItems(${loop.count})">Delete</button>
														</td>

													</tr>

												</c:if>

											</c:forEach>

										</tbody>
										<!-- <tfoot>
											<div class="row-1">
												<td colspan=4>Total</td>
												<td id="qTotal"></td>
												<td id="gTotal"></td>
											</div>
											<div class="row-1">
												<td colspan=5>Tax</td>
												<td id="taxTotal"></td>
											</div>
											<div class="row-1">	
												<td colspan=5>Grand Total</td>
												<td id="tTotal"></td>
											</div>
										</tfoot> -->
										<tfoot>
											<td class="row">
												<div class="text-center">
													<Strong>Total</Strong>
												</div>
												<div class="text-left pr-3" id="qTotal"></div>
												<div class="text-right" id="gTotal"></div>
											</td>

										</tfoot>

									</table>
								</div>


								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
										placeholder="Enter Note">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Tax Required</label> <input
										type="checkbox" id="taxrate" checked="true">
								</div>

							</div>
							<!-- /.card-body -->

							<div class="card-footer">



								<button type="button" class="btn btn-primary"
									onclick="getPurchase()">Submit</button>
							</div>
							<div class="form-group">
								<c:if test="${not empty Msg}">
									<jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
								</c:if>
							</div>
						</form>
						<form method="POST"
							action="${pageContext.request.contextPath}/uploadfile"
							autocomplete="off" modelAttribute="purchasefilePojo"
							name="upload" enctype="multipart/form-data">
							<div class="card-body">
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<input id="fileupload" type="file" name="fileupload" />
										<button id="upload-button" onclick="uploadFile()">
											Upload</button>

									</div>
								</div>

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

<!-- Select2 JS -->
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

<script type="text/javascript">
window.onload = function() {
	var supplier = $('#supplier').val();
	 $.ajax({
			
			url : '${pageContext.request.contextPath}/getVendor',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				$('#vendorPojo').append(
						$("<option></option>").attr("value", "0").text("Select"));
				$.each(data, function(i, data) {
					if (supplier == data.vendorName){
					$('#vendorPojo').append(
							'<option value="' + data.venderCode +'"'+'data-vendorname="'+data.vendorname+'"selected>'
										+ data.vendorName + '</option>');
					
				}else {
					
					$('#vendorPojo').append(
							'<option value="' + data.venderCode +'  ">'
									+ data.vendorName + '</option>');
				}
				});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	CalculateTotal();
	
}
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
  let response = await fetch('/uploadfile', {
    method: "POST", 
    body: formData
  }); 
  getPurchasebefore();
}


	$(document).ready(function(){

		// Initialize select2
		  $("#customerPojo").select2();
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getProducts',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				$('#productDetailsPojo').append(
						$("<option></option>").attr("value", "0").text("Select"));
				$.each(data, function(i, data) {
					$('#productDetailsPojo').append(
							'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-promotion="' + data.promotion + '"data-cost="'+data.cost+
							'" value="' + data.productId + '">'	+ data.name + ' - ' + data.cf1 + ' - ' + data.code + '</option>');
				});

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	 
	  // Initialize select2
	  $("#productDetailsPojo").select2();


	}); 

	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var productList = [];
	
	
	// New GetSales -- making JSON from table elements---when file is uploaded
	function getPurchasebefore() {
		 
		 
		// var customerId = $('#customerPojo :selected').val();
		var supplier = $('#vendorPojo :selected').text();
		 var note = $('#note').text();
		 var taxrate = document.getElementById("taxrate");
		 var file =$('#fileupload ').val();
		 if(file==""){
			 alert("Please Upload the file");
		 }else{
			// uploadFile();
		 var tax="YES";
		  if (taxrate.checked){
		     //   alert("checked") ;
		        tax ="YES";
		    }else{
		    	tax ="NO";
		        alert("You didn't check it! Let me check it for you.")
		    }
		 
		
		 
		// alert(ctype);
		 
		 var _table = document.getElementById("purchasetable");
		 var _trLength =_table.getElementsByTagName("tr").length -1;
		 var _jsonData = [];
		 var _obj = {};

		 var _htmlToJSON = function(index){
				var _tr = _table.getElementsByTagName("tr")[index];
				var _td = _tr.getElementsByTagName("td");
		  //   var quantity = ($(_data[4]).text().trim()) ? parseInt($(this).text().trim()) : $(this).text().trim();
		 //    alert(quantity);
		     var _arr = [].map.call(_td, function(td) {
		         return td.innerHTML;
		     }).join( ',' );
		     var _data = _arr.split(",");
		     
		     _obj = {
		    	  productId     : _data[1]
		         ,productName   : _data[2]
		     	 ,price         : _data[3]
		         ,quantity      : _data[4]
		    	 , note			: note
		    	 ,tax           :tax
		    	 ,subtotal      :_data[5]
		    	,unitname : supplier
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
		// myJSON = myJSON.replace(/\\/g, "");
		 myJSON = myJSON.split('"[{').join('[{');
		 myJSON = myJSON.split('"}]"').join('"}]');
		 console.log("myJSON======" + myJSON);

		 data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

	//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");
	
	  
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addPurchaseBeforSubmit',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success :function(data) {
  	 			//alertify
				 // .alert(data.msgDescr, function(){
					// location.reload();
					 window.location.replace('${pageContext.request.contextPath}/addPurchase');
					// location.reload();
			//	  }); 
  	 			//location.reload();
			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		// alert("Sale added Succesfully !");
	
	  
	   
		$("#purchasetable  tbody").empty();
		 document.getElementById("note").value="";
		 $("#gTotal").html(0.00);
         $("#qTotal").html(0.00);
         $("#taxTotal").html(0.00);
         $("#tTotal").html(0.00);
         $("#productDetailsPojo").val('');
         $("#productDetailsPojo").prop('selectedIndex',0);
         $('#productDetailsPojo').attr('selected', 'selected');
        // $('#vendorPojo').attr('selected', 'selected');
 
         
         //window.location.replace('${pageContext.request.contextPath}/viewPurchase');
         } 
	}
	
	

	// New GetSales -- making JSON from table elements
	function getPurchase() {
		 
		 
		// var customerId = $('#customerPojo :selected').val();
		var supplier = $('#vendorPojo :selected').text();
		 var note = $('#note').text();
		 var taxrate = document.getElementById("taxrate");
	
		
		 var tax="YES";
		  if (taxrate.checked){
		     //   alert("checked") ;
		        tax ="YES";
		    }else{
		    	tax ="NO";
		        alert("You didn't check it! Let me check it for you.")
		    }
		 
		
		 
		// alert(ctype);
		 
		 var _table = document.getElementById("purchasetable");
		 var _trLength =_table.getElementsByTagName("tr").length -1;
		 var _jsonData = [];
		 var _obj = {};

		 var _htmlToJSON = function(index){
				var _tr = _table.getElementsByTagName("tr")[index];
				var _td = _tr.getElementsByTagName("td");
		  //   var quantity = ($(_data[4]).text().trim()) ? parseInt($(this).text().trim()) : $(this).text().trim();
		 //    alert(quantity);
		     var _arr = [].map.call(_td, function(td) {
		         return td.innerHTML;
		     }).join( ',' );
		     var _data = _arr.split(",");
		     
		     _obj = {
		    	  productId     : _data[1]
		         ,productName   : _data[2]
		     	 ,price         : _data[3]
		         ,quantity      : _data[4]
		    	 , note			: note
		    	 ,tax           :tax
		    	 ,subtotal      :_data[5]
		    	,unitname : supplier
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
		// myJSON = myJSON.replace(/\\/g, "");
		 myJSON = myJSON.split('"[{').join('[{');
		 myJSON = myJSON.split('"}]"').join('"}]');
		 console.log("myJSON======" + myJSON);

		 data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

	//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");
	
	  
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addPurchase',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success :function(data) {
  	 			alertify
				  .alert(data.msgDescr, function(){
					// location.reload();
					 window.location.replace('${pageContext.request.contextPath}/viewPurchase');
					// location.reload();
				  }); 
  	 			//location.reload();
			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
		// alert("Sale added Succesfully !");
	
	  
	   
		$("#purchasetable  tbody").empty();
		 document.getElementById("note").value="";
		 $("#gTotal").html(0.00);
         $("#qTotal").html(0.00);
         $("#taxTotal").html(0.00);
         $("#tTotal").html(0.00);
         $("#productDetailsPojo").val('');
         $("#productDetailsPojo").prop('selectedIndex',0);
         $('#productDetailsPojo').attr('selected', 'selected');
        // $('#vendorPojo').attr('selected', 'selected');
 
         
         window.location.replace('${pageContext.request.contextPath}/viewPurchase');
         } 
	

	function ChangeSubTotal(price, quantity, rowCount) {

	//	alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}
	
	function dotest(event,rowCount)
	{
	    //GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
	//  alert('reached');
	   
	   var price = $('#price' + rowCount).html();
	   var quantity = $('#quantity' + rowCount).html();
	  
	   var subtotal = (quantity * price).toFixed(2);
	  
	   $('#subtotal' + rowCount).html(subtotal);
	   CalculateTotal();
	 }
	
	function unitcal(event,rowCount)
	{
	    //GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
	//  alert('reached');
	   
	   var subtotal = $('#subtotal' + rowCount).html();
	  // var quantity = $('#quantity' + rowCount).html();
	  
	   var quantity = $('#quantity' + rowCount).html();
	  var price=(subtotal/quantity).toFixed(2);
	   $('#price' + rowCount).html(price);
	  // $('#quantity' + rowCount).html(quantity);
	   CalculateTotal();
	 }
	
	$("#productDetailsPojo").change(function(){
		
		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#purchasetable tr').length;
		//var sprice =document.getElementById("sellingPrice").value;
		var origprice = $('#productDetailsPojo :selected').data('cost');
		var discount = $('#productDetailsPojo :selected').data('promotion');
		//var price = document.getElementById("unitcost").value;
		var supplier = $('#vendorPojo :selected').text();
		origprice =origprice.toFixed(2);
	
		
		var note = $('#note').text();
		var quantity = 1;
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');	
		
	//	quantity= $('#unitId :selected').val();
		
		
		
		//'row"+rowCount+"'
		
		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');"></td>').html(origprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html(quantity));
		tr.append($('<td id="subtotal'+rowCount+'" contenteditable="true" onkeyup="javascript:unitcal(event,'+rowCount+');" ></td>').html(origprice));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#purchasetable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : origprice,
			"productId" : productId,
			"quantity" : quantity,
			"subtotal":origprice,
			"note" : note,
			"unitname" : supplier
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		// Adding footer
		CalculateTotal();
		
	});


	function addItem() {

		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#purchasetable tr').length;
		var sprice =document.getElementById("sellingPrice").value;
		var origprice = $('#productDetailsPojo :selected').data('cost');
		var discount = $('#productDetailsPojo :selected').data('promotion');
		var price = document.getElementById("unitcost").value;
		var supplier = $('#vendorPojo :selected').text();
		origprice =origprice.toFixed(2);
	
		
		var note = $('#note').text();
		var quantity = 1;
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');	
		
	//	quantity= $('#unitId :selected').val();
		
		
		
		//'row"+rowCount+"'
		
		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');"></td>').html(origprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html(quantity));
		tr.append($('<td id="subtotal'+rowCount+'"  contenteditable="true" onkeyup="javascript:unitcal(event,'+rowCount+');"></td>').html(origprice));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#purchasetable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : origprice,
			"productId" : productId,
			"quantity" : quantity,
			"subtotal":origprice,
			"note" : note,
			"unitname" : supplier
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		// Adding footer
		CalculateTotal();
		 		
	}
	
	function CalculateTotal() {
        var grandT = 0;
        var qtY = 0;
        var taxT =0;
        var totalT =0;
        $("#purchasetable > TBODY > tr").each(function () {
        	var t2 = $(this).find('td').eq(4).html();
            var t3 = $(this).find('td').eq(5).html();
            if (!isNaN(t3) && !isNaN(t2)) {
                grandT += parseFloat(t3);
                qtY +=parseFloat(t2);
            }
        });
        
        taxT= grandT * .125;
        totalT = grandT + taxT;
        $("#gTotal").html(grandT.toFixed(2));
        $("#qTotal").html(qtY.toFixed());
        $("#taxTotal").html(taxT.toFixed(2));
        $("#tTotal").html(totalT.toFixed(2));
        
    }

	// Creating a function to remove item from list
	function removeItem() {

		// Declaring a variable to get select element
		var a = document.getElementById("list");
		var candidate = document.getElementById("candidate");
		var item = document.getElementById(candidate.value);
		a.removeChild(item);
	}

	$("#productDetails").submit(function(event) {
		if ($("#discount").val() == "") {
			$("#discount").val(0)
		}
		if ($("#pieces").val() == "") {
			$("#pieces").val(0)
		}
		if ($("#productPrice").val() == "") {
			$("#productPrice").val(0.0)
		}
		//event.preventDefault();
	});
	
	function reset(){
		window.location.reload(true);	
	}
	function deleteDetails(count) {
		var x = confirm("Are you sure you want to delete?");
		if (x) {
		//	alert(count);
			var i=count-1;
			// var purchaseid = $("#purchaseid"+count).text();
			//$("#tr").remove();
			var row = document.getElementById("purchasetable");
			row.deleteRow(i);
			
		}
		CalculateTotal();
	}
	function deleteItems(count){
		
		$("#row"+count).remove();
		var table = document.getElementById("purchasetable");
		var rows = table.getElementsByTagName("tr");
		//alert(rows.length-1);
		for (var i = 1; i <= rows.length-1; i++) {
		  rows[i].setAttribute("id", "row" + (i));
		 // rows[i].setAttribute("serial",(i+1));
		  var cells = rows[i].cells;
		 // alert(cells.length);
		  for (var j = 0; j < cells.length -3; j++) {
		    cells[0].textContent = i;
		    cells[3].setAttribute("id", "price" + (i));
		    cells[3].setAttribute("onkeyup", "javascript:dotest(event,'"+(i)+"')" );
            cells[4].setAttribute("id", "quantity" + (i));
            cells[4].setAttribute("onkeyup", "javascript:dotest(event,'"+(i)+"')" );
            cells[5].setAttribute("id", "subtotal" + (i));
            cells[5].setAttribute("onkeyup", "javascript:unitcal(event,'"+(i)+"')" );
            cells[6].setAttribute("onclick", "deleteItems('"+(i)+"')");
		  }
		}
		CalculateTotal();
	}
	
	
</script>