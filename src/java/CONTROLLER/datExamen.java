/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import BEAN.BeanExamen;
import MODEL.clsExamen;
import MODEL.clsUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class datExamen extends HttpServlet {

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
        String tipoExamen= request.getParameter("tipoExamen");
        String descripcion= request.getParameter("descripcion");
        int numPreguntas=  Integer.parseInt(request.getParameter("numPreguntas"));
        String fechaInicio=  request.getParameter("fechaInicio");
        String fechaFin= request.getParameter("fechaFin");
        Date fInicio = null;
        Date fFin=null;
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fInicio = fechaHora.parse(fechaInicio);
            fFin = fechaHora.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(datExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //creacion clase usuario
        //clsUsuario usr= new clsUsuario();
        //usr.setCodUsuario("0000000001");
        //llenando el objeto examen
        clsExamen exa= new clsExamen();
        exa.setCodExamen("0000000003");
        //exa.setCodUsuario(usr);     falta por q viene del session   
        exa.setTipo(tipoExamen);
        exa.setDescripcion(descripcion);
        exa.setNumPreguntasExamen(numPreguntas);
        
        exa.setFechainicio(fInicio);
        exa.setFechafin(fFin);
        
        BeanExamen beanExamen = new BeanExamen();
        try {
            beanExamen.guargar(exa);
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(datExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet datExamen</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>saludos desde el ervlet datExamen </h1>");
            out.println("<h1>Se ha guardado exitosamente </h1>");
            out.println(fInicio);
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
