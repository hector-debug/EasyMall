package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProductDao;
import entity.Product;

/**
 * Servlet implementation class UpdateBuyNum
 */
@WebServlet("/UpdateBuyNum")
public class UpdateBuyNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBuyNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String pid = request.getParameter("pid");
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		Product prod=ProductDao.selectById(pid);
		HttpSession session=request.getSession(false);
		Map<Product, Integer> cartmap=(Map<Product, Integer>)session.getAttribute("cartmap");
		cartmap.put(prod,buyNum);
		System.out.println("test");
		request.getRequestDispatcher("/EasyMall/cart/cart.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
