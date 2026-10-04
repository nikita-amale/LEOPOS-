
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
.button__border {
	border: none;
	background: transparent;
}
.btn {
    border-radius: 4px !important;
    padding: 0.375rem 0.5rem !important;
}
button:focus {
	outline: transparent !important;
	outline: transparent !important;
}
table.dataTable {
    clear: both;
    margin-top: 6px !important;
    margin-bottom: 6px !important;
    max-width: none !important;
    border-collapse: collapse !important;
    border-spacing: 0;
}
.shadow {
    box-shadow: 0 .1rem 1rem rgba(0,0,0,0.1)!important;
}
.wrapper .content-wrapper {
    min-height: calc(100vh - calc(3.5rem + 1px) - calc(3.5rem + 1px)) !important;
}
.show-div {
	display: flex;
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
							<h3 class="card-title">Returns Details</h3>
						</div>

					</div>
					<div class="show-div">
						<label for="exampleInputEmail1">Show</label> <select id="show" >
							<option
								href="${pageContext.request.contextPath}/getReturnsLispage?pageSize=10"
								value="10">10</option>
							<option
								href="${pageContext.request.contextPath}/getReturnsLispage?pageSize=25"
								value="25">25</option>
							<option
								href="${pageContext.request.contextPath}/getReturnsLispage?pageSize=50"
								value="50">50</option>
						</select>
						<div class="form-search" style="position:absolute; right:0;">
						  <form action="${pageContext.request.contextPath}/searchReturn">
							<input type="text" placeholder="Searchreturn" name="search">
							<button type="submit">
								<i class="fa fa-search"></i>
							</button>
						</form>
						</div>
					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								
									<th>Serial No</th>
									<th>Date</th>
									<th>Return Id</th>
									<th>Return Reference No.</th>
									<th>Customer Name</th>
									<th>Amount</th>
									<th>Tax</th>
									<th>Applied To SaleId</th>
									<th hidden>total</th>
									<th hidden>CreatedBy</th>
									<th>View Receipt</th>
									<th>Status</th>
									<th>Edit</th>
									<th>SaleReferno</th>
									<sec:authorize access="hasAuthority('admin')">
									<th>Delete</th>
									</sec:authorize>
									<th hidden>note</th>
								
							</tr>
							</thead>
							<tbody>
								<c:forEach var="returnPojo" items="${returnPojo}"
									varStatus="loop">
									<c:set var="currentPage" value="${page}" />
											<c:set var="pageSize" value="${pageSize}" />
											<c:set var="serialNumber"
												value="${(currentPage) * pageSize + loop.index}" />
									<c:set var = "amount" value = "${returnPojo.amount}" />
									<c:set var = "tax" value = "${returnPojo.tax}" />

									<tr id="${loop.count}">
										<td>${serialNumber+1}</td>
										<td id="quotesDate${loop.count}">${returnPojo.date}</td>
										<td id="returnId${loop.count}">${returnPojo.returnId}</td>
										<td id="returnRefno${loop.count}">${returnPojo.referenceno}</td>
										<td id="customer${loop.count}">${returnPojo.member_name}</td>
										<td id="amount${loop.count}"><fmt:formatNumber pattern="0.00" value="${amount}" /></td>
										<td id="tax${loop.count}"><fmt:formatNumber pattern="0.00" value="${tax}" /></td>
										<c:choose>
										<c:when test="${returnPojo.returnType == '1'}">
										<td id="applied${loop.count}">${returnPojo.saleid}</td>
										</c:when>
										<c:otherwise>
										<td id="applied${loop.count}">Store as Credit</td>
										</c:otherwise>
										</c:choose>
										<td hidden id="rettotal${loop.count}">${returnPojo.userid}</td>
										<td hidden id="purchaseCreatedby${loop.count}">${returnPojo.createdBy}</td>

										<td>
											<button type="button"
												class="btn btn-primary d-block mx-auto w-100" data-toggle="modal"
												data-target="#modal-lg" onclick="ViewDetails(${loop.count})">View
												Receipt</button>
										</td>
										<c:if test="${returnPojo.isActive == '0'}">
											<c:set var="status" value="Active"/>
											</c:if>
												<c:if test="${returnPojo.isActive == '1'}">
											<c:set var="status" value="Deleted"/>
											</c:if>
											
										<td id="status${loop.count}">${status}</td>	
									
											<td><c:if test="${returnPojo.isActive == '0'}"><a type="button"
												class="btn btn-primary d-block mx-auto w-100"
												href="${pageContext.request.contextPath}/editReturns?returnId=${returnPojo.returnId}">Edit</a></c:if>
											</td>
											<td id="salereferenceno${loop.count}">${returnPojo.salereferenceno}</td>
											<sec:authorize access="hasAuthority('admin')">
											
											<td><c:if test="${returnPojo.isActive == '0'}">
												<button type="button" class="btn btn-primary d-block mx-auto w-100"
													onclick="deleteDetails(${loop.count},${returnPojo.returnId})">Delete</button>
											</c:if></td>
											</sec:authorize>
											
											<td hidden id="note${loop.count}">${returnPojo.note}</td>
											
									</tr>
									
								</c:forEach>

							</tbody>

						</table>Showing ${currentRecords} of ${totalRecords} entries
					</div>
						<div class=".bottom-right" style=" position: absolute; bottom: 0; right: 0;padding-bottom: 30px; ">
					<table border="1" cellpadding="5" cellspacing="5">

						<c:set var="currentPage" value="${page}" />
						<c:set var="pageSize" value="${pageSize}" />

						<tr>
						   <c:if test="${previous}">
							<td  ><a href="getReturnsListNew?page=${currentPage - 1}" style="color: black;">Previous</a></td>
							</c:if>
                             <c:if test="${next}">
							<c:forEach begin="${currentPage}" end="${currentPage+4}" var="i">
							
								<c:choose>
									<c:when test="${currentPage == i}">
										<td style="background-color: blue;"><a
											href="getReturnsListNew?page=${i}" style="color: white;">${i}</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="getReturnsListNew?page=${i}" >${i}</a></td>
									</c:otherwise>

								</c:choose>
								

							</c:forEach>
							</c:if>



							<c:if test="${next}">
								<td><a href="getReturnsListNew?page=${currentPage +1}" style="color: black;">Next</a></td>
							</c:if>

						</tr>
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
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
						fill="currentColor" class="bi bi-x-square-fill text-secondary"
						viewBox="0 0 16 16">
						<path
							d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
					</svg>
				</button>

				<!-- <button type="button" class="btn btn-xs btn-default no-print pull-right" style="margin-right:15px;" onclick="window.print();">
                <i class="fa fa-print"></i> Print            </button> -->



				<div id="printTable">

					<div class="well well-sm">

						<div class="clearfix"></div>
					</div>
					<div id="pdfContent">
					<div  class="logo-print"> 
					<img id="logoImage"
						src="${pageContext.request.contextPath}/resources/images/logo_s.png"
						width="280px"  />
				</div>

				<br> <br> 
				

					<div class="" style="justify-content: space-between;">

					<div id="headerDiv" class="row"
					style="justify-content: space-between; position: relative; width: 100%; ">

					<div class="row"
						style="justify-content: space-between; font-size: 12px; width: 100%; margin-top: -30px; margin-bottom: 0px;">
						<div class="col col-left">
							<!-- Left content goes here -->
							3754 Central American Blvd.<br> Belize City Belize<br>
							Tel: 501 207-0669<br> TIN # 128693
						</div>
						<div class="col col-right"
							style="float: right; text-align: right;">
							<!-- Right content goes here -->
							Tax Invoice <br>
							<span id="date"></span><br>
							<span id="sRefno"></span><br>
							<span id="salereferenceno"></span><br>
						</div>
					</div>

				</div>
				
						


					</div>

					<br>
					<p style="margin-bottom: 0; font-size: 13px;">Returns Receipt</p>

					<!-- <p style="margin-bottom: 0 !important; font-size: 12px;" id="customer">
						 </p>
					 -->
					<p style="margin-bottom: 0 !important; font-size: 13px;">Bill
						To:</p>
					<p style="margin-bottom: 0; font-size: 12px;">

						Name: <label class="mb-0" id="cName"></label><br> 
						Address: <label class="mb-0" id="cAddress"></label><br> 
						<label class="mb-0" id="phonemain"></label><br>
						<label class="mb-0" id="pincode"></label><br>
						
					</p>
					<br>
					</div>

					<div class="table-responsive" style="font-size: 14px !important;">
						<table id="returnReceipt"
							class="table table-bordered table-hover table-striped print-table order-table"
							width="100%" border="1"
							style="border-collapse: collapse !important; margin-bottom: 0 !important;">

							<thead>

								<tr>
									<th style="text-align: center !important;">No.</th>
									<th style="text-align: center !important;">Product Name</th>
									<th style="text-align: center !important;">Unit Price</th>
									<th style="text-align: center !important;">Unit of Measure</th>
									<th style="text-align: center !important;">Quantity</th>
									<th style="text-align: center !important;">Subtotal</th>
								</tr>

							</thead>

							<tbody id="tableBody" style="text-align: center !important;">

							</tbody>
							<table class="table table-bordered table-hover table-striped print-table order-table totalTable"
									id="fullWidthTable" width="100%" border="1"
									style="border-collapse: collapse !important;">
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Amount (BZD)</td>
									<td id="total"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Total
										Tax</td>
									<td align="center" id="tax"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>
								<tr>
									<td colspan="5" style="text-align: right; font-weight: bold;">Grand
										Total (BZD)</td>
									<td id="balTot"
										style="text-align: right; font-weight: bold; width: 17%;"></td>
								</tr>

							</table>
						</table>
					</div>
					<div id="notes">
						<div class="row">
					<div class="col pull-right">
						<p id="note" style="font-size: 14px !important;"></p>
					</div>
				</div>



					<div class="row">
						<div class="col-xs-12"></div>


					<div class="col pull-right">
						<div class="print-flex well well-sm">
								
								<p style="font-size: 14px !important;" id="purchaseCreatedby">
								</p>
							</div>
						</div>
					</div>

					</div>
					
				</div>
				<a href="javascript:void(0);" onclick="printData()"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
				class="fa fa-print fa-sm text-white-50"></i> Print Invoice</a> 
					
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

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.umd.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>


<script type="text/javascript">
 
    $(function () {
      $('#myTable').DataTable({      
        "paging": false,
        "pageLength": 20,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "responsive": true,
        "scrollX": true,
      });
      //window.location.reload(true);
    });
    document.getElementById('show').onchange = function() {
        window.location.href = this.children[this.selectedIndex].getAttribute('href');
    }
   

    function ViewDetails(count){
  	  var sTotal = $("#amount"+count).text();
  	  var returnId = $("#returnId"+count).text();
  	
  	 
  	  //alert(sTotal);
 	  
  		 $("#returnReceipt  tbody").empty();
  		 $("#tax").html($("#tax"+count).text());
  		// $("#totaltax").html($("#totaltax"+count).text());
  		 $("#total").html($("#amount"+count).text());

  		var rettotalValue = parseFloat($("#rettotal" + count).text()); 
  		var formattedValue = rettotalValue.toFixed(2); 
  		$("#balTot").html(formattedValue);
  	// $("#saleTotal").html($("#saleTotal"+count).text());
  		 $("#sRefno").html("Returns Reference No. " + $("#returnRefno"+count).text());
  		 $("#salereferenceno").html("Sales Reference No. " + $("#salereferenceno"+count).text());
  		
  		 $("#cName").html( $("#customer"+count).text());
 		 $("#date").html("Date :" + $("#quotesDate"+count).text());
  		 $("#purchaseCreatedby").html("Created By :" + $("#purchaseCreatedby"+count).text()+("<br>Date :" + $("#quotesDate"+count).text()));
  		 $("#customer").html("Bill To :" + $("#customer"+count).text());
  		$("#note").html("Note :" + $("#note"+count).text());
  		 
  		 
  		 
//  		 alert(rquoteId);
  		 $.ajax({
  				url : '${pageContext.request.contextPath}/getReturnitembyreturnId?returnId=' + returnId,
  				type : "GET",
  				dataType : "json",
  				success : function(data) {
  					var ajaxCallData = JSON.stringify(data);
  					
  					$.each(data, function(i, data) {
  						var unitname = data.unitname;
  						if(unitname == "Piece"){
							unitname = "Pc";
						  }
  						if(unitname == "Wire Roll 328 Ft"){
							unitname = "Roll 328'";
						  }
  						var rowCount = $('#returnReceipt tr').length ;
  						
  						var tr = $('<tr></tr>');
  						tr.append($('<td></td>').html(rowCount));
  						tr.append($('<td></td>').html(data.product_name));
  						tr.append($('<td></td>').html(data.real_unit_price.toFixed(2)));
  						tr.append($('<td></td>').html(unitname));
  						tr.append($('<td></td>').html(data.quantity));
  						tr.append($('<td style="text-align: right;"></td>').html(data.subtotal.toFixed(2)));
  						tr.append($('<tr></tr>').html());
  						$('#returnReceipt tbody').append(tr);
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



	
	function deleteDetails(count, returnid){
		var ctype = $("#ctype"+count).text();
		  var x = confirm("Are you sure you want to delete?");
	  if (x) {
		
		  $.ajax({
				url : '${pageContext.request.contextPath}/newDeleteReturn',
				type : "POST",
				dataType : "json",
				data:{id:returnid},
				success : function(data) {
		 			//alertify
					//  .alert(data.msgDescr, function(){
						 location.reload();
					//  }); 
					 
				},
				error : function(error) {
					console.log(`Error ${error}`);
				}

			});
	  }
	}
	function exportPDF()
	  {
	      var element = document.getElementById('printTable');
	      var opt = {
	          margin:       0.5,
	          filename:     'return'+<%=System.currentTimeMillis()%>+'.pdf',
	          image:        { type: 'jpeg', quality: 1 },
	          html2canvas:  { scale: 1 },
	          jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
	        };
	      html2pdf().set(opt).from(element).save();
	  }
	  	
	/* function printData()
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
} */
	
	function printData() {
	    var headerContent = document.getElementById("pdfContent").innerHTML;
	    var tbodyRows = document.getElementById("tableBody").getElementsByTagName("tr");
	    var theadContent = document.querySelector("#tableBody").previousElementSibling.outerHTML;
	    var tbodyToPrint = document.getElementById("tableBody").innerHTML;
	    var totalTableToPrint = document.getElementById("fullWidthTable").outerHTML;
	    var notesToPrint = document.getElementById("notes").innerHTML;
	    
	    console.log("Header Content:", headerContent);
	    console.log("Table Body Rows:", tbodyRows);
	    console.log("Table Head Content:", theadContent);
	    console.log("Table Body Content:", tbodyToPrint);
	    console.log("Total Table Content:", totalTableToPrint);
	    console.log("Notes Content:", notesToPrint);

	    
	    var columnCount = document.querySelector("#tableBody tr:first-child").children.length;
	    var secondColumnWidth = 28; // Keeping the width of the second column
	    var firstColumnWidth = secondColumnWidth / 3; // Calculating the width of the first column based on the second column width
	    var thirdColumnWidth = secondColumnWidth / 3.7;
	    var remainingColumnWidth = (100 - secondColumnWidth - firstColumnWidth) / (columnCount - 2); // Calculating the width for the remaining columns

	    var newWin = window.open("");
	    newWin.document.write('<html><head><title>Print</title>');
	    newWin.document.write('<style>@page { size: auto; margin: 0; }</style>'); // Adjust page margins as needed
	    newWin.document.write('<style>');
	    newWin.document.write('body {margin: 1mm; padding: 0; box-sizing: border-box;font-size: 10px; }'); // Reset body margins and padding
	    newWin.document.write('.content-wrapper { font-size: 10px; }'); // Set margin for the content wrapper
	    newWin.document.write('.well well-sm { font-size: 10px; }'); 
	    newWin.document.write('table { width: 100%; border-collapse: collapse; margin-bottom: 0;font-size: 12px; table-layout: fixed;}'); // Ensure table fits within its container with fixed layout
	    newWin.document.write('table, th, td { border: 1px solid black; padding: 1px; text-align: center; }');
	    newWin.document.write('th { background-color: #f2f2f2; }');
	    newWin.document.write('.hide-on-subsequent-pages { display: none; }'); // CSS to hide thead on subsequent pages
	    
	    // Set width for the columns
	    newWin.document.write('#printTable2 th:first-child, #printTable2 td:first-child, #printTable2 th:nth-child(3), #printTable2 td:nth-child(3), #printTable2 th:nth-child(4), #printTable2 td:nth-child(4), #printTable2 th:nth-child(5), #printTable2 td:nth-child(5) { width: ' + firstColumnWidth + '%; word-wrap: break-word; }'); // Set width for the 1st, 3rd, 4th, and 5th columns of the second table
	    newWin.document.write('#printTable2 th:nth-child(2), #printTable2 td:nth-child(2) { width: ' + secondColumnWidth + '%; word-wrap: break-word; }'); // Set width for the second column of the second table
	    newWin.document.write('#printTable2 th:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)), #printTable2 td:not(:first-child):not(:nth-child(2)):not(:nth-child(3)):not(:nth-child(4)):not(:nth-child(5)) { width: ' + remainingColumnWidth + '%; word-wrap: break-word; }'); // Set width for the remaining columns of the second table

	    newWin.document.write('</style>');
	    newWin.document.write('</head><body>');

	 // Write header content
	    newWin.document.write('<div class="well well-sm" style="font-size: 25px">');
	    newWin.document.write(headerContent);
	    newWin.document.write('</div>');


	    // Write the content wrapper div
	    newWin.document.write('<div class="content-wrapper">');

	    // Write table with thead for the first page
	    newWin.document.write('<table id="printTable2">');
	    newWin.document.write('<thead>');
	    newWin.document.write(theadContent);
	    newWin.document.write('</thead>');
	    newWin.document.write('<tbody>');
	    newWin.document.write(tbodyToPrint);
	    newWin.document.write('</tbody>');
	    newWin.document.write('</table>');

	    // Close the content wrapper div
	    newWin.document.write('</div>');

	    newWin.document.write('<div class="content-wrapper">');
	    // Write totalTable content
	    newWin.document.write(totalTableToPrint);
	    newWin.document.write('</div>');

	    // Write notes content
	    newWin.document.write('<div id="notes">');
	    newWin.document.write(notesToPrint);
	    newWin.document.write('</div>');

	    // Hide thead on subsequent pages
	    newWin.document.querySelectorAll('.thead-first-page th').forEach(function(th) {
	        th.classList.add('hide-on-subsequent-pages');
	    });

	    newWin.document.write('</body></html>');

	    // Print the document
	    setTimeout(function () {
	        newWin.document.close();
	        newWin.focus();
	        newWin.print();
	        newWin.close();
	    }, 350);
	}

</script>

</body>
</html>
