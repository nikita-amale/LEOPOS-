<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>LeoPOS_spv1.0</title>

  <!-- Favicon -->
  <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/Leonet_Fav.png">

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<style>

.login-box-msg {
    font-size: 16px; /* Adjust font size for smaller screens */
  }

/* Styles for mobile screens */
@media (max-width: 767px) {
  .container {
    margin-bottom: 4rem ;/* Adjust margin for smaller screens */
    max-width: 310px;
  }
  .col-lg-6 {
    padding: 2rem;
  }
  .login-logo img {
    width: 250px; /* Adjust logo size for smaller screens */
  }
  .login-logo{
	  margin-left: -15px;
  }
  .login-box-msg {
    font-size: 12px; /* Adjust font size for smaller screens */
  }
  .input-group {
    margin-bottom: 1rem;
  }
}
</style>

<body class=" login-page">
 
  <!-- /.login-logo -->
<div class="container">
  <div class="row  justify-content-center" style="margin-top: 8rem;">
    <div class="col-lg-6 col-md-8 col-sm-12 p-5 bg-white rounded">
      <div class="login-logo">
        <a href="#"><b><img src="${pageContext.request.contextPath}/resources/images/logo.png" width="350px"></b></a>
      </div>
      <p class="login-box-msg">SIGN IN TO START YOUR SESSION</p>
      <form name='login' action="${pageContext.request.contextPath}/login" method='POST'>
        <div class="input-group mb-3">
          <input type="text" class="form-control" name="username" placeholder="USERNAME" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" class="form-control" name="password" placeholder="PASSWORD" >
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <!-- /.col -->
          <div class="col">
            <button type="submit" style="border-radius: 4px !important;" class="btn btn-primary btn-block">SIGN IN</button>
          </div>
          <!-- /.col -->
        </div>
        
        <div class="form-group">
					<c:if test="${not empty errorMessge}">
			<div style="color: red; font-weight: bold;">${errorMessge}</div>
		</c:if>
					</div>
      </form>
 
      <p class="mb-1 text-center">
        <a href="${pageContext.request.contextPath}/forgotPassword">I forgot my password</a>
      </p>
    </div>
  </div>
</div>  
 
  
<!-- /.login-box -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<script>
localStorage.removeItem("product");
</script>
</body>
</html>
