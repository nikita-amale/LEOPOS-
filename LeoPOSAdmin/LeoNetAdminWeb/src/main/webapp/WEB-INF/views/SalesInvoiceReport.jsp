<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
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
							<h3 class="card-title">Sales Invoice Report</h3>
						</div>

						<form id="saleInvoiceForm" autocomplete="off" >
							<div class="form-group" style="display: flex">
								<div class="col-md-4">
									<label>From Invoice</label> <input type="text"
										class="form-control input-tip" name="start" id="start">
								</div>
								<div class="col-md-4">
									<label>To Invoice</label> <input type="text"
										class="form-control input-tip" name="end" id="end">
								</div>
								<div class="col-md-4">
									<label>Filter by Date</label> <input type="date"
										class="form-control input-tip" name="filterDate"
										id="filterDate">
								</div>
							</div>
							<input type="button" class="btn btn-primary" value="Submit"
								onclick="submitForm()" /> 
							<input type="button"
								class="btn btn-primary" value="Export To Excel"
								onclick="exportToExcel()" /> 
							<input type="button"
								class="btn btn-primary" value="Export To PDF"
								onclick="exportToPdf()" />
						</form>

						<div id = "exportArea" class="card-body" style="font-size: 15px;">
						
						 <!-- Heading for first report -->
    <div style="text-align:center; margin-bottom:20px; font-size:16px; line-height:1.4;">
        <div><strong>R.C IMPORTS</strong></div>
        <div>5 AMARA AVENUE</div>
        <div>BELIZE</div>
        <br>
        <div style="font-size:18px; font-weight:bold;">SALES LISTING REPORT</div>
        <div><em>* Not Posted</em></div>
    </div>
							<table id="saleInvoicetable" class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>
									<tr>
										<th>Invoice #</th>
										<th>Reference No</th>
										<th>Cust/Lay Name</th>
										<th>Date</th>
										<th>Gross</th>
										<th>Tax</th>
										<th>Total</th>
										<th>Amount Pd</th>
										<th>Net To Pay</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
							
							
    <!-- Heading for second report -->
    <div style="text-align:center; margin:40px 0 20px; font-size:16px; line-height:1.4;">
        <div><strong>R.C IMPORTS</strong></div>
        <div>5 AMARA AVENUE</div>
        <div>BELIZE</div>
        <br>
        <div style="font-size:18px; font-weight:bold;">SALES LISTING REPORT</div>
        <div>SUMMARY BY INVENTORY AND CODE</div>
        <div><em>* Not Posted</em></div>
    </div>
							
							
							<table id="saleItemsTable" class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="margin-top: 50px;border-collapse: collapse !important;">
								<thead>
									<tr>
										<th>Invoice #</th>
										<th>Dept.</th>
										<th>Type</th>
										<th>Item/Acct #</th>
										<th>Description</th>
										<th>Quantity</th>
										<th>Amount</th>										
										<th>Avg./Unit</th>
										<th>Sale Price</th>
										<th>%Variance</th>
									</tr>
								</thead>
								<tbody>
									
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>

<script type="text/javascript">
$(function() {
    // Initialize DataTables
    $('#saleInvoicetable, #saleItemsTable').DataTable({
        paging: false,
        searching: true,
        ordering: true,
        info: false,
        autoWidth: false,
        responsive: true,
        scrollX: true,
        stateSave: false
    });

    // Date picker
    document.getElementById('filterDate').addEventListener('click', function() {
        this.showPicker();
    });
});

// Validate filters
function validateFilters() {
    const start = $('#start').val();
    const end = $('#end').val();
    const filterDate = $('#filterDate').val();

    if (!filterDate && (!start || !end)) {
        alert("Please enter either (From & To Invoice) or (Filter Date).");
        return false;
    }
    if ((start && !end) || (!start && end)) {
        alert("Both From Invoice and To Invoice must be entered together.");
        return false;
    }
    return true;
}

// Submit form via AJAX
function submitForm() {
    if (!validateFilters()) return;

    $.ajax({
        url: 'saleInvoiceReport',
        method: 'POST',
        data: $("#saleInvoiceForm").serialize(),
        success: function(reports) {
            let invoiceTable = $('#saleInvoicetable').DataTable();
            let itemsTable = $('#saleItemsTable').DataTable();

            // Clear old data
            invoiceTable.clear();
            itemsTable.clear();

            // Populate tables dynamically
            reports.forEach(report => {
                invoiceTable.row.add([
                    report.invoiceNumber,
                    report.code,
                    report.customerName,
                    report.date,
                    report.gross,
                    report.tax,
                    report.total,
                    report.amountPaid,
                    report.netPay
                ]);

                report.items.forEach(item => {
                    itemsTable.row.add([
                        report.invoiceNumber,
                        item.department,
                        item.type,
                        item.itemCode,
                        item.description,
                        item.quantity,
                        item.amount,
                        item.average,
                        item.salePrice,
                        item.variance
                    ]);
                });
            });

            // Redraw tables
            invoiceTable.draw();
            itemsTable.draw();
        },
        error: function() {
            alert('Failed to load report.');
        }
    });
}

// Export to Excel
function exportToExcelOld() {
    var uri = 'data:application/vnd.ms-excel;base64,';
    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" ' +
                   'xmlns:x="urn:schemas-microsoft-com:office:excel" ' +
                   'xmlns="http://www.w3.org/TR/REC-html40">' +
                   '<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets>' +
                   '<x:ExcelWorksheet><x:Name>{worksheet}</x:Name>' +
                   '<x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions>' +
                   '</x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->' +
                   '</head><body>{table}</body></html>';

    var base64 = function(s) {
        return window.btoa(unescape(encodeURIComponent(s)));
    };

    var format = function(s, c) {
        return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; });
    };

    var tables = ['saleInvoicetable', 'saleItemsTable'];
    var tab_text = "";

    tables.forEach(function(id) {
        var table = $('#' + id).DataTable();
        if (!table) return;

        tab_text += "<table border='1'>";

        // Add headers
        var headers = $('#' + id + ' thead tr th');
        var headerHtml = "";
        headers.each(function() {
            headerHtml += '<td style="background:#87AFC6;font-weight:bold;">' + $(this).text() + '</td>';
        });
        tab_text += '<tr>' + headerHtml + '</tr>';

        // Add body rows
        var data = table.rows({ search: 'applied' }).data(); // visible rows
        data.each(function(row) {
            tab_text += '<tr>';
            for (var i = 0; i < row.length; i++) {
                tab_text += '<td>' + row[i] + '</td>';
            }
            tab_text += '</tr>';
        });

        tab_text += "</table><br><br>";
    });

    var ctx = { worksheet: 'Worksheet', table: tab_text };

    var today = new Date();
    var dd = today.getDate().toString().padStart(2, '0');
    var mm = (today.getMonth() + 1).toString().padStart(2, '0');
    var yyyy = today.getFullYear();

    var link = document.createElement("a");
    document.body.appendChild(link);
    link.download = "SalesInvoiceReport_" + yyyy + "-" + mm + "-" + dd + ".xls";
    link.href = uri + base64(format(template, ctx));
    link.click();
    document.body.removeChild(link);
}

//Export to Excel
function exportToExcel() {
    var uri = 'data:application/vnd.ms-excel;base64,';
    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" ' +
                   'xmlns:x="urn:schemas-microsoft-com:office:excel" ' +
                   'xmlns="http://www.w3.org/TR/REC-html40">' +
                   '<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets>' +
                   '<x:ExcelWorksheet><x:Name>{worksheet}</x:Name>' +
                   '<x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions>' +
                   '</x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->' +
                   '</head><body>{table}</body></html>';

    var base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))); };
    var format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }); };

    // 🔹 Add headings manually
    var heading1 = "<div style='text-align:center;font-weight:bold;font-size:16px;'>R.C IMPORTS<br>5 AMARA AVENUE<br>BELIZE<br><br>" +
                   "SALES LISTING REPORT<br><em>* Not Posted</em></div><br>";

    var heading2 = "<div style='text-align:center;font-weight:bold;font-size:16px;'>R.C IMPORTS<br>5 AMARA AVENUE<br>BELIZE<br><br>" +
                   "SALES LISTING REPORT<br>SUMMARY BY INVENTORY AND CODE<br><em>* Not Posted</em></div><br>";

    var tables = ['saleInvoicetable', 'saleItemsTable'];
    var tab_text = "";

    tables.forEach(function(id, index) {
        if (index === 0) tab_text += heading1; // before first table
        if (index === 1) tab_text += heading2; // before second table

        var table = $('#' + id).DataTable();
        if (!table) return;

        tab_text += "<table border='1'>";
        var headers = $('#' + id + ' thead tr th');
        var headerHtml = "";
        headers.each(function() {
            headerHtml += '<td style="background:#87AFC6;font-weight:bold;">' + $(this).text() + '</td>';
        });
        tab_text += '<tr>' + headerHtml + '</tr>';

        var data = table.rows({ search: 'applied' }).data();
        data.each(function(row) {
            tab_text += '<tr>';
            for (var i = 0; i < row.length; i++) {
                tab_text += '<td>' + row[i] + '</td>';
            }
            tab_text += '</tr>';
        });

        tab_text += "</table><br><br>";
    });

    var ctx = { worksheet: 'Worksheet', table: tab_text };

    var today = new Date();
    var dd = today.getDate().toString().padStart(2, '0');
    var mm = (today.getMonth() + 1).toString().padStart(2, '0');
    var yyyy = today.getFullYear();

    var link = document.createElement("a");
    document.body.appendChild(link);
    link.download = "SalesInvoiceReport_" + yyyy + "-" + mm + "-" + dd + ".xls";
    link.href = uri + base64(format(template, ctx));
    link.click();
    document.body.removeChild(link);
}



// Export to PDF
async function exportToPdfOld() {
    const { jsPDF } = window.jspdf;
    let tables = [document.getElementById("saleInvoicetable"), document.getElementById("saleItemsTable")];

    const pdf = new jsPDF("p", "pt", "a4");
    const pdfWidth = pdf.internal.pageSize.getWidth();
    let y = 10;

    for (let table of tables) {
        if (!table) continue;

        // Clone table
        const clone = table.cloneNode(true);
        clone.style.display = "table";
        clone.style.position = "absolute";
        clone.style.left = "-9999px";
        clone.style.width = "100%";

        // Fix DataTables hidden header
        const origHeader = table.closest('.dataTables_wrapper')?.querySelector('.dataTables_scrollHead thead');
        if (origHeader) {
            const cloneThead = clone.querySelector('thead');
            if (cloneThead) {
                cloneThead.innerHTML = origHeader.innerHTML; // copy visible header
                cloneThead.style.display = 'table-header-group';
            }
        }

        document.body.appendChild(clone);

        const canvas = await html2canvas(clone, {
            scale: 2,
            useCORS: true,
            allowTaint: true
        });

        const imgData = canvas.toDataURL("image/png");
        const imgHeight = (canvas.height * pdfWidth) / canvas.width;

        if (y + imgHeight > pdf.internal.pageSize.getHeight()) {
            pdf.addPage();
            y = 10;
        }

        pdf.addImage(imgData, "PNG", 0, y, pdfWidth, imgHeight);
        y += imgHeight + 10;

        document.body.removeChild(clone);
    }

    pdf.save("SalesInvoiceReport.pdf");
}

//Export to PDF
async function exportToPdf() {
    const { jsPDF } = window.jspdf;
    const pdf = new jsPDF("p", "pt", "a4");
    const pdfWidth = pdf.internal.pageSize.getWidth();

    // 🔹 Capture the whole exportArea (headings + tables)
    const exportArea = document.getElementById("exportArea");
    const canvas = await html2canvas(exportArea, {
        scale: 2,
        useCORS: true,
        allowTaint: true
    });

    const imgData = canvas.toDataURL("image/png");
    const imgHeight = (canvas.height * pdfWidth) / canvas.width;

    pdf.addImage(imgData, "PNG", 0, 10, pdfWidth, imgHeight);
    pdf.save("SalesInvoiceReport.pdf");
}



</script>









</body>
</html>