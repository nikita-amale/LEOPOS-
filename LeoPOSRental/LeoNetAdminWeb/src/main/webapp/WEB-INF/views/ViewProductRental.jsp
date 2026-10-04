
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
<link rel="stylesheet" href="resources/css/style.css">
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
						<div class="card-header">
							<h3 class="card-title">Product Rental</h3>
						</div>
		
					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>ItemCode</th>
									<th>Image</th>
									<th>Name</th>
									<th>Cost</th>
									<th>Price</th>
									<th>Brand</th>
									<th>Edit</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="productRentalPojo"
									items="${productRentalPojo}" varStatus="loop">
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="rproductId${loop.count}">${productRentalPojo.rproductId}</td>
										<td style="width: 150px; max-width: 100%;">
									    <a href="${pageContext.request.contextPath}/images/${productRentalPojo.productFileName}" data-lightbox="product-gallery" data-title="Product Image">
									        <img class="me-2 mb-2" src="${pageContext.request.contextPath}/images/${productRentalPojo.productFileName}" style="max-width: 80%; max-height: 80%;" alt="Product Image" />
									    </a>
									</td>

										<td id="productName${loop.count}">${productRentalPojo.name}</td>
										<td id="productCost${loop.count}">${productRentalPojo.cost}</td>
										<td id="productPrice${loop.count}">${productRentalPojo.rprice}</td>
										<td id="productBrand${loop.count}">${productRentalPojo.brand}</td>
										
										<td>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-lg"
												onclick="EditDetails(${loop.count})">Edit</button>
										</td>
										
									</tr>
								</c:forEach>
							</tbody>

						</table>
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
<div class="modal fade" id="modal-lg">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Edit Product</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- general form elements -->
				<!-- form start -->
				<form method="POST"
					action="${pageContext.request.contextPath}/updateProductRentalDetails"
					autocomplete="off" modelAttribute="productRental"
					name="productDetails" enctype="multipart/form-data">
					<div class="card-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Product Name</label> <input
								type="text" class="form-control" name="name"
								id="productName" placeholder="Enter productName">
								<input
								type="test" class="form-control" name="rproductId"
								id="productId" >
						
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Product Cost</label> <input
								type="text" class="form-control" name="Cost"
								id="productCost" placeholder="Enter productDetails">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Product Price</label> <input
								type="text" class="form-control" name="Rprice"
								id="productPrice" placeholder="Manufacturer">
						</div>
						
						<div class="form-group">
							<label for="exampleInputPassword1">Product brand</label> <input
								type="text" class="form-control" name="brand" id="productBrand"
								placeholder="Brand">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Product Image</label> <input
								type="file" class="form-control" name="updatedImage"
								id="updatedImage" placeholder="updatedImage">
						</div>
						
						
						

					
						
					</div>
					<div class="modal-footer justify-content-between">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script type="text/javascript">
 
    $(function () {
      $('#myTable').DataTable({
        "paging": true,
        "pageLength": 25,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "scrollX": true
      });
    });
   
//   $.ajax({
// 		url : '${pageContext.request.contextPath}/getCatagory',
// 		type : "GET",
// 		dataType : "json",
// 		success : function(data) {
// 			var ajaxCallData = JSON.stringify(data);
// 			  $('#catagoryList').append($("<option></option>").attr("value","0").text("Select")); 
// 				$.each(data, function(i, data) {
// 					$('#catagoryList').append('<option value="' + data.catCode + '">' + data.catName
// 							+ '</option>');
// 			});
			  
// 		},
// 		error : function(error) {
// 			console.log(`Error ${error}`);
// 		}

// 	});


//   $("#catagoryList").change(function() {

// 	  $.ajax({
// 			url : '${pageContext.request.contextPath}/getSubCatagory',
// 			type : "GET",
// 			dataType : "json",
// 			data:{catagoryId:$( "#catagoryList option:selected" ).val()},
// 			success : function(data) {
//  				var ajaxCallData = JSON.stringify(data);
// 				  $('#subcatagoryList').append($("<option></option>").attr("value","0").text("Select")); 
// 					$.each(data, function(i, data) {
// 						$('#subcatagoryList').append('<option value="' + data.subCatCode + '">' + data.subCatName
// 								+ '</option>');
// 				});
				  
// 			},
// 			error : function(error) {
// 				console.log(`Error ${error}`);
// 			}

// 		});
//   });

  function EditDetails(count){
	  
	
     $("#productName").val($("#productName"+count).text());
     $("#productId").val($("#rproductId"+count).text());
     $("#productCost").val($("#productCost"+count).text());
     $("#productPrice").val($("#productPrice"+count).text());
     $("#productBrand").val($("#productBrand"+count).text());
    
	  }
 
  function deleteDetails(count){
	  
	  var x = confirm("Are you sure you want to delete?");
      if (x) { 
	  var productCode = $("#productCode"+count).text();
	  $("#"+count).remove();  
   $.ajax({
		url : '${pageContext.request.contextPath}/deleteProductDetails',
		type : "POST",
		dataType : "json",
		data:{productCode:productCode},
		success : function(data) {
			alertify
			  .alert(data.msgDescr, function(){
			    alertify.message('OK');
			  });
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
      }
      else {
       //Action for cancel
          return false;
      }
	 }
  function searchTable() {
	    var input, filter, found, table, tr, td, i, j;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    table = document.getElementById("myTable");
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


  $.ajax({
		url : '${pageContext.request.contextPath}/getVendor',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			  $('#vendorPojo').append($("<option></option>").attr("value","0").text("Select")); 
				$.each(data, function(i, data) {
					$('#vendorPojo').append('<option value="' + data.venderCode + '">' + data.vendorName
							+ '</option>');
			});
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
	
  $("#vendorPojo").click(function() {
	  var e = document.getElementById("vendorPojo");
	  var text=e.options[e.selectedIndex].text;
	  $('#vendorName').val(text);
	  
	  
  });
</script>

</body>
</html>
