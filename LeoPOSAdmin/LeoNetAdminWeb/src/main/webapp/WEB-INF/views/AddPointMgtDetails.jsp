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
          <div class="col-md-10" style="padding-top:60px">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Point Management</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
            <form  method="POST" action="${pageContext.request.contextPath}/productManagent" autocomplete="off"
					modelAttribute="productDetails" name="productDetails" enctype="multipart/form-data">
                <div class="card-body">
                 <div class="form-group">
                  <label>Select Category</label>
                  <select class="form-control select2bs4"  name="catCode" id="catagoryList" style="width: 100%;">
                   
                  </select>
                </div>
                <div class="form-group">
                  <label>Select SubCategory</label>
                 <select class="form-control select2bs4"  name="Subcatlist" id="SubcatagoryList" style="width: 100%;">
                   
                  </select>
                </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">product Name</label>
                    <input type="text" class="form-control" name="productName"  placeholder="Enter productName">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Manufacturer</label>
                    <input type="text" class="form-control" name="Manufacturer"  placeholder="Manufacturer">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Product Price</label>
                    <input type="text" class="form-control" name="productPrice"  placeholder="productPrice">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Product Mrp</label>
                    <input type="text" class="form-control" name="mrp"  placeholder="product MRP">
                  </div>
                  
                    <div class="form-group">
                    <label for="exampleInputPassword1">Discount</label>
                    <input type="text" class="form-control" name="discount"  placeholder="Discount">
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Purchase Price</label>
                    <input type="text" class="form-control" name="PurchasePrice"  placeholder="Purchase Price">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Points</label>
                    <input type="text" class="form-control" name="Points"  placeholder="Points">
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Description</label>
                    <input type="text" class="form-control" name="productDiscription"  placeholder="Discription">
                  </div>
                 <!--  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <div class="input-group">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" name="File" multiple>
                        <label class="custom-file-label" >Choose file</label>
                      </div>
                    </div>
                  </div> 
                 <div class="form-check">
                    <input type="checkbox" class="form-check-input" name="isActive" value = "0">
                    <label class="form-check-label" for="exampleCheck1">Active</label>
                  </div> -->
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
  <!-- <script type="text/javascript">
  $(function() {
   	if('${Msg}' == "" || '${Msg}' == null){
  	 	$("#resultmsg").hide();
  		}else{
  			$("#resultmsg").show();
  			}
  });
  
  
  $.ajax({
		url : '${pageContext.request.contextPath}/getCatagory',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			  $('#catagoryList').append($("<option></option>").attr("value","0").text("Select")); 
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

	  $.ajax({
			url : '${pageContext.request.contextPath}/getSubCatagory',
			type : "GET",
			dataType : "json",
			data:{catagoryId:$( "#catagoryList option:selected" ).val()},
			success : function(data) {
				var ajaxCallData = JSON.stringify(data);
				  $('#subcatagoryList').append($("<option></option>").attr("value","0").text("Select")); 
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


  
</script>
 -->