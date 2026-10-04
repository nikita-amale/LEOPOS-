
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
							<h3 class="card-title">Quotes Details</h3>
						</div>

					</div>
					<div class="form-group">
						<c:if test="${not empty Msg}">
							<jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
						</c:if>
					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th>Reference_no</th>
									<th>Customer</th>
									<th hidden>Grand_Total</th>
									<th>Grand_Total</th>
									<th>Status</th>
									<th>View Quotes</th>
									<th>Edit</th>
									<th>Convert to Sale</th>
									<th hidden>Address</th>
									<th hidden>pincode</th>
									<th hidden>phoneno</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="rquotePojo" items="${rquotePojo}"
									varStatus="loop">
									<c:set var="quoteGtotal" value="${rquotePojo.grand_total}" />
									<c:set var="quotetax" value="${rquotePojo.total_tax}" />
									<c:set var="quotetotal" value="${rquotePojo.total}" />
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="rquoteDate${loop.count}">${rquotePojo.date}</td>
										<td id="rquoteId${loop.count}">${rquotePojo.rquoteId}</td>
										<td id="customer${loop.count}">${rquotePojo.member_name}</td>
										<td id="quoteTotal${loop.count}" hidden>${rquotePojo.grand_total}</td>
										<td id="gtotal${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${quoteGtotal}" /></td>
										<td id="rquoteStatus${loop.count}">${rquotePojo.status}</td>
										<td hidden id="tax${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${quotetax}" /></td>
										<td hidden id="totaltax${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${quotetotal}" /></td>
										<td>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-lg"
												onclick="EditDetails(${loop.count})">View Quotes</button>
										</td>
											<td><a type="button"
											class="btn btn-primary d-block mx-auto w-100"
											href="${pageContext.request.contextPath}/editRentalQuotes?id=${rquotePojo.rquoteId}">Edit</a>

										</td>
										
										<td><c:if test="${rquotePojo.status == 'Available'}">

												<button type="button" class="btn btn-primary" id="hide"
													onclick="ConvertToSale(${loop.count})">Convert To
													Sale</button>
											</c:if></td>
										<td hidden id="caddress${loop.count}">${rquotePojo.customeraddress}</td>
										<td hidden id="pincode${loop.count}">${rquotePojo.pincode}</td>
										<td hidden id="phonemain${loop.count}">${rquotePojo.phonemain}</td>

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
					<i class="fa fa-close">X</i>
				</button>
				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->

				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a>

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
									<td align="right">Quote</td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right" id="date"></td>
								</tr>
								<tr>
									<td>Tel: 501 207-0669</td>
									<td id="qRefno" align="right"></td>
								</tr>
								<tr>
									<td>TIN # 35467</td>
									<td align="right">Sales Person: SalesUser Sales</td>
								</tr>
							</table>
						</div>

					</div>

					<br>

					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
						Name: <label class="mb-0" id="cName"></label><br> Address: <label
							class="mb-0" id="cAddress"></label><br> <label class="mb-0"
							id="pincode"></label><br> <label class="mb-0" id="phonemain"></label><br>
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
									<td id="totaltax"
										style="text-align: center; padding-right: 10px; font-weight: bold;"></td>
								</tr>

								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Total
										Tax</td>
									<td id="tax"
										style="text-align: center; padding-right: 10px; font-weight: bold;"></td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot" style="text-align: center; font-weight: bold;"></td>
								</tr>

							</tfoot>
						</table>
					</div>


					<div class="row">
						<div class="col-xs-12"></div>


						<div class="col-xs-5 pull-right">
							<div class="well well-sm">
								<p style="font-size: 14px !important;">
									Created by: Admin <br>
								</p>
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

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script type="text/javascript">

	$(function() {
		if ('${Msg}' == "" || '${Msg}' == null) {
			$("#resultmsg").hide();
		} else {
			$("#resultmsg").show();
		}
	});
	
	

 
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
	 var qTotal = $("#quoteTotal"+count).text();
	 var rquoteId = $("#rquoteId"+count).text();
	
	 $("#quoteReceipt  tbody").empty();
	 $("#tax").html($("#tax"+count).text());
	 $("#totaltax").html($("#totaltax"+count).text());

	 $("#balTot").html($("#gtotal"+count).text());
	 $("#quoteTotal").html($("#quoteTotal"+count).text());
	 $("#qRefno").html("Quote Reference No. " + $("#rquoteId"+count).text());
	 $("#cName").html( $("#customer"+count).text());
	 $("#cAddress").html( $("#caddress"+count).text());
	 $("#pincode").html("TIN# " + $("#pincode"+count).text());
	 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
	 $("#date").html("Date :" + $("#rquoteDate"+count).text());

	 
	 
	 
//	 alert(rquoteId);
	 $.ajax({
			url : '${pageContext.request.contextPath}/getRquoteitembyrquoteId?rquoteId=' + rquoteId,
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
					tr.append($('<td></td>').html(data.quantity));
					tr.append($('<td></td>').html(real_unit_price));
					tr.append($('<td></td>').html(subtotal));
					tr.append($('<tr></tr>').html());
					$('#quoteReceipt tbody').append(tr);
				});

			},
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
	 }
 
  
  
  
  function ConvertToSale(count){
	  
		
		 var rquoteId = $("#rquoteId"+count).text();
		 var quoteData = {
		            "rquoteId" : rquoteId
		        }

		        $.ajax({
		            type: "POST",
		            url: "${pageContext.request.contextPath}/convertToSale?" + $.param(quoteData),
		            dataType : 'json',
		            contentType: 'application/json'   
		        });
			alert("Quote converted to Sale Sucessfully !");
			 window.location.replace('${pageContext.request.contextPath}/listRentalSales');	       
  	
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
