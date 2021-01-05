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
@WebServlet(urlPatterns = {"/processEditRoom"})
public class ProcessEditRoom extends HttpServlet {

    private final faccade_main fb = new faccade_main();
    boolean check = true;

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
        PrintWriter out = response.getWriter();
        String roomID = request.getParameter("id");
        String roomSeat = request.getParameter("Seat");
        String roomNum = request.getParameter("roomNum");

        check = fb.is_Number(roomSeat);
        
        if (check == true) {
            if (fb.edit_room(roomNum, roomSeat, roomID)) {
                response.sendRedirect("index.jsp");
            }
        } else {
            request.setAttribute("id", roomID);
            request.setAttribute("SeatValue", roomSeat);
            request.setAttribute("RoomValue", roomNum);
            request.setAttribute("errorMessage", "Room Seat only numbers, from 1 to 120");
            RequestDispatcher rq = request.getRequestDispatcher("/edit_Room.jsp");
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
            out.println("<title>Servlet ProcessEditRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessEditRoom at " + request.getContextPath() + "</h1>");
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
