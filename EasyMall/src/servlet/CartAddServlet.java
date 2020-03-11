package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Dao.ProductDao;
import entity.Product;

/**
 * Servlet implementation class CartAddServlet
 */
@WebServlet("/CartAddServlet")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddServlet() {
        super();
        // TODO Auto-generated constructor stub
        //String byNum=Integer.parseInt(Request.get)
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取商品id和种类
		Integer buyNum=Integer.parseInt(request.getParameter("buyNum"));	
		String pid=request.getParameter("pid");
		
		Product prod=ProductDao.selectById(pid);
		
		HttpSession session=request.getSession(false);
		Map<Product, Integer> cartmap=(Map<Product, Integer>)session.getAttribute("cartmap");
		
		if(buyNum<0) {
			
			cartmap.remove(prod);
			System.out.println(cartmap);
		}else if(buyNum==0){
			
				
		}else{
				System.out.println(cartmap);
				//cartmap.put(prod, cartmap.containsKey(prod)?cartmap.get(prod)+buyNum:buyNum);
				cartmap.put(prod, cartmap.containsKey(prod) ? cartmap.get(prod) + buyNum : buyNum);
			}
			
			
			
		request.getRequestDispatcher("/EasyMall/cart/cart.jsp").forward(request, response);
		//JOptionPane.showMessageDialog(null, "加入购物车成功");
		//response.sendRedirect(request.getContextPath()+"/EasyMall/cart/cart.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
