
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<h5 class="mb-0 text-dark">Rental Sale Details</h5>
						</div>
					<div class="card-body">

						<table id="myTable" class="table table-bordered table-hover table-responsive">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Date</th>
									<th>Ref. No</th>
									<th>Customer</th>
									<th>Grand_Total</th>
									<th>Balance</th>
									<th>Status</th>
									<th>View Receipt</th>
									<th>Payment</th>
									<th>View Payment</th>
									<th>Way Bill</th>
									<th hidden>Address</th>
									<th hidden>pincode</th>
									<th hidden>phoneno</th>
									<th hidden>deliverydate</th>
									<th hidden>pickupdate</th>
									<th hidden>note</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="rsalePojo" items="${rsalePojo}" varStatus="loop">
									<c:set var="balance"
										value="${rsalePojo.grand_total - rsalePojo.paid}" />
										<c:set var="saleGtotal"
										value="${rsalePojo.grand_total}" />
											<c:set var="saletax"
										value="${rsalePojo.total_tax}" />
										<c:set var="saletotal"
										value="${rsalePojo.total}" />
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="rsaleDate${loop.count}">${rsalePojo.date}</td>
										<td id="rsaleId${loop.count}">${rsalePojo.rsaleId}</td>
										<td id="customer${loop.count}">${rsalePojo.member_name}</td>
										<td id="rsaleGtotal${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${saleGtotal}" /></td>
										<td id="balance${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${balance}" /></td>
										
										<c:choose>
											<c:when test="${rsalePojo.payment_status=='1.0'}">
												<td id="rsaleStatus${loop.count}">Due</td>
											</c:when>
											<c:otherwise>
												<td id="rsaleStatus${loop.count}">Paid</td>
											</c:otherwise>
										</c:choose>
										<td hidden id="tax${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${saletax}" /></td>
										<td hidden id="totaltax${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${saletotal}" /></td>

										<td>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-lg"
												onclick="EditDetails(${loop.count})">Sale
												Receipt</button>
										</td>
										<td><c:if test="${rsalePojo.payment_status=='1.0'}">
												<button style="font-size: 14px;" type="button"
													class="btn btn-primary" data-toggle="modal"
													onclick="loadPayment(${loop.count})"
													data-target="#modal-payment">Add Payment</button>
											</c:if></td>
												<td>
												
											<button type="button" style="font-size: 14px;"
												class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="ViewPayment(${loop.count})">View</button>
										</td>
										<td>
												
											<button type="button" style="font-size: 14px;"
												class="btn btn-primary" data-toggle="modal"
												data-target="#WayBill"
												onclick="WayBill(${loop.count})">WayBill</button>
										</td>
										
										
										<td hidden id="caddress${loop.count}">${rsalePojo.customeraddress}</td>
										<td hidden id="pincode${loop.count}">${rsalePojo.pincode}</td>
										<td hidden id="phonemain${loop.count}">${rsalePojo.phonemain}</td>
										<td hidden id="deliverydate${loop.count}">${rsalePojo.deliverydate}</td>
										<td hidden id="pickupdate${loop.count}">${rsalePojo.pickupdate}</td>
										<td hidden id="note${loop.count}">${rsalePojo.note}</td>


									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
					<!-- /.card-body -->
					</div>
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
					<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
						<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
					  </svg>
				</button>
				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->

				

				<div id="printTable">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between; padding: 0rem 1rem;">

						<div class="col">
							<table width="100%" style="font-size: 14px;">
							<tr>
									<td><b>Exquisite Event Rentals</b></td>
									
								</tr>
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right">Tax Invoice</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date"></td>
								</tr>
								<tr>
									<td>Tel: 501 207-0669</td>
									<td align="right" id="qRefno"></td>
								</tr>
								<tr>
									<td>TIN # 35467</td>
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
                      <div style="padding: 0rem 1rem;">
					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						Name: <label class="mb-0" id="cName"></label><br> Address: <label
							class="mb-0" id="cAddress"></label><br> <label class="mb-0"
							id="pincode"></label><br> <label class="mb-0" id="phonemain"></label><br>
							</p>
						</div>

						<br>
					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="quoteReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important;">

							<thead style="text-align: center !important;">

								<tr>
									<th>No.</th>
									<th>Description</th>
									<th >Image</th>
									<th>Quantity</th>
									<th>Unit Price</th>
									<th>Subtotal</th>
								</tr>

							</thead>

							<tbody style="text-align: center !important;">


							</tbody>
									<tfoot>
							<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="totaltax" colspan="2"
										style="text-align: right; font-weight: bold;"></td>
								</tr>

								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total Tax
										</td>
									<td id="tax"  colspan="2"
										style="text-align: right; font-weight: bold;"></td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot"   colspan="2" style="text-align: right; font-weight: bold;"></td>
								</tr>

							</tfoot>

						</table>
						

					</div>

					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col-xs-5 pull-right">
							<div class="well well-sm">
								<p style="font-size: 14px !important; padding-left: 0.5rem;">
									Created by: Admin <br>
								</p>
							</div>
						</div>
					</div>

				</div>

				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a>
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
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-close">X</i>
				</button>

				<div class="table-responsive" style="font-size: 14px !important;">
					<table id="quoteReceipt"
						class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1"
						style="border-collapse: collapse !important; margin-bottom: 0rem;">

						<thead style="text-align: center !important;">

							<tr>
								<th style="text-align: center !important;">No.</th>
								<th style="text-align: center !important;">Description</th>
								<th style="text-align: center !important;">Image</th>
								<th style="text-align: center !important;">Quantity</th>
								<th style="text-align: center !important;">Unit Price</th>
								<th style="text-align: center !important;">Subtotal</th>
							</tr>

						</thead>

						<tbody style="text-align: center !important;">


						</tbody>
						<tfoot>


							<tr>
								<td colspan="4" style="text-align: right; font-weight: bold;">Total
									Amount (BZD)</td>
								<td id="quoteTotal"
									style= "text-align: right;" padding-right: 10px; font-weight: bold;"></td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: right; font-weight: bold;">Grand
									Total (BZD)</td>
								<td id="balTot" style="text-align: right; font-weight: bold;"></td>
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


<div class="modal fade" id="modal-payment">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-close"
						style="font-size: 16px; color: #1eb53a !important;">close</i>
				</button>
				<div class="form-group">
					<label for="exampleInputEmail1">Total Amount</label> <input
						type="text" class="form-control" id="amount" readonly="true";>
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Pay Amount</label> <input
						type="text" class="form-control" id="payamount"
						placeholder="Enter Amount to be paid" Required>
				</div>
				<input type="hidden" class="form-control" id="hidsaleid">
				<div class="form-group">
					<label for="exampleInputEmail1">Payment Type</label> <select
						class="form-control select2bs4" id="ptype" style="width: 100%;">
						<option val="">Please Select Payment Type</option>
						<option val="Cheque">Cheque</option>
						<option val="Online">Online</option>
						<option val="Credit Card">Credit Card</option>
						<option val="Cash">Cash</option>
					</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Cheque No/CC No</label> <input
						type="text" class="form-control" id="chequecc"
						placeholder="Enter Cheque / Receipt">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Note</label> <input type="text"
						class="form-control" id="paymentnote" placeholder="Enter Note">
				</div>
				<div class="card-footer">
					<button type="button" onclick="addPayment()"
						class="btn btn-primary">Add Payment</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>

<!--View payment-->
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
				<div id="printTable1">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<table width="100%" style="font-size: 14px;">
						<tr>
							<td><b>Exquisite Event Rentals</b></td>
						</tr>
					</table>

					<br>

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;" id="viewPaymentTbl">


								<tr>
									<td>3754 Central American Blvd.</td>

								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date10"></td>
								</tr>
								<tr>
								<tr>
									<td>Tel: 501 207-0669</td>
								</tr>

								<tr>
									<td align="left" id="customerName"></td>
								</tr>
								<tr>
									<td align="left" id="pAddress"></td>
								</tr>

								<c:forEach var="paymentPojo" items="${paymentPojo}"
									varStatus="loop">
									<tr>
										<td align="left">Date Received:${paymentPojo.paymentdate}</td>
									</tr>
								</c:forEach>

							</table>
						</div>


					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="paymentreceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>

								<tr>

									<th>Serial no.</th>
									<th>Payment Method</th>
									<th>#</th>
									<th>Payment Amount</th>
									<th>Sale id</th>
									<th>Date Recevied</th>
									<th>Note</th>


								</tr>

							</thead>

							<tbody style="text-align: center !important;">



							</tbody>
						</table>

					</div>


					<div class="row">
						<div class="col pull-right">
							<p id="paynote" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col pull-right">
							<div class="print-flex well well-sm">

								<p style="font-size: 14px !important;" id="Createdbyten"></p>



							</div>

						</div>

					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right"></div>

					</div>


				</div>

				<a href="javascript:void(0);" onclick="printData1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> 
			</div>
			<!-- <div class="modal-footer">
<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary">Save changes</button>
</div> -->
		</div>
	</div>
</div>




<div class="modal fade" id="WayBill" tabindex="-1"
	aria-labelledby="WayBill" aria-hidden="true">
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
				<div id="printTable2">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>

					<table width="100%" style="font-size: 14px;">
						<tr>
							<td><b>Exquisite Event Rentals</b></td>
						</tr>
					</table>

					<br>

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;" id="viewPaymentTbl">


								<tr>
									<td>Way Bill</td>

								</tr>
								
							</table>
						</div>
						
						

					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="waybill"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0rem;">

							<thead>
                            
							</thead>

							<tbody >



							</tbody>
						</table>

					</div>


					<div class="row">
						<div class="col pull-right">
							<p id="paynote" style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col pull-right">
							<div class="print-flex well well-sm">

								<p style="font-size: 14px !important;" id="Createdbyten"></p>



							</div>

						</div>

					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right"></div>

					</div>


				</div>

				<a href="javascript:void(0);" onclick="printData2()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a> 
			</div>
			<!-- <div class="modal-footer">
<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary">Save changes</button>
</div> -->
		</div>
	</div>
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
    
    function loadPayment(count){
  	  var SaleBalance = $("#rsaleGtotal"+count).text();
  	  var saleTotal = $("#rsaleGtotal"+count).text();
  	  
  	  var SaleId = $("#rsaleId"+count).text();
  	 // var SalePaid = $("#salePaid"+count).text();
  	  
  	  //alert("RentalSale Balance: " + SaleBalance + "....... RentalSale ID: " + SaleId);
  	  $("#amount").val(saleTotal);
  	  $("#hidsaleid").val(SaleId);
  	  //$("#paidamount").val(SalePaid);
  	 
  	// $("#amount").html(SaleBalance).text();
  	// $("#totaltax").html($("#totaltax"+count).text());
  		 
    }
   
    function addPayment(){
  	  
	  	 var saleId = $('#hidsaleid').val();
		 var amount = $('#amount').val();
		 var note = $('#paymentnote').val();
		// var paid = $('#paidamount').val();
		 var amounttopay = $('#payamount').val();
		 var pref = $('#chequecc').val();
		 var ptype = $('#ptype :selected').val();
		// alert(ptype);
		 var flag =0;
		 
		 
		 
		 if (ptype == "")
			{
			  alert("The Payment type must be selected !");
			  flag =1;
			}
		 
		 if (pref == "" && ptype!="Cash" && ptype!="Credit Card" && ptype!="Online")
			{
			  alert("The Payment reference number must be entered !");
			  flag =1;
			}
		 
		 
		 if(flag ==0)
		 
		 {
		 
		// alert("Sale Amount: " + amount + "....... Sale ID: " + saleId + "....... Payment Note: " + note);
		 
		
		 item = {}
	     item ["saleId"] = saleId;
		 item ["amount"] = amounttopay;
		 item ["note"] = note;
		 item ["ptype"] = ptype;
		 item ["pref"] = pref;
		 
		 jsonObj = JSON.stringify(item);
		 console.log("JSON Object",jsonObj);
		
	
	//	 alert(myJSON);
		 
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${pageContext.request.contextPath}/addPayment',
				contentType : 'application/json; charset=utf-8',
				crossDomain : true,
				data : jsonObj,
				success : (function(response) {
					console.log(response);
				})
			});
			alert("Payment Added Succedfully !");
			location.reload();
		  }
 		}


  function EditDetails(count){	  
	  var qTotal = $("#rsaleTotal"+count).text();
		 var rsaleId = $("#rsaleId"+count).text();
		 
		 $("#quoteReceipt  tbody").empty();

		 $("#balTot").html($("#rsaleGtotal"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#tax").html($("#tax"+count).text());
		 $("#quoteTotal").html($("#rsaleTotal"+count).text());
		 $("#qRefno").html("Sales Reference No. " + $("#rsaleId"+count).text());
		 $("#cName").html( $("#customer"+count).text());
		 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
		 $("#date").html("Date :" + $("#rsaleDate"+count).text());

  $.ajax({
		url : '${pageContext.request.contextPath}/getRsalesitembyrsaleId?rsaleId=' + rsaleId,
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			$.each(data, function(i, data) {
				var real_unit_price = data.real_unit_price;
				var subtotal =  data.subtotal;
				if(!isNaN(real_unit_price)){
					real_unit_price = parseFloat(real_unit_price).toFixed(2); 
				  }
				if(!isNaN(subtotal)){
					subtotal = parseFloat(subtotal).toFixed(2); 
				  }
				
				var tr = $('<tr></tr>');
				tr.append($('<td></td>').html(i+1));
				tr.append($('<td></td>').html(data.product_name));
				  var tdImage = $('<td style="padding: 1; text-align: center;"></td>');
	                var link = $('<a></a>')
	                    .attr('href', '${pageContext.request.contextPath}/images/' + data.productFileName)
	                    .attr('data-lightbox', 'product-gallery')
	                    .attr('data-title', 'Product Image');
	                var img = $('<img>')
	                    .addClass('me-2 mb-2')
	                    .attr('src', '${pageContext.request.contextPath}/images/' + data.productFileName)
	                    .attr('style', 'width: 10%; height: 10%;')
	                    .attr('alt', 'Product Image');
	                link.append(img);
	                tdImage.append(link);
	                tr.append(tdImage);
				tr.append($('<td></td>').html(data.quantity));
				tr.append($('<td></td>').html(real_unit_price));
				tr.append($('<td style= "text-align: right;"></td>').html(subtotal));
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
function printData1()
{
   var divToPrint=document.getElementById("printTable1");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   newWin.print();
   newWin.close();
}
function printData2()
{
   var divToPrint=document.getElementById("printTable2");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   newWin.print();
   newWin.close();
}
function payment(){
	alert('payment done successfully');
}
function ViewPayment(count) {
	
	
	 var memberId = $("#memberId"+count).text();
	 var SaleId = $("#rsaleId"+count).text();
	// var ctype = $("#ctype"+count).text();
	// alert(SaleId);
	 $('#paymentreceipt tbody').empty();
	 
	 $("#customerName").html("Customer Name: " + $("#customer"+count).text());
	 $("#pAddress").html("Customer Address:"+ $("#caddress"+count).text());
	

	 $.ajax({
			url : '${pageContext.request.contextPath}/getPaymentsaleid?SaleId=' + SaleId ,
			
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				
				$.each(data, function(i, data) {
					
					//String paymentdate = json.getString(data.paymentdate);//"2013-03-26"
					//DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
					const paymentdate =(data.date);
					const dt = new Date(paymentdate);
					//alert(paymentdate);
					   
					//var d=paymentdate.Format("dd/MM/yyyy");
					//var paymentdate =(data.paymentdate)Date;
					//alert(dt);
					var rowCount = $('#paymentreceipt tr').length ;
					var tr = $("<tr></tr>");

					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.ptype));
					tr.append($('<td></td>').html(data.pref));
					tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
					tr.append($('<td></td>').html(data.rsaleId));
					tr.append($('<td></td>').html(dt));
					tr.append($('<td></td>').html(data.note));
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
				});
				//$('#paymentreceipt tbody').empty();
				

			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
	
	
}





function WayBill(count){	
	  var qTotal = $("#rsaleTotal"+count).text();
		 var rsaleId = $("#rsaleId"+count).text();
		 
		 $("#waybill  tbody").empty();

		 $("#balTot").html($("#rsaleGtotal"+count).text());
		 $("#totaltax").html($("#totaltax"+count).text());
		 $("#tax").html($("#tax"+count).text());
		 $("#quoteTotal").html($("#rsaleTotal"+count).text());
		 $("#qRefno").html("Sales Reference No. " + $("#rsaleId"+count).text());
		 $("#cName").html( $("#customer"+count).text());
		 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
		 $("#date").html("Date :" + $("#rsaleDate"+count).text());

$.ajax({
		url : '${pageContext.request.contextPath}/getRsalesitembyrsaleId?rsaleId=' + rsaleId,
		type : "GET",
		dataType : "json",
		 success: function (data) {
	            var ajaxCallData = JSON.stringify(data);

	            var billNo = $("#rsaleId" + count).text();
	            var date = $("#rsaleDate" + count).text();
	            var customerName = $("#customer" + count).text();
	            var customerAdress = $("#caddress" + count).text();
	            var customerTin = $("#pincode" + count).text();
	            var customerPhone = $("#phonemain" + count).text();
	            var deliveryDate = $("#deliverydate" + count).text();
	            var pickupDate = $("#pickupdate" + count).text();
	            var note = $("#note" + count).text();

	            // Collect product names for the current Bill No
	            var productInfoArray = [];

	            $.each(data, function (i, data) {
	            	 var product_name = data.product_name;
	                 var quantity = data.quantity;
	                 var productInfo = product_name + "--" + quantity;
	                 productInfoArray.push(productInfo);
	            });
	            var productInfo = productInfoArray.join('<br>'); 

	            // Format the information
	            var billInfo = "<b>Bill No:</b> " + billNo + "<br>" +
	                "<b>Date: </b>" + date + "<br>" +
	                "<b>Customer Name:</b> " + customerName + "<br>" +
	                "<b>Customer Adress:</b> " + customerAdress + "<br>" +
	                "<b>TIN:</b> " + customerTin + "<br>" +
	                "<b>Customer Phone:</b> " + customerPhone + "<br>" +
	                "<b>Delivery Date:</b> " + deliveryDate + "<br>" + 
	                "<b>Pickup Date:</b> " + pickupDate + "<br>" +  
	                "<b>Product And Quantity: </b> <br>" +   productInfo +"<br>" +  
	                "<b>Note:</b> " + note  ;

	            // Set the formatted information in the modal
	            $("#waybill tbody").html('<tr><td colspan="6">' + billInfo + '</td></tr>');
	        },
	        error: function (error) {
	            console.log(`Error ${error}`);
	        }
	    });
	}



</script>

</body>
</html>
