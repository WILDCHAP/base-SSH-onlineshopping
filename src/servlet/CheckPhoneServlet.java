package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class CheckPhoneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone=request.getParameter("phone");
		UserDao dao=new UserDao();
		User user=dao.ajaxCheckPhone(phone, 0);
		Writer out=response.getWriter();
		if(user==null){
			out.write("no");
		}else{
			out.write("yes");
		}
		out.close();
	}

}