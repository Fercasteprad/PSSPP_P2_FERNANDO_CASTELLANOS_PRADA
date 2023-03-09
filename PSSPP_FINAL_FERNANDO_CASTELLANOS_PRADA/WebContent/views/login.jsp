<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
        <% 
        Locale locale;
        if(session.getAttribute("idioma") == null){
          locale = request.getLocale();
        } else {
          locale = new Locale((String)session.getAttribute("idioma"));
        }
        ResourceBundle rb = ResourceBundle.getBundle("idioma", locale);
        %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<title>Login</title>
</head>
<body>
    <div class ="container">
        <form class="row" action="<%=request.getContextPath()%>/" method="post">
            <div class="col-md-6">
            <label class="form-label"><%=rb.getString("introduceUser")%></label>
            <input class="form-control" type ="text" id="name" name="user" placeholder="<%=rb.getString("introduceUser")%>"><br>
            </div>
            <div class="col-md-6">
            <label class="form-label"><%=rb.getString("contrasena")%></label>
            <input class="form-control" type ="password" id= "pass01" name="passLogin" placeholder="<%=rb.getString("contrasena")%>"><br>
            </div>
            <!--  <input class="barras" type ="password" id = "pass02" name="pass2" placeholder="Introduce de nuevo la contraseña"><br> -->
            <div class ="col-md-4">
                <input class ="btn-success col-md-4" id="boton" type ="submit" name="boton" value="<%=rb.getString("enviar")%>">
            </div><br>
        </form>


       <%
       String mensajeError = (String) request.getAttribute("mensajeContrasenaIncorrecta");
       if (mensajeError != null) {
    	  
    	   %><div><%=rb.getString("contraIncorrecta")%></div>
    	   <%
       }
       %>
       
    <%@ include file="pie.jsp"%>     
    </div>
     
      
</body>
</html>
