
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
.row{
	justify-content: center;
}
</style>
<!-- Content Wrapper. Contains page content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row" >
				<!-- left column -->
				<div class="col-lg-10 col-md-12" style="padding-top: 40px">
					<!-- general form elements -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Edit Return</h3>
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
										id="customerPojo" style="width: 100%;" readonly>
									</select>
								</div>
								<input type="hidden" class="form-control" id="memberid"
									name="memberid" value="${returnPojo.memberid}"> <input
									type="hidden" class="form-control" id="saleid" name="saleid"
									value="${returnPojo.saleid}"><input type="hidden" class="form-control" id="memberunit"
									value="${memberPojo.ctype}"><input
									type="hidden" class="form-control" id="salereferenceno" name="salereferenceno"
									value="${returnPojo.salereferenceno}">

								<div class="form-group">
									<label for="exampleInputEmail1">Select Invoice</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="salesId" id="invoicePojo" onChange="loadProducts()"
										readonly>
									</select>
									<!--	<button type="button" style="font-size: 14px;" class="btn btn-primary" data-toggle="modal"
										data-target="#modal-lg" onclick="ViewDetails()">View Items </button> -->
								</div>
								<div class="form-group">
									<label>Select Product</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="productCode" id="productDetailsPojo" style="width: 100%;"  required>
									</select>
								</div>
								<div class="form-group">
									<label>Select Unit</label><font color="red"></font> <select
										class="form-control select2bs4" name="unitId" id="unitId"
										style="width: 100%;" required>
									</select>
								</div>

								


								<div class="form-group">
									<label for="exampleInputEmail1">Apply 10% Re Stocking
										Fees</label> <input type="checkbox" id="restock">
								</div>

								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>
									<button class="btn btn-primary" onclick="reset()">Reset</button>
								</div>


								<!--<div class="form-group">
									<label for="exampleInputEmail1">Apply to Invoice</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="salesId" id="applyinvoicePojo">
									</select>
								</div>-->

								<!-- /.card-header -->

								<div class="card-body pl-0 pr-0">
									<table id="returntable"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Net Unit Price</th>
												<th>Quantity</th>
												<th>Unit of Measure</th>
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
									name="returnid" id="returnid" value="${returnPojo.returnId}">

								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
												value="${returnPojo.note}">
								</div>
								
								<div class="form-group">
									<label for="exampleInputEmail1">Apply Return As</label> 
									</br>
									
									<c:choose>
                                <c:when test="${returnPojo.returnType == 1}">
                                <input type="radio" name="applyReturn" value="1" checked> Apply to Pending Invoice
                    				</br>
                               </c:when>
                              <c:otherwise>
                               <input type="radio" name="applyReturn" value="1" > Apply to Pending Invoice
                    				</br>
                             </c:otherwise>
                                  </c:choose>
                                  
                                  <c:choose>
                                <c:when test="${returnPojo.returnType == 2}">
                                <input type="radio" name="applyReturn" value="2" checked> Store as Credit
                    				</br>
                               </c:when>
                              <c:otherwise>
                               <input type="radio" name="applyReturn" value="2" > Store as Credit
                    				</br>                             
                    			</c:otherwise>
                                  </c:choose>
                                  
                                 <c:choose>
                                <c:when test="${returnPojo.returnType == 3}">
                              <input type="radio" name="applyReturn" value="3" checked> Give a Refund
                               </c:when>
                              <c:otherwise>
                              <input type="radio" name="applyReturn" value="3" > Give a Refund                      
                    			</c:otherwise>
                                 </c:choose>
						
								</div>


							</div>
							<!-- /.card-body -->

							<div class="card-footer">

								<button type="button" class="btn btn-primary"
									onclick="Returnvalidation()">Edit Return</button>
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
	var productList = [];

	window.onload = function() {

		var memberid = $('#memberid').val();
		var ctype = $('#memberunit').val();
		var returnId = $('#returnid').val();
		var saleid = $('#saleid').val();
		var salereferenceno = $('#salereferenceno').val();
		
		//alert(ctype);
		//alert(salereferenceno);
		$.ajax({
			url : '${pageContext.request.contextPath}/getCustomer',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);

				$.each(data, function(i, data) {
					if (memberid == data.id) {
						$('#customerPojo').append(
								'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '" selected>'
										+ data.userName + '</option>');
					} else {
						$('#customerPojo').append(
								'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '">'
										+ data.userName + '</option>');
					}
				});
				//loadInvoice();

			},
			error : function(error) {

				console.log(`Error ${error}`);
			}

		});

		if (ctype != "Special") {
			//alert("General");
			$
					.ajax({
						url : '${pageContext.request.contextPath}/getSalebyMemberId?memberId='+ memberid,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							  var allPaid = data.every(function(item) {
						            return item.paymentstatus === "Paid";
						        });

							$.each(data, function(i, data) {
								if (salereferenceno == data.referenceno) {
									//alert( data.referenceno);
									$('#invoicePojo').append(
											 '<option data-referenceno="' + data.referenceno + '" data-paymentstatus="' + data.paymentstatus + '" value="' + data.saleId + '" selected>'
										        + data.referenceno + ' - '
										        + data.member_name
										        + ' (' + data.paymentstatus + ')' 
										        + '</option>'
										    );
								} else {
									$('#invoicePojo').append(
											'<option data-referenceno="' + data.referenceno + '" data-paymentstatus="' + data.paymentstatus + '" value="' + data.saleId + '">'
										    + data.referenceno + ' - '
										    + data.member_name
										    + ' (' + data.paymentstatus + ')' 
										    + '</option>');
								}

							});
							loadProducts();
							 if (allPaid) {

								 $("input[name='applyReturn'][value='1']").hide();
								 $("input[name='applyReturn'][value='2']").show().prop('checked', true);
						        } else {
						            $("#applyToPendingInvoice").show();
						        }

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});

			$
					.ajax({
						url : '${pageContext.request.contextPath}/getPendingSalebyMemberId?memberId='+ memberid,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							
							$.each(data, function(i, data) {
								$('#applyinvoicePojo').append(
										'<option data-referenceno="'+data.referenceno +  '" value="' + data.saleId + '">'
												+ data.referenceno + ' - '
												+ data.member_name
												+ '</option>');
							});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
		} else {
			//alert("Special");
			$
			.ajax({
				url : '${pageContext.request.contextPath}/getSpecialSalebyMemberId?memberId='+ memberid,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					 var allPaid = data.every(function(item) {
				            return item.paymentstatus === "Paid";
				        });

					$.each(data, function(i, data) {
						if (saleid == data.saleId) {
							$('#invoicePojo').append(
									 '<option data-referenceno="' + data.referenceno + '" data-paymentstatus="' + data.paymentstatus + '" value="' + data.saleId + '" selected>'
								        + data.referenceno + ' - '
								        + data.member_name
								        + ' (' + data.paymentstatus + ')' 
								        + '</option>');
						} else {
							$('#invoicePojo').append(
									'<option data-referenceno="' + data.referenceno + '" data-paymentstatus="' + data.paymentstatus + '" value="' + data.saleId + '">'
								    + data.referenceno + ' - '
								    + data.member_name
								    + ' (' + data.paymentstatus + ')' 
								    + '</option>');
						}

					});
					loadProducts();

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});

			$
					.ajax({
						url : '${pageContext.request.contextPath}/getPendingSpecialSalebyMemberId?memberId='+ memberid,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$.each(data, function(i, data) {
								$('#applyinvoicePojo').append(
										'<option data-referenceno="'+data.referenceno +  '" value="' + data.saleId + '">'
												+ data.referenceno + ' - '
												+ data.member_name
												+ '</option>');
								$('#invoicePojo').append(
										'<option data-referenceno="'+data.referenceno +  '" value="' + data.saleId + '">'
												+ data.referenceno + ' - '
												+ data.member_name
												+ '</option>');
							});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
		}

		$
				.ajax({
					url : '${pageContext.request.contextPath}/getReturnitembyreturnId?returnId='
							+ returnId,
					type : "GET",
					dataType : "json",
					success : function(data) {
						var ajaxCallData = JSON.stringify(data);

						$
								.each(
										data,
										function(i, data) {

											var productid = data.productid;
											var rowCount = $('#returntable tr').length;
											var productname = data.product_name;
											var unitname = data.unitname;
											var unitprice = data.real_unit_price;
											var unit = data.unitid;
											var customerId = $('#memberid').val();
											var note =$('#note').text();
											var quantity = data.quantity;
											var subtotal = data.subtotal;

											var tr = $("<tr id='row"+rowCount+"'></tr>");
											tr.append($('<td></td>').html(
													rowCount - 1));
											tr.append($('<td></td>').html(
													productid));
											tr.append($('<td id="productname'+rowCount+'"></td>').html(
													productname));
											tr
													.append($(
															'<td id="price'+rowCount+'"></td>')
															.html(unitprice));
											tr
													.append($(
															'<td id="quantity'+rowCount+'"contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>')
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
															'<td id="subtotal'+rowCount+'"></td>')
															.html(subtotal));
											tr
													.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('
															+ rowCount
															+ ')">Delete</button>'));
											tr.append($('<tr></tr>').html());
											$('#returntable tbody').append(tr);

											var myJSON = {
												"productName" : productname,
												"price" : unitprice,
												"productId" : returnId,
												"customerId" : customerId,
												"roll" : unitname,
												"quantity" : quantity,
												"subtotal" : subtotal,
												"note" : note,
												"unit" : unit
											};

											productList.push(myJSON);
											CalculateTotal();
											//	document.getElementById("productList").val()=productList;
											console.log(productList);
										});

					},
					error : function(error) {
						console.log(`Error ${error}`);
					}
				});

		

	}

	function deleteItems(count) {
		$("#row"+count).remove();
		var table = document.getElementById("returntable");
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

	function loadInvoice() {

		$("#productDetailsPojo").empty();
		$("#invoicePojo").empty();
		$("#applyinvoicePojo").empty();

		var memberId = $('#customerPojo :selected').val();
		var ctype = $('#customerPojo :selected').data('ctype');
		// alert(ctype);

		if (ctype != "Special") {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/getSalebyMemberId?memberId='+ memberId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$('#invoicePojo').append(
									$("<option></option>").attr("value", "0")
											.text("Select"));
							$.each(data, function(i, data) {
								$('#invoicePojo').append(
										'<option data-saleId="'+data.saleId +  '" value="' + data.saleId + '">'
												+ data.saleId + ' - '
												+ data.member_name
												+ '</option>');
							});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});

			$
					.ajax({
						url : '${pageContext.request.contextPath}/getPendingSalebyMemberId?memberId='+ memberId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$.each(data, function(i, data) {
								$('#applyinvoicePojo').append(
										'<option data-saleId="'+data.saleId +  '" value="' + data.saleId + '">'
												+ data.saleId + ' - '
												+ data.member_name
												+ '</option>');
							});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
		} else {
			
			$
			.ajax({
				url : '${pageContext.request.contextPath}/getSpecialSalebyMemberId?memberId='+ memberId,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					$('#invoicePojo').append(
							$("<option></option>").attr("value", "0")
									.text("Select"));
					$.each(data, function(i, data) {
						$('#invoicePojo').append(
								'<option data-saleId="'+data.saleId +  '" value="' + data.saleId + '">'
										+ data.saleId + ' - '
										+ data.member_name
										+ '</option>');
					});

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});

			$
					.ajax({
						url : '${pageContext.request.contextPath}/getPendingSpecialSalebyMemberId?memberId='+ memberId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							$.each(data, function(i, data) {
								$('#applyinvoicePojo').append(
										'<option data-saleId="'+data.saleId +  '" value="' + data.saleId + '">'
												+ data.saleId + ' - '
												+ data.member_name
												+ '</option>');
								$('#invoicePojo').append(
										'<option data-saleId="'+data.saleId +  '" value="' + data.saleId + '">'
												+ data.saleId + ' - '
												+ data.member_name
												+ '</option>');
							});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

					});
		}

		$('#applyinvoicePojo').prop("selectedIndex", 1);

	}
	function loadProducts() {

		$("#productDetailsPojo").empty();

		var saleId = $('#invoicePojo :selected').val();

		var ctype = $('#customerPojo :selected').data('ctype');
		//alert(ctype);
		//	alert(saleId);

		if (ctype != "Special")

		{

			$
					.ajax({
						url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId='
								+ saleId,
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);

							$('#productDetailsPojo').append(
									$("<option></option>").attr("value", "0")
											.text("Select"));
							$
									.each(
											data,
											function(i, data) {
												$('#productDetailsPojo')
														.append(
																'<option data-price="'+data.real_unit_price + '" data-returnqty="'+data.returnqty+'" data-quantity="'+data.quantity+'"data-roll="'+data.roll+'" value="' + data.product_id + '">'
																		+ data.product_name
																		+ data.roll
																		+ '</option>');
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

							$('#productDetailsPojo').append(
									$("<option></option>").attr("value", "0")
											.text("Select"));
							$
									.each(
											data,
											function(i, data) {
												$('#productDetailsPojo')
														.append(
																'<option data-price="'+data.real_unit_price + '" data-quantity="'+data.quantity+'"data-roll="'+data.roll+'" value="' + data.product_id + '">'
																		+ data.product_name
																		+ data.roll
																		+ '</option>');
											});

						},
						error : function(error) {
							console.log(`Error ${error}`);
						}
					});

		}
		// Initialize select2
		$("#productDetailsPojo").select2();

	}
	$.ajax({
		url : '${pageContext.request.contextPath}/getUnits',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$.each(data, function(i, data) {
				$('#unitId').append(
						'<option value="' + data.id + '">' + data.unitname
								+ '</option>');

			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});

	function addReturn() {
		
	
		var isOldCashReturn =false;
	    var applyReturn= $('input[name="applyReturn"]:checked').val();
		var customerId = $('#customerPojo :selected').val();
		var note = $('#note').val();
		var returnid = $('#returnid').val();
		var saleid =  $('#invoicePojo :selected').val();
	

		var ctype = $('#customerPojo :selected').data('ctype');
		var applysaleId = $('#invoicePojo :selected').val();

		var _table = document.getElementById("returntable");
		var _trLength = _table.getElementsByTagName("tr").length - 1;
		var _jsonData = [];
		var _obj = {};

		var _htmlToJSON = function(index) {
			var _tr = _table.getElementsByTagName("tr")[index];
			var _td = _tr.getElementsByTagName("td");
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
				note : note,
				applysaleId : applysaleId,
				subtotal : _data[7],
				roll : _data[5],
				unit : _data[6]
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
		myJSON = myJSON.split('"[{').join('[{');
		myJSON = myJSON.split('"}]"').join('"}]');
		console.log("myJSON======" + myJSON);

		data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");


		$
				.ajax({
					type : 'post',
					dataType : 'json',
					url : '${pageContext.request.contextPath}/updateAllReturn?returnid='+ returnid +'&applyReturn='+applyReturn +'&isOldCashReturn='+isOldCashReturn,
					contentType : 'application/json; charset=utf-8',
					crossDomain : true,
					data : myJSON,
					success : function(data) {
						
				        if(applyReturn != 3)
						window.location.href = "${pageContext.request.contextPath}/viewReturnNew";
						else
							window.location.href = "${pageContext.request.contextPath}/viewReturnscash";
									
					},
					error : function(error) {
						console.log(`Error ${error}`);
					}
				});

		$("#returntable  tbody").empty();
		document.getElementById("note").value = "";
		
		
	
	}
	
	
	function Returnvalidation() {
		 var memberId = $('#customerPojo :selected').val();
		 var applyReturn= $('input[name="applyReturn"]:checked').val();
		 var ctype = $('#customerPojo :selected').data('ctype');
		 
		 if(applyReturn==1){
			 
			 if (ctype == "Special") {
		 $.ajax({
			 		
				 url : '${pageContext.request.contextPath}/getPendingSpecialSalebyMemberId?memberId=' + memberId,
						
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					 if (data.length == 0) {		             
		                    alert("There are no pending invoices.");
		                    return false;
		                }
					 else{
						  
						 addReturn();				
					 }
				},
				error : function(error) {
					console.log(`Error ${error}`);
					 return false;
				}

				});
		 
			 }else {
				 
				 $.ajax({
					 
			
						url : '${pageContext.request.contextPath}/getPendingSalebyMemberId?memberId=' + memberId,									
						type : "GET",
						dataType : "json",
						success : function(data) {
							var ajaxCallData = JSON.stringify(data);
							 if (data.length == 0) {
				                    // No pending invoices, show an alert.
				                    alert("There are no pending invoices.");
				                    return false;
				                }
							 else{
								 addReturn();
							
							 }
						},
						error : function(error) {
							console.log(`Error ${error}`);
							 return false;
						}

						});
				 
				 
				 
			 }
		 }
		 else{
			 addReturn();
			
		 }

	}
	
	
	
	
	
	function addItem() {

		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#returntable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var origprice = $('#productDetailsPojo :selected').data('price');
		var discount = $('#productDetailsPojo :selected').data('promotion');
		var customerId = $('#customerPojo :selected').val();
		var unit = $('#unitId :selected').val();
		var unitname = $('#unitId :selected').text();
		var note = $('#note').val();
		var quantity = "1";
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');
		var roll = "NO";
		var unitprice = $('#productDetailsPojo :selected').data('roll');
		
		

		newprice = newprice.toFixed(2)
		var percentage = 0;
		percentage = $('#salespercent :selected').val();
		var restock = document.getElementById("restock");

		if (percentage > 0) {
			newprice = (origprice + ((origprice * percentage) / 100))
					.toFixed(2);

			if (ctype != "WholeSellers" && discount > 0) {

				newprice = (newprice - ((newprice * discount) / 100))
						.toFixed(2);

			}
		}

		if (unit == 1) {
			price = (newprice * quantity).toFixed(2);
			if (restock.checked) {

				  newprice=(newprice - (newprice * 0.1)).toFixed(2);
				   //alert(newprice);
					 price = (price - (price * 0.1)).toFixed(2);

			}
		} else {
			if (rollprice > 0) {
				roll = "YES";
				price = rollprice.toFixed(2);
				newprice = rollprice.toFixed(2);
				//	alert(roll);
				if (percentage > 0) {
					newprice = (+newprice + ((+newprice * percentage) / 100))
							.toFixed(2);
					price = (+price + ((+price * percentage) / 100)).toFixed(2);
					//		alert(newprice);
					if (ctype != "WholeSellers" && discount > 0) {

						newprice = (+newprice - ((+newprice * discount) / 100))
								.toFixed(2);
						price = (+price - ((+price * discount) / 100))
								.toFixed(2);

						if (restock.checked) {

							price = (price - (price * 0.1)).toFixed(2);
							//	 alert(price);

						}

					}
				}
			}
		}

		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount - 1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td id="productname'+rowCount+'"></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"></td>').html(newprice));
		tr
				.append($(
						'<td id="quantity'
								+ rowCount
								+ '" contenteditable="true" onkeyup="javascript:dotest(event,'
								+ rowCount + ');" ></td>').html("1"));
		tr.append($('<td id="unitname'+rowCount+'"></td>').html(unitprice));

		tr.append($('<td hidden id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr
				.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('
						+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#returntable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : price,
			"productId" : productId,
			"customerId" : customerId,
			"roll" : roll,
			"quantity" : quantity,
			"subtotal" : price,
			"note" : note,
			"unit" : unit
		};

		productList.push(myJSON);
		CalculateTotal();

		//	document.getElementById("productList").val()=productList;
		console.log(productList);
	}
	function CalculateTotal() {
		var grandT = 0;
		var qtY = 0;
		var taxT = 0;
		var totalT = 0;
		$("#returntable > TBODY > tr").each(function() {
			var t2 = $(this).find('td').eq(4).html();
			var t3 = $(this).find('td').eq(7).html();
			if (!isNaN(t3) && !isNaN(t2)) {
				grandT += parseFloat(t3);
				qtY += parseFloat(t2);
			}
		});

		taxT = grandT * .125;
		totalT = grandT + taxT;
		$("#gTotal").html(grandT.toFixed(2));
		$("#qTotal").html(qtY.toFixed());
		$("#taxTotal").html(taxT.toFixed(2));
		$("#tTotal").html(totalT.toFixed(2));
	

	}
	function dotest(event,rowCount)
	{
		var pname= $('#productname' + rowCount).html();
		// alert(pname);
		 var price = $('#price' + rowCount).html();
		var restock = document.getElementById("restock");
		
		$("#productDetailsPojo option:contains(" +pname +")").attr('selected',true);
		

		
		 var qty= $('#productDetailsPojo :selected').data('quantity');
		 var rqty=$('#productDetailsPojo :selected').data('returnqty');
		// alert(rqty);
		// alert(qty);
		 var rqty=0;
		  
		   
		 
		   var quantity = $('#quantity' + rowCount).html();
		   var q=0;
		   q=(+quantity+(+rqty));
		//   alert(q);
		   if (restock.checked){
				 
				 //price = (price - (price * 0.1)).toFixed(2);
			//	 alert(price);
				 var subtotal = quantity * price;
				   subtotal = subtotal.toFixed(2)
						  
				   $('#subtotal' + rowCount).html(subtotal);
			     
			 }
		   
		   if(q>qty) {
			   alert("This quantity exceeds the invoice quantity,"
			   		+ "The invoice quantity is:"+qty);
			   qty= $('#productDetailsPojo :selected').data('quantity');
			   $('#quantity' + rowCount).html(qty);
			//   alert(qty);
			   var subtotal = qty * price;
			   subtotal = subtotal.toFixed(2)
			   $('#subtotal' + rowCount).html(subtotal);
			 
		   }
		   else {
		   var subtotal = quantity * price;
		   subtotal = subtotal.toFixed(2)
				  
		   $('#subtotal' + rowCount).html(subtotal);
		   }
		   CalculateTotal();
		 	

	 	
	 }

</script>
