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
      <div class="row justify-content-center">
          <!-- left column -->
          <div class="col-md-8 my-5">

            <div class="form-group">
              <c:if test="${not empty Msg}">
                <jsp:include page="/WEB-INF/common/Result.jsp"></jsp:include>
              </c:if>
            </div>

            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Add Category</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form  method="POST" action="${pageContext.request.contextPath}/addCatagory" autocomplete="off"
					modelAttribute="addCatagory" name="addCatagory">           

                <div class="card-body">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Category Name</label>
                    <input type="text" class="form-control" name="catName"  placeholder="Enter catagory" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Description</label>
                    <input type="text" class="form-control" name="catDesc"  placeholder="Discription" Required>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
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
