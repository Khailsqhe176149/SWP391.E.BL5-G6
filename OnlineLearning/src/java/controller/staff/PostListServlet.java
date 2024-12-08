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
        } else {
            // Search and display posts
            String search = request.getParameter("search");
            String status = request.getParameter("status");
            List<Post> posts = dao.getFilteredPosts(search, status);
            request.setAttribute("posts", posts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/post-list.jsp");
            dispatcher.forward(request, response);
        }
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

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

            // Chuyển hướng về trang danh sách bài viết
            response.sendRedirect("post-management");
        }
    }
    
    
    
    
}
