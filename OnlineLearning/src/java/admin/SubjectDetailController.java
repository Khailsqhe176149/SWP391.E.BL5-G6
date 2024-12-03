/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dao.DAOSubject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Subjects;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SubjectDetailController", urlPatterns = {"/admin/subject-detail"})
public class SubjectDetailController extends HttpServlet {

    private final DAOSubject subjectDAO = new DAOSubject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 1) {
                int id = Integer.parseInt(request.getParameter("id"));
                Subjects subject = subjectDAO.getSubjectByID(id);

                request.setAttribute("subject", subject);
                request.setAttribute("title", "Subject Detail - " + subject.getName());

                request.getRequestDispatcher("subject-detail.jsp").forward(request, response);
            } else {
                session.setAttribute("notificationErr", "Access Denined!!");
                response.sendRedirect("../Login");
            }
        } else {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("../Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 1) {
                String action = request.getParameter("action");
                if ("edit".equals(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name").trim();
                    int status = Integer.parseInt(request.getParameter("status"));

                    // Validation variables
                    String errorMessage = null;
                    String namePattern = "^[a-zA-Z0-9\\s\\-–',.()]{5,255}$"; // Extended for special characters, 5 to 255 characters

                    // Validate Name
                    if (errorMessage == null && name.isEmpty()) {
                        errorMessage = "Subject name cannot be empty.";
                    } else if (errorMessage == null && !name.matches(namePattern)) {
                        errorMessage = "Subject name must be between 5 and 255 characters and can only include letters, numbers, spaces, hyphens, and certain punctuation.";
                    }

                    if (subjectDAO.isExistedSubjectName(name, id)) {
                        errorMessage = "Subject with name <strong>" + name + "</strong> already exists!";
                    }
                    // If validation fails, redirect back with an error message
                    if (errorMessage != null) {
                        session.setAttribute("notificationErr", errorMessage);
                        response.sendRedirect("subject-detail?id=" + id);
                        return; // Stop further execution
                    }

                    // Create Subject Object and Save
                    Subjects subject = subjectDAO.getSubjectByID(id);
                    subject.setName(name);
                    subject.setStatus(status);

                    boolean isAdded = subjectDAO.editSubject(subject);
                    if (isAdded) {
                        session.setAttribute("notification", "Subject update successfully!");
                    } else {
                        session.setAttribute("notificationErr", "Something went wrong! Please try again.");
                    }
                    response.sendRedirect("subject-detail?id=" + id);
                }
            } else {
                session.setAttribute("notificationErr", "Access Denined!!");
                response.sendRedirect("../Login");
            }
        } else {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("../Login");
        }
    }
}
