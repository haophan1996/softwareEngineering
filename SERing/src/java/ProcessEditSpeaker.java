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
@WebServlet(urlPatterns = {"/processEditSpeaker"})
public class ProcessEditSpeaker extends HttpServlet {

    boolean check = true;
    private final faccade_main fb = new faccade_main();
    String name = "",doc = "",phone = "",email = "",spkID = "";

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
        getParameter(request);
        while (true) {
            if (fb.check_name(name) == false) {
                request.setAttribute("errorMessage", "Invalid name, name only containts Letter or too Short");
                check = false;
                break;
            }
            if (fb.check_phone(phone) == false) {
                request.setAttribute("errorMessage", "We only allow US phone Number or 10 digits." + phone);
                check = false;
                break;
            }
            if (fb.checkDup_speaker("phone", phone, spkID) == false) {
                request.setAttribute("errorMessage", "This phone number is already in use");
                check = false;
                break;
            }
            if (fb.check_email(email) == false) {
                request.setAttribute("errorMessage", "Invalid email, please check it again!");
                check = false;
                break;
            }
            if (fb.checkDup_speaker("email", email, spkID) == false) {
                request.setAttribute("errorMessage", "Email already used, Please use different one");
                check = false;
                break;
            }
            check = true;
            break;
        }

        if (check == true) {
            if (fb.edit_speaker(name, phone, email, doc, spkID) == true) {
                response.sendRedirect("index.jsp");
            } else {
                setAttribute(request);
                request.setAttribute("errorMessage", "Cannot connect to database");
                RequestDispatcher rq = request.getRequestDispatcher("/edit_Speaker.jsp");
                rq.include(request, response);
            }
        } else {
            setAttribute(request);
            RequestDispatcher rq = request.getRequestDispatcher("/edit_Speaker.jsp");
            rq.include(request, response);
        }
    }

    public void setAttribute(HttpServletRequest request) {
        request.setAttribute("NameValue", name);
        request.setAttribute("PhoneNumberValue", phone);
        request.setAttribute("EmailValue", email);
        request.setAttribute("docValue", doc);
        request.setAttribute("idValue", spkID);
    }

    public void getParameter(HttpServletRequest request) {
        name = request.getParameter("Name");
        doc = request.getParameter("doc");
        phone = request.getParameter("PhoneNumber");
        email = request.getParameter("Email");
        spkID = request.getParameter("id");
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
