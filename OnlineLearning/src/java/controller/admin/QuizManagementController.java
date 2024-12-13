/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOQuiz;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Quiz;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizManagementController", urlPatterns = {"/admin/quiz-management"})
public class QuizManagementController extends HttpServlet {

    final DAOQuiz daoQuiz = new DAOQuiz();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 1) {
                String pageParam = request.getParameter("page");
                String searchParam = request.getParameter("search");

                int page = 1;
                int pageSize = 10;
                if (pageParam != null && !pageParam.isEmpty()) {
                    page = Integer.parseInt(pageParam);
                }
                List<Quiz> listQuiz = daoQuiz.getAllQuizWithParam(searchParam);
                List<Quiz> pagingPost = daoQuiz.Paging(listQuiz, page, pageSize);
                request.setAttribute("posts", pagingPost);
                request.setAttribute("totalPages", listQuiz.size() % pageSize == 0 ? (listQuiz.size() / pageSize) : (listQuiz.size() / pageSize + 1));
                request.setAttribute("currentPage", page);
                request.setAttribute("searchParam", searchParam);

                request.getRequestDispatcher("quiz-management.jsp").forward(request, response);

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
                if (action.equals("add")) {

                    String name = request.getParameter("name");
                     float minScore = Float.parseFloat(request.getParameter("minScore"));
                    String description = request.getParameter("description");
                    String content = request.getParameter("content");

                    if (daoQuiz.isExistedQuizName(name, null)) {
                        session.setAttribute("notificationErr", "Quiz with name <strong>" + name + "</strong> already exists!");
                        response.sendRedirect("quiz-management");
                        return;
                    }
                    Quiz quiz = new Quiz();

                    quiz.setName(name);
                    quiz.setMiniumscore(minScore);
                    quiz.setDescription(description);
                    quiz.setContent(content);
                    boolean isAdded = daoQuiz.addNewQuiz(quiz);
                    if (isAdded) {
                         session.setAttribute("notification", "Quiz added successfully");
                         response.sendRedirect("quiz-management");
                    }else{
                        session.setAttribute("notificationErr", "Quiz added failed");
                         response.sendRedirect("quiz-management");
                    }
                    
                    
                }else if (action.equals("edit")) {

                    String name = request.getParameter("name");
                    int id = Integer.parseInt(request.getParameter("id"));
                    float minScore = Float.parseFloat(request.getParameter("minScore"));
                    String description = request.getParameter("description");
                    String content = request.getParameter("content");

                    if (daoQuiz.isExistedQuizName(name, id)) {
                        session.setAttribute("notificationErr", "Quiz with name <strong>" + name + "</strong> already exists!");
                        response.sendRedirect("quiz-management");
                        return;
                    }
                    Quiz quiz = new Quiz();
                    quiz.setQuizid(id);
                    quiz.setName(name);
                    quiz.setMiniumscore(minScore);
                    quiz.setDescription(description);
                    quiz.setContent(content);
                    boolean isAdded = daoQuiz.editQuiz(quiz);
                    if (isAdded) {
                         session.setAttribute("notification", "Quiz edit successfully");
                         response.sendRedirect("quiz-management");
                    }else{
                        session.setAttribute("notificationErr", "Quiz edit failed");
                         response.sendRedirect("quiz-management");
                    }
                    
                    
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
