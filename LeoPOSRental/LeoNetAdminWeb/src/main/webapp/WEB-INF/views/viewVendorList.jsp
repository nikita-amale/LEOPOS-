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
                <h3 class="card-title">Vendor Details</h3>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th>VendorCode</th>
                    <th>Name</th> 
                    <th>Address</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Remark</th>
                    <th>Edit</th>
                    <th>Delete</th>
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="vendorPojo" items="${vendorPojo}" varStatus="loop">
                 <tr id="${loop.count}">
					<td>${loop.count}</td>
					<td id="venderCode${loop.count}">${vendorPojo.venderCode}</td>
					<td id="vendorName${loop.count}">${vendorPojo.vendorName}</td>
					<td id="address${loop.count}">${vendorPojo.address}</td>
					<td id="email${loop.count}">${vendorPojo.email}</td>
					 <td id="phoneNumber${loop.count}">${vendorPojo.phoneNumber}</td>
					  <td id="remark${loop.count}">${vendorPojo.remark}</td>
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
              <h4 class="modal-title">Vendor Details</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
               <form  method="POST" action="${pageContext.request.contextPath}/addvendor" autocomplete="off"
					modelAttribute="addvendor" name="addvendor">
                <div class="card-body">
                    <div class="form-group">
                    <label for="exampleInputEmail1">Vendor Name</label>
                    <input type="text" class="form-control" name="vendorName"  id="vendorName" placeholder="Vendor Name">
                  </div>
                 <div class="form-group">
                    <label for="exampleInputPassword1">Address</label>
                    <input type="text" class="form-control" name="address" id="address" placeholder="Address">
                  </div>
                  
                   <div class="form-group">
                    <label for="exampleInputPassword1">Email</label>
                    <input type="email" class="form-control" name="email" id="email"  placeholder="Email">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Phone Number</label>
                    <input type="tel" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Phone Number">
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">remark</label>
                    <input type="tel" class="form-control" name="remark" id="remark" placeholder="remark">
                  </div>
                   <div class="form-group">
                    <input type="hidden" class="form-control" name="venderCode" id="venderCode" placeholder="venderCode">
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
   

  function EditDetails(count){
	 $("#venderCode").val($("#venderCode"+count).text());  
     $("#vendorName").val($("#vendorName"+count).text());
     $("#address").val($("#address"+count).text());
     $("#email").val($("#email"+count).text());
     $("#phoneNumber").val($("#phoneNumber"+count).text());
     $("#remark").val($("#remark"+count).text());
	  }
  function deleteDetails(count){
	  var x = confirm("Are you sure you want to delete?");
      if (x) {
    	  var venderCode = $("#venderCode"+count).text();
    	  $("#"+count).remove();
       // Action for press ok
    	  $.ajax({
    			url : '${pageContext.request.contextPath}/deleteVendorDetails',
    			type : "POST",
    			dataType : "json",
    			data:{id:venderCode},
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

