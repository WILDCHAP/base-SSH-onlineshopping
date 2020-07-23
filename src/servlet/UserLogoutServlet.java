package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//移出session对象
		HttpSession session=request.getSession();
		System.out.println(session.getId());
		request.getSession().removeAttribute("user");
		//页面跳转
		response.sendRedirect("login.jsp");
		
	}
	
}
