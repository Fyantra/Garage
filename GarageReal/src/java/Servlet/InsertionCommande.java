/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.BddObject;
import personne.Employer;
import personne.Specialite;
import personne.SpecialiteEmployer;
import Depense.*;
import Personne.*;
import Remise.*;

/**
 *
 * @author ITU
 */
public class InsertionCommande extends HttpServlet {

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
            out.println("<title>Servlet InsertionCommande</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertionCommande at " + request.getContextPath() + "</h1>");
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
     
            String idClient=(String)req.getAttribute("idClient");
            
            //Commande c = new Commande( nom, prenom,adresse,contact, genre, dtn);
            Date date = new Date(); //date du jour de commande
            
            
            Reparation s = new Reparation();
            BddObject [] resultSelect = s.select(null); 
            for (BddObject bddObject : resultSelect) {
                Reparation rep = (Reparation) bddObject;
                String idrep=req.getParameter(rep.getIdReparation());           //value a partir du checkbox
                if(idrep!=null){
                    
                    Commande newComm=new Commande(idClient,date); 
                    Commande c = newComm.getNewCommande();
                    
                    newComm.insertCommande(null);
                    
                    QuantiteCommande qc = new QuantiteCommande(idrep , c.getIdCommande());
                    qc.insertQuantCommande(null);
                    
                    Client cl = new Client();
                    RemiseCommande rm = new RemiseCommande(c.getIdCommande(),50);
                    cl.setId(idClient);
                    if(cl.isAnniversaire(date) == true){
                               rm.insertRemisecommande(null);
                    }
                    
                    RequestDispatcher dispat = req.getRequestDispatcher("/listeCommande.jsp");
                    dispat.forward(req, res);
                }
                else{
                //session.setAttribute("idEmployer", 0);
                RequestDispatcher dispat = req.getRequestDispatcher("/reparation.jsp");   
                dispat.forward(req, res);
            }
            }
          
        }catch (Exception e) {
            //session.setAttribute("error", e);
            
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
