<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
    <% 
    Locale localep;
    if(session.getAttribute("idioma") == null){
      localep = request.getLocale();
    } else {
      localep = new Locale((String)session.getAttribute("idioma"));
    }
    ResourceBundle rbp = ResourceBundle.getBundle("idioma", localep);
    %>    
       <div class="fixed-bottom row justify-content-center">
            <div class="col-4">
                <p><%=rbp.getString("hechopor")%></p>
            </div>
            <div class="col-4">
                <p><%=rbp.getString("registrado")%></p>
            </div>
       </div>

