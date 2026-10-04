<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.28/jspdf.plugin.autotable.min.js"></script>

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
							<h3 class="card-title">Customer Report</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm" action="customerReport">
							<div class="card-body">
								<input type="button" id="btnExport" class="btn btn-primary btn-font-size"
								onclick="fnExcelReport()" value="Export To Excel">

    <input type="button" id="btnExportPDF" class="btn btn-primary btn-font-size" 
        onclick="fnPDFReport()" value="Export To PDF" style="margin-left: 10px;">

							</div>
							</form>
							
							<div class="card-body">
							<table id="purchasetable"
								class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>

									<tr>
										<th>User_id</th>
										<th>Name</th>
										<th>Email</th>
										<th>Phone</th>
										<th>Total_Sale</th>
										<th>Total_Amount</th>
										<th>Total_paid</th>
										<th>Balance</th>
										<th hidden>ctype</th>
										<th>View</th>
										
										
									</tr>

								</thead>

								<tbody>
									<c:forEach var="customerReport"
										items="${customerReport}" varStatus="loop">
										  <c:set var = "balance" value = "${customerReport.balance}" />
										   <c:set var = "totalPaid" value = "${customerReport.totalPaid}" />
										   <c:set var = "totalAmount" value = "${customerReport.totalAmount}" />
										   <c:set var = "totalSale" value = "${customerReport.totalSale}" />
										<tr id="${loop.count}">
											<td id="id${loop.count}">${customerReport.id}</td>
											<td id="name${loop.count}">${customerReport.name}</td>
											<td id="emaily${loop.count}">${customerReport.email}</td>
											<td id="phoneno${loop.count}">${customerReport.phoneno}</td>
											<td id="totalSale${loop.count}"><fmt:formatNumber pattern="0.00" value="${totalSale}" /></td>
											<td id="totalAmount${loop.count}"><fmt:formatNumber pattern="0.00" value="${totalAmount}" /></td>
											<td id="totalPaid${loop.count}"><fmt:formatNumber pattern="0.00" value="${totalPaid}" /></td>
										   <td id="balance${loop.count}"><fmt:formatNumber pattern="0.00" value="${balance}" /></td>
										   <td hidden id="ctype${loop.count}">${customerReport.ctype}</td>
										   <td> <a type="button" style="font-size: 12px;"
												class="btn btn-primary" href="${pageContext.request.contextPath}/getcustomerbymemberId?MemberId=${customerReport.id}&Ctype=${customerReport.ctype}">Customer Report</a></td>
										     
										   
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
      "paging": false,
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
	
	function fnExcelReportt() {
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
		link.download = "Customer Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}
	
	
	function fnExcelReport() {
	    var uri = 'data:application/vnd.ms-excel;base64,';
	    var template = `
	        <html xmlns:o="urn:schemas-microsoft-com:office:office"
	              xmlns:x="urn:schemas-microsoft-com:office:excel"
	              xmlns="http://www.w3.org/TR/REC-html40">
	        <head>
	            <!--[if gte mso 9]>
	            <xml>
	                <x:ExcelWorkbook>
	                    <x:ExcelWorksheets>
	                        <x:ExcelWorksheet>
	                            <x:Name>{worksheet}</x:Name>
	                            <x:WorksheetOptions>
	                                <x:DisplayGridlines/>
	                            </x:WorksheetOptions>
	                        </x:ExcelWorksheet>
	                    </x:ExcelWorksheets>
	                </x:ExcelWorkbook>
	            </xml>
	            <![endif]-->
	        </head>
	        <body>
	            {table}
	        </body>
	        </html>`;

	    var base64 = function (s) {
	        return window.btoa(unescape(encodeURIComponent(s)));
	    };

	    var format = function (s, c) {
	        return s.replace(/{(\w+)}/g, function (m, p) {
	            return c[p];
	        });
	    };

	    var table = document.getElementById("purchasetable");
	    var tab_text = "<table border='1'><thead><tr>";

	    // Add headers (use <th>)
	    var headerRow = table.rows[0];
	    for (var i = 0; i < headerRow.cells.length; i++) {
	        if (i === 9 || headerRow.cells[i].hidden) continue; // skip View column and hidden
	        tab_text += "<th>" + headerRow.cells[i].textContent.trim() + "</th>";
	    }
	    tab_text += "</tr></thead><tbody>";

	    // Add data rows
	    for (var r = 1; r < table.rows.length; r++) {
	        var row = table.rows[r];
	        if (row.style.display === "none") continue; // skip hidden rows

	        tab_text += "<tr>";
	        for (var c = 0; c < row.cells.length; c++) {
	            if (c === 9 || row.cells[c].hidden) continue;
	            tab_text += "<td>" + row.cells[c].textContent.trim() + "</td>";
	        }
	        tab_text += "</tr>";
	    }

	    tab_text += "</tbody></table>";

	    var ctx = {
	        worksheet: 'Customer_Report',
	        table: tab_text
	    };

	    // Filename with current date
	    var today = new Date();
	    var dd = String(today.getDate()).padStart(2, '0');
	    var mm = String(today.getMonth() + 1).padStart(2, '0');
	    var yyyy = today.getFullYear();
	    var filename = `Customer_Report_${yyyy}-${mm}-${dd}.xls`;

	    // Create link and trigger download
	    var link = document.createElement("a");
	    link.download = filename;
	    link.href = uri + base64(format(template, ctx));
	    document.body.appendChild(link);
	    link.click();
	    document.body.removeChild(link);
	}
	
	function fnPDFReportt() {
	    fetch('/download-customer-pdf')
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('Network response was not ok');
	            }
	            return response.blob();
	        })
	        .then(blob => {
	            // Create download link
	            const url = window.URL.createObjectURL(blob);
	            const a = document.createElement('a');
	            a.href = url;
	            a.download = 'customer_report.pdf';
	            document.body.appendChild(a);
	            a.click();
	            window.URL.revokeObjectURL(url);
	            document.body.removeChild(a);
	        })
	        .catch(error => {
	            console.error('Error downloading PDF:', error);
	            alert('Failed to download report');
	        });
	}
	
	function fnPDFReport() {
	    const { jsPDF } = window.jspdf;
	    const doc = new jsPDF('landscape');

	    const table = document.getElementById("purchasetable");
	    const headers = [];
	    const data = [];

	    const headerCells = table.querySelectorAll("thead tr th");
	    headerCells.forEach((th, index) => {
	        // Skip hidden and last column (View)
	        if (!th.hasAttribute('hidden') && index !== headerCells.length - 1) {
	            headers.push(th.textContent.trim());
	        }
	    });

	    const rows = table.querySelectorAll("tbody tr");
	    rows.forEach(row => {
	        const rowData = [];
	        const cells = row.querySelectorAll("td");
	        cells.forEach((cell, index) => {
	            // Skip last column (View) and match visible headers only
	            const th = headerCells[index];
	            if (th && !th.hasAttribute('hidden') && index !== headerCells.length - 1) {
	                rowData.push(cell.textContent.trim());
	            }
	        });
	        data.push(rowData);
	    });

	    const today = new Date().toISOString().split('T')[0];
	    doc.text("Customer Report - " + today, 14, 20);

	    doc.autoTable({
	        head: [headers],
	        body: data,
	        startY: 30,
	        theme: 'grid',
	        styles: { fontSize: 8 }
	    });

	    doc.save("Customer_Report_" + today + ".pdf");
	}


	// Call this function on button click
	document.getElementById('downloadBtn').addEventListener('click', downloadCustomerReport);
	
	

	
</script>


</body>
</html>