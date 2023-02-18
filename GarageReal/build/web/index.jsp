<%-- 
    Document   : index
    Created on : 27 janv. 2023, 14:29:35
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="style/stylelogin.css">
        <link rel="stylesheet" href="fontawesome-5/css/all.css">
    </head>
    <body>
        <div class="container"> 

        <div class="form-box">
            <h1>Login</h1>
            <form action="LoginServlet" method="post">
                <div class="input-group">
                    <div class="input-field">
                        <i class="fa fa-envelope"></i>
                        <input type="email" placeholder="email" name="email">
                    </div>

                    <div class="input-field">
                        <i class="fa fa-lock"></i>
                        <input type="password" placeholder="password" name="password">
                    </div>
                    
                    <div class="btn-field">
                        <input type="submit" value="connexion">
                    </div>    
                </div>
            </form>
           <p>Inscription <a href="#">Click Here!</a></p> 
        </div>

    </div>
    </body>
</html>
