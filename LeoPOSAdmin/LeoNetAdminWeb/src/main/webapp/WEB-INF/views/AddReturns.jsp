
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" /> 

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

<!-- jQuery --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 

	

<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
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
							<h3 class="card-title">Add Return</h3>
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
										id="customerPojo"  onChange="loadInvoice()" style="width: 100%;" required>
									</select>
								</div>
								
								<div class="form-group">
									<label >Select Invoice</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="salesId" id="invoicePojo"  onChange="loadProducts()"  Required>
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
										class="form-control select2bs4" name="unitId"
										id="unitId" style="width: 100%;" required>
									</select>
								</div>
								
								
								
								<div class="form-group">
									<label for="exampleInputEmail1">Apply 10% Re Stocking Fees</label> <input type="checkbox"  id="restock" >
								</div>	
								
								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>
										 <button class="btn btn-primary" type="reset">Reset</button>
								</div>
								
								
								<!--<div class="form-group">
									<label for="exampleInputEmail1">Apply to Invoice</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="salesId" id="applyinvoicePojo"  >
									</select>
								</div>-->
								
								<!-- /.card-header -->
								<div class="card-body pl-0 pr-0">
									<table id="returntable" class="table table-bordered table-hover">
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
										<tbody >
										
										</tbody>
										<tfoot>
										<td class="row">
										<div class="text-center"><Strong>Total</Strong></div>
										<div class="text-left pr-3" id="qTotal"></div>
										<div class="text-right" id="gTotal"></div>
									</td>
									<td class="row">
										<div class="text-center"><strong>Tax</strong></div>
										<div class="text-right"></div>
										<div class="text-right" id="taxTotal"></div>
									</td>
									<td class="row">
										<div class="text-center"><strong>Grand Total</strong></div>
										<div class="text-right"></div>
										<div class="text-right" id="tTotal"></div>
									</td>
										</tfoot>

									</table>
								</div>
								<!-- /.card-body -->
								<input type="hidden" class="form-control" name="ctype" id="ctype">

								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
										placeholder="Enter Note">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1" id="returntype">Apply Return As</label> 
									</br>
									<input type="radio" name="applyReturn" value="1" checked> Apply to Pending Invoice
                    				</br>
                    				<input type="radio" name="applyReturn" value="2" > Store as Credit
                    				</br>
                    				<input type="radio" name="applyReturn" value="3" > Give a Refund
					    
								</div>
								
									

							</div>
							<!-- /.card-body -->

							<div class="card-footer">
									
								<button type="button" class="btn btn-primary"	onclick=" Returnvalidation()">Add Return </button>
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

<div class="modal fade" id="modal-lg">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-times fa-sm text-black-50"></i>

				</button>

	
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="salesReceipt"
							class="table table-bordered table-hover table-striped print-table order-table text-center"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead class="text-center">

								<tr>
									<th align="center">No.</th>
									<th align="center">Description</th>
									<th align="center">Quantity</th>
									<th align="center">Unit Price</th>
									<th align="center">Subtotal</th>
								</tr>

							</thead>

							<tbody align="center">


							</tbody>
							
						</table>
			
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>





<!-- Select2 JS --> 
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>


<script type="text/javascript">

$(document).ready(function(){
	
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
 						'<option value="' + data.id + '"' + 'data-ctype="' + data.ctype + '">' + data.userName
 								+ '</option>');
 			});

 		},
 		error : function(error) {
 			console.log(`Error ${error}`);
 		}

 	});
	
	// Initialize select2
	  $("#customerPojo").select2();
	
	  $.ajax({
			url : '${pageContext.request.contextPath}/getUnits',
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				
				$.each(data, function(i, data) {
					$('#unitId').append(
							'<option value="' + data.id + '">'
									+ data.unitname + '</option>');
					
				});

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  $("#unitId").select2();

}); 

	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});

	
	
	var productList = [];
	
	
	// New addReturn -- making JSON from table elements


	function ChangeSubTotal(price, quantity, rowCount) {

		//alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}
	 $("#customerPojo").change(function() {
		
		 var cname = $('#customerPojo :selected').val();
		 if(cname=="385"){
			 $("input[name='applyReturn'][value='1']").hide();
		     $("input[name='applyReturn'][value='2']").hide();
			 $("input[name='applyReturn'][value='3']").prop('checked', true);
			
		 }
		 else{
			 $("input[name='applyReturn'][value='1']").show().prop('checked', true);
		     $("input[name='applyReturn'][value='2']").show(); 
		 }
	 });
	
	function dotest(event,rowCount)
	{
	  //GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
	  var pname= $('#productname' + rowCount).html();
		var restock = document.getElementById("restock");

	  //$("#productDetailsPojo option:contains(" +pname +")").attr('selected',true);
	  
	  var qty= $('#productDetailsPojo :selected').data('quantity');
	  var rqty= $('#productDetailsPojo :selected').data('returnqty');
	
	   var price = $('#price' + rowCount).html();
	   var quantity = $('#quantity' + rowCount).html();
	   
	   if (restock.checked){
			 
			// price = (price - (price * 0.1)).toFixed(2);
			 //alert(price);
		   var subtotal = quantity * price;
		   subtotal = subtotal.toFixed(2)
				  
		   $('#subtotal' + rowCount).html(subtotal);
		     
		 }
	  
	 
	   var q=0;
	   q=(+quantity+(+rqty));
	   
	   if(quantity==0) {
		  
		   alert("Quantity cannot be 0"); 
		   
		   $('#quantity' + rowCount).html(1);
		   var subtotal = 1 * price;
		   subtotal = subtotal.toFixed(2)
		   $('#subtotal' + rowCount).html(subtotal);
				 
	   }
	 
	//   alert(q);
	   
	 
	   if(q>qty) {
		   
		   alert("This quantity exceeds the invoice quantity");
		   qty= $('#productDetailsPojo :selected').data('quantity');
		   $('#quantity' + rowCount).html(qty);
		   //alert(qty);
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
		var quantity =  "1";
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');	
		var roll= "NO";
		var unitprice= $('#productDetailsPojo :selected').data('roll');	
		//alert(unitprice);
		
		newprice = newprice.toFixed(2)
		var percentage =0;
		percentage = $('#salespercent :selected').val();
		var restock = document.getElementById("restock");
		 var qty= $('#productDetailsPojo :selected').data('quantity');
		  var rqty= $('#productDetailsPojo :selected').data('returnqty');
		  
		   
		 //  var quantityy = $('#quantity' + rowCount).html();
		 // alert(qty);
		 //  alert(quantity);
		 
		   var q=0;
		   q=(+quantity+(+rqty));
		//   alert(q);
		   if(q>qty) {
			   alert("This quantity exceeds the invoice quantity");
		   }
		
		
		
		
		if(percentage > 0)
		{
			newprice = (origprice + (( origprice * percentage ) / 100)).toFixed(2) ;
			
			if( ctype != "WholeSellers" && discount>0){
			
				newprice = (newprice - (( newprice * discount ) / 100)).toFixed(2) ;
			 
			}
		}
		
		if(unit == 1 )
		{
		   price = (newprice * quantity).toFixed(2);
		   if (restock.checked){
			  // alert(newprice);
			   newprice=(newprice - (newprice * 0.1)).toFixed(2);
			   //alert(newprice);
				 price = (price - (price * 0.1)).toFixed(2);
			//	 alert(price);
			     
			 }
		}
		else
		 {
			if(rollprice > 0){
				roll="YES";
				price = rollprice.toFixed(2);
				newprice = rollprice.toFixed(2);
			//	alert(roll);
				if(percentage > 0)
				{
					newprice = (+newprice + (( +newprice * percentage ) / 100)).toFixed(2) ;
					price = (+price + (( +price * percentage ) / 100)).toFixed(2) ;
			//		alert(newprice);
					if( ctype != "WholeSellers" && discount>0){
					
						newprice = (+newprice - (( +newprice * discount ) / 100)).toFixed(2) ;
						price = (+price - (( +price * discount ) / 100)).toFixed(2) ;
						
						if (restock.checked){
							 
							 price = (price - (price * 0.1)).toFixed(2);
						//	 alert(price);
						     
						 }
					 
					}
				}
			}
		}

		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td id="productname'+rowCount+'"></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"></td>').html(newprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html("1"));
		tr.append($('<td id="unitname'+rowCount+'"></td>').html(unitprice));
		tr.append($('<td hidden id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#returntable tbody').append(tr);

		var myJSON = {
			"productName" : productName,
			"price" : price,
			"productId" : productId,
			"customerId" : customerId,
			"roll"       :roll,
			"quantity" : quantity,
			"subtotal":price,
			"note" : note,
			"unit": unit
		};

		productList.push(myJSON);
	
		//	document.getElementById("productList").val()=productList;
 		console.log(productList);
 		
 	// Get customer list
 		
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
 		CalculateTotal();
 		
	}
	

	function CalculateTotal() {
        var grandT = 0;
        var qtY = 0;
        var taxT =0;
        var totalT =0;
        $("#returntable > TBODY > tr").each(function () {
        	var t2 = $(this).find('td').eq(4).html();
            var t3 = $(this).find('td').eq(7).html();
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
	
	document.querySelector('form').addEventListener('reset', function(event) {
		  if (!confirm('Are you sure you want to reset?')) {
		    event.preventDefault();
		  }
		  else{
			  window.location.reload(true);
		  }
		});
	function deleteDetails(count) {
		var x = confirm("Are you sure you want to delete?");
		if (x) {
			//alert(count);
			var i=count-1;
			// var purchaseid = $("#purchaseid"+count).text();
			//$("#tr").remove();
			var row = document.getElementById("returntable");
			row.deleteRow(i);
			
		}
		CalculateTotal();
	}
	
	function loadInvoice(){
		
		$("#productDetailsPojo").empty();
		$("#invoicePojo").empty();
		$("#applyinvoicePojo").empty();
		
		 var memberId = $('#customerPojo :selected').val();
		 var ctype = $('#customerPojo :selected').data('ctype');
	
		// alert(ctype);
		 
		 if (ctype != "Special")
		  {		 
			$.ajax({
			url : '${pageContext.request.contextPath}/getSalebyMemberId?memberId=' + memberId,
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				$('#invoicePojo').append(
						$("<option></option>").attr("value", "0").text("Select"));
				  var allPaid = data.every(function(item) {
			            return item.paymentstatus === "Paid";
			        });
				  console.log("All Paid:", allPaid);
				$.each(data, function(i, data) {
					 var option = $('<option data-referenceno="' + data.referenceno + '" value="' + data.saleId + '"></option>');
			            option.data('paymentstatus', data.paymentstatus); // store paymentstatus in data attribute
			            option.text(data.referenceno + ' - ' + data.member_name); // display only referenceno and member_name
			            $('#invoicePojo').append(option);
				});
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
			// Initialize select2
			  $("#invoicePojo").select2();
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getPendingSalebyMemberId?memberId=' + memberId,
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				$.each(data, function(i, data) {
					 $('#applyinvoicePojo').append(
							'<option data-referenceno="'+data.referenceno +  '" value="' + data.saleId + '">'
									+ data.referenceno + ' - ' + data.member_name +  '</option>');		
				});

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

			});
		  } 
		 else {
			 $.ajax({
					url : '${pageContext.request.contextPath}/getSpecialSalebyMemberId?memberId=' + memberId,
					type : "GET",
					dataType : "json",
					success : function(data) {
						var ajaxCallData = JSON.stringify(data);
						$('#invoicePojo').append(
								$("<option></option>").attr("value", "0").text("Select"));
						  var allPaid = data.every(function(item) {
					            return item.paymentstatus === "Paid";
					        });
						  console.log("All Paid:", allPaid);
						$.each(data, function(i, data) {
							 var option = $('<option data-referenceno="' + data.referenceno + '" value="' + data.saleId + '"></option>');
					            option.data('paymentstatus', data.paymentstatus); // store paymentstatus in data attribute
					            option.text(data.referenceno + ' - ' + data.member_name); // display only referenceno and member_name
					            $('#invoicePojo').append(option);
						});
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
			 
			 $.ajax({
					url : '${pageContext.request.contextPath}/getPendingSpecialSalebyMemberId?memberId=' + memberId,
					type : "GET",
					dataType : "json",
					success : function(data) {
						var ajaxCallData = JSON.stringify(data);
						$.each(data, function(i, data) {
							 $('#applyinvoicePojo').append(
									'<option data-referenceno="'+data.referenceno +  '" value="' + data.saleId + '">'
											+ data.referenceno + ' - ' + data.member_name +  '</option>');		
							
						});

					},
					error : function(error) {
						console.log(`Error ${error}`);
					}

				});
	 	 }
		 
		 
		 $('#applyinvoicePojo').prop("selectedIndex", 1);
	//	 loadProducts();
		
	 // Here if Pending sales applyinvoicePojo are empty then load products  7th Aug 2022
	 
	 /*>if( $('#invoicePojo').has('option').length < 1 ) {
		 
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
							'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-promotion="' + data.promotion + 
							'" value="' + data.productId + '">'	+ data.name + ' - ' + data.cf1 +  '</option>');
				});

		},
		error : function(error) {
  			console.log(`Error ${error}`);
			}

		});
	 
    // Initialize select2
	   $("#productDetailsPojo").select2();
		 
		 
	 }*/
	 
	 
		
	}
	
	function ViewDetails(){
		
			$("#salesReceipt  tbody").empty();
		
		 	var saleId = $('#invoicePojo :selected').val();
						 
//			 alert(rquoteId);
			 $.ajax({
					url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId=' + saleId,
					type : "GET",
					dataType : "json",
					success : function(data) {
						var ajaxCallData = JSON.stringify(data);
						
						$.each(data, function(i, data) {
							
							var tr = $('<tr></tr>');
							tr.append($('<td></td>').html(data.id));
							tr.append($('<td></td>').html(data.product_name));
							tr.append($('<td></td>').html(data.quantity));
							tr.append($('<td></td>').html(data.real_unit_price));
							tr.append($('<td></td>').html(data.subtotal));
							tr.append($('<tr></tr>').html());
							$('#salesReceipt tbody').append(tr);
						});

					},
					error : function(error) {
						console.log(`Error ${error}`);
					}
				});
	   }
	
	function loadProducts(){
		
		$("#productDetailsPojo").empty();
	
	 	var saleId = $('#invoicePojo :selected').val();
	 	
	 	 var ctype = $('#customerPojo :selected').data('ctype');
		// alert(ctype);
		 //alert(saleId);
			 
	   if (ctype != "Special")
		
	   {	
					 
		 $.ajax({
				url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId=' + saleId,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					
					$('#productDetailsPojo').append(
							$("<option></option>").attr("value", "0").text("Select"));
					$.each(data, function(i, data) {
						$('#productDetailsPojo').append(
								'<option data-price="'+data.real_unit_price + '" data-returnqty="'+data.returnqty+'" data-quantity="'+data.quantity+'"data-roll="'+data.roll+'" value="' + data.product_id + '">'
										+ data.product_name +  data.roll+  '</option>');
					});

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		 
	   }
	   else
	   {
		   
		   $.ajax({
				url : '${pageContext.request.contextPath}/getSpecialSaleitembysaleId?saleId=' + saleId,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var ajaxCallData = JSON.stringify(data);
					
					$('#productDetailsPojo').append(
							$("<option></option>").attr("value", "0").text("Select"));
					$.each(data, function(i, data) {
						$('#productDetailsPojo').append(
								'<option data-price="'+data.real_unit_price + '" data-returnqty="'+data.returnqty+'" data-quantity="'+data.quantity+'"data-roll="'+data.roll+'" value="' + data.product_id + '">'
										+ data.product_name +  data.roll+  '</option>');
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
	var isProcessing = false;
	function addReturnvalidation() {
		 var _table = document.getElementById("returntable");
		 var _trLength = _table.getElementsByTagName("tr").length -1;
		 var _jsonData = [];
		 var _obj = {};
		 if(_trLength==1){
			 alert("Please add the product");
		 }else{
			  isProcessing = true;
		        addReturn();
		 }
		
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
		                    // No pending invoices, show an alert.
		                    alert("There are no pending invoices.");
		                }
					 else{
						  
						  addReturnvalidation();
					 }
				},
				error : function(error) {
					console.log(`Error ${error}`);
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
				                }
							 else{
								  
								  addReturnvalidation();
							 }
						},
						error : function(error) {
							console.log(`Error ${error}`);
						}

						});
				 
				 
				 
			 }
		 }
		 else{
			 addReturnvalidation();
		 }
		 //addReturnvalidation();
	}
		 
	
	
	
	
	// New addReturn -- making JSON from table elements
	function addReturn() {
		
		 
		 var customerId = $('#customerPojo :selected').val();
		 var note = $('#note').val();
		 
		 var ctype = $('#customerPojo :selected').data('ctype');
		 var applysaleId = $('#invoicePojo :selected').val();
		 var applyReturn= $('input[name="applyReturn"]:checked').val();
		 //alert(applyReturn);
		
		 var _table = document.getElementById("returntable");
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
		    	  productId     : _data[1],
		          productName   : _data[2],
		     	  price         : _data[3],
		    	  customerId    : customerId,
		          quantity      : _data[4],
		    	  note			: note,
		    	  applysaleId : applysaleId,
		    	  subtotal      :_data[7],
		    	  roll          :_data[5],
		    	  unit          :_data[6]
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
	if (applyReturn != "3"){
	 	$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addReturn?applyReturn=' + applyReturn,
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success :function(data) {
  	 			alertify
				  .alert(data.MsgDescr, function(){
					// location.reload();
					 window.location.replace('${pageContext.request.contextPath}/viewReturnNew');
					// location.reload();
				  }); 
  	 			location.reload();
  	 			
				 
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
	}else{
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addReturnCash',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success :function(data) {
  	 			alertify
				  .alert(data.MsgDescr, function(){
					// location.reload();
					 window.location.replace('${pageContext.request.contextPath}/viewReturnscash');
					// location.reload();
				  }); 
  	 			location.reload();
  	 			
				 
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
		
	}
	 	
		
		alert("Return added Succesfully !");
			   
		$("#returntable  tbody").empty();
		 document.getElementById("note").value="";
			if (applyReturn != "3"){
		 window.location.replace('${pageContext.request.contextPath}/viewReturnNew');
			}else{
				 window.location.replace('${pageContext.request.contextPath}/viewReturnscash');
			}
	}
	function deleteItems(count){
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
	
	
	
</script>
