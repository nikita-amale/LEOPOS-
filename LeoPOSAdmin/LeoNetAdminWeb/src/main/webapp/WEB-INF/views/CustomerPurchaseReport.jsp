<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<h3 class="card-title">Customer Purchase Report</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm"
							action="customerPurchaseReport">
							<div class="card-body">
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

								<button type="submit" id="submitbtn"
									class="btn btn-primary btn-font-size">Submit</button>
								<button class="btn btn-primary btn-font-size" onclick="reset()">Reset</button>
								<input type="button" id="btnExport"
									class="btn btn-primary btn-font-size" onclick="fnExcelReport()"
									value="Export To Excel">

							</div>
						</form>

						<div class="card-body">
							<table id="purchasetable"
								class="table table-bordered table-hover table-striped print-table order-table"
								width="100%" border="1"
								style="border-collapse: collapse !important;">
								<thead>

									<tr>
										<th >SaleId</th>
										
										<th>Member Name</th>
										<th>Grand_total</th>
										
										<th>View</th>
									</tr>

								</thead>

								<tbody>
									<c:forEach var="customerPurchaseReport"
										items="${customerPurchaseReport}" varStatus="loop">
										<c:set var="grandTotal"
										value="${customerPurchaseReport.grandTotal}" />
										<tr id="${loop.count}">
											<td   id="saleId${loop.count}">${customerPurchaseReport.saleId}</td>
											
											<td id="member_name${loop.count}">${customerPurchaseReport.member_name}</td>
											<td id="grand_total${loop.count}"><fmt:formatNumber
												pattern="0.00" value="${grandTotal}" /></td>
											
											<td>
												   <a type="button" style="font-size: 12px;"
														class="btn btn-primary"
														href="${pageContext.request.contextPath}/getSaleID?id=${customerPurchaseReport.saleId}">View</a>
														 <!--	<a type="button" style="font-size: 12px;"
														class="btn btn-primary"
														href="${pageContext.request.contextPath}/getSaleidmember?memberid=${customerPurchaseReport.memberid}">View</a>-->
														
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

					<table id="viewpurchasetable"
						class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1"
						style="border-collapse: collapse !important;">
						<thead>
						<tr>
							<th>SaleId</th>
							<th>View</th>
						</tr>
						</thead>
						<tbody>

						<tr>
						
							<td id="saleid"></td>
							
							<td>

								<button
									style="font-size: 12px; padding: 0.375rem 0.5rem !important;"
									type="button" class="btn btn-primary" data-toggle="modal"
									onclick="ViewDetails()" data-target="#modal">View</button>
							</td>
						</tr>
						</tbody>
						


					</table>

				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>




<div class="modal fade" id="modal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="fa fa-close"
						style="font-size: 16px; color: #1eb53a !important;">close</i>
				</button>
				<div class="form-group">

					<table id="salesReceipt"
						class="table table-bordered table-hover table-striped print-table order-table"
						width="100%" border="1"
						style="border-collapse: collapse !important; margin-bottom: 0rem;">

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

						<tbody style="text-align: center !important">

						</tbody>
					</table>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
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
		link.download = "CustomerPurchase Report" + yyyy + "-" + mm + "-" + dd
				+ ".xls";
		link.href = uri + base64(format(template, ctx));
		link.click();
	}
	
	function View(count){
		//alert("Reached");
		 var SaleId = $("#saleId"+count).text();
		 // $("#saleid").val(SaleId);
		  $("#saleid").html($("#saleId"+count).text());
	}
	function ViewDetails(){

		 var SaleId = $("#saleid").text();
		 alert(SaleId)
	 $("#salesReceipt  tbody").empty();
		 $.ajax({
				url : '${pageContext.request.contextPath}/getSaleitembysaleId?saleId=' + SaleId ,
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
						var rowCount = $('#salesReceipt tr').length;
						var tr = $("<tr></tr>");

						tr.append($('<td></td>').html(rowCount));
						tr.append($('<td></td>').html(data.product_name));
						tr.append($('<td></td>').html(data.quantity));
						tr.append($('<td></td>').html(real_unit_price));
						tr.append($('<td></td>').html(unitname));
						tr.append($('<td style="text-align: right;"></td>').html(subtotal));
						tr.append($('<tr></tr>').html());
						
						$('#salesReceipt tbody').append(tr);
					});
					
					//Calculate(paid,tax);

				},
				error : function(error) {
					console.log(`Error ${error}`);
				}
			});
		
	}
	
	
	

	
</script>


</body>
</html>