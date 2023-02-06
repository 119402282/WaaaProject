/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import service.UserService;

/**
 *
 * @author be_me
 */
public class userAdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserService uServ = new UserService();
        String url = null;
        if (action == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        switch (action) {
            case "listUsers":
                ArrayList<User> users = uServ.getAllUsers();
                setUsersAsAttribute(request, users);
                request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("addUser.jsp").forward(request, response);
                break;
            case "delete":
                deleteUser(request,response);
                users = uServ.getAllUsers();
                setUsersAsAttribute(request, users);
                request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
                break;
            case "insertUser":
                insertUser(request,response);
                users = uServ.getAllUsers();
                setUsersAsAttribute(request, users);
                request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
                break;
            case "updateCompleteUser":
                updateUser(request,response);
                users = uServ.getAllUsers();
                setUsersAsAttribute(request, users);
                request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
                break;
            case "edit":
                String userId = request.getParameter("id");
                if (userId == null) {
                    request.getRequestDispatcher("Home").forward(request, response);
                    return;
                } else {
                    long uId = Long.parseLong(userId);
                    uServ = new UserService();
                    User oldUser = uServ.getUser(uId);
                    request.setAttribute("oldUser", oldUser);
                    request.getRequestDispatcher("editUser.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("Home").forward(request, response);
                break;
        }
}

private void setUsersAsAttribute(HttpServletRequest request, ArrayList<User> users) {
    request.setAttribute("users", users);
}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
       
    }
    
    private void insertUser(HttpServletRequest request, HttpServletResponse response){
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userType = request.getParameter("userType");
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUserType(userType);
        
        UserService uServ = new UserService();
        uServ.insertUser(newUser);
        
        
        
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response){
        
        long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userType = request.getParameter("userType");
        
        User newUser = new User();
        newUser.setId(id);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUserType(userType);
        
        UserService uServ = new UserService();
        uServ.updateUser(newUser);
        
        
        
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response){
        
        long userId = Long.parseLong(request.getParameter("id"));
        UserService uServ = new UserService();
        uServ.deleteUser(userId);
        return;
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
