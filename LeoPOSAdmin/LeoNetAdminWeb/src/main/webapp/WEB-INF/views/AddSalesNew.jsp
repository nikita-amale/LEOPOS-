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
							<h3 class="card-title">Add Sale New</h3>
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
							
									<sec:authorize access="hasAuthority('admin')">
										<div class="form-group">
											<label>Customer Type</label><font color="red"></font> <select
												class="form-control select2bs4" id="salespercent"
												style="width: 100%;" readonly>
											</select>
										</div>
									</sec:authorize>
									
								<div id="otherFields">
									<div class="form-group">
										<label>Select Product</label><font color="red">*</font> <select
											class="form-control select2bs4" name="productCode"
											id="productDetailsPojo1" style="width: 100%;" required>
										</select>
									</div>
									<div class="form-group">
										<label>Select Unit</label><font color="red"></font> <select
											class="form-control select2bs4" id="unitId"
											style="width: 100%;" required>
										</select>
									</div>

									<div class="form-group">
										<label for="exampleInputEmail1">Central
											WarehouseQuantity</label> <input type="text" class="form-control"
											id="pname" value="0" readonly> <label
											for="exampleInputEmail1">Other Warehouse Quantity</label> <input
											type="text" class="form-control" id="othername" value="0"
											readonly>
									</div>

									<sec:authorize access="hasAuthority('admin')">
										<div class="form-group">
											<label>Customer Type</label><font color="red"></font> <select
												class="form-control select2bs4" id="salespercent"
												style="width: 100%;" readonly>
											</select>
										</div>
									</sec:authorize>


									<div class="form-group" hidden>
										<label>Customer Type</label><font color="red"></font> <select
											class="form-control select2bs4" id="salespercent"
											style="width: 100%;" readonly>
										</select>
									</div>

									<div class="form-group">
										<label>Customer Credit Facility</label><font color="red"></font>
										<select class="form-control select2bs4" id="creditfacility"
											style="width: 100%;" readonly>
											<option val="YES">YES</option>
											<option val="NO">NO</option>
										</select>

									</div>




									<div class="card-footer">
										<button type="button" onclick="addItem()"
											class="btn btn-primary">Add</button>
										<button class="btn btn-primary" type="reset">Reset</button>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body pl-0 pr-0">
									<table id="salestable" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>Serial No</th>
												<th>Product Code</th>
												<th>Product Name</th>
												<th>Quantity</th>
												<th>Net Unit Price</th>	
												<th>Unit Of Measure</th>
												<th hidden>Unit</th>
												<th>Sub Total (BZD )</th>
												<th>Action</th>
												
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

window.onload = function() {
    document.getElementById("otherFields").style.display = "none";
  };

  let productListFromAjax = [], unitListFromAjax = [];


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
	 			console.log(`Error ${error}`);
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
				productListFromAjax = data;
				createInputRow();
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
							'<option data-price="'+data.price+  '" data.cf1="' + data.cf1 +  '" data-rollprice="' + data.rollprice + '" data-quantity="' + data.quantity +  '"data-centralwarehouseqty="'+data.centralwarehousequantity+ '"data-otherwarehouseqty="'+data.otherwarehousequantity+
							'" data-promotion="' + data.promotion + '" value="' + data.productId + '" >'	+ data.name + ' - ' + data.cf1 + ' - ' + data.code + '</option>');
				});
				
			},
			error : function(error) {
				console.log(`Error ${error}`);
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

	

	
	$.ajax({
		url : '${pageContext.request.contextPath}/getUnits',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			unitListFromAjax = data;
			
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
								+data.ctype + '</option>');
				
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
		   alert(productId);
		    // Clear the dropdown options
		    $("#unitId").empty();
		 // alert(productId);
		 // alert(productname);
		  if (productname.startsWith("Wire")&&product!=0){
			  $("#unitId").append('<option value="1" data-id="5">Ft</option>');
			  $("#unitId").append('<option value="2" data-id="2">Wire Roll 328 Ft</option>');
		 
		  }else if(productId==277 || productId==278 ){
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
		  else if(productId==852 || productId==853 ||productId==854 ||  productId==855 || productId==856 || productId==857 || productId==858 || productId==859 || productId==860 || productId==861 || productId==862 || productId==863 ||productId==864 ||productId==1336){
			  $("#unitId").append('<option value="1" data-id="8">10Ft</option>');
			  $("#unitId").append('<option value="9" data-id="9">20Ft</option>');
			  
		  }
		  else if(productId==1511 || productId==1512 ){
			  $("#unitId").append('<option value="1" data-id="10">Roll 100 Ft</option>');
			   
		  }
		  else if(productId==1513 || productId==1514 || productId==1515 || productId==1516 || productId==1521 || productId==1522  ){
			  $("#unitId").append('<option value="1" data-id="11">Box 50lb</option>');
			   
		  }
		  else if(productId==1517 || productId==1518 || productId==1519 || productId==1520 || productId==1523 ){
			  $("#unitId").append('<option value="1" data-id="12">Box 55lb</option>');
			   
		  }
		  else {
			  //alert("else");
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
		  $("#creditfacility option:contains(NO)").attr('selected', false);
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
		 //alert(creditamout);
		 if(creditamout>"0"){
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
		 }
	
		 
		 var tax="YES";
		  if (taxrate.checked){
		     //   alert("checked") ;
		        tax ="YES";
		    }else{
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
		 

		/* var _htmlToJSON = function(index){
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
		 */
		 var _htmlToJSON = function(index){
			    var _tr = _table.getElementsByTagName("tr")[index];
			    var $tr = $(_tr);

			    var productId = $tr.find(".product-code-select").val();
			    var productName = $tr.find(".product-name-select option:selected").text().trim();

			    var price = parseFloat($tr.find("td:eq(4)").text().trim()) || 0;
			    var quantity = parseFloat($tr.find(".qty").text().trim()) || 1;
			    var unit = $tr.find(".unit-id").text().trim();
			    var roll = $tr.find("td:eq(6)").text().trim(); // hidden
			    var subtotal = parseFloat($tr.find("td:eq(7)").text().trim()) || 0;

			    _obj = {
			        productId     : productId,
			        productName   : productName,
			        price         : price,
			        customerId    : customerId,
			        quantity      : quantity,
			        note          : note,
			        tax           : tax,
			        subtotal      : subtotal,
			        roll          : roll,
			        unit          : unit,
			        purchaseorder : purchaseorder,
			        applycreditpayment: applycreditpayment
			    };

			    _jsonData.push(_obj);
			};


		 for(var i = 1; i < _trLength; i++){
			 var tr = _table.getElementsByTagName("tr")[i];
			 if (tr.id === "inputRow") continue;
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
					
					 window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
				 // });
					//	window.location.href = "${pageContext.request.contextPath}/viewSales";
			},
			error : function(error) {
				console.log(`Error ${error}`);
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
					
						 
						 
						window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
						// location.reload();
					 // }); 
	  	 			//location.reload();
	  	 			
					 
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		   
			
		   window.location.href = "${pageContext.request.contextPath}/viewSalesNew";
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
		
       //  window.location.replace('${pageContext.request.contextPath}/viewSales');
         }   
         //window.location.replace('${pageContext.request.contextPath}/viewSalesNew');	 
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
		}  */
		CalculateTotal();
	   //if(ogsubtotal==subtotal){
		//   alert("Please add the product again");
		//   document.getElementById("myBtn").disabled = true;  
	   //}else{
		
		//   document.getElementById("myBtn").disabled = false;  
	   //}
	   
	       if (!isNaN(quantity) && quantity !== '') {
	        var quantityVal = parseFloat(quantity);
	        var subtotal = (quantityVal * price).toFixed(2);
	        $('#subtotal' + rowCount).text(subtotal);
	        CalculateTotal();
	    }
	  
	 
	   console.log("quantity changes ="+quantity);
	   console.log("subtotal changes ="+subtotal);    
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
		var centralquantity = $('#productDetailsPojo :selected').data('centralwarehouseqty');
		var otherquantity = $('#productDetailsPojo :selected').data('otherwarehouseqty');
		
		$('#pname').val(centralquantity);
		$('#othername').val(otherquantity);
		
     	//alert(rollprice);
	//	alert(unit);
	//	quantity= $('#unitId :selected').val();
	
	
	 if (unit === 5 || unit === 3 || unit == 8 || unit == 9 ) {
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
		tr.append($('<td id="quantity'+rowCount+'" contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" ></td>').html(quantity));
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

	function addItem(product) {
		
	//	alert("we are here");

		const $tbody = $('#salestable tbody');
		var rowCount = $('#salestable tbody tr').not('#inputRow').length + 1;

		var productId = product.productId;
	    var productName = product.name;

	    var price = parseFloat(product.price) || 0;
	    var origprice = parseFloat(product.price) || 0;
	    var discount = parseFloat(product.promotion) || 0;

	    var rollprice = parseFloat(product.rollprice) || 0;
	    var pquantity = product.quantity;
	    var centralquantity = product.centralwarehouseqty;
	    var otherquantity = product.otherwarehouseqty;

	    var newprice = parseFloat(product.price) || 0;

		var customerId = $('#customerPojo :selected').val();
		var unit = $('#unitId :selected').data('id');
		var unitname = $('#unitId :selected').text();
		var note = $('#note').val();
		var purchaseorder= $('#purchaseorder').val();
		var quantity = 1;

		var ctype = $('#customerPojo :selected').data('ctype');
		var roll="NO";
		
		$('#pname').val(centralquantity);
		$('#othername').val(otherquantity);

		//alert(unit);
		
	//	quantity= $('#unitId :selected').val();
		
		if (isNaN(newprice) || newprice == null) {
        newprice = 0;
       }
		
		newprice = newprice.toFixed(2)
		var percentage =0;
		percentage = $('#salespercent :selected').val();
		
		
		if(customerId==0){
			alert("Please select customer");
			return;
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
		
		if(unit == 1 || unit ==5 )
		{
		   price = (newprice * quantity).toFixed(2);
		}
		else if(unit ==3){
		   if (isNaN(rollprice) || rollprice == null) {
			   rollprice = 0;
	        }
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
		
		if(newprice == 0.00)
			{
			 newprice = 0;
			}
		
		// Create select dropdowns
        const $codeSelect = $('<select class="form-control product-code-select"></select>').append('<option value="">Select Code</option>');
        const $nameSelect = $('<select class="form-control product-name-select"></select>').append('<option value="">Select Name</option>');

        // Create unit dropdown
        const $unitSelect = $('<select class="form-control unit-select"></select>');

       // Populate both dropdowns
        productListFromAjax.forEach(prod => {
            const option = $('<option></option>').val(prod.productId).data('details', prod);
            $codeSelect.append(option.clone().text(prod.code));
            $nameSelect.append(option.clone().text(prod.name));
        });

        unitListFromAjax.forEach(unitObj => {
            $unitSelect.append(
                $('<option></option>')
                    .val(unitObj.id)
                    .attr('data-id', unitObj.id)
                    .text(unitObj.unitname)
            );
        });

        // Set selected value
        $codeSelect.val(productId);
        $nameSelect.val(productId);

        $unitSelect.val(unit); // set default

     	// Sync selects on change
        $codeSelect.on('change', function () {
            $nameSelect.val($(this).val());
        });
        $nameSelect.on('change', function () {
            $codeSelect.val($(this).val());
        });

		var tr = $("<tr id='row"+rowCount+"'></tr>");
		tr.append($('<td></td>').html(rowCount));

		tr.append($('<td></td>').append($codeSelect));
		tr.append($('<td></td>').append($nameSelect));
		tr.append($('<td id="quantity'+rowCount+'" class="qty" contenteditable="true"  oninput="dotest(event, '+rowCount+')" onblur="handleBlur('+rowCount+')" ></td>').html(quantity));
		tr.append($('<td id="price'+rowCount+'" <sec:authorize access="hasAuthority('admin')"> contenteditable="true" onkeyup="javascript:dotest(event,'+rowCount+');" </sec:authorize> ></td>').html(newprice));
		tr.append($('<td></td>').append($unitSelect));
		tr.append($('<td hidden class="unit-id" id="unit'+rowCount+'"></td>').html(unit));
		tr.append($('<td id="subtotal'+rowCount+'" ></td>').html(price));
		tr.append($('<td <button type="button" class="btn btn-danger delete-btn">Delete</button>'));
		tr.append($('<tr></tr>').html());
		$('#salestable tbody').append(tr);

		$codeSelect.select2();
		$nameSelect.select2();

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
	
	// Attach once on document ready
	$(document).on('click', '#salestable tbody .delete-btn', function () {
	    const $row = $(this).closest('tr');
	    deleteItemsNew($row);
	});


	function deleteItemsNew($row) {
	    $row.remove();

	    // Renumber rows excluding input row
	    $("#salestable tbody tr").not("#inputRow").each(function (i) {
	        const index = i + 1;
	        $(this).attr("id", "row" + index);
	        $(this).find("td").eq(0).text(index); // update serial number
	    });

	    // Move input row to last position
	    const $inputRow = $("#inputRow").detach();
	    $("#salestable tbody").append($inputRow);

	    // Reinitialize Select2
	    $("#inputRow select").select2();

	    CalculateTotal();
	}


	function createInputRow() {
		  const $tr = $('<tr id="inputRow"></tr>');

		  const $codeSelect = $('<select class="form-control"></select>').append('<option value="">Select Code</option>');
		  const $nameSelect = $('<select class="form-control"></select>').append('<option value="">Select Name</option>');

		  // Populate both dropdowns
		  productListFromAjax.forEach(prod => {
		    $codeSelect.append($('<option></option>').val(prod.productId).text(prod.code).data('details', prod));
		    $nameSelect.append($('<option></option>').val(prod.productId).text(prod.name).data('details', prod));
		    
		    $codeSelect.off('change').on('change', function () {
		    	  const selectedProd = $(this).find(':selected').data('details');
		    	  addItem(selectedProd);
		    	});

		    $nameSelect.off('change').on('change', function () {
		         const selectedProd = $(this).find(':selected').data('details');
		    	 const customerId = $('#customerPojo :selected').val();
		    	  if (customerId === "0" || !customerId) {
				        alert("Please select customer");
				        if (!$('#inputRow').parent().length) {
				            $('#salestable tbody').prepend($('#inputRow'));
				        }
				        return;
		    	  }
		    	  addItem(selectedProd);
		    	  // Always move input row to last position
				  const $tbody = $('#salestable tbody');
				  const $inputRow = $('#inputRow').detach();
				  $tbody.append($inputRow);

				// Reinitialize select2 after re-attach
				   
				  $nameSelect.select2();
		    	});
		  });

		  // Sync both selects
		  $codeSelect.on('change', function () {
		    $nameSelect.val($(this).val());
		  });
		  $nameSelect.on('change', function () {
		    $codeSelect.val($(this).val());
		  });

		  const $addButton = $('<button class="btn btn-sm btn-success">Add</button>').on('click', function () {
			    const selectedId = $codeSelect.val();
			    if (!selectedId) return;

			    const product = $codeSelect.find('option:selected').data('details');

			    const customerId = $('#customerPojo :selected').val();
			    if (customerId === "0" || !customerId) {
			        alert("Please select customer");
			        if (!$('#inputRow').parent().length) {
			            $('#salestable tbody').prepend($('#inputRow'));
			        }
			        return;
			    }

			    // Add new product row
			    addItem(product);

			    // Always move input row to last position
			    const $tbody = $('#salestable tbody');
			    const $inputRow = $('#inputRow').detach();
			    $tbody.append($inputRow);

			    // Reinitialize select2 after re-attach
			    $codeSelect.select2();
			    $nameSelect.select2();
			});




		  $tr.append('<td>#</td>');
		  $tr.append($('<td></td>').append($codeSelect));
		  $tr.append($('<td></td>').append($nameSelect));
		  $tr.append('<td colspan="4"></td>');
		  $tr.append($('<td></td>').append($addButton));

		  $('#salestable tbody').prepend($tr); // add at top
		  $codeSelect.select2();
		  $nameSelect.select2();
		}

		function deleteRow(btn) {
		  $(btn).closest('tr').remove();
		}

		$(document).on('change', '.product-code-select', function () {
		    let row = $(this).closest('tr');
		    let rowCount = row.attr('id').replace('row', '');
		    let productId = $(this).val();

		    row.find('.product-name-select').val(productId);
		    updateRowPrice(rowCount, productId);
		});

		$(document).on('change', '.product-name-select', function () {
		    let row = $(this).closest('tr');
		    let rowCount = row.attr('id').replace('row', '');
		    let productId = $(this).val();

		    row.find('.product-code-select').val(productId);
		    updateRowPrice(rowCount, productId);
		});

		$(document).on('change', '.unit-select', function () {
            let row = $(this).closest('tr');
            let rowCount = row.attr('id').replace('row', '');
            let productId = row.find('.product-code-select').val();

            updateRowPrice(rowCount, productId);
        });

       function updateRowPrice(rowCount, productId) {
           let product = productListFromAjax.find(p => p.productId == productId);
           if (!product) return;

           let $row = $('#row' + rowCount);

           // Get current values
           let unit = parseInt($row.find('.unit-select').val());
           let quantity = parseFloat($row.find('#quantity' + rowCount).text()) || 1;
           let customerId = $('#customerPojo :selected').val();
           let percentage = parseFloat($('#salespercent :selected').val()) || 0;
           let ctype = $('#customerPojo :selected').data('ctype');
           let discount = parseFloat(product.promotion) || 0;
           let origprice = parseFloat(product.price) || 0;
           let rollprice = parseFloat(product.rollprice) || 0;
           let newprice = origprice;
           let price = 0;

           // === Paste your existing calculation logic here ===
           if (percentage > 0) {
               newprice = (origprice + ((origprice * percentage) / 100));
               newprice = Math.round(newprice * 100) / 100;
               newprice = newprice.toFixed(2);

               if (ctype != "WholeSellers" && discount > 0) {
                   newprice = (newprice - ((newprice * discount) / 100));
                   newprice = Math.round(newprice * 100) / 100;
                   newprice = newprice.toFixed(2);
               }
           }

           if (unit == 9) {
               newprice = newprice * 2;
           }

           if (unit == 1 || unit == 5) {
               price = (newprice * quantity).toFixed(2);
           } else if (unit == 3) {
               if (isNaN(rollprice) || rollprice == null) rollprice = 0;
               price = rollprice.toFixed(2);
               newprice = rollprice.toFixed(2);
               price = (newprice * quantity).toFixed(2);

               if (percentage > 0) {
                   newprice = (+newprice + ((+newprice * percentage) / 100));
                   newprice = Math.round(newprice * 100) / 100;
                   newprice = newprice.toFixed(2);

                   price = (+price + ((+price * percentage) / 100));
                   price = Math.round(price * 100) / 100;
                   price = price.toFixed(2);
               }
           } else {
               if (rollprice > 0) {
                   price = rollprice.toFixed(2);
                   newprice = rollprice.toFixed(2);

                   if (percentage > 0) {
                       newprice = (+newprice + ((+newprice * percentage) / 100));
                       newprice = Math.round(newprice * 100) / 100;
                       newprice = newprice.toFixed(2);

                       price = (+price + ((+price * percentage) / 100));
                       price = Math.round(price * 100) / 100;
                       price = price.toFixed(2);

                       if (ctype != "WholeSellers" && discount > 0) {
                           newprice = (+newprice - ((+newprice * discount) / 100));
                           newprice = Math.round(newprice * 100) / 100;
                           newprice = newprice.toFixed(2);

                           price = (+price - ((+price * discount) / 100));
                           price = Math.round(price * 100) / 100;
                           price = price.toFixed(2);
                       }
                   }
               } else {
                   price = (newprice * quantity).toFixed(2);
               }
           }

           // Update row
           $row.find('#price' + rowCount).text(newprice);
           $row.find('#subtotal' + rowCount).text(price);

           // Recalculate total
           CalculateTotal();
       }


	
</script>