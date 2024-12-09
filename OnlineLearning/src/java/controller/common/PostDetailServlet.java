/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.DAOPost;
import dao.DAOPostCategory;
import dao.DAOUser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.Account;
import model.Post;
import model.PostCategory;
import model.Users;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "PostDetailServlet", urlPatterns = {"/post-detail"})
public class PostDetailServlet extends HttpServlet {

    final DAOPost daoPost = new DAOPost();
    final  DAOPostCategory categoryDAO = new DAOPostCategory();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String postIdParam = request.getParameter("postId");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (postIdParam == null || postIdParam.isEmpty()) {
            response.sendRedirect("post");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdParam);
            // Fetch post details
            Post post = daoPost.getById(postId);
            if (post == null) {
                request.setAttribute("error", "Post not found");
                request.getRequestDispatcher("post.jsp").forward(request, response);
                return;
            }

            // Pass post details to JSP
            request.setAttribute("post", post);
            DAOUser dAOUser = new DAOUser();
            if (account != null) {
                Users user = dAOUser.getByAccountId(account.getAcc_id());
                request.setAttribute("uid", user.getUserID());
            }
           
            List<PostCategory> categories = categoryDAO.getAllPostCategoryWithParam(null, null);

            request.setAttribute("categories", categories);
            request.getRequestDispatcher("post-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("post");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            String action = request.getParameter("action");
            String postIdParam = request.getParameter("postId");
            int postId = Integer.parseInt(postIdParam);

            if ("edit".equals(action)) {
                // Handle Edit
                String categoryId = request.getParameter("categoryId");
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Part imagePart = request.getPart("image");

                Post post = daoPost.getById(postId);
                post.setTitle(title);
                post.setContent(content);
                if (categoryId != null) {
                    PostCategory category = categoryDAO.getById(Integer.parseInt(categoryId));
                    post.setCategory(category);
                }

                // Update image only if a new image is uploaded
                if (imagePart != null && imagePart.getSize() > 0) {
                    String uploadPath = getServletContext().getRealPath("/") + "img";
                    String fileName = imagePart.getSubmittedFileName();
                    imagePart.write(uploadPath + File.separator + fileName);
                    post.setImg(fileName);
                }

                daoPost.updatePost(post);
                session.setAttribute("notification", "Update successfully");
                response.sendRedirect("post-detail?postId=" + postId);

            } else if ("delete".equals(action)) {
                // Handle Delete
                daoPost.deletePost(postId);
                 session.setAttribute("notification", "Delete successfully");
                response.sendRedirect("post");
            }
        } else {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("Login");
        }
    }

}
