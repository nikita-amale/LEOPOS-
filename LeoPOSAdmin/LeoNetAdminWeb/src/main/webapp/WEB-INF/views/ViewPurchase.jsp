
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/style.css">
<style>
	.button__border{
		border: none;
		background: transparent;
	}
	button:focus {
    outline: transparent !important;
    outline: transparent !important;
}
</style>
<!-- Content Wrapper. Contains page content -->
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
							<h3 class="card-title">Purchase Details</h3>
						</div>

					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>PurchaseId</th>
									<th>Date</th>
									<th>Supplier</th>
									<th>Grand_Total</th>
									<th>Balance</th>
									<th hidden>CreatedBy</th>
									<th>View</th>
									<th>View file</th>
									<th>Transfer To Quotes</th>
									
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="purchasePojo" items="${purchasePojo}"
									varStatus="loop">
									<c:set var = "grand_total" value = "${purchasePojo.grand_total}" />
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="purchaseId${loop.count}">${purchasePojo.purchaseId}</td>
										<td id="purchaseDate${loop.count}">${purchasePojo.date}</td>
										<td id="supplier${loop.count}">${purchasePojo.supplier}</td>
										<td  id="purchaseTotal${loop.count}"><fmt:formatNumber pattern="0.00" value="${grand_total}" /></td>
										<td id="purchasePojoPaid${loop.count}">0</td>
										<td  hidden id="purchaseCreatedby${loop.count}">${purchasePojo.createdBy}</td>
										<td>
										<button type="button" style="font-size: 12px;padding: 0.375rem 0.5rem !important;"
											class="btn btn-primary" data-toggle="modal"
											data-target="#modal-lg" onclick="ViewDetails(${loop.count})">View
											</button>
									</td>
									<td>
										<a type="button" style="font-size: 12px;"
												class="btn btn-primary" href="${pageContext.request.contextPath}/download?id=${purchasePojo.purchaseId}"  >File Download</a>
									</td>
									<td  >
										<button type="button" style="font-size: 12px;padding: 0.375rem 0.5rem !important;"
											class="btn btn-primary" data-toggle="modal"
											data-target="#viewpayment" onclick="TransferToQuote(${loop.count})">Transfer To Quotes
											</button>
									</td>

									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>

					<!-- /.card-body -->
				</div>

			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
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
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
						<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
					  </svg>
				</button>
				
				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->

				<div id="printTable">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					
							<table width="100%" style="font-size: 14px;">
								<tr>
									<td><img
										src="${pageContext.request.contextPath}/resources/images/logo_s.png"
										width="300px" /></td>
									<td></td>
								</tr>
							</table>
					<br>	

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between; padding: 0rem 1rem;">

						<div class="col">
							<table width="100%" style="font-size: 14px;">
							
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right">Tax Invoice</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date"></td>
								</tr>
								
								<tr>
									<td>TIN # 128693</td>
									<td align="right">Sales Person: SalesUser Sales</td>
								</tr>
							</table>
						</div>

					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;" id="supplier">
						</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						<strong></strong>
					</p>
					<p style="margin-bottom: 0; font-size: 14px;">Purchase Receipt</p>

					<br>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="purchaseReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th style="text-align: center !important;">No.</th>
									<th style="text-align: center !important;">Description</th>
									<th style="text-align: center !important;">Quantity</th>
									<th style="text-align: center !important;">Cost</th>
									<th style="text-align: center !important;">Price</th>
								
									
								</tr>

							</thead>

							<tbody>


							</tbody>
							<tfoot>
							
								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="purchaseTotal"
										style="padding-right: 10px; font-weight: bold;"></td>
								</tr>
								

							</tfoot>
						</table>
					</div>

					<div class="row">
						<div class="col-xs-12"></div>
						
						<div class="col pull-right">
						<div class="print-flex well well-sm">

							<p style="font-size: 14px !important;" id="purchaseCreatedby"></p>



						</div>

					</div>
					</div>

				</div>


				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice
				</a>
					<a href="javascript:void(0);" onclick="exportPDF()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade" id="modal-payment">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-close">X</i>
				</button>
				<div class="form-group">
					<label for="exampleInputEmail1">Amount</label> <input type="text"
						class="form-control" id="amount" placeholder="Payment Amount"
						readonly="true";>
				</div>
				<input type="hidden" class="form-control" id="purchaseid">
				<div class="form-group">
					<label for="exampleInputEmail1">Note</label> <input type="text"
						class="form-control" id="paymentnote" placeholder="Enter Note">
				</div>
				<div class="card-footer">
					<button type="button" onclick="addPayment()"
						class="btn btn-primary">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>


<div class="modal fade" id="viewpayment" tabindex="-1"
	aria-labelledby="viewpayment" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
						fill="currentColor" class="bi bi-x-square-fill text-secondary"
						viewBox="0 0 16 16">
			<path
							d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
		  </svg>
				</button>
				<div id="printTable">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<table width="100%" style="font-size: 14px;">
						<tr>
							<td><img
								src="${pageContext.request.contextPath}/resources/images/logo_s.png"
								width="300px" /></td>
							<td></td>
						</tr>
					</table>

					<br>

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;" id="viewPaymentTbl">
							
                                <label>Select Customer</label><font color="red"></font> <select
										class="form-control select2bs4" name="customerId"
										id="customerPojo" style="width: 100%;" required>
									</select>
									
									<tbody>

								<tr>
									<td id="purchaseId"></td>
	
								</tr>

							</tbody>

								

							</table>
						</div>
						


					</div>
					
					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

				</div>

				
<a href="javascript:void(0);" 
   class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right transfer-btn">
    <i class="fa fa-print fa-sm text-white-50"></i> Transfer
</a>
			</div>
			<!-- <div class="modal-footer">
<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary">Save changes</button>
</div> -->
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.umd.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>


<script type="text/javascript">
 
    $(function () {
      $('#myTable').DataTable({
        "paging": true,
        "pageLength": 25,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "scrollX": true
      });
    });
	const reloadUsingLocationHash = () => {
	      window.location.hash = "";
	    }
	    window.onload = reloadUsingLocationHash();
    function exportPDF()
    {
        var element = document.getElementById('printTable');
        var opt = {
            margin:       0.5,
            filename:     'purchase'+<%=System.currentTimeMillis()%>+'.pdf',
            image:        { type: 'jpeg', quality: 1 },
            html2canvas:  { scale: 1 },
            jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
          };
        html2pdf().set(opt).from(element).save();
    }
    
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


  function EditDetails(count){
	  var pTotal = $("#purchaseTotal"+count).text();
	  var purchaseId = $("#purchaseId"+count).text();
		
		 $("#purchaseReceipt  tbody").empty();
		 $("#tax").html($("#tax"+count).text());
		

		 $("#balTot").html($("#purchaseTotal"+count).text());
		 $("#purchaseTotal").html($("#purchaseTotal"+count).text());
		 $("#sRefno").html("Sale Reference No. " + $("#purchaseId"+count).text());
		 $("#cName").html( $("#customer"+count).text());

		 $("#purchasePojoCreatedby").html($("#purchasePojoCreatedby"+count).text());
		
		 
		 
		 
//		 alert(rquoteId);
		 $.ajax({
				url : '${pageContext.request.contextPath}/getPurchaseitembypurchaseId?purchaseId=' + purchaseId,
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
						tr.append($('<td></td>').html(pTotal));
						tr.append($('<tr></tr>').html());
						$('#purchaseReceipt tbody').append(tr);
					});

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		 }
  
  function loadPayment(count){
	  var PurchaseBalance = $("#purchaseTotal"+count).text();
	  var PurchaseId = $("#purchaseId"+count).text();
	  alert(PurchaseBalance);
	  alert("Purchase Balance: " + PurchaseBalance + "....... Purchase ID: " + PurchaseId);
	  $("#amount").val(PurchaseBalance);
	  $("#purchaseid").val(PurchaseId );
	 
	// $("#amount").html(SaleBalance).text();
	// $("#totaltax").html($("#totaltax"+count).text());
		 
  }
  
  function addPayment(){
	  
	  	 var purchaseId = $('#purchaseid').val();
		 var amount = $('#amount').val();
		 var note = $('#paymentnote').val();
		 alert(amount);
		 alert("Purchase Amount: " + amount + "....... Purchase ID: " + purchaseId + "....... Payment Note: " + note);
		 
		
		 item = {}
	     item ["purchaseId"] = purchaseId;
		 item ["amount"] = amount;
		 item ["note"] = note;
		 
		 jsonObj = JSON.stringify(item);
		 console.log("JSON Object",jsonObj);
		
	
	//	 alert(myJSON);
		 
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/addPurchasePayment',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : jsonObj,
				success : (function(response) {
					console.log(response);
				})
			});
		 }

  function searchTable() {
	    var input, filter, found, table, tr, td, i, j;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    table = document.getElementById("myTable");
	    tr = table.getElementsByTagName("tr");
	    for (i = 0; i < tr.length; i++) {
	        td = tr[i].getElementsByTagName("td");
	        for (j = 0; j < td.length; j++) {
	            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
	                found = true;
	            }
	        }
	        if (found) {
	            tr[i].style.display = "";
	            found = false;
	        } else {
	            tr[i].style.display = "none";
	        }
	    }
	}


// 	function printData()
// {
//    var divToPrint=document.getElementById("printTable");
//    newWin= window.open("");
//    newWin.document.write(divToPrint.outerHTML);
//    newWin.print();
//    newWin.close();
// }

function printData()
{
   var divToPrint=document.getElementById("printTable");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);

   	setTimeout(function () { // wait until all resources loaded 
		newWin.document.close(); // necessary for IE >= 10
		newWin.focus(); // necessary for IE >= 10
		newWin.print();  // change window to winPrint
		newWin.close();// change window to winPrint
       	}, 50);
  	return true;
}
	
	function ViewDetails(count){
		var sTotal = $("#quotesTotal"+count).text();
	  	  var purchaseId = $("#purchaseId"+count).text();
	  		
	  		 $("#purchaseReceipt  tbody").empty();
	  		 $("#purchaseTotal").html($("#purchaseTotal"+count).text());
	  		 $("#totaltax").html($("#totaltax"+count).text());
	  		var row = document.getElementById("purchaseReceipt");
	  		


	  		 $("#balTot").html($("#quotesTotal"+count).text());
	  		 $("#quotesTotal").html($("#quotesTotal"+count).text());
	  		 $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
	  		 $("#notee").html("Note: " + $("#note"+count).text());
	  		 $("#cName").html($("#customer"+count).text());
	  		 $("#supplier").html("Bill To :" + $("#supplier"+count).text());
	  		 $("#date").html("Date :" + $("#purchaseDate"+count).text());
	  		 $("#purchaseCreatedby").html("CreatedBy :" + $("#purchaseCreatedby"+count).text()+("<br>Date :" + $("#purchaseDate"+count).text()));
	  		 
	  		 
	  		 
	  		 
	  		 
		//	 alert($("#customer"+count).text());
	  		 $.ajax({
	  				url : '${pageContext.request.contextPath}/getPurchaseitembypurchaseId?purchaseId=' + purchaseId,
	  				type : "GET",
	  				dataType : "json",
	  				success : function(data) {
	  					var ajaxCallData = JSON.stringify(data);
	  					
	  					$.each(data, function(i, data) {
	  						var total =data.total;
	  						var rowCount = $('#purchaseReceipt tr').length-1;
	  						
	  						var tr = $('<tr></tr>');
	  						tr.append($('<td></td>').html(rowCount));
	  						tr.append($('<td></td>').html(data.product_name));
	  						tr.append($('<td></td>').html(data.quantity));
	  						tr.append($('<td></td>').html(data.unitcost));
	  						tr.append($('<td></td>').html(data.sellingprice));
	  						
	  					
	  						$('#purchaseReceipt tbody').append(tr);
	  					});

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		
	 
	
	  }
	
	
		function TransferToQuote(count){
			//var sTotal = $("#quotesTotal"+count).text();
		  	 var purchaseId = $("#purchaseId"+count).text();
		  	  $("#hiddenPurchaseId").val(purchaseId);
		
				var rowCount = $('#viewPaymentTbl tr').length-1;
				
				var tr = $('<tr></tr>');
				tr.append($('<td id="purchaseId" hidden></td>').html(purchaseId));
			
				
			
				$('#viewPaymentTbl tbody').append(tr);
				
				 $(".transfer-btn").attr("onclick", "TransferToQuotes('" + purchaseId + "')");
				
				//TransferToQuotes(purchaseId);
		}
	
	
	function TransferToQuotes(purchaseId){
		//var sTotal = $("#quotesTotal"+count).text();
	  //	 var purchaseId = $("#purchaseId"+count).text();
	  	var customerId = document.getElementById('customerPojo').value;
	  	// var purchaseId = $("#hiddenPurchaseId").val(); // Retrieve the purchaseId

	  //	alert(customerId);
	//	alert(purchaseId);
	  
		//	 alert($("#customer"+count).text());
	  		 $.ajax({
	  				url : '${pageContext.request.contextPath}/transferpurchasetoquote?purchaseId=' + purchaseId + "&customerId=" + customerId ,
	  				type : "POST",
	  				dataType : "json",
	  				success : function(data) {
	  					

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		window.location.href = "${pageContext.request.contextPath}/viewQuotesNew";

	  }
	


</script>

</body>
</html>
