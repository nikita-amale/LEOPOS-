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
							<h3 class="card-title">Replenishment Report</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm" action="replenishmentReport">
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
										<th>Product Name</th>
										<th>MPN</th>
										<th>SOLD_QTY</th>
										<th>AVAILABLE_QTY</th>
										<th>QTY_TO_ORDER</th>
									</tr>

								</thead>

								<tbody>
									<c:forEach var="replenishmentReport"
										items="${replenishmentReport}" varStatus="loop">
											<c:set var="qtyorder"
										value="${replenishmentReport.soldquantity-replenishmentReport.availableqty}" />
										<tr id="${loop.count}">
											<td id="productId${loop.count}">${replenishmentReport.productId}</td>
											<td id="productName${loop.count}">${replenishmentReport.productName}</td>
											<td id="cf1${loop.count}">${replenishmentReport.cf1}</td>
											<td id="soldquantity${loop.count}">${replenishmentReport.soldquantity}</td>
											<td id="availableqty${loop.count}">${replenishmentReport.availableqty}</td>
											<td id="qtyorder${loop.count}">${qtyorder}</td>
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
	    var uri = 'data:application/vnd.ms-excel;base64,';
	    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><><body><table>{table}</table></body></html>';
	    var base64 = function (s) {
	        return window.btoa(unescape(encodeURIComponent(s)));
	    };

	    var format = function (s, c) {
	        return s.replace(/{(\w+)}/g, function (m, p) {
	            return c[p];
	        });
	    };

	    var tab_text = "<table border='2px'><thead><tr bgcolor='#87AFC6'>";

	    // Get DataTable instance
	    var table = $('#purchasetable').DataTable();

	    // Add column headings to tab_text
	    table.columns().every(function () {
	        var title = this.header().textContent.trim();
	        tab_text += "<th>" + title + "</th>";
	    });

	    tab_text += "</tr></thead><tbody>";

	    // Set page length to display all rows on a single page
	    table.page.len(-1).draw();

	    // Iterate through each row in DataTable
	    table.rows().every(function () {
	        var data = this.data();
	        tab_text += "<tr>";
	        for (var j = 0; j < data.length; j++) {
	            tab_text += "<td>" + data[j] + "</td>";
	        }
	        tab_text += "</tr>";
	    });

	    // Retrieve all rows from other tables
	    var allRows = getAllTableRows();
	    console.log("allRows==="+allRows.length);
	  

	    // Iterate through all rows (excluding DataTable rows)
	    
	    

	    tab_text += "</tbody></table>";
	    tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");
	    tab_text = tab_text.replace(/<img[^>]*>/gi, "");
	    tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, "");

	    var ctx = {
	        worksheet: 'Worksheet',
	        table: tab_text
	    };

	    // Restore original page length and redraw
	    table.page.len(20).draw();

	    var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth() + 1;
	    var yyyy = today.getFullYear();

	    if (dd < 10) {
	        dd = '0' + dd;
	    }

	    if (mm < 10) {
	        mm = '0' + mm;
	    }

	    var link = document.createElement("a");
	    document.body.appendChild(link);
	    link.download = "Quantity to Order" + yyyy + "-" + mm + "-" + dd + ".xls";
	    link.href = uri + base64(format(template, ctx));
	    link.click();
	}

	function getAllTableRows() {
	    var allRows = [];
	    var tables = document.getElementsByTagName('table');
	    for (var i = 0; i < tables.length; i++) {
	        var rows = tables[i].getElementsByTagName('tr');
	        for (var j = 0; j < rows.length; j++) {
	            allRows.push(rows[j]);
	        }
	    }
	    return allRows;
	}






	
	
	
	

	
</script>


</body>
</html>