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
@WebServlet(urlPatterns = {"/addingSessionSPK"})
public class AddingSpeaker extends HttpServlet {

    private final faccade_main fb = new faccade_main();
    boolean check = true;
    String ssName = "";
    String roomNum = "";
    String roomSeat = "";
    String timeS = "";
    String timeE = "";
    //Get value from speaker
    String name = "";
    String doc = "";
    String phone = "";
    String email = "";

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
        getParameter(request);

        while (true) {
            if (fb.check_name(name) == false) {
                request.setAttribute("errorMessage", "Invalid name<br>Name only containts letter or too Short");
                check = false;
                break;
            }
            if (fb.check_phone(phone) == false) {
                request.setAttribute("errorMessage", "We only allow US phone Number or 10 digits.");
                check = false;
                break;
            }
            if (fb.checkDup_speaker("phone", phone, "none") == false) {
                request.setAttribute("errorMessage", "This phone number is already in use<br>Please use different one");
                check = false;
                break;
            }
            if (fb.check_email(email) == false) {
                request.setAttribute("errorMessage", "Invalid email<br>please check it again!");
                check = false;
                break;
            }
            if (fb.checkDup_speaker("email", email, "none") == false) {
                request.setAttribute("errorMessage", "This email is already in use<br>Please use different one");
                check = false;
                break;
            }
            check = true;
            break;
        }
        if (!check) {
            request.setAttribute("SessionNameValue", ssName);
            request.setAttribute("roomNumberValue", roomNum);
            request.setAttribute("roomSeatValue", roomSeat);
            request.setAttribute("timeStartValue", timeS);
            request.setAttribute("timeEndValue", timeE);

            request.setAttribute("NameValue", name);
            request.setAttribute("PhoneNumberValue", phone);
            request.setAttribute("EmailValue", email);
            request.setAttribute("docValue", doc);

            RequestDispatcher rq = request.getRequestDispatcher("/Speaker.jsp");
            rq.include(request, response);
        } else {
            fb.write_speaker(name, phone, email, doc);
            fb.write_room(roomNum, roomSeat);
            fb.write_time_slot(timeS, timeE);
            int spkID = fb.getId_speaker(name, phone, email, doc);
            int roomID = fb.getId_room(roomNum, roomSeat);
            int timeID = fb.getId_time_slot(timeS, timeE);
            fb.write_session(ssName, String.valueOf(spkID), String.valueOf(roomID), String.valueOf(timeID));
            response.sendRedirect("index.jsp");
        }
    }

    public void getParameter(HttpServletRequest request) {
        //Get value from these information in session, 
        ssName = request.getParameter("SessionName");
        roomNum = request.getParameter("roomNumber");
        roomSeat = request.getParameter("roomSeat");
        timeS = request.getParameter("timeStart");
        timeE = request.getParameter("timeEnd");
        //Get value from speaker
        name = request.getParameter("Name");
        doc = request.getParameter("doc");
        phone = request.getParameter("PhoneNumber");
        email = request.getParameter("Email");

        //Get value from these information in session, 
        /*String ssName = request.getParameter("SessionName");
        String roomNum = request.getParameter("roomNumber");
        String roomSeat = request.getParameter("roomSeat");
        String timeS = request.getParameter("timeStart");
        String timeE = request.getParameter("timeEnd");
        //Get value from speaker
        String name = request.getParameter("Name");
        String doc = request.getParameter("doc");
        String phone = request.getParameter("PhoneNumber");
        String email = request.getParameter("Email");*/
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
            out.println("<title>Servlet AddingSpeaker</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddingSpeaker at " + request.getContextPath() + "</h1>");
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
