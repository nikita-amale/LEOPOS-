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
      <div class="row" style="margin-left: 450px;">
          <!-- left column -->
          <div class="col-md-7">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">BarCode Generator</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
            <form  method="POST" action="${pageContext.request.contextPath}/addProductBarCodeDetails" autocomplete="off"
					modelAttribute="BarCodePojo" name="BarCodePojo">
                <div class="card-body">
                 <div class="form-group">
                  <label>Select Category</label>
                  <select class="form-control select2bs4"  name="catCode" id="catagoryList" style="width: 100%;" Required>
                  </select>
                </div>
                <div class="form-group">
                  <label>Select Category</label>
                  <select class="form-control select2bs4"  name="subCatCode" id="subcatagoryList" style="width: 100%;" Required>
                  </select>
                </div>
                
                 <div class="form-group">
                  <label>Select Product</label>
                  <select class="form-control select2bs4"  name="productCode" id="productList" style="width: 100%;" Required>
                  </select>
                </div>
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

  $("#subcatagoryList").change(function() {
  $.ajax({
		url : '${pageContext.request.contextPath}/getProductList',
		type : "GET",
		dataType : "json",
		data:{subCatagoryId:$( "#subcatagoryList option:selected" ).val()},
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			  $('#productList').append($("<option></option>").attr("value","0").text("Select")); 
				$.each(data, function(i, data) {
					$('#productList').append('<option value="' + data.itemCode + '">' + data.productName
							+ '</option>');
			});
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
  });

  
</script>
