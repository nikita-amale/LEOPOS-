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
							<h3 class="card-title">Sales Pdf Regeneration</h3>
						</div>

						<form autocomplete="off" name="purchase" id="itemForm"
							action="salesReport">
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

							</div>
						</form>

						<div class="card-body">
						
						<div id="progressDiv" style="margin-top:20px; font-weight:bold;"></div>
						
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

document.getElementById('startDate').addEventListener('click', function() {
    this.showPicker();
});

document.getElementById('endDate').addEventListener('click', function() {
    this.showPicker();
});

function reset() {
    window.location.reload(true);
}

$("#itemForm").submit(function (event) {
    event.preventDefault();

    $.post("salesPdfRegenerate", $(this).serialize(), function (jobId) {
        alert("Job started with ID: " + jobId);

        let interval = setInterval(function () {
            $.get("salesPdfProgress", {jobId: jobId}, function (progress) {
                let percent = 0;
                if (progress.percent !== undefined && !isNaN(progress.percent)) {
                    percent = parseFloat(progress.percent).toFixed(2);
                }

                $("#progressDiv").html(
                    "Processed: " + progress.processed + "/" + progress.total +
                    " (" + percent + "%)<br>" +
                    "Elapsed: " + (progress.elapsedTime || "N/A") + "<br>" +
                    "ETA: " + (progress.eta || "N/A") +
                    (progress.totalTime ? "<br>Total Time: " + progress.totalTime : "")
                );

                if (progress.completed) {
                    clearInterval(interval);
                    $("#progressDiv").append("<br><b>Completed!</b>");
                }
            });
        }, 2000);
    });
});

window.addEventListener('DOMContentLoaded', function() {
    let startDateInput = document.getElementById('startDate');
    let endDateInput = document.getElementById('endDate');

    let today = new Date();
    let yyyy = today.getFullYear();
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let dd = String(today.getDate()).padStart(2, '0');

    startDateInput.value = yyyy + "-" + mm + "-01";   // first of month
    endDateInput.value = yyyy + "-" + mm + "-" + dd; // today
});


	
	
</script>


</body>
</html>