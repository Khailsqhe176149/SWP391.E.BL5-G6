/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import common.EmailUntil;
import dao.DAORegister;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Khải
 */
@WebServlet(name="RegisterServlet", urlPatterns={"/Register"})
public class RegisterServlet extends HttpServlet {
   

    private static final long serialVersionUID = 1L;

    private final DAORegister dao = new DAORegister(); 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

           boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();
     boolean emailExist = dao.isEmailExist(email);
        boolean phoneExist = dao.isPhoneExist(phone);

        if (emailExist) {
            request.setAttribute("emailError", "Email đã tồn tại. Vui lòng chọn email khác.");
        }

        if (phoneExist) {
            request.setAttribute("phoneError", "Số điện thoại đã tồn tại. Vui lòng chọn số điện thoại khác.");
        }
        if (email != null && !email.contains("@")) {
            
            request.setAttribute("emailError","Email phải có '@'.");
        }

      
        if (phone != null && !phone.matches("^0\\d{9}$")) {
            
            request.setAttribute("phoneError","Số điện thoại phải có 10 số và bắt đầu bằng số 0.");
        }
        
        
        
        if (emailExist || phoneExist) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return; 
        }
        
        
        
        
        boolean isRegistered = dao.registerUser(name, gender, dob, phone, address, email, password);

        if (isRegistered) {
            String subject = "Registration Successful";
            String message = "Dear " + name + ",\n\nYour account has been successfully created!";
            //String code =EmailUntil.getRandomCode();
            EmailUntil.sendEmail(email, subject, message);
            // EmailUntil.sendEmailOTP(email, code);
            response.sendRedirect("Login");
        } else {
            
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("/Register").forward(request, response);
        }
    }

}
