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
							<h3 class="card-title">Profit Loss Report</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm" action="salesReport">
							<div class="card-body">
								

								
								<input type="button" id="btnExport" class="btn btn-primary btn-font-size"
								onclick="fnExcelReport()" value="Export To Excel">

							</div>
							</form>
							
								<div class="form-group">
											<div class="form-group" style="display: flex">
												<div class="col-md-6">
													<label>Start Date</label><input type="date"
														class="form-control input-tip" name="startDate"
														id="startDate">
												</div>
												<div class="col-md-6">
													<label>End Date</label><input type="date"
														class="form-control input-tip" name="endDate" id="endDate">
												</div>
											</div>


											<button type="submit" id="submitbtn" onclick="myFunction()"
												class="btn btn-primary btn-font-size">Submit</button>
											<button class="btn btn-primary btn-font-size"
												onclick="reset()">Reset</button>

											<table id="myTable" class="table table-bordered table-hover">
												<thead>

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
  
function myFunction() {
	
	var flag =0;
	
	var sdate=  document.getElementById("startDate").value ;
	var edate=  document.getElementById("endDate").value ;
	 
	 if (sdate == "" || edate == "" )
		{
		  alert("Start or End dates must be Selected !");
		  flag =1;
		}
	
	if(flag == 0){
		newWin= window.open("${pageContext.request.contextPath}/ProfitLossReport?startDate="+sdate+"&endDate=" + edate);
	}
	
	//window.location.href="${pageContext.request.contextPath}/statementReport?MemberId=${memberPojo.id}&startDate="+sdate+"&endDate=" + edate;
	 
}
	
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
		link.download = "ProfitLoss Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}
	
	
	
	

	
</script>


</body>
</html>