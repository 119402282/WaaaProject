/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Henry
 */
public class SetupDbListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SetupDb sdb = new SetupDb();
        sdb.createTables();
        sdb.insertSetupData(); //To change body of generated methods, choose Tools | Templates.
        sdb.showData();// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Contexted Destroyed");
    }
}
