
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
							<h3 class="card-title">Add Quotes</h3>
						</div>
						<!-- /.card-header -->
						<!-- form start 
            <form  method="POST" action="${pageContext.request.contextPath}/addSales" autocomplete="off" name="Sales" id="Sales" enctype="multipart/form-data">
            -->
						<form autocomplete="off" name="Sales" id="itemForm"
							enctype="multipart/form-data">
							<div class="card-body">


								<div class="form-group">
									<label>Select Customer</label><font color="red"></font> <select
										class="form-control select2bs4" name="customerId"
										id="customerPojo" style="width: 100%;" required>
									</select>
								</div>

								<div class="form-group">
									<label for="exampleInputEmail1">Select Product</label><font
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
									<table id="quotestable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Net Unit Price</th>
												<th>Quantity</th>
												<th>Sub Total (BZD )</th>
											</tr>

										</thead>
										<tbody>

										</tbody>
										<tfoot>
											<td colspan=4>Total</td>
											<td>Quantity</td>
											<td>Total (BZD )</td>
										</tfoot>

									</table>
								</div>
								<!-- /.card-body -->


								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
										placeholder="Enter Note">
								</div>


							</div>
							<!-- /.card-body -->

							<div class="card-footer">
							
										
										
								<button type="button" class="btn btn-primary"
									onclick="getQuotes()">Submit
								</button>
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
						'<option value="' + data.id + '">' + data.userName
								+ '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});

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
						'<option data-price="'+data.price+'" value="' + data.productId + '">'
								+ data.name + '</option>');
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});

	var productList = [];
	
	
	// Old Get sales -- 
	/*	function getSales() {
	 var form = $('#itemForm')[0];
	 var data = new FormData(form);
	 var myJSON = JSON.stringify(productList);
	 myJSON = myJSON.split(']}]"}').join(']}]}');
	 myJSON = myJSON.replace(/\\/g, "");
	 myJSON = myJSON.split('"[{').join('[{');
	 myJSON = myJSON.split('"}]"').join('"}]');
	 console.log("myJSON======" + myJSON);

	 data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

	 $.ajax({
	 type : 'post',
	 dataType : 'json',
	 url : '${pageContext.request.contextPath}/addSales',
	 contentType : 'application/json; charset=utf-8',
	 crossDomain : true,
	 data : myJSON,
	 success : (function(response) {
	 console.log(response);
	 })
	 });
	 }
	 */

	// New GteSales -- making JSON from table elements
	function getQuotes() {
		 
		 var customerId = $('#customerPojo :selected').val();
		 var note = $('#note').text();
		 
		 var _table = document.getElementById("quotestable");
		 var _trLength = _table.getElementsByTagName("tr").length -1;
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
		    	  productId     : _data[1]
		         ,productName   : _data[2]
		     	 ,price         : _data[3]
		    	 ,customerId    : customerId
		         ,quantity      : _data[4]
		    	 , note			: note
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

		 data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

	//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addQuotes',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success : (function(response) {
				console.log(response);
			})
		});
	}

	function ChangeSubTotal(price, quantity, rowCount) {

		alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}
	
	function dotest(event,rowCount)
	{
	    //GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
	   alert("rowCount " + rowCount);
	   
	   var price = $('#price' + rowCount).html();
	   var quantity = $('#quantity' + rowCount).html();
	   alert("quantity " + quantity);
	   alert("price " + price);
	   var subtotal = quantity * price;
	   alert("subtotal " + subtotal);
	   $('#subtotal' + rowCount).html(subtotal);
	 }

	function addItem() {

		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#quotestable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var customerId = $('#customerPojo :selected').val();
		var note = $('#note').text();
		var quantity = 1;

		var tr = $('<tr></tr>');
		tr.append($('<td></td>').html(rowCount));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"></td>').html(price));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html("1"));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<tr></tr>').html());
		alert(rowCount);
		$('#quotestable tbody').append(tr);

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
</script>
