/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contenCreater;

import dao.DAOLesson;
import dao.DAOLessonDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.LessonReading;

/**
 *
 * @author Admin
 */
  
@WebServlet(name="ViewLessonReading", urlPatterns={"/ViewLessonReading"})
public class ViewLessonReadings extends HttpServlet {
   private DAOLessonDetail dao = new DAOLessonDetail();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
         // Lấy ID bài học từ tham số URL
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));

        // Lấy danh sách LessonReading từ DAO
        List<LessonReading> readings = dao.getLessonReadingsByLessonId(lessonId);
        
        // Đặt thuộc tính để truyền vào JSP
        request.setAttribute("readings", readings);
        request.setAttribute("lessonId", lessonId);  // Để dùng trong các form khác (sửa, thêm)

        // Forward tới trang JSP để hiển thị
        request.getRequestDispatcher("/list-lesson-reading.jsp").forward(request, response);
    }

}
