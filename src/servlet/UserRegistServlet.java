package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

//¿ØÖÆ²ã
public class UserRegistServlet extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		int role=0;
		//
		User user=new User();
		user.setUname(uname);
		user.setPhone(phone);
		user.setEmail(email);
		user.setUpwd(upwd);
		user.setRole(role);
		//
		UserDao ud=new UserDao();
		try {
			ud.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("login.jsp");
		
	}
}
