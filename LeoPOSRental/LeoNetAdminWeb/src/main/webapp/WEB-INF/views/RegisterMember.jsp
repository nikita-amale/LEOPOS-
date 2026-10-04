
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
          <div class="col-md-8 my-5 ">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Add Customer</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
            <form  method="POST" action="${pageContext.request.contextPath}/memberRegiProccess" autocomplete="off"
					modelAttribute="memberDetails" name="memberDetails" id="memberDetails" enctype="multipart/form-data">
                <div class="card-body">
                 <div class="form-group">
                    <label for="exampleInputEmail1">Company</label>
                    <input type="text" class="form-control" name="company"  placeholder="Company" Required>
                  </div>
				  <div class="form-group">
                    <label for="exampleInputEmail1">Date of Birth</label>
                    <input type="text" class="form-control" name="dob"  placeholder="dd-mm-yyyy">
                  </div>
				  
                 <div class="form-group">
                    <label for="exampleInputEmail1">Username</label>
                    <input type="text" class="form-control" name="userName"  placeholder="Enter User Name" Required>
                  </div>
                
                  <div class="form-group">
                    <label for="exampleInputEmail1">Customer Name</label>
                    <input type="text" class="form-control" name="name"  placeholder="Enter Customer Full Name" Required>
                  </div>
				  <!-- <div class="form-group">
                    <label for="exampleInputPassword1">Member Main Phone number</label>
                    <input type="text" class="form-control" name="phonemain"  placeholder="Enter Member Main Phone number" Required>
                  </div> -->
                  <div class="form-group">
                    <label for="exampleInputPassword1">Customer Whatsapp Number 1</label>
                    <input type="text" class="form-control" name="phonealter"  placeholder="Enter Customer Main Phone number" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Customer Whatsapp Number 2</label>
                    <input type="text" class="form-control" name="phonewhatsapp"  placeholder="Enter Customer Main Phone number" Required>
                  </div>
				   <div class="form-group">
                    <label for="exampleInputPassword1">Address</label>
                    <input type="text" class="form-control" name="address"  placeholder="Enter Address" >
                  </div>
				  
				  
                  
                   <div class="form-group">
                    <label for="exampleInputPassword1">Customer City</label>
                    <input type="text" class="form-control" name="city"  placeholder="Enter Customer City" >
                  </div>
				                    <div class="form-group">
                    <label for="exampleInputPassword1">Pin Code</label>
                    <input type="text" class="form-control" name="pincode"  placeholder="Enter Pin Code" Required>
                  </div>
           		  <!--  <div class="form-group">
                  <label for="exampleInputPassword1">Member Email</label>
                    <input type="text" class="form-control" name="email"  placeholder="Enter Member Email" Required>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Member Date of Birth</label>
                    <input type="text" class="form-control" name="dob"  placeholder="Enter Member Date of Birth" Required>
                  </div> -->
                  
                 
                  
                  

                <!-- /.card-body -->

                <div class="card-footer px-0">
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
  
</script>
