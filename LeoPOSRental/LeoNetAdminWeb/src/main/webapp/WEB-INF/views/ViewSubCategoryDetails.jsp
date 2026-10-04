<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
 <link rel="stylesheet" href="resources/css/style.css">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">SubCategory Details</h3>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
              <form  method="GET" action="${pageContext.request.contextPath}/getSubCategoryDetailsList" autocomplete="off" name="ProductDetails">
                <div class="card-body">
                <div class="form-group">
                  <label>Select Category</label>
                  <select class="form-control select2bs4"  name="catagoryId" id="catagoryList" style="width: 100%;">
                  </select>
                </div>
                 </div>
                 <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>CategoryName</th>
                    <th>Edit</th>
                    <th>Delete</th>
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="subCatagoryPojo" items="${subCatagoryPojo}" varStatus="loop">
                 <tr id="${loop.count}">
					<td>${loop.count}</td>
					<td id="subCatCode${loop.count}">${subCatagoryPojo.subCatCode}</td>
					<td id="subCatName${loop.count}">${subCatagoryPojo.subCatName}</td>
					<td id="subCatDesc${loop.count}">${subCatagoryPojo.subCatDesc}</td>
					<td id="catName${loop.count}">${subCatagoryPojo.catName}</td>
					 <td> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="EditDetails(${loop.count})">Edit</button></td>
					 <td> <button type="button" class="btn btn-primary" onclick="deleteDetails(${loop.count})">Delete</button></td>
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
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
      <div class="modal fade" id="modal-lg">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">SubCategory Details</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
               <form  method="POST" action="${pageContext.request.contextPath}/addSubCatagory" autocomplete="off"
					modelAttribute="addSubCatagory" name="addSubCatagory" enctype="multipart/form-data">
                <div class="card-body">
                    <div class="form-group">
                    <label>SubCatagory Name</label>
                    <input type="text" class="form-control" name="subCatName" id="subCatName" placeholder="Enter Subcatagory">
                    </div>
                  <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" name="subCatDesc"  id="subCatDesc" placeholder="Enter Description">
                  </div>
                  <div class="form-group">
                     <input type="hidden" class="form-control" name="subCatCode"  id="subCatCode">
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
      $('#example2').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
      });
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

  function EditDetails(count){
     $("#subCatName").val($("#subCatName"+count).text());
     $("#subCatDesc").val($("#subCatDesc"+count).text());
      $("#subCatCode").val($("#subCatCode"+count).text());
	  }
  function deleteDetails(count){
	  var x = confirm("Are you sure you want to delete?");
      if (x) {
    	  var subCatCode = $("#subCatCode"+count).text();
    	  $("#"+count).remove();
       // Action for press ok
    	  $.ajax({
    			url : '${pageContext.request.contextPath}/deleteSubCategoryDetails',
    			type : "POST",
    			dataType : "json",
    			data:{subCatCode:subCatCode},
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
</script>
 
</body>
</html>
