/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import dao.DAOSliderList;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import model.Slider;

/**
 *
 * @author Khải
 */
@WebServlet(name="SliderListServlet", urlPatterns={"/slider-management"})
public class SliderListServlet extends HttpServlet {
   
   private final DAOSliderList dao = new DAOSliderList();
    private static final String UPLOAD_DIRECTORY = "img";  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int sliderId = Integer.parseInt(request.getParameter("sliderId"));
            Slider slider = dao.getSliderById(sliderId);
            
            request.setAttribute("slider", slider);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-slider.jsp");
            dispatcher.forward(request, response);
        }else if ("add".equals(action)) {
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-slider.jsp");
            dispatcher.forward(request, response);
        }
        else if ("delete".equals(action)) {
            int sliderId = Integer.parseInt(request.getParameter("sliderId"));
            dao.deleteSlider(sliderId);
            
            
                response.sendRedirect("slider-management");
            
        } else {
         
            String search = request.getParameter("search");
            String status = request.getParameter("status");
            List<Slider> sliders = dao.getFilteredSliders(search, status);
            request.setAttribute("sliders", sliders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/slider-list.jsp");
            dispatcher.forward(request, response);
        }
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
            
        if ("addSlider".equals(action)) {
            // Lấy thông tin từ form
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));
            int slidercategoryId = Integer.parseInt(request.getParameter("slidercategoryid"));

         

            // Lấy ảnh từ form (nếu có)
            Part imagePart = request.getPart("img");
            String imgPath = null;
            if (imagePart != null) {
                String fileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/") + "img"; // Dựng thư mục lưu ảnh
                File uploadFile = new File(uploadDir);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();  // Tạo thư mục nếu không có
                }
                imgPath = "img/" + fileName;
                imagePart.write(uploadDir + File.separator + fileName);  // Lưu file vào thư mục
            }

            // Tạo đối tượng Slider
            Slider newSlider = new Slider();
            newSlider.setTitle(title);
            newSlider.setDescription(description);
            newSlider.setStatus(status);
            newSlider.setSlidercategoryid(slidercategoryId);
            newSlider.setImg(imgPath);
            

            // Thêm bài viết vào database
            boolean success = dao.addSlider(newSlider);
            if (success) {
                response.sendRedirect("slider-management");
            } else {
                response.getWriter().write("Failed to add slider");
            }
        }
    
        
        if ("updateSlider".equals(action)) {
            int sliderId = Integer.parseInt(request.getParameter("sliderId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            
            int status = Integer.parseInt(request.getParameter("status")); 
            int slidercategoryId = Integer.parseInt(request.getParameter("slidercategoryid"));
            
            
            // Lấy bài viết hiện tại
            Slider slider = dao.getSliderById(sliderId);
            slider.setTitle(title);
            slider.setDescription(description);
            slider.setStatus(status);
            slider.setSlidercategoryid(slidercategoryId);

            // Xử lý tải ảnh lên (nếu có)
            if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                Part filePart = request.getPart("img");

                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = filePart.getSubmittedFileName();
                    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

                    // Tạo thư mục nếu chưa có
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // Lưu file ảnh
                    Path filePath = Path.of(uploadPath, fileName);
                    Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    // Lưu đường dẫn ảnh vào đối tượng 
                    String imagePath = UPLOAD_DIRECTORY + "/" + fileName;
                    slider.setImg(imagePath);
                }
            }

     
            dao.updateSlider(slider);

   
            response.sendRedirect("slider-management");
        }
    }
    
    
    private String processImageUpload(HttpServletRequest request) throws ServletException, IOException {
        String imgFileName = null;
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            Part filePart = request.getPart("img");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                Path filePath = Path.of(uploadPath, fileName);
                Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imgFileName = UPLOAD_DIRECTORY + "/" + fileName;
            }
        }
        return imgFileName;
    }

}
