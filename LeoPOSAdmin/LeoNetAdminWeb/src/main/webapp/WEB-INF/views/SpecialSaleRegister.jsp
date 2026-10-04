<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Special Sale Register</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<style>
.pagination {
	margin-top: 20px;
	display: flex;
	justify-content: flex-end;
}

.pagination a {
	margin: 2px;
}
</style>

</head>

<body>

	<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>

	<div class="content-wrapper">

		<section class="content">

			<div class="container-fluid">

				<div class="row">

					<div class="col-12">

						<div class="card">

							<div class="card-header">
								<h3 class="card-title">Special Sale Register</h3>
							</div>


							<div class="card-body">

								<table class="table table-bordered table-hover">

									<thead>

										<tr>

											<th>Serial No</th>
											<th>Date</th>
											<th>Opening Balance</th>
											<th>Cash Payment</th>
											<th>Sales Amount</th>
											<th>Closing Balance</th>
											<th>View</th>

											<th hidden>rid</th>

										</tr>

									</thead>


									<tbody>

										<c:forEach var="registerPojo" items="${registerPojo}"
											varStatus="loop">

											<tr id="${loop.count}">

												<td>${loop.count}</td>

												<td id="date${loop.count}">
													${registerPojo.registerDate}</td>

												<td id="openingbal${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${registerPojo.openingBalance}" />
												</td>

												<td id="cashpayment${loop.count}"
													class="btn btn-link text-right"
													data-date="${registerPojo.registerDate}"
													onclick="handleCashPayment(this)"><fmt:formatNumber
														pattern="0.00" value="${registerPojo.cashPayment}" /></td>

												<td id="salesamount${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${registerPojo.saleAmount}" /></td>

												<td id="closingbal${loop.count}"><fmt:formatNumber
														pattern="0.00" value="${registerPojo.closingBalance}" />
												</td>

												<td>

													<button class="btn btn-primary" data-toggle="modal"
														data-target="#modal-lg"
														onclick="ViewDetails(${loop.count})">View</button>

												</td>

												<td hidden id="rid${loop.count}">${registerPojo.id}</td>

											</tr>

										</c:forEach>

									</tbody>

								</table>


								<!-- Pagination -->

								<c:set var="startPage" value="${currentPage - 2}" />
								<c:set var="endPage" value="${currentPage + 2}" />

								<c:if test="${startPage < 0}">
									<c:set var="startPage" value="0" />
								</c:if>

								<c:if test="${endPage >= totalPages}">
									<c:set var="endPage" value="${totalPages - 1}" />
								</c:if>


								<div class="pagination">

									<c:if test="${currentPage > 0}">
										<a class="btn btn-sm btn-secondary"
											href="?page=${currentPage - 1}&size=10">Previous</a>
									</c:if>

									<c:forEach begin="${startPage}" end="${endPage}" var="i">

										<a
											class="btn btn-sm ${i == currentPage ? 'btn-primary' : 'btn-light'}"
											href="?page=${i}&size=10"> ${i + 1} </a>

									</c:forEach>

									<c:if test="${currentPage < totalPages - 1}">
										<a class="btn btn-sm btn-secondary"
											href="?page=${currentPage + 1}&size=10">Next</a>
									</c:if>

								</div>


							</div>

						</div>

					</div>

				</div>

			</div>

		</section>

	</div>



	<!-- View Modal -->

	<div class="modal fade" id="modal-lg">

		<div class="modal-dialog modal-lg">

			<div class="modal-content">

				<div class="modal-body">

					<button type="button" class="close" data-dismiss="modal">

						×</button>


					<h5>Register Details</h5>

					<div class="table-responsive">

						<table class="table table-bordered">

							<tr>
								<td>Date</td>
								<td id="dateRegister"></td>
							</tr>

							<tr>
								<td>Opening Balance</td>
								<td id="openingbal"></td>
							</tr>

							<tr>
								<td>Cash Payment</td>
								<td id="cashpayment"></td>
							</tr>

							<tr>
								<td>Sales Amount</td>
								<td id="salesamount"></td>
							</tr>

							<tr>
								<td>Closing Balance</td>
								<td id="closingbal"></td>
							</tr>

						</table>

					</div>

				</div>

			</div>

		</div>

	</div>
	
	<div class="modal fade" id="viewpayment" tabindex="-1" aria-labelledby="viewpaymentLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                <h5>Cash Payment Details</h5>

                <div class="table-responsive">
                    <table id="paymentreceipt" class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Amount</th>
                            </tr>
                        </thead>
                        <tbody style="text-align: center;"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


	<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>



	<script>

function ViewDetails(count){

$("#dateRegister").html($("#date"+count).text());

$("#openingbal").html($("#openingbal"+count).text());

$("#cashpayment").html($("#cashpayment"+count).text());

$("#salesamount").html($("#salesamount"+count).text());

$("#closingbal").html($("#closingbal"+count).text());

}

</script>

<script>
function handleCashPayment(element) {
    var date = $(element).data('date'); // yyyy-MM-dd format
    console.log("Selected Date:", date);

    // Open modal
    $('#viewpayment').modal('show');

    // Clear previous data
    $('#paymentreceipt tbody').empty();

    // AJAX call to fetch cash payments
    $.ajax({
        url: "${pageContext.request.contextPath}/special/cash-payments?date=" + date,
        type: "GET",
        dataType: "json",
        success: function(data) {
            if(data.length === 0){
                $('#paymentreceipt tbody').append('<tr><td colspan="1">No payments found</td></tr>');
                return;
            }

            $.each(data, function(i, payment) {
                var tr = $("<tr></tr>");
                tr.append($('<td></td>').text(payment.cashPayment.toFixed(2)));
                $('#paymentreceipt tbody').append(tr);
            });
        },
        error: function(err) {
            console.error("Error fetching cash payments:", err);
            $('#paymentreceipt tbody').append('<tr><td colspan="1">Error fetching payments</td></tr>');
        }
    });
}
</script>

</body>

</html>