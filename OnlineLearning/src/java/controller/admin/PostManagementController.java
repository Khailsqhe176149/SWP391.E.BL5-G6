/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOPost;
import dao.DAOPostCategory;
import dao.DAOUser;
import java.io.IOException;
import jakarta.servlet.ServletException;
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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PostManagementController", urlPatterns = {"/admin/post-management"})
public class PostManagementController extends HttpServlet {

    final DAOPost daoPost = new DAOPost();
    final DAOUser daoUser = new DAOUser();
    final DAOPostCategory categoryDAO = new DAOPostCategory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            if (account.getRole_id() == 1) {
                String pageParam = request.getParameter("page");
                String searchParam = request.getParameter("search");
                String categoryParam = request.getParameter("c");

                int page = 1;
                int pageSize = 10;
                if (pageParam != null && !pageParam.isEmpty()) {
                    page = Integer.parseInt(pageParam);
                }
                Integer categỏy = (categoryParam != null && !categoryParam.isEmpty()) ? Integer.valueOf(categoryParam) : null;
                List<Post> posts = daoPost.getAllPostWithParam(searchParam, categỏy, 1);
                List<Post> pagingPost = daoPost.Paging(posts, page, pageSize);
                List<PostCategory> categories = categoryDAO.getAllPostCategoryWithParam(null, null);
                request.setAttribute("posts", pagingPost);
                request.setAttribute("totalPages", posts.size() % pageSize == 0 ? (posts.size() / pageSize) : (posts.size() / pageSize + 1));
                request.setAttribute("currentPage", page);
                request.setAttribute("categories", categories);
                request.setAttribute("searchParam", searchParam);
                request.setAttribute("c", categoryParam);

                request.getRequestDispatcher("post-management.jsp").forward(request, response);

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
                String postIdParam = request.getParameter("id");
                int postId = Integer.parseInt(postIdParam);
                daoPost.deletePost(postId);
                session.setAttribute("notification", "Delete successfully");
                response.sendRedirect("post-management");
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
