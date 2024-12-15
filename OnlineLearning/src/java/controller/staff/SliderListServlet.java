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
@WebServlet(name = "SliderListServlet", urlPatterns = {"/slider-management"})
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
        } else if ("add".equals(action)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-slider.jsp");
            dispatcher.forward(request, response);
        } else if ("delete".equals(action)) {
            int sliderId = Integer.parseInt(request.getParameter("sliderId"));
            dao.deleteSlider(sliderId);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Delete slider successfully.");
            response.sendRedirect("slider-management");

        } else {
            

            String search = request.getParameter("search");
            String status = request.getParameter("status");
            int page = 1; // Trang mặc định
            int pageSize = 4; // Số bản ghi mỗi trang
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            //List<Slider> sliders = dao.getFilteredSliders1(search, status);
            List<Slider> sliders = dao.getFilteredSliders(search, status, page, pageSize);

            if (sliders == null || sliders.isEmpty()) {
                // Nếu danh sách trống, có thể hiển thị thông báo hoặc xử lý khác.
                request.setAttribute("message", "No sliders found.");
            }
            // Lấy tổng số bản ghi để tính số trang
            int totalSliders = dao.getTotalSliderCount(search, status);
            int totalPages = (int) Math.ceil((double) totalSliders / pageSize);

            request.setAttribute("sliders", sliders);

            // Truyền dữ liệu vào request
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            //request.setAttribute("message", "aa.");

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
 
            String imgPath = "img/slider.jpg"; // Đặt ảnh mặc định là slider.jpg
            Part imagePart = request.getPart("img");
            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/") + "img"; // Đường dẫn lưu ảnh
                File uploadFile = new File(uploadDir);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();  // Tạo thư mục nếu không có
                }
                imgPath = "img/" + fileName;
                imagePart.write(uploadDir + File.separator + fileName);  // Lưu ảnh vào thư mục
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
                HttpSession session = request.getSession();
                session.setAttribute("message", "Add slider successfully.");
                response.sendRedirect("slider-management");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("message", "Fail to add slider.");
                response.sendRedirect("slider-management");
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
            HttpSession session = request.getSession();
            session.setAttribute("message", "Update slider successfully.");
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
