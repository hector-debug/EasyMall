package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import Dao.USerDao;
import entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		String remember=request.getParameter("remname"); 
		//记住用户名功能
		Cookie namecookie=new Cookie("name",name);
		Cookie pwdcookie = new Cookie("pwd",pwd);
		Cookie renamecookie = new Cookie("rename",remember);
		
		
		response.addCookie(namecookie);
		response.addCookie(pwdcookie);
		response.addCookie(renamecookie);
		
		
		if(USerDao.selectbynameandpwd(name, pwd)!=null) {
			User user=USerDao.selectbynameandpwd(name, pwd);
			request.getSession().setAttribute("user",user);
			response.sendRedirect("EasyMall/index/index.jsp");
		}else {
			JOptionPane.showMessageDialog(null, "账号或密码错误");
			//request.setAttribute("msg", "用户名不能为空");
			//response.sendRedirect("/EasyMall/EasyMall/login/login.jsp");
			request.getRequestDispatcher("/EasyMall/login/login.jsp").forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
