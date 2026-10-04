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
	<style> @media screen {
  #printSection {
      display: none;
  }
}

@media print {
  body * {
    visibility:hidden;
  }
  #printSection, #printSection * {
    visibility:visible;
  }
  #printSection {
    position:absolute;
    left:0;
    top:0;
  }
}
</style>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">BarCode Details</h3>
              </div> 
			 <!-- /.card-header -->
              <div class="card-body">
              <form  method="GET" action="${pageContext.request.contextPath}/getBarCodeList" autocomplete="off"
					modelAttribute="BarCodePojo" name="BarCodePojo">
                <div class="card-body">
                <div class="form-group">
                  <label>Select Category</label>
                  <select class="form-control select2bs4"  name="catagoryId" id="catagoryList" style="width: 100%;">
                   
                  </select>
                </div>
                <div class="form-group">
                  <label>Select SubCategory</label>
                  <select class="form-control select2bs4"  name="subCatagoryId" id="subcatagoryList" style="width: 100%;">
                   
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
                    <th>BarCode</th>
                    <th>ItemCode</th>
                    <th>SubCode</th>
                    <th>CategoryCode</th>
                    <th>Image</th>
                    <th>View Bar Code</th>
                    <th>Delete</th>
                  </tr>
                  </thead>
                  <tbody>
                 <c:forEach var="barCodePojo" items="${barCodePojo}" varStatus="loop">
                 <tr id="${loop.count}">
					<td>${loop.count}</td>
					<td id="barCodeId${loop.count}">${barCodePojo.barCodeId}</td>
					<td id="productCode${loop.count}">${barCodePojo.productCode}</td>
					<td id="subCatCode${loop.count}">${barCodePojo.subCatCode}</td>
					<td id="catCode${loop.count}">${barCodePojo.catCode}</td>
					<td>
					
					<img style='display:block;height:100px;' id='base64image' src="${pageContext.request.contextPath}/barbecue/ean13/${barCodePojo.barCodeId}"/>
				   
					
 				   </td>
 				    <td> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-bp" data-barCodeId="${barCodePojo.barCodeId}" onclick="viewBarcode(${loop.count},'${pageContext.request.contextPath}/barbecue/ean13/${barCodePojo.barCodeId}')">Print Bar Code</button></td>
 					<td> <button type="button" class="btn btn-primary" onclick="deleteDetails(${loop.count},)">Delete</button></td>
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
    
        <!-- /Model for printing Bar code -->
       
       <div class="modal fade" id="modal-bp">
        <div class="modal-dialog modal-bp">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Print Bar Code</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <!-- general form elements -->
              <!-- form start -->
                <form  method="POST" autocomplete="off"
					modelAttribute="BarCodePojo1" name="BarCodePojo1" enctype="multipart/form-data">
                <div class="card-body">
                 <div id="printThis">
                <div class="form-group" style="display:flex;">
				  <div class="col-md-10" >
                <img   id= "barcodeimg" style='display:block; height:100px;' src="" /> 
				
				 </div>
				 <div class="col-md-2">
				  <label style="writing-mode: vertical-rl; text-orientation:mixed; padding-left:30px; font-size:14px;"> Being Kids Library</label>
                  </div>
				  </div>
                  <div class="form-group" style="display:flex; font-size:18px;">
                     <div class="col-md-12">
                    <label style="margin-bottom: 0;">Item : Winneie the Pooh- Piglet is rescued</label>
					
                  </div>
                  
                   
                  </div>
				  
				   <div class="form-group" style="display:flex; font-size:18px;">
                     <div class="col-md-6">
					 <label style="margin-bottom: 0;">Pieces :<b>10 </b></label>
                   
                  </div>
                  
                     <div class="col-md-6" style="text-align:center;">
                    <label style="margin-bottom: 0;">Br000012</label>
					
				  </div>
				  </div>
				  
				   <div class="form-group" style="display:flex; font-size:18px;">
                     <div class="col-md-6">
                    <label style="margin-bottom: 0;"> PTS: <b>2 </b></label>
				  
                  </div>
                  
                     <div class="col-md-6" style="text-align:center;">
                     <label style="margin-bottom: 0;">MRP :<b>100/- </b></label>
				  </div>

                  </div>
                 
               
                  </div>
				  </div>
            	<div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
           
			<button id="btnPrint" type="button" class=" printPage,btn btn-default">Print</button>
      
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
  function deleteDetails(count){
	  var x = confirm("Are you sure you want to delete?");
      if (x) { 
	  var barCode = $("#barCodeId"+count).text();
	  $("#"+count).remove();
    $.ajax({
		url : '${pageContext.request.contextPath}/deleteBarCodeDetails',
		type : "POST",
		dataType : "json",
		data:{barCode:barCode},
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
 

  function viewBarcode(count, barcodeImgUrl){
	  console.log(barcodeImgUrl);
	  
      $("#modal-bp").find("#barcodeimg").attr('src', barcodeImgUrl);
	
       
 	  }
  


$('button.printPage').click(function(){
           window.print();
           return false;
});


document.getElementById("btnPrint").onclick = function () {
    printElement(document.getElementById("printThis"));
}

function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    $printSection.appendChild(domClone);
    window.print();
}
</script>
 
</body>
</html>
