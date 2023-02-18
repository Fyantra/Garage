<%-- 
    Document   : resultatvente
    Created on : 27 janv. 2023, 14:39:53
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Depense.Piece"%>
<%@page import="javax.websocket.Session"%>
<%
    String idpiece=(String)request.getAttribute("idPiece");
    Piece p=new Piece(); 
    Piece[] piececible=p.select(null,idpiece);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>resultatvente</title>
        <link rel="stylesheet" href="style/styleresultatvente.css">
    </head>
    <body>
        <div class="container">
        <div class="form-box">
                <h1>Devie Piece</h1>

                <div class="table">
                    <table border="1">
                        <tr class="tete">
                            <th>Nom</th>
                            <th>Prix d'achat</th>
                            <th>Prix de Vente</th>
                            <th>Benefice</th>
                            <th>%</th>
                        </tr>
                        <tr>
                            <td class="info"><% out.print(piececible[0].getNomPiece()); %></td>
                            <td class="info"><% out.print(piececible[0].getPrixAchat()); %></td>
                            <td class="info"><% out.print(piececible[0].getPrixVente()); %></td>
                            <td class="info"><% out.print(piececible[0].getBenefice()); %></td>
                            <td class="info"><% out.print(piececible[0].getPourcentage()); %></td>
                        </tr>
                    </table>
                </div>
        </div>
    </div>

    </body>
</html>
