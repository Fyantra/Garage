<%-- 
    Document   : reparation
    Created on : 27 janv. 2023, 14:36:56
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Depense.Reparation"%>

<% 
    Reparation inirep=new Reparation();
    Reparation[] listereparation=Reparation.bddListToReparation(inirep.select(null));
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reparation</title>
        <link rel="stylesheet" href="style/stylereparation.css">
        <link rel="stylesheet" href="fontawesome-5/css/all.css">
    </head>
    <body>
        <div class="container"> 

        <div class="form-box">
            <h1>Reparation</h1>

            <div class="search">
                <input type="search" placeholder="search">
                <i class="fa fa-search"></i>
            </div>

            <form action="#">
                <div class="input-group">
                    <div class="bloc">
                    <% for(int i=0;i<9;i++){ %>
                        <div class="wrapper">
                            <table>
                                <input type="checkbox" class="check">
                                <label for="ckeck"><% out.print(listereparation[i].getReparation()); %></label>
                            </table>
                        </div>
                    <% } %>
                    </div>
                    
                    
                    <div class="bloc">
                        <% for(int i=9;i<18;i++){ %>
                            <div class="wrapper">
                                <input type="checkbox" class="check">
                                <label for="ckeck"><% out.print(listereparation[i].getReparation()); %></label>
                            </div>
                        <% } %>
                    </div>
                    
                    <div class="btn-field">
                        <input type="submit" value="Valider">
                        <button>Devie</button>
                    </div>                    
                   
                </div>
            </form>
        </div>

    </div>

    </body>
</html>
