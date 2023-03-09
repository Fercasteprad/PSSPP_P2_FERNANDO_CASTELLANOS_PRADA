<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
    <% 
    Locale localec;
    if(session.getAttribute("idioma") == null){
      localec = request.getLocale();
    } else {
      localec = new Locale((String)session.getAttribute("idioma"));
    }
    ResourceBundle rbc = ResourceBundle.getBundle("idioma", localec);
    %>    
     <%String usuario = (String) request.getSession().getAttribute("nombre");
     
     if (usuario != null){%>
    	 
    	  <div class="row bg-light">
	            <div class="col-xl-3 d-flex justify-content-center align-items-center">
	                <p><%=rbc.getString("hola")%> 
	                <a href="<%=request.getContextPath()%>/?sesion=edit"><%=usuario%></a></p>
	            </div>
	            <div class="col-xl-3 d-flex justify-content-center align-items-center">
                    <a href="<%=request.getContextPath()%>/views/home.jsp"><%=rbc.getString("inicio")%></a>
                </div>
	            <div class="col-xl-3 d-flex justify-content-center align-items-center">
	                <a href="<%=request.getContextPath()%>/?sesion=close"><%=rbc.getString("cerrarSesion")%></a>
	            </div>
	            <div class="col-xl-3 d-flex justify-content-center align-items-center">
                    <a href="<%=request.getContextPath()%>/?idioma=es">
                        <button class="btn btn-link">ES</button>
                    </a>
                    <a href="<%=request.getContextPath()%>/?idioma=en">
                        <button class="btn btn-link">EN</button>
                    </a>
                </div>
                
          </div>
    	 
     <%} else {%>
    	 
    	   <div class="row bg-light">
	            <div class="col-xl-4 d-flex justify-content-center align-items-center">
	                <a href="<%=request.getContextPath()%>/?view=login"><%=rbc.getString("entrar")%></a>
	            </div>
	            <div class="col-xl-4 d-flex justify-content-center align-items-center">
	                <a href="<%=request.getContextPath()%>/?view=form"><%=rbc.getString("alta")%></a>
	            </div>
                 <div class="col-xl-4 d-flex justify-content-center align-items-center">
                    <a href="<%=request.getContextPath()%>/?idioma=es">
                        <button class="btn btn-link">ES</button>
                    </a>
                    <a href="<%=request.getContextPath()%>/?idioma=en">
                        <button class="btn btn-link">EN</button>
                    </a>
                </div>
           </div>
     <%}
     %>
    

