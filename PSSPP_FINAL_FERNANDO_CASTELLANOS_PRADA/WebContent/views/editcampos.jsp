<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dam2.add.p22.model.Usuario"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dam2.add.p22.model.Provincia"%>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<title><%=rb.getString("editarCampos")%></title>
</head>
<body>

	<div class="container">
	
		<%@ include file="cabecera.jsp"%>   
		
		<%Usuario user = (Usuario) request.getSession().getAttribute("usuario");%>
		
		     
		     <div class="row mx-auto">
			     <form class="row mx-auto justify-content-center align-items-center" action="<%=request.getContextPath()%>/" method="post">
				     <div class="col-xl-6 justify-content-center align-items-center">
			             <label class="form-label"><%=rb.getString("nombre")%></label>
			             <input class="form-control" type="text" required name="nombreEdit" placeholder="<%=rb.getString("nombre")%>" value="<%=user.getNombre()%>"><br>
			         </div>
			         <div class="col-xl-6 justify-content-center align-items-center">
                         <label class="form-label"><%=rb.getString("apellidos")%></label>
                         <input class="form-control" type="text" required name="apellidoEdit" placeholder="<%=rb.getString("apellidos")%>" value="<%=user.getApellidos()%>"><br>
                     </div>
			          <div class="col-xl-6 justify-content-center align-items-center">
                         <label class="form-label"><%=rb.getString("email")%></label>
                         <input class="form-control" type="email" required name="emailEdit" placeholder="<%=rb.getString("email")%>" value="<%=user.getEmail()%>"><br>
                     </div>
			         <% String error = (String) request.getSession().getAttribute("error");
			         if (error != null){
			        	 %><p><%=rb.getString("existeEmail")%></p><% 
			         }%>
			         <div class="col-xl-6 justify-content-center align-items-center">
                         <label class="form-label"><%=rb.getString("telefono")%></label>
                         <input class="form-control" type="text" required name="telefonoEdit" placeholder="<%=rb.getString("telefono")%>" value="<%=user.getTelefono()%>"><br>
                     </div>
                     <div class="col-md-6">
                      <label class="form-label"><%=rb.getString("provincia")%></label>
                        <select class="form-control" name="provinciaEdit" id="provincia">
                           <% ArrayList<Provincia> provincias =  (ArrayList<Provincia>) request.getSession().getAttribute("provincias");
                             int contadorProv = 0;
                           for (int i = 0; i < provincias.size(); i++) { 
                              if(contadorProv == 0){%>
                           	  <option value="<%=user.getProvincia()%>"><%=user.getProvincia()%></option>
                            <% contadorProv++;  }%>
                              <option value="<%=provincias.get(i).getId()%>"><%=provincias.get(i).getNm()%></option>
                          <% } %>
                        </select><br>
                      </div>
                      <div class="col-md-6">
                      <label class="form-label"><%=rb.getString("municipio")%></label>
                        <select class="form-control" name="municipioEdit">
                            <option value="<%=user.getMunicipio()%>"><%=user.getMunicipio()%></option>
                        </select><br>
                      </div>
			         <input class="barras" type="number" hidden=true name="id" value="<%=user.getId()%>"><br>
			         <input class="btn-success" type="submit" name="boton" value="<%=rb.getString("actualizarCampos")%>">
			     </form>
		     </div>
		
		<%@ include file="pie.jsp"%>   
	</div>
</body>
</html>
<script>

     $(document).ready(function(){
         
            $('#provincia').change(function() {
                var provincia = $(this).val();
                $.ajax({
                    url: 'https://raw.githubusercontent.com/IagoLast/pselect/master/data/municipios.json',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                    	
                    	var opciones = "";
                    	
                    	for (var i = 0; i<data.length; i++){
                    		
                    		if(data[i].id.substring(0,2) === (provincia)){
                    			opciones += '<option value="' + data[i].nm +'">'+data[i].nm+'</option>';

                    		}
                    	}
                  
                    	$('select[name="municipioEdit"]').html(opciones);
                        
                         console.log(data);

                    },
                    error: function(xhr, status, error) {
                        console.log("Error");
                        console.error(xhr.responseText);
                    }
                });
            });
 
     });
     
     

</script>

