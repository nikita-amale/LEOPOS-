
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">

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

.row {
	justify-content: center;
}
</style>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<!-- left column -->
				<div class="col-lg-10 col-md-12" style="padding-top: 40px">
					<!-- general form elements -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Edit Sale</h3>
						</div>
						<!-- /.card-header -->
						<!-- form start 
            <form  method="POST" action="${pageContext.request.contextPath}/addSales" autocomplete="off" name="Sales" id="Sales" enctype="multipart/form-data">
            -->
						<form autocomplete="off" name="Sales" id="itemForm"
							enctype="multipart/form-data">
							<div class="card-body">

								<div class="form-group" hidden>
									<label>Select Customer</label><font color="red"></font> <select
										class="form-control select2bs4" name="customerId"
										id="customerPojo" style="width: 100%;" readonly>
									</select>

								</div>
								<div class="form-group">
									<label>Customer</label><font color="red"></font> <input
										type="text" class="form-control" id="membername"
										name="membername" value="${salePojo.member_name}" readonly>
								</div>
								<div class="form-group">
									<label>Select Product</label><font color="red">*</font> <select
										class="form-control select2bs4" name="productCode"
										id="productDetailsPojo" style="width: 100%;" required>
									</select>
								</div>
								<input type="hidden" class="form-control" id="memberid"
									name="memberid" value="${salePojo.memberid}">


								<div class="form-group">
									<label>Select Unit</label><font color="red"></font> <select
										class="form-control select2bs4" name="unitId" id="unitId"
										style="width: 100%;" required>
									</select>
								</div>


								<div class="form-group">
									<label for="exampleInputEmail1">Quantity</label> <input
										type="text" class="form-control" id="pname" value="0" readonly>
								</div>



								<div class="form-group">
									<label>Select Sales Percentage</label><font color="red"></font>
									<select class="form-control select2bs4" id="salespercent"
										style="width: 100%;" readonly>
									</select>
								</div>
								<input type="hidden" class="form-control" id="memberunit"
									value="${memberPojo.ctype}">

								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>

								</div>
								<!-- /.card-header -->
								<div class="card-body  pl-0 pr-0">
									<table id="salestable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Net Unit Price</th>
												<th>Quantity</th>
												<th>Unit Of Measure</th>
												<th hidden>Unit</th>
												<th>Sub Total (BZD )</th>
												<th>Delete</th>
											</tr>

										</thead>
										<tbody>

										</tbody>
										<tfoot>
											<td class="row">
												<div class="text-center">
													<Strong>Total</Strong>
												</div>
												<div class="text-left pr-3" id="qTotal"></div>
												<div class="text-right" id="gTotal"></div>
											</td>
											<td class="row">
												<div class="text-center">
													<strong>Tax</strong>
												</div>
												<div class="text-right"></div>
												<div class="text-right" id="taxTotal"></div>
											</td>
											<td class="row">
												<div class="text-center">
													<strong>Grand Total</strong>
												</div>
												<div class="text-right"></div>
												<div class="text-right" id="tTotal"></div>
											</td>
										</tfoot>


									</table>
								</div>
								<!-- /.card-body -->
								<input type="hidden" class="form-control" name="ctype"
									id="ctype"> <input type="hidden" class="form-control"
									name="saleid" id="saleid" value="${salePojo.saleId}">
								<div class="form-group">
									<label for="exampleInputEmail1">PO</label> <input type="text"
										class="form-control" name="purchaseorder" id="purchaseorder"
										value="${salePojo.purchaseorder}" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
										value="${salePojo.note}">

								</div>
								<sec:authorize access="hasAuthority('admin')">
								<div class="form-group">
									<label for="exampleInputEmail1">Tax Required</label> <input type="checkbox"  id="taxrate" checked="true">
								</div>
								</sec:authorize>
								<div class="form-group" hidden>
									<label for="exampleInputEmail1">Tax Required</label> <input type="checkbox"  id="taxrate" checked="true">
								</div>	

							</div>
							<!-- /.card-body -->

							<div class="card-footer">

								<button type="button" class="btn btn-primary" id="mybtn"
									onclick="getSales()">Submit</button>
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

<!-- Select2 JS -->
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

<script type="text/javascript">
	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});

	window.onload = function() {

		var memberid = $('#memberid').val();
		var ctype = $('#memberunit').val();
		var saleId = $('#saleid').val();
		// alert(saleId);
		$.ajax({
			url : '${pageContext.request.contextPath}/getCustomer',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);

				$.each(data, function(i, data) {
					if (memberid == data.id) {
						$('#customerPojo').append(
								'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '" '+'data-pricegroup="'+data.pricegroup+'"selected>'
										+ data.userName + '</option>');
					} else {
						$('#customerPojo').append(
								'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '">'
										+ data.userName + '</option>');
					}
				});

			},
			error : function(error) {

				console.log(`Error ${error}`);
			}

		});

		// Initialize select2
	//	$("#customerPojo").select2();

		$.ajax({
			url : '${pageContext.request.contextPath}/getSalesPercent',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				$.each(data, function(i, data) {

					if (ctype == data.ctype) {
						$('#salespercent').append(
								'<option  hidden value="' + data.percentage + '" selected>'
										+ data.ctype + '</option>');
					} else {
						$('#salespercent').append(
								'<option hidden value="' + data.percentage + '">'
										+ data.ctype + '</option>');

					}
				});

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});

		$("#salestable  tbody").empty();

		if (ctype != "Special") {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId='
								+ saleId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$
									.each(
											data,
											function(i, data) {

												var productName = data.product_name;
												var productId = data.product_code;
												var rowCount = $('#salestable tr').length;
												var price = data.real_unit_price;
												price=price.toFixed(2);
												var subtotal = data.subtotal;
												var customerId = $(
														'#customerPojo :selected')
														.val();
												var note = $('#note').text();
												var unit = data.sale_item_id;
												var unitname = data.roll;
												var quantity = data.quantity;
												var newprice = data.real_unit_price;
												newprice = (price * quantity).toFixed(2);
												//var roll = "NO";

												//alert(saleId);

												//newprice = price*qty;

												var tr = $("<tr id='row"+rowCount+"'></tr>");
												tr.append($('<td></td>').html(
														rowCount-1));
												tr.append($('<td></td>').html(
														productId));

												tr.append($('<td></td>').html(
														productName));
												tr
														.append($(
																'<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>')
																.html(price));
												tr
														.append($(
																'<td id="quantity'
																		+ rowCount
																		+ '" contenteditable="true"  oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')"></td>')
																.html(quantity));
												tr
														.append($(
																'<td id="unitname'+rowCount+'"></td>')
																.html(unitname));
												tr
														.append($(
																'<td hidden id="unit'+rowCount+'"></td>')
																.html(unit));
												tr
														.append($(
																'<td id="subtotal'+rowCount+'" ></td>')
																.html(subtotal));
												tr
														.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('
																+ rowCount
																+ ')">Delete</button>'));
												tr
														.append($('<tr></tr>')
																.html());
												$('#salestable tbody').append(
														tr);

												var myJSON = {
													"productName" : productName,
													"price" : newprice,
													"productId" : productId,
													"customerId" : customerId,
													"quantity" : quantity,
													"roll" : unitname,
													"note" : note,
													"saleId" : saleId,
													"unit" : unit
												};

												productList.push(myJSON);
												CalculateTotal();

											});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});

		} else {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleId?saleId='
								+ saleId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$
									.each(
											data,
											function(i, data) {

												var productName = data.product_name;
												var productId = data.product_code;
												var rowCount = $('#salestable tr').length;
												var price = data.real_unit_price;
												var subtotal = data.subtotal;
												var customerId = $(
														'#customerPojo :selected')
														.val();
												var note = $('#note').text();
												var quantity = data.quantity;
												var unit = data.sale_item_id;
												var unitname = data.roll;
												var roll = "NO";
												var newprice = data.real_unit_price;
												
												var qty = "";

												if (unit != 1) {
													roll ="YES";
												} 
											//	alert(quantity);

												var tr = $("<tr id='row"+rowCount+"'></tr>");
												tr.append($('<td></td>').html(
														rowCount-1));
												tr.append($('<td></td>').html(
														productId));

												tr.append($('<td></td>').html(
														productName));
												tr
														.append($(
																'<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>')
																.html(price));
												tr
														.append($(
																'<td id="quantity'
																		+ rowCount
																		+ '" contenteditable="true" oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')" ></td>')
																.html(quantity));
												tr
												.append($(
														'<td id="unitname'+rowCount+'"></td>')
														.html(unitname));
												tr
												.append($(
														'<td hidden id="unit'+rowCount+'"></td>')
														.html(unit));
												tr
														.append($(
																'<td id="subtotal'+rowCount+'" ></td>')
																.html(subtotal));
												tr
														.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('
																+ rowCount
																+ ')">Delete</button>'));
												tr
														.append($('<tr></tr>')
																.html());
												$('#salestable tbody').append(
														tr);

												var myJSON = {
													"productName" : productName,
													"price" : price,
													"productId" : productId,
													"customerId" : customerId,
													"quantity" : quantity,
													"roll" : roll,
													"note" : note,
													"saleId" : saleId,
													"unit" : unit
												};
												CalculateTotal();
												productList.push(myJSON);

											});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
			CalculateTotal();

		}

		console.log(productList);

	}

	// ************ Windows on load function ends ***********************

	$.ajax({
		url : '${pageContext.request.contextPath}/getCustomer',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$('#customerPojo').append(
					$("<option></option>").attr("value", "0").text("Select"));
			$.each(data, function(i, data) {
				$('#customerPojo').append(
						'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '">'
								+ data.userName + '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	

	$
			.ajax({
				url : '${pageContext.request.contextPath}/getProducts',
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					$('#productDetailsPojo').append(
							$("<option></option>").attr("value", "0").text("Select"));
					$.each(data, function(i, data) {
						$('#productDetailsPojo').append(
								'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-quantity="' + data.quantity + 
								'" data-promotion="' + data.promotion + '" value="' + data.productId + '">'	+ data.name + ' - ' + data.cf1 + ' - ' + data.code + '</option>');
					});

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});

	// Initialize select2
	$("#productDetailsPojo").select2();

	$.ajax({
		url : '${pageContext.request.contextPath}/getUnits',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$.each(data, function(i, data) {
				$('#unitId').append(
						'<option value="' + data.id + '" data-id="' + data.id + '">' + data.unitname + '</option>');

			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});

	$.ajax({
		url : '${pageContext.request.contextPath}/getSalesPercent',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$.each(data, function(i, data) {
				$('#salespercent').append(
						'<option hidden value="' +data.percentage  + '">'
								+ data.ctype + '</option>');

			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	$("#productDetailsPojo").change(function() {
		  var productname = $('#productDetailsPojo :selected').text();
		  var product = $('#productDetailsPojo :selected').data('rollprice');
		  var productId = parseInt($('#productDetailsPojo :selected').val());
		  var selectedUnit = $("#unitId").val(); // Get the currently selected unit
		    
		    // Clear the dropdown options
		    $("#unitId").empty();
		 // alert(productId);
		 // alert(productname);
		 
		    if (productname.startsWith("Wire") && product == 0){
				  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			 
			  }
		    
		    else  if (productname.startsWith("Wire")&&product!=0){
			  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			  $("#unitId").append('<option value="2" data-id="2">Wire Roll 328 Ft</option>');
	
				  $("#unitId option:contains(Ft)").show();
						 
		  }else if(productId==277){
			  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			  $("#unitId").append('<option value="2" data-id="2">Wire Roll 328 Ft</option>');
								 
		  }else if(productId==278 ){
			  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			  $("#unitId").append('<option value="4" data-id="4">Roll 164 Ft</option>');				
				 
		  }else if(productId==269 || productId==270 || productId==273 ){
			  $("#unitId").append('<option value="3" data-id="3">Box</option>');
			  $("#unitId").append('<option value="1" data-id="1">Piece</option>');
		  }
		  else if(productId==1398 || productId==1399 || productId==1400 || productId==1401 || productId==1402 || productId==1403 ){
			  $("#unitId").append('<option value="1" data-id="6">Roll 66 Ft</option>');
		  }
		  else if(productId==1419){
			  $("#unitId").append('<option value="1" data-id="7">Roll 1000 Ft</option>');
		  }else if(productId==852 || productId==853 ||productId==854 ||  productId==855 || productId==856 || productId==857 || productId==858 || productId==859 || productId==860 || productId==861 || productId==862 || productId==863 ||productId==864 ||productId==1336 || productId==1581 || productId==1532 || productId==867 || productId==1679 ){
			  $("#unitId").append('<option value="1" data-id="8">10Ft</option>');
			  $("#unitId").append('<option value="9" data-id="9">20Ft</option>');
			  
		  }
		  else if(productId==1511 || productId==1512 ){
			  $("#unitId").append('<option value="1" data-id="10">Roll 100 Ft</option>');
			   
		  }
		  else if(productId==1513 || productId==1514 || productId==1515 || productId==1516 || productId==1521 || productId==1522   ){
			  $("#unitId").append('<option value="1" data-id="11">Box 50lb</option>');
			   
		  }
		   //Add this on 04-06-2025
		  else if (productId == 1678 || productId == 1680 || productId == 1681 || productId == 1682) {
		        $("#unitId").append('<option value="1" data-id="11">Box 50lb</option>');
		    }
		   
		  else if(productId==1517 || productId==1518 || productId==1519 || productId==1520|| productId==1523 || productId==1712){
			  $("#unitId").append('<option value="1" data-id="12">Box 55lb</option>');
			   
		  }
		   
		  else if(productId==1607  ){
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="2" data-id="14">Box</option>');
			 
		  }
		  else if(productId==1608){
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="2" data-id="15">Box</option>');
		  }
		  else if(productId==1609){
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="2" data-id="16">Box</option>');
		  }
		  else if(productId==1610){
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="2" data-id="17">Box</option>');
		  }
		  else if(productId==1611){
				
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="17" data-id="18">Box</option>');
		  }
		    
		    //Add this on 19-11-2025
		  else if(productId == 1743 || productId == 1744 || productId == 1745 || productId == 1746 || productId == 1747){
				
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="19" data-id="19">Box</option>');
		  }
		 
		  else {
			  //alert("else");
					  $("#unitId").append('<option value="1" data-id="1">Piece</option>');
			
		  }
	 });
	$("#customerPojo").change(
			function() {
				//  alert("test");

				var ctype = $('#customerPojo :selected').data('ctype');
				//alert(ctype);

				if (ctype == "Special") {
					$("#salespercent option:contains(Special)").attr(
							'selected', true);
					$("#salespercent option:contains(WholeSellers)").attr(
							'selected', false);
					$("#salespercent option:contains(General)").attr(
							'selected', false);
					 $("#salespercent option:contains(10Percentprice)").attr(
							 'selected', false);
				} else if (ctype == "WholeSellers") {
					$("#salespercent option:contains(WholeSellers)").attr(
							'selected', true);
					$("#salespercent option:contains(Special)").attr(
							'selected', false);
					$("#salespercent option:contains(General)").attr(
							'selected', false);
					 $("#salespercent option:contains(10Percentprice)").attr(
							 'selected', false);
				}else if (ctype == "10Percentprice") {
					$("#salespercent option:contains(WholeSellers)").attr(
							'selected', false);
					$("#salespercent option:contains(Special)").attr(
							'selected', false);
					$("#salespercent option:contains(General)").attr(
							'selected', false);
					$("#salespercent option:contains(10Percentprice)").attr(
							'selected', true);
				}
				else {
					$("#salespercent option:contains(General)").attr(
							'selected', true);
					$("#salespercent option:contains(Special)").attr(
							'selected', false);
					$("#salespercent option:contains(WholeSellers)").attr(
							'selected', false);
					 $("#salespercent option:contains(10Percentprice)").attr(
							 'selected', false);

				}
			});

	var productList = [];

	// New GetSales -- making JSON from table elements
	function getSales() {

		var customerId = $('#customerPojo :selected').val();
		var note = $('#note').val();
		var ctype = $('#customerPojo :selected').data('ctype');
		var saleId = $('#saleid').val();
		var purchaseorder =$('#purchaseorder').val();
		
 		var taxrate = document.getElementById("taxrate");
 		
		 
		 var tax="YES";
	
		 if (customerId === '133') {
			    //alert("Reached customer 133");
			    tax = "NO";
			}
			
			else if (taxrate.checked) {
			    //alert("Reached checked");
			    tax = "YES";
			}
		  else{
		    	tax ="NO";
		      //  alert("You didn't check it! Let me check it for you.")
 		    }

		// alert(ctype);

		var _table = document.getElementById("salestable");
		var _trLength = _table.getElementsByTagName("tr").length - 1;
		var _jsonData = [];
		var _obj = {};
		if(_trLength==1){
			alert("Please select product");
		}
		
		else{

			var _htmlToJSON = function(index) {
			var _tr = _table.getElementsByTagName("tr")[index];
			var _td = _tr.getElementsByTagName("td");
		
			var _arr = [].map.call(_td, function(td) {
				return td.innerHTML;
			}).join(',');
			var _data = _arr.split(",");
			
			var quantity = _data[4];
			var price = _data[3];
			var ogsubtotal = _data[7];
			var subtotal = quantity * price;
		//	alert("Quantity is = " + quantity);
		//	alert("ogsubtotal is = " + ogsubtotal);
		//	alert("subtotal is = " + subtotal);
			
			if(ogsubtotal != subtotal){
					 //alert("Please add the product again, Prices not matching stopping here");
					// throw "exit";
		//			   document.getElementById("myBtn").disabled = true;  
				 }
	 	
		//	 		   document.getElementById("myBtn").disabled = false;  
			_obj = {
					productId : _data[1],
					productName : _data[2],
					price : _data[3],
					customerId : customerId,
					quantity : _data[4],
					note :    note,
					tax : tax,
					subtotal : _data[7],
					roll    :_data[5],
					unit    :_data[6],
					saleId :saleId,
					purchaseorder :purchaseorder
				 };

			_jsonData.push(_obj);

			};

		for (var i = 1; i < _trLength; i++) {
			_htmlToJSON(i);
		}
		console.log("html to JSON", _jsonData);

		var form = $('#itemForm')[0];
		var data = new FormData(form);
		var myJSON = JSON.stringify(_jsonData);
		myJSON = myJSON.split(']}]"}').join(']}]}');
		//myJSON = myJSON.replace(/\\/g, "");
		myJSON = myJSON.split('"[{').join('[{');
		myJSON = myJSON.split('"}]"').join('"}]');
		console.log("myJSON======" + myJSON);

		data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		if (ctype != "Special") {
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/updateSales',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : myJSON,
				success :function(data) {
	  	 			//alertify
					//  .alert(data.msgDescr, function(){
						 // alertify.alert("success");
						  alert(data.msgDescr);	
						  window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
					 // });
						//	window.location.href = "${pageContext.request.contextPath}/viewSales";
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		//	window.location.href = "${pageContext.request.contextPath}/viewSales";

			//alert("Sale Updated Succesfully !");
		} else {
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/updateSpecialSales',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : myJSON,
				success :function(data) {
	  	 			//alertify
					  //.alert(data.msgDescr, function(){
						//  alertify.alert("success");
						 alert(data.msgDescr);	
						  window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
					  //});
						//	window.location.href = "${pageContext.request.contextPath}/viewSales";
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		//	window.location.href = "${pageContext.request.contextPath}/viewSales";

			//alert("Special Sale Updated Succesfully !");

		}

			$("#salestable  tbody").empty();
			document.getElementById("note").value = "";

			$("#gTotal").html(0.00);
			$("#qTotal").html(0.00);
			$("#taxTotal").html(0.00);
			$("#tTotal").html(0.00);
			$("#productDetailsPojo").val('');
			$("#productDetailsPojo").prop('selectedIndex', 0);
			$('#productDetailsPojo').attr('selected', 'selected');
		}
	  
	}

	function ChangeSubTotal(price, quantity, rowCount) {

		//	alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}

	function dotest(event, rowCount) {
		//GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
          console.log("Reached dotest");
		var price = $('#price' + rowCount).html();
		var quantity = $('#quantity' + rowCount).html();
		 var ogsubtotal=$('#subtotal' + rowCount).html();
	//	 alert("Quantity = " + quantity);
	/*
		 if(isNaN(quantity)){
				
				alert("Quantity must be a number");
				$('#quantity' + rowCount).html(1);
				var subtotal = (1 * price).toFixed(2);

				$('#subtotal' + rowCount).html(subtotal);
				//alert(subtotal);
			}else{

			var subtotal = (quantity * price).toFixed(2);

			$('#subtotal' + rowCount).html(subtotal);
			}
			CalculateTotal(); */
			
		    if (!isNaN(quantity) && quantity !== '') {
		        var quantityVal = parseFloat(quantity);
		        var subtotal = (quantityVal * price).toFixed(2);
		        $('#subtotal' + rowCount).text(subtotal);
		        CalculateTotal();
		    }
	//	alert("Subtotal = " + subtotal + " and ogsubtotal=" + ogsubtotal);
		
		console.log("quantity changes ="+quantity);
		console.log("subtotal changes ="+subtotal);
	  //  $('#productDetailsPojo').focus();

	}
	
	function handleBlur(rowCount) {
	    let price = parseFloat($('#price' + rowCount).text()) || 0;
	    let quantity = $('#quantity' + rowCount).text().trim();

	    if (quantity === '' || isNaN(quantity)) {
	        alert("Quantity must be a number. Defaulting to 1.");
	        $('#quantity' + rowCount).text(1);
	        let subtotal = (1 * price).toFixed(2);
	        $('#subtotal' + rowCount).text(subtotal);
	        CalculateTotal();
	    }
	}

	$("#productDetailsPojo")
			.change(
					function() {

						var productName = $('#productDetailsPojo :selected')
								.text();
						var productId = $('#productDetailsPojo :selected')
								.val();
						var rowCount = $('#salestable tr').length;
						var price = $('#productDetailsPojo :selected').data(
								'price');
						var origprice = $('#productDetailsPojo :selected')
								.data('price');
						var discount = $('#productDetailsPojo :selected').data(
								'promotion');
						var customerId = $('#customerPojo :selected').val();
						var unit = $('#unitId :selected').data(
						'id');
						var unitname = $('#unitId :selected').text();
						var note = $('#note').text();
						var quantity = 1;
						var newprice = $('#productDetailsPojo :selected').data(
								'price');
						var ctype = $('#customerPojo :selected').data('ctype');
						var rollprice = $('#productDetailsPojo :selected')
								.data('rollprice');
						var roll = "NO";
						var purchaseorder= $('#purchaseorder').val();
						
						var pquantity = $('#productDetailsPojo :selected').data('quantity');
						$('#pname').val(pquantity);
						//alert(unit);

						//alert(rollprice);

						//	quantity= $('#unitId :selected').val();

						newprice = newprice.toFixed(2)
						var percentage = 0;
						var pricegroup;
						pricegroup =  $('#customerPojo :selected').data('pricegroup');
						//alert(pricegroup);
						if(ctype=="Special"&&pricegroup=="WholeSellers"){
							percentage=0;
						}else if(ctype=="Special"&&pricegroup=="Special"){
							percentage=18;
						}else if(ctype=="WholeSellers"&&pricegroup=="WholeSellers"){
							percentage=0;
						}else{
							percentage=18;
						}

						//	 alert(rollprice);
						 if (unit === 5 || unit === 3 || unit == 8 || unit == 9 ||unit==13 || unit==14 ||unit==15||unit==16||unit==17  ) {
        return;
    }

						if (percentage > 0) {
							newprice = (origprice + (( origprice * percentage ) / 100)) ;
							newprice=Math.round(newprice * 100) / 100;
							newprice=newprice.toFixed(2);

							if (ctype != "WholeSellers" && discount > 0) {

								newprice = (newprice - (( newprice * discount ) / 100));
								newprice=Math.round(newprice * 100) / 100;
								newprice=newprice.toFixed(2);

							}
						}

						if (unit == 1 || unit == 5) {
							price = (newprice * quantity).toFixed(2);
						}
						else if(unit ==3){
							price = rollprice.toFixed(2);
							newprice = rollprice.toFixed(2);
							 price = (newprice * quantity).toFixed(2);
							 unitname="Box";
							 //alert(newprice);
							 if(percentage > 0){
								 newprice = (+newprice + (( +newprice * percentage ) / 100)) ;
								  newprice=Math.round(newprice * 100) / 100;
									newprice=newprice.toFixed(2);
									//alert(newprice);
									price = (+price + (( +price * percentage ) / 100));
									price=Math.round(price * 100) / 100;
									price=price.toFixed(2);
								 
							 }
						}else {
							if (rollprice > 0) {
								roll = "YES";
								price = rollprice.toFixed(2);
								newprice = rollprice.toFixed(2);
								//	alert(newprice);
								if (percentage > 0) {
									newprice = (+newprice + (( +newprice * percentage ) / 100)) ;
									newprice=Math.round(newprice * 100) / 100;
									newprice=newprice.toFixed(2);
									price = (+price + (( +price * percentage ) / 100));
									price=Math.round(price * 100) / 100;
									price=price.toFixed(2);
									//		alert(newprice);
									if (ctype != "WholeSellers" && discount > 0) {

										newprice = (+newprice - (( +newprice * discount ) / 100));
										newprice=Math.round(newprice * 100) / 100;
										newprice=newprice.toFixed(2);
										price = (+price - (( +price * discount ) / 100)) ;
										price=Math.round(price * 100) / 100;
										price=price.toFixed(2);

									}
								}
							}
							else{
								roll="YES";
								price =(newprice * quantity).toFixed(2);
							}
						}

						var tr = $("<tr id='row"+rowCount+"'></tr>");
						tr.append($('<td></td>').html(rowCount - 1));
						tr.append($('<td></td>').html(productId));

						tr.append($('<td></td>').html(productName));
						tr.append($('<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>').html(
								newprice));
						tr
								.append($(
										'<td id="quantity'
												+ rowCount
												+ '" contenteditable="true" oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')" ></td>')
										.html(quantity));
						tr.append($('<td id="roll'+rowCount+'" ></td>').html(
								unitname));
						tr.append($('<td hidden id="unit'+rowCount+'"></td>')
								.html(unit));
						tr.append($('<td id="subtotal'+rowCount+'"></td>')
								.html(price));
						tr
								.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('
										+ rowCount + ')">Delete</button>'));
						tr.append($('<tr></tr>').html());
						$('#salestable tbody').append(tr);

						var myJSON = {
							"productName" : productName,
							"price" : newprice,
							"productId" : productId,
							"customerId" : customerId,
							"roll" : unitname,
							"quantity" : quantity,
							"subtotal" : price,
							"note" : note,
							"unit" : unit,
							"purchaseorder" : purchaseorder
						};

						productList.push(myJSON);
						//	document.getElementById("productList").val()=productList;

						console.log(productList);
						$("#unitId option:contains(Piece)").attr('selected',
								true);
						// Adding footer
						CalculateTotal();

					});

	function addItem() {

		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#salestable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var origprice = $('#productDetailsPojo :selected').data('price');
		var discount = $('#productDetailsPojo :selected').data('promotion');
		var customerId = $('#customerPojo :selected').val();
		var unit = $('#unitId :selected').data('id');
		var unitname = $('#unitId :selected').text();
		var note = $('#note').text();
		var quantity = 1;
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');
		var roll = "NO";
		var purchaseorder = $('#purchaseorder').val();
		var pquantity = $('#productDetailsPojo :selected').data('quantity');
		$('#pname').val(pquantity);

		//	quantity= $('#unitId :selected').val();

		/*newprice = newprice.toFixed(2)
		var percentage = 0;
		var pricegroup;
		pricegroup =  $('#customerPojo :selected').data('pricegroup');
		
		if(ctype=="Special"&&pricegroup=="WholeSellers"){
			percentage=0;
			//alert(percentage);
		}else if(ctype=="Special"&&pricegroup=="Special"){
			percentage=18;
			//alert(percentage);
		}else if(ctype=="WholeSellers"&&pricegroup=="WholeSellers"){
			percentage=0;
			//alert(percentage);
		}else{
			percentage=18;
			//alert(percentage);
		}*/

		//	 alert(rollprice);

		
		newprice = newprice.toFixed(2)
		var percentage =0;
		percentage = $('#salespercent :selected').val();
		
		
		if(customerId==0){
			alert("Please select customer");
		}else{
		
		
		// alert(rollprice);
		
		if(percentage > 0)
		{
			newprice = (origprice + (( origprice * percentage ) / 100));
			newprice=Math.round(newprice * 100) / 100;
			newprice=newprice.toFixed(2);
			
			if( ctype != "WholeSellers" && discount>0){
			
				newprice = (newprice - (( newprice * discount ) / 100)) ;
				newprice=Math.round(newprice * 100) / 100;
				newprice=newprice.toFixed(2);
			 
			}
		}
		if(unit == 9){
			newprice =newprice*2
		}
		
		if(unit == 1 || unit ==5 || unit==13 )
		{
		   price = (newprice * quantity).toFixed(2);
		}
		else if(unit == 14 || unit==15 || unit==16 || unit==17){
			//alert("Reached")
			roll="YES";
			price = rollprice.toFixed(2);
			newprice = rollprice.toFixed(2);
			//alert(percentage);
			if(percentage > 0)
			{
				newprice = (+newprice + (( +newprice * percentage ) / 100));
				newprice=Math.round(newprice * 100) / 100;
				newprice=newprice.toFixed(2);
				price = (+price + (( +price * percentage ) / 100)) ;
				price=Math.round(price * 100) / 100;
				price=price.toFixed(2);
				//alert(newprice);
				if( ctype != "WholeSellers" && discount>0){
				
					newprice = (+newprice - (( +newprice * discount ) / 100)) ;
					newprice=Math.round(newprice * 100) / 100;
					newprice=newprice.toFixed(2);
					price = (+price - (( +price * discount ) / 100));
					price=Math.round(price * 100) / 100;
					price=price.toFixed(2);
			
		}
			}
		}
		else if(unit ==3){
			price = rollprice.toFixed(2);
			newprice = rollprice.toFixed(2);
			 price = (newprice * quantity).toFixed(2);
			 unitname="Box";
			 //alert(newprice);
			 if(percentage > 0){
				 newprice = (+newprice + (( +newprice * percentage ) / 100)) ;
				  newprice=Math.round(newprice * 100) / 100;
					newprice=newprice.toFixed(2);
					//alert(newprice);
					price = (+price + (( +price * percentage ) / 100));
					price=Math.round(price * 100) / 100;
					price=price.toFixed(2);
				 
			 }
		}
		else
		 {
			if(rollprice > 0){
				roll="YES";
				price = rollprice.toFixed(2);
				newprice = rollprice.toFixed(2);
			//	alert(newprice);
				if(percentage > 0)
				{
					newprice = (+newprice + (( +newprice * percentage ) / 100));
					newprice=Math.round(newprice * 100) / 100;
					newprice=newprice.toFixed(2);
					price = (+price + (( +price * percentage ) / 100)) ;
					price=Math.round(price * 100) / 100;
					price=price.toFixed(2);
			//		alert(newprice);
					if( ctype != "WholeSellers" && discount>0){
					
						newprice = (+newprice - (( +newprice * discount ) / 100)) ;
						newprice=Math.round(newprice * 100) / 100;
						newprice=newprice.toFixed(2);
						price = (+price - (( +price * discount ) / 100));
						price=Math.round(price * 100) / 100;
						price=price.toFixed(2);
					 
					}
				}
			}
			else{
				roll="YES";
				price =(newprice * quantity).toFixed(2);
			}
		 }
		
		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount - 1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>').html(newprice));
		tr
				.append($(
						'<td id="quantity'
								+ rowCount
								+ '" contenteditable="true" oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')" ></td>').html(quantity));
		tr.append($('<td id="roll'+rowCount+'" ></td>').html(unitname));
		tr.append($('<td hidden id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'"></td>').html(price));
		tr
				.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('
						+ rowCount + ')">Delete</button>'));
		$('#salestable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : newprice,
			"productId" : productId,
			"customerId" : customerId,
			"roll" : unitname,
			"quantity" : quantity,
			"subtotal" : price,
			"note" : note,
			"unit" : unit,
			"purchaseorder" : purchaseorder
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		$("#unitId option:contains(Piece)").attr('selected', true);
		// Adding footer
		CalculateTotal();

	}
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

	function reset() {
		window.location.reload(true);

	}

	function CalculateTotal() {
		var grandT = 0;
		var qtY = 0;
		var taxT = 0;
		var totalT = 0;
		$("#salestable > TBODY > tr").each(function() {
			var t2 = $(this).find('td').eq(4).html();
			var t3 = $(this).find('td').eq(7).html();
			if (!isNaN(t3) && !isNaN(t2)) {
				grandT += parseFloat(t3);
				qtY += parseFloat(t2);
			}
		});
		 grandT= grandT.toFixed(2);
		taxT = grandT * .125;
		 taxT=+(Math.round(taxT + "e+2")  + "e-2");
		totalT = Number(grandT)+taxT;
		 totalT=  Math.round(totalT * 100) / 100;
		$("#gTotal").html(grandT);
		$("#qTotal").html(qtY.toFixed());
		$("#taxTotal").html(taxT.toFixed(2));
		$("#tTotal").html(totalT.toFixed(2));

	}

	function deleteDetails(count) {
		var x = confirm("Are you sure you want to delete?");

		if (x) {
			//	alert(count);

			var i = count - 1;
			// var purchaseid = $("#purchaseid"+count).text();
			//$("#tr").remove();
			var row = document.getElementById("salestable");
			row.deleteRow(i);

		}
		CalculateTotal();
	}

	function deleteItems(count) {
		$("#row"+count).remove();
		var table = document.getElementById("salestable");
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
            cells[5].setAttribute("id", "unitname" + (i));
            cells[6].setAttribute("id", "unit" + (i));
            cells[7].setAttribute("id", "subtotal" + (i));
            cells[8].setAttribute("onclick", "deleteItems('"+(i)+"')");
		  }
		}

		CalculateTotal();
	}
</script>
