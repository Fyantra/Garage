<%-- 
    Document   : insertionEmployer
    Created on : 27 janv. 2023, 14:19:45
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="personne.Specialite"%>
<%@page import="personne.Genre"%>
<%
    Genre inigenre=new Genre();
    Genre[] listegenre=Genre.bddListToGenre(inigenre.select(null));
  
    Specialite inispec=new Specialite();
    Specialite[] listespecialite=Specialite.bddListToSpecialite(inispec.select(null));   
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertion Employer</title>
        <link rel="stylesheet" href="style/styleInsertionEmployer.css">
        <link rel="stylesheet" href="fontawesome-5/css/all.css">
    </head>
    <body>
        <div class="container"> 

        <div class="form-box">
            <h1>Insertion Employer</h1>
            <form action="AjoutEmp" method="post">
                <div class="input-group">
                    <div class="input-field">
                        <input type="text" placeholder="nom" name="nom">
                    </div>

                    <div class="input-field">
                        <input type="text" placeholder="prenom" name="prenom">
                    </div>

                    <div class="input-field">
                        <input type="text" placeholder="date de naissance" onfocus="(this.type='date')" name="dtn">
                    </div>

                    <div class="input-field">
                        <select name="genre" id="">
                            <% for(int i=0;i<listegenre.length;i++){ %>
                                <option value="<% out.print(listegenre[i].getIdGenre()); %>"><% out.print(listegenre[i].getGenre()); %></option>
                            <% } %>
                        </select>
                    </div>
                        
                    <div class="input-field">
                        <input type="text" placeholder="adresse" name="adresse">
                    </div>
                        
                     <div class="input-field" name="contact">
                        <input type="text" placeholder="contact" name="contact">
                    </div>
                        
                    <% for(int i=0;i<4;i++){ %>    
                    <label class="box"><% out.print(listespecialite[i].getSpecialite()); %>
                                <input type="checkbox" checked="checked" name="<% out.print(listespecialite[i].getIdSpecialite()); %>" value="<% out.print(listespecialite[i].getIdSpecialite()); %>">
                                 <span class="checkmark"></span>
                            </label> 
                   <% } %>
                    
                    <div class="btn-field">
                        <input type="submit" value="Ajouter">
                    </div>    
                </div>
            </form>
        </div>

    </div>
    </body>
</html>
