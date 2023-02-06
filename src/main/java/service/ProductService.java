/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import servlets.productgetter;
import utils.DBManager;

/**
 *
 * @author be_me
 */
public class ProductService {
    
    public ArrayList<Product> getHomePageProducts(){
        
        ProductDAO pDao = new ProductDAO();
        return pDao.getTopProducts(9);
        
    }
    public Product getProductByID(int prodid){
        
        Product prod = null;
        DBManager dm = new DBManager();
        Connection con = dm.getConnection();
        int productId = 0;
        String name = null;
        String description = null;
        float price = 0.0f;
        String imageLocation = null;
        String category = null;
        String query = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID =" + prodid ;
        
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
          
            while (rs.next()) {
            
                productId = (rs.getInt(1));
                name = (rs.getString(2));
                description = (rs.getString(3));
                price = (rs.getFloat(4));
                imageLocation = (rs.getString(5));
                category = (rs.getString(6));
                Product product = new Product();
                product.setId(productId);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setImageLocation(imageLocation);
                product.setCategory(category);
                prod = product;

            }
       
        }   catch (SQLException ex) {
                Logger.getLogger(productgetter.class.getName()).log(Level.SEVERE, null, ex);
            }
        return prod;
    
        
    
}
}
    
