
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
							<h3 class="card-title">Add Quote Request</h3>
						</div>
						<!-- /.card-header -->
						<!-- form start 
            <form  method="POST" action="${pageContext.request.contextPath}/addSales" autocomplete="off" name="Sales" id="Sales" enctype="multipart/form-data">
            -->
						<form autocomplete="off" name="QuoteReq" id="itemForm"
							enctype="multipart/form-data">
							<div class="card-body" >


								<div class="form-group">
									<label for="exampleInputEmail1">Select Product</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="productCode" id="productDetailsPojo" Required>
									</select>
								</div>
								
							
								<div class="form-group">
									<label for="exampleInputEmail1">Email Address</label> <input
										type="text" class="form-control" id="email"
										placeholder="Enter Email Address">
								</div>
						<div  id="contentToPrint">
								<div class="form-group">
									<label for="exampleInputEmail1">Supplier Name</label> <input
										type="text" class="form-control" id="sname"
										placeholder="Enter Suppliers Name">
								</div>

								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="salestable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Product MPN</th>
												<th>Quantity</th>
											</tr>

										</thead>
										<tbody>

										</tbody>

									</table>
								</div>
								<!-- /.card-body -->
							</div>

							</div>
							<!-- /.card-body -->

							<div class="card-footer">



								<button type="button" class="btn btn-primary"
									onclick="requestQuote()">Submit</button>

								<button type="button" class="btn btn-primary"
									onclick="RequisitionQuotePDF()">Export To PDF</button>

								<button type="button" class="btn btn-primary"
									onclick="RequisitionQuotePDF()">Export To PDF & Email</button>
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

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>



<script type="text/javascript">
	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
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
							$("<option></option>").attr("value", "0").text(
									"Select"));
					$
							.each(
									data,
									function(i, data) {
										$('#productDetailsPojo')
												.append(
														'<option data-price="'+data.price+ '" data-mpn="' + data.cf1 + '" value="' + data.productId + '">'
																+ data.name
																+ '</option>');
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
	function requestQuote() {

		//	 var customerId = $('#customerPojo :selected').val();
		var email = document.getElementById("email").value;
		var note = document.getElementById("sname").value;
	//	alert("Reached here *** ");
		var _table = document.getElementById("salestable");
		var _trLength = _table.getElementsByTagName("tr").length;
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
				price : _data[4],
				customerId : _data[4],
				quantity : _data[4],
				mpn : _data[3],
				note : note,
				email : email
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
		myJSON = myJSON.split('"}]"').join('"}]');
		console.log("myJSON======" + myJSON);

		data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/requestQuote',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success : (function(response) {
				console.log(response);
			})
		});
		
		alert("Request Quote Saved Successfully !");
	}

	function ChangeSubTotal(price, quantity, rowCount) {

		alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}

	function dotest(event, rowCount) {
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
		var rowCount = $('#salestable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var mpn = $('#productDetailsPojo :selected').data('mpn');
		var email = document.getElementById("email").value; 
		var note = document.getElementById("sname").value; 
	//	alert("Printing Email here *** ");
	//	alert(email);
		var quantity = 1;

		var tr = $('<tr></tr>');
		tr.append($('<td></td>').html(rowCount));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td></td>').html(mpn));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" ></td>').html("1"));
		tr.append($('<tr></tr>').html());
	//	alert(rowCount);
		$('#salestable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"productId" : productId,
			"quantity" : quantity,
			"mpn" : mpn,
			"note" : note,
			"email" : email
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
	
	function RequisitionQuotePDF() {
		var today = new Date();
		
		html2canvas(document.getElementById('contentToPrint'), {
			onrendered : function(canvas) {
				var data = canvas.toDataURL();
				var docDefinition = {
					content : [ {
						image : data,
						width : 500
					} ]
				};
				pdfMake.createPdf(docDefinition).download(
						"RequistionQuote" + today + ".pdf");
			}
		});
	}
	
	


</script>
