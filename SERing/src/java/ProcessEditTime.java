/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.code.faccade_main;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HaoPhan
 */
@WebServlet(urlPatterns = {"/processEditTime"})
public class ProcessEditTime extends HttpServlet {

    private final faccade_main fb = new faccade_main();
    String error = "0";
    boolean check;

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
        //PrintWriter out = response.getWriter();
        String timeS = request.getParameter("timeStart");
        String timeE = request.getParameter("timeEnd");
        String timeID = request.getParameter("id");
        error = fb.check_time(timeS, timeE);

        if (error.equals("0")) {
            check = fb.edit_time_slot(timeS, timeE, timeID);
            if (check == true) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("errorMessage", "Can't add data to Database, Please re-input again");
                request.setAttribute("timeStartValue", timeS);
                request.setAttribute("timeEndValue", timeE);
                request.setAttribute("idTime", timeID);
                RequestDispatcher rq = request.getRequestDispatcher("/edit_TimeSlot.jsp");
                rq.include(request, response);
            }
        } else {
            request.setAttribute("errorMessage", error);
            request.setAttribute("timeStartValue", timeS);
            request.setAttribute("timeEndValue", timeE);
            request.setAttribute("idTime", timeID);
            RequestDispatcher rq = request.getRequestDispatcher("/edit_TimeSlot.jsp");
            rq.include(request, response);
        }

    }

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
            out.println("<title>Servlet ProcessEditSpeaker</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessEditSpeaker at " + request.getContextPath() + "</h1>");
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
