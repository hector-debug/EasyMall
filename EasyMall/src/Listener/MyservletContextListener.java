package Listener;

import javax.servlet.ServletContextEvent;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import entity.Product;



/**
 * Application Lifecycle Listener implementation class MyservletContextListener
 *
 */
@WebListener
public class MyservletContextListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent e)  { 
         // TODO Auto-generated method stub
    	ServletContext context = e.getServletContext();
    	String contextPath = context.getContextPath();
    	context.setAttribute("app", contextPath);
    	
    	
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }


	
}
