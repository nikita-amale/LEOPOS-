
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
	/*.btn {
    border-radius: 4px !important;
    padding: 0.375rem 0.5rem !important;
}*/
	.button__border{
		border: none;
		background: transparent;
	}
	button:focus {
    outline: transparent !important;
    outline: transparent !important;
}

.pagination-container {
    display: flex;
    justify-content: flex-end; /* move to right */
    align-items: center;
    gap: 12px;
    margin-top: 15px;
    font-size: 16px;
}

.page-btn {
    padding: 8px 16px;
    background-color: #007bff;
    color: white;
    border-radius: 6px;
    text-decoration: none;
    font-weight: 500;
    transition: 0.2s;
}

.page-btn:hover {
    background-color: #0056b3;
}

.page-info {
    font-weight: 600;
    font-size: 16px;
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
					<!-- 	<div class="card-header">
							<h3 class="card-title">Payment Report</h3>
							<input type="button" id="btnExport" class="btn btn-primary btn-font-size"
								onclick="fnExcelReport()" value="Export To Excel" style="float: right;">
						</div>  -->
						
						<div class="card-header d-flex justify-content-between align-items-center">

    <h3 class="card-title">Payment Report</h3>

							<form method="get" action="paymentReport"
								style="display: flex; gap: 10px; align-items: center; margin: 0;">

								<label style="margin: 0;">From:</label> <input type="date"
									name="fromDate" class="form-control form-control-sm"
									value="${fromDate}"> <label style="margin: 0;">To:</label>
								<input type="date" name="toDate"
									class="form-control form-control-sm" value="${toDate}">

								<button type="submit" class="btn btn-primary btn-sm">Filter</button>

								<a href="paymentReport" class="btn btn-secondary btn-sm">Reset</a> <a class="btn btn-success btn-sm"
									href="paymentReport?export=true&fromDate=${fromDate}&toDate=${toDate}">
									Export </a>

							</form>

						</div>
						
						

					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>Date</th>
									<th>Id</th>
									<th>BulkId</th>
									<th>Amount</th>
									<th>Memberid</th>
									<th>Membername</th>
									<th>Ptype</th>
									<th>View</th>
								
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="paymentPojo" items="${paymentPojo}"
									varStatus="loop">
									<c:set var = "grand_total" value = "${paymentPojo.grand_total}" />
										<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="Date${loop.count}">
    <fmt:formatDate value="${paymentPojo.paymentdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
</td>
										<td id="id${loop.count}">${paymentPojo.id}</td>
										<td id="bulkid${loop.count}">${paymentPojo.bulkid}</td>
										<td id="grand_total${loop.count}"><fmt:formatNumber pattern="0.00" value="${grand_total}" /></td>
										<td  id="memberId${loop.count}">${paymentPojo.member_id}</td>
										<td  id="customer${loop.count}">${paymentPojo.member_name}</td>
										<td  id="ctype${loop.count}">${paymentPojo.ptype}</td>
										<td>
											<button type="button" style="font-size: 14px;"
												class="btn btn-primary" data-toggle="modal"
												data-target="#viewpayment"
												onclick="ViewPayment(${loop.count})">View</button>
										</td>
										
										
									
								</c:forEach>
							</tbody>

						</table>


						<div class="pagination-container">

							<c:if test="${currentPage > 0}">
								<a class="page-btn"
									href="paymentReport?page=${currentPage - 1}&fromDate=${fromDate}&toDate=${toDate}">
									Previous </a>
							</c:if>

							<span class="page-info"> Page ${currentPage + 1} of
								${totalPages} </span>

							<c:if test="${currentPage < totalPages - 1}">
								<a class="page-btn"
									href="paymentReport?page=${currentPage + 1}&fromDate=${fromDate}&toDate=${toDate}">
									Next </a>
							</c:if>

						</div>
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


<!--View payment-->
<div class="modal fade" id="viewpayment" tabindex="-1"
aria-labelledby="viewpayment" aria-hidden="true">
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-body">
		<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">
		<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x-square-fill text-secondary" viewBox="0 0 16 16">
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
									<td align="left" id="customerName">
										</td>
								</tr>
								<tr>
								
									<td align="left" id="paymentamount">
										</td>
								</tr>
								<tr>
								<td align="left" id="date">
									</td>
							</tr>
						
							<!--<c:forEach var="paymentPojo" items="${paymentPojo}"
							varStatus="loop">
							<tr>
								<td align="left"></td>
							</tr>
							</c:forEach>-->
							
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
								<th>Invoice id</th>
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

			<a href="javascript:void(0);" onclick="printData()"
				class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm float-right"><i
				class="fa fa-print fa-sm text-white-50"></i> Print Invoice </a>
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
<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.umd.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>

<script type="text/javascript">

/*
var table;
 
    $(function () {
    	table =   $('#myTable').DataTable({      
        "paging": false,
        "pageLength": 20,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "scrollX": true,
      });
      //window.location.reload(true);
    });  */

   


    
    
    // View Normal Receipt
   function ViewPayment(count) {
	
	 var id = $("#id"+count).text();
	 var bulkid = $("#bulkid"+count).text();
	 var Date = $("#Date"+count).text();
	 
	 
	// alert(id);
	 //alert(Date);
	 $('#paymentreceipt tbody').empty();
	 
	 $("#customerName").html("Customer Name: " + $("#customer"+count).text());
	 $("#date").html("Date Recieved : " + $("#Date"+count).text());
	 $("#pAddress").html("Customer Address:"+ $("#caddress"+count).text());
	 $("#paymentamount").html("Payment Amount: $" + $("#grand_total"+count).text());
	
    if(bulkid==0){
    	//alert("bulkid is 0");
	 $.ajax({
			url : '${pageContext.request.contextPath}/getPaymentid?id='+id ,
			type : "GET",
			dataType : "json",
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			
					console.log("data==="+data);				
					//const paymentdate =(data.paymentdate);
					//const dt = new Date(paymentdate)					
					//alert(dt);
					var rowCount = $('#paymentreceipt tr').length ;
					
					var tr = $("<tr></tr>");

					tr.append($('<td></td>').html(rowCount));
					tr.append($('<td></td>').html(data.ptype));
					tr.append($('<td></td>').html(data.pref));
					tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
					tr.append($('<td></td>').html(data.referenceno));
					tr.append($('<td></td>').html(Date));
					tr.append($('<td></td>').html(data.note));
					tr.append($('<tr></tr>').html());
					
					$('#paymentreceipt tbody').append(tr);
			
				//$('#paymentreceipt tbody').empty();
				

			},
			
			error : function(error) {
				console.log(`Error ${error}`);
			}
		});
    }else{
    	if(bulkid!=0){
    		 $.ajax({
    				url : '${pageContext.request.contextPath}/getPaymentbulkid?bulkid='+bulkid ,
    				type : "GET",
    				dataType : "json",
    				success : function(data) {
    					var ajaxCallData = JSON.stringify(data);
    					$.each(data, function(i, data) {
    				
    						console.log("data==="+data);
    					
    						var rowCount = $('#paymentreceipt tr').length ;
    						
    						var tr = $("<tr></tr>");

    						tr.append($('<td></td>').html(rowCount));
    						tr.append($('<td></td>').html(data.ptype));
    						tr.append($('<td></td>').html(data.pref));
    						tr.append($('<td></td>').html(data.grand_total.toFixed(2)));
    						tr.append($('<td></td>').html(data.referenceno));
    						tr.append($('<td></td>').html(Date));
    						tr.append($('<td></td>').html(data.note));
    						tr.append($('<tr></tr>').html());
    						
    						$('#paymentreceipt tbody').append(tr);
    				
    					//$('#paymentreceipt tbody').empty();
    					
    					});
    				},
    				
    				error : function(error) {
    					console.log(`Error ${error}`);
    				}
    			});
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
   
   function exportPDF()
   {
       var element = document.getElementById('printTable');
       var opt = {
           margin:       0.5,
           filename:     'payment'+<%=System.currentTimeMillis()%>+'.pdf',
           image:        { type: 'jpeg', quality: 1 },
           html2canvas:  { scale: 1 },
           jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
         };
       html2pdf().set(opt).from(element).save();
   }
   function exportPDF1()
   {
       var element = document.getElementById('myTable');
       var opt = {
           margin:       0.5,
           filename:     'payment'+<%=System.currentTimeMillis()%>+'.pdf',
           image:        { type: 'jpeg', quality: 1 },
           html2canvas:  { scale: 1 },
           jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait',precision: '12' }
         };
       html2pdf().set(opt).from(element).save();
   }
   
   function fnExcelReport() {
		var htmls = "";
		var uri = 'data:application/vnd.ms-excel;base64,';
		var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><><body><table>{table}</table></body></html>';
		var base64 = function(s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		};

		var format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		};

		//htmls = document.getElementById('tablepaging').innerHTML;
		var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
		var textRange ;
		var j = 0;
		tab = document.getElementById('myTable');

		for (j = 0; j < tab.rows.length; j++) {
			tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
		}

		tab_text = tab_text + "</table>";
		tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
		tab_text = tab_text.replace(/<img[^>]*>/gi, "");
		tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");

		var ctx = {
			worksheet : 'Worksheet',
			table : tab_text
		}

		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();

		if (dd < 10) {
			dd = '0' + dd
		}

		if (mm < 10) {
			mm = '0' + mm
		}
		var link = document.createElement("a");
		document.body.appendChild(link);
		link.download = "Payment Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}
	
  
   $.fn.dataTable.ext.search.push(
		    function(settings, data, dataIndex) {

		        var fromDate = $('#fromDate').val();
		        var toDate = $('#toDate').val();

		        var tableDate = data[1]; // Date column (yyyy-MM-dd HH:mm:ss)

		        if (!fromDate && !toDate) {
		            return true;
		        }

		        // Extract only date part from table
		        var rowDate = tableDate.split(" ")[0]; // yyyy-MM-dd

		        if (
		            (!fromDate || rowDate >= fromDate) &&
		            (!toDate || rowDate <= toDate)
		        ) {
		            return true;
		        }

		        return false;
		    }
		);
   
   function filterByDate() {
	    table.draw();
	}

	function resetFilter() {
	    $('#fromDate').val('');
	    $('#toDate').val('');
	    table.draw();
	}

</script>

</body>
</html>
