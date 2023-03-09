<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page import="dam2.add.p22.model.Cuenta"%>
    <% 
    Locale localeg;
    if(session.getAttribute("idioma") == null){
      localeg = request.getLocale();
    } else {
      localeg = new Locale((String)session.getAttribute("idioma"));
    }
    ResourceBundle rbg = ResourceBundle.getBundle("idioma", localeg);
    %>    
     <%Cuenta cuentaUsuario = (Cuenta) request.getSession().getAttribute("cuenta");
     
     if (cuentaUsuario != null){%>
    	  
    	  <div style="height: 20px;"></div>
    	  <div class="row bg-light">
	            <div class="col-xl-12 d-flex justify-content-center align-items-center">
	                <h3><%=rbg.getString("dineroDisponible")%> 
	                </h3>
	            </div>
                <div class="col-xl-12 d-flex justify-content-center align-items-center">
                    <h1><%=cuentaUsuario.getDinero()%> 
                    </h1><h4> Euros </h4>
                </div>
          </div>
          <br>
          <div class="row bg-light">
                <div class="col-xl-12 d-flex justify-content-center align-items-center">
                    <h3><%=rbg.getString("operacionesDisponibles")%> 
                    </h3>
                </div>
                <div class="bg-light" style="height: 20px;"></div>
           </div>
           
           <div class="row bg-light">
            <div class="col-xl-6 d-flex justify-content-center align-items-center">
                <div class="row bg-light">
                <div class="col-xl-12 d-flex justify-content-center align-items-center">
	                <h3><%=rbg.getString("enviarDinero")%> 
	                </h3>
	            </div>
	            <form class="justify-content-center align-items-center" action="<%=request.getContextPath()%>/" method="get">
	              <div class="col-xl-12 d-flex justify-content-center align-items-center">
	                     <label class="form-label"><%=rbg.getString("emailIngreso")%></label>
	                     <input class="form-control" type="text" required name="emailIngreso" placeholder="<%=rbg.getString("email")%>" value=""><br>
	               </div><br>
	                 <div class="col-xl-12 d-flex justify-content-center align-items-center">
	                     <label class="form-label"><%=rbg.getString("dineroAenviar")%></label>
	                     <input class="form-control" type="number" required name="dineroTransferencia" placeholder="<%=rbg.getString("dineroAenviar")%>" value=""><br>
	                 </div><br>
	                  <div class="col-xl-12 d-flex justify-content-center align-items-center">
	                     <input class="btn-success" type="submit" name="boton" value="<%=rbg.getString("enviar")%>">
	                 </div>
	             </form>
                 <% String mensaje = (String) request.getSession().getAttribute("error");
                 
                 if(mensaje != null){
                     
                     %> <p><%=mensaje %></p><% 
                     
                 }
                 
                 %>
	            </div>
           </div>
           <div class="col-xl-6 d-flex justify-content-center align-items-center">
                <div class="row bg-light">
                <div class="col-xl-12 d-flex justify-content-center align-items-center">
                    <h3><%=rbg.getString("ingresarDinero")%> 
                    </h3>
                </div>
                <form class="justify-content-center align-items-center" action="<%=request.getContextPath()%>/" method="get">
                  <div class="col-xl-12 d-flex justify-content-center align-items-center">
                         <label class="form-label"><%=rbg.getString("dineroAingresar")%></label>
                         <input class="form-control" type="number" required name="dineroIngreso" placeholder="<%=rbg.getString("ingreso")%>" value=""><br>
                   </div><br>
                      <div class="col-xl-12 d-flex justify-content-center align-items-center">
                      <input class="btn-success" type="submit" name="boton" value="<%=rbg.getString("enviar")%>">
                  </div>
                 </form>

                </div>
           </div>
           <div class="bg-light" style="height: 20px;"></div>
          </div>
          
    	 
     <%} %>
    

