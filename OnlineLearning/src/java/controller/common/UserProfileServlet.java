package controller.common;

import dao.DAOProfile;
import model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "UserProfileServlet", urlPatterns = {"/profile"})
public class UserProfileServlet extends HttpServlet {

    private final DAOProfile dao = new DAOProfile();
    private static final String UPLOAD_DIRECTORY = "img";  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        HttpSession session = request.getSession();
        Integer userID = (Integer) session.getAttribute("userID");

        if (userID != null) {
     
            Users user = dao.getUserByID(userID);

            if (user != null) {
             
                request.setAttribute("user", user);
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            } else {
              
                response.sendRedirect("home");
            }
        } else {
          
            response.sendRedirect("login.jsp");
        }
    }

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    HttpSession session = request.getSession();
    Integer userID = (Integer) session.getAttribute("userID");

    if (userID != null) {
        
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        java.util.Date dob = java.sql.Date.valueOf(request.getParameter("dob")); 
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

      
        Users user = dao.getUserByID(userID);
        user.setName(name);
        user.setGender(gender);
        user.setDob(dob);
        user.setPhone(phone);
        user.setAddress(address);

       
        boolean isUpdated = dao.updateUserProfile(user);

        // Kiểm tra nếu người dùng có chọn ảnh mới để cập nhật
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            Part filePart = request.getPart("image");

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;

                // Tạo thư mục nếu chưa có
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                
                Path filePath = Path.of(uploadPath, fileName);
                Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                
                String imagePath = UPLOAD_DIRECTORY + "/" + fileName;
                user.setImg(imagePath); 
                dao.updateUserImage(user); 
            }
        }

        
        response.sendRedirect("profile");
    } else {
      
        response.sendRedirect("login.jsp");
    }
}


    
    
    
}
