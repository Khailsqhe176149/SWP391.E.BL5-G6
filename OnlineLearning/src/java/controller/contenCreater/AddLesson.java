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
import java.util.ArrayList;
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
    
    int courseId = Integer.parseInt(req.getParameter("courseId"));

    
    List<Lesson> allLessons = dao.getAllLessons();
    List<Lesson> existingLessons = dao.getLessonsByCourseId(courseId);
    List<Lesson> lessonsAvailableForAdding = new ArrayList<>();
    
    
    
    for (Lesson lesson : allLessons) {
        boolean isAlreadyAdded = false;
        for (Lesson existingLesson : existingLessons) {
            if (lesson.getLessonid()== existingLesson.getLessonid()) {
                isAlreadyAdded = true;
                break;
            }
        }
        if (!isAlreadyAdded) {
            lessonsAvailableForAdding.add(lesson);
        }
    }

   
    req.setAttribute("lessons", lessonsAvailableForAdding);
    req.setAttribute("courseId", courseId);
    req.getRequestDispatcher("addLesson.jsp").forward(req, resp);
}

      
}
