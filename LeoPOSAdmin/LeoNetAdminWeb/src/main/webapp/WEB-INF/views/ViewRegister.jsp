
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
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
.btn {
    border-radius: 4px !important;
    padding: 0.375rem 0.5rem !important;
}
.btn-primary{
	font-size: 12px;
}
table.dataTable {
    clear: both;
    margin-top: 6px !important;
    margin-bottom: 6px !important;
    max-width: none !important;
    border-collapse: collapse !important;
    border-spacing: 0;
}	
.register_table{
	padding-bottom: 5px !important; padding-top: 5px !important; border-top: none !important; border-bottom: 1px solid #dee2e6;
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
							<h3 class="card-title">Register Details</h3>
						</div>

					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th>Opening balance</th>
									<th>Closing balance</th>
									<th>Status</th>

									<th hidden>Reference_no</th>
									<th hidden>Cash In Hand</th>
									<th hidden>Cash Payment</th>
									<th hidden>Cheque Payment</th>
									<th hidden>Credit Card Payment</th>
									<th hidden>Online Payment</th>
									<th hidden>Other Payment</th>
									<th>Sales amount</th>
									<th hidden>Refunds</th>
									<th>View</th>
									<th>Close</th>
									<th hidden>regid</th>



								</tr>
							</thead>
							<tbody>
								<c:forEach var="registerPojo" items="${registerPojo}"
									varStatus="loop">
								 <c:set var = "balance" value = "${registerPojo.salesamount}" />
								 <c:set var = "closingbal" value = "${registerPojo.closingbal}" />
								 <c:set var = "refund" value = "${registerPojo.refunds}" />
								 <c:set var = "cashpayment" value = "${registerPojo.cashpayment}" />
								 <c:set var = "chequepayment" value = "${registerPojo.chequepayment}" />
								 <c:set var = "creditcardpayment" value = "${registerPojo.creditcardpayment}" />
								 <c:set var = "onlinepayment" value = "${registerPojo.onlinepayment}" />
								 <c:set var = "otherpayment" value = "${registerPojo.otherpayment}" />
								  <tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="date${loop.count}">${registerPojo.date}</td>
										<td id="openingbal${loop.count}">${registerPojo.openingbal}</td>
										<td id="closingbal${loop.count}"><fmt:formatNumber pattern="0.00" value="${closingbal}" /></td>
										<td id="status${loop.count}">${registerPojo.status}</td>

										<td hidden id="referenceno${loop.count}">${registerPojo.referenceno}</td>
										<td hidden id="cashinhand${loop.count}">${registerPojo.cashinhand}</td>
										<td hidden id="cashpayment${loop.count}"><fmt:formatNumber pattern="0.00" value="${cashpayment}" /></td>
										<td hidden id="chequepayment${loop.count}"><fmt:formatNumber pattern="0.00" value="${chequepayment}" /></td>
										<td hidden id="creditcardpayment${loop.count}"><fmt:formatNumber pattern="0.00" value="${creditcardpayment}" /></td>
										<td hidden id="onlinepayment${loop.count}"><fmt:formatNumber pattern="0.00" value="${onlinepayment}" /></td>
										<td hidden id="otherpayment${loop.count}"><fmt:formatNumber pattern="0.00" value="${otherpayment}" /></td>
										<td id="salesamount${loop.count}"><fmt:formatNumber pattern="0.00" value="${balance}" /></td>
										<td hidden id="refund${loop.count}"><fmt:formatNumber pattern="0.00" value="${refund}" /></td>



										<td>
											<button type="button" class="btn btn-primary w-100 d-block mx-auto"
												data-toggle="modal" data-target="#modal-lg"
												onclick="ViewDetails(${loop.count})">View</button>
										</td>
										<td><c:if test="${registerPojo.status=='Open'}">

												<button type="button" class="btn btn-primary w-100 d-block mx-auto"
													onclick="Close(${loop.count})">Close</button>
											</c:if></td>
										<td hidden id="rid${loop.count}">${registerPojo.id}</td>



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
				<button type="button" class="close mb-3 button__border" data-dismiss="modal"
					aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
						<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
					</svg>
				</button>


				<div>
					<p>Please review the details below as <strong>Paid(Total)</strong></p>
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="register"
							class="table print-table order-table"
							width="100%" border="0"
							style="border-collapse: collapse !important;">
							<tr>
									<td hidden class="register_table">Date</td>
									<td hidden class="register_table" id="dateRegister"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>
							
								<tr>
									<td class="register_table">Opening balance</td>
									<td class="register_table" id="openingbal"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>
								<tr>	
									<td class="register_table">Cash In Hand</td>
									<td class="register_table" id="cashinhand"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Cash Payment</td>
									<td class="register_table" id="cashpayment" class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="handleCashPayment(this)"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Cheque Payment</td>
									<td class="register_table" id="chequepayment" class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="handleChequePayment(this)"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Credit Card Payment</td>
									<td class="register_table" id="creditcardpayment" class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="handleCreditCardPayment(this)"
										style="text-align: right; padding-right: 10px; font-weight: bold;" >
									</td>
									
								
								</tr>	
								<tr>
									<td class="register_table">Online Payment</td>
									<td class="register_table" id="onlinepayment" class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="handleOnlinePayment(this)"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>
								<tr>
									<td class="register_table">Other Payments / Emolument</td>
									<td class="register_table" id="otherpayment"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>		
								<tr>
									<td class="register_table">Sales amount</td>
									<td class="register_table" id="salesamount"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Refunds</td>
									<td class="register_table" id="refund"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Closing balance</td>
									<td class="register_table" id="closingbal"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>	
								<tr>
									<td class="register_table">Status</td>
									<td class="register_table" id="status"
										style="text-align: right; padding-right: 10px; font-weight: bold;">
									</td>
								</tr>

						</table>
					</div>



				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal fade" id="viewpayment" tabindex="-1"
aria-labelledby="viewpayment" aria-hidden="true">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-body">
		<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">
		
			</button>
			<div id="printTable">

				<div class="well well-sm">

					<div class="clearfix"></div>
				</div>
		
				<div class="table-responsive" style="font-size: 14px !important;">
					<table id="paymentreceipt"
						class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1"
						style="border-collapse: collapse !important; margin-bottom: 0rem;">

						<thead>
							<tr>
								<th>Amount</th>
							</tr>
						</thead>
						<tbody style="text-align: center !important;">
						
						</tbody>
					</table>

				</div>

			</div>

			
		</div>
		
	</div>
</div>
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script type="text/javascript">
 
    $(function () {
      $('#myTable').DataTable({      
        "paging": true,
        "pageLength": 20,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "scrollX": true,
      });
      //window.location.reload(true);
    });
    
    function Close(count){
    	  
		
		 //var referenceno = $("#referenceno"+count).text();
		 var rid = $("#rid"+count).text();
		
		 var registerData = {
		            "rid" : rid
		        }

		        $.ajax({
		            type: "POST",
		            url: "${pageContext.request.contextPath}/close?" + $.param(registerData),
		            dataType : 'json',
		            contentType: 'application/json'   
		        });
			alert("register closed Sucessfully !");
			document.location.reload(true);      
	
   }
    
   


    function ViewDetails(count){
    	
    $("#dateRegister").html($("#date"+count).text());	
     $("#openingbal").html($("#openingbal"+count).text());
  	 $("#cashinhand").html($("#cashinhand"+count).text());
  	 $("#cashpayment").html($("#cashpayment"+count).text());
  	 $("#chequepayment").html($("#chequepayment"+count).text());
  	 $("#creditcardpayment").html($("#creditcardpayment"+count).text());
  	 $("#onlinepayment").html($("#onlinepayment"+count).text());
  	 $("#otherpayment").html($("#otherpayment"+count).text());
  	 $("#salesamount").html($("#salesamount"+count).text());
  	 $("#closingbal").html($("#closingbal"+count).text());
  	 $("#status").html($("#status"+count).text());
  	 $("#refund").html($("#refund"+count).text());
  	
  	}
    
    
    
    function handleCreditCardPayment(element) {
        var date = $('#dateRegister').text().trim();
        console.log("Date:", date);
        var dateString = date;
        var parts = dateString.split(" ");
        var monthIndex = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06",
            "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };
        var formattedDate = parts[5] + "-" + monthIndex[parts[1]] + "-" + (parts[2].length === 1 ? '0' + parts[2] : parts[2]);
        console.log("Date:", formattedDate);
       

    	console.log("Reached");
        $.ajax({
            url: "${pageContext.request.contextPath}/creditcardpayment?date=" + formattedDate,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
            	console.log("data"+data);
            	 $('#paymentreceipt tbody').empty();
             $.each(data, function(i, data) {
            	
				
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");
					
					tr.append($('<td></td>').text(data.creditcardpayment));
					
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
            	 
            
            },
          
        });
    }
    
    
    function handleChequePayment(element) {
        var date = $('#dateRegister').text().trim();
        console.log("Date:", date);
        var dateString = date;
        var parts = dateString.split(" ");
        var monthIndex = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06",
            "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };
        var formattedDate = parts[5] + "-" + monthIndex[parts[1]] + "-" + (parts[2].length === 1 ? '0' + parts[2] : parts[2]);
        console.log("Date:", formattedDate);
       

    	console.log("Reached");
        $.ajax({
            url: "${pageContext.request.contextPath}/chequepayment?date=" + formattedDate,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
            	console.log("data"+data);
            	 $('#paymentreceipt tbody').empty();
             $.each(data, function(i, data) {
            	
				
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");
					
					var paymentInfo = "#" +data.note + " " + "$"+data.chequepayment;
					
					tr.append($('<td></td>').text(paymentInfo));
					
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
            	 
            
            },
          
        });
    }
    function handleCashPayment(element) {
        var date = $('#dateRegister').text().trim();
        console.log("Date:", date);
        var dateString = date;
        var parts = dateString.split(" ");
        var monthIndex = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06",
            "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };
        var formattedDate = parts[5] + "-" + monthIndex[parts[1]] + "-" + (parts[2].length === 1 ? '0' + parts[2] : parts[2]);
        console.log("Date:", formattedDate);
       

    	console.log("Reached");
        $.ajax({
            url: "${pageContext.request.contextPath}/cashpayment?date=" + formattedDate,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
            	console.log("data"+data);
            	 $('#paymentreceipt tbody').empty();
             $.each(data, function(i, data) {
            	
				
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");
					
					tr.append($('<td></td>').text(data.cashpayment));
					
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
            	 
            
            },
          
        });
    }
    
    function handleOnlinePayment(element) {
        var date = $('#dateRegister').text().trim();
        console.log("Date:", date);
        var dateString = date;
        var parts = dateString.split(" ");
        var monthIndex = {
            "Jan": "01", "Feb": "02", "Mar": "03", "Apr": "04", "May": "05", "Jun": "06",
            "Jul": "07", "Aug": "08", "Sep": "09", "Oct": "10", "Nov": "11", "Dec": "12"
        };
        var formattedDate = parts[5] + "-" + monthIndex[parts[1]] + "-" + (parts[2].length === 1 ? '0' + parts[2] : parts[2]);
        console.log("Date:", formattedDate);
       

    	console.log("Reached");
        $.ajax({
            url: "${pageContext.request.contextPath}/onlinepayment?date=" + formattedDate,
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
            	console.log("data"+data);
            	 $('#paymentreceipt tbody').empty();
             $.each(data, function(i, data) {
            	
				
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");
					
					tr.append($('<td></td>').text(data.onlinepayment));
					
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
            	 
            
            },
          
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


	function printData()
{
   var divToPrint=document.getElementById("printTable");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   newWin.print();
   newWin.close();
}


</script>

</body>
</html>
