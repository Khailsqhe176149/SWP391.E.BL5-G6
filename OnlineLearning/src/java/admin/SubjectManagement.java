/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin;

import dao.DAOSubject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Subjects;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SubjectManagement", urlPatterns = {"/admin/subject-management"})
public class SubjectManagement extends HttpServlet {

    final DAOSubject subjectDAO = new DAOSubject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 4) {
                String pageParam = request.getParameter("page");
                String searchParam = request.getParameter("search");
                String statusParam = request.getParameter("status");

                int page = 1; // Default to the first page
                int pageSize = 10; // Set the desired page size
                if (pageParam != null && !pageParam.isEmpty()) {
                    page = Integer.parseInt(pageParam);
                }
                Integer status = (statusParam != null && !statusParam.isEmpty()) ? Integer.valueOf(statusParam) : null;
                List<Subjects> subjects = subjectDAO.getAllSubjectWithParam(searchParam, status);
                List<Subjects> pagingSubject = subjectDAO.Paging(subjects, page, pageSize);

                request.setAttribute("subjects", pagingSubject);
                request.setAttribute("totalPages", subjects.size() % pageSize == 0 ? (subjects.size() / pageSize) : (subjects.size() / pageSize + 1));
                request.setAttribute("currentPage", page);
                request.setAttribute("searchParam", searchParam);
                request.setAttribute("statusParam", statusParam);

                request.getRequestDispatcher("subject-management.jsp").forward(request, response);

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
            if (account.getRole_id() == 4) {
                String action = request.getParameter("action");
                if ("add".equals(action)) {
                    String name = request.getParameter("name").trim();
                    int status = Integer.parseInt(request.getParameter("status"));

                    // Validation variables
                    String errorMessage = null;
                    String namePattern = "^[a-zA-Z0-9\\s\\-–',.()]{5,255}$"; // Extended for special characters, 5 to 255 characters

                    // Validate Code
                    // Validate Name
                    if (errorMessage == null && name.isEmpty()) {
                        errorMessage = "Subject name cannot be empty.";
                    } else if (errorMessage == null && !name.matches(namePattern)) {
                        errorMessage = "Subject name must be between 5 and 255 characters and can only include letters, numbers, spaces, hyphens, and certain punctuation.";
                    }

                    if (errorMessage == null && subjectDAO.isExistedSubjectName(name, null)) {
                        errorMessage = "Subject with name <strong>" + name + "</strong> already exists!";
                    }
                    // If validation fails, redirect back with an error message
                    if (errorMessage != null) {
                        session.setAttribute("notificationErr", errorMessage);
                        response.sendRedirect("subject-management");
                        return; // Stop further execution
                    }

                    // Create Subject Object and Save
                    Subjects newSubject = new Subjects();
                    newSubject.setName(name);
                    newSubject.setStatus(status);

                    boolean isAdded = subjectDAO.addNewSubject(newSubject);
                    if (isAdded) {
                        session.setAttribute("notification", "Subject added successfully!");
                    } else {
                        session.setAttribute("notificationErr", "Something went wrong! Please try again.");
                    }
                    response.sendRedirect("subject-management");
                } else if ("change-status".equals(action)) {
                    // Handle change status logic
                    String idParam = request.getParameter("id");
                    String newStatus = request.getParameter("newStatus");

                    try {
                        int subjectId = Integer.parseInt(idParam);

                        // Attempt to update the status in the database
                        boolean isUpdated = subjectDAO.changeSubjectStatus(subjectId, newStatus);
                        if (isUpdated) {
                            if ("0".equalsIgnoreCase(newStatus)) {
                                session.setAttribute("notification", "Subject has been successfully deactivated!");
                            } else if ("1".equalsIgnoreCase(newStatus)) {
                                session.setAttribute("notification", "Subject has been successfully activated!");
                            }
                        } else {
                            session.setAttribute("notificationErr", "Failed to update subject status. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        session.setAttribute("notificationErr", "Invalid subject ID.");
                    }

                    // Redirect to the subject management page
                    response.sendRedirect("subject-management");
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
