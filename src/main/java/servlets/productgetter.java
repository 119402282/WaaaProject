package servlets;

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Product;
import service.ProductService;
import utils.DBManager;

public class productgetter extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       //getting id and call the corresponding arraylist object
        String id = request.getParameter("id");
        int productid = Integer.parseInt(id);
        if (id == null || "".equals(id)) {
            id = "-1";
        }
        ProductService pServ = new ProductService();
        Product prod = pServ.getProductByID(productid);

              request.setAttribute("prod", prod);
            RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
            rd.forward(request, response);          
            }
            
        }   
        
      


           
       
    

    