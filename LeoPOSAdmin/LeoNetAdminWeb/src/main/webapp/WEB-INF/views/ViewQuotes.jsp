
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>

<link rel="stylesheet" href="resources/css/style.css">
<style>
	.btn-primary{
		font-size: 12px;
	}
	.btn {
    border-radius: 4px !important;
    padding: 0.375rem 0.5rem !important;
}
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
							<h3 class="card-title">Quotes Details</h3>
						</div>

					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th hidden>quotesid</th>
									<th>Reference_no</th>
									<th>Customer</th>
									<sec:authorize access="hasAuthority('admin')">
									<th>Customer Type</th>
									</sec:authorize>
									<th hidden>Customer Type</th>
									<th>Grand_Total</th>
									<th hidden>tax</th>
									<th hidden>total</th>
									<th hidden>View Receipt</th>
									<th>View Receipt</th>
									<th>View Receipt</th>
									<th>Convert To Sale</th>
									<th hidden>Note</th>
									<th>Edit</th>
									<th hidden>creadtedby</th>
									<th hidden>caddress</th>
									<th hidden >pinno</th>
									<th hidden>phoneno</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="quotesPojo" items="${quotesPojo}"
									varStatus="loop">
									<c:set var = "quotesTotal" value = "${quotesPojo.grand_total}" />
									<c:set var = "total_tax" value = "${quotesPojo.total_tax}" />
									<c:set var = "total" value = "${quotesPojo.total}" />
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="quotesDate${loop.count}">${quotesPojo.date}</td>
										<td id="quoteId${loop.count}" hidden>${quotesPojo.quotesId}</td>
										<td id="referenceno${loop.count}">${quotesPojo.referenceno}</td>
										<td id="customer${loop.count}">${quotesPojo.member_name}</td>
										 <sec:authorize access="hasAuthority('admin')">
										<td id="ctype${loop.count}">${quotesPojo.ctype}</td>
										</sec:authorize>
										<td  hidden id="ctype${loop.count}">${quotesPojo.ctype}</td>
										<td id="quotesTotal${loop.count}"><fmt:formatNumber pattern="0.00" value="${quotesTotal}" /></td>
										<td hidden id="totaltax${loop.count}"><fmt:formatNumber pattern="0.00" value="${total_tax}" /></td>
										<td hidden id="total${loop.count}"><fmt:formatNumber pattern="0.00" value="${total}" /></td>

										<td hidden>
											<!-- <button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-lg"
												onclick="EditDetails(${loop.count})">View Receipt</button> -->
										</td>
										<td>
										<!-- <a type="button" style="font-size: 12px;" class="btn btn-primary"
								          href="${pageContext.request.contextPath}/viewQuotesreceipt?id=${quotesPojo.quotesId}&ctype=${quotesPojo.ctype}" target="_blank">View Receipt</a> -->

											<button type="button" class="btn btn-primary d-block mx-auto w-100" data-bs-toggle="modal" data-bs-target="#modal-lg" onclick="ViewDetails(${loop.count})">
												View Quotes
											</button>	
									</td>
									<td>
							         <!-- <a type="button" style="font-size: 12px;" class="btn btn-primary"
							          href="${pageContext.request.contextPath}/quotesReceipt?id=${quotesPojo.quotesId}&ctype=${quotesPojo.ctype}" target="_blank">View Quotes 10%</a> -->

									  <button type="button" class="btn btn-primary d-block mx-auto w-100" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="ViewDetails10(${loop.count})" >
										View Quotes 10%
									  </button>
								</td>
										<td>
										 	<c:if test= "${quotesPojo.quotes_status == 'Available'}">
										 	
											<button type="button" class="btn btn-primary d-block mx-auto w-100"  id="hide"
												onclick="ConvertToSale(${loop.count})">Convert To
												Sale</button>
											</c:if>
										</td>
											<td hidden id="note${loop.count}">${quotesPojo.note}</td>
											<td>
											<c:if test="${quotesPojo.quotes_status=='Available'}">
									             <a type="button"
												class="btn btn-primary d-block mx-auto w-100" href="${pageContext.request.contextPath}/editQuotes?id=${quotesPojo.quotesId}" >Edit</a>
												</c:if>
								</td>
								<td hidden  id="Createdby${loop.count}">${quotesPojo.createdBy}</td>
									
									<td hidden id="caddress${loop.count}">${quotesPojo.customeraddress}</td>
									<td hidden id="pincode${loop.count}">${quotesPojo.pincode}</td>
									<td hidden id="phonemain${loop.count}">${quotesPojo.phonemain}</td>
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
<div class="modal fade" id="modal-lg" tabindex="-1" aria-labelledby="modal-lg" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body" style="z-index: 9999;">
				
				<button type="button" class="btn-close button__border float-right close" data-bs-dismiss="modal" aria-label="Close">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
						<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
					</svg>
				</button>
				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->

				<!-- <a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a> -->

				<div id="printTable1">
					

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
					style="justify-content: space-between;">

						<div class="col">
							<table width="100%" style="font-size: 14px;">
								<tr>
									<td>3754 Central American Blvd.</td>
									<td align="right" ></td>
								</tr>
								<tr>
									<td>Belize City Belize</td>
									<td align="right"  id="date"></td>
								</tr>
								<tr>
									<td>Tel: 501 207-0669</td>
									<td align="right" id="qRefno"></td>
								</tr>
								<tr>
									<td>TIN # 128693</td>
									<td align="right" id="createduser"></td>
								</tr>
							</table>
						</div>

						
					</div>
					<br>
					<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 14px;">
					
						Name: <label class="mb-0" id="cName"></label><br>
						Address: <label class="mb-0" id="cAddress"></label><br>
						<label class="mb-0" id="phonemain"></label><br>
						<label class="mb-0" id="pincode"></label><br>
						
					</p>
					<!--<p style="margin-bottom: 0; font-size: 14px;">Quotes Receipt</p>-->
					<br>
					<div class="table-responsive" style="font-size: 14px !important;">
						<span style="    z-index: -1;
						font-size: 10rem;
						position: absolute;
						transform: rotate(-45deg);
						text-align: center;
						width: 100%;
						color: rgba(255, 99, 71, 0.1);
						vertical-align: middle;">Quotation</span>
						<table id="quotesReceipt"
							class="table table-bordered table-hover table-striped print-table order-table mb-0"
							width="100%" border="1"
							style="border-collapse: collapse !important;" >

							<thead>

								<tr>
									<th style="text-align: center !important;">S.No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">Quantity</th>
									<th style="text-align: center !important;">Units</th>
									<th style="text-align: center !important;">U.O.M</th>
									<th style="text-align: center !important;">Subtotal</th>
								</tr>

							</thead>

							<tbody style="text-align: center !important;">

							</tbody>
							<table class="table table-bordered table-hover table-striped print-table order-table" width="100%" border="1"
							style="border-collapse: collapse !important;">
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="totalt"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Tax</td>
									<td id="totaltaxt"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTott" style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

							</table>
						</table>
					</div>
					<div class="row">
						<div class="col pull-right">
							<p  id ="notee"style="font-size: 14px !important;"></p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12"></div>
						<div class="col pull-right">
							<div class="print-flex well well-sm">
								<p style="font-size: 14px !important;" id="Createdby">
								</p>
								
							</div>
						</div>
					</div>


					
				</div>

				<a href="javascript:void(0);" onclick="printData1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Print Invoice
				</a>
					<a href="javascript:void(0);" onclick="exportPDF1()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
					class="fa fa-print fa-sm text-white-50"></i> Export to PDF </a>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- View receipt 10% -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
	  <div class="modal-content">
		<div class="modal-body" style="z-index: 9999;">
			<button type="button" class="btn-close button__border float-right close" data-bs-dismiss="modal" aria-label="Close">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
					<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
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
						<table width="100%" style="font-size: 14px;">
							<tr>
								<td>3754 Central American Blvd.</td>
								<td align="right" ></td>
							</tr>
							<tr>
								<td>Belize City Belize</td>
								<td align="right"  id="dateten"></td>
							</tr>
							<tr>
								<td>Tel: 501 207-0669</td>
								<td align="right" id="qRefnoten"></td>
							</tr>
							<tr>
								<td>TIN # 128693</td>
								<td align="right" ></td>
							</tr>
						</table>
					</div>

	
				</div>
				<br>
				<p style="margin-bottom: 0 !important; font-size: 14px;">Bill
					To:</p>
				<p style="margin-bottom: 0; font-size: 14px;">
				
					
					Name: <label class="mb-0" id="cNameten"></label><br>
					Address: <label class="mb-0" id="cAddresss"></label><br>
					<label class="mb-0" id="phonemainn"></label><br>
					<label class="mb-0" id="pincodee"></label><br>
					
				</p>
				<!--<p style="margin-bottom: 0; font-size: 14px;">Quotes Receipt</p>-->
				<br>
				<div class="table-responsive" style="font-size: 14px !important;">
				<span style="    z-index: -1;
						font-size: 10rem;
						position: absolute;
						color: rgba(255, 99, 71, 0.1);
						transform: rotate(-45deg);
						text-align: center;
						width: 100%;
						vertical-align: middle;">Quotation</span>
				
					<table id="quotesReceipt10"
						class="table table-bordered table-hover table-striped print-table order-table mb-0"
						width="100%" border="1"
						style="border-collapse: collapse !important;">

						<thead>

							<tr>
								<th style="text-align: center !important;">S.No.</th>
								<th style="text-align: center !important;">Product Name</th>
								<th style="text-align: center !important;">Quantity</th>
								<th style="text-align: center !important;">Units</th>
								<th style="text-align: center !important;">U.O.M</th>
								<th astyle="text-align: center !important;">Subtotal</th>
							</tr>

						</thead>

						<tbody style="text-align: center !important;">

						</tbody>
						<table class="table table-bordered table-hover table-striped print-table order-table" width="100%" border="1" style="border-collapse: collapse !important;">
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total
									Amount (BZD)</td>
								<td id="totalten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Total
									Tax</td>
								<td id="totaltaxten"
									style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right; font-weight: bold;">Grand
									Total (BZD)</td>
								<td id="balTotten" style="text-align: right; font-weight: bold; width: 17%;"></td>
							</tr>

						</table>
					</table>
				</div>


			
					
					
				<div class="row">
				<div class="col pull-right">
					<p id="noteten"style="font-size: 14px !important;"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12"></div>


				<div class="col pull-right">
					<div class="print-flex well well-sm">

						<p style="font-size: 14px !important;"id="Createdbyten" >
						
						</p>
						

						
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
		<!-- <div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		  <button type="button" class="btn btn-primary">Save changes</button>
		</div> -->
	  </div>
	</div>
  </div>

<!-- View receipt 10% ends -->

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.umd.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>

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
    function exportPDF()
    {
        var element = document.getElementById('printTable');
        var opt = {
            margin:       0.5,
            filename:     'quotes'+<%=System.currentTimeMillis()%>+'.pdf',
            image:        { type: 'jpeg', quality: 1 },
            html2canvas:  { scale: 1 },
            jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
          };
        html2pdf().set(opt).from(element).save();
    }
    function exportPDF1()
    {
        var element = document.getElementById('printTable1');
        var opt = {
            margin:       0.5,
            filename:     'quotes'+<%=System.currentTimeMillis()%>+'.pdf',
            image:        { type: 'jpeg', quality: 1 },
            html2canvas:  { scale: 1 },
            jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
          };
        html2pdf().set(opt).from(element).save();
    }
    
   


    function EditDetails(count){
  	  var sTotal = $("#quotesTotal"+count).text();
  	  var quoteId = $("#quoteId"+count).text();
  		
  		 $("#quotesReceipt  tbody").empty();
  		 $("#tax").html($("#tax"+count).text());
  		 $("#totaltax").html($("#totaltax"+count).text());
  		 $("#total").html($("#total"+count).text());

  		 $("#balTot").html($("#quotesTotal"+count).text());
  		 $("#quotesTotal").html($("#quotesTotal"+count).text());
  		 $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
  		 $("#notee").html("Note: " + $("#note"+count).text());
  		 $("#cName").html($("#customer"+count).text());
  		 $("#date").html("Date :" + $("#quotesDate"+count).text());
  		 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
  		 
  		 
		
  		 $.ajax({
  				url : '${pageContext.request.contextPath}/getQuoteitembyquoteId?quoteId=' + quoteId,
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
						var rowCount = $('#quotesReceipt tr').length ;
  						
  						var tr = $('<tr></tr>');
  						tr.append($('<td></td>').html(data.id));
  						tr.append($('<td></td>').html(data.product_name));
  						tr.append($('<td></td>').html(data.quantity));
  						tr.append($('<td></td>').html(real_unit_price));
  						tr.append($('<td></td>').html(data.roll));
  						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
  						tr.append($('<tr></tr>').html());
  						$('#quotesReceipt tbody').append(tr);
  					});

  				},
  				error : function(error) {
  					console.log(`Error ${error}`);
  				}
  			});
  		 }
    
    // View Normal Receipt
    //==========================
    function ViewDetails(count){
		 // var sTotal = $("#quotesTotal"+count).text();
	  	  var quoteId = $("#quoteId"+count).text();
	  	  var ctype = $("#ctype"+count).text();
	  	
	  	  $("#quotesReceipt  tbody").empty();
	  	  $("#tax").html($("#tax"+count).text());
 		  $("#totaltax").html($("#totaltax"+count).text());
 		  $("#total").html($("#total"+count).text());
	  		
    	  $("#balTot").html($("#quotesTotal"+count).text());
	  	  $("#quotesTotal").html($("#quotesTotal"+count).text());
	  	  $("#qRefno").html("Quote Reference No. " + $("#referenceno"+count).text());
	  	  $("#notee").html("Note: " + $("#note"+count).text());
	  	  $("#cName").html($("#customer"+count).text());
	  	 $("#cAddress").html( $("#caddress"+count).text());
		 $("#pincode").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemain").html("Tel. " + $("#phonemain"+count).text());
	  	 $("#date").html("Date :" + $("#quotesDate"+count).text());
	  	 $("#Createdby").html("Created By :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
	  	 var cname = $("#customer"+count).text();
	  //	 alert(cname);
	  	 
	  	 $.ajax({
	  			url : '${pageContext.request.contextPath}/getQuoteitembyquoteId?quoteId=' + quoteId,
	  			type : "GET",
	  			dataType : "json",
	  			success : function(data) {
	  				var ajaxCallData = JSON.stringify(data);
	  					
	  				$.each(data, function(i, data) {
	  					var real_unit_price = data.real_unit_price;
						var subtotal =  data.subtotal;
						var unitname = data.roll;
						if(!isNaN(real_unit_price)){
							real_unit_price = parseFloat(real_unit_price).toFixed(2); 
						  }
						if(!isNaN(subtotal)){
							subtotal = parseFloat(subtotal).toFixed(2); 
						  }
						if(unitname == "Piece"){
							unitname = "Pc";
						  }
						if(unitname == "Box12"){
							unitname = "Box";
						  }
						var rowCount = $('#quotesReceipt tr').length;																
						var tr = $("<tr></tr>");

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						
						tr.append($('<tr></tr>').html());
	  					
	  						$('#quotesReceipt tbody').append(tr);
	  					});
	  				Calculate(cname);

	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  		
	  }
    
    // View 10% Addition receipt
    //===========================
    
    	
    function ViewDetails10(count){
		 // var sTotal = $("#quotesTotal"+count).text();
	  	  var quoteId = $("#quoteId"+count).text();
	  	  var ctype = $("#ctype"+count).text();
	  	//  alert(quoteId);
	  	
	  	  $("#quotesReceipt10  tbody").empty();
	  	 
    	 
	  	  $("#qRefnoten").html("Quote Reference No. " + $("#referenceno"+count).text());
	  	  $("#noteten").html("Note: " + $("#note"+count).text());
	  	  $("#cNameten").html($("#customer"+count).text());
	  	 $("#dateten").html("Date :" + $("#quotesDate"+count).text());
	  	 $("#cAddresss").html( $("#caddress"+count).text());
		 $("#pincodee").html("TIN# " + $("#pincode"+count).text());
		 $("#phonemainn").html("Tel. " + $("#phonemain"+count).text());
		 $("#Createdbyten").html("CreatedBy :" + $("#Createdby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
		 var cname = $("#customer"+count).text();
	  //	  alert("reaching here 10%");
	  //	  alert("Date :" + $("#quotesDate"+count).text());
		//	 
	  	 $.ajax({
	  			url : '${pageContext.request.contextPath}/getQuoteitembyquoteIdTen?quoteId=' + quoteId + "&ctype=" + ctype,
	  			type : "GET",
	  			dataType : "json",
	  			success : function(data) {
	  				var ajaxCallData = JSON.stringify(data);
	  					
	  				$.each(data, function(i, data) {
	  					
	  					var real_unit_price = data.real_unit_price;
						var subtotal =  data.subtotal;
						var unitname = data.roll;
						if(!isNaN(real_unit_price)){
							real_unit_price = parseFloat(real_unit_price).toFixed(2); 
						  }
						if(!isNaN(subtotal)){
							subtotal = parseFloat(subtotal).toFixed(2); 
						  }
						if(unitname == "Piece"){
							unitname = "Pc";
						  }
						if(unitname == "Box12"){
							unitname = "Box";
						  }
						var rowCount = $('#quotesReceipt10 tr').length ;		
																						
						var tr = $("<tr></tr>");

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						
						tr.append($('<tr></tr>').html());
						
	  					
	  						$('#quotesReceipt10 tbody').append(tr);
	  						
	  					});
	  				 CalculateTotal(cname);  
	  				},
	  				error : function(error) {
	  					console.log(`Error ${error}`);
	  				}
	  			});
	  	
	  		
	  }
    
    function CalculateTotal(cname)
    {
      var grandT = 0;
      var taxT = 0;
      var totalT = 0;
      var origtotalT = 0;
      $("#quotesReceipt10 > tbody > tr").each(function() {
       var t5 = $(this).find("td:eq(5)").text();
       if (!isNaN(t5)) {
        grandT += parseFloat(t5);
        origtotalT += parseFloat(t5);
       }
      });
       taxT = grandT * .125;
       taxT=Math.round(taxT * 100) / 100;
       totalT = grandT + taxT;
       totalT=  Math.round(totalT * 100) / 100;
       if(cname == "Def. Infra. Org. Oper. Training")
	   {
	   //  alert(cname);
	     taxT =0;
	     totalT = origtotalT;
	   }
       $("#totalten").html(grandT.toFixed(2));
       $("#totaltaxten").html(taxT.toFixed(2));
       $("#balTotten").html(totalT.toFixed(2));
    }
    function Calculate(cname)
    {
      var grandT = 0;
      var taxT = 0;
      var totalT = 0;
      var origtotalT = 0;
      $("#quotesReceipt > tbody > tr").each(function() {
       var t5 = $(this).find("td:eq(5)").text();
       if (!isNaN(t5)) {
        grandT += parseFloat(t5);
        origtotalT += parseFloat(t5);
       }
      });
       taxT = grandT * .125;
       taxT=Math.round(taxT * 100) / 100;
       totalT = grandT + taxT;
       totalT=  Math.round(totalT * 100) / 100;
       if(cname == "Def. Infra. Org. Oper. Training")
    	   {
    	   //  alert(cname);
    	     taxT =0;
    	     totalT = origtotalT;
    	   }
       $("#totalt").html(grandT.toFixed(2));
       $("#totaltaxt").html(taxT.toFixed(2));
       $("#balTott").html(totalT.toFixed(2));
    }
    
	
	function computeTableColumnTotal()
	{
	  // find the table with id attribute tableId
	  // return the total of the numerical elements in column colNumber
	  // skip the top row (headers) and bottom row (where the total will go)
			
	  var result = 0;
			
	  try
	  {
	    var tableElem = window.document.getElementById("#quotesReceipt10"); 		   
	    var tableBody = tableElem.getElementsByTagName("tbody").item(0);
	    var i;
	    var howManyRows = tableBody.rows.length;
	    for (i=1; i<(howManyRows-1); i++) // skip first and last row (hence i=1, and howManyRows-1)
	    {
	       var thisTrElem = tableBody.rows[i];
	       var thisTdElem = thisTrElem.cells[5];			
	       var thisTextNode = thisTdElem.childNodes.item(0);
	       if (debugScript)
	       {
	     //     alert("text is " + thisTextNode.data);
	       } // end if

	       // try to convert text to numeric
	       var thisNumber = parseFloat(thisTextNode.data);
	       // if you didn't get back the value NaN (i.e. not a number), add into result
	       if (!isNaN(thisNumber))
	         result += thisNumber;
		 } // end for
			 
	  } // end try
	  catch (ex)
	  {
	     window.alert("Exception in function computeTableColumnTotal()\n" + ex);
	     result = 0;
	  }
	  finally
	  {
	     return result;
	  }
		
	}
    
    
    function ConvertToSale(count){
  	  
		
		 var quoteId = $("#quoteId"+count).text();
		 var quoteData = {
		            "quoteId" : quoteId
		        }

		        $.ajax({
		            type: "POST",
		            url: "${pageContext.request.contextPath}/converquotestToSale?" + $.param(quoteData),
		            dataType : 'json',
		            contentType: 'application/json'   
		        });
			alert("Quote converted to Sale Sucessfully !");
			window.location.href = "${pageContext.request.contextPath}/viewSales";
		//	document.location.reload(true);      
 	
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

   	setTimeout(function () { // wait until all resources loaded 
		newWin.document.close(); // necessary for IE >= 10
		newWin.focus(); // necessary for IE >= 10
		newWin.print();  // change window to winPrint
		newWin.close();// change window to winPrint
       	}, 350);
  	return true;
}

function printData1()
{
   var divToPrint=document.getElementById("printTable1");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);

   	setTimeout(function () { // wait until all resources loaded 
		newWin.document.close(); // necessary for IE >= 10
		newWin.focus(); // necessary for IE >= 10
		newWin.print();  // change window to winPrint
		newWin.close();// change window to winPrint
       	}, 350);
  	return true;
}


</script>

</body>
</html>
