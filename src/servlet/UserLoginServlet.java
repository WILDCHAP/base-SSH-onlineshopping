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
		//获取页面中在数据
		request.setCharacterEncoding("utf-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		int role=0;
		//调用dao层的方法
		UserDao dao= new UserDao();
		User user=dao.selectByLogin(uname, upwd, role);
		//页面跳转
		if(user!=null){
			request.getSession().setAttribute("user", user);//记录第一次
			//正式跳转
			request.getRequestDispatcher("allBookServlet").forward(request, response);
		}
		else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
