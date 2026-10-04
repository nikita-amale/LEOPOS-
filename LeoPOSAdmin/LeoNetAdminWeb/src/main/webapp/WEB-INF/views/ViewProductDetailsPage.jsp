

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>

<link rel="stylesheet" href="resources/css/style.css">
<!-- Include Lightbox2 CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.3/css/lightbox.min.css">

<!-- Include Lightbox2 JavaScript -->
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.3/js/lightbox.min.js"></script>
 -->
<!-- Content Wrapper. Contains page content -->
<style>
.show-div {
	display: flex;
}

/* Styles for the lightbox container */
.lightbox {
	/* Add styles to control the lightbox container */
	width: 90%;
	height: 90%;
}

/* Styles for the image in the lightbox */
.lb-image {
	/* Add styles to control the image appearance */
	width: 100%; /* Allow the image to expand to its original size */
	height: 100%;
}
</style>
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
					<div class="show-div">
						<label for="exampleInputEmail1">Show</label> <select id="show">
							<option
								href="${pageContext.request.contextPath}/getProductDetailsPageNew?pageSize=10"
								value="10">10</option>
							<option
								href="${pageContext.request.contextPath}/getProductDetailsPageNew?pageSize=25"
								value="25">25</option>
							<option
								href="${pageContext.request.contextPath}/getProductDetailsPageNew?pageSize=50"
								value="50">50</option>
						</select>
						<div class="form-search" style="position: absolute; right: 0;">
    <input type="text" placeholder="Search product" id="searchInput" name="search" 
           value="${search != null ? search : ''}" oninput="searchProductNew()" />
    <button type="button" onclick="searchProductNew()">
        <i class="fa fa-search"></i>
    </button>
</div>


					</div>
					<div class="form-group">

						<table id="myTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Serial No</th>
									<th>ItemCode</th>
									<th>Name</th>
									<th>Image</th>
									<th>MPN</th>
									<th>Cost</th>
									<th>RollPrice</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Inventory(%)</th>
									<th>Discount</th>
									<th>Created Date</th>
									<th>Edit</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="productDetailsPojo"
									items="${productDetailsPojo}" varStatus="loop">
									<c:set var="inventoryPercentage" value="${(productDetailsPojo.quantity * 100) / totalQuantity}" />									
                                   
									<c:set var="currentPage" value="${page}" />
									<c:set var="pageSize" value="25" />
									<c:set var="serialNumber"
										value="${(currentPage) * pageSize + loop.index}" />
									<c:set var="productCost" value="${productDetailsPojo.cost}" />
									<c:set var="rollprice" value="${productDetailsPojo.rollprice}" />
									<c:set var="price" value="${productDetailsPojo.price}" />
									<tr id="${loop.count}">
										<td>${serialNumber+1}</td>
										<td id="productId${loop.count}" class="productId">${productDetailsPojo.productId}</td>
										<td id="productName${loop.count}" contenteditable="true"
											class="productName">${productDetailsPojo.name}</td>
										<%-- <td style="padding: 1; text-align: center;"><img
											class="me-2 mb-2"
											src="${contextPath}/file/${productDetailsPojo.productFileName}"
											style="width: 90%; height: 100%;" alt="Product Image" /></td> --%>


										<td style="padding: 1; text-align: center;"><a
											href="${pageContext.request.contextPath}/images/${productDetailsPojo.productFileName}"
											data-lightbox="product-gallery" data-title="Product Image">
												<img class="me-2 mb-2"
												src="${pageContext.request.contextPath}/images/${productDetailsPojo.productFileName}"
												style="width: 90%; height: 100%;" alt="Product Image" />
										</a> <c:choose>
												<c:when
													test="${not empty productDetailsPojo.productFileName}">
													<a href="javascript:void(0);"
														onclick="removeImage(${productDetailsPojo.productId});">Remove
														Image</a>
												</c:when>
												<c:otherwise>
													<!-- No image exists, so hide the link -->
													<a style="display: none;">Remove Image</a>
												</c:otherwise>
											</c:choose></td>








										<td id="cf1${loop.count}" contenteditable="true" class="cf1">${productDetailsPojo.cf1}</td>
										<td id="productCost${loop.count}" contenteditable="true" class="productCost"><fmt:formatNumber
												pattern="0.00" value="${productCost}" /></td>
										<td id="rollprice${loop.count}" contenteditable="true"
											class="rollprice"><fmt:formatNumber pattern="0.00"
												value="${rollprice}" /></td>
										<td id="productPrice${loop.count}" contenteditable="true"
											class="productPrice"><fmt:formatNumber pattern="0.00"
												value="${price}" /></td>
										<td id="productQuantity${loop.count}" contenteditable="true"
											class="productQuantity">${productDetailsPojo.quantity}</td>
										   <td>
        <fmt:formatNumber value="${inventoryPercentage}" type="number" maxFractionDigits="4" minFractionDigits="4"/> %
    </td>
										<td id="productDiscount${loop.count}">${productDetailsPojo.promotion}</td>
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
					
						<div id="showingText">
   Showing ${currentRecords} of ${totalRecords} entries 
   | Total Quantity: ${totalQuantity}
</div>

						
					</div>
					<div class=".bottom-right" id="paginationSection"
						style="position: absolute; bottom: 0; right: 0; padding-bottom: 30px;">
						<table border="1" cellpadding="5" cellspacing="5">

							<c:set var="currentPage" value="${page}" />
							<c:set var="pageSize" value="${pageSize}" />

							<tr>
								<c:if test="${previous}">
									<td><a
										href="getProductDetailsPage?page=${currentPage - 1}"
										style="color: black;">Previous</a></td>
								</c:if>
								<c:if test="${next}">
									<c:forEach begin="${currentPage}" end="${currentPage+4}"
										var="i">

										<c:choose>
											<c:when test="${currentPage == i}">
												<td style="background-color: blue;"><a
													href="getProductDetailsPage?page=${i}"
													style="color: white;">${i}</a></td>
											</c:when>
											<c:otherwise>
												<td><a href="getProductDetailsPage?page=${i}">${i}</a></td>
											</c:otherwise>

										</c:choose>


									</c:forEach>
								</c:if>



								<c:if test="${next}">
									<td><a href="getProductDetailsPage?page=${currentPage +1}"
										style="color: black;">Next</a></td>
								</c:if>

							</tr>
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
								type="text" class="form-control" name="name" id="productName"
								placeholder="Enter productName"> <input type="test"
								class="form-control" name="productId" id="productId"
								hidden="true">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Product Cost</label> <input
								type="text" class="form-control" name="cost" id="productCost"
								placeholder="Age">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Product Price</label> <input
								type="text" class="form-control" name="price" id="productPrice"
								placeholder="Enter productDetails">
						</div>
						
							<div class="form-group">
							<label for="exampleInputEmail1">Product Quantity</label> <input
								type="text" class="form-control" name="quantity" id="productQuantity"
								placeholder="Enter product quantity">
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1">Product Roll Price</label> <input
								type="text" class="form-control" name="rollprice" id="rollprice"
								placeholder="Enter Roll Price">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Product MPN</label> <input
								type="text" class="form-control" name="cf1" id="productMPN"
								placeholder="Manufacturer">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Discount</label> <input
								type="number" class="form-control" name="promotion"
								id="productDiscount" placeholder="Discount">
						</div>

						<div class="form-group">
							<label for="exampleInputPassword1">Product Image</label> <input
								type="file" class="form-control" name="updatedImage"
								id="updatedImage" placeholder="updatedImage">
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

let debounceTimer;

function searchProductNew() {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
        actualSearchProductCall();
    }, 300);
}

 
function actualSearchProductCall() {
    const searchValue = document.getElementById('searchInput').value; // Get the search value

    // Ensure search is not empty to avoid unnecessary calls
    if (searchValue.length === 0) {
    	 console.warn("Search value is empty.");
         // Reload the page if the search is empty
         location.reload();
         return;
    }

    console.log("Search==", searchValue);

    const url = '${pageContext.request.contextPath}/searchProductNew';// The endpoint for search

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ search: searchValue }),
    })
    .then(response => response.json()) // Expect JSON response
    .then(data => {
    	
     //   console.log("Fetched data:", data); // Debugging log

        // Inject the fetched product rows into the table body
        const tableBody = document.querySelector('#myTable tbody');
        if (tableBody) {
            tableBody.innerHTML = data.productRows;
        }

        // Update the "Showing ${currentRecords} of ${totalRecords} entries" text
       const showingText = document.querySelector('#showingText');  // Assuming you have a span with this ID

if (showingText) {
    // Using string concatenation to set the text content
    showingText.textContent = 'Showing ' + data.totalRecords  + ' entries';
}


        // Hide pagination after search
        const paginationSection = document.querySelector('#paginationSection');
        if (paginationSection) {
            paginationSection.style.display = 'none';  // Hide pagination
        }
    })
    .catch(error => {
        console.error('Error during search:', error);
    });
}


// Ensure the search input gets focus and cursor blinks once the page loads
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.focus(); // Focus on the input field
        searchInput.selectionStart = searchInput.selectionEnd = searchInput.value.length; // Ensure cursor is at the end
    }
});



 
    $(function () {
      $('#myTable').DataTable({
        "paging": false,
        "pageLength": 100,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": fixed,
        "responsive": true,
        "scrollX": true
      });
    });
    
    document.getElementById('show').onchange = function() {
        window.location.href = this.children[this.selectedIndex].getAttribute('href');
    }
   
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
	  console.log("inside edit==="+count);
	 $("#productId").val($("#productId"+count).text());
     $("#productName").val($("#productName"+count).text());
     $("#productPrice").val($("#productPrice"+count).text());
     $("#rollprice").val($("#rollprice"+count).text());
     $("#productMPN").val($("#cf1"+count).text());
     $("#productCost").val($("#productCost"+count).text());
     $("#discount").val($("#discount"+count).text());
     $("#productDiscount").val($("#productDiscount"+count).text());
     $("#productQuantity").val($("#productQuantity"+count).text());
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
  
  
  // change quantity 
  
  $("#myTable tbody").on("blur", ".productQuantity", function(e) { 
	  console.log("triggered change quantity ");
	  var productId = $(this).closest("tr").find("td:eq(1)").text();
	  var quantity = $(this).text();
	  
	  $.ajax({
			url : '${pageContext.request.contextPath}/editQuantity',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				productId : productId,
				quantity : quantity
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  
  });
  

  
//  $("#myTable tbody tr .productPrice").on("blur",function(e){
	
	<sec:authorize access="hasAuthority('admin')">
	 $("#myTable tbody").on("blur", ".productPrice", function(e) { 
	  console.log("triggered");
	  var productId = $(this).closest("tr").find("td:eq(1)").text();
	  var price = $(this).text();
	  
	  $.ajax({
			url : '${pageContext.request.contextPath}/editPrice',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				productId:productId,
				price:price
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  
  });
	 </sec:authorize>
  
  
 // $("#myTable tbody tr .rollprice").on("blur",function(e){
	 
	 <sec:authorize access="hasAuthority('admin')">
	   $("#myTable tbody").on("blur", ".rollprice", function (e) {
	  var productId = $(this).closest("tr").find("td:eq(1)").text();
	  var rollprice = $(this).text();
	  
	  $.ajax({
			url : '${pageContext.request.contextPath}/rollprice',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				productId:productId,
				rollprice:rollprice
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  
  });	   
	   </sec:authorize>
  
//  $("#myTable tbody tr .productCost").on("blur", function (e) {
	
		<sec:authorize access="hasAuthority('admin')">
	 $("#myTable tbody").on("blur", ".productCost", function (e) {
	    var productId = $(this).closest("tr").find("td:eq(1)").text();
	    var productCost = $(this).text();

	    $.ajax({
	        url: "${pageContext.request.contextPath}/updateProductCost",
	        type: "POST",
	        data: {
	            productId: productId,      
	            productCost: productCost  
	        },
	        success: function (data) {
	            console.log("Success:", data.message);
	        },
	        error: function (error) {
	            console.log("Error:", error);
	        }
	    });
	});
	 </sec:authorize>

  
 // $("#myTable tbody tr .productName").on("blur",function(e){
	   $("#myTable tbody").on("blur", ".productName", function (e) {
	  var productId = $(this).closest("tr").find("td:eq(1)").text();
	  var productName = $(this).text();
	  console.log(productName);
	  
	  $.ajax({
			url : '${pageContext.request.contextPath}/productName',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				productId:productId,
				name:productName
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  
  });
  
 // $("#myTable tbody tr .cf1").on("blur",function(e){
	   $("#myTable tbody").on("blur", ".cf1", function (e) {
	  var productId = $(this).closest("tr").find("td:eq(1)").text();
	  var cf1 = $(this).text();
	
	  
	  $.ajax({
			url : '${pageContext.request.contextPath}/mpn',
			type : "POST",
			contentType: "application/json",
			dataType : "json",
			data : JSON.stringify({
				productId:productId,
				cf1:cf1
				}),
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
			},
			error : function(error) {
				console.log(`Error ${error}`);
			}

		});
	  
  });
  

  
  function removeImage(productId, linkElement) {
      if (confirm("Are you sure you want to remove this image?")) {
          $.ajax({
              type: "POST",
              url: "${pageContext.request.contextPath}/removeImage/" + productId,
              success: function (response) {
                  //alert(response); // Display a success message
                  // Remove the link from the DOM
                  $(linkElement).remove();
                  window.location.replace('${pageContext.request.contextPath}/viewProductDetailsPage');
              },
              error: function (xhr, status, error) {
                  console.error("Error: " + error);
              }
          });
      }
  }
  
  $("form[action*='updateProductDetails']").submit(function(event) {

	    let productName = $("#productName").val().trim();
	    let regex = /^[^,]+$/;

	    if (!regex.test(productName)) {
	        alert("Comma ( , ) is not allowed in Product Name!");
	        $("#productName").focus();
	        event.preventDefault();
	        return false;
	    }

	});

/*   lightbox.option({
      'resizeDuration': 200, // Set the duration for resizing animation
      'wrapAround': true,    // Allow navigation to the first/last image in the group
      'maxWidth': 800,       // Set a maximum width for the lightbox container (adjust as needed)
      'maxHeight': 600       // Set a maximum height for the lightbox container (adjust as needed)
  }); */
  
</script>



<script>
    // Initialize Lightbox2 with options
   
</script>



</body>
</html>
