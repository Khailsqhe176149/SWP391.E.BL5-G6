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
import java.util.Date;
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
@WebServlet(name = "PostServlet", urlPatterns = {"/post"})
public class PostServlet extends HttpServlet {

    final DAOPost daoPost = new DAOPost();
    final DAOUser daoUser = new DAOUser();
    final DAOPostCategory categoryDAO = new DAOPostCategory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageParam = request.getParameter("page");
        String searchParam = request.getParameter("search");
        String categoryParam = request.getParameter("c");

        int page = 1;
        int pageSize = 6;
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }
        Integer categỏy = (categoryParam != null && !categoryParam.isEmpty()) ? Integer.valueOf(categoryParam) : null;
        List<Post> posts = daoPost.getAllPostWithParam(searchParam, categỏy, 1);
        List<PostCategory> categories = categoryDAO.getAllPostCategoryWithParam(null, null);
        List<Post> pagingPost = daoPost.Paging(posts, page, pageSize);

        request.setAttribute("posts", pagingPost);
        request.setAttribute("categories", categories);
        request.setAttribute("totalPages", posts.size() % pageSize == 0 ? (posts.size() / pageSize) : (posts.size() / pageSize + 1));
        request.setAttribute("currentPage", page);
        request.setAttribute("searchParam", searchParam);
        request.setAttribute("c", categoryParam);

        request.getRequestDispatcher("post.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            String uploadPath = getServletContext().getRealPath("/") + "img";

            String action = request.getParameter("action");
            if (action.equals("add")) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Retrieve form data
                String categoryId = request.getParameter("categoryId");
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Part filePart = request.getPart("image");
                // Validate fields
                boolean hasError = false;
                StringBuilder errorMessage = new StringBuilder();
                if (categoryId == null || categoryId.isEmpty()) {
                    errorMessage.append("Category is required.\n");
                    hasError = true;
                }

                if (title == null || title.trim().isEmpty()) {
                    errorMessage.append("Title is required.\n");
                    hasError = true;
                }
                if (content == null || content.trim().isEmpty()) {
                    errorMessage.append("Content is required.\n");
                    hasError = true;
                }
                if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
                    errorMessage.append("Image is required.\n");
                    hasError = true;
                }

                // If validation fails, redirect with errors
                if (hasError) {
                    request.setAttribute("notificationErr", errorMessage.toString());
                    request.getRequestDispatcher("post").forward(request, response);
                    return;
                }

                // Save image to /img folder
                String fileName = filePart.getSubmittedFileName();
                filePart.write(uploadPath + File.separator + fileName);

                // Save post to database
                Post newPost = new Post();
                newPost.setTitle(title);
                newPost.setContent(content);
                newPost.setImg(fileName);
                int cid = Integer.parseInt(categoryId);

                PostCategory category = categoryDAO.getById(cid);
                newPost.setCategory(category);
                newPost.setCreatetime(new Date());
                Users author = daoUser.getByAccountId(account.getAcc_id());
                newPost.setAuthor(author);
                daoPost.savePost(newPost);
                session.setAttribute("notification", "Post add successfully!");
                // Redirect to post list
                response.sendRedirect("post");
            }
            // Create directory if it doesn't exist

        } else {
            session.setAttribute("notificationErr", "You must login first!");
            response.sendRedirect("Login");
        }
    }

}
