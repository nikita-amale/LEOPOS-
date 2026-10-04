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
          <div class="col-md-10">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Add Supplier</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form  method="POST" action="${pageContext.request.contextPath}/addvendor" autocomplete="off"
					modelAttribute="addvendor" name="addvendor">
                <div class="card-body">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Supplier Name</label>
                    <input type="text" class="form-control" name="vendorName"  placeholder="vendor Name" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Address</label>
                    <input type="text" class="form-control" name="address"  placeholder="Address">
                  </div>
                  
                   <div class="form-group">
                    <label for="exampleInputPassword1">Email</label>
                    <input type="email" class="form-control" name="email"  placeholder="Email">
                  </div>
                  
                  <div class="form-group">
                    <label for="exampleInputPassword1">Phone Number</label>
                    <input type="tel" class="form-control" name="phoneNumber"  maxlength="10" placeholder="Phone Number">
                  </div>
                    <div class="form-group">
                    <label for="exampleInputPassword1">Remark</label>
                    <input type="tel" class="form-control" name="remark" placeholder="remark">
                  </div>
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
<script>
$(function() {
 	if('${Msg}' == "" || '${Msg}' == null){
	 	$("#resultmsg").hide();
		}else{
			$("#resultmsg").show();
			}
});

</script>
