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
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h5 class=" mb-0 text-dark">Member Details</h5>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th>Customer User Name</th>
                    <th>Customer Name</th>
                    <!-- <th>Email</th>
                    <th>Date of Birth</th>
                   <th>Main Phone</th> -->
                    <th>Whatsapp 1</th>
                    <th>Whatsapp 2</th>
                    <th>Deposit</th>
                    <th>Points</th>
                    <th>Edit Member</th>
                    <th>Purchase Plan</th>
                     <th>Customer Report </th>
                    
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="memberDtlVO" items="${memberDtlVO}" varStatus="loop">
                 <tr>
					<td>${loop.count}</td>
					<td id="userName${loop.count}">${memberDtlVO.userName}</td>
					<td id="name${loop.count}">${memberDtlVO.name}</td>
					<!-- <td id="email${loop.count}">${memberDtlVO.email}</td>
					 <td id="DOB${loop.count}">${memberDtlVO.DOB}</td>
					<td id="phonemain${loop.count}">${memberDtlVO.phonemain}</td> -->
					<td id="phonealter${loop.count}">${memberDtlVO.phonealter}</td>
					<td id="phonewhatsapp${loop.count}">${memberDtlVO.phonewhatsapp}</td>
					<td id="deposit${loop.count}">${memberDtlVO.deposit}</td>
					<td id="bpts${loop.count}">${memberDtlVO.bpts}</td>
					<td> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="EditMemberDetails(${loop.count})">Edit</button></td>
					<td> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-pp" onclick="EditMemberDetails(${loop.count})">Purchase Plan</button></td>
					<td><a href="${pageContext.request.contextPath}/getcustomerbymemberId?MemberId=${memberDtlVO.id}"target="_blank">Customer Report</a></td>
					
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
       <!-- /.content -->
  </div>
      <div class="modal fade" id="modal-lg">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header bg-primary">
              <h4 class="modal-title">Edit Customer Details</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" class="text-white">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
                <form  method="POST" action="${pageContext.request.contextPath}/updateMemberDetails" autocomplete="off"
					modelAttribute="memberDetails" name="memberDetails" enctype="multipart/form-data">
                <div class="card-body">
                   <div class="form-group">
                    <label for="exampleInputEmail1">Customer Name</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter Parent Name">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">DOB</label>
                    <input type="number"  step="any" class="form-control" name="child1age" id="child1age" placeholder="First Child Age">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Company Name</label>
                    <input type="text"  step="any" class="form-control" name="child1" id="child1" placeholder="Second child Name">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">DOB</label>
                    <input type="number"  step="any" class="form-control" name="child2age" id="child2age" placeholder="Second child Age">
                  </div>
                 <!--  <div class="form-group">
                    <label for="exampleInputEmail1">Member Email</label>
                    <input type="text" class="form-control" name="email" id="email" placeholder="Enter Member Email">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Member DOB</label>
                    <input type="text" class="form-control" name="DOB"  id="DOB" placeholder="Date of Birth">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Main Phone</label>
                    <input type="text" class="form-control" name="phonemain"  id="phonemain" placeholder="Main Phone">
                  </div> -->
                   <div class="form-group">
                    <label for="exampleInputPassword1">Whatsapp 1</label>
                    <input type="text" class="form-control" name="phonealter"   id="phonealter" placeholder="Whatsapp 1">
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Whatsapp 2</label>
                    <input type="number"  step="any" class="form-control" name="phonewhatsapp" id="phonewhatsapp" placeholder="Whatsapp 2">
                  </div>
                 
                   <div class="form-group">
                    <label for="exampleInputPassword1">Deposit</label>
                    <input type="number" class="form-control" name="deposit"  id="deposit" placeholder="deposit">
                  </div>
                
                  <div class="form-group">
                     <input type="hidden" class="form-control" name="userName"  id="userName">
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
      
       <div class="modal fade" id="modal-pp">
        <div class="modal-dialog modal-pp">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Purchase Plan Member</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" class="text-white">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
                <form  method="POST" action="${pageContext.request.contextPath}/purchaseMemberPlan" autocomplete="off"
					 enctype="multipart/form-data">
                <div class="card-body">
                   <div class="form-group">
                    <label for="exampleInputEmail1">Parent Name</label>
                    <input type="text" class="form-control" name="name" id="parentname" placeholder="Parent Name" readonly>
                     
                  </div>
                  <div class="form-group">
                    <label for="exampleInputEmail1">Select Plan</label>
                    <select class="form-control select2bs4"  name="id" id="planPojo1" style="width: 100%;" Required>
                    </select>
                  </div>
    
					<div class="form-group">
                     <input type="hidden" class="form-control" name="userName"  id="userNamepp" >
                  </div>
                 
                  </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Purchase</button>
            </div>
              </form>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal --> 
    
  </div>
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
    
    
    function EditMemberDetails(count){
        $("#userName").val($("#userName"+count).text());
        $("#name").val($("#name"+count).text());
        $("#email").val($("#email"+count).text());
        $("#DOB").val($("#DOB"+count).text());
        $("#phonemain").val($("#phonemain"+count).text());
        $("#phonealter").val($("#phonealter"+count).text());
        $("#phonewhatsapp").val($("#phonewhatsapp"+count).text());
        $("#deposit").val($("#deposit"+count).text());
        $("#tgpts").val($("#tgpts"+count).text());
        $("#bpts").val($("#bpts"+count).text());
        $("#child1").val($("#child1"+count).text());
        $("#child1age").val($("#child1age"+count).text());
        $("#child2").val($("#child2"+count).text());
        $("#child2age").val($("#child2age"+count).text());
        $("#parentname").val($("#name"+count).text());
        $("#userNamepp").val($("#userName"+count).text());
       
           
   	  }
    
    

    
    $.ajax({
		url : '${pageContext.request.contextPath}/getPlan',
		type : "GET",
		dataType : "json",
		success : function(data) {
			var ajaxCallData = JSON.stringify(data);
			  $('#planPojo1').append($("<option></option>").attr("value","0").text("Select")); 
				$.each(data, function(i, data) {
					$('#planPojo1').append('<option value="' + data.id + '">' + data.planName
							+ '</option>');
			});
			  
		},
		error : function(error) {
			console.log(`Error ${error}`);
		}

	});
    
    </script>
</body>
</html>