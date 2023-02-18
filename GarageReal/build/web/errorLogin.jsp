<%-- 
    Document   : errorLogin
    Created on : 19 janv. 2023, 09:50:46
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    Exception e = (Exception) session.getAttribute("error"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <body>
        <header>
            <div class="row">
                <div class="header">
                    <div class="col-md-3">
                        <div class=headleft>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="headcenter">
                            <div>
                                <h3>Error</h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="headright">
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div id="boite1">
            <p>
                <h4>Erreur de login...veuillez reessayer : </h4>
            </p>
            <p>
                <h6><% out.print(e.getMessage()); %></h6>
            </p>
        </div>
            <a href="index.jsp"><button  id="submit-btn">Reessayer</button></a>
    </body>
</html>
