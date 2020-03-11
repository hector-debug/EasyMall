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
		//�����������Ҫ������֤��ͼƬ��	
				response.setDateHeader("Expires",-1);
				response.setHeader("Cache-Control", "no-cache");
				
				VerifyCode vc=new VerifyCode();		
				//��ͼƬ���浽response�������У�����Ӧ�������
				vc.drawImage(response.getOutputStream());
				
				//��ȡͼƬ�ϵ���֤��
				String code=vc.getCode();
				//����֤���ı����浽session�С����ں��ڵ�У��
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
