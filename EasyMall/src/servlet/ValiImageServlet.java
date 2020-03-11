package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.VerifyCode;

/**
 * Servlet implementation class ValiImageServlet
 */
@WebServlet("/ValiImageServlet")
public class ValiImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//控制浏览器不要缓存验证码图片。	
				response.setDateHeader("Expires",-1);
				response.setHeader("Cache-Control", "no-cache");
				
				VerifyCode vc=new VerifyCode();		
				//将图片保存到response缓冲区中，再响应给浏览器
				vc.drawImage(response.getOutputStream());
				
				//获取图片上的验证码
				String code=vc.getCode();
				//将验证码文本保存到session中。用于后期的校验
				request.getSession().setAttribute("code",code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
