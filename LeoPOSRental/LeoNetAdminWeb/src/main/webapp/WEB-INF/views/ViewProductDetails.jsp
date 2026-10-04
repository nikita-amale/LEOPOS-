
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
							<h3 class="card-title">Product Details</h3>
						</div>
		
					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>ItemCode</th>
									<th>Name</th>
									<th>MPN</th>
									<th>Cost</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Created Date</th>
									<th>Edit</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="productDetailsPojo"
									items="${productDetailsPojo}" varStatus="loop">
									<tr id="${loop.count}">
										<td>${loop.count}</td>
										<td id="productId${loop.count}">${productDetailsPojo.productId}</td>
										<td id="productName${loop.count}">${productDetailsPojo.name}</td>
										<td id="productMPN${loop.count}">${productDetailsPojo.cf1}</td>
										<td id="productCost${loop.count}">${productDetailsPojo.cost}</td>
										<td id="productPrice${loop.count}">${productDetailsPojo.price}</td>
										<td id="productDiscription${loop.count}">${productDetailsPojo.quantity}</td>
										<td id="created_date${loop.count}">${productDetailsPojo.start_date}</td>
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
					action="${pageContext.request.contextPath}/updateProductDetails"
					autocomplete="off" modelAttribute="productDetails"
					name="productDetails" enctype="multipart/form-data">
					<div class="card-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Product Name</label> <input
								type="text" class="form-control" name="name"
								id="productName" placeholder="Enter productName">
								<input
								type="test" class="form-control" name="productId"
								id="productId" >
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Product Price</label> <input
								type="text" class="form-control" name="price"
								id="productPrice" placeholder="Enter productDetails">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Product MPN</label> <input
								type="text" class="form-control" name="cf1"
								id="productMPN" placeholder="Manufacturer">
						</div>
						
						<div class="form-group">
							<label for="exampleInputPassword1">Product Cost</label> <input
								type="text" class="form-control" name="cost" id="productCost"
								placeholder="Age">
						</div>
						
						
						

					
						<div class="form-group">
							<input type="hidden" class="form-control" name="itemCode"
								id="productCode">
						</div>
						<div class="form-group">
							<input type="hidden" class="form-control" name="vendorName"
								id="vendorName">
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
	  productPrice
	 $("#productId").val($("#productId"+count).text());
     $("#productName").val($("#productName"+count).text());
     $("#productPrice").val($("#productPrice"+count).text());
     $("#productMPN").val($("#productMPN"+count).text());
     $("#productCost").val($("#productCost"+count).text());
     $("#discount").val($("#discount"+count).text());
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
  							<h3 class="card-title">Product Details</h3>
  						</div>
  		
  					</div>
  					<div class="form-group">

  						<table id="myTable" class="table table-bordered table-hover">
  							<thead>
  								<tr>
  									<th>Serial No</th>
  									<th>ItemCode</th>
  									<th>Name</th>
  									<th>MPN</th>
  									<th>Cost</th>
  									<th>Price</th>
  									<th>Quantity</th>
  									<th>Created Date</th>
  									<th>Edit</th>
  									
  								</tr>
  							</thead>
  							<tbody>
  								<c:forEach var="productDetailsPojo"
  									items="${productDetailsPojo}" varStatus="loop">
  									<tr id="${loop.count}">
  										<td>${loop.count}</td>
  										<td id="productId${loop.count}">${productDetailsPojo.productId}</td>
  										<td id="productName${loop.count}">${productDetailsPojo.name}</td>
  										<td id="productMPN${loop.count}">${productDetailsPojo.cf1}</td>
  										<td id="productCost${loop.count}">${productDetailsPojo.cost}</td>
  										<td id="productPrice${loop.count}">${productDetailsPojo.price}</td>
  										<td id="productDiscription${loop.count}">${productDetailsPojo.quantity}</td>
  										<td id="created_date${loop.count}">${productDetailsPojo.start_date}</td>
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
  					action="${pageContext.request.contextPath}/updateProductDetails"
  					autocomplete="off" modelAttribute="productDetails"
  					name="productDetails" enctype="multipart/form-data">
  					<div class="card-body">
  						<div class="form-group">
  							<label for="exampleInputEmail1">Product Name</label> <input
  								type="text" class="form-control" name="name"
  								id="productName" placeholder="Enter productName">
  								<input
  								type="test" class="form-control" name="productId"
  								id="productId" >
  						</div>
  						<div class="form-group">
  							<label for="exampleInputEmail1">Product Price</label> <input
  								type="text" class="form-control" name="price"
  								id="productPrice" placeholder="Enter productDetails">
  						</div>
  						<div class="form-group">
  							<label for="exampleInputPassword1">Product MPN</label> <input
  								type="text" class="form-control" name="cf1"
  								id="productMPN" placeholder="Manufacturer">
  						</div>
  						
  						<div class="form-group">
  							<label for="exampleInputPassword1">Product Cost</label> <input
  								type="text" class="form-control" name="cost" id="productCost"
  								placeholder="Age">
  						</div>
  						
  						
  						

  					
  						<div class="form-group">
  							<input type="hidden" class="form-control" name="itemCode"
  								id="productCode">
  						</div>
  						<div class="form-group">
  							<input type="hidden" class="form-control" name="vendorName"
  								id="vendorName">
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
//   		url : '${pageContext.request.contextPath}/getCatagory',
//   		type : "GET",
//   		dataType : "json",
//   		success : function(data) {
//   			var ajaxCallData = JSON.stringify(data);
//   			  $('#catagoryList').append($("<option></option>").attr("value","0").text("Select")); 
//   				$.each(data, function(i, data) {
//   					$('#catagoryList').append('<option value="' + data.catCode + '">' + data.catName
//   							+ '</option>');
//   			});
  			  
//   		},
//   		error : function(error) {
//   			console.log(`Error ${error}`);
//   		}

//   	});


  //   $("#catagoryList").change(function() {

//   	  $.ajax({
//   			url : '${pageContext.request.contextPath}/getSubCatagory',
//   			type : "GET",
//   			dataType : "json",
//   			data:{catagoryId:$( "#catagoryList option:selected" ).val()},
//   			success : function(data) {
//    				var ajaxCallData = JSON.stringify(data);
//   				  $('#subcatagoryList').append($("<option></option>").attr("value","0").text("Select")); 
//   					$.each(data, function(i, data) {
//   						$('#subcatagoryList').append('<option value="' + data.subCatCode + '">' + data.subCatName
//   								+ '</option>');
//   				});
  				  
//   			},
//   			error : function(error) {
//   				console.log(`Error ${error}`);
//   			}

//   		});
  //   });

    function EditDetails(count){
  	  productPrice
  	 $("#productId").val($("#productId"+count).text());
       $("#productName").val($("#productName"+count).text());
       $("#productPrice").val($("#productPrice"+count).text());
       $("#productMPN").val($("#productMPN"+count).text());
       $("#productCost").val($("#productCost"+count).text());
       $("#discount").val($("#discount"+count).text());
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

  $("#vendorPojo").click(function() {
	  var e = document.getElementById("vendorPojo");
	  var text=e.options[e.selectedIndex].text;
	  $('#vendorName').val(text);
	  
	  
  });
</script>

</body>
</html>
