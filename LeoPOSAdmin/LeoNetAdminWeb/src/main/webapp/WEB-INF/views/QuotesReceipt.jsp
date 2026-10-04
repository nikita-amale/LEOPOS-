
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" /> 
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.row{
		margin-left: 0;
		margin-right:0;
	}
	.table-pad{
		padding: 0 1rem;
	}
	.navbar{
		display: none;
	}
</style>
<!-- Content Wrapper. Contains page content -->
<div id="printTable">

	<div class="well well-sm">

		<div class="clearfix"></div>
	</div>

	<div class="row"
		style="margin-bottom: 15px; justify-content: space-between; padding: 0rem 1rem;">

		<div class="col">
			<table width="100%" style="font-size: 14px;">
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/resources/images/logo.png"
						width="300px" /></td>
					<td></td>
				</tr>
				<tr>
					<td>3754 Central American Blvd.</td>
					<td align="right">Tax Invoice</td>
				</tr>
				<tr>
					<td>Belize City Belize</td>
					<td align="right" id="date">Date :${quotesPojo.date}</td>
				</tr>
				<tr>
					<td>Tel: 501 207-0669</td>
					<td align="right" id="sRefno">${quotesPojo.referenceno}</td>
				</tr>
				<tr>
					<td>TIN # 128693</td>
					<td align="right">Sales Person: SalesUser Sales</td>
				</tr>
			</table>
		</div>
	<input type="hidden" class="form-control" name="ctype" id="ctype" value="${quotesPojo.ctype}">
	<input type="hidden" class="form-control" name="saleid" id="saleid" value="${quotesPojo.quotesId}">
	<input type="hidden" class="form-control" name="memberid" id="memberid" value="${quotesPojo.memberid}">
	
	

	</div>

	<br>

	<p class="table-pad" style="margin-bottom: 0 !important; font-size: 14px;">Bill
		To:${quotesPojo.member_name}</p>
	<p class="table-pad" style="margin-bottom: 0; font-size: 14px;">
		<strong></strong>
	</p>
	<p class="table-pad" style="margin-bottom: 0; font-size: 14px;">Sales Receipt</p>

	<br>

	<div class="table-responsive table-pad" style="font-size: 14px !important;">
		<table id="salesReceipt"
			class="table table-bordered table-hover table-striped print-table order-table text-center"
			width="100%" border="1"
			style="border-collapse: collapse !important;">

			<thead class="text-center">

				<tr>
					<th align="center">S.No.</th>
					<th align="center">Product Name</th>
					<th align="center">Unit Price</th>
					<th align="center">Unit of Measure</th>
					<th align="center">Quantity</th>
					<th align="center">Subtotal</th>
					
				</tr>

			</thead>
			<tbody align="center">
				<c:forEach var="quotesItem" items="${quotesItemList}" varStatus="loop">
					<tr id="${loop.count}">
						<td id="productId${loop.count}">${loop.count}</td>
						<td id="productName${loop.count}">${quotesItem.product_name}</td>
						<td id="unitPrice${loop.count}">${quotesItem.real_unit_price}</td>
						<td id="roll${loop.count}">${quotesItem.roll}</td>
						<td id="quantity${loop.count}">${quotesItem.quantity}</td>
						<td id="subtotal${loop.count}">${quotesItem.subtotal}</td>	
					</tr>
					</c:forEach>
			</tbody>
			<tfoot  >
				<table width="100%" class="mt-3">
					<tr >
						<td colspan="5" style="text-align: right; font-weight: bold; padding: 0.5rem;">Total
							Amount (BZD)</td>
						<td width="15.5%"  align="center" id="tTotal"
							style="padding-right: 10px; font-weight: bold; padding: 0.5rem;"></td>
						
					</tr>

					<tr>
						<td colspan="5" style="text-align: right; font-weight: bold; padding: 0.5rem;" >Total
							Tax</td>
						<td width="15.5%" align="center" id="taxT"
							style="padding-right: 10px; font-weight: bold; padding: 0.5rem;"></td>
					</tr>
					<tr>
						<td colspan="5" style="text-align: right; font-weight: bold; padding: 0.5rem;">Grand
							Total (BZD)</td>
						<td width="15.5%" align="center" id="gTotal" style="font-weight: bold; padding: 0.5rem;"></td>
					</tr>
				</table>
			</tfoot>
		</table>
	</div>
	<div class="row">
		<div class="col pull-right">
			<p style="font-size: 14px !important;">Note:${quotesPojo.note}</p>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12"></div>


		<div class="col pull-right">
			<div class="print-flex well well-sm">

				<p style="font-size: 14px !important;">
				Created by:${quotesPojo.createdBy}  <br> Date: ${quotesPojo.date}
				</p>

				<a href="javascript:void(0);" onclick="printData()"><i class="fa fa-print fa-sm text-dark"></i></a>
			</div>

		</div>
	</div>

</div>
<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">
window.onload = function()  {
    var gTotal = 0;
    var taxT =0;
    var totalT =0;
    $("#salesReceipt > TBODY > tr").each(function () {
    	var t2 = $(this).find('td').eq(5).html();
   
    	 totalT += parseFloat(t2);
           
    });
     
    taxT= totalT * .125;
    gTotal=totalT+taxT;
    //totalT = grandT + taxT;
    $("#gTotal").html(gTotal.toFixed(2));
    $("#taxT").html(taxT.toFixed(2));
    $("#tTotal").html(totalT.toFixed(2));
    
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
	   }, 50);
  return true;
}
	
		
	
	
</script>	
