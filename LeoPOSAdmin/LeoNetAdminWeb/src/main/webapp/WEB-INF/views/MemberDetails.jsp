<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
 <jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>
 <link rel="stylesheet" href="resources/css/style.css">
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
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
                <h3 class="card-title">Member Details</h3>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th> User Name</th>
                    <th>Customer Name</th>
                    <th>Price group</th>
                    <th>Member Id</th>
                    <th hidden>phonewhatsapp</th>
                    <th>Customer Type</th>
                    <th hidden> Company Name</th>
                    <th hidden> DOB</th>
                    <th>Whatsapp</th>
                    <th>Phone Number</th>
                    <th>First Email </th>
                    <th>Deposit</th>
                    <th>Credit Payment</th>
                    <th>Credit Facility</th>
                    <th hidden>Pin Code</th>
                    <th hidden>address</th>
                    <th>Threshold Amount</th>
                    <th>Threshold Days</th>
                    <th hidden>Customer block</th>
                    <th hidden >Contact name</th>
                    <th></th>
                    <!-- <th>Purchase Plan</th>
                    <th>Customer Report</th> -->
                    
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="memberDtlVO" items="${memberDtlVO}" varStatus="loop">
                 <c:set var = "balance" value = "${memberDtlVO.deposit}" />
                 <tr>
					<td>${loop.count}</td>
					<td id="userName${loop.count}">${memberDtlVO.userName}</td>
					<td id="name${loop.count}">${memberDtlVO.name}</td>
					<td id="pricegroup${loop.count }">${memberDtlVO.pricegroup}</td>
					<td id="memberId${loop.count}">${memberDtlVO.id}</td>
					<td hidden id="phonewhatsapp${loop.count}">${memberDtlVO.phonewhatsapp}</td>
					<td hidden id="company${loop.count}">${memberDtlVO.company}</td>
			        <td hidden id="dob${loop.count}">${memberDtlVO.DOB}</td>
					<td id="ctype${loop.count}">${memberDtlVO.ctype}</td>
					<td id="phonealter${loop.count}">${memberDtlVO.phonealter}</td>
					<td id="phonemain${loop.count}">${memberDtlVO.phonemain}</td>
					<td id="email${loop.count}">${memberDtlVO.email}</td>
					<td id="deposit${loop.count}" contenteditable="true" class="deposit"><fmt:formatNumber pattern="#,###.##" value="${balance}" /></td>
					<td id="creditpayment${loop.count}">${memberDtlVO.creditpayment}</td>
					<td id="creditfacility${loop.count}">${memberDtlVO.creditfacility}</td>
					<td hidden id="pin${loop.count}">${memberDtlVO.pincode}</td>
					<td hidden id="address${loop.count}">${memberDtlVO.address}</td>
					<td  id="threshholdamount${loop.count}">${memberDtlVO.threshholdamount}</td>
					<td  id="threshholddays${loop.count}">${memberDtlVO.threshholddays}</td> 
					<td hidden id="blocked${loop.count}">${memberDtlVO.blocked}</td> 
					<td  hidden id="contactname${loop.count}">${memberDtlVO.contactname}</td> 							
					<td> 
            <div class="dropdown">
              <button style="padding: 0 !important;" class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="material-icons">
                  more_vert
                  </span>
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="margin-left:120px;">
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#modal-lg" onclick="EditMemberDetails(${loop.count})">Edit Member</a>
               <!-- <a class="dropdown-item" href="${pageContext.request.contextPath}/getStatementbymemberId?MemberId=${memberDtlVO.id}" >Customer Statement</a>-->
                <a class="dropdown-item" href="${pageContext.request.contextPath}/getcustomerbymemberId?MemberId=${memberDtlVO.id}&Ctype=${memberDtlVO.ctype}">Customer Report</a>
              </div>
            </div>
            
            <!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-lg" onclick="EditMemberDetails(${loop.count})">Edit</button> -->
          </td>
					<!-- <td> <a href="${pageContext.request.contextPath}/getStatementbymemberId?MemberId=${memberDtlVO.id}" target="_blank" class="btn btn-primary" >Statement</a></td>
					<td> <a href="${pageContext.request.contextPath}/getcustomerbymemberId?MemberId=${memberDtlVO.id}" target="_blank" class="btn btn-primary" >Customer Report</a></td> -->
					
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
            <div class="modal-header">
              <h4 class="modal-title">Edit Customer Details</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
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
									<label for="exampleInputEmail1">Username</label> <input
										type="text" class="form-control" name="userName" id="userName"
										placeholder="Enter User Name" Required>
								</div>
                  <div class="form-group">
                    <label for="exampleInputPassword1"hidden>Member ID</label>
                    <input type="hidden"   class="form-control" name="id" id="memberId" >
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">DOB</label>
                    <input type="text"  class="form-control" name="DOB" id="dob" placeholder="Enter DOB">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Company Name</label>
                    <input type="text"   class="form-control" name="company" id="company" placeholder="Company Name">
                  </div>

					<div class="form-group">
						<label for="exampleInputPassword1">Address</label> <input
							type="text" class="form-control" name="address" id="address"
							placeholder="Address">
					</div>
					<div class="form-group">
					<label for="exampleInputPassword1">TIN Code</label> <input
						type="number" class="form-control" name="pincode" id="pin"
						placeholder="pincode" >
				
                  <div class="form-group">
					<label for="exampleInputEmail1">Customer Type</label> 
					<select required class="form-control select2bs4" name="ctype" id="ctype" style="width: 100%;"  >
								 	<option value="">Please Select Customer Type</option> 
										<option value="Special">Special</option>
										<option value="WholeSellers">WholeSellers</option>
										<option value="General">General</option>
					</select>
                  </div>
                  <div class="form-group">
									<label for="exampleInputEmail1">Price Group</label> <select required
										class="form-control select2bs4" name="pricegroup" id="pricegroup"
										style="width: 100%;"  >
								 	<option value="">Please Select Price Group</option> 
										<option value="Special">Special</option>
										<option value="WholeSellers">WholeSellers</option>
										<option value="General">General</option>
										<option value="TaxExemption">TaxExemption</option>
									</select>
								</div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Whatsapp 1</label>
                    <input type="text" class="form-control" name="phonealter"   id="phonealter" placeholder="Whatsapp 1">
                  </div>
                   <div class="form-group">
                    <label for="exampleInputPassword1">Phone number</label>
                    <input type="text" class="form-control" name="phonemain"   id="phonemain" placeholder="phonemain">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Email</label>
                    <input type="text" class="form-control" name="email" id="email" placeholder="firstemail">
                  </div>
                    <div class="form-group">
									<label for="exampleInputEmail1">Credit Facility</label> <select
										class="form-control select2bs4" name="creditfacility" id="creditfacility"
										style="width: 100%;">
										<option val="">Please Select Credit Facility</option>
										<option value="YES">YES</option>
										<option value="NO">NO</option>
									</select>
								</div>
							
                  
                 
                   <div class="form-group">
                    <label for="exampleInputPassword1">Deposit</label>
                    <input type="number" class="form-control" name="deposit"  id="deposit" placeholder="deposit" readonly>
                  </div>
                   
                    <div class="form-group">
                    <sec:authorize access="hasAuthority('admin') ">
                    <label for="exampleInputPassword1">Threshholdamount</label>
                    <input type="number" class="form-control" name="threshholdamount"  id="threshholdamount" placeholder="threshholdamount" >
                    </sec:authorize>
                  </div>
                  
                   <div class="form-group">
                   <sec:authorize access="hasAuthority('admin') ">
                    <label for="exampleInputPassword1">Threshholdday</label>
                    <input type="number" class="form-control" name="threshholddays"  id="threshholddays" placeholder="threshholddays" >
                    </sec:authorize>
                  </div> 
                  	<div class="form-group">
									<label for="exampleInputEmail1">Contact Name</label> <input
										type="text" class="form-control" name="contactname" id="contactname"
										placeholder="Add Contact name" >
								</div>
                   <div class="form-group">
                   <sec:authorize access="hasAuthority('admin') ">
                    <label for="exampleInputEmail1">Block Customer</label>
                    <select class="form-control select2bs4" name="blocked" id="blocked" style="width: 100%;">
										<option value="1">YES</option>
										<option value="0">NO</option>
									</select>
                    </sec:authorize>
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
                <span aria-hidden="true">&times;</span>
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
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
      });
    });
    
    
    
    function EditMemberDetails(count){
        $("#userName").val($("#userName"+count).text());
        $("#name").val($("#name"+count).text());
        $("#dob").val($("#dob"+count).text());
        $("#company").val($("#company"+count).text());
        $("#secondemail").val($("#secondemail"+count).text());
        $("#phonealter").val($("#phonealter"+count).text());
        $("#phonemain").val($("#phonemain"+count).text());
        $("#phonewhatsapp").val($("#phonewhatsapp"+count).text());
        $("#deposit").val($("#deposit"+count).text());
        $("#email").val($("#email"+count).text());
        $("#company").val($("#company"+count).text());
        $("#child2").val($("#child2"+count).text());
        $("#child2age").val($("#child2age"+count).text());
        $("#parentname").val($("#name"+count).text());
        $("#userNamepp").val($("#userName"+count).text());
        $("#pin").val($("#pin"+count).text());      
        $("#creditfacility").val($("#creditfacility"+count).text());
        $("#pricegroup").val($("#pricegroup"+count).text());
        $("#memberId").val($("#memberId"+count).text());
        $("#address").val($("#address"+count).text());
        $("#threshholdamount").val($("#threshholdamount"+count).text());
       $("#threshholddays").val($("#threshholddays"+count).text());
       $("#blocked").val($("#blocked"+count).text());
       $("#contactname").val($("#contactname"+count).text());
       
        
        
       
     //   $("#ctype").val($("#ctype"+count).text());
        
        var ctype = $("#ctype"+count).text();
		//alert(ctype);

		if (ctype == "Special") {
			$("#ctype option:contains(Special)").attr(
					'selected', true);
			$("#ctype option:contains(WholeSellers)").attr(
					'selected', false);
			$("#ctype option:contains(General)").attr(
					'selected', false);
		} else if (ctype == "WholeSellers") {
			$("#ctype option:contains(WholeSellers)").attr(
					'selected', true);
			$("#ctype option:contains(Special)").attr(
					'selected', false);
			$("#ctype option:contains(General)").attr(
					'selected', false);
		} else {
			$("#ctype option:contains(General)").attr(
					'selected', true);
			$("#ctype option:contains(Special)").attr(
					'selected', false);
			$("#ctype option:contains(WholeSellers)").attr(
					'selected', false);

		}
		
		var blocked=  $("#blocked"+count).text();
		if (blocked == "0") {
			$("#blocked option:contains(No)").attr(
					'selected', true);
			$("#blocked option:contains(Yes)").attr(
					'selected', false);
			
		} else {
			$("#blocked option:contains(Yes)").attr(
					'selected', true);
			$("#blocked option:contains(No)").attr(
					'selected', false);
		
		}
           
   	  }
    function searchTable() {
	    var input, filter, found, table, tr, td, i, j;
	    input = document.getElementById("myInput");
	    filter = input.value.toUpperCase();
	    table = document.getElementById("example2");
	    tr = table.getElementsByTagName("tr");
	    for (i = 0; i < tr.length; i++) {
	        td = tr[i].getElementsByTagName("td");
	        for (j = 0; j < td.length; j++) {
	            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
	                found = true;
	            }
	        }
	        if (found) {
	            tr[i].style.display = "";
	            found = false;
	        } else {
	            tr[i].style.display = "none";
	        }
	    }
	}
    $("#example2 tbody tr .deposit").on("blur",function(e){
  	  var customerId = $(this).closest("tr").find("td:eq(4)").text();
  	  var deposit = $(this).text();
  	  //alert(customerId);
  	//alert(deposit);
  	  
  	  $.ajax({
  			url : '${pageContext.request.contextPath}/addDeposit',
  			type : "POST",
  			contentType: "application/json",
  			dataType : "json",
  			data : JSON.stringify({
  				id:customerId,
  				deposit:deposit
  				}),
  			success : function(data) {
  				var ajaxCallData = JSON.stringify(data);
  			},
  			error : function(error) {
  				console.log(`Error ${error}`);
  			}

  		});
  	  
    });

   
    
    </script>
</body>
</html>