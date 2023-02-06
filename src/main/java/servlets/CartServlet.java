package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Item;
import model.Product;
import service.ProductService;



public class CartServlet extends HttpServlet {

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
                //gets product id and quantity from jsp and assigns them to integers    
                String id = request.getParameter("id");
                int productid = Integer.parseInt(id);
                String quantity = request.getParameter("quantity");
                int productquantity = Integer.parseInt(quantity);
                Product prod =null;
                HttpSession session = request.getSession();
                
		
                //Makes arraylists and adds any session arraylist to a new one 	
                ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
                
                //if no CartServlet exists makes one
                if(cart == null){
                    cart = new ArrayList<>();  
                }
                
                //Checks to see if product already exists in CartServlet ; if so updates the quantity
                for(Item pd : cart){
                    if(productid == pd.product.getId()){
                        pd.quantity= productquantity + pd.quantity;
                        session.setAttribute("cart", cart);
                        response.sendRedirect("shopping-cart.jsp"); 
                        return;
                    }  
                }
                ProductService pServ = new ProductService();
                prod = pServ.getProductByID(productid);
                
                //Adds lineItem to arraylist if they are not already present 
                cart.add(new Item(prod,productquantity));
                
 
                //Assigns CartServlet to session parameter CartServlet 
                session.setAttribute("cart", cart);
                response.sendRedirect("shopping-cart.jsp"); 

                
	}
}


