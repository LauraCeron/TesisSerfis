/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import BEAN.BeanArea;
import BEAN.BeanMateria;
import MODEL.clsArea;
import static MODEL.clsArea_.codArea;
import MODEL.clsMateria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lau Cerón
 */
public class datMateria extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       
       //tomar los datos del formulario jsp
       String nombreMateria= request.getParameter("nombreMateria");
       Boolean estado= Boolean.parseBoolean(request.getParameter("estado"));
       String codArea= request.getParameter("cmbArea");
       
       //obtener cod area
       clsArea area= new clsArea();
       area.setCodArea(codArea);
       
        clsMateria materia = new clsMateria();
        materia.setCodMateria("0000000002");
        materia.setCodArea(area);
        materia.setNombreMateria(nombreMateria);
        
       // materia.setCodArea((clsArea) codArea);
        
       materia.setEstado(estado);
        BeanMateria beanMateria = new BeanMateria();
        try {
            beanMateria.guargar(materia);
        } catch (Exception ex) {
            Logger.getLogger(datArea.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet datMateria</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet datMateria at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
