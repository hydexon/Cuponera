<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Simple Login Form</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap.bundle.js"></script>
<style type="text/css">
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>
</head>
<body>
<div class="login-form">
    <form action="LoginAuth" method="post">
        <h2 class="text-center">Restaurar Contraseña</h2>       
        <div class="form-group">
            <input type="email" name="email" class="form-control" placeholder="Correo" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Enviar Restaurar Contraseña</button>
        </div>
    </form>
</div>
</body>
</html>                                		                            