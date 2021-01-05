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
@WebServlet(urlPatterns = {"/editInformation"})
public class EditInformation extends HttpServlet {

    private faccade_main fb = new faccade_main();

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

        String sessionID = request.getParameter("sessionID");
        String roomID = request.getParameter("roomID");
        String speakID = request.getParameter("speakID");
        String timeID = request.getParameter("timeID");

        if (request.getParameter("edit_Session") != null) {
            request.setAttribute("SessionID", fb.showInforSession(sessionID));
            RequestDispatcher rq = request.getRequestDispatcher("/edit_Session.jsp");
            rq.include(request, response);
        } else if (request.getParameter("edit_Speaker") != null) {
            request.setAttribute("SpeakerID", fb.showInforspeaker(speakID));
            RequestDispatcher rq = request.getRequestDispatcher("/edit_Speaker.jsp");
            rq.include(request, response);
        } else if (request.getParameter("edit_TimeSlot") != null) {
            request.setAttribute("timeID", fb.showInforTimeSlot(timeID));
            RequestDispatcher rq = request.getRequestDispatcher("/edit_TimeSlot.jsp");
            rq.include(request, response);
        } else if (request.getParameter("edit_Room") != null) {
            request.setAttribute("RoomID", fb.showInforRoom(roomID));
            RequestDispatcher rq = request.getRequestDispatcher("/edit_Room.jsp");
            rq.include(request, response);
        } else if (request.getParameter("Delete") != null) {
            boolean check = false;
            
            while (true) {
                if (!fb.remove_session(sessionID)) {
                    check = false;
                    break;
                } else if (!fb.remove_room(roomID)) {
                    check = false;
                    break;
                } else if (!fb.remove_speaker(speakID)) {
                    check = false;
                    break;
                } else if (!fb.remove_time_slot(timeID)) {
                    check = false;
                    break;
                }
                check = true;
                break;
            }

            if (check) {
                response.sendRedirect("index.jsp");
            } else //out.print("cannot connect to database");
            {
                out.print("cannot connect to database");
            }
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditInformation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
