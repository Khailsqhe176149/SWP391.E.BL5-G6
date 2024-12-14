/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.DAOPostList;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Post;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
/**
 *
 * @author Khải
 */
@WebServlet(name = "PostListServlet", urlPatterns = {"/post-management"})
public class PostListServlet extends HttpServlet {

    private final DAOPostList dao = new DAOPostList();
    private static final String UPLOAD_DIRECTORY = "img";  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int postId = Integer.parseInt(request.getParameter("postId"));
            Post post = dao.getPostById(postId);
            //Post post = dao.getPostAndAuthorNameById(postId);
            request.setAttribute("post", post);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-post.jsp");
            dispatcher.forward(request, response);
        }else if ("add".equals(action)) {
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-post.jsp");
            dispatcher.forward(request, response);
        }
        else {
            // Search and display posts
            String search = request.getParameter("search");
            String status = request.getParameter("status");
            
            int page = 1; // Trang mặc định
            int pageSize = 4; // Số bản ghi mỗi trang
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            //List<Post> posts = dao.getFilteredPosts(search, status);
            List<Post> posts = dao.getFilteredPosts(search, status, page, pageSize);
            
             if (posts == null || posts.isEmpty()) {
                // Nếu danh sách trống, có thể hiển thị thông báo hoặc xử lý khác.
                request.setAttribute("message", "No posts found.");
            }
            // Lấy tổng số bản ghi để tính số trang
            int totalPosts = dao.getTotalPostCount(search, status);
            int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
            
            request.setAttribute("posts", posts);
            
            // Truyền dữ liệu vào request
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/post-list.jsp");
            dispatcher.forward(request, response);
        }
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
            
        if ("addPost".equals(action)) {
            // Lấy thông tin từ form
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int status = Integer.parseInt(request.getParameter("status"));
            int sliderId = Integer.parseInt(request.getParameter("sliderid"));

            // Lấy authorid từ session
            HttpSession session = request.getSession();
            int authorId = (int) session.getAttribute("userID");

            // Lấy ảnh từ form (nếu có)
            String imgPath = "img/post.jpg"; // Đặt ảnh mặc định là slider.jpg
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
            
            
            // Tạo đối tượng Post
            Post newPost = new Post();
            newPost.setTitle(title);
            newPost.setContent(content);
            newPost.setStatus(status);
            newPost.setSliderid(sliderId);
            newPost.setImg(imgPath);
            newPost.setAuthorid(authorId);  // Gán authorid từ session

            // Thêm bài viết vào database
            boolean success = dao.addPost(newPost);
            if (success) {
                
                session.setAttribute("message", "Add post successfully.");
                response.sendRedirect("post-management");
            } else {
                
                session.setAttribute("message", "Fail to add post.");
                response.sendRedirect("post-management");
            }
        }
    
        
        if ("updatePost".equals(action)) {
            int postId = Integer.parseInt(request.getParameter("postId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            //String status = request.getParameter("status");
            int status = Integer.parseInt(request.getParameter("status")); 
            int sliderId = Integer.parseInt(request.getParameter("sliderid"));
            
            // Lấy bài viết hiện tại
            Post post = dao.getPostById(postId);
            post.setTitle(title);
            post.setContent(content);
            post.setStatus(status);
            post.setSliderid(sliderId);

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

                    // Lưu đường dẫn ảnh vào đối tượng Post
                    String imagePath = UPLOAD_DIRECTORY + "/" + fileName;
                    post.setImg(imagePath);
                }
            }

            // Cập nhật bài viết
            dao.updatePost(post);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Update post successfully.");
            // Chuyển hướng về trang danh sách bài viết
            response.sendRedirect("post-management");
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
