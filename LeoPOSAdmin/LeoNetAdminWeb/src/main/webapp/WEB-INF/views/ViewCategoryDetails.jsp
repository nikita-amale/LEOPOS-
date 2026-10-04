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
      <div class="container-fluid">
        <div class="row mb-2">
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Category Details</h3>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Edit</th>
                    <th>Delete</th>
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="catagoryPojo" items="${catagoryPojo}" varStatus="loop">
                 <tr id="${loop.count}">
					<td>${loop.count}</td>
					<td id="catCode${loop.count}">${catagoryPojo.catCode}</td>
					<td id="catName${loop.count}">${catagoryPojo.catName}</td>
					<td id="catDesc${loop.count}">${catagoryPojo.catDesc}</td>
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
              <h4 class="modal-title">Category Details</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
               <form  method="POST" action="${pageContext.request.contextPath}/addCatagory" autocomplete="off"
					modelAttribute="addCatagory" name="addCatagory">
                <div class="card-body">
                    <div class="form-group">
                    <label for="exampleInputEmail1">Category Name</label>
                    <input type="text" class="form-control" name="catName"  id="catName" placeholder="Enter category">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Description</label>
                    <input type="text" class="form-control" name="catDesc"  id="catDesc" placeholder="Enter Description">
                  </div>
                     <div class="form-group">
                     <input type="hidden" class="form-control" name="catCode"  id="catCode" placeholder="Enter productName">
                  </div>
                  </div>
	            <div class="modal-footer justify-content-between">
	              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	              <button type="submit" class="btn btn-primary">Submit</button>
	            </div>
	              <div class="form-group">
					<c:if test="${not empty Msg}">
					 <jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
 		           </c:if>
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
  	if('${Msg}' == "" || '${Msg}' == null){
    		 	$("#resultmsg").hide();
    			}else{
    				$("#resultmsg").show();
    	}
     
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
   

  function EditDetails(count){
     $("#catCode").val($("#catCode"+count).text());
     $("#catName").val($("#catName"+count).text());
      $("#catDesc").val($("#catDesc"+count).text());
	  }
  function deleteDetails(count){
	  
	  var x = confirm("Are you sure you want to delete?");
      if (x) {
    	  var catCode = $("#catCode"+count).text();
    	  $("#"+count).remove();
       // Action for press ok
    	  $.ajax({
    			url : '${pageContext.request.contextPath}/deleteCategoryDetails',
    			type : "POST",
    			dataType : "json",
    			data:{catCode:catCode},
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