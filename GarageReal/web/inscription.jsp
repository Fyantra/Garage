<%-- 
    Document   : inscription
    Created on : 27 janv. 2023, 14:18:13
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/styleinscription.css">
        <link rel="stylesheet" href="fontawesome-5/css/all.css">
        <title>inscription</title>
    </head>
    <body>
        
        <div class="container"> 

        <div class="form-box">
            <h1>Inscription</h1>
            <form action="InscriptionClientServlet" method="post">
                <div class="input-group">
                    <div class="input-field">
                        <i class="fa fa-envelope"></i>
                        <input type="email" placeholder="email" name="Email">
                    </div>

                    <div class="input-field">
                        <i class="fa fa-lock"></i>
                        <input type="password" placeholder="password" name="Motdepasse">
                    </div>
                    
                    <div class="btn-field">
                        <input type="submit" value="s'inscrire">
                    </div>    
                </div>
            </form>
        </div>

    </div>

    </body>
</html>
