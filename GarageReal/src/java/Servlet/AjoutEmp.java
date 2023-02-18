/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.servlet.*;
import javax.servlet.http.*;

import connexion.Connect;
import personne.*;
import jdbc.*;
/**
 *
 * @author ITU
 */
public class AjoutEmp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AjoutEmp</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjoutEmp at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
        
        res.setContentType("text/plain");

        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession();

        try {
            //nom prenom genre niveau specialite dtn
            String nom = req.getParameter("nom");   //1
            String prenom = req.getParameter("prenom"); //2
            String genre = req.getParameter("genre");   //3
            int intgenre = Integer.parseInt(genre); 
            //String niveau = req.getParameter("niveau");
           // int intniveau = Integer.parseInt(niveau);
            String dtStr = req.getParameter("dtn"); //4
            String adresse = req.getParameter("adresse");   //5
            String contact = req.getParameter("contact");   //6
        
            Date dtn=new SimpleDateFormat("yyyy-MM-dd").parse(dtStr);
            // System.out.println(dtStr+"\t"+date1);
            //Date dtn = new Date(dtStr);
            //Date dateEmbauche = new Date(); //7

            //public Employer(Integer idEmploye, String nom, String prenom,String adresse, String contact, Date dateNaissance, Integer idGenre, Date dateembauche)
            Employer e = new Employer( nom, prenom,adresse,contact, genre, dtn);
            
            if(e.isMajeur(dtn) == true){
            e.insertEmp(null); //insertion
            Employer newEmp = e.getNewEmp(); //select order by desc
            session.setAttribute("IdEmployer", newEmp.getIdEmployer());
            
            Specialite s = new Specialite();
            BddObject [] resultSelect = s.select(null); 
            for (BddObject bddObject : resultSelect) {
                Specialite sp = (Specialite) bddObject;
                String idspe=req.getParameter(sp.getIdSpecialite());
                if(idspe!=null){
                    out.print(newEmp.getIdEmployer());
                    SpecialiteEmployer newspec=new SpecialiteEmployer(newEmp.getIdEmployer(),sp.getIdSpecialite()); 
                    //newspec.insert(null);
                    newspec.insertSpecEmp(null);
                }   
            }
            
            RequestDispatcher dispat = req.getRequestDispatcher("/service.jsp");
            dispat.forward(req, res);
            }
            else{
                session.setAttribute("idEmployer", 0);
                RequestDispatcher dispat = req.getRequestDispatcher("/ajoutEmp.jsp");   //formulaire d`ajout de Employer
                dispat.forward(req, res);
            }
        }catch (Exception e) {
            //session.setAttribute("error", e);
            //res.sendRedirect("/Imprimerie/error.jsp");
            out.println(e.fillInStackTrace());
            out.print(e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
