/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contenCreater;

import dao.DAOLesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Lesson;

/**
 *
 * @author Admin
 */
@WebServlet(name="AddLesson", urlPatterns={"/addLesson"})
public class AddLesson extends HttpServlet {
   private DAOLesson dao = new DAOLesson();
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Lesson> lessons = dao.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.setAttribute("courseId", req.getParameter("courseId"));
        req.getRequestDispatcher("addLesson.jsp").forward(req, resp);
    }
      
}
