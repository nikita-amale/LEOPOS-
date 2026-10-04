
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
  <link rel="stylesheet" href="resources/css/style.css">
  <!-- Content Wrapper. Contains page content -->
  
  
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      <div class="row" style="margin-left:20%;">
          <!-- left column -->
          <div class="col-md-10" style="padding-top:40px">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Add Product</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
            <form  method="POST" action="${pageContext.request.contextPath}/addProductDetails" autocomplete="off"
					modelAttribute="productDetails" name="productDetails" id="productDetails" enctype="multipart/form-data">
                <div class="card-body">
                 <div class="form-group" style="display:flex">
				  <div class="col-md-6" >
                  <label>Select Category</label><font color="red">*</font><required>
                  <select class="form-control select2bs4"  name="category_id" id="catagoryList" style="width: 100%;" required>
                    <option value=""> Select </option>
                  </select>
				   </div>
				     <div class="col-md-6" >
                  <label>Select Sub-Category</label><font color="red">*</font>
                  <select class="form-control select2bs4"  name="subcategory_id" id="subcatagoryList" style="width: 100%;" required>
                    <option value=""> Select </option>
                  </select>
				   </div>
				 
                </div>
               
                
                 <div class="form-group" style="display:flex">
				  <div class="col-md-6" >
                  <label>Select Supplier</label><font color="red">*</font>
                  <select class="form-control select2bs4"  name="vendorCode" id="vendorPojo" style="width: 100%;" required >
                   <option value=""> Select </option>
                  </select>
                </div>
				 <div class="col-md-6" >
				 <label for="exampleInputEmail1">Product Name</label><font color="red">*</font>
                    <input type="text" class="form-control" name="name"  placeholder="Enter productName" Required>
				
				</div>
				</div>

                  <div class="form-group">
                    <label for="exampleInputEmail1">Product Details</label>
                    <input type="text" class="form-control" name="product_details"  placeholder="Enter productDetails" Required>
                  </div>
				  
				  
                    <div class="form-group" style="display:flex">
				  <div class="col-md-6" >
                    <label for="exampleInputEmail1">Track Quantity</label>
                    <input type="number" class="form-control" name="track_quantity"  placeholder="Track Quantity" Required>
                  </div>
				  
				  <div class="col-md-6" >
				  <label for="exampleInputPassword1">Description</label>
                    <input type="text" class="form-control" name="details"  placeholder="Description" Required >
				  </div>
				  </div>
                  
                 <div class="form-group">
                    <label for="exampleInputEmail1">Roll Price</label>
                    <input type="number" class="form-control" name="rollprice" id="rollprice" placeholder="Roll Price" Required>
                  </div>
				 
				  
                   <div class="form-group" style="display:flex">
				   <div class="col-md-6" >
                    <label for="exampleInputPassword1">Sale Unit</label><font color="red">*</font>
                    <input type="number"  inputmode="numeric"  class="form-control" name="sale_unit"  placeholder="Sale Unit" Required>
                  </div>
                   <div class="col-md-6" >
                    <label for="exampleInputPassword1">Purchase Unit</label>
                    <input type="number" class="form-control" name="purchase_unit" id="purchase_unit"  placeholder="Purchase Unit" Required>
                  </div>
				  </div>
                  
                 <div class="form-group" style="display:flex">
				   <div class="col-md-6" >
                    <label for="exampleInputPassword1">Cost</label><font color="red">*</font>
                    <input type="number"  class="form-control" name="cost"  placeholder="Cost" Required>
                  </div>
                      <div class="col-md-6" >
                    <label for="exampleInputPassword1">Price</label>
                    <input type="number" class="form-control" name="price" id="price"  placeholder="Price" Required>
                  </div>
				  </div>
				  
				  
                  <div class="form-group" style="display:flex">
                     <div class="col-md-6" >
                    <label for="exampleInputPassword1">Purchase Price</label>
                    <input type="number"  class="form-control" name="productPrice" id="productPrice" placeholder="Purchase Price" Required >
                  </div>
                  
                    <div class="col-md-6" >
                    <label for="exampleInputPassword1">MPN</label><font color="red">*</font>
                    <input type="text"  class="form-control" name="cf1"  placeholder="MPN" Required>
                  </div>
				  </div>
				  <div class="form-group">
							<label for="exampleInputPassword1">Product Discount</label> <input
								type="number" class="form-control" name="promotion"
								id="promotion" placeholder="Discount">
						</div>
						  <div class="form-group">
							<label for="exampleInputPassword1">Product Image</label> <input
								type="file" class="form-control" name="productImage"
								id="promotion" placeholder="productImage" required>
						</div>
                  
				  <input type="hidden" class="form-control" name="user" id="user" value="${userName}">
				  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
                  <div class="form-group">
					<c:if test="${not empty Msg}">
					 <jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
 		           </c:if>
				 </div>
              </form>
            </div>
            <!-- /.card -->
     </div>
     </div>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  
 <jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
  <script type="text/javascript">
  $(function() {
   	if('${Msg}' == "" || '${Msg}' == null){
  	 	$("#resultmsg").hide();
  		}else{
  			$("#resultmsg").show();
  			}
  });
  
  
  
  $.ajax({
		url : '${pageContext.request.contextPath}/getVendor',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
 		<!--	  $('#vendorPojo').append($("<option></option>").attr("value","0").text("Select")); -->
				$.each(data, function(i, data) {
					 
					$('#vendorPojo').append('<option value="' + data.venderCode + '">' + data.vendorName
							+ '</option>');
			});
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});


  
  
  
  
  $.ajax({
		url : '${pageContext.request.contextPath}/getCatagory',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			<!--  $('#catagoryList').append($("<option></option>").attr("value","0").text("Select")); -->
				$.each(data, function(i, data) {
					$('#catagoryList').append('<option value="' + data.catCode + '">' + data.catName
							+ '</option>');
			});
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});


  $("#catagoryList").change(function() {
	  
	  $('#subcatagoryList').empty();

	  $.ajax({
			url : '${pageContext.request.contextPath}/getSubCatagory',
			type : "GET",
			dataType : "json",
			data:{catagoryId:$( "#catagoryList option:selected" ).val()},
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				<!--  $('#subcatagoryList').append($("<option></option>").attr("value","0").text("Select")); -->
					$.each(data, function(i, data) {
						$('#subcatagoryList').append('<option value="' + data.subCatCode + '">' + data.subCatName
								+ '</option>');
				});

			},



			error : function(error) {
				console.log(`Error ${error}`);
			}

		});


	  
  });

  $("#vendorPojo").click(function() {
	  var e = document.getElementById("vendorPojo");
	  var text=e.options[e.selectedIndex].text;
	  $('#vendorName').val(text);
	  
	  
  });
  $("#productDetails").submit(function( event ) {
	  if ( $( "#promotion" ).val() == "" ) {
		  $( "#promotion" ).val(0)
	  }
	  if ( $( "#pieces" ).val() == "" ) {
		  $( "#pieces" ).val(0)
	  }
	  if ( $( "#productPrice" ).val() == "" ) {
		  $( "#productPrice" ).val(0.0)
	  }
	 
	  //event.preventDefault();
	});
</script>
