
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
							<h3 class="card-title">Edit Rental Quote</h3>
						</div>
						<!-- /.card-header -->
						<!-- form start 
            <form  method="POST" action="${pageContext.request.contextPath}/addSales" autocomplete="off" name="Sales" id="Sales" enctype="multipart/form-data">
            -->
						<form autocomplete="off" name="RentalQuote" id="itemForm"
							enctype="multipart/form-data">
							<div class="card-body">


								<div class="form-group" hidden>
									<label>Select Customer</label><font color="red"></font> <select
										class="form-control select2bs4" name="customerId"
										id="customerPojo" style="width: 100%;" required>
									</select>
								</div>
								<div class="form-group">
									<label >Customer</label><font color="red"></font> <input type="text" class="form-control" id="member_name"
									name="member_name" value="${quotesPojo.member_name}" readonly>
									</div>
										<input type="hidden" class="form-control"  id="member_id" name="member_id" value="${quotesPojo.member_id}">
											<input type="hidden" class="form-control" name="rquoteId" id="rquoteId" value="${quotesPojo.rquoteId}">

								<div class="form-group">
									<label for="exampleInputEmail1">Select Rental Product</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="productCode" id="productDetailsPojo" Required>
									</select>
								</div>
								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="rentaltable"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Net Unit Price</th>
												<th>Quantity</th>
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
												<div class="text-left pr-4" id="qTotal"></div>
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

<script>
	/*	$(document).ready(
			function() {
				alert("test");
				$('#salestable').dataTable(
						{
							
						});
			});
	 */
</script>

<script type="text/javascript">
	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});
	
	window.onload = function() {
		var memberid = $('#member_id').val();
		var rquotesId = $('#rquoteId').val();
		//alert(memberid);
		//alert(rquotesId);
		
		$.ajax({
	 		url : '${pageContext.request.contextPath}/getCustomer',
	 		type : "GET",
	 		dataType : "json",
	 		success : function(data) {
	 			var ajaxCallData = JSON.stringify(data);
	 	
	 			$.each(data, function(i, data) {
	 				if(memberid == data.id)
	 				{
	 					$('#customerPojo').append(
	 							'<option value="' + data.id + '">' + data.userName
								+ '</option>');
	 				}
	 				else
	 				{
	 				$('#customerPojo').append(
	 						'<option value="' + data.id + '">' + data.userName
							+ '</option>');
	 				}
	 			});

	 		},
	 		error : function(error) {

	 			console.log(`Error ${error}`);
	 		}

	 	});
		
		 $.ajax({
				url : '${pageContext.request.contextPath}/getRquoteitembyrquoteId?rquoteId=' + rquotesId,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					
					$.each(data, function(i, data) {
						var real_unit_price = data.real_unit_price;
						var subtotal =  data.subtotal;
						var customerId =  $('#member_id').val();
						var productName =data.product_name;
						var productId = data.product_id;
						var price = data.real_unit_price;
						var subtotal = data.subtotal;
						var quantity = data.quantity;
						var rowCount = $('#rentaltable tr').length;
						if(!isNaN(real_unit_price)){
							real_unit_price = parseFloat(real_unit_price).toFixed(2); 
						  }
						if(!isNaN(subtotal)){
							subtotal = parseFloat(subtotal).toFixed(2); 
						  }
						
						var tr = $('<tr></tr>');
						var tr = $("<tr id='row"+rowCount+"'></tr>");
						tr.append($('<td></td>').html(i+1));
						tr.append($('<td></td>').html(data.product_id));

						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td id="price'+rowCount+'"></td>').html(real_unit_price));
						tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html(data.quantity));
					
						
						tr.append($('<td id="subtotal'+rowCount+'"></td>').html(subtotal));
						tr.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('
								+ rowCount
								+ ')">Delete</button>'));
						tr.append($('<tr></tr>').html());
						$('#rentaltable tbody').append(tr);
						
						var myJSON = {
								"productName" : productName,
								"price" : price,
								"productId" : productId,
								"customerId" : customerId,
								"quantity" : quantity,
								"saleId" : rquotesId,
								"subtotal" :subtotal

							};

							productList.push(myJSON);
						
			
						CalculateTotal();
						
					
					});

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		 
	 
	}//window onload end



	$.ajax({
		url : '${pageContext.request.contextPath}/getRentalProducts',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$('#productDetailsPojo').append(
					$("<option></option>").attr("value", "0").text("Select"));
			$.each(data, function(i, data) {
				$('#productDetailsPojo').append(
						'<option data-price="'+data.rprice+'" value="' + data.rproductId + '">'
								+ data.name + '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}


	});

	var productList = [];

	// New GteSales -- making JSON from table elements
	function getSales() {

		var customerId =  $('#member_id').val();
		var note = document.getElementById("note").value;
		var taxrate = document.getElementById("taxrate");
		var rquotesId = $('#rquoteId').val();
		var tax = "YES";
		if (taxrate.checked) {
			//   alert("checked") ;
			tax = "YES";
		} else {
			tax = "NO";
			//  alert("You didn't check it! Let me check it for you.")
		}

		var _table = document.getElementById("rentaltable");
		var _trLength = _table.getElementsByTagName("tr").length - 1;
		var _jsonData = [];
		var _obj = {};

		var _htmlToJSON = function(index) {
			var _tr = _table.getElementsByTagName("tr")[index];
			var _td = _tr.getElementsByTagName("td");
			//   var quantity = ($(_data[4]).text().trim()) ? parseInt($(this).text().trim()) : $(this).text().trim();
			//    alert(quantity);
			var _arr = [].map.call(_td, function(td) {
				return td.innerHTML;
			}).join(',');
			var _data = _arr.split(",");

			_obj = {
				productId : _data[1],
				productName : _data[2],
				price : _data[3],
				customerId : customerId,
				quantity : _data[4],
				saleId	: rquotesId,
				customerId : customerId,
				note : note,
				tax : tax
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
		myJSON = myJSON.replace(/\\/g, "");
		myJSON = myJSON.split('"[{').join('[{');
		myJSON = myJSON.split('"}]"').join('"}]');
		console.log("myJSON======" + myJSON);

		data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/updateRentalQuotes',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success : (function(response) {
				console.log(response);

			})
		});

		alert("Rental Quote Added Succesfully !");
		$("#rentaltable  tbody").empty();
		document.getElementById("note").value = "";
		window.location.replace('${pageContext.request.contextPath}/listRentalQuotes');	
	}

	function ChangeSubTotal(price, quantity, rowCount) {

		//alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}

	function dotest(event, rowCount) {
		//GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
		//  alert("rowCount " + rowCount);

		var price = $('#price' + rowCount).html();
		var quantity = $('#quantity' + rowCount).html();
		// alert("quantity " + quantity);
		// alert("price " + price);
		var subtotal = quantity * price;
		//alert("subtotal " + subtotal);
		$('#subtotal' + rowCount).html(subtotal);
		CalculateTotal();
	}
	function CalculateTotal() {

		var grandT = 0;
		var qtY = 0;
		var taxT = 0;
		var totalT = 0;

		$("#rentaltable > TBODY > tr").each(function() {
			var t4 = $(this).find('td').eq(4).html();
			var t6 = $(this).find('td').eq(5).html();
			// alert(t4);
			//alert(t6);

			if (!isNaN(t6) && !isNaN(t4)) {
				grandT += parseFloat(t6);
				qtY += parseFloat(t4);
			}
		});
		grandT = grandT.toFixed(2);
		taxT = grandT * .125;
		//alert(taxT);
		taxT = +(Math.round(taxT + "e+2") + "e-2");
		//  alert(taxT);
		totalT = Number(grandT) + taxT;
		//alert(totalT);
		totalT = Math.round(totalT * 100) / 100;
		// alert(totalT);
		$("#gTotal").html(grandT);
		$("#qTotal").html(qtY.toFixed());
		$("#taxTotal").html(taxT.toFixed(2));
		$("#tTotal").html(totalT.toFixed(2));

	}

	function addItem() {

		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#rentaltable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var customerId = $('#customerPojo :selected').val();
		var note = $('#note').text();
		var quantity = 1;

		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"></td>').html(price));
		tr
				.append($(
						'<td id="quantity'
								+ rowCount
								+ '" contenteditable="true" onkeyup="javascript:dotest(event,'
								+ rowCount + ');" ></td>').html("1"));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		//alert(rowCount);
		$('#rentaltable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : price,
			"productId" : productId,
			"customerId" : customerId,
			"quantity" : quantity,
			"note" : note
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		CalculateTotal();
	}
	
	$("#productDetailsPojo").change(function(){
		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#rentaltable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var customerId = $('#customerPojo :selected').val();
		var note = $('#note').text();
		var quantity = 1;

		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"></td>').html(price));
		tr
				.append($(
						'<td id="quantity'
								+ rowCount
								+ '" contenteditable="true" onkeyup="javascript:dotest(event,'
								+ rowCount + ');" ></td>').html("1"));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		//alert(rowCount);
		$('#rentaltable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : price,
			"productId" : productId,
			"customerId" : customerId,
			"quantity" : quantity,
			"note" : note
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		CalculateTotal();
	});


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
	function deleteItems(count){
		$("#row"+count).remove();
		var table = document.getElementById("rentaltable");
		var rows = table.getElementsByTagName("tr");
		for (var i = 1; i <= rows.length-1; i++) {
		  rows[i].setAttribute("id", "row" + (i));
		 // rows[i].setAttribute("serial",(i+1));
		  var cells = rows[i].cells;
		  for (var j = 0; j < cells.length -3 ; j++) {
		    cells[0].textContent = i;
		    cells[3].setAttribute("id", "price" + (i));
		    cells[3].setAttribute("onkeyup", "javascript:dotest(event,'"+(i)+"')" );
            cells[4].setAttribute("id", "quantity" + (i));
            cells[4].setAttribute("onkeyup", "javascript:dotest(event,'"+(i)+"')" );
            cells[5].setAttribute("id", "subtotal" + (i));
            cells[6].setAttribute("onclick", "deleteItems('"+(i)+"')");
		  }
		}
	
		CalculateTotal();
	}
</script>
