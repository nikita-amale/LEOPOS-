<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  
  
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
							<h3 class="card-title">Sales Report</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm" action="salesReport">
							<div class="card-body">
								<div class="form-group" style="display: flex">
									<div class="col-md-6">
										<label>Start Date</label><input
											type="date" class="form-control input-tip" name="startDate"
											id="startDate">
									</div>
									<div class="col-md-6">
										<label>End Date</label><input
											type="date" class="form-control input-tip" name="endDate"
											id="endDate">
									</div>
								</div>

								<button type="submit" id="submitbtn"
									class="btn btn-primary btn-font-size">Submit</button>
								<button class="btn btn-primary btn-font-size" onclick="reset()">Reset</button>
								<input type="button" id="btnExport" class="btn btn-primary btn-font-size"
								onclick="fnExcelReport()" value="Export To Excel">

							</div>
							</form>
							
							<div class="card-body">
							<table id="purchasetable"
								class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>

									<tr>
										<th>Product Id</th>
										<th  >Product Name</th>
										<th>QTY</th>
										<th hidden>MemeberName</th>
										<th hidden>saleid</th>
										<th hidden>refernceno</th>
										<th >View</th>
									</tr>

								</thead>

								<tbody>
									<c:forEach var="salesReport"
										items="${salesReport}" varStatus="loop">
										<tr id="${loop.count}">
											<td id="productId${loop.count}">${salesReport.productId}</td>
											<td   id="productName${loop.count}" >${salesReport.productName}</td>
											<td id="quantity${loop.count}">${salesReport.quantity}</td>
											<td hidden id="membername${loop.count}">${salesReport.membername}</td>
											<td hidden id="saleId${loop.count}">${salesReport.saleId}</td>
											<td hidden id="referenceno${loop.count}">${salesReport.referenceno}</td>
											
											<td >
											 <a type="button" style="font-size: 12px;"
														class="btn btn-primary"
														href="${pageContext.request.contextPath}/getInvoiceId?id=${salesReport.saleId}&referenceno=${salesReport.referenceno}&productid=${salesReport.productId}">View</a>
														
														</button>
														</td>
										</tr>	
									</c:forEach>
								</tbody>
							</table>
						</div>
						


					</div>
				</div>
			</div>
		</div>

	</section>
</div>

<div class="modal fade" id="viewcustomer" tabindex="-1"
	aria-labelledby="viewcustomer" aria-hidden="true">
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

				

					<br>

					<div class="row"
						style="margin-bottom: 15px; justify-content: space-between;">
						
						<div class="col">
							<table width="100%" style="font-size: 14px;" id="viewPaymentTbl">



								<tr>
									<td align="left" id="customerName"></td>
								</tr>
						

							</table>
						</div>

						


					</div>

					<p style="margin-bottom: 0 !important; font-size: 14px;"></p>

		



				</div>
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
src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js">

$(function () {
    $('#purchasetable').DataTable({      
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
    //window.location.reload();
  });
	
document.getElementById('startDate').addEventListener('click', function() {
    this.showPicker();
});

document.getElementById('endDate').addEventListener('click', function() {
    this.showPicker();
});

	function reset(){
		window.location.reload(true);
		
	}
	function ViewCustomer(count) {
		 var membername = $("#membername" + count).text();
		    var saleId = $("#saleId" + count).text(); // Get the sale id
		    var memberNames = membername.split(','); // Assuming member names are separated by commas
		    var formattedNames = memberNames.join('<br>');
		    $("#customerName").html("<strong>Customer Name:</strong><br>" + formattedNames);
		    $("#saleId").html("<strong>Sale ID:</strong> " + saleId); // Display sale id
	}
		

	
	function searchTable() {
	    var input, filter, found, table, tr, td, i, j;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    table = document.getElementById("purchasetable");
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
		tab = document.getElementById('purchasetable');

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
		link.download = "Sales Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}
	
	
	
	

	
</script>


</body>
</html>