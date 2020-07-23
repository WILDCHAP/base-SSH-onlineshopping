package servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import javafx.beans.value.WritableBooleanValue;
import pojo.User;

public class CheckUpwdServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user=(User) request.getSession().getAttribute("user");
		
		String upwd=request.getParameter("upwd");
		
		UserDao dao=new UserDao();
		
		User result=null;
		try {
			result = dao.ajaxCheckUpwd(upwd,user.getPhone(),user.getRole());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(result==null);
		System.out.println(user.getUname());
		Writer out=response.getWriter();
		if(result!=null){
			out.write("yes");
		}else{
			out.write("no");
		}
		out.close();
		
	}

}
