package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class UserLoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡҳ����������
		request.setCharacterEncoding("utf-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		int role=0;
		//����dao��ķ���
		UserDao dao= new UserDao();
		User user=dao.selectByLogin(uname, upwd, role);
		//ҳ����ת
		if(user!=null){
			request.getSession().setAttribute("user", user);//��¼��һ��
			//��ʽ��ת
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}
		else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
