
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<style>
.table td, .table th {
    padding: 0.5rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
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
							<h3 class="card-title">Rental Sale Details</h3>
						</div>
		
					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th>Reference_no</th>
									<th>Customer</th>
									<th>Grand_Total</th>
									<th>Balance</th>
									<th>Status</th>
									<th>View Receipt</th>
									<th>Payment</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rsalePojo"
									items="${rsalePojo}" varStatus="loop">
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="rsaleDate${loop.count}">${rsalePojo.date}</td>
										<td id="rsaleId${loop.count}">${rsalePojo.rsaleId}</td>
										<td id="customer{loop.count}">${rsalePojo.member_name}</td>
										<td id="rsaleTotal${loop.count}">${rsalePojo.grand_total}</td>
										<td id="rsaleGtotal${loop.count}">${rsalePojo.grand_total}</td>
										<td id="rsaleStatus${loop.count}">${rsalePojo.payment_status}</td>
										<td hidden id="tax${loop.count}">${rsalePojo.total_tax}</td>
										<td hidden id="totaltax${loop.count}">${rsalePojo.total}</td>
									
										<td>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-lg"
												onclick="EditDetails(${loop.count})">View Sale Receipt</button>
										</td>
										<td>
										<c:if test= "${rsalePojo.payment_status == '1'}">
										 	
											<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#modal-llg"
											>Payment</button>
												</c:if>
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
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                <i class="fa fa-close">X</i>
            </button>
            <!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->

			<a href="javascript:void(0);" onclick="printData()"	class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a>	
          
			<div id="printTable">    
	
            <div class="well well-sm">
                
                <div class="clearfix"></div>
            </div>

            <div class="row" style="margin-bottom:15px;justify-content: space-between;padding:0rem 1rem;">

				<div class="col">
					<table width="100%" style="font-size: 14px;">
						<tr>
							<td>3754 Central American Blvd.</td>
							<td align="right">Tax Invoice</td>
						</tr>
						<tr>
							<td>Belize City Belize</td>
							<td align="right">Date: 30/03/2022	17:12</td>
						</tr>
						<tr>
							<td>Tel: 501 207-0669</td>
							<td align="right">Sale No. ref: SALE2022/03/13247</td>
						</tr>
						<tr>
							<td>TIN # 128693</td>
							<td align="right">Sales Person: SalesUser Sales</td>
						</tr>
					</table>
				</div>
                
                <!-- <div class="col-xs-6">
                  <p style="margin-bottom: 0; font-size: 14px;">3754 Central American Blvd.</p>
				  <p style="margin-bottom: 0; font-size: 14px;">Belize City Belize</p>          
				  <p style="margin-bottom: 0; font-size: 14px;">Tel: 501 207-0669</p>
				  <p style="margin-bottom: 0; font-size: 14px;">TIN # 35467</p>
				</div>

                <div class="col-xs-6">
					<p style="margin-bottom: 0; font-size: 14px;">Tax Invoice</p>
					<p style="margin-bottom: 0; font-size: 14px;">Date: 30/03/2022	17:12</p>          
					<p style="margin-bottom: 0; font-size: 14px;">Sale No. ref: SALE2022/03/13247</p>
					<p style="margin-bottom: 0; font-size: 14px;">Sales Person: SalesUser Sales</p>          
				</div> -->

            </div>

			<br>

			<p style="margin-bottom: 0 !important; font-size: 14px;">Bill To:</p>
			<p style="margin-bottom: 0; font-size: 14px;"><strong>Luis Moses</strong></p>
			<p style="margin-bottom: 0; font-size: 14px;">Rental Sales Receipt</p>
			
			<br>

        
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="quoteReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th>No.</th>
									<th>Description</th>
									<th>Quantity</th>
									<th>Unit Price</th>
									<th>Subtotal</th>
								</tr>

							</thead>

							<tbody>


							</tbody>
							<tfoot>


								<tfoot>
							<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="totaltax"
										style="text-align: right; padding-right: 10px; font-weight: bold;"></td>
								</tr>

								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total Tax
										</td>
									<td id="tax"
										style="text-align: right; padding-right: 10px; font-weight: bold;"></td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot" style="text-align: right; font-weight: bold;"></td>
								</tr>


							</tfoot>
						</table>
					</div>
            
            <div class="row">
                <div class="col-xs-12">
                                    </div>

                
                <div class="col-xs-5 pull-right">
                    <div class="well well-sm">
                        <p style="font-size: 14px !important;">
                            Created by: P <br>
                            Date: 30/03/2022 04:50                        </p>
                                            </div>
                </div>
            </div>
                          
                    </div>
		</div>			
    </div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->










<div class="modal fade" id="modal-llg">
	<div class="modal-dialog modal-llg">
		<div class="modal-content">
        <div class="modal-body">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                <i class="fa fa-close">X</i>
            </button>
         
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="quoteReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead>

								<tr>
									<th>No.</th>
									<th>Description</th>
									<th>Quantity</th>
									<th>Unit Price</th>
									<th>Subtotal</th>
								</tr>

							</thead>

							<tbody>


							</tbody>
							<tfoot>


								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="quoteTotal"
										style="text-align: right; padding-right: 10px; font-weight: bold;"></td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot" style="text-align: right; font-weight: bold;"></td>
								</tr>
								<tr>
								<td ><button  onclick="payment()"> Pay</button></td>
								</tr>

							</tfoot>
							</table>
					</div>
            
    </div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
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
   


  function EditDetails(count){	  
	  var qTotal = $("#rsaleTotal"+count).text();
		 var rsaleId = $("#rsaleId"+count).text();
		 $("#quoteReceipt  tbody").empty();

		 $("#balTot").html($("#rsaleGtotal"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#tax").html($("#tax"+count).text());
		 $("#quoteTotal").html($("#rsaleTotal"+count).text());
		 $("#qRefno").html("Quote Reference No. " + $("#rsaleId"+count).text());
		 $("#cName").html( $("#customer"+count).text());
		 

  $.ajax({
		url : '${pageContext.request.contextPath}/getRsalesitembyrsaleId?rsaleId=' + rsaleId,
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
				$('#quoteReceipt tbody').append(tr);
			});

		},
		error : function(error) {
			console.log(`Error ${error}`);
		}
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
function payment(){
	alert('payment done successfully');
}



</script>

</body>
</html>
