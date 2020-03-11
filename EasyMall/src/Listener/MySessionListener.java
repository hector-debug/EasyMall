package Listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.Product;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
//@WebListener
public class MySessionListener implements HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	
    	se.getSession().setAttribute("cartmap", new HashMap<Product, Integer>());
    }

	
	
}
