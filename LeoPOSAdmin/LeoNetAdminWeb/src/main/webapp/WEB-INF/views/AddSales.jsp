<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


	
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
							<h3 class="card-title">Add Sale</h3>
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
									<div class="input-group-append">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addCustomerModal">
                <i class="fas fa-plus"></i> New Customer
            </button>
        </div>
							</div>
							<div class="form-group">
									<label>Select Product</label><font
										color="red">*</font> <select class="form-control select2bs4"
										name="productCode" id="productDetailsPojo" style="width: 100%;"  required>
									</select>																	
							</div>
							<div class="form-group">
									<label>Select Unit</label><font color="red"></font> <select
										class="form-control select2bs4" 
										id="unitId" style="width: 100%;" required>
									</select>
							</div>
							
							<div class="form-group">
							<label for="exampleInputEmail1">Quantity</label>
							   <input type="text" class="form-control" id="pname" value="0" readonly>
							</div>
						
								 <sec:authorize access="hasAuthority('admin')">
							<div class="form-group">
									<label>Customer Type</label><font color="red"></font> <select
										class="form-control select2bs4" id="salespercent" style="width: 100%;" readonly>
										</select>
							</div>
							</sec:authorize>
                               

							<div class="form-group"  hidden>
									<label>Customer Type</label><font color="red"></font> <select
										class="form-control select2bs4" id="salespercent" style="width: 100%;" readonly>
										</select>
							</div>
							
							<div class="form-group">
									<label>Customer Credit Facility</label><font color="red"></font> <select
										class="form-control select2bs4" id="creditfacility" style="width: 100%;" readonly>
										<option val="YES">YES</option>
										<option val="NO">NO</option>
										</select>
										
							</div>
						
								
							
								<div class="card-footer">
									<button type="button" onclick="addItem()"
										class="btn btn-primary">Add</button>	
									  <button class="btn btn-primary"  type="reset">Reset</button>
								</div>
							
								<!-- /.card-header -->
								<div class="card-body pl-0 pr-0">
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
								<label for="exampleInputEmail1">PO</label><font
										color="red">*</font> <input type="text" 
									class="form-control" name="purchaseorder" id="purchaseorder"
									placeholder="Enter purchaseorder">
							</div>

								<div class="form-group">
									<label for="exampleInputEmail1">Note</label> <input type="text"
										class="form-control" name="note" id="note"
										placeholder="Enter Note">
										<small id="noteError" style="color: red; display: none;">Note format is invalid: '/' is not allowed.</small>
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
										
								<button type="button" class="btn btn-primary" id="myBtn"
									onclick="getSales()">Submit
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
	 						'<option value="' + data.id + '"' + 'data-creditfacility="' + data.creditfacility + '"'  + 'data-ctype="' + data.ctype + '" '+'data-pricegroup="'+data.pricegroup+'"'+' data-blocked="'+data.blocked+'"'+'data-creditamount="'+data.creditpayment+'">' + data.userName
	 								+ '</option>');
	 				
	 			});
	 			
	 		},
	 		error : function(error) {
	 			console.log(Error ${error});
	 		}

	 	});
		
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
				//var select;
				$.each(data, function(i, data) {
					//select = "";
					//var productId = localStorage.getItem("product");
					//if(productId && productId == data.productId){
					//  select = "selected";
					//}
					$('#productDetailsPojo').append(
							'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-quantity="' + data.quantity + 
							'" data-promotion="' + data.promotion + '" value="' + data.productId + '" >'	+ data.name + ' - ' + data.cf1 + ' - ' + data.code + '</option>');
				});
				
			},
			error : function(error) {
				console.log(Error ${error});
			}
			
		});
		
	 
	  // Initialize select2
	  $("#productDetailsPojo").select2({
		  matcher: function(params, data) {
			    // If there are no search terms, return all results
			    if ($.trim(params.term) === '') {
			      return data;
			    }
			    
			    // Split the search term into individual words
			    var terms = params.term.split(" ");

			    // Match each term individually
			    for (var i = 0; i < terms.length; i++) {
			      // If the term is not found in the data text, return null
			      if (data.text.toUpperCase().indexOf(terms[i].toUpperCase()) < 0) {
			        return null;
			      }
			    }

			    // If all terms are found, return the data
			    return data;
			  }
			});
	  
	  
	   
	}); 

	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});
	
	 document.getElementById('note').addEventListener('input', function () {
		    const noteInput = this.value;
		    const errorMessage = document.getElementById('noteError');
		    
		    // Check if the note contains '/'
		    if (noteInput.includes('/')) {
		        errorMessage.style.display = 'block'; // Show error message
		    } else {
		        errorMessage.style.display = 'none'; // Hide error message
		    }
		});

	

	
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
			console.log(Error ${error});
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
								+data.ctype + '</option>');
				
			});

		},
		error : function(error) {
			console.log(Error ${error});
		}

	});
	$("#productDetailsPojo").change(function() {
		  var productname = $('#productDetailsPojo :selected').text();
		  var product = $('#productDetailsPojo :selected').data('rollprice');
		  var productId = parseInt($('#productDetailsPojo :selected').val());
		  var selectedUnit = $("#unitId").val(); // Get the currently selected unit
		 //  alert(productId);
		    // Clear the dropdown options
		    $("#unitId").empty();
		 // alert(productId);
		 // alert(productname);
		 
		    if (productname.startsWith("Wire") && product == 0){
				  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			 
			  }
		 
		    else if (productname.startsWith("Wire")&&product!=0){
			  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			  $("#unitId").append('<option value="2" data-id="2">Wire Roll 328 Ft</option>');
		 
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
		  }
		  else if(productId==852 || productId==853 ||productId==854 ||  productId==855 || productId==856 || productId==857 || productId==858 || productId==859 || productId==860 || productId==861 || productId==862 || productId==863 ||productId==864 ||productId==1336 || productId==1581 || productId==1532 || productId==867 || productId==1679 ){
			  $("#unitId").append('<option value="1" data-id="8">10Ft</option>');
			  $("#unitId").append('<option value="9" data-id="9">20Ft</option>');
			  
		  }
		  else if(productId==1511 || productId==1512 ){
			  $("#unitId").append('<option value="1" data-id="10">Roll 100 Ft</option>');
			   
		  }
		  else if(productId==1513 || productId==1514 || productId==1515 || productId==1516 || productId==1521 || productId==1522  ){
			  $("#unitId").append('<option value="1" data-id="11">Box 50lb</option>');
			   
		  }
		  else if(productId==1517 || productId==1518 || productId==1519 || productId==1520 || productId==1523 || productId==1712){
			  $("#unitId").append('<option value="1" data-id="12">Box 55lb</option>');
			   
		  }
		  //Add this on 29-05-2025
		    else if (productId == 1678 || productId == 1680 || productId == 1681 || productId == 1682) {
		        $("#unitId").append('<option value="1" data-id="11">Box 50lb</option>');
		    }
		  else if(productId==1607  ){
			 
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="14" data-id="14">Box</option>');
			 
		  }
		  else if(productId==1608){
			 
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="15" data-id="15">Box</option>');
		  }
		  else if(productId==1609){
			 
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="16" data-id="16">Box</option>');
		  }
		  else if(productId==1610){
			
			  $("#unitId").append('<option value="1" data-id="13">lb</option>');
			  $("#unitId").append('<option value="17" data-id="17">Box</option>');
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
			
				  $("#unitId").append('<option value="1" data-id="1">Piece</option>');
					
		  }
	 });
	 $("#customerPojo").change(function() {
		//  alert("test");
		
		  var ctype = $('#customerPojo :selected').data('ctype');
		  var pricegroup = $('#customerPojo :selected').data('pricegroup');
		  //alert(ctype);
		  //alert(pricegroup);
		  var cfacility = $('#customerPojo :selected').data('creditfacility');
		  var blocked= $('#customerPojo :selected').data('blocked');
		  <sec:authorize access="hasAuthority('admin') || hasAuthority('salesplus')">
		  if(blocked==1){
			  let text = "Customer is blocked do you want to add sale?";
			  if (confirm(text) == false) {
				  window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
				  } else {
					//window.location.href = "${pageContext.request.contextPath}/addSales";
				  }
			 // window.location.href = "${pageContext.request.contextPath}/viewSales";
		  }
		  </sec:authorize>
		  <sec:authorize access="hasAuthority('sales')">
		  if(blocked==1){
			 alert("Customer is blocked.Sale cannot be added");
			  
			  window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
		  }
		  </sec:authorize>
		  
		 
		 if( ctype == "Special" && pricegroup == "WholeSellers")	
			 {
			  $("#salespercent option:contains(Special)").attr('selected', false);
			  $("#salespercent option:contains(WholeSellers)").attr('selected', true);
			  $("#salespercent option:contains(General)").attr('selected', false);
			  $("#salespercent option:contains(10Percentprice)").attr('selected', false);
			 }
		 else if( ctype == "WholeSellers")	{
			 $("#salespercent option:contains(WholeSellers)").attr('selected', true);		
			 $("#salespercent option:contains(Special)").attr('selected', false);
			 $("#salespercent option:contains(General)").attr('selected', false);
			 $("#salespercent option:contains(10Percentprice)").attr('selected', false);
		 }else if(ctype == "Special" && pricegroup == "Special"){
			 $("#salespercent option:contains(Special)").attr('selected', true);
			  $("#salespercent option:contains(WholeSellers)").attr('selected', false);
			  $("#salespercent option:contains(General)").attr('selected', false);
			  $("#salespercent option:contains(10Percentprice)").attr('selected', false);
		 }else if( ctype == "10Percentprice" && pricegroup == "TaxExemption")	
		 {
			  $("#salespercent option:contains(Special)").attr('selected', false);
			  $("#salespercent option:contains(WholeSellers)").attr('selected', false);
			  $("#salespercent option:contains(General)").attr('selected', false);
			  $("#salespercent option:contains(10Percentprice)").attr('selected', true);
			 }
		 else {
			 $("#salespercent option:contains(General)").attr('selected', true);	
			 $("#salespercent option:contains(Special)").attr('selected', false);
			 $("#salespercent option:contains(WholeSellers)").attr('selected', false);
			 $("#salespercent option:contains(10Percentprice)").attr('selected', false);
			// alert("reached");
			 
		 }
		 
		 if( cfacility == "YES")	
		 {
		  $("#creditfacility option:contains(YES)").attr('selected', true);
		  }
	     else  {
		 $("#creditfacility option:contains(NO)").attr('selected', true);		
		 $("#creditfacility option:contains(YES)").attr('selected', false);
		 
	   }
		 
	 });

	
	var productList = [];
	
	
	

	// New GetSales -- making JSON from table elements
	function getSales() {
		 
		 
		 var customerId = $('#customerPojo :selected').val();
		 var note = $('#note').val();
		 var taxrate = document.getElementById("taxrate");
		 var purchaseorder =$('#purchaseorder').val();
		 var creditamout =$('#customerPojo :selected').data('creditamount');
		 var applycreditpayment="0";
		// alert(customerId);
		 //alert(creditamout);
		 /*if(creditamout>"0"){
			 var applyCredit = confirm("Customer has a credit amount. Do you want to apply the credit amount for payment?");
			    if (applyCredit) {
			        alert("Credit amount will be applied for payment.");
			        applycreditpayment="1";
			        // Proceed with applying the credit amount
			    } else {
			        alert("Credit amount will not be applied. Proceeding with normal sale.");
			        applycreditpayment="0";
			        // Proceed with normal sale
			    }
		 }*/
	
		 if (creditamout > "0") {
			    Swal.fire({
			        title: 'Customer has a credit amount.',
			        text: 'Do you want to apply the credit amount for payment?',
			        icon: 'warning',
			        showCancelButton: true,
			        confirmButtonText: 'Yes, apply credit',
			        cancelButtonText: 'No, proceed with normal sale'
			    }).then((result) => {
			        if (result.isConfirmed) {
			            Swal.fire('Credit Applied', 'Credit amount will be applied for payment.', 'success');
			            applycreditpayment ="1";
			           // document.getElementById("yourFormId").submit();
			            // Proceed with applying the credit amount
			        } else {
			            Swal.fire('Normal Sale', 'Credit amount will not be applied. Proceeding with normal sale.', 'info');
			            applycreditpayment ="0";
			           // document.getElementById("yourFormId").submit();
			            // Proceed with normal sale
			        }
			       // alert(applycreditpayment);
			        handleSaleSubmission(customerId, note, taxrate, purchaseorder, applycreditpayment);
			    });
			}else{
				handleSaleSubmission(customerId, note, taxrate, purchaseorder, applycreditpayment);
			}
		
	
		 function handleSaleSubmission(customerId, note, taxrate, purchaseorder, applycreditpayment) {
		 var tax="YES";
		 if (customerId === '133') {
			   // alert("Reached customer 133");
			    tax = "NO";
			}
			
			else if (taxrate.checked) {
			 //   alert("Reached checked");
			    tax = "YES";
			}
		  else{
			 // alert("Reached else" );
		    	tax ="NO";
		      //  alert("You didn't check it! Let me check it for you.")
		    }
		  
		   
		 
		 var ctype = $('#customerPojo :selected').data('ctype');
		
		// alert(ctype);
		
		 
		 var _table = document.getElementById("salestable");
		 var _trLength =_table.getElementsByTagName("tr").length -1;
		 var _jsonData = [];
		 var _obj = {};
		// alert(_trLength);
		 if(customerId==0){
				alert("Please select customer ");
			}else if(_trLength==1){
				alert("Please select product ");
			}else{
		 

		 var _htmlToJSON = function(index){
				var _tr = _table.getElementsByTagName("tr")[index];
				var _td = _tr.getElementsByTagName("td");
		  //   var quantity = ($(_data[4]).text().trim()) ? parseInt($(this).text().trim()) : $(this).text().trim();
		 //    alert(quantity);
		     var _arr = [].map.call(_td, function(td) {
		    	  return td.innerText.trim();
		     }).join( ',' );
		     var _data = _arr.split(",");
		     
		     _obj = {
		    	  productId     : _data[1]
		         ,productName   : _data[2]
		     	 ,price         : _data[3]
		    	 ,customerId    : customerId
		         ,quantity      : _data[4]
		    	 , note			: note
		    	 , tax			: tax
		    	 ,subtotal      :_data[7]
		    	 ,roll          :_data[5]
		    	 ,unit          :_data[6]
		    	 ,purchaseorder :purchaseorder
		    	 ,applycreditpayment:applycreditpayment
		    	
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
		// console.log("myJSON" + myJSON);
		// myJSON = myJSON.replace(/\\/g,"");
		 //console.log("myJSON" + myJSON);
		 myJSON = myJSON.split('"[{').join('[{');
		 myJSON = myJSON.split('"}]"').join('"}]');
		 console.log("myJSON======" + myJSON);

		 data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");

	//	data.set("myJSON", "{\"addItemReqPojo\":" + myJSON + "}");
	
	   if (ctype != "Special")
	  {	 
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : '${pageContext.request.contextPath}/addSales',
			contentType : 'application/json; charset=utf-8',
			crossDomain : true,
			data : myJSON,
			success :function(data) {
  	 			//alertify
				//  .alert(data.msgDescr, function(){
					
					// window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
				 // });
					//	window.location.href = "${pageContext.request.contextPath}/viewSales";
					
			    	 alert(data.msgDescr);	
				  window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
			},
			error : function(error) {
				console.log(Error ${error});
			
			}
		});
		
		// alert("Sale added Succesfully !");
		}
	   else
		   {
		   $.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/addSpecialSales',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : myJSON,
				success :function(data) {
	  	 			//alertify
					 // .alert(data.msgDescr, function(){
					
						 alert(data.msgDescr);	
						 
						window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
						// location.reload();
					 // }); 
	  	 			//location.reload();
	  	 			
					 
				},
				error : function(error) {
					console.log(Error ${error});
				}
			});
		   
			
		//   window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
			//alert("Special Sale added Succesfully !");
		   
		   } 
	   
	   
		$("#salestable  tbody").empty();
		 document.getElementById("note").value="";
		 document.getElementById("purchaseorder").value="";
		 $("#gTotal").html(0.00);
         $("#qTotal").html(0.00);
         $("#taxTotal").html(0.00);
         $("#tTotal").html(0.00);
         $("#productDetailsPojo").val('');
         $("#productDetailsPojo").prop('selectedIndex',0);
         $('#productDetailsPojo').attr('selected', 'selected');
		
        // window.location.replace('${pageContext.request.contextPath}/viewSales');
         }   
      //   window.location.replace('${pageContext.request.contextPath}/viewSalesNew');	 
	}
	}

	function ChangeSubTotal(price, quantity, rowCount) {

	//	alert("changed me " + $(quantity).val());
		var subtotal = $(quantity).val() * price;
		$('#subtotal' + rowCount).html(subtotal);

	}
	
	function dotest(event,rowCount)
	{
	    //GET THE CONTENT as TEXT ONLY, you may use innerHTML as required
	    
	    console.log("Reached dotest");
	    document.getElementById("myBtn").disabled = false;  
	   var price = $('#price' + rowCount).html();
	   var quantity = $('#quantity' + rowCount).html();
	   var ogsubtotal=$('#subtotal' + rowCount).html();
	  // alert(ogsubtotal);
	  
	  // commenting below for fixing alert occures in blank quantity.
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
	   //if(ogsubtotal==subtotal){
		//   alert("Please add the product again");
		//   document.getElementById("myBtn").disabled = true;  
	   //}else{
		
		//   document.getElementById("myBtn").disabled = false;  
	   //}
	 
	   console.log("quantity changes ="+quantity);
	   console.log("subtotal changes ="+subtotal);    
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


		
	$("#productDetailsPojo").change(function(){
		
		var productName = $('#productDetailsPojo :selected').text();
		var productId = $('#productDetailsPojo :selected').val();
		var rowCount = $('#salestable tr').length;
		var price = $('#productDetailsPojo :selected').data('price');
		var origprice = $('#productDetailsPojo :selected').data('price');
		var discount = $('#productDetailsPojo :selected').data('promotion');
		var customerId = $('#customerPojo :selected').val();
		var unit = $('#unitId :selected').data('id');
		var unitname = $('#unitId :selected').text();
		var note = $('#note').val();
		var purchaseorder= $('#purchaseorder').val();
		var quantity = 1;
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');	
		var roll= "NO";
		var pquantity = $('#productDetailsPojo :selected').data('quantity');
		$('#pname').val(pquantity);
     	//alert(rollprice);
	//	alert(unit);
	//	quantity= $('#unitId :selected').val();
	
	
	 if (unit === 5 || unit === 3 || unit == 8 || unit == 9 || unit==14 ||unit==15||unit==16||unit==17 || unit==13 ) {
        return;
    }
	
		
		if(typeof newprice !== "undefined"){
			newprice = newprice.toFixed(2)
		}
		var percentage =0;
		percentage = $('#salespercent :selected').val();
		
		localStorage.setItem("product", productId);
		
		if(customerId==0){
			alert("Please select customer");
		}else{
		
		
	//	 alert(rollprice);
		
			if(percentage > 0)
			{
				newprice = (origprice + (( origprice * percentage ) / 100)) ;
				discountnewprice = (origprice + (( origprice * percentage ) / 100)) ;
				//alert(newprice);
				newprice=Math.round(newprice * 100) / 100;
				newprice=newprice.toFixed(2);
				//alert(newprice);
				
				if( ctype != "WholeSellers" && discount>0){
				
					newprice = (discountnewprice - (( discountnewprice * discount ) / 100));
					//alert(newprice);
					newprice=Math.round(newprice * 100) / 100;
					newprice=newprice.toFixed(2);
					//alert(newprice);
					//alert(newprice);
				 
				}
			}
			
			if(unit == 1 || unit ==5)
			{
				
			   price = (newprice * quantity).toFixed(2);
			   roll="NO";
			 //  alert(roll);
			   
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
				   
					if(percentage > 0)
					{
						
						newprice = (+newprice + (( +newprice * percentage ) / 100)) ;
						//newprice=Math.round(newprice * 100) / 100;
						//newprice=newprice.toFixed(2);
						price = (+price + (( +price * percentage ) / 100));
						price=Math.round(price * 100) / 100;
						price=price.toFixed(2);
						  
						  //alert(price);
						if( ctype != "WholeSellers" && discount>0){
						
							newprice = (+newprice - (( +newprice * discount ) / 100));
							//newprice=Math.round(newprice * 100) / 100;
							//newprice=newprice.toFixed(2);
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
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'"  <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>').html(newprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true"  oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')"></td>').html(quantity));
		tr.append($('<td id="unitname'+rowCount+'"></td>').html(unitname));
		tr.append($('<td hidden id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#salestable tbody').append(tr);
		}

		var myJSON = {
			"productName" : productName,
			"price" : newprice,
			"productId" : productId,
			"customerId" : customerId,
			"roll"       : roll,
			"quantity" : quantity,
			"subtotal":price,
			"note" : note,
			"unit" : unit,
			"purchaseorder":purchaseorder
			
 		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
	//	alert("Setting unit");
		$('#unitId').val("1");
	//	$("#unitId option:contains(Piece)").attr('selected', true);
	//	 $("#salespercent option:contains(General)").attr('selected', true);
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
		var note = $('#note').val();
		var purchaseorder= $('#purchaseorder').val();
		var quantity = 1;
		var newprice = $('#productDetailsPojo :selected').data('price');
		var ctype = $('#customerPojo :selected').data('ctype');
		var rollprice = $('#productDetailsPojo :selected').data('rollprice');
		var roll="NO";
		var pquantity = $('#productDetailsPojo :selected').data('quantity');
		$('#pname').val(pquantity);
		//alert(unit);
		
	//	quantity= $('#unitId :selected').val();
		
		
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
		
		if(unit == 1 || unit ==5 || unit==13)
		{
		   price = (newprice * quantity).toFixed(2);
		}
		else if(unit == 14 || unit==15 || unit==16 || unit==17){
			//alert("Reached")
			roll="YES";
			price = rollprice.toFixed(2);
			newprice = rollprice.toFixed(2);
			//alert(price);
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
		
		else if(unit ==3 ){
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
		tr.append($('<td></td>').html(rowCount-1));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize> ></td>').html(newprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true"  oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')" ></td>').html(quantity));
		tr.append($('<td id="unitname'+rowCount+'"></td>').html(unitname));
		tr.append($('<td hidden id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#salestable tbody').append(tr);
		}

		var myJSON = {
			"productName" : productName,
			"price" : newprice,
			"productId" : productId,
			"customerId" : customerId,
			"roll"       :roll,
			"quantity" : quantity,
			"subtotal":price,
			"note" : note,
			"unit": unit,
			"purchaseorder":purchaseorder
			
		};

		productList.push(myJSON);
		//	document.getElementById("productList").val()=productList;

		console.log(productList);
		$('#unitId').val("1");
		$("#unitId option:contains(Piece)").attr('selected', true);
		// Adding footer
		CalculateTotal();
		 		
	}
	
	
	function CalculateTotal() {
	
		 var grandT = 0;
	        var qtY = 0;
	        var taxT =0;
	        var totalT =0;
	        
	        $("#salestable > TBODY > tr").each(function () {
	        	var t4 = $(this).find('td').eq(4).html();
	            var t6 = $(this).find('td').eq(7).html();
	          
	            if (!isNaN(t6) && !isNaN(t4)) {
	                grandT += parseFloat(t6);
	                qtY +=parseFloat(t4);
	            }
	        });
	        grandT= grandT.toFixed(2);
	        taxT= grandT * .125;
	        //alert(taxT);
	         taxT=+(Math.round(taxT + "e+2")  + "e-2");
	      //  alert(taxT);
	        totalT = Number(grandT)+taxT;
	        //alert(totalT);
	        totalT=  Math.round(totalT * 100) / 100;
	       // alert(totalT);
	        $("#gTotal").html(grandT);
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
		    count =count-1;
			var row = document.getElementById("salestable");
			row.deleteRow(count);
			
		// Save Table State in JSON
			
		 var customerId = $('#customerPojo :selected').val();
		 var note = $('#note').text();
		 var ctype = $('#customerPojo :selected').data('ctype');
		 var saleId = $('#saleid').val();
		 
		// alert(ctype);
		 
		 var _table = document.getElementById("salestable");
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
		    	 , saleId		: saleId
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
	//	 console.log("myJSON======" + myJSON);
		
	//	 alert(myJSON);
		 // Empty table at this point 
		 
	// $("#salestable  tbody").empty();
		 
		 // Recreate the table from JSON
		
		var rowCount = $('#salestable tr').length; 
		 
		// Loop into myJSON to recrate the table with correct rowcount 
		
		s = jQuery.parseJSON(myJSON);

    //	alert( s[0]["productName"] );
		
		var tr = $('<tr id="tr"></tr>');
		tr.append($('<td></td>').html(rowCount));
		tr.append($('<td></td>').html(productId));

		tr.append($('<td></td>').html(productName));
		tr.append($('<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize>></td>').html(newprice));
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html(quantity));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="deleteItems('+ rowCount + ')">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#salestable tbody').append(tr);
		 
		 
		}
		CalculateTotal();
	}
	
	function deleteItems(count){
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
<!-- Add Customer Modal -->
<div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
      <div class="modal-header">
        <h5 class="modal-title" id="addCustomerModalLabel">Add New Customer</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <form id="addCustomerForm">
        <div class="modal-body">

          <div class="form-group">
    <label>Customer Name</label>
    <input type="text" class="form-control" name="name" placeholder="Enter Customer Full Name" required>
  </div>

                                 <div class="form-group">
									<label for="DOB">Date of Birth</label> <input
										type="date" class="form-control" name="DOB" id="DOB"
										placeholder="Enter Customer DOB" >
								</div>

  <div class="form-group">
    <label>Company</label>
    <input type="text" class="form-control" name="company" placeholder="Enter Company Name">
  </div>

  <div class="form-group">
    <label>Username</label>
    <input type="text" class="form-control" name="userName" placeholder="Enter User Name">
  </div>

  <div class="form-group">
    <label>Contact Name</label>
    <input type="text" class="form-control" name="contactname" placeholder="Enter Contact Name">
  </div>

  <div class="form-group">
    <label>Main Phone Number</label>
    <input type="text" class="form-control" name="phonemain" placeholder="Enter Main Phone Number" required>
  </div>

  <div class="form-group">
    <label>WhatsApp Number</label>
    <input type="text" class="form-control" name="phonewhatsapp" placeholder="Enter WhatsApp Number">
  </div>

  <div class="form-group">
    <label>First Email</label>
    <input type="email" class="form-control" name="firstemail" placeholder="Enter First Email" required>
  </div>

  <div class="form-group">
    <label>Second Email</label>
    <input type="email" class="form-control" name="secondemail" placeholder="Enter Second Email">
  </div>

  <div class="form-group">
    <label>Address</label>
    <textarea class="form-control" name="address" placeholder="Enter Address"></textarea>
  </div>

  <div class="form-group">
    <label>City</label>
    <input type="text" class="form-control" name="city" placeholder="Enter City">
  </div>

  <div class="form-group">
    <label>PIN Code</label>
    <input type="text" class="form-control" name="pincode" placeholder="Enter PIN Code">
  </div>

  <div class="form-group">
    <label>Customer Type</label>
    <select class="form-control" name="ctype" required>
      <option value="">Please Select Customer Type</option>
      <option value="Special">Special</option>
      <option value="Wholesalers">Wholesalers</option>
      <option value="General">General</option>
    </select>
  </div>

  <div class="form-group">
    <label>Product Type</label>
    <select class="form-control" name="pricegroup">
      <option value="">Please Select Product Type</option>
      <option value="Special">Special</option>
      <option value="Wholesalers">Wholesalers</option>
      <option value="General">General</option>
    </select>
  </div>

  <div class="form-group">
    <label>Credit Facility</label>
    <select class="form-control" name="creditfacility">
      <option value="">Please Select Credit Facility</option>
      <option value="Yes">Yes</option>
      <option value="No">No</option>
    </select>
  </div>

        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Save Customer</button>
        </div>
      </form>

    </div>
  </div>
</div>


<!-- JavaScript (jQuery + SweetAlert2 required) 
<script>
$('#addCustomerForm').submit(function (e) {
    e.preventDefault();

    $.ajax({
        url: '${pageContext.request.contextPath}/addCustomerFromSales',
        type: 'POST',
        data: $(this).serialize(),
        success: function (response) {
            if (!response) {
                Swal.fire('Error!', 'No server response', 'error');
                return;
            }

            if (!response.error) {
                $('#addCustomerModal').modal('hide');
                $('#addCustomerForm')[0].reset();

                const customer = response.customer;
                if (customer) {
                    $('#customerPojo').append(
                        $('<option></option>')
                            .val(customer.id)
                            .attr('data-creditfacility', customer.creditfacility || '')
                            .attr('data-ctype', customer.ctype || '')
                            .attr('data-pricegroup', customer.pricegroup || '')
                            .attr('data-blocked', customer.blocked || '')
                            .attr('data-creditamount', customer.creditpayment || '')
                            .text(customer.userName || 'Unnamed Customer')
                    ).val(customer.id);
                }

                Swal.fire({
                    icon: 'success',
                    title: 'Success!',
                    text: response.msgDescr || 'Customer added successfully',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
      else {
                // Error case from server
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: response.msgDescr || 'Registration failed',
                    showConfirmButton: true
                });
            }
        },
        error: function (xhr) {
            // Handle HTTP errors
            const errorMsg = xhr.responseJSON?.msgDescr || 
                          'Server error: ' + xhr.statusText;
            Swal.fire({
                icon: 'error',
                title: 'Request Failed',
                text: errorMsg,
                showConfirmButton: true
            });
        }
    });
});

  document.getElementById('DOB').addEventListener('click', function() {
    this.showPicker();
   });
  
</script>
$('#addCustomerForm').submit(function (e) {
    e.preventDefault();

    $.ajax({
        url: '${pageContext.request.contextPath}/addCustomerFromSales',
        type: 'POST',
        data: $(this).serialize(),
        success: function (response) {
            if (!response) {
                Swal.fire('Error!', 'No server response', 'error');
                return;
            }

            if (!response.error) {
                // Success case
                $('#addCustomerModal').modal('hide');
                $('#addCustomerForm')[0].reset();

                // Refresh customer dropdown
                $.ajax({
                    url: '${pageContext.request.contextPath}/getCustomers',
                    type: 'GET',
                    success: function(customersResponse) {
                        const $dropdown = $('#customerPojo');
                        $dropdown.empty().append(
                            $('<option></option>').val('0').text('Select')
                        );

                        $.each(customersResponse.data, function (i, customer) {
                            $dropdown.append(
                                $('<option></option>')
                                    .val(customer.id)
                                    .attr('data-creditfacility', customer.creditfacility || '')
                                    .attr('data-ctype', customer.ctype || '')
                                    .attr('data-pricegroup', customer.pricegroup || '')
                                    .attr('data-blocked', customer.blocked || '')
                                    .attr('data-creditamount', customer.creditpayment || '')
                                    .text(customer.userName || 'Unnamed Customer')
                            );
                        });
                    }
                });

                Swal.fire({
                    icon: 'success',
                    title: 'Success!',
                    text: response.msgDescr || 'Customer added successfully',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                // Error case from server
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: response.msgDescr || 'Registration failed',
                    showConfirmButton: true
                });
            }
        },
        error: function (xhr) {
            // Handle HTTP errors
            const errorMsg = xhr.responseJSON?.msgDescr || 
                          'Server error: ' + xhr.statusText;
            Swal.fire({
                icon: 'error',
                title: 'Request Failed',
                text: errorMsg,
                showConfirmButton: true
            });
        }
    });
});
</script> -->