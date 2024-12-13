/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOQuiz;
import dao.DAOSubject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Quiz;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizDetailController", urlPatterns = {"/admin/quiz-detail"})
public class QuizDetailController extends HttpServlet {

    private final DAOQuiz daoQuiz = new DAOQuiz();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 1) {
                int id = Integer.parseInt(request.getParameter("id"));
                Quiz quiz = daoQuiz.getQuizById(id);

                request.setAttribute("subject", quiz);
                request.setAttribute("title", "Quiz Detail - " + quiz.getName());

                request.getRequestDispatcher("quiz-detail.jsp").forward(request, response);
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
