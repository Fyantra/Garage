<%-- 
    Document   : gestionvente
    Created on : 27 janv. 2023, 14:12:38
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jdbc.BddObject"%>
<%@page import="Depense.Piece"%>

<% 
    Piece inipiece=new Piece();
    BddObject[] listepiece=inipiece.select(null);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="style/stylegestionvente.css">
        <title>Piece</title>
    </head>
    <body>
            
            <div class="container">
        <div class="form-box">
            <h1>Vente</h1>
            <form action="PieceServlet" method="get">
                <div class="input-field">
                    <select name="piece" id="">
                        <% 
                            for(int i=0;i<listepiece.length;i++){ 
                                Piece piece=(Piece) listepiece[i];
                        %>
                            <option value="<% out.print(piece.getIdPiece()); %>" ><% out.print(piece.getNomPiece()); %></option>
                        <% } %>
                    </select>
                </div>
                
                <div class="btn-field">
                    <input type="submit" value="Ajouter">
                </div>    
            </form>
        </div>
    </div>
    </body>
</html>
