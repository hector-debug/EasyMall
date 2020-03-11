package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ProductDao;

/**
 * Servlet implementation class prodlist
 */
@WebServlet("/prodlist")
public class prodlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prodlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		// 1.获取参数(商品名称、商品分类、最低价格、最高价格)
				String name = request.getParameter("name");
				String category = request.getParameter("category");
				String minPrice = request.getParameter("minprice");
				String maxPrice = request.getParameter("maxprice");
				// 2.为搜索条件设置默认值, 并检查条件是否合法
				String _name = "";
				String _category = "";
				double _minPrice = 0;
				double _maxPrice = Double.MAX_VALUE;
				
				//3.检查条件是否合法
				if(name!=null&&!"".equals(name.trim())) {
					_name=name;
				}
				if(category!=null&&!"".equals(category.trim())) {
					_category=category;
				}
				String reg = "^\\d+$";
				if(minPrice!=null&&!"".equals(minPrice.trim())&&minPrice.matches(reg)) {
					_minPrice=Double.parseDouble(minPrice);
				}
				if(maxPrice!=null&&!"".equals(maxPrice.trim())&&maxPrice.matches(reg)) {
					_maxPrice=Double.parseDouble(maxPrice);
				}
				
				request.setAttribute("list",ProductDao.selectprod(_name, _category, _minPrice, _maxPrice) );
				request.getRequestDispatcher("/EasyMall/prodlist/prod_list.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
