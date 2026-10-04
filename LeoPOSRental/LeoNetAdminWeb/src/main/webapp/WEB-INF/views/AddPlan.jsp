<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
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
                <h3 class="card-title">Add Plan</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form  method="POST" action="${pageContext.request.contextPath}/addPlan" autocomplete="off"
					modelAttribute="addPlan" name="addPlan">
                <div class="card-body">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Plan Name</label>
                    <input type="text" class="form-control" name="planName"  placeholder="Enter Plan Name" Required>
                  </div>
                  <!-- <div class="form-group">
                    <label for="exampleInputPassword1">Deposit</label>
                    <input type="text" class="form-control" name="deposit"  placeholder="Plan Deposit" Required>
                  </div> -->
                  <div class="form-group">
                    <label for="exampleInputPassword1">Monthly Fees</label>
                    <input type="text" class="form-control" name="monthlyFee"  placeholder="Monthly Fees" Required>
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Points for Games & toys</label>
                    <input type="text" class="form-control" name="gamestoysPts"  placeholder="Points for Games & toys" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Points for Books</label>
                    <input type="text" class="form-control" name="booksPts"  placeholder="Points for Books" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Plan Validity</label>
                    <input type="text" class="form-control" name="validity"  placeholder="Plan Validity" Required>
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
<script>
$(function() {
 	if('${Msg}' == "" || '${Msg}' == null){
	 	$("#resultmsg").hide();
		}else{
			$("#resultmsg").show();
			}
});

</script>
