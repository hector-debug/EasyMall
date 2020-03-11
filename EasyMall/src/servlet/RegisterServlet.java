package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import Dao.Connect;
import Dao.USerDao;
import entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		//response.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		String nickname = request.getParameter("nickname");
		String email=request.getParameter("email");
		User user = new User(0, username, password, nickname, email);
		if(password.equals(password2))
		{
			if(USerDao.selectUserbyName(username)==null) {
				if(USerDao.addUSer(user));
					response.getWriter().print("注册成功");
					System.out.println(user);
			}else {
				JOptionPane.showMessageDialog(null, "用户名存在");
				//response.setHeader("refresh", "3;url=/EasyMall/EasyMall/regist/regist.jsp");

				response.sendRedirect("/EasyMall/EasyMall/regist/regist.jsp");
			}
		} else {
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			//response.setHeader("refresh", "3;url=/EasyMall/EasyMall/regist/regist.jsp");
			response.sendRedirect("/EasyMall/EasyMall/regist/regist.jsp");
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
